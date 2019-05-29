package com.itcast.Action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.itcast.entity.Admin;
import com.itcast.entity.OrderDetail;
import com.itcast.entity.OrderItem;
import com.itcast.entity.OrderShop;
import com.itcast.entity.Orders;
import com.itcast.entity.Supplier;
import com.itcast.entity.Type;
import com.itcast.service.OrderDetailService;
import com.itcast.service.OrderService;
import com.itcast.service.SupplierService;
import com.itcast.service.TypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Orders>{
	
	private OrderItem orderItem =new OrderItem();
	
	private OrderService orderService;
	
	private TypeService typeService;
	
	private SupplierService supplierService;
	
	private OrderDetailService orderDetailService;

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}



	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}


	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	private String orName;//订单药品名称
	private double orUnitPrice; //单价
	private String orValDate;//有效期
	private String orProductDate;//出厂日期
	private Integer ornum;//数量
	private String sname;
	private String tname;
	

	public void setOrName(String orName) {
		this.orName = orName;
	}

	public void setOrUnitPrice(double orUnitPrice) {
		this.orUnitPrice = orUnitPrice;
	}

	public void setOrValDate(String orValDate) {
		this.orValDate = orValDate;
	}

	public void setOrProductDate(String orProductDate) {
		this.orProductDate = orProductDate;
	}

	public void setOrnum(Integer ornum) {
		this.ornum = ornum;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	
	private String orderCode;
	
	


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}



	/**
	 * 进货
	 * @return
	 */
	public String importGoods() {
		List<Type> findAllType = typeService.findAllType();
		List<Supplier> allSuppliers = supplierService.allSuppliers();
		
		ActionContext.getContext().getValueStack().set("allSuppliers", allSuppliers);
		ActionContext.getContext().getValueStack().set("findAllType", findAllType);
		
		return "importGoods";
	}
	
	
	/**
	 * 加入到订单购物车
	 * @return
	 */
	public String addOrderShop() {
		System.out.println(orName+".."+orUnitPrice+".."+orValDate+".."+orProductDate+".."+ornum+".."+sname+".."+tname);
		orderItem.setOrName(orName);
		orderItem.setOrUnitPrice(orUnitPrice);
		orderItem.setOrValDate(orValDate);
		orderItem.setOrProductDate(orProductDate);
		orderItem.setOrnum(ornum);
		orderItem.setSname(sname);
		orderItem.setTname(tname);
		OrderShop orderShop=getOrderShop();
		orderShop.addOrderShop(orderItem);
		return "addOrderSuccess";
	}
	
	/**
	 * 删除单个订单购物项
	 * @return
	 */
	public String removeOrderShopItem() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String name;
		try {
			name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
			System.out.println("............"+name);

			OrderShop orderShop=getOrderShop();
			orderShop.removeOrderShopItem(name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "removeSuccess";
		
	}
	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearOrderShop() {
		OrderShop orderShop=getOrderShop();
		orderShop.clearOrderShop();
		return "clearSuccess";
	}
	/**
	 * 提交订单
	 * @return
	 */
	public String commit() {
		getOrderShop();
		return "commitSuccess";
	}
	/**
	 * 讲订单购物车放到session中
	 * @return
	 */
	public OrderShop getOrderShop() {
		OrderShop orderShop = (OrderShop)ServletActionContext.getRequest().getSession().getAttribute("orderShop");
		if(orderShop==null) {
		orderShop=new OrderShop();
		 ServletActionContext.getRequest().getSession().setAttribute("orderShop",orderShop);
		}
		return orderShop;
		
	}
	
	/**
	 * 加入到订单列表
	 * @return
	 */
	Orders orders =new Orders();
	
	
	public Orders getOrders() {
		return orders;
	}



	public void setOrders(Orders orders) {
		this.orders = orders;
	}


	@Override
	public Orders getModel() {
		// TODO Auto-generated method stub
		return orders;
	}

	public String addOrder() {
		//生成订单时间编号
		System.out.println("啦啦啦啦啦");
		SimpleDateFormat df =new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式, 编号
		SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式, 下单时间
		String ordernumber=df.format(new Date());
		String orderDate =date.format(new Date());
		OrderShop orderShop =(OrderShop) ServletActionContext.getRequest().getSession().getAttribute("orderShop");
		Admin admin =(Admin) ServletActionContext.getRequest().getSession().getAttribute("adminlogin");
		System.out.println(orderShop.getTotal()+".."+ordernumber+".."+orderDate+"..."+admin.getName());
		orders.setOrderSum(orderShop.getTotal()); 
		orders.setOrderCode(ordernumber);
		orders.setOrderDate(orderDate);
		orders.setOrderName(admin.getName());
		//添加订单
		System.out.println(orderShop.getTotal()+".."+ordernumber+".."+orderDate+"..."+admin.getName()+"111");
		System.out.println(orders);
		System.out.println("aaaaa");
		orderService.addOrder(orders);
		System.out.println("成功了吗");
		
		Collection<OrderItem> orderItems = orderShop.getOrderItems();
		OrderDetail orderDetail =new OrderDetail();
		for (OrderItem orderItem : orderItems) {
			orderDetail.setOrdernumber(ordernumber);
			orderDetail.setOrName(orderItem.getOrName());
			orderDetail.setOrUnitPrice(orderItem.getOrUnitPrice());
			orderDetail.setOrValDate(orderItem.getOrValDate());
			orderDetail.setOrProductDate(orderItem.getOrProductDate());
			orderDetail.setOrnum(orderItem.getOrnum());
			orderDetail.setOrsum(orderItem.getOrsum());
			String tname2 = orderItem.getTname();
			Type type=typeService.findType(tname2);
			String sname2 = orderItem.getSname();
			Supplier supplier =supplierService.findSupplier(sname2);
			orderDetail.setSid(supplier);
			orderDetail.setTid(type);
			System.out.println(orderDetail);
			System.out.println("bbbbbb");
			orderDetailService.addOrderDetail(orderDetail);
			
		}
		ServletActionContext.getRequest().getSession().removeAttribute("orderShop");
		return "addOrderDetailSuccess";
	}
	
	/**
	 * 查找所有订单列表
	 */
	public String findAllOrder() {
		List<Orders> list =orderService.findAllOrder();
		 ActionContext.getContext().getValueStack().set("list", list);
		return "findSuccess";
	}
	
	/**
	 * 查询订单详情
	 * @return
	 */
	public String findOrderDetail() {
		System.out.println("进来了吗");
		System.out.println(orders.getOrderCode());
		List<OrderDetail> list= orderDetailService.findOrderDetail(orders.getOrderCode());
		List<Orders> ordersList =orderService.findOrderList(orders.getOrderCode());
		System.out.println(list);
		System.out.println(ordersList);
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().set("ordersList", ordersList);
		System.out.println("成功了吗？");
		return "findDetailSuccess";
	}


}
