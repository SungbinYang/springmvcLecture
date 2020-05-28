<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<style>
h1 {
	color: yellow;
}

.blurText {
	text-shadow: 3px 3px 5px blue;
}

hr {
	border: 5px solid gray;
}

table {
	border-collapse: collapse;
	width: 100%
}

td, th {
	text-align: center;
	padding: 5px;
	height: 15px;
	width: 100px;
}

thead {
	background: skyblue;
}

tbody {
	background: lightgray;
}

tbody tr:hover {
	background: yellow;
}

input[type=button] {
	color: white;
}
</style>
</head>
<body>
	<h1 class="blurText">회원목록</h1>
	<form action="./app/board/boardList" method="get">
		갯수: <input type="text" name="count" value="${param.count }"
			style="width: 50px;" /> 페이지: <input type="text" name="page"
			value="${param.page }" style="width: 50px;" />
		<button type="submit">검색</button>
	</form>
	<hr>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<c:forEach var="board" items="${boardList }">
			<tbody>
				<tr>
					<td>${board.seq }</td>
					<td>${board.title }</td>
					<td>${board.writer }</td>
					<td>${board.cnt }</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>