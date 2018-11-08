package com.wong.feature;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.wong.feature.MathOperate.OperateEnum;

/**
* @author HuangZhibin
* 
* 2017年12月14日 下午3:57:10
*/
public class VerifyCodeUtil {
	
	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
	private static final List<Integer> SRC = new ArrayList<>(10);
	static {
		for (int i = 1; i <= 10; i++) {
			SRC.add(i);
		}
	}

	public static String create(OutputStream out) {
		OperateEnum operate = MathOperate.getRandomOperate();
		
		int a = RANDOM.nextInt(SRC.size()), 
			b = RANDOM.nextInt(SRC.size());
		
		String exp = operate.getExp(a, b);
		int result = operate.operate(a, b);
		
		try {
			ImageCodeUtil.create(exp, out, 200, 50);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return String.valueOf(result);
	}

}
