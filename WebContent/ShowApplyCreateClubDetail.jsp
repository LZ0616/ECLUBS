<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="club.NewsListBean"%>
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
	 <li><a href="IndexServlet">网站首页</a></li>
	 <li class="cur"><a href="ListClubServlet">社团一览</a>

	 </li>
	 <li ><a href="ActListServlet">最近活动</a>

	 </li>
<li >
<a href="NewsListServlet">大事件</a>
	 </li>
					<li>
					<a href="SelfPageServlet">  我的消息</a>
		
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

 	 <c:forEach items="${list }" var="ccb">
	 <div class="news_xqbox">
	 <div class="xq_title">
		<h1>${ccb.cname }		
				<a  style="font-size:20px ;	
							color:#333;
							background:#D3D3D3;
							margin-left:20px;
							margin-right:20px;
							padding:5px 15px;
							border-radius: 10px;
							border: 1px solid rgba(0,0,0,0.4) ;
							cursor: pointer;" href="CheckCreateClubServlet?id=${ccb.id }&&con=1">同意</a>
				<a  style="font-size:20px ;	
							color:#333;
							background:#D3D3D3;
							margin-right:20px;
							padding:5px 15px;
							border-radius: 10px;
							border: 1px solid rgba(0,0,0,0.4) ;
							cursor: pointer;" href="CheckCreateClubServlet?id=${ccb.id }&&con=2">驳回</a>
		<p><span>作者：${info.master }</span><span>申请人：${ccb.master}</span></p>
		
		<p><span><a href="getApplyClubPicture?id=${ccb.id }"target="_blank">	<img src="getApplyClubPicture?id=${ccb.id }"/></a></span></p>
		
	 </div>
	 <div class="txt">
		<p>${ccb.intro}</p>
	 </div>
	 <div class="txt">

		<p>${ccb.rules}</p>
	 </div>
	 </div>
	 </c:forEach>
	 </div>
	
	 </div>
	 		<div class="footerbox">	
	    <div class="foot_xx">
	    <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	    </div></div>
	 
</body>
</html>