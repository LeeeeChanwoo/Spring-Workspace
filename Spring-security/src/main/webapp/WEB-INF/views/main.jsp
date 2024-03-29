<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 
	Authentication? 로그인한 사용자의 정보가 담기는 객체. 요안에 내용을 얻어오는 방법?
 -->
 <div class="content">
	<p>
		-Principal : 인증된 사용자객체(Member객체)
		<sec:authentication property="principal" />
		<br />
	</p>
	<p>
		-Credentials : 인증에 필요한 "비밀번호"에 대한 정보를 가진 객체, 내부 인증작업시에만 사용되며 외부에 노출x
		<sec:authentication property="credentials" />
		<br />
	</p>
	<p>
		Authorities : 인증된 사용자가 가진 권한목록들 => ROLE_USER, ROLE_ADMIN..
		<sec:authentication property="authorities" />
		<br />
	</p>
</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>