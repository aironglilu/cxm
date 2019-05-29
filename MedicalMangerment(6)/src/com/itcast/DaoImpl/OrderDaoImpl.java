package com.itcast.DaoImpl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.itcast.Dao.OrderDao;
import com.itcast.entity.Orders;

public class OrderDaoImpl implements OrderDao {


	//在Dao中注入hibernateTemplate对象
			private HibernateTemplate hibernateTemplate;
			
			public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
				this.hibernateTemplate = hibernateTemplate;
			}
			


	/**
	 * 进货
	 */
	@Override
	public String importGoods() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * 生成订单
	 */
	@Override
	public void addOrder(Orders orders) {
		System.out.println("daoiml..");
		 hibernateTemplate.save(orders);
		 System.out.println("daoiml..出来了吗");
		 
	}


	/**
	 * 查询所有订单
	 */
	@Override
	public List<Orders> findAllOrder() {
		List<Orders> find = (List<Orders>) hibernateTemplate.find("from Orders");
		return find;
	}


	/**
	 * 通过编号查询所有订单
	 */
	@Override
	public List<Orders> findOrderList(String orderCode) {
		List<Orders> orderList = (List<Orders>) hibernateTemplate.find("from Orders where orderCode=?", orderCode);
		return orderList;
	}

}
