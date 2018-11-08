package com.wong.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年6月19日 上午11:46:32
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@NonNull
	private String name;
	
	private int age;
	
	private Gender gender;
	
	private String address;
	
	public enum Gender {
		MAIL,
		FEMAIL;
	}
}
