<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project"
	prefix="e"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>게시글 목록</h2>
		<form action="" method="get">
			<p>
				<label>갯수</label> <input type="number" name="count" value="${search.count }"
					min="10" step="5" style="width: 50px;" /> <label>페이지</label><input
					type="number" name="page" value="${search.page }" min="1"
					style="width: 50px;" /> <input type="search" name="keyword"
					value="${search.keyword }" placeholder="검색어" style="width: 150px;"
					autofocus />
				<button type="submit">검색</button>
				<a href="./app/board/s/boardForm" class="button">글쓰기</a>
			</p>
		</form>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일시</th>
					<th>글쓴이</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList }">
					<tr>
						<td>${board.seq }</td>
						<td><a href="./app/board/boardInfo?seq=${board.seq }">${e:forHtml(board.title) }</a></td>
						<td>${board.regdate }</td>
						<td><a href="./app/user/userInfo?id=${board.id}">${board.writer }</a></td>
						<td style="text-align: right;">${board.cnt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>