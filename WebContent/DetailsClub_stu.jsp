<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="club.ListClubBean"%>
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
	 
	 <div class="zy_contentbox">
	 <div class="zy_content wid_main">
		<c:forEach items="${ list}" var="c">
		<div class="zy_vipshow_xq">
			<i><img src="getClubPicture?id=${c.id }"></i>
			<div class="txt">
				<dl>
					<dt><b>社团名称：</b><span>${c.cname }</span></dt>>
					<dd>社长：${c.master }</dd>
					<dd>成立时间：${c.time }</dd>
					<dd>社团简介：${c.intro }</dd>
					<dd>社规：${c.rules }</dd>
					<div class="bt">
					<dd> <a  href="ListClubMemberServlet?id=${c.id }"><button type="submit">社团人员</button></a></dd>
					</div>
				</dl>
			 </div>
			 
		   </div>
		   			<div class="bt">
			<%
			int temp = (int)request.getAttribute("temp");
			int flag_join = (int)request.getAttribute("flag_join");
			int flag_applyjoin = (int)request.getAttribute("flag_applyjoin");
			int flag_applyout = (int)request.getAttribute("flag_applyout");
			if(temp==1){
				%>
			
				<br>
				<br>
				<a  href="ApplyActShowServlet?id=${c.id }"><button type="submit">发布活动</button></a>
				<a  href="ApplyNewsShowServlet?id=${c.id }"><button type="submit">发布新闻</button></a>
				<a href="ShowApplyJoinActServlet?id=${c.id }"><button type="submit">活动申请</button></a>
				<a  href="ListApplyJoinClubServlet?cid=${c.id }"><button type="submit">入团申请</button></a>
				<a  href="ListApplyOutClubServlet?cid=${c.id }"><button type="submit">退团申请</button></a>
				<a href="ListResponseActNewsServlet?cid=${c.id }"><button type="submit">反馈消息</button></a>
				<a href="ApplyDeleteClubShowServlet?cid=${c.id }"><button type="submit">解散社团</button></a> 
				<% 
			}
			else if(temp==2){
				%>			
				<% 
			}
			else{
				if(flag_join == 0 && flag_applyjoin == 0){
					%>	
					<a href="ApplyJoinClubShowServlet?cid=${c.id}"><button type="submit">申请入社团</button></a>	
					<%
				}
				else if(flag_join == 0 && flag_applyjoin == 1){
					%>
					<p>已发出入团申请</p>	
					<% 
				}
				else if(flag_join == 1 && flag_applyout == 0){
					%>
					<a href="ApplyOutClubShowServlet?cid=${c.id}"><button type="submit">申请退社团</button></a>	
					<% 
				}
				else if(flag_join == 1 && flag_applyout == 1){
					%>
					<p>已发出退团申请</p>	
					<% 
				}
			}
			%>
			 </div>
			  </c:forEach>
		</div>
		</div>
					 	<div class="iwrap fix">
	<!-- +++演示代码区开始+++ -->
	

		<div class="footerbox">	
	    <div class="foot_xx">
	    <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	    </div></div>

</body>
</html>