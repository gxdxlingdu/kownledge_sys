/**
 * Created by admin on 2016/5/7.
 */
//-----------------------------------根据文件类型显示图片
$(function getHomeFiles(){
	$.ajax({
		url : "getHomeFiles",
		type : "post",
		data : {					
							
		},				
	    success : function(response, stutas, xhr){
	    	var flag=0;
	    	$.each(response.myfiles,function(index,value){
	    		var fileDiv=$(".fileDiv").eq(0).clone(true);
		    	fileDiv.css("display","block");

		    	if(displayPic(value.fileType)=="images/image.png"){		    		
		    		fileDiv.find("a").attr("href",value.filePath);	
		    		fileDiv.find("img").attr("src",value.filePath).css({"max-width":"88px","max-height":"80px","width":"auto","height":"auto"});	
		    	}else{
		    		fileDiv.find("img").attr("src",displayPic(value.fileType));
		    	}
		    	
	    		fileDiv.find("p").text(value.fileName);
				$(".all-files").append(fileDiv);				
	    		
//	    		处理导航			
	    	if(value.filePath!=null&flag==0){//flag 让此代码段只执行一次
	    		
	    		var str1=value.filePath.substr(7)
	    		var strArray=str1.split("/");
	    		strArray.pop();//删除数组最后一个元素
	    		strArray.shift();//删除数组第一个元素  ，下标左移	    		
	    		for(x in strArray){
	    			var li=$(".breadcrumb").find("li").eq(0).clone(true);
	    			li.css("display","block");
	    			li.find(".text").text(strArray[x]);
	    			
	    			$(".breadcrumb").prepend(li);
	    		}
	    		flag++;
	    	}
	    		
			});
	    
	   }
	});	
	
	
	function displayPic(filetype){
	    var t=filetype.toLowerCase();
	    var imgpath;
	    if(t=="knowledge-sys-floder"){
	        imgpath="images/folder.png";
	    }
	    else if(t=="jpg"|t=="jpeg"|t=="png"|t=="bmp"|t=="gif"){
	        imgpath="images/image.png";
	    }else if(t=="psd"){
	        imgpath="images/psd.png";
	    }else if(t=="txt"){
	        imgpath="images/txt.png";
	    }else if(t=="doc"|t=="docx"){
	        imgpath="images/doc.png";
	    }else if(t=="pdf"){
	        imgpath="images/pdf.png";
	    }else if(t=="xlsx"|t=="xls"){
	        imgpath="images/xlsx.png";
	    }else if(t=="mp3"|t=="wav"){
	        imgpath="images/audio.png";
	    }else if(t=="mp4"|t=="avi"|t=="rmvb"|t=="wmv"|t=="mkv"|t=="flv"|t=="3gp"){
	        imgpath="images/movie.png";
	    }else if(t=="html"){
	        imgpath="images/html.png";
	    }else if(t=="apk"){
	        imgpath="images/apk.png";
	    }else if(t=="exe"){
	        imgpath="images/exe.png";
	    } else if(t=="zip"|t=="rar"){
	        imgpath="images/rar.png";
	    }else if(t=="css"){
	    	 imgpath="images/css.png";
	    }else if(t=="js"){
	    	 imgpath="images/js.png";
	    }else if(t=="ppt"|t=="pptx"){
	    	 imgpath="images/ppt.png";
	    }else{
	        imgpath="images/undefined.png";
	    }
	    return imgpath;
	}
});

$(function () {
    $(".uk-panel li").click(function () {
        $(this).addClass("uk-active").siblings().removeClass("uk-active");
    });

    var bwHeight = $(window).height();
    $(".right-box").css({
        height: bwHeight - 62
    })
    $(".leftnav .uk-panel-box").css({
        height: bwHeight - 94
    });
    $(".files").css({
        height: bwHeight - 127
    });
    $(window).resize(function () {
        bwHeight = $(window).height();
        $(".leftnav .uk-panel-box").css({
            height: bwHeight - 94
        });
        $(".right-box").css({
            height: bwHeight - 62
        });
        $(".files").css({
            height: bwHeight - 127
        });
    });
});

$(function () {
    $(".files").eq(0).css("display", "block");
    $(".leftnav li").click(function () {
        var index = $(this).index();
        $(".files").eq(index).show();
        $(".files").eq(index).siblings().hide();
    })
});

