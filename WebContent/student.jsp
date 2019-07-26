<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="club.ActListBean" %>
<%@ page import="club.NewsListBean" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta content="社团管理" http-equiv="keywords">
<meta name="description" content="社团管理,wangid">
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
<title>社团管理</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="js/public.js"></script><!-- 菜单js等-->

</head>
<body>

<!--头部-->
<div class="topbox">
	<div class="top_line">
		<div class="wid_main fix">
			<h2>您好，欢迎访问ECLUBS!</h2>
		
		</div>
	</div>
	<div class="top_logo">
		<div class="wid_main fix">
			<a ><img src="images/logo.png"/></a>
			<span><img src="images/running.png"/></span>
		</div>
	</div>
	<div class="top_nav">
		<div class="wid_main">
			<ul class="fix">
				<li class="cur">
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
				<li>
					<a href="SelfPageServlet">  个人中心</a>
		
				</li>
				
	
				<li>
					<a href="LogOutServlet">  退出登录</a>
				</li>
			</ul>
			<div class="nav_bg_cur">
				<img src="images/icon_nav.png">
			</div>
		</div>
	</div>
</div>

<div class="bannerbox">
	<!-- banner -->
	<div class="banner">
		<div class="hd">
			<ul>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<div class="bd">
			<ul>
				<li><a><img src="images/banner1.jpg" /></a></li>
				<li><a><img src="images/banner2.jpg" /></a></li>
				<li><a><img src="images/banner3.jpg" /></a></li>
			</ul>
		</div>
		<!-- 下面是前/后按钮代码，如果不需要删除即可 -->
		<a class="prev" href="javascript:void(0)"><img src="images/jt_left.png"></a>
		<a class="next" href="javascript:void(0)"><img src="images/jt_right.png"></a>
	</div>
	<script type="text/javascript">
	jQuery(".banner").slide({mainCell:".bd ul",autoPlay:true,interTime:3000,effect:"fold",delayTime:1000,mouseOverStop:false,});
	</script>

	<!-- banner end -->
