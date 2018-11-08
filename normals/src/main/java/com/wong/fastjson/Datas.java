package com.wong.fastjson;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年9月4日 下午3:53:52
*/
@Getter
@Setter
public class Datas {

	@JSONField(format="HH:mm")
	private Date date;
	
	
}