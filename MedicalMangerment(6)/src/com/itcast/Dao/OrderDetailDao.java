package com.itcast.Dao;

import java.util.List;

import com.itcast.entity.OrderDetail;

public interface OrderDetailDao {

	//加入采购订单详情
	void addOrderDetail(OrderDetail orderDetail);

	//查询订单详情
	List<OrderDetail> findOrderDetail(String orderCode);

}
