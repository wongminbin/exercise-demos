package com.wong.poi.fuckcccs;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.alibaba.fastjson.annotation.JSONField;
import com.wong.poi.anno.Column;

import lombok.Getter;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年7月25日 上午9:06:11
*/
@Entity
@Getter
@Setter
public class Medicine {

	@Id
	@JSONField(serialize=false, deserialize=false)
	private ObjectId id;
	
	@Column("item")
	private String publicity;
	
	@Column("item-href")
	private String href;
	
	@Column("序号")
	private String serial;
	
	@Column({"制品名称", "产品名称"})
	private String medicine;
	
	@Column("规格")
	private String size;
	
	@Column("批号")
	private String bathLot;
	
	@Column({"批量/进口量", "签发量"})
	private String count;
	
	@Column("批量（人份）")
	private String lot;
	
	@Column("人份剂量")
	private String dosage;
	
	@Column("有效期至")
	private String validatity;
	
	@Column("生产企业")
	private String company;
	
	@Column("签发结论")
	private String result;
	
	@Column({"检品编号", "收检编号"})
	private String productSerial;
	
	@Column({"批签发证号", "证书编号"})
	private String issueSerial;
	
	@Column({"报告书编号", "报告编号"})
	private String reportSerial;
	
	@Column("签发日期")
	private String reportDate;
	
}