$(function(){
	var seledfiles;//被选中的文件
	var files = [];//被选中文件的名字集合
	
	//显示菜单并获取被选着的文件
    $(".files .uk-panel-box").mousedown(function(e){    	
//                alert(e.which) // 1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键
        if(e.which==3){
            $(this).bind("contextmenu",function(e){
                return false;});
            $(".smartMenu").css({
                top: e.pageY-62,
                left: e.pageX-180,
                display:"block"
            });
            if($(this).attr("class").indexOf("seled")==-1){
            	$(this).addClass("seled").siblings().removeClass("seled");  
            }
            seledfiles = $(".seled");            
            for (var i = 0; i < seledfiles.length; i++) {
            	files.push(seledfiles.eq(i).find("p").text()); // 将文本框的值添加到数组中
            }
            
        }else{
            $(".smartMenu").hide();
        }
    });
    
  //------------------隐藏菜单
    $(".files").mousedown(function(e){
    //    alert(e.which) // 1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键
        if(e.which!=3){
            $(".smartMenu").hide();
        }
    });
    
    //-------------------文件下载
    
    $("#download").click(function(){  
		var fileName = JSON.stringify(files);
		document.getElementById("download").href='downAction?fileName='+fileName;
    	//还原数组为空           	
    	files = [];
    	$(".smartMenu").hide();
    });  
    
    //--------------------文件删除
    $("#delete").click(function(){
    	var fileName = JSON.stringify(files);
		$.ajax({
			url : "fileOperate_deleteFile",
			type : "post",
			data : {					
				fileName:fileName
			},				
		    success : function(response, stutas, xhr){
		    	location.reload(true); 	
		   }
		});
    	//还原数组为空           	
    	files = [];
    	$(".smartMenu").hide();
    })
    //------------文件上传成功回调   自动刷新页面
    $(".uk-close").click(function(){
    	location.reload(true); 	
    })
    
    //--------------------文件重名
    $("#reName").click(function(){
    	var fileName = files[0];
    	var newName;
    	UIkit.modal.prompt("输入新文件名字：",fileName,function(value){       		
    		$.ajax({
    			url : "fileOperate_reName",
    			type : "get",
    			data : {					
    				fileName:fileName,
    				newName:value
    			},				
    		    success : function(response, stutas, xhr){
    		    	var array = response.flag;
    		    	if(array[0]=="success"){   		        	    		    		
    		    		location.reload(true); 	
    		    	}else{
    		    		UIkit.modal.alert("重命名失败！");
    		    	}    		    					   
    		   }
    		});  	
    		
		});
    	//还原数组为空      
		files = [];		
    	$(".smartMenu").hide();
    });
});


//-----------------------ctrl+左键选取
$(function () {

    $(".fileDiv").mousedown(function(e){
        if(e.which==1 && e.ctrlKey){
            if ($(this).hasClass("seled")) {
                $(this).removeClass("seled")
            } else {
                $(this).addClass("seled");
            }
        }
    });
    $(".fileDiv").mouseup(function(e){
        if(e.which==1&&e.ctrlKey==false){
        	$(this).addClass("seled");
        	$(this).siblings().removeClass("seled");
        }
    });
})

