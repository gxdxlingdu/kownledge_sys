<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>

    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
	<script src="js/jquery-2.2.3.js" type="text/javascript"></script>
	<script src="js/fileinput.js" type="text/javascript"></script>
	<script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
	<script>
		$(function(){
			$("#upload_input").fileinput({
    
                uploadUrl: "${pageContext.request.contextPath}/fileUploadAction", //上传的地址
                //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
                uploadAsync: true, //默认异步上传
                showUpload: true, //是否显示上传按钮
                showRemove : true, //显示移除按钮
                showPreview : true, //是否显示预览
                showCaption: true,//是否显示标题
                previewFileType:"any",
                browseClass: "btn btn-primary", //按钮样式     
                dropZoneEnabled: true,//是否显示拖拽区域
                maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                maxFileCount: 50, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                //msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                previewTemplates:{
                	text: '<div class="file-preview-frame{frameClass}" id="{previewId}" data-fileindex="{fileindex}" style="width:130px">\n' +
                	' <span class="glyphicon glyphicon-file" aria-hidden="true" style="font-size:80px;line-height:160px"></span>\n' +
                    '   {footer}\n' +
                    '</div>',
                    other: '<div class="file-preview-frame{frameClass}" id="{previewId}" data-fileindex="{fileindex}" style="width:130px">\n' +
                	' <span class="glyphicon glyphicon-file" aria-hidden="true" style="font-size:80px;line-height:160px"></span>\n' +
                    '   {footer}\n' +
                    '</div>',
                    html: '<div class="file-preview-frame{frameClass}" id="{previewId}" data-fileindex="{fileindex}" style="width:130px">\n' +
                	' <span class="glyphicon glyphicon-header" aria-hidden="true" style="font-size:80px;line-height:160px"></span>\n' +
                    '   {footer}\n' +
                    '</div>',
                    object: '<div class="file-preview-frame{frameClass}" id="{previewId}" data-fileindex="{fileindex}" style="width:130px">\n' +
                	' <span class="glyphicon glyphicon-file" aria-hidden="true" style="font-size:80px;line-height:160px"></span>\n' +
                    '   {footer}\n' +
                    '</div>',
                },
			});
		});
	</script>
	<style type="text/css">
	*{margin:0; padding:0;}
	html,body{width:100%; height:100%;}
	</style>
  </head>
  
  <body>
    <input id="upload_input" type ="file" name="upload" multiple >
  </body>
</html>
