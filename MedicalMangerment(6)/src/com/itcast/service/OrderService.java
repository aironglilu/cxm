package com.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itcast.Dao.OrderDao;
import com.itcast.entity.Orders;

@Transactional
public class OrderService {
	//orderDao
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 进货
	 * @return
	 */
	public String importGoods() {
		
		
		return "importGoods";
	}

	/**
	 * 生成订单
	 * @param order
	 */
	public void addOrder(Orders orders) {

		orderDao.addOrder(orders);
	}

	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Orders> findAllOrder() {
		return orderDao.findAllOrder();
	}

	/**
	 * 通过编号查询所有订单
	 * @param orderCode
	 * @return
	 */
	public List<Orders> findOrderList(String orderCode) {
		return orderDao.findOrderList(orderCode);
	}
}