</div>
<!-- 内容框 -->
<div class="contentbox">
	<!-- 学会公告比赛公告 -->
	<div class="xhgg_box">
		<div class="wid_main">
		
			<!-- 比赛公告 -->
			<div class="bsggbox fix">
				<div class="bsgg">
					<div class="index_title">
						<h2><i></i>活动公告</h2>
						<a href="ActListServlet">更多>></a>
					</div>
					<div class="box fix">
						<!-- 比赛花絮图片 -->
						<div class="bshx_pic">
							<div class="bd">
								<ul>
									<li>
										<a >
											<img src="images/pic01.jpg" />
											<h2><p>摄影——祈愿亭</p><span>2019-07-21</span></h2>
										</a>
									</li>
									<li>
										<a >
											<img src="images/pic02.jpg" />
											<h2><p>校园一角</p><span>2019-07-21</span></h2>
										</a>
									</li>
									<li>
										<a >
											<img src="images/pic03.jpg" />
											<h2><p>环青海湖骑行</p><span>2019-07-21</span></h2>
										</a>
									</li>
								</ul>
							</div>

							<!-- 下面是前/后按钮代码，如果不需要删除即可 -->
							<a class="prev" href="javascript:void(0)"><img src="images/jt_left.png"></a>
							<a class="next" href="javascript:void(0)"><img src="images/jt_right.png"></a>
						</div>
						<script type="text/javascript">
						jQuery(".bshx_pic").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true});
						</script>

						<!-- 比赛公告栏 -->
						<div class="bsgg_txt">
						<c:forEach items="${act }" begin="0" end = "7" var="s">
							<ul>
								<li><a href="ActivityDetailServlet?id=${s.id }">${s. title}</a><span>${s.time }</span></li>
							
							</ul>
 						</c:forEach>
						</div>
					</div>
				</div>
		      <div class="kjrk">
					<div class="index_title">
						<h2><i></i>快捷入口</h2>
					</div>
					<ul>
						<li><a href="SelfPageServlet"><img src="images/icon_01_dl.png">个人中心</a></li>
					
						<li><a class="login_user"><img src="images/icon_03_cx.png">我的消息</a></li>
					</ul>
				</div>
			</div>
			<!-- 比赛公告 end-->
		</div>
	</div>
	<!-- 学会公告比赛公告 end-->

	<!-- 最新动态 -->
	<div class="newsbox">
		<div class="wid_main">
			<div class="index_title">
				<h2><i></i>最新动态</h2>
				<a href="NewsListServlet">更多>></a>
			</div>
			
			<ul>
			<c:forEach items="${news }" begin="0" end="3" var="s">
				<li>
					<a href="DetailsNewsServlet?id=${s.id }">
						<i><img src="getNewsPicture?id=${s.id }"></i>
						<div>
							<h2>${s. title}</h2>
							<p>${s. content}</p>
							<span>${s. time}</span>
						</div>
					</a>
				</li>
				</c:forEach>

			</ul>
		</div>
	</div>
   <script type="text/javascript">      /* 多行/多列的滚动解决思路在于：把每次滚动的n个li放到1个ul里面，然后用SuperSlide每次滚动1个ul，相当于每次滚动n个li  */
	      jQuery(".vipshow_pic").slide({titCell:".hd ul",mainCell:".bd .ulWrap",effect:"leftLoop",autoPlay:true,vis:4,interTime:5000,});
	  </script>
	  </div></div>	
	  
	  <div class="videobox">
	  <div class="wid_main">
	  <div class="index_title">
	  <h2><i></i>精彩剪影</h2>
	  </div>
	  <div class="video_list">

	  <div class="parBd">
	  
	  <div class="slideBox">
	  <ul>
		  <li>
		     <span><img src="images/pic04.jpg"><i></i></span>
			 <h2>军训动员大会</h2>
		  </li>
		  <li>
			 <span><img src="images/pic05.jpg"><i></i></span>
			 <h2>篮球大赛</h2>
		  </li>
		  <li>
		     <span><img src="images/pic06.jpg"><i></i></span>
			 <h2>励勤杯乒乓球比赛</h2>
		  </li>
		  <li>
		     <span><img src="images/pic07.jpg"><i></i></span>
			 <h2>爱心社</h2>
		  </li>
	   </ul>
	   </div></div></div>

		<script type="text/javascript">
			  jQuery(".video_list .slideBox").slide({ mainCell:"ul",vis:3,effect:"leftMarquee",interTime:40,autoPlay:true,trigger:"click"});
			  jQuery(".video_list").slide({titCell:".parHd li",mainCell:".parBd"});
        </script>
		</div></div>
	<!-- 底部 -->
	<div class="footerbox">

		<div class="foot_xx">
			<p>CopyRight © 青海大学计算机系“四菜一汤”制&nbsp;&nbsp;2019&nbsp;&nbsp;All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;“四菜一汤”&nbsp;&nbsp;&nbsp;&nbsp;</p>
		</div>
	</div>
	<!-- 底部end -->
<div class="login_box">
	<div class="login">
		<i><img src="images/close.png"></i><br>
		<br>
			<h3><center><span style="font-size:20px;text-align:center;color:#faa528;">您收到如下消息：</span></center></h3><br>
			<c:forEach items="${list}" var="s">
			<tbody>			 
			      <ul>
			      
				     <li>
				        <h3><span style="font-size:15px;color:#faa528;">${s.content}    <a href="CheckResponseServlet?id=${s.id}&account=${s.account}&flag=${s.flag}">
					    	<button style="font-size:20px color:#faa528;	
							background:#faa528;
							float:right;
							padding:3px 6px;
							border-radius: 20px;
							border: 1px solid rgba(0,0,0,0.4) ;
							cursor: pointer;" type="submit">确认</button><br><br></span></h3>
				     
							
				        </a>
				     </li>
				  </ul>				 
			</tbody>	
			</c:forEach>	
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
<!-- 内容框end -->


</body>
</html>