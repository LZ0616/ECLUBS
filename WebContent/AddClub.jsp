


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


<script type="text/javascript">
	function check(form) {
		if (form.cname.value == "null") {
			alert("此社团名称禁用！");
			return false;
		}
		if (form.cname.value == "") {
			alert("社团名称不能为空");
			return false;
		}
		if (form.master.value == "") {
			alert("社长不能为空");
			return false;
		}
		if (form.time.value == "") {
			alert("成立时间不能为空");
			return false;
		}
		if (form.intro.value == "") {
			alert("简介不能为空");
			return false;
		}
		if (form.rules.value == "") {
			alert("社规不能为空");
			return false;
		}
		return true;
	}
</script>
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
		<div class="zy_formbox zy_register fix">
			<div class="left">
			
			    <p align="center">申请社团创建</p>

				<form id="form1" name="form1" action="ApplyCreateClubDoServlet" method="post" enctype="multipart/form-data">
					<div>
						<p><b>*</b>社团名称</p>
						<input type="text" name="cname" id="cname" placeholder="请输入社团名">
					</div>
			
				
					<div>
						<p><b>*</b>社团简介</p>
						<textarea name="intro" id="intro" placeholder="请输入社团简介"></textarea>
					</div>
					<div>
						<p><b>*</b>社团规则</p>
						<textarea name="rules" id="rules" placeholder="请输入社团规则"></textarea>
					</div>
					<div>
						<p><b>*</b>申请人：${ccb.master }</p>
					</div>
						<p><b style="color:red">*</b>社团logo</p>
						<input type="file" name="file" id="upfile">

					<div class="btn">
						<button type="submit">提交</button>
						<button type="reset">重新填写</button>
					</div>
				

				</form>
			</div>
		
		</div>
	</div>

		</div>
		<div class="footerbox">	
	    <div class="foot_xx">
	    <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	    </div></div>

</body>
</html>