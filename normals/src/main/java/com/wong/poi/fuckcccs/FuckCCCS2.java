package com.wong.poi.fuckcccs;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wong.poi.anno.Column;
import com.wong.poi.mongo.Mongodb;
import com.wong.poi.mongo.MongodbConfig;
import com.wong.poi.mongo.MongodbFactory;

/**
* @author HuangZhibin
* 
* 2018年7月24日 下午7:00:30
*/
public class FuckCCCS2 {
	private String[] years = {"2015", "2016", "2017", "2018"};
	private Map<String, Field> titles = new HashMap<>();
	private Map<CellType, Function<Cell, String>> invokes = new HashMap<>();
	private Mongodb mongo;
	
	@Before
	public void init() {
		System.out.println("................init titles");
		Field[] fields = Medicine.class.getDeclaredFields();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				field.setAccessible(true);
				for (String name : column.value()) {
					titles.put(name, field);
				}
			}
		}
	}
	
	@Before
	public void init2() {
		System.out.println("................init invokes");
		
		invokes.put(CellType.NUMERIC, cell->{
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");
			} else if (cell.getCellStyle().getDataFormat() == 31) {
				// 自定义时间日期
				return DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");
			}
			
			return new BigDecimal(cell.getNumericCellValue()).toString();
		});
		
		invokes.put(CellType.STRING, cell->{
			String str = cell.getStringCellValue();
			if ("null".equals(str)) {
				return StringUtils.EMPTY;
			}
			return str;
		});
		
		invokes.put(CellType.BLANK, cell->StringUtils.EMPTY);
	}
	
	@Before
	public void init3() {
		System.out.println("................init mongo");
		mongo = MongodbFactory.getDatastore(new MongodbConfig("10.10.0.205", 27017, 
				"phs_erp_dev", "74uRtG58qaz^%$#", "phs_erp_dev", "com.wong.poi.fuckcccs"));
	}
	

	@Test
	public void fuck() throws Exception {
		String path = "D:\\用户目录\\下载\\nifdc-bioproducts-test-results-master\\中检院生物制品批签发信息公示表-%s年.xlsx";
		for (String year : years) {
			
			Workbook workbook = WorkbookFactory.create(new File(String.format(path, year)));
			
			Sheet sheet = workbook.getSheetAt(0);
			
			System.out.println(String.format("%s -- %d", year, sheet.getLastRowNum()));
			
			List<String> titles = new ArrayList<>();
			List<Medicine> medicines = new ArrayList<>(sheet.getLastRowNum());
			sheet.forEach(row -> {
				if (row.getRowNum() == 0) {
					row.forEach(cell->titles.add(cell.getStringCellValue()));
				} else {
					Medicine medicine = new Medicine();
					row.forEach(cell-> {
						int index = cell.getColumnIndex();
						Field field = this.titles.get(titles.get(index));
						if (field != null) {
							try {
								field.set(medicine, invokes.get(cell.getCellTypeEnum()).apply(cell));
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
					medicines.add(medicine);
				}
			});
			
			mongo.getDatastore().save(medicines);
		}
	}
	
	@After
	public void close() {
		System.out.println("................close mongo");
		mongo.getClient().close();
	}
}
