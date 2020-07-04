<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p>
	<a href="./">홈</a> | <a href="./app/user/userList">회원</a> | <a
		href="./app/board/boardList">게시판</a> |
	<c:if test="${!empty sessionScope.user }">
		<a href="./app/user/s/userInfo">${sessionScope.user.name }</a> | <a
			href="./app/s/logout">로그아웃</a>
	</c:if>
	<c:if test="${empty sessionScope.user }">
		<a href="./app/user/joinForm">회원가입</a> | <a href="./app/user/loginForm">로그인</a>
	</c:if>
</p>