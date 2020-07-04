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
		<h2>회원목록</h2>
		<form action="./app/user/userList" method="get">
			<p>
				<label>갯수</label><input type="number" name="count" value="${param.count }"
					min="10" step="5" style="width: 50px;" /> <label>페이지</label><input
					type="number" name="page" value="${param.page }" min="1"
					style="width: 50px;" />
				<button type="submit">조회</button>
			</p>
		</form>
		<table>
			<tr>
				<th>회원번호</th>
				<th>이메일</th>
				<th>이름</th>
			</tr>
			<c:forEach var="user" items="${userList }">
				<tr>
					<td>${user.id }</td>
					<td>${user.email }</td>
					<td>${user.name }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
