package com.wong.feature;

import java.util.concurrent.ThreadLocalRandom;

/**
* @author HuangZhibin
* 
* 2017年12月14日 下午3:09:39
*/
public class MathOperate {
	
	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
	
	public static OperateEnum getRandomOperate() {
		return OperateEnum.values()[RANDOM.nextInt(OperateEnum.values().length)];
	}
	
	public enum OperateEnum {
		PLUS((a, b) -> a + b, "%d + %d = "),
		MINUS((a, b) -> a - b, "%d - %d = "),
		MULTIPLY((a, b) -> a * b, "%d * %d = ");
		
		private Operate operate;
		private String exp;
		private OperateEnum(Operate operate, String exp) {
			this.operate = operate;
			this.exp = exp;
		}
		
		public int operate(int a, int b) {
			return operate.operate(a, b);
		}
		
		public String getExp(int a, int b) {
			return String.format(exp, a, b);
		}
	}
	
	public interface Operate {
		int operate(int num1, int num2);
	}
}
