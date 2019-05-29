package com.itcast.DaoImpl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.itcast.Dao.OrderDetailDao;
import com.itcast.entity.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDao{
	//在Dao中注入hibernateTemplate对象
			private HibernateTemplate hibernateTemplate;
			
			public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
				this.hibernateTemplate = hibernateTemplate;
			}

			@Override
			public void addOrderDetail(OrderDetail orderDetail) {
				hibernateTemplate.save(orderDetail);
			}

			@Override
			public List<OrderDetail> findOrderDetail(String orderCode) {
				System.out.println(orderCode);
				List<OrderDetail> list = (List<OrderDetail>) hibernateTemplate.find("from OrderDetail where ordernumber=?", orderCode);
				System.out.println(list);
				return list;
			}
			
	
}