//-----------------------------------鼠标框选
$(function(){
	document.getElementById("rightContent").onmousedown = function(e) {		
		if(e.which==3){
			return false;
		}
		if(e.ctrlKey){
		   return false;
		}
		var selList = [];
		var fileNodes = document.getElementsByTagName("div");
		for ( var i = 0; i < fileNodes.length; i++) {
			if (fileNodes[i].className.indexOf("fileDiv") != -1) {
				fileNodes[i].className = "fileDiv"+" uk-width-1-4 uk-panel uk-panel-box";
				selList.push(fileNodes[i]);
			}
		}
		var isSelect = true;
		var evt = window.event || arguments[0];
		var startX = (evt.x || evt.clientX);
		var startY = (evt.y || evt.clientY);
		var selDiv = document.createElement("div");
		selDiv.style.cssText = "position:absolute;" +
				"width:0px;height:0px;" +
				"font-size:0px;margin:0px;" +
				"padding:0px;border:1px dashed #0099FF;" +
				"background-color:#C3D5ED;" +
				"z-index:1000;" +
				"filter:alpha(opacity:60);" +
				"opacity:0.6;" +
				"display:none;";
		selDiv.id = "selectDiv";
		document.body.appendChild(selDiv);

		selDiv.style.left = startX + "px";
		selDiv.style.top = startY + "px";

		var _x = null;
		var _y = null;
		clearEventBubble(evt);

		document.onmousemove = function() {
			evt = window.event || arguments[0];
			if (isSelect) {
				if (selDiv.style.display == "none") {
					selDiv.style.display = "";
				}
				_x = (evt.x || evt.clientX);
				_y = (evt.y || evt.clientY);
				selDiv.style.left = Math.min(_x, startX) + "px";
				selDiv.style.top = Math.min(_y, startY) + "px";
				selDiv.style.width = Math.abs(_x - startX) + "px";
				selDiv.style.height = Math.abs(_y - startY) + "px";			
				// ---------------- 关键算法 ---------------------
				var _l = selDiv.offsetLeft-182, _t = selDiv.offsetTop-128;
				var _w = selDiv.offsetWidth, _h = selDiv.offsetHeight;

				for ( var i = 0; i < selList.length; i++) {
					var sl = selList[i].offsetWidth + selList[i].offsetLeft;
					var st = selList[i].offsetHeight + selList[i].offsetTop;
					if (sl > _l && st > _t && selList[i].offsetLeft < _l + _w && selList[i].offsetTop < _t + _h) {
						if (selList[i].className.indexOf("seled") == -1) {
							selList[i].className = selList[i].className + " seled";
						}
					} else {
						if (selList[i].className.indexOf("seled") != -1) {
							selList[i].className = "fileDiv"+" uk-width-1-4 uk-panel uk-panel-box";
						}
					}
				}

			}
//			clearEventBubble(evt);  为了防止冲突，不对默认行为处理。
		};

		document.onmouseup = function() {
			isSelect = false;
			if (selDiv) {
				document.body.removeChild(selDiv);
//				showSelDiv(selList);
			}
			selList = null, _x = null, _y = null, selDiv = null, startX = null, startY = null, evt = null;
			
			control = true;
		};
	};
	function clearEventBubble(evt) {
        if (evt.stopPropagation)
            evt.stopPropagation();
        else
            evt.cancelBubble = true;
        if (evt.preventDefault)
            evt.preventDefault();
        else
            evt.returnValue = false;
    }
//    function showSelDiv(arr) {
//        var count = 0;
//        var selInfo = "";
//        for ( var i = 0; i < arr.length; i++) {
//            if (arr[i].className.indexOf("seled") != -1) {
//                count++;
//                selInfo += arr[i].innerHTML + "\n";
//            }
//        }
//        alert("共"+ count + " 个文件，分别是：\n" + selInfo);
//    }
});

//---------------------create new folder
$(function(){
	$(".createfile").click(function(e){		
		e.stopPropagation();
		UIkit.modal.prompt("输入文件夹名字：","",function(value){
			$.ajax({
				url : "createfolder",
				type : "post",
				data : {					
					foldername:value			
				},				
			    success : function(response, stutas, xhr){			  
			    	if(response.flag=="true"){
			    		location.reload(true); 	
			    	}else{
			    		UIkit.modal.alert("新建文件夹失败！");
			    	}
			    					   
			   }
			});	
		});
	});
	
	//--------------------------鼠标双击左键 进入下层
	$(".fileDiv").dblclick(function(e){
		e.stopPropagation();
		var fileName=$(this).find("p").text();
		$.ajax({
			url : "getHomeFiles",
			type : "post",
			data : {					
				fileName:fileName			
			},				
		    success : function(response, stutas, xhr){		    
		    	location.reload(true); 			    	
		   }
		});
	});
	
//	------------------------------点击home图标    返回所有文件层 （事件处理）
	$(".breadcrumb li").click(function(){
		var clickname;
		if($(this).attr("name")=="home"){
			clickname=null;
		}else{
			clickname=$(this).find(".text").text();			
		}
		$.ajax({
			url : "updateSession",
			type : "post",
			data : {
				clickname:clickname
			},				
		    success : function(response, stutas, xhr){		    
		    	location.reload(true); 			   
		   }
		});
	})
//	--------------------------------------点击所有文件(更改currentpath)----
	$(".AllFiles").click(function(){
		$.ajax({
			url : "updateCpath",
			type : "post",
			data : {				
			},				
		    success : function(response, stutas, xhr){		    
		    	location.reload(true);   		    	
		   }
		});
	});
});
