<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
     <meta content="社团联盟" http-equiv="keywords">
     <meta name="description" content="社团联盟,wangid">
     <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
     <title>社团信息列表</title>
     <link rel="stylesheet" href="css/index.css" type="text/css">
     <link rel="stylesheet" type="text/css" href="css/children.css">
     <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
     <script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
     <script type="text/javascript" src="js/public.js"></script>
     <script type="text/javascript" src="js/YMDClass.js"></script>

<script type="text/javascript">
	function check(form) {
		if (form.keyword.value == "") {
			alert("查询信息不能为空");
			return false;
		}
	}
</script>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<title>学生端界面</title>
	<link href="css/base.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet">
	<link href="css/m.css" rel="stylesheet">
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/jquery.easyfader.min.js"></script>
	<script src="js/scrollReveal.js"></script>
	<script src="js/common.js"></script>
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
<li>
<a href="NewsListServlet">大事件</a>
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







<div class="vipshowbox">
     <div class="wid_main">
	 <div class="index_title">
	 <h2 style=" font-size:35px;margin-top: 30px;">社团风采</h2>
	 <a style="margin-top: 40px;font-size:20px;" href="ApplyCreateClubShowServlet">申请创建社团</a>
	 <div style="margin-top: 0;" class="zy_formbox">
		<div class="left">
 			<form action="GetClubByCnameServlet" method="post" onsubmit="return check(this)">
				<div class="btn">
					<input type="text" autocomplete="off" name="keyword" id="keyword" placeholder="请输入查询信息">
					<button type="submit">查询</button>
    			 </div>
    		 </form>	
     	</div>
     </div>
  
	 </div>
	  <div style="padding:10px;margin-bottom:20px;font-size:20px;" class="left"> <a href="OrderClubServlet?flag=1">按社团人数排序</a>
	  <a href="OrderClubServlet?flag=0">按社团活动数排序</a>
	  </div>
	 <div class="vipshow_pic">
	 <div class="bd">
	 <div class="ulWrap">
		  			 <ul>    <!-- 把每次滚动的n个li放到1个ul里面 -->
	<c:forEach begin="0" step="4" items="${list}" var="s">
		<li>

		   <div class="pic"><img src="getClubPicture?id=${s.id }"></div>
		 
		   <div class="txt">
		       <h3><span>${s.cname}</span> </h3>
		       <p>成立时间:${s.time}</p>
		       <p>社团人数:${s.sum_member}<br>社团活动数:${s.sum_act}</p>
		       <a href="DetailsClubServlet?id=${s.id }">了解更多>></a>
		       
		   </div>
		
		</li>
 </c:forEach>
	  </ul>	

	  			  			 <ul>    <!-- 把每次滚动的n个li放到1个ul里面 -->
	<c:forEach begin="1" step="4" items="${list}" var="s">
		<li>

		   <div class="pic"><img src="getClubPicture?id=${s.id }""></div>
		 
		   <div class="txt">
		       <h3><span>${s.cname}</span> </h3>
		       <p>成立时间:${s.time}</p>
		       <p>社团人数:${s.sum_member}<br>社团活动数:${s.sum_act}</p>
		       <a href="DetailsClubServlet?id=${s.id }">了解更多>></a>
		       
		   </div>
		
		</li>
 </c:forEach>
	  </ul>	
	  		  			 <ul>    <!-- 把每次滚动的n个li放到1个ul里面 -->
	<c:forEach begin="2" step="4" items="${list}" var="s">
		<li>

		   <div class="pic"><img src="getClubPicture?id=${s.id }""></div>
		 
		   <div class="txt">
		       <h3><span>${s.cname}</span> </h3>
		       <p>成立时间:${s.time}</p>
		       <p>社团人数:${s.sum_member}<br>社团活动数:${s.sum_act}</p>
		       <a href="DetailsClubServlet?id=${s.id }">了解更多>></a>
		       
		   </div>
		
		</li>
 </c:forEach>
	  </ul>	
	  		  			 <ul>    <!-- 把每次滚动的n个li放到1个ul里面 -->
	<c:forEach begin="3" step="4" items="${list}" var="s">
		<li>

		   <div class="pic"><img src="getClubPicture?id=${s.id }""></div>
		 
		   <div class="txt">
		       <h3><span>${s.cname}</span> </h3>
		       <p>成立时间:${s.time}</p>
		       <p>社团人数:${s.sum_member}<br>社团活动数:${s.sum_act}</p>
		       <a href="DetailsClubServlet?id=${s.id }">了解更多>></a>
		       
		   </div>
		
		</li>
 </c:forEach>
	  </ul>							
						

	  </div></div></div>
 

	
	  	  </div>  </div>
	  
	    <div class="footerbox">	
	    <div class="foot_xx">
	    <p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
	    </div></div>  
	   
	    
</body>
</html>