package com.itcast.entity;

public class IndentDetail {

	private Integer did;  		//订单详情号(唯一)
	private String indentid; 	//订单编号
	private Integer gid;		//药品号
	private String gname;		//药品名
	private Integer count;		//购买数量
	private Double price;		//药品单价
	private Double subtotal;	//药品小计(单个总价)
	private String tname;		//类别名
	private String sname;		//供应商名
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	
	
	public String getIndentid() {
		return indentid;
	}
	public void setIndentid(String indentid) {
		this.indentid = indentid;
	}
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "IndentDetail [did=" + did + ", indentid=" + indentid + ", gid=" + gid + ", gname=" + gname + ", count="
				+ count + ", price=" + price + ", subtotal=" + subtotal + ", tname=" + tname + ", sname=" + sname + "]";
	}
	
	
	
}
