package com.wong.groovy

/**
 * @author Huang zhibin
 * 
 * 2017年9月5日 下午7:00:35
 */
class GroovyDemo {
	
	private def str = new StringBuilder();

	def repeat(val,repeat=3) {
		for (i in 0..<repeat) {
			println "This is ${i}:${val}"
		}
	}
}
