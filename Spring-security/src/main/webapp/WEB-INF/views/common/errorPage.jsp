<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<div align="center">
		<img src="https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678069-sign-error-64.png">
		<br><br>	
		<h1 style="font-weight:bold;">접근 권한이 없는 페이지입니다</h1>
		<a href="${pageContext.request.contextPath}">홈으로</a>
	</div>
	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>