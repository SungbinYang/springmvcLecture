<%@ page isErrorPage="true"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h1>에러 페이지</h1>
	<p><%=exception.getMessage()%></p>
	<p>
		<a href="./">홈으로</a>
	</p>
</body>
</html>