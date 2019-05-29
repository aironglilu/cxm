<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>


<script type="text/javascript">

$(document).ready(function(){
	$("#username").change(function(){
		
		setInterval("callAjaxByjQuery()",2000);
		
	  });
});	
	
/**
 * 用jQuery的方式发起AJAX请求
 */
function callAjaxByjQuery(){
	//alert("222");
	$.ajax({
		url:"goods_findTypeGoods",
		type:"post",
		cache:false,
		success:function(msg){
			$("#msg").append(msg);
		}
	});
	
}


</script>


<body>
	<form action="">
		用户名：<input type="text" id="username" name="username">
		<br>
	
	</form>

</body>
</html>