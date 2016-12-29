<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pcidata ccds test web add cardNo</title>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
function add() {  
    var trObj = document.createElement("tr");
    trObj.id = new Date().getTime();  
    trObj.innerHTML = '<td>卡号:</td><td><input name="cardno" value="" maxlength="16" minlength="16"></td><td><input type="button" value="删除" onclick="del(this)"></td>';  
    document.getElementById("tbody").appendChild(trObj);
    $(trObj).find("input")[0].focus();
}  

function del(obj) {  
    var trId = obj.parentNode.parentNode.id;  
    var trObj = document.getElementById(trId);  
    document.getElementById("tbody").removeChild(trObj);  
}

$(function(){
   	//按钮单击时执行
    $("#ajaxSubmit").click(function(){
         
    	var result = true;
    	//alert("hello");
    	//alert($("input[name='cardno']")[0].value.length);
    	//alert($("input[name='cardno']").length);
    	var cardnosum = $("input[name='cardno']").length;
    	if(cardnosum <= 0){
    		alert('卡号数量不能小于0');
    		result = false;
    	}
    	$.each($("input[name='cardno']"), function(){
    		if(this.value.length < 16){
    			alert('卡号长度不能小于16');
    			result = false;
    		}
    	});
    	
    	if(result){
    		//Ajax调用处理
            $.ajax({
                type: "POST",
                url: "/addCardNo.do",
                data: $('#formAddCardNo').serialize(),
                success: function(data){
                	if("success" == data.result){
                		alert('添加成功！');
                		$('#formAddCardNo')[0].reset();	//清空表单内容
                	} else{
                		alert('添加失败！');
                	}
               	}
             });
    	}
        
     });
});

</script>
</head>
<body>
	<h2 align="center">添加卡号页面</h2>
	
	<form action="/addCardNo.do" method="post" id="formAddCardNo">
		<table align="center" border="0" cellpadding="8" cellspacing="2">
			<tbody id="tbody">
				<tr>
					<td><input type="button" value="增加输入框" onclick="add()"></td>
				</tr>
				<tr id="fixed">
					<td>卡号:</td>
					<td><input name="cardno" value="" maxlength="16" minlength="16"></td>
					<td><input type="button" value="删除" onclick="del(this)"></td>
				</tr>
			</tbody>
			<tr align="center">
				<td colspan="3"><input id="ajaxSubmit" type="button" value="添加"></td>
			</tr>
		</table>
	</form>
</body>
</html>
