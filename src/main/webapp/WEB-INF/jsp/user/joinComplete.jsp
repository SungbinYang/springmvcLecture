<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>회원가입 완료</h2>
		<p class="tl">회원가입을 완료했습니다.</p>
		<p>이메일 : ${user.email }</p>
		<p class="bl">이름 : ${user.name }</p>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
