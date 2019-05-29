<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/iconfont.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/framework/jquery-1.11.3.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/layui/css/layui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/framework/cframe.js"></script>
<!-- 仅供所有子页面使用 -->
<!-- 公共样式 结束 -->

<script src="${pageContext.request.contextPath}/js/out_intoData.js"></script>

</head>

<body>
	<div class="cBody">
	
		<div class="layui-form">
			<div class="console">
				<div class="layui-form-item">
					<a class="layui-btn"
						href="${pageContext.request.contextPath}/tgls/into_out/increase.jsp">新增</a>
			<form action="${pageContext.request.contextPath}/repertory_mohuselect" method="post">
			<div class="layui-form-item">
					<label class="layui-form-label">开始时间</label>
					<div class="layui-input-block">
						<input type="date" name="begintime"   lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">结束时间</label>
					<div class="layui-input-block">
						<input type="date" name="endtime"   lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
					
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input class="layui-btn" type="submit" value="立即查询">
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
             
					</form>


				</div>
			</div>

		</div>
		<table id="customList" class="layui-table">
			<thead>
				<tr>
					<th>订单单号</th>
					<th>药品名称</th>
					<th>入库时间</th>
					<th>药品类别</th>
					<th>供应商</th>
					<!-- <th>商品</th> -->

					<th id="one11">单价</th>
					<th>仓库数量</th>
					<th>已上架数量</th>
					<th>保质期</th>
					<th>操作员</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="repertoryall" var="reper">
				
					<tr>
						<td><s:property value="#reper.rnumber" />
						<td><s:property value="#reper.rproduct" /></td>
						<td><s:property value="#reper.rdate" /></td>
						<td><s:property value="#reper.rtype" /></td>
						<td><s:property value="#reper.rput" /></td>
						<td><s:property value="#reper.runit" /></td>
						<td id="rtotal"><s:property value="#reper.rtotal" /></td>
						<td></td>
						<td><s:property value="#reper.rbdate" /></td>
						<td><s:property value="#reper.rname" /></td>
						
					
						<td>
							<form id="form01" action="${pageContext.request.contextPath}/repertory_onshowGoods" method="post">
								<div class="layui-input-inline">
									<input type="number" name="count" required lay-verify="required" placeholder="请输入上/下架数量" autocomplete="off" class="layui-input">
								</div>
								<input type="hidden" name="rid" value="<s:property value="#reper.rid"></s:property>">
								<input type="hidden" name="rnumber" value="<s:property value="#reper.rnumber"></s:property>">
								<input type="hidden" name="rproduct" value="<s:property value="#reper.rproduct"></s:property>">
								<input type="hidden" name="rtype" value="<s:property value="#reper.rtype"></s:property>">
								<input type="hidden" name="rput" value="<s:property value="#reper.rput"></s:property>">
								<input type="hidden" name="rdate" value="<s:property value="#reper.rdate"></s:property>">		
								<input type="hidden" name="runit" value="<s:property value="#reper.runit"></s:property>">
								<input type="hidden" name="rtotal" value="<s:property value="#reper.rtotal"></s:property>">
								<input type="hidden" name="rname" value="<s:property value="#reper.rname"></s:property>">
								<input type="hidden" name="rbrithdate" value="<s:property value="#reper.rbrithdate"></s:property>">
								<input type="hidden" name="rbdate" value="<s:property value="#reper.rbdate"></s:property>">
								<a><input id="btn01" class="layui-btn layui-btn-xs" type="submit" value="上架"></a>
								
								<a class="btn_down" href="javascript:void;">
									<input class="layui-btn layui-btn-xs" type="button" value="下架" />
								</a>
						
							</form>	
						</td>
					</tr>

				</s:iterator>
				<script>
								
				                
				            $(".btn_down").on("click", function (){
		    					  var that = this;
		    					  
		    					  var value = $(this).parent("form").find("input[name='count']").val();
		    					  var rid = $(this).parent("form").find("input[name='rid']").val();
		    					  
		                			alert(value);
		                		
		                		 $.ajax({
		                			url:"repertory_outshowGoods",
		                			data: {
		                				"count": value,
		                				"rid":rid
		                			},
		                			type:"post",
		                			dataType:"json",
		                			cache:false,
		                			success:function(msg){
		                				$(that).parents("tr").find("#rtotal").text(msg);
		                				alert("回调成功");
		                				alert(msg);
		                				
		                				//$(this).parent("tr:nth-child(7)").text("1111");
		                				
		                				
		                				//prev().find("tr:nth-child(7)").text("11234");
		                				
		                				
		                				//$("#rtotal").text(msg);
		                				
		                				/* $(this).parent("tr").children().eq(7).text("1111");
		                				
		                				alert($(this).val()); */
		                				
		                			}
		                		});  
		                		
		                	} )
				               	
									
					</script>	
			</tbody>
		</table>

	</div>
</body>

</html>