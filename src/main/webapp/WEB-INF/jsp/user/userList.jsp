<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/user/user.jsp"%>
	<h2>회원목록</h2>
	<form action="./app/user/userList" method="get">
		갯수: <input type="text" name="count" value="${param.count }"
			style="width: 50px;" /> 페이지: <input type="text" name="page"
			value="${param.page }" style="width: 50px;" />
		<button type="submit">검색</button>
	</form>
	<ul>
		<c:forEach var="user" items="${userList }">
			<li><span>${user.id }</span> <span>${user.email }</span> <span>${user.name }</span></li>
		</c:forEach>
	</ul>
</body>
</html>