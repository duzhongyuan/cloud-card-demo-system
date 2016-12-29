<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    var basePath = "${pageContext.request.contextPath}";
   function login(){
        if($(".userName").val() == ""){
            alert("用户名不能为空");
            return;
        }
       if($(".password").val() == ""){
           alert("密码不能为空");
           return;
       }
        $.ajax({
            dataType:"json",
            data:$(".loginForm").serialize(),
            url:"/login.do",
            type:"post",
            success: function (data) {
                if(data.code == '0'){
                	//alert("跳转到主页面");
                    window.location.href = "/jsp/main.jsp";
                }else{
                	alert(data.code + data.msg);
                    alert(data.msg);
                }
            },
            error:function () {
                alert("登录出错...");
            }
        });
   }
</script>
<html>
<head>
    <title>地铁演示系统后台服务端测试登录页面</title>
</head>
<body>
    <h1 align="center">地铁演示系统后台服务端测试登录</h1>
    <table align="center" border="0" cellpadding="10" cellspacing="10">
	    <form class="loginForm">
	    	<tr>
		       	<td>用户名:</td><td><input class="userName" name="userName" type="text"></td>
		    </tr>
		    <tr>
		       	<td>密码:</td><td><input class="password" name="password" type="password"></td>
		    </tr>
		    <tr>
		    	<td colspan="2" align="center"><input type="button" value="登录" class="loginClass" onclick="login()"></td>
		    </tr>
	    </form>
    </table>
</body>
</html>
