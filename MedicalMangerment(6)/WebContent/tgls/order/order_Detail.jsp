<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="穷在闹市" />
		<!-- 作者 -->
		<meta name="revised" content="穷在闹市.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>穷在闹市出品</title>

		<!-- 公共样式 开始 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/iconfont.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/framework/jquery-1.11.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<script src="${pageContext.request.contextPath}/framework/printThis.js"></script>
		<!-- 公共样式 结束 -->
		

	</head>

	<body>
		<div class="cBody">
			<div class="console">
				<div class="layui-form-item">
					<a class="layui-btn layui-btn-sm layui-btn-normal" onclick="printTable('dayindaju1')">打印</a>
				</div>
			</div>
			
			<div class="layui-row" id="dayindaju1">
				<table class="layui-table printTable">
					<tbody>
						<tr>
							<td class="printName" colspan="8">采购订单</td>
							
						</tr>
						<tr>
							<td class="printDis" colspan="8">
								<span>进货日期：<s:property value="ordersList[0].orderDate"/></span>
								<span class="right">单据编号：<s:property value="ordersList[0].orderCode"/></span>
								<span>入库类型：采购入库</span>
							</td>
						</tr>						
						<tr>
							<th width="7%" class="textCenter">商品名称</th>
							<th width="8%" class="textCenter">进货价</th>
							<th width="8%" class="textCenter">保质期</th>
							<th width="8%" class="textCenter">生产日期</th>
							<th width="8%" class="textCenter">数量</th>
							<th width="8%" class="textCenter">类别</th>
							<th width="8%" class="textCenter">供应商</th>
							<th width="8%" class="textCenter">小计</th>
						</tr>
						<s:iterator value="list" var="list">
						<tr>
						<td class="textCenter"><s:property value="#list.orName"></s:property></td>
						<td class="textCenter"><s:property value="#list.orUnitPrice"></s:property></td>
						<td class="textCenter"><s:property value="#list.orValDate"></s:property></td>
						<td class="textCenter"><s:property value="#list.orProductDate"></s:property></td>
						<td class="textCenter"><s:property value="#list.ornum"></s:property></td>
						<td class="textCenter"><s:property value="#list.tid.tname"></s:property></td>
						<td class="textCenter"><s:property value="#list.sid.sname"></s:property></td>
						<td class="textCenter"><s:property value="#list.orsum"></s:property>元</td>
						</tr>
						</s:iterator>
						<tr>
							<td class="printOperator" colspan="8">
								<span>总价：￥<s:property value="ordersList[0].orderSum"></s:property>元</span>
							<span class="right">经手人：<s:property value="ordersList[0].orderName"/></span>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<script>
			//打印方法
		    function printTable(id){
		    	$('#'+id).printThis({ importCss: true, importStyle: true });
		    }
		</script>
	</body>

</html>