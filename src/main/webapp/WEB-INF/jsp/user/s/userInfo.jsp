<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h2>개인정보</h2>
	<c:if test="${param.mode == 'LOGIN'}">
		<p style="color: blue;">로그인을 했습니다.</p>
	</c:if>
	<p>회원번호 : ${sessionScope.user.id }</p>
	<p>이메일 : ${sessionScope.user.email }</p>
	<p>이름 : ${sessionScope.user.name }</p>
	<p>
		<a href="./">홈으로</a> <a href="./app/user/userList">회원목록</a> <a
			href="./app/s/logout">로그아웃</a>
	</p>
</body>
</html>