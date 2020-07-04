<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
<style>
label {
	display: inline-block;
	width: 100px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>회원정보</h2>
		<p class="tl">
			<label>회원번호</label> : ${user.id }
		</p>
		<p>
			<label>이메일</label> : ${user.email }
		</p>
		<p class="bl">
			<label>이름</label> : ${user.name }
		</p>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>