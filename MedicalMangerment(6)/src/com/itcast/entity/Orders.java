package com.itcast.entity;

public class Orders {
	private Integer oid;// 采购订单Id
	private String orderCode;// 订单编号
	private double orderSum;// 采购的总价
	private String orderDate;// 订单生成时间
	private String orderName;//购买人
	
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public double getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(double orderSum) {
		this.orderSum = orderSum;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderCode=" + orderCode + ", orderSum=" + orderSum + ", orderDate=" + orderDate
				+ ", orderName=" + orderName + "]";
	}
	
	
	
	
	


	

}
