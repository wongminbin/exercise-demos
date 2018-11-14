package com.phs.esl.canal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.phs.esl.canal.dao.IBaseDao;
import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;
import com.phs.esl.canal.database.entry.parse.impl.ParseDeteleStatement;
import com.phs.esl.canal.service.AbstractBaseService;
import com.phs.esl.canal.service.IBaseService;

/**
* @author HuangZhibin
* 
* 2018年10月26日 上午9:11:02
*/

@Service("deleteService")
@Transactional(rollbackFor = Exception.class)
public class DeleteServiceImpl extends AbstractBaseService implements IBaseService<MyTable> {
	
//	private static final String TEMPLATE = "UPDATE TABLE SET update_user = -1, update_time = NOW()";
//		修改SQL，delete转update
//		SQLDeleteStatement delete = SQLParserUtils.createSQLStatementParser(sql, JdbcConstants.MYSQL)
//				.parseDeleteStatement();
//		
//		String table = Optional.ofNullable(delete.getFrom()).map(f->f.toString()).orElse(delete.getTableName().getSimpleName());
//		
//		SQLUpdateStatement update = SQLParserUtils.createSQLStatementParser(TEMPLATE.replace("TABLE", table), 
//				JdbcConstants.MYSQL).parseUpdateStatement();
//		update.setWhere(delete.getWhere());
	
	@Autowired
	private IBaseDao baseDao;

	private ParseStatement parse = new ParseDeteleStatement();
	
	@Override
	public void execute(String sql) {
		getLogger().debug(sql);
		
		try {
			baseDao.updateCommondSql(sql);
		} catch (Exception e) {
			getLogger().error(sql, e);
		}
	}
	
	@Override
	protected void executePlaceholder(Map<String, Object> holders) {
		try {
			baseDao.updateKeyValue(holders);
		} catch (Exception e) {
			getLogger().error(JSON.toJSONString(holders), e);
		}
	}
	
	@Override
	protected IBaseDao getDao() {
		return baseDao;
	}

	@Override
	protected ParseStatement getParse() {
		return parse;
	}

	@Override
	protected boolean isExecute(MyTable table) {
		return true;
	}

}
