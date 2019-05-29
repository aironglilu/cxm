package com.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itcast.Dao.OrderDetailDao;
import com.itcast.entity.OrderDetail;

@Transactional
public class OrderDetailService {

	//注入
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void addOrderDetail(OrderDetail orderDetail) {

		orderDetailDao.addOrderDetail(orderDetail);
	}

	public List<OrderDetail> findOrderDetail(String orderCode) {
		return orderDetailDao.findOrderDetail(orderCode);
	}

	
	
	
}
