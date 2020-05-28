<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h2>로그인</h2>
	<form action="./app/login" method="post">
		<c:if test="${param.mode == 'ERROR'}">
			<p style="color: red;">이메일이나 비밀번호가 틀립니다.</p>
		</c:if>
		<p>
			<input type="email" name="email" value="${email }" placeholder="이메일"
				required autofocus />
		</p>
		<p>
			<input type="password" name="password" placeholder="비밀번호" required />
		</p>
		<p>
			<button type="submit">로그인</button>
		</p>
	</form>
</body>
</html>