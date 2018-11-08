package com.phs.esl.canal.database.entry;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午1:37:52
*/
@Getter
@Setter
public class MyTable {

	private String tableName;
	
	private List<MyColumn> columns;
}
