<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Student.SelfStuBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <meta charset="utf-8">
     <meta content="社团联盟" http-equiv="keywords">
     <meta name="description" content="社团联盟,wangid">
     <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
     <title>社团联盟</title>
     <script type="text/javascript">
	 function check(form) {
		 if (form.name.value == "") {
				alert("姓名不能为空");
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
		if (form.age.value == "") {
			alert("年龄不能为空");
			return false;
		}
		if (form.dep.value == "") {
			alert("院系不能为空");
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
     <link rel="stylesheet" href="css/index.css" type="text/css">
     <link rel="stylesheet" type="text/css" href="css/children.css">
     <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
     <script type="text/javascript" src="js/public.js"></script>
     <script type="text/javascript" src="js/YMDClass.js"></script>
</head>

<body>
     <div class="topbox">
	 <div class="top_line">
	 <div class="wid_main fix">
	 <h2>您好，欢迎访问ECLUBS!</h2>
     </div></div></div>
     
     <div class="top_logo">
     <div class="wid_main fix">
	 <a href="index.html"><img src="images/logo.png"/></a>
	 <span><img src="images/running.png"/></span>
	 </div></div>
	
	 <div class="top_nav">
	 <div class="wid_main">
	 <ul class="fix">
				<li >
					<a href="IndexServlet">  网站首页</a>
				</li>
				<li>
					<a href="ListClubServlet"> 社团一览</a>
		
				</li>
				<li>
					<a href="ActListServlet">最近活动</a>
			
				</li>
				
				
				<li>
					<a href="NewsListServlet">  大事件</a>
			
				</li>
				<li class = "cur">
					<a href="SelfPageServlet">  个人中心</a>
		
				</li>
				
     <li><a href="LogOutServlet">  退出登录</a></li>
	 </ul>
	 <div class="nav_bg_cur">
	 <img src="images/icon_nav.png">
	 </div></div></div>
 
     <div class="zy_banner">
	 <div class="pic"><img src="images/zx.png"></div>
	 <div class="weizhi">
	 <div class="wid_main">
	 </div></div></div>
	 
	 <div class="zy_contentbox">
	 <div class="zy_content wid_main">
	 <div class="zy_formbox zy_register fix">
	 <div class="left">
	 <div class="txt">
	 个人信息
	 </div>
	 <form>
	 <c:forEach items="${list }" var="s">
		  <div>
			  <p>账号：<span class="info-body"> ${s.account }</span></p>
		  </div>
	
		  <div>
			  <p>学号：<span class="info-body"> ${s.sno }</span></p>
		  </div>
		  <div>
		      <p>姓名：<span class="info-body"> ${s.sname }</span></p>
		  </div>
		  <div>
		      <p>性别：<span class="info-body">${s.sex } </span></p>
		  </div>
		  <div>
		      <p>年龄：<span class="info-body"> ${s.age }</span></p>
		  </div>
		  <div>
		      <p>院系：<span class="info-body">  ${s.dep }</span></p>
		  </div>
		  <div>
		      <p>联系电话：<span class="info-body">${s.phone }</span></p>
		  </div>
	  </c:forEach>
	  
	  	 <div class="txt">
	 我的社团
	 </div>
	      <c:forEach items="${sclub }" var="t">
		  <div>
			  <p>社团名： <a href="DetailsClubServlet?id=${t.cid }">${t.cname }</a> </p>
		  </div>
		  </c:forEach>
	<div class="txt">
	我参加的活动
	 </div>
	 <c:forEach items="${stuact }" var="act">
		  <div>
			  <p>活动名：<a href="ActivityDetailServlet?id=${act.aid}">${act.atitle }</a>&nbsp;&nbsp;&nbsp;&nbsp;活动社团： <a href="DetailsClubServlet?id=${act.cid }">${act.aclub }</a> &nbsp;&nbsp;&nbsp;&nbsp;活动时间：${act.atime }</p>
		  </div>
		  </c:forEach>
		  
	 </form>
	 </div>
	 <div class="right">
		 <h2>
            <p>信息不对？</p>
            <a class="login_user">修改信息</a>                                               
		 </h2>
	 </div></div></div></div>
	 
	 <div class="footerbox">	
	 <div class="foot_xx">
	 <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	 </div></div>
	 
	 <div class="login_box">
	 <div class="login">
	 <i><img src="images/close.png"></i>
	 <form id="form1" name="form1" onsubmit=" return check(this)" action="UpdateStuDoServlet" method="post">
	  <c:forEach items="${list }" var="s">
		  <h3>请修改<span>个人信息</span></h3>
		  <div>
			  <span><img src="images/login (1).png"></span>
			  <input type="text" autocomplete="off" name="sname" placeholder="请修改姓名" value="${s.sname }">
		  </div>
		  <div>
			  <span><img src="images/login (2).png"></span>
			  <input type="password" name="password" placeholder="输入密码">
		  </div>
		  <div>
			  <span><img src="images/login (2).png"></span>
			  <input type="password" name="password1" placeholder="确认密码">
		  </div>
		  <div>
			  <span><img src="images/age.png"></span>
			  <input type="text"  autocomplete="off"name="age" placeholder="请修改年龄" value="${s.age }" >
		  </div>
		  <div>
			  <span><img src="images/dep.png"></span>
			  <input type="text"  autocomplete="off"name="dep" placeholder="请修改院系" value="${s.dep}">
		  </div>
		  <div>
			  <span><img src="images/phone.png"></span>
			  <input type="text" autocomplete="off" name="phone" placeholder="请修改联系电话" value="${s.phone }">
		  </div>
		  <p>
			  <button type="submit">立即修改</button>
		  </p>
		  </c:forEach>
	  </form>
	  </div>
	  
	  <script type="text/javascript">
		$(function()
		{
			$(".login_user").mouseenter
			(
				function()
				{
					$(".login").css("top","0")
				}
			)
			$(".login_user").click
			(
				function()
				{
					$(".login_box").fadeIn()
					$(".login").animate({top:'50%'},300)
				}
			)
			$(".login i").click
			(
				function()
				{
					$(".login_box").fadeOut()
					$(".login").animate({top:'100%'},300)
				}
			)

			if(!placeholderSupport())
			{   // 判断浏览器是否支持 placeholder
			   $('[placeholder]').focus(function() 
			   {
			       var input = $(this);
			       if (input.val() == input.attr('placeholder')) 
			       {
			           input.val('');
			           input.removeClass('placeholder');
			       }
			   }).blur(function() 
				  {
			       var input = $(this);
			       if (input.val() == '' || input.val() == input.attr('placeholder')) 
			       {
			           input.addClass('placeholder');
			           input.val(input.attr('placeholder'));
			       }
			   }).blur();
			};
		})
		function placeholderSupport() 
		{
		   return 'placeholder' in document.createElement('input');
		}
	  </script>
      </div>
	 
	 
</body>
</html>