package com.itcast.entity;


public class Good {
	private Integer gid;//药品编号
	private String gname;//药品名称	
	private Integer stock;//库存表id
	private Double price;//售价
	private Double purchasePrice;//进货价
	private String date;//生产日期
	private Integer qualitaDate;//保质期	
	private String purchaseNum;//进货编号
	private String sid;//供应商
	private String type;//类别id
	private String overTime;//距离过期时间
	
	
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getQualitaDate() {
		return qualitaDate;
	}
	public void setQualitaDate(Integer qualitaDate) {
		this.qualitaDate = qualitaDate;
	}
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	@Override
	public String toString() {
		return "Good [gid=" + gid + ", gname=" + gname + ", stock=" + stock + ", price=" + price + ", purchasePrice="
				+ purchasePrice + ", date=" + date + ", qualitaDate=" + qualitaDate + ", purchaseNum=" + purchaseNum
				+ ", sid=" + sid + ", type=" + type + ", overTime=" + overTime + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
