<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pcidata ccds test web</title>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	
$(function(){
		function showtime(){
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
	        var day = now.getDate();
	        var hours = now.getHours();
	        var minutes = now.getMinutes();
	        var seconds = now.getSeconds();
	
	        if (month >= 1 && month <= 9) {
	            month = "0" + month;
	        }
	        if (day >= 0 && day <= 9) {
	        	day = "0" + day;
	        }
	        
	        if (hours >=0 && hours <= 9) {
				hours = "0" + hours;
			}
	        
	        if (minutes >= 0 && minutes <= 9) {
				minutes = "0" + minutes;
			}
	        
	        if (seconds >= 0 && seconds <= 9) {
				seconds = "0" + seconds;
			}
	        
			var tt = "" + year + month + day + hours + minutes + seconds;
			
			document.getElementById("cardVersion").value=tt;
		}
		showtime();
		
	   	//按钮单击时执行
	    $("#ajaxSubmit").click(function(){
	         
	    	//alert("hello");
	    	
	    	var result = true;
	    	//alert("hello");
	    	//alert($("input[name='cardno']")[0].value.length);
	    	//alert($("input[name='cardno']").length);
	    	var cardnosum = $("input[name='cardVersion']")[0].value.length;
	    	//alert('length:' + cardnosum);
	    	
	    	if(cardnosum < 14){
    			alert('版本号长度不能小于14');
    			result = false;
    		}
	    	
	    	if(result){
	    		//Ajax调用处理
	            $.ajax({
	                type: "POST",
	                url: "/modifyCardVersion.do",
	                data: $('#formModifyCardVersion').serialize(),
	                success: function(data){
	                	if("success" == data.result){
	                		alert('修改成功！');
	                		//$('#formModifyCardVersion')[0].reset();	//清空表单内容
	                		location.reload();
	                	} else{
	                		alert('修改失败！');
	                	}
	               	}
	             });
	    	}
	        
	     });
});
		
</script>
</head>
<body>
	<h2 align="center">修改卡号版本页面</h2>
	
	<form action="/modifyCardVersion.do" method="post" id="formModifyCardVersion">
		<table align="center" border="0" cellpadding="10" cellspacing="10">
			<tr>
				<td>卡号版本号:</td>
				<td><input id="cardVersion" name="cardVersion" value="" maxlength="16" minlength="14"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="ajaxSubmit" type="button" value="修改"></td>
			</tr>
		</table>
	</form>
	
</body>
</html>
