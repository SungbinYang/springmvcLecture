<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h2>회원가입 완료</h2>
	<p>회원가입을 완료했습니다.</p>
	<p>이메일 : ${user.email }</p>
	<p>이름 : ${user.name }</p>
	<p>
		<a href="./">홈으로</a> <a href="./user/loginForm">로그인</a> <a
			href="./app/user/userList">회원목록</a>
	</p>
</body>
</html>