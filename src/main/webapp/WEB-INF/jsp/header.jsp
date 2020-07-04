<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="header">
		<h1>Spring MVC Board Application</h1>
	</div>
	<ul class="menu">
		<li><a href="./">홈</a></li>
		<li><a href="./app/user/userList">회원</a></li>
		<li><a href="./app/board/boardList">게시판</a></li>
		<c:if test="${!empty sessionScope.user }">
			<li><a href="./app/user/s/userInfo">${sessionScope.user.name }</a></li>
			<li><a href="./app/s/logout">로그아웃</a></li>
		</c:if>
		<c:if test="${empty sessionScope.user }">
			<li><a href="./app/user/joinForm">회원가입</a></li>
			<li><a href="./app/user/loginForm">로그인</a></li>
		</c:if>
	</ul>
</header>