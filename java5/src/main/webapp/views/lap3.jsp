<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="formUpLoadFile" action="/uploadFile" method="post"  enctype="multipart/form-data">
              <input id="fileUpload" type="file" name="photo" accept="image/png" />
			<input type="Submit" value="SUBMIT">
    </form>
</body>
</html>