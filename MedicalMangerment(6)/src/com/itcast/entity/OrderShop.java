package com.itcast.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderShop implements Serializable{

	private Map<String, OrderItem> map =new LinkedHashMap<String, OrderItem>();

	private Double total=0.0; //总价
	
	public Collection<OrderItem> getOrderItems(){
		return map.values();
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	//订单购物项加入到购物车
	public void addOrderShop(OrderItem orderItem) {
		String name =orderItem.getOrName();
		if(map.containsKey(name)) {
			OrderItem orderItemOld= map.get(name);
			orderItemOld.setOrnum(orderItemOld.getOrnum()+orderItem.getOrnum());
		}else {
			map.put(name, orderItem);
		}
		total+=orderItem.getOrsum();
		System.out.println(map);
	}
	
	
	//删除单个购物项
	public void removeOrderShopItem(String name) {
		OrderItem orderItem = map.remove(name);
		total-=orderItem.getOrsum();
	}
	
	//清空购物车
	public void clearOrderShop() {
		map.clear();
		total=0.0;
	}
}
