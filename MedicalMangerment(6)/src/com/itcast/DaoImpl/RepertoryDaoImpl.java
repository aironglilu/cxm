package com.itcast.DaoImpl;

import java.util.List;

import javax.persistence.criteria.From;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.itcast.Dao.RepertoryDao;
import com.itcast.entity.Good;
import com.itcast.entity.OrderDetail;
import com.itcast.entity.Repertory;

public class RepertoryDaoImpl implements RepertoryDao {

	// 在RepertoryDaoImpl注入 hibernate 并创建set
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

//  查询 仓库所有信息      和Repertory连接
	@Override
	public List<Good> findrepertoryall() {
		// TODO Auto-generated method stub
		System.out.println("repertorImpl--------");
//c inner join fetch c.good  c inner join fetch c.repertory      from Good  不能内连接不行
		List<Good> findGood = (List<Good>) hibernateTemplate.find("from Good c inner join fetch c.repertory ");

		System.out.println("能到吗。。。");
//		for (Good repertory : findGood) {
//			System.out.println("商品名"+repertory.getGname());
//		}

		return findGood;
	}

////查询是否存在 订单编号
	@Override
	public List<OrderDetail> selectorder2(String ordernumberr) {
		// TODO Auto-generated method stub

		System.out.println("repertorImpl 查询--------");
		//
		List<OrderDetail> findorder =  (List<OrderDetail>) hibernateTemplate.find("from OrderDetail where ordernumber=?",ordernumberr);
       for (OrderDetail orderetial : findorder) {
		     System.out.println("实现成打印"+orderetial.toString());
	}
		System.out.println("能到吗。。。实现出");
		return findorder;

	}

	@Override
	public void increasedate2(Repertory repertory) {
		// TODO Auto-generated method stub
		System.out.println("添加入商品impl 进入");
		hibernateTemplate.save(repertory);
		System.out.println("添加入商品impl......");
		
	}

	@Override
	public List<Repertory> repertoryall2() {
		// TODO Auto-generated method stub
		System.out.println("repertorImpl--------");
		//c inner join fetch c.good  c inner join fetch c.repertory      from Good  不能内连接不行
				List<Repertory> findRepertory = (List<Repertory>) hibernateTemplate.find("from Repertory");

				System.out.println("能到吗。。。");
//				for (Good repertory : findGood) {
//					System.out.println("商品名"+repertory.getGname());
//				}

				return findRepertory;
	}

	@Override
	public List<Repertory> mohuselectDao(String begintime, String endtime) {
		// TODO Auto-generated method stub
		
		//List<Repertory> mohuselect=hibernateTemplate.find("from Repertory where rdate between '"+?+"' and '"+?+"'",begintime,endtime);
		
		
		
		return null;
	}

	
	@Override
	public void insertrepertory(Repertory repertory) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(repertory);
	}
	
	
	/**
	 * 上架商品
	 * @return
	 */
	public void onshowGoods(Repertory repertory,Integer num) {
		
		//查找所有已上架的商品
		List<Good> findGoods = (List<Good>) hibernateTemplate.find("from Good");
		int i = 0;
		
		if(num <= repertory.getRtotal()) {
		
			for (Good good : findGoods) {
				//如果商品名一样
				if(good.getGname().equals(repertory.getRproduct())) {
					System.out.println("商品名已经存在！！");
					
					//判断输入的上架商品是否小于仓库总商品
					
						//上架增加新的库存
						Integer newStock = good.getStock()+num;
						good.setStock(newStock);
					
			
					i++;
				}
			}
			
			if(i == 0) {
				System.out.println("创建商品！！");
				Good addgood = new Good();
				//产品名
				addgood.setGname(repertory.getRproduct());
				//总数
				addgood.setStock(num);
				//单价
				addgood.setPurchasePrice(repertory.getRunit());
				//保质期
				addgood.setQualitaDate(repertory.getRbdate());
				//类型
				addgood.setType(repertory.getRtype());
				//供应商
				addgood.setSid(repertory.getRput());
				//生产日期
				addgood.setDate(repertory.getRbrithdate());
				//进货价
				addgood.setPurchasePrice(repertory.getRunit());
				
				hibernateTemplate.save(addgood);
				
				
			}
		
		}else {
			System.out.println("输入数量超出范围！");
			num = 0;
		}
		
		
			//仓库表减库存
			Integer newReInteger = repertory.getRtotal()-num;
			repertory.setRtotal(newReInteger);
			hibernateTemplate.saveOrUpdate(repertory);
				
		
		System.out.println(repertory.toString());
		System.out.println("上架商品impl结束。。。。。。。。。。。。。。。。。");
		
		
	}
	
	/**
	 * 下架商品
	 * @return
	 */


	@Override
	public Integer outshowGoods(Integer id, Integer num) {
		
			System.out.println("下架商品impl..............");

			//查找所有已上架的商品
				List<Good> findGoods = (List<Good>) hibernateTemplate.find("from Good");
				
				
				//查找要下架的商品名
				Repertory repertory2 = hibernateTemplate.get(Repertory.class, id);
				
				int i = 0;
				
				
				for (Good good : findGoods) {
					//如果是药品名相同
					if (good.getGname().equals(repertory2.getRproduct())) {
						//如果输入的下架数量小于或者货架上的数量
						if(num <= good.getStock()) {
							
							//上架减库存
							Integer newStock = good.getStock() - num;
							
							good.setStock(newStock);
							hibernateTemplate.saveOrUpdate(good);
	
							if(good.getStock() <= 0) {
								//如果数量为0.删除上架的药品
								hibernateTemplate.delete(good);
								
							}
							
							
							//仓库表加库存
							Integer newReInteger = repertory2.getRtotal() + num;
							repertory2.setRtotal(newReInteger);
							hibernateTemplate.saveOrUpdate(repertory2);
							
						}
						
						i++;
						
					}
					
				}
				
				//货架上不存在该商品
				if(i == 0) {
					
					System.out.println("药品暂时未出售！！");
					
				}
				
				System.out.println("上架商品impl结束。。。。。。。。。。。。。。。。");
				
				return repertory2.getRtotal();
				
				
		
	}
	
	


}
