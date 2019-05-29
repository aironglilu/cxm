package com.itcast.Dao;

import java.util.List;

import com.itcast.entity.Orders;

public interface OrderDao {
	

	/**
	 * 进货
	 * @return
	 */
	public String importGoods();

	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Orders orders);

	/**
	 * 查询所有订单列表
	 * @return
	 */
	public List<Orders> findAllOrder();

	/**
	 * 通过id查询所有订单
	 * @param orderCode
	 * @return
	 */
	public List<Orders> findOrderList(String orderCode);
	
}
