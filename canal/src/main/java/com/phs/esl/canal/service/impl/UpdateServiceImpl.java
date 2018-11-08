package com.phs.esl.canal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phs.esl.canal.dao.IBaseDao;
import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;
import com.phs.esl.canal.database.entry.parse.impl.ParseUpdateStatement;
import com.phs.esl.canal.service.AbstractBaseService;
import com.phs.esl.canal.service.IBaseService;

/**
* @author HuangZhibin
* 
* 2018年10月26日 上午9:11:02
*/

@Service("updateService")
@Transactional(rollbackFor = Exception.class)
public class UpdateServiceImpl extends AbstractBaseService implements IBaseService<MyTable> {
	
	@Autowired
	private IBaseDao baseDao;
	
	private ParseStatement parse = new ParseUpdateStatement();
	
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
	protected ParseStatement getParse() {
		return parse;
	}

	@Override
	protected boolean isExecute(MyTable table) {
		return table.getColumns().stream().filter(col -> col.isUpdate()).count() > 0;
	}
	
}
