<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width-device-width,initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="sie-edge" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>登录注册页面</title>
<script src="https://unpkg.com/ionicons@4.2.2/dist/ionicons.js"></script>
<script type="text/javascript">
	function check(form) {
		if (form.account.value == "") {
			alert("账号不能为空");
			return false;
		}
		if (form.password.value == "") {
			alert("密码不能为空");
			return false;
		}
		if (form.password1.value == "") {
			alert("确认密码不能为空");
			return false;
		}
		if (form.name.value == "") {
			alert("姓名不能为空");
			return false;
		}
		if (form.age.value == "") {
			alert("年龄不能为空");
			return false;
		}
		if (form.phone.value == "") {
			alert("电话不能为空");
			return false;
		}
		if (form.password.value != form.password1.value) {
			alert("两次输入密码不一致");
		return false;
		}
		return true;
	}
</script>
</head>
<body>


<div class="container" id="container">
  <div class="form-container sign-up-container">
    <form id="form1" name="form1" method="post" onsubmit=" return check(this)" action="RegistServlet">
      <h1>注册</h1>
      <div class="social-container"> <a href="#" class="social">
        <ion-icon name="logo-facebook"></ion-icon>
        </a> <a href="#" class="social"> 
        <ion-icon name="logo-googleplus"></ion-icon>
        </a> <a href="#" class="social">
        <ion-icon name="logo-linkedin"></ion-icon>
        </a> </div>
      
      <input type="text"  name="account" autocomplete="off" placeholder="账号">
      <input type="password" name="password"   placeholder="密码">
      <input type="password" name="password1"  placeholder="确认密码">
	  <input type="text" name="sno" autocomplete="off" placeholder="学号">
	  <input type="text"  name="sname"autocomplete="off" placeholder="姓名">
	   <select  select=1" name="sex">
						<option>男</option>
						<option>女</option>
						</select>
	  <input type="text" name="age" autocomplete="off" placeholder="年龄">
	  <input type="text" name="dep" autocomplete="off" placeholder="院系">
	  <input type="text" name="phone" autocomplete="off" placeholder="联系方式">
      <div><button>注册</button><button type="reset" >重置</button></div>

     
    </form>
  </div>
  <div class="form-container sign-in-container">
    <form form id="form1" name="form1" method="post"  action="LoginServlet">
      <h1>登录</h1>
      <div class="social-container"> <a >
        <ion-icon name="logo-facebook"></ion-icon>
        </a> <a > 
        <ion-icon name="logo-googleplus"></ion-icon>
        </a> <a >
        <ion-icon name="logo-linkedin"></ion-icon>
        </a> </div>
      <input type="text"  autocomplete="off" name="account"  placeholder="账号">
      <input type="password" name="password"  placeholder="密码">
      <select name = "status">
<option  value="student">&nbsp;&nbsp;&nbsp;学生</option>
<option  value="admin">&nbsp;&nbsp;&nbsp;管理员</option>
</select>
      <button>登录</button>
      <%
		if (request.getAttribute("error1") != null) {
	%>
	<center>
		<h3>
			<%=request.getAttribute("error1")%>
		</h3>
	</center>
	<%
		}
	%>
      
          	<%
		if (request.getAttribute("error") != null) {
	%>
	<center>
		<h3>
			<font color="red"><%=request.getAttribute("error")%></font>
		</h3>
	</center>
	<%
		}
	%>  
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h2>欢迎回来</h2>
        <p>登录来发现更美的社团</p>
        <button class="ghost" id="signIn">登录</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h2>欢迎光临</h2>
        <p>加入我们开启一段美妙的社团之旅</p>
        <button class="ghost" id="signUp">注册</button>
      </div>
    </div>
  </div>
</div>

<script  src="js/script.js"></script> 
	
</body>
</html>
