<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h2>회원가입</h2>
	<form action="./app/user/join" method="post">
		<c:if test="${param.mode == 'ERROR'}">
			<p style="color: red;">이메일 중복입니다.</p>
		</c:if>
		<p>
			<input type="email" name="email" value="${user.email }" placeholder="이메일"
				required autofocus />
		</p>
		<p>
			<input type="password" name="password" placeholder="비밀번호" required />
		</p>
		<p>
			<input type="text" name="name" value="${user.name }" placeholder="이름"
				required />
		</p>
		<p>
			<button type="submit">가입</button>
		</p>
	</form>
</body>
</html>