package com.wong.poi.fuckcccs;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.alibaba.fastjson.annotation.JSONField;
import com.wong.poi.anno.Column;

import lombok.Setter;

import lombok.Getter;

/**
* @author HuangZhibin
* 
* 2018年7月25日 上午10:18:24
*/

@Entity
@Getter
@Setter
public class SalesStatus {
	
	@Id
	@JSONField(serialize=false, deserialize=false)
	private ObjectId id;

	@Column("name")
	private String medicine;
	
	@Column("src")
	private String product;

	@Column("create_company")
	private String createCompany;
	
	@Column("report_company")
	private String reportCompany;

	@Column("prov")
	private String prov;
	
	@Column("year")
	private String year;
	
	@Column("price")
	private String price;
}
