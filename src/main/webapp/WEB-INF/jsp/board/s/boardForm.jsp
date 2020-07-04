<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>게시글 쓰기</h2>
		<form action="./app/board/s/addBoard" method="post">
			<p>
				<input type="text" name="title" placeholder="제목" style="width: 100%;"
					required autofocus />
			</p>
			<p>
				<textarea name="content" placeholder="내용"
					style="width: 100%; height: 200px;"></textarea>
			</p>
			<p>
				<button type="submit">저장</button>
				<a href="./app/board/boardList" class="button">취소</a>
			</p>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>