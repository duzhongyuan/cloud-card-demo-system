<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pcidata ccds test web</title>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
/* function deleteThis(nowTr){
	alert("删除自己");
};  */
$(function(){
	
	modifyCardVersionUrl = "window.location.href='modifyCardVersion.jsp'";		//在本页面打开
	modifyCardVersionUrl = "window.open('modifyCardVersion.jsp')";				//在新页面打开
	
	//Ajax调用处理
    $.ajax({
        type: "POST",
        url: "/getCardNoAndVersion.do",
        data: "name=garfield&age=18",
        success: function(data){
                 //$("#myDiv").html('<h2>'+data.cardSet+'</h2>');
                 
                 $("#mythead").html('<tr><td>版本号:</td><td>' + data.cardVersion + '</td><td><input type="button" value="修改" onclick="' + modifyCardVersionUrl + '"></td></tr>');
                 var trs = "";
                 $.each(data.cardSet,function(){
                 	//console.log(this);
                 	trs += '<tr><td>卡号:</td><td>' + this + '</td><td><input type="button" value="删除" class="delete"></td></tr>';
                 });
 			  	 $("#mytBody").html(trs);
 			  	 
 			    $("input.delete").click(function(){
 			    	//alert($(this).parent().prev().html());
 			    	
 			    	var cardNo = $(this).parent().prev().html();
 			    	///alert("cardNo=" + cardNo);
 			    	
 			    	$.ajax({
 			          	type: "POST",
 			          	url: "/deleteCardNo.do",
 			          	data: "cardNo=" + cardNo,
 			          	success:function(data){
 			          		if ("success" == data.result) {
 			          			alert("删除卡号成功!");
 			          			location.reload();
							} else{
								alert("删除卡号失败!");
							}
 			          	}
 			          	
 			    	});
 			    	
 			    });
           }
     }); 
	
	//按钮单击时执行
    /* $("#modifyCardVersion").click(function(){
    	alert("hello");
     }); */

});
</script>
</head>
<body>
	<h3 align="center">查看当前版本号和卡号</h3>
	
		<table align="center" border="1" cellpadding="5" cellspacing="2">
			<thead id="mythead"></thead>
			<tbody id="mytBody"></tbody>
		</table>
		<!-- <div id="myDiv"><h2>通过 AJAX 改变文本</h2></div>
        <button id="testAjax" type="button">Ajax改变内容</button> -->
        <!-- <input type="button" value="测试" onclick="deleteThis(this);"> -->
</body>
</html>
