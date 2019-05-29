package com.itcast.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.entity.Good;
import com.itcast.entity.OrderDetail;
import com.itcast.entity.Repertory;
import com.itcast.entity.Supplier;
import com.itcast.entity.Type;
import com.itcast.service.RepertoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RepertoryAction extends ActionSupport implements ModelDriven<Repertory> {

	// 创建模型封装的对象
	private Repertory repertory = new Repertory();

	@Override
	public Repertory getModel() {
		// TODO Auto-generated method stub
		return repertory;
	}

	public Repertory getRepertory() {
		return repertory;
	}

	public void setRepertory(Repertory repertory) {
		this.repertory = repertory;
	}

	/*
	 * public static String getOrderdetial() { return OrderDetial; }
	 */

	private static final String OrderDetial = null;
	// 这里在action注入 service
	private RepertoryService repertoryService;

	public RepertoryService getRepertoryService() {
		return repertoryService;
	}

	public void setRepertoryService(RepertoryService repertoryService) {
		this.repertoryService = repertoryService;
	}

	
	private String count;
	
		
	public void setCount(String count) {
		this.count = count;
	}

	
	
	// 查询仓库的所有
	public String repertoryall2() {

		System.out.println("findrepertoryall_Action进....");
		
		//查询所有仓库订单列表
		List<Repertory> repertoryall = repertoryService.repertoryall2();
		//查询对应商品的上架数量
		
		
		ActionContext.getContext().getValueStack().set("repertoryall", repertoryall);

//		for (Repertory repertoryallfind : repertoryall) {
//			
//			//找到前台的数量
//			Integer findgoodsNum = repertoryService.findgoodsNum(repertoryallfind.getRname());
//			
//			System.out.println("查询仓库Action出");
//		}

		return "repertoryall2";
	}
	
	
	

//  查询 入库所有信息   和good 的级联             
	public String findrepertoryall() {
		System.out.println("findrepertoryall_Action进....");
		List<Good> findrepertoryall = repertoryService.findrepertoryall();
		System.out.println("findrepertoryall_Action出....");

		ActionContext.getContext().getValueStack().set("findrepertoryall", findrepertoryall);

		for (Good good : findrepertoryall) {
			System.out.println("shang");
			//System.out.println("库存名：" + good.getGname() + good.getRepertory().getRname());

		}

		return "repertoryall";

	}

	// 查询是否存在 订单编号
	public String selectorder() {
		System.out.println("进action.............");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String ordernumberr = request.getParameter("ordernumber") == null ? "" : request.getParameter("ordernumber");
		System.out.println("前台传过来的值" + ordernumberr);
		//OrderDetial 需要返回list 因为  你里面 主键 不是订单标号不能判断唯一

		if (!ordernumberr.equals("")) {
			System.out.println("进入循环");
			
			List<OrderDetail> Orderdetial = (List<OrderDetail>) repertoryService.selectorder2(ordernumberr);

			ActionContext.getContext().getValueStack().set("Orderdetial", Orderdetial);
			System.out.println("值栈出......");

			for (OrderDetail orderDetial2 : Orderdetial) {
				String onumber = orderDetial2.getOrdernumber();//订但编号
				double oPrice = orderDetial2.getOrUnitPrice();//价格
				String oName = orderDetial2.getOrName();//产品名字
				String oDate = orderDetial2.getOrValDate();//保质期
				
				int parseInt = Integer.parseInt(oDate);
				String orPDate = orderDetial2.getOrProductDate();//生厂日期
				Integer onum = orderDetial2.getOrnum();//数量
				//double osum = orderDetial2.getOrsum();//数量总价
				String sname = orderDetial2.getSid().getSname();//厂家
				String tname = orderDetial2.getTid().getTname();//类别
				/* private Integer rid; //入库编号
			     private String rnumber;//入库订单编号
			     private String rproduct;//入库产品
			     private String rdate; //入库时间
			     private String rtotal; //入库总数 
			    private  String  runit;//单价
			    private String rtype;//类型
			    private String rbdate;//保质期
			    private  String rbrithdate;  //生产日期
			    private String rput;//供应商*/
				//这里是将查到的数据插入repertory
				
				Date date = new Date();
				System.out.println(date);
				SimpleDateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 多态
				// 2017-04-19 星期三 下午 20:17:38

				String format = bf.format(date);// 格式化 bf.format(date);
				System.out.println(format);
				// 往repertory添加时间
				repertory.setRnumber(onumber);//订单编号
				repertory.setRproduct(oName);//入库产品
				repertory.setRdate(format);//入库时间
				repertory.setRtotal(onum);//
				repertory.setRunit(oPrice);
				repertory.setRput(sname);
				repertory.setRbrithdate(orPDate);//生厂日期
				repertory.setRtype(tname);
				repertory.setRbdate(parseInt);//保质期
				
				System.out.println(repertory);
				repertoryService.insertrepertory(repertory);
			}

		} else {
			return "selectorderfase";
		}
		return "insertrepertory";

	}

	

	// 根据时间模糊查询
	public String mohuselect() {
		System.out.println("进入action");
		HttpServletRequest request = ServletActionContext.getRequest();
		String begintime = request.getParameter("begintime") == null ? "" : request.getParameter("begintime");
		String endtime = request.getParameter("endtime") == null ? "" : request.getParameter("endtime");
  
			  System.out.println(begintime); 
			  System.out.println(endtime);
			 
		List<Repertory>	 mohuselect=  repertoryService.mohuselectService(begintime,endtime);
			  

	

		return "mohuselect";
	}
	
	
	
	/**
	 * 上架商品
	 * @return
	 */
   
	public String onshowGoods() {
		
		System.out.println("上架商品Action..................。。。。");
		HttpServletRequest request = ServletActionContext.getRequest();
		String count1 = request.getParameter("count");
		Integer count = Integer.parseInt(count1);
		
		System.out.println("数量！"+count);
		
		System.out.println(repertory.getRname());
		
		repertoryService.onshowGoods(repertory,count);
		
		
		
		return "onshowGoods";
	}
	
	/**
	 * 下架商品
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
   
	public String outshowGoods(){
		System.out.println(count);
		
		System.out.println("下架商品Action..................。。。。");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
			
		String parameter = request.getParameter("count");
		String parameter2 = request.getParameter("rid");
		
		System.out.println("数量："+parameter+"id:"+parameter2);
			
			Integer id = Integer.parseInt(parameter2);
			Integer count = Integer.parseInt(parameter);
		
			//获得新的仓库药品数量
			Integer outshowGoods = repertoryService.outshowGoods(id,count);
			
			System.out.println(outshowGoods);
			
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				
				PrintWriter writer = response.getWriter();
				mapper.writeValue(writer, outshowGoods);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return "outshowGoods";
		
	
	}
	

}
