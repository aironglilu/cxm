<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>jQuery 实现自动补全功能</title>
	 
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
	  
      <script type="text/javascript">
      var names ; //定义数据
      //开始函数
      $(document).ready(function(){
        $.ajax({
          type:'POST',
          contentType: "application/json",
          url: "getAutoComplteList.action?1=1",
          dataType: "json",
          success:function(data){
            names = data;
            alert(data);
            autocompleteFn(data);
          }
        });
      });
       //自动 补全方法
       function autocompleteFn(names){
         $("#autocomplete").autocomplete(names,{
           minChars:1,
           max: 10,
           dataType:"json",
           autoFill: true,
           mustMatch: true,
           matchContains: true,
           scrollHeight: 220,
           formatItem: function(data, i, total) {
             return "<I>"+data.NICKNAME+"</I>";
           },
           formatMatch: function(data, i, total) {
             return data.NICKNAME;
           },
           formatResult: function(data) {
             return data.NICKNAME;
           }
         });
       }
       
       //搜索数据
       function onSearch()
       {
    	  var nickName =$("#autocomplete").val(); 
    	   alert(nickName);
    	   $.ajax({
    	          type:'POST',
    	          contentType: "application/json",
   	              url: "getAutoComplteListByName.action?nickName="+encodeURI(encodeURI(nickName)),
    	          dataType: "json",
    	          success:function(data){
    	            names = data;
    	            alert(data);
    	          }
    	        });
       }
    </script>
  </head>
  <body>
     <input type="text" id="autocomplete" placeHolder="请输入搜索词" name="nickName"/>
     <input type="button" name="btnSearch" onclick="onSearch();" value="搜索"/>
  </body>
</html>