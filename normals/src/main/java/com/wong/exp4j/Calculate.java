package com.wong.exp4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.objecthunter.exp4j.ExpressionBuilder;

/**
* @author HuangZhibin
* 
* 2018年9月11日 上午9:44:23
*/
public interface Calculate {

	String getCalculateExp();
	
	default double calculate() {
		return new ExpressionBuilder(getCalculateExp())
				.variables(calculateVars())
				.build()
				.setVariables(calculateValues())
				.evaluate();
	}
	
	default Set<String> calculateVars() {
		Set<String> vars = new HashSet<>();
		for (Field field : getClass().getDeclaredFields()) {
			if (field.getDeclaredAnnotation(Var.class) != null) {
				vars.add(field.getName());
			}
		}
		return vars;
	}
	
	default Map<String, Double> calculateValues() {
		Map<String, Double> values = new HashMap<>();
		for (String fieldName : calculateVars()) {
			try {
				Object value = FieldUtils.readDeclaredField(this, fieldName, true);
				values.put(fieldName, Double.valueOf(value.toString()));
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return values;
	}
}
