$(function(){
	/****************登陆注册滑动*****************************/
	$(".rigister-box").click(function(){
		$(".login-register").animate({
			"margin-left": "-640px"
			},300);
		$(".lrbox").animate({"height":"520px"},300);
	});
	$(".login-box").click(function(){
		$(".login-register").animate({
			"margin-left": "-320px"
			},300);
		$(".lrbox").animate({"height":"400px"},300);
	});
	$(".reset-box").click(function(){
		$(".login-register").animate({
			"margin-left": "0"
			},300);
		$(".lrbox").animate({"height":"330px"},300);
	});
	/******************************************************/
	
	/********************输入框样式*************************/
	$(".input_email").focus(function(){
		$("#email").css({
			"border-color":"#0DABF3",
			"box-shadow":"0 0 10px #0DABF3"
		});
	});
	$(".input_email").blur(function(){
		$("#email").css({
			"border":"1px solid #D9D9D9",
			"box-shadow":"none"
		});
	});
	
	$(".text_input").focus(function(){
		$(this).css({
			"outline":"none",
			"border-color":"#0DABF3",
			"box-shadow":"0 0 10px #0DABF3"
		});
	});
	$(".text_input").blur(function(){
		$(this).css({
			"border":"1px solid #D9D9D9",
			"box-shadow":"none"
		});
	});
	/*************************************************************/
	
	/*****************使用Jquery Color插件**************************/


	$(".submit-rigister-input").hover(
	function(){
		$(this).stop(true,false).animate({
            "backgroundColor": "#006BF3"
    }, 200 );
		$(this).children().stop(true,false).animate({
			"line-height":"57px"
		},200);
	},
	
	function(){
		$(this).stop(true,false).animate({
            "backgroundColor": "#0DABF3"
    }, 200 );
		$(this).children().stop(true,false).animate({
			"line-height":"53px"
		},200);
	}
	);
	
	
	$(".login-box").hover(
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#0DABF3",
   		}, 200 );
			$("#arrow1").stop(true,false).css({
				"display":"block"
			}).animate({
				"left":"255px"
			},200);
			$(this).children().css("color","#FFF");
			
	},
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#FFF"
    	}, 200 );
			$("#arrow1").css({
				"left":"245px",
				"display":"none"
			});
			$(this).children().css("color","#0DABF3");
	}
	);
	
		$(".rigister-box").hover(
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#0DABF3",
   		}, 200 );
			
			$(".arrow2").css({
				"display":"block"
			}).animate({
				"left":"255px"
			},200);
			$(this).children().css("color","#FFF");
			
	},
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#FFF"
    	}, 200 );
			
			$(".arrow2").css({
				"left":"245px",
				"display":"none"
			});
			$(this).children().css("color","#0DABF3");
	}
	);
	
	$("#back-login").hover(
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#0DABF3",
   		}, 200 );
			
			$("#arrow3").css({
				"display":"block"
			}).animate({
				"left":"13px"
			},200);
			$(this).children().css("color","#FFF");
			
	},
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#FFF"
    	}, 200 );
			
			$("#arrow3").css({
				"left":"23px",
				"display":"none"
			});
			$(this).children().css("color","#0DABF3");
	}
	);
	
	
	
	$(".reset-box").hover(
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#C2C2C2",
   		}, 200 );
			$(this).children().css("color","#FFF");
	},
		function(){
			$(this).stop(true,false).animate({
            	"backgroundColor": "#FFF"
    	}, 200 );
			$(this).children().css("color","#C2C2C2");
	}
	);
	/*************************************************************/
	/******************邮箱自动完成***********************************/
	
	$('.emails').autocomplete({
        autoFocus: true,
        delay: 0,
        source: function (request, response) {
            var hosts = ['qq.com', '163.com', '126.com', '139.com', 'sina.com'], //起始
                term = request.term, //获取输入值
                ix = term.indexOf('@'), //@
                name = term, //用户名
                host = '', //域名
                result = []; //结果
            //结果第一条是自己输入
            result.push(term);
            if (ix > -1) { //如果有@的时候
                name = term.slice(0, ix); //得到用户名
                host = term.slice(ix + 1); //得到域名
            }
            if (name) {
                //得到找到的域名
                var findedHosts = (host ? $.grep(hosts, function (value, index) {
                        return value.indexOf(host) > -1;
                    }) : hosts),
                //最终列表的邮箱
                    findedResults = $.map(findedHosts, function (value, index) {
                        return name + '@' + value;
                    });
                //增加一个自我输入
                result = result.concat(findedResults);
            }
            response(result);
        },
    });

	/*****************登陆框高度**********************/
	var brHeight = $(window).height();
	var brWidth=$(window).width();
	$(".alert").css({
		position:"absolute",
		width:"300px",
		top:"20px",
		left:(brWidth-300)/2
	});
	var matop = (brHeight-520)/2;
	
	$(".lrbox").css("margin-top",matop);
	$(window).resize(function() {
		brHeight = $(window).height();
		brWidth=$(window).width();
		matop = (brHeight-520)/2;
        $(".lrbox").css("margin-top",matop);
        
        $(".alert").css({
    		position:"absolute",
    		width:"300px",
    		top:"20px",
    		left:(brWidth-300)/2
    	})
    });
	/*********************************************/
	
	/*****************发送验证码*********************/
	$(function(){
		$("#vrify").click(function(event){
			event.stopPropagation();
			if($(".input_email").val()==""){

			}else{			
				$.ajax({
					url : "vrify",
					type : "get",
					data : {					
						email:$(".input_email").val()					
					},				
				    success : function(response, stutas, xhr){	
				    	$(".alert strong").text("验证码已发送成功，请注意接收！")
				    	$(".alert").slideDown();
				    	setTimeout(function(){
				    		$(".alert").slideUp();		    		
				    	}, 3000 );
				   }
				});											 
			}
		});
	});
	
});

