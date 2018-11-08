package com.wong.exp4j;

/**
* @author HuangZhibin
* 
* 2018年9月11日 上午9:31:21
*/
public class Orders implements Calculate {

	private String calculateExp = "price * count * sale - discount + fee";
	
	@Var
	private double price;
	
	@Var
	private int count;
	
	@Var
	private double fee;
	
	@Var
	private double discount;
	
	@Var
	private double sale;
	
	private double totalPay;
	
	private double needPay;
	
	@Override
	public double calculate() {
		needPay = Calculate.super.calculate();

		return needPay;
	}
	
	@Override
	public String getCalculateExp() {
		return calculateExp;
	}
	
	public void setCalculateExp(String calculateExp) {
		this.calculateExp = calculateExp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}

	public double getNeedPay() {
		return needPay;
	}

	public void setNeedPay(double needPay) {
		this.needPay = needPay;
	}
	
	
}
