<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>개인정보 수정</h2>
		<c:if test="${param.mode == 'ERROR'}">
			<p class="warn">이메일 중복입니다.</p>
		</c:if>
		<p class="tl">회원번호 : ${sessionScope.user.id }</p>
		<form action="./app/user/s/updateUser" method="post">
			<p>
				이메일 : <input type="text" name="email" value="${user.email }" autofocus
					required />
			</p>
			<p class="bl">
				이름 : <input type="text" name="name" value="${user.name }" required />
			</p>
			<p>
				<button type="submit">수정</button>
				<a href="./app/user/s/userInfo" class="button">취소</a>
			</p>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>