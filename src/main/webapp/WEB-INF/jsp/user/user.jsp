<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty sessionScope.user }">
	<p>
		<a href="./app/user/s/userInfo">${sessionScope.user.name }</a> | <a
			href="./app/s/logout">로그아웃</a>
	</p>
</c:if>
<c:if test="${empty sessionScope.user }">
	<p>
		<a href="./app/user/joinForm">회원가입</a> | <a href="./app/user/loginForm">로그인</a>
	</p>
</c:if>