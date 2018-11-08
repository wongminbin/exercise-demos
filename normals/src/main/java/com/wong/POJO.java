package com.wong;

import lombok.Getter;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年7月5日 下午5:03:33
*/
@Getter
@Setter
public class POJO {

	private String age;
	
	private String name;
	
	private double score;
	
	private Test2 test2;
	
	public class Test2 {
		private String address;
		
		public Test2() {
			
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			POJO.super.hashCode();
			POJO.this.hashCode();
			this.address = POJO.this.age;
			this.address = address;
		}
		
		@Override
		public String toString() {
			return "address is " + address;
		}
	}
}
