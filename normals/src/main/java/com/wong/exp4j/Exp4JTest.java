package com.wong.exp4j;

import org.junit.Test;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

/**
* @author HuangZhibin
* 
* 2018年9月10日 下午2:22:49
*/
public class Exp4JTest {

	@Test
	public void test1() {
		String exp = "x + (y - z) * b + a";
		
		Expression e = new ExpressionBuilder(exp)
		        .variables("x", "y", "z", "a", "b")
		        .build()
		        .setVariable("x", 1)
		        .setVariable("y", 2)
		        .setVariable("z", 3)
		        .setVariable("a", 4)
		        .setVariable("b", 5.5);
		
		double result = e.evaluate();
		
		System.out.println(result);
	}
	
	@Test
	public void test2() {
		// 自定义操作符
		Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

		    @Override
		    public double apply(double... args) {
		        final int arg = (int) args[0];
		        if ((double) arg != args[0]) {
		            throw new IllegalArgumentException("Operand for factorial has to be an integer");
		        }
		        if (arg < 0) {
		            throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
		        }
		        double result = 1;
		        for (int i = 1; i <= arg; i++) {
		            result *= i;
		        }
		        return result;
		    }
		};

		double result = new ExpressionBuilder("4!")
		        .operator(factorial)
		        .build()
		        .evaluate();

		System.out.println(result);
	}
	
	@Test
	public void test3() {
		// 自定义函数
		Function avg = new Function("avg", 2) {

		    @Override
		    public double apply(double... args) {
		        double sum = 0;
		        for (double arg : args) {
		            sum += arg;
		        }
		        return sum / args.length;
		    }
		};
		double result = new ExpressionBuilder("avg(x, y)")
		        .function(avg)
		        .variables("x", "y")
		        .build()
		        .setVariable("x", 5)
		        .setVariable("y", 3)
		        .evaluate();
		
		System.out.println(result);
	}
	
	@Test
	public void test4() {
		Orders orders = new Orders();
		orders.setPrice(15.5);
		orders.setCount(5);
		orders.setFee(10);
		orders.setDiscount(5);
		orders.setSale(0.95);
		
		orders.calculate();
		System.out.println(orders.getNeedPay());
		
	}
	
}
