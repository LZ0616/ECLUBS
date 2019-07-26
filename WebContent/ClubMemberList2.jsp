<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
     <meta charset="utf-8">
     <meta content="社团联盟" http-equiv="keywords">
     <meta name="description" content="社团联盟,wangid">
     <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
     <title>社团人员信息</title>
     <link rel="stylesheet" href="css/index.css" type="text/css">
     <link rel="stylesheet" type="text/css" href="css/children.css">
     <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
     <script type="text/javascript" src="js/public.js"></script>
     <script type="text/javascript" src="js/YMDClass.js"></script>
     <link href="css/form.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript"></script>
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
	 <li><a href="IndexServlet">网站首页</a></li>
	 <li class="cur"><a href="ListClubServlet">社团一览</a>

	 </li>
	 <li ><a href="ActListServlet">最近活动</a>

	 </li>
	 <li><a href="NewsListServlet">大事件</a>
	 </li>
	 				<li>
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
     
     <br>
     <div class="rightinfo">
     <div>
     <div>
         <h3 align="center" style="color: blue;font-size:20px;">社团人员信息</h3>
     </div>
     </div>
     </div>
     
     <div class="zy_contentbox">
	 <div class="zy_content wid_main">
     <table class="tablelist">
     <thead>
     <tr>
        <th>学号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>院系</th>
        <th>联系电话</th>
        <th>身份</th>
     </tr>
     </thead>	 
     <c:forEach items="${stuList}" var="s">
	 <tr>
		<td>${s.sno}</td>
		<td><a href="#link" style="color: blue;">${s.sname}</a></td>
		<td>${s.sex}</td>
		<td>${s.age}</td>
		<td>${s.dep}</td>
		<td>${s.phone}</td>
		<td>${s.pos}</td>
	
	  </tr>
	  </c:forEach>  
	  </table>
	  </div></div>
	    
	  <div class="footerbox">	
	  <div class="foot_xx">
	  <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	  </div></div>
	  
	  <script type="text/javascript">
	   $('.tablelist tbody tr:odd').addClass('odd');
	  </script>

</body>
</html>