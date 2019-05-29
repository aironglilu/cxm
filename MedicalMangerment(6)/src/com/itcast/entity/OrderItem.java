package com.itcast.entity;

public class OrderItem {

		//订单 详情 
		private String orName;//订单药品名称
		private double orUnitPrice; //单价
		private String orValDate;//有效期
		private String orProductDate;//出厂日期
		private Integer ornum;//数量
		private double orsum;//总价

		private String sname;//药品类型id
		private String tname;
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
		public String getOrProductDate() {
			return orProductDate;
		}
		public void setOrProductDate(String orProductDate) {
			this.orProductDate = orProductDate;
		}
		public Integer getOrnum() {
			return ornum;
		}
		public void setOrnum(Integer ornum) {
			this.ornum = ornum;
		}
		
		//设置总价
		public double getOrsum() {
			return ornum*orUnitPrice;
		}

		
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		@Override
		public String toString() {
			return "OrderItem [orName=" + orName + ", orUnitPrice=" + orUnitPrice + ", orValDate=" + orValDate
					+ ", orProductDate=" + orProductDate + ", ornum=" + ornum + ", orsum=" + orsum + ", sname=" + sname
					+ ", tname=" + tname + "]";
		}
		
}
