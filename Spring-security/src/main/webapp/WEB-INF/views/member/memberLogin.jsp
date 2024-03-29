<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Jquey 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 부트스트랩에서 제공하있는 스타일 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 부투스트랩에서 제공하고있는 스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
$(()=>{
	$("#loginModal").modal()
					.on('hide.bs.modal',e=>{
						//modal를 비활성화시 이전페이지로 이동 (X버튼, 취소, 모달창 이외의 영역 클릭시)
						location.href='${pageContext.request.contextPath}';
					});
})
</script>
</head>
<body>
<div class="modal fade" id="loginModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<!-- 모달 해더 -->
				<div class="modal-header">
					<h4 class="modal-title">Login</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<form:form action="${pageContext.request.contextPath }/login.me" method="post">
					<!--  모달 바디 -->
					<div class="modal-body">
					<c:if test="${param.error!=null}">
						<span class="text-danger">아이디 또는 비밀번호가 일치하지 않습니다.</span>
					</c:if>
						<label for="userId" class="mr-sm-2">ID : </label>
						<input type="text" class="form-controll mb-2 mr-sm-2" placeholder="Enter ID" id="userId" name="userId"> <br>
						<label for="userPwd" class="mr-sm-2">PWD : </label>
						<input type="password" class="form-controll mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd">
					</div>
					
					<!-- 모달 푸터 -->
					<div class="modal-footer">
						<div>
						<input type="checkbox" class="form-check-input" name="remember-me" id=remember-me"/>
						<label for="remember-me" class="form-check-label">로그인유지</label>
						</div>
						<button type="submit" class="btn btn-primary">로그인</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>