$(function () {
	  $('[data-toggle="popover"]').popover();
	});

$(function(){
	$(".rig-imput").click(function(){
		if($(".input_email").val()==""){
			$(".alert strong").text("请先输入邮箱账号！");
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();		    		
	    	}, 3000 );
	    	return false;
		}
		if($(".username").val()==""){
			$(".alert strong").text("请先输入账户名！");
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();		    		
	    	}, 3000 );
	    	return false;
		}
		if($(".vrify").val()==""){
			$(".alert strong").text("请先输入您接收到的验证码！");
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();		    		
	    	}, 3000 );
	    	return false;
		}		
		if($(".password").val()!="" & $(".repw").val()!=""){
			if($(".password").val()==$(".repw").val()){				
				$.ajax({
					url : "user_register",
					type : "get",
					data : {					
						email:$(".input_email").val(),
						userName:$(".username").val(),
						passWord:$(".password").val(),
						vrify:$(".vrify").val()
					},				
				    success : function(response,stutas, xhr){		    	
				   
				        if(response.email=="0"){
				        	$(".alert strong").text("此邮箱已存在，请更换其他邮箱！")
					    	$(".alert").slideDown();
					    	setTimeout(function(){
					    		$(".alert").slideUp();		    		
					    	}, 3000 )
				        }else if(response.email=="1"){
				        	$(".alert strong").text("验证码错误，请检查后重新输入！")
					    	$(".alert").slideDown();
					    	setTimeout(function(){
					    		$(".alert").slideUp();		    		
					    	}, 3000 )
				        	
				        }else if(response.email=="2"){
				        	$(".alert strong").text("已经注册成功，正在跳转页面！")
					    	$(".alert").slideDown();
					    	setTimeout(function(){
					    		$(".alert").slideUp();		    		
					    	}, 3000 );
					    	
					    	setTimeout(function(){
					    		window.location.href="index.jsp"	    		
					    	}, 1000 )
				        }
				   }
				});					
			}else{
				$(".alert strong").text("两次密码不一致，请检查后输入！")
		    	$(".alert").slideDown();
		    	setTimeout(function(){
		    		$(".alert").slideUp();		    		
		    	}, 3000 )
			}
		}else{
			$(".alert strong").text("密码与二次密码不可为空!")
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();	    		
	    	}, 3000 )
		}
			
	});
});

$(function(){
	$(".ulogin").click(function(){	
		if($(".login-email").val()==""){
			$(".alert strong").text("请先输入邮箱账号！")
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();		    		
	    	}, 3000 )
	    	return flase;
		}else if($(".login-pw").val()==""){
			$(".alert strong").text("请先输入密码再登陆！")
	    	$(".alert").slideDown();
	    	setTimeout(function(){
	    		$(".alert").slideUp();		    		
	    	}, 3000 )
	    	return flase;
		}
		$.ajax({
			url : "user_login",
			type : "get",
			data : {					
				email:$(".login-email").val(),			
				passWord:$(".login-pw").val(),		
			},				
		    success : function(response,stutas, xhr){		    	
		        if(response.email=="0"){
		        	$(".alert strong").text("账号或密码错误，请检查重新输入！")
			    	$(".alert").slideDown();
			    	setTimeout(function(){
			    		$(".alert").slideUp();		    		
			    	}, 2000 )
		        }else if(response.email=="1"){
		        	$(".alert strong").text("登陆成功，正在跳转页面！")
			    	$(".alert").slideDown();
			    	setTimeout(function(){
			    		$(".alert").slideUp();		    		
			    	}, 2000 )
			    	setTimeout(function(){
			    		window.location.href="login_home"  		
			    	}, 1000 )
		        }
		   }
		});
	});
})
