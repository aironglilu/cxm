package com.itcast.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.entity.Good;
import com.itcast.entity.Supplier;
import com.itcast.entity.Type;
import com.itcast.service.GoodService;
import com.itcast.service.SupplierService;
import com.itcast.service.TypeService;
import com.itcast.utils.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import net.sf.json.JsonConfig;

public class GoodAction extends ActionSupport implements ModelDriven<Good>{
	
	/**
	 * 注入goodservices
	 */
	private GoodService goodService;

	public void setGoodService(GoodService goodService) {
		this.goodService = goodService;
	}

	public GoodService getGoodService() {
		return goodService;
	}
	
	/**
	 * 注入type services
	 * 
	 */
	private TypeService typeService;
	
	
	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	/**
	 * 注入 supplierService
	 *
	 */
	private SupplierService supplierService;
	

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	/**
	 *  商品的集合
	 */
	private List<Good> goodList = new ArrayList<Good>();

	public List<Good> getGoodList() {
		return goodList;
	}
	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}
	

	
	
	/**
	 * 模型驱动封装，封装商品jsp页面提交的表单信息
	 * @author ysx
	 */
	private Good goods =new Good();

	
	public Good getGoods() {
		return goods;
	}

	public void setGoods(Good goods) {
		this.goods = goods;
	}
	
	

	
	

	/**
	 * 查找所有商品列表页面
	 * 
	 * @author ysx
	 * @return
	 */
	public String findAllGoods() {
		System.out.println("查找所有商品列表action.....");
		
		List<Good> GoodList = goodService.findAllGoods();
		//List<Type> findAllType = typeService.findAllType();
		
		ActionContext.getContext().getValueStack().set("GoodList", GoodList);
		//ActionContext.getContext().getValueStack().set("findAllType", findAllType);
		
		
//		System.out.println("查找所有类别。。。。。");
//		for (Type type : findAllType) {
//			System.out.println(type.getTname());
//		}
		
		
		
		
		String today = Utils.getToday();
		
		for (Good good : GoodList) {
			System.out.println("商品名："+good.getGname());
			System.out.println("生产日期"+good.getDate());
			System.out.println("保质期"+good.getQualitaDate());
			Date d1 = null;
	        Date d2 = null;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        
			try {
				d1 = format.parse(today);
	            d2 = format.parse(good.getDate());
	            
				//获取现在的时间距离生产日期的天数
	            long day =(d1.getTime()-d2.getTime())/(24*60*60*1000);
	            //保质期 - 相差的日期天数 = 距离过期的天数 
	            long overDate = (long)good.getQualitaDate() - day;
	             
	            //两个日期之间相差的天数，转为字符串
	            String daynum = String.valueOf(overDate);
	            
				System.out.println("相差的天数："+ daynum);
				if(daynum != null) {
					if(overDate>0 && overDate<=30) {
						good.setOverTime("只剩："+daynum+"天！");
					}else if(overDate>30){
						good.setOverTime("距离过期："+daynum+"天");
					}else{
						good.setOverTime("已过期！");
					}
				}				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
		System.out.println("findAllGoodList............");
		
		return "findAllGoods";
	}

	/**
	 * 增加商品
	 * @author ysx
	 * @return
	 */
	public String goodsAdd() {
		
		System.out.println("增加商品Action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//获取前台表单选择的类别id
		String tid = request.getParameter("one");
		//获取前台供应商的id值
		String sid = request.getParameter("two");
				
		System.out.println("类型id："+tid);
		System.out.println("供应商id："+sid);
		//将String转为int
		int ttid = Integer.parseInt(tid);
		int ssid = Integer.parseInt(sid);
		
		if(ttid!=-1 && ssid!=-1) {
			//查询对应的类别
			Type typeAboutId = typeService.TypeAboutId(ttid);
			//级联保存
			typeAboutId.getSetgoods().add(goods);
			
			//查询对应的供应商
			Supplier findSupplierOfId = supplierService.findSupplierOfId(ssid);
			//级联保存
			findSupplierOfId.getSuppliergoods().add(goods);
		
			//增加商品
			goodService.goodsAdd(goods,typeAboutId,findSupplierOfId);
			
		}else {
			System.out.println("类别或者供应商没有选择！");
		}
		
		
		

		return "goodsAdd";
	}


	/**
	 * 显示修改页面商品信息
	 * @author ysx
	 */
	public String updateShowGoods() {


		System.out.println(
				"显示修改页面商品信息" + goods.getGid() + ":" + goods.getGname() + ":" + goods.getPrice());
		
		List<Type> findAllType = typeService.findAllType();
		List<Supplier> allSuppliers = supplierService.allSuppliers();

		ActionContext.getContext().getValueStack().set("allSuppliers", allSuppliers);
		ActionContext.getContext().getValueStack().set("findAllType", findAllType);
		

		return "updateShowGoods";

	}

	/**
	 * 修改药品信息
	 * @author ysx
	 */
	public String updateGoods() {
		
		System.out.println("修改商品Action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//获取前台表单选择的类别id
		String tid = request.getParameter("one");
		//获取前台供应商的id值
		String two = request.getParameter("two");
				
		System.out.println("类型："+tid);
		System.out.println("供应商："+two);
		
		
		if(tid.equals("0")||two.equals("0")) {
			System.out.println("类别或者供应商没有选择！");
			
		}else {
			//修改商品
			goodService.updateGoods(goods.getGid(),tid,two,goods);
		}
		 
		return "updateGoods";
	}

	/**
	 * 删除药品
	 * @author ysx
	 */

	public String deleteGoods() {
		System.out.println("删除药品方法测试。。。。。");
	
		HttpServletRequest request = ServletActionContext.getRequest();
		String gid;
		try {
			gid = new String(request.getParameter("goodid").getBytes("iso-8859-1"), "utf-8");
			Integer id = Integer.parseInt(gid);
			goodService.deleteGoods(id);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "deleteGoods";
	}

	/**
	 * 模糊查找商品
	 * @author ysx
	 * @return
	 */
	public String findSomeGood() {
		System.out.println(" 模糊查找商品测试。。。。。");
		
		List<Good> findSomeGood = goodService.findSomeGood(goods.getGname());
		//向值栈放对象
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		
		valueStack.set("findSomeGood", findSomeGood);
		
		System.out.println("findSomeGood.....");

		return "findSomeGood";
	}
	
	/**
	 * ajax动态查找商品
	 * @author ysx
	 */
	public String findTypeGoods() {
		
		System.out.println("ajax动态查找商品Action方法");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取前台传的json属性值
		String data = request.getParameter("jsondata");
		System.out.println("传过来的值" + data);
		
		//数据库查找所有商品
		List<Good> findSomeGood = goodService.findAllGoods();
		
		//创建一个商品名字list集合
		List<String> goodname = new ArrayList<String>();
		
		//把所有商品的名字添加到商品集合list中
		for (Good good : findSomeGood) {
			goodname.add(good.getGname());

		}
		//创建string类型数组
		String [] arr = new String[goodname.size()]; 
 
		goodname.toArray(arr);//将list集合转成string数组
		
		 //遍历输出string数组
		Arrays.stream(arr).forEach(System.out::println);
	
			
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			PrintWriter writer = response.getWriter();
			mapper.writeValue(writer, arr);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

				
		
		return "findTypeGoods";
		
		
	}
	
	
	
	/**
	 * 主页跳转到添加药品页面
	 */
	public String addGoodsPage() {
		
		System.out.println("主页跳转到添加药品页面......");
		List<Type> findAllType = typeService.findAllType();
		List<Supplier> allSuppliers = supplierService.allSuppliers();
		
		ActionContext.getContext().getValueStack().set("allSuppliers", allSuppliers);
		ActionContext.getContext().getValueStack().set("findAllType", findAllType);
		return "addGoodsPage";
		
	}
	
	

	@Override
	public Good getModel() {
		// TODO Auto-generated method stub
		return goods;
	}

	
	/**
	 * 前台查找商品（ypf+）
	 * @return
	 */
	public String shopSearchGood() {	
		List<Good> GoodList = goodService.findSomeGood(goods.getGname());
		ActionContext.getContext().getValueStack().set("GoodList", GoodList);
		return "shopSearchGood";
	}
	
	
	
	
	/**
	 * 前台遍历所有商品(ypf+)
	 * @return
	 */
	public String findAllShopGoods() {
		
		List<Good> GoodList = goodService.findAllGoods();
		List<Type> findAllType = typeService.findAllType();
		
		ActionContext.getContext().getValueStack().set("GoodList", GoodList);
		ActionContext.getContext().getValueStack().set("findAllType", findAllType);
			
		return "findAllShopGoods";
	}
	
	
	/**
	 * 分页ajax
	 * @throws IOException 
	 */
	public String pagingGoods() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//保证前台json不出现中文乱码
		response.setContentType("text/html;Character=utf-8");
		response.setCharacterEncoding("UTF-8");

		
		System.out.println("分页！！");
		String one = request.getParameter("one");
		System.out.println("分页传过来的值" + one);
		
		Integer oneInteger = Integer.parseInt(one);
		
		Integer sumCount = 10;
		
		Integer startnum = (oneInteger-1) * sumCount;
		
		
		List<Good> pagingGoods = goodService.pagingGoods(startnum,10);
		
		for (Good good : pagingGoods) {
			System.out.println("..............................."+good.getGname());
			
		}
		
		//转json
		String jsonString = JSON.toJSONString(pagingGoods);
		JSONArray jsonArray = JSON.parseArray(jsonString); 
		System.out.println("$$$$"+jsonArray);

	
		 ObjectMapper mapper = new ObjectMapper(); 
		 
		 try {
		  
			  PrintWriter writer = response.getWriter();
			  mapper.writeValue(writer, jsonArray);
			  
		  
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
		  }
		 
		
		
		System.out.println("分页Action结束！！。。。。。。。。。。。。。。。。。");
		
		return "pagingGoods";
	}
	
	
	
	
}
