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
					<a href="ListClubServlet">  社团一览</a>
		
				</li>
				<li>
					<a href="ActListServlet">最近活动</a>
			
				</li>
				
				
				<li>
					<a href="NewsListServlet">  大事件</a>
			
				</li>
				<li class = "cur">
					<a href="SelfPageServlet">  个人中心</a>
								<dl>
						<dd><a href="SelfActServlet">我参加的活动</a></dd>
						<dd><a href="SelfClubServlet">我的社团&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></dd>
						<dd><a href="">我的消息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></dd>
					</dl>
				</li>
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
	 我加入的社团
	 </div>
	 <form>
	 <c:forEach items="${sclub }" var="s">
		  <div>
			  <p>社团名： ${s.cname } &nbsp;&nbsp;&nbsp;&nbsp;社长：${s.master }</p>
		  </div>
		  </c:forEach>
	 </form>
	 </div>
</div></div></div>
	 
	 <div class="footerbox">	
	 <div class="foot_xx">
	 <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	 </div></div>
	
	 
	 
</body>
</html>