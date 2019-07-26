


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
function check(){
	var extlist="jpg,Jpg,JPG,jpeg,JEPG,gif,GIF,Gif,bmp,BMP,Bmp,png,PNG,Png";
	var name=document.getElementById('file').value;
	if(name==""){
		alert("请选择要上传的图片");
		return false;
	}
	var items=name.split(".");
	var ext=items[items.length-1];
	var inx =extlist.indexOf(ext);
	if(inx<0){
		alert("请选择图片文件");
		return false;
	}
}
function DrawImage(ImgD,FitWidth,FitHeight){
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		if(image.width/image.height>=FitWidth/FitHeight){
			if(image.width>FitWidth){
				ImgD.width=FitWidth;
				ImgD.heigth=(image.height*FitWidth)/image.width;
			}else {
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}else{
			if(image.height>FitHeight){
				ImgD.height=FitHeight;
				ImgD.width=(image.width*FitHeight)/image.height;
			}else {
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}
	}
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
	 <li><a href="ListClubServlet">社团一览</a>

	 </li>
	 <li class="cur"><a href="ActListServlet">最近活动</a>

	 </li>
	 <li><a href="NewsListServlet">大事件</a>

	 </li>
					<li>
					<a href="SelfPageServlet"> 个人中心</a>
		
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
				<form id="form1" name="form1" action="ApplyActDoServlet?cid=${act.cid }" method="post" enctype="multipart/form-data">
					<div>
						<p><b>*</b>社团名:${act.cname }</p>
					</div>
					<div>
						<p><b>*</b>活动发布人:${act.master }</p>
					</div>
					<div>
						<p><b>*</b>活动主题</p>
						<input type="text" name="title" placeholder="请输入活动主题">
					</div>
					<div>
						<p><b>*</b>活动内容</p>
						<textarea name="content" placeholder="请输入活动内容"></textarea>
					</div>
					
				
					<div>
						<p><b>*</b>活动时间</p>
						<input type="text" name="time" placeholder="请输入活动时间">
					</div>
					<p><b style="color:red">*</b>活动照片</p>
						<input type="file" id="file" name="file">
					
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