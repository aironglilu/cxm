 package com.itcast.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 药品类别实体类
 * @author 50339
 *
 */
public class Type {
	private Integer tid;
	private String tname;
	
	//配置一对多关系
	private Set<Good> setgoods = new HashSet<Good>();
	
	
	//配置一对多关系
	private Set<OrderDetail> orderDetialgoods = new HashSet<OrderDetail>();
	
	/**
	 * 进货商品详情set
	 * @return
	 */
	
	public void setOrderDetialgoods(Set<OrderDetail> orderDetialgoods) {
		this.orderDetialgoods = orderDetialgoods;
	}
	public void setSetgoods(Set<Good> setgoods) {
		this.setgoods = setgoods;
	}
	
	
	
	
	/**
	 * 商品set
	 * @return
	 */
	public Set<Good> getSetgoods() {
		return setgoods;
	}
	public Set<OrderDetail> getOrderDetialgoods() {
		return orderDetialgoods;
	}
	
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	@Override
	public String toString() {
		return "Type [tid=" + tid + ", tname=" + tname + "]";
	}
	
	
	
	
}
