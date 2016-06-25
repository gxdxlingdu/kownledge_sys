<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Title</title>
<script src="js/jquery-2.2.3.js" type="text/javascript"></script>
<script src="js/uikit.js"></script>
<script src="js/grid.js"></script>
<script src="js/home.js"></script>
<link href="css/zoom.css" rel="stylesheet" type="text/css">
<link href="css/uikit.css" rel="stylesheet" type="text/css">
<link href="css/uikit.gradient.css" rel="stylesheet" type="text/css">
<link href="css/home.css" rel="stylesheet" type="text/css">
</head>
<%
	String path = request.getContextPath();
	session.setAttribute("projectName", path);
%>
<body>
	<nav class="uk-navbar">
		<a class="uk-navbar-brand" href="#">知识库系统</a>
		<ul class="uk-navbar-nav">
			<li aria-expanded="false" aria-haspopup="true" class="uk-parent "
				data-uk-dropdown=""><a href="" class="userlist"><i
					class="uk-icon-user"></i> 测试用户</a>
				<div style="top: 40px; left: 0px;"
					class="uk-dropdown uk-dropdown-navbar uk-dropdown-bottom">
					<ul class="uk-nav uk-nav-navbar">
						<li><a href="#">账号中心</a></li>
						<li><a href="#">帮助中心</a></li>
						<li><a href="#">意见反馈</a></li>
						<li class="uk-nav-divider"></li>
						<li><a href="#">重新登录</a></li>
						<li><a href="#">退出系统</a></li>
					</ul>
				</div></li>
		</ul>
		<div class="uk-navbar-content uk-hidden-medium">
			<form class="uk-form uk-margin-remove uk-display-inline-block">
				<input placeholder="请输入关键字" type="text">
				<button class="uk-button uk-button-primary">搜索</button>
			</form>
		</div>
	</nav>


	<div class="uk-width-medium-1-10 leftnav">
		<div class="uk-panel uk-panel-box">
			<ul class="uk-nav uk-nav-side uk-nav-parent-icon" data-uk-nav="">
				<li class="uk-active AllFiles"><a href="#"><i
						class="uk-icon-folder"></i> 所有文件</a></li>
				<li><a href="#"><i class="uk-icon-file"></i> 常用文件</a></li>
				<li><a href="#"><i class="uk-icon-picture-o"></i> 图片</a></li>
				<li><a href="#"><i class="uk-icon-book"></i> 文档</a></li>
				<li><a href="#"><i class="uk-icon-video-camera"></i> 视频</a></li>
				<li><a href="#"><i class="uk-icon-music"></i> 音乐</a></li>
			</ul>
		</div>
	</div>

	<div class="right-box">
		<div class="top-nav">
			<ul class="breadcrumb">
				<li style="display:none"><a href="#"> <span
						class="uk-icon-folder"></span> <span class="text">所有文件</span>
				</a></li>
				<li name="home"><a href="#"> <span
						class="uk-icon-home uk-icon-medium"></span></a></li>
			</ul>
			<div class="top-btn" data-uk-button-radio>
				<button class="uk-button uk-button-primary"
					data-uk-modal="{target:'#modal6'}">
					<i class="uk-icon-upload"></i> 上传文件
				</button>
				<button class="uk-button createfile">
					<i class="uk-icon-plus-square"></i> 新建文件夹
				</button>

				<button class="uk-button" style="display: none">
					<i class="uk-icon-download"></i> 下载
				</button>
				<button class="uk-button" style="display: none">
					<i class="uk-icon-pencil-square-o"></i> 重命名
				</button>
				<button class="uk-button" style="display: none">
					<i class="uk-icon-trash-o"></i> 删除
				</button>

			</div>
		</div>

		<div id="modal6" class="uk-modal">
			<div class="uk-modal-dialog" style="width:80%;height:410px;">
				<button type="button" class="uk-modal-close uk-close"></button>
				<div class="uk-modal-header"
					style="padding: 10px; margin-bottom: 5px;">文件上传</div>

				<div style="height: 340px;">
					<iframe width="100%" height="100%" src="upload_page"></iframe>
				</div>

			</div>
		</div>

		<div>
			<!-- 所有文件 -->
			<div class="files all-files uk-flex gallery" id="rightContent">
				<!-- 文件样式  用来克隆 -->
				<div class="uk-width-1-4 uk-panel  uk-panel-box fileDiv "
					style="display:none">
					<a> <div class="box-content"><img src="images/folder.png"></div>
						<p>图片</p>
					</a>
				</div>
				<div class="uk-width-1-4 uk-panel  uk-panel-box fileDiv "
					style="display:none">
					<a><div class="box-content"><img src="images/folder.png"></div>
						<p>图片</p>
					</a>
				</div>
				<!-- -- -->

			</div>

			<div class="files fre-files">常用文件</div>
			<div class="files picture">图片</div>
			<div class="files document">文档</div>
			<div class="files video">视频</div>
			<div class="files music">音乐</div>

			<div class="smartMenu">
				<a id="download"><li>&nbsp;&nbsp;&nbsp;<i
						class="uk-icon-download"></i>&nbsp;&nbsp;下载
				</li></a>
				<li>&nbsp;&nbsp;&nbsp;<i class="uk-icon-copy"></i>&nbsp;&nbsp;复制
				</li> <a id="delete"><li>&nbsp;&nbsp;&nbsp;<i
						class="uk-icon-trash-o"></i> &nbsp;&nbsp;删除
				</li></a> <a id="reName"><li>&nbsp;&nbsp;&nbsp;<i
						class="uk-icon-pencil-square-o reName"></i> &nbsp;&nbsp;重命名
				</li></a>
				<li>&nbsp;&nbsp;&nbsp;<i class="uk-icon-recycle"></i>&nbsp;&nbsp;刷新
				</li>
			</div>
		</div>
	</div>

</body>
<script src="js/zoom.js"></script>
</html>
