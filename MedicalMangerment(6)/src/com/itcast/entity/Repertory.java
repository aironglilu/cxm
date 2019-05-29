package com.itcast.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Repertory {
	private Integer rid; //入库编号
    private String rnumber;//入库订单编号
    private String rproduct;//入库产品
    
    private String rdate; //入库时间
    private Integer rtotal; //入库总数 
    
   private  double runit;//单价
   private String rtype;//类型
   private Integer rbdate;//保质期
   private  String rbrithdate;  //生产日期
   private String rput;//供应商*/
   private String rname;//入库名字
   
   
@Override
public String toString() {
	return "Repertory [rid=" + rid + ", rnumber=" + rnumber + ", rproduct=" + rproduct + ", rdate=" + rdate
			+ ", rtotal=" + rtotal + ", runit=" + runit + ", rtype=" + rtype + ", rbdate=" + rbdate + ", rbrithdate="
			+ rbrithdate + ", rput=" + rput + ", rname=" + rname + "]";
}
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}
public String getRnumber() {
	return rnumber;
}
public void setRnumber(String rnumber) {
	this.rnumber = rnumber;
}
public String getRproduct() {
	return rproduct;
}
public void setRproduct(String rproduct) {
	this.rproduct = rproduct;
}
public String getRdate() {
	return rdate;
}
public void setRdate(String rdate) {
	this.rdate = rdate;
}
public Integer getRtotal() {
	return rtotal;
}
public void setRtotal(Integer rtotal) {
	this.rtotal = rtotal;
}
public double getRunit() {
	return runit;
}
public void setRunit(double runit) {
	this.runit = runit;
}
public String getRtype() {
	return rtype;
}
public void setRtype(String rtype) {
	this.rtype = rtype;
}

public String getRbrithdate() {
	return rbrithdate;
}
public void setRbrithdate(String rbrithdate) {
	this.rbrithdate = rbrithdate;
}
public String getRput() {
	return rput;
}
public void setRput(String rput) {
	this.rput = rput;
}
public String getRname() {
	return rname;
}
public void setRname(String rname) {
	this.rname = rname;
}
public Integer getRbdate() {
	return rbdate;
}
public void setRbdate(Integer rbdate) {
	this.rbdate = rbdate;
}
   
   
   
   
 

	

	
	
	
	
	
	
	
	
	
	
     
     
	
     
     
}
