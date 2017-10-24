<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是上次示例页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/upload/upload1" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile1"> <input type="submit" value="第一种方式">
	</form>
	<a href="${pageContext.request.contextPath}/upload/download">下载一张图片</a>
	
</body>
</html>