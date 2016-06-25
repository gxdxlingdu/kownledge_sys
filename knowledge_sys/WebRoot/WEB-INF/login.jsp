<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>

<title>知识库管理系统</title>
<script src="js/jquery-2.2.3.js"></script>
<script src="js/jquery.color.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/login.js"></script>
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/login.css" type="text/css">
</head>

<body>

	<ul id="scene">
		<li class="layer" data-depth="0.80"><img src="images/bg2.jpg"></li>
	</ul>
	<div class="content">

		<div class="alert alert-info alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>验证码已发送成功，请注意接收！</strong> 
		</div>

		<div class="right-box">
			<div class="lrbox">
				<div class="login-register">
					<div class="reset">
						<form action="#" method="post">
							<input class="text_input emails" type="text"
								placeholder="请输入您的邮箱地址">
							<button type="submit" class="submit-rigister-input">
								<a href="#">发送充值密码邮件</a>
							</button>
							<div
								style="height: 0;border-top: 1px #c2c2c2 solid;margin-top: 20px;"></div>
							<div class="button_rigister login-box font">
								<a href="#">已有账号？登陆</a> <img src="images/arrow-right.png"
									class="input-arrow" id="arrow1">
							</div>
							<div class="button_rigister rigister-box font">
								<a href="#">还没有账号？免费注册</a><img src="images/arrow-right.png"
									class="input-arrow arrow2">
							</div>

						</form>
					</div>
					<div class="login">
						<form 
							method="post">
							<input name="email" class="text_input emails login-email" type="text"
								placeholder="邮箱" data-container="body" data-trigger="focus"
								data-toggle="popover" data-placement="left"
								data-content="请在这里输入您的邮箱账号"> <input name="passWord"
								class="text_input login-pw" type="password" placeholder="密码"
								data-container="body" data-trigger="focus" data-toggle="popover"
								data-placement="left" data-content="请在这里输入您的登录密码">
							<button type="button" class="submit-rigister-input ulogin">
								<a>登陆</a>
							</button>
							<div
								style="height: 0;border-top: 1px #c2c2c2 solid;margin-top: 20px;"></div>
							<div class="button_rigister rigister-box font">
								<a href="#">还没有账号？免费注册</a><img src="images/arrow-right.png"
									class="input-arrow arrow2">
							</div>
							<div class="button_reset reset-box font">
								<a href="#">忘记密码？重置</a>
							</div>
						</form>
					</div>
					<div class="regiseter">
						<form action="${pageContext.request.contextPath}/user_register"
							method="post">
							<div id="email">
								<input name="email" class="input_email emails" type="text"
									placeholder="邮箱" data-container="body" data-trigger="focus"
									data-toggle="popover" data-placement="left"
									data-content="请在这里输入您的邮箱账号"> <a href="#" id="vrify"><span>
										获取验证码</span></a>
							</div>
							<input name="userName" class="text_input username" type="text"
								placeholder="您的名字" data-container="body" data-trigger="focus"
								data-toggle="popover" data-placement="left"
								data-content="请在这里输入您的账户名"> <input name="passWord"
								class="text_input password" type="password" placeholder="密码（字母、数字，至少6位）"
								data-container="body" data-trigger="focus" data-toggle="popover"
								data-placement="left" data-content="请输入您的密码(数字,字母至少6位)">
							<input name="PassWordAgain" class="text_input repw" type="password"
								placeholder="再次输入密码" data-container="body" data-trigger="focus"
								data-toggle="popover" data-placement="left"
								data-content="请在这里再次输入您的密码"> <input name="vrify"
								class="text_input vrify" type="text" placeholder="验证码"
								data-container="body" data-trigger="focus" data-toggle="popover"
								data-placement="left" data-content="请在这里输入您的收到的验证码">
							<button type="button" class="submit-rigister-input rig-imput">
								<a>注册</a>
							</button>
							<div class="button_rigister login-box font" id="back-login">
								<a href="#">已有账号？登陆</a> <img src="images/arrow-left.png"
									id="arrow3" alt="">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<svg style="width: 65%;height: 100%;"> <polygon
				points="0,0 250,0 1100,1080 0,1080"
				style="fill: rgba(18, 30, 40,0.7);stroke:black;stroke-width:0" /> <text
				x="60" y="220" fill="white" font-size="35px" font-family="cursive">知识</text>
		<text x="60" y="400" fill="white" font-size="20px"
				font-family="cursive">终点的距离,无关前行的速度,取决于前行的态度</text> <text x="60"
				y="300" fill="white" font-size="20px" font-family="cursive">迷茫那是因为才华还配不上梦想</text>
		<text x="60" y="350" fill="white" font-size="20px"
				font-family="cursive">现代人不是渴望成功，而是渴望马上成功</text> <text x="60" y="450"
				fill="white" font-size="15px" font-family="cursive">yesterday
		is history，tomorrow is a mystery，but today is a </text> <text x="60" y="470"
				fill="white" font-size="15px" font-family="cursive">
		gift，that's why it is called the present. </text> </svg>
	</div>

	<script src="js/jquery.parallax.js"></script>
	<script>
		$('#scene').parallax({
			originX : 0.0,
			originY : 0.0,
			scalarX : 8,
			scalarY : 6,
			invertX : true,
			invertY : true,
		});
	</script>

</body>
</html>
