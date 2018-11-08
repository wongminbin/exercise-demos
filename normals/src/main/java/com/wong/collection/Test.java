package com.wong.collection;

import java.util.Arrays;

import com.wong.test.Person;

/**
* @author Huang zhibin
* 
* 2017年10月11日 上午9:07:27
*/
public class Test {

	public static void main(String[] args) {
		
//		List<Person> extendList = new ArrayList<>();
//		
//		for (Person person : extendList) {
//			person.getName();
//		}
//		
//		List<Person> superList = new ArrayList<>();
//		superList.add(new Son());
//		superList.add(new Person());
//		
//		
//		Collections.copy(superList, extendList);
		
		Person[] persons = new Person[3];
		persons[0] = new Person();
		persons[1] = new Person();
		persons[2] = new Person();
		for (Person person : persons) {
			System.out.println(person);
		}
		
		Person[] newPersons = Arrays.copyOf(persons, 3);
		for (Person person : newPersons) {
			System.out.println(person);
		}
	}
	
}
