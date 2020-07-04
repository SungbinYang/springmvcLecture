<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${!empty param.returnUrl}">
		<c:set var="returnUrl" value="${param.returnUrl }" />
	</c:when>
	<c:otherwise>
		<c:set var="returnUrl" value="${header.referer }" />
	</c:otherwise>
</c:choose>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>로그인</h2>
		<form action="./app/login" method="post">
			<c:if test="${param.mode == 'FAILURE'}">
				<p style="color: red;">이메일이나 비밀번호가 틀립니다.</p>
			</c:if>
			<p>
				<input type="email" name="email" value="${param.email }" placeholder="이메일"
					required autofocus />
			</p>
			<p>
				<input type="password" name="password" placeholder="비밀번호" required />
			</p>
			<p>
				<input type="text" name="returnUrl" value="${returnUrl }"
					style="width: 100%;" readonly />
			</p>
			<p>
				<button type="submit">로그인</button>
			</p>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
