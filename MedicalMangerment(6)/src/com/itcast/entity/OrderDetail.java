package com.itcast.entity;

import java.util.Date;

public class OrderDetail {
	//订单 详情 
	private Integer orderId;//采购订单主键
	private String ordernumber; //采购订单编号
	private String orName;//药品名称
	private double orUnitPrice; //单价
	private String orValDate;//保质期
	private String orProductDate;//生产日期
	private Integer ornum;//数量
	private double orsum;//总价
	private Supplier sid;//供应商实体属性
	private Type tid;	//类别实体属性
	
	
	
	

	public String getOrProductDate() {
		return orProductDate;
	}
	
	public void setOrProductDate(String orProductDate) {
		this.orProductDate = orProductDate;
	}
	public Supplier getSid() {
		return sid;
	}
	public void setSid(Supplier sid) {
		this.sid = sid;
	}
	public Type getTid() {
		return tid;
	}
	public void setTid(Type tid) {
		this.tid = tid;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getOrName() {
		return orName;
	}
	public void setOrName(String orName) {
		this.orName = orName;
	}
	public double getOrUnitPrice() {
		return orUnitPrice;
	}
	public void setOrUnitPrice(double orUnitPrice) {
		this.orUnitPrice = orUnitPrice;
	}
	public String getOrValDate() {
		return orValDate;
	}
	public void setOrValDate(String orValDate) {
		this.orValDate = orValDate;
	}

	public Integer getOrnum() {
		return ornum;
	}
	public void setOrnum(Integer ornum) {
		this.ornum = ornum;
	}
	public double getOrsum() {
		return orsum;
	}
	public void setOrsum(double orsum) {
		this.orsum = orsum;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", ordernumber=" + ordernumber + ", orName=" + orName
				+ ", orUnitPrice=" + orUnitPrice + ", orValDate=" + orValDate + ", orProductDate=" + orProductDate
				+ ", ornum=" + ornum + ", orsum=" + orsum + ", sid=" + sid + ", tid=" + tid + "]";
	}

	

}
