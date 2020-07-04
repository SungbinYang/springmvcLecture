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
		<h2>게시글 수정</h2>
		<form action="./app/board/s/updateBoard" method="post">
			<p>
				<input type="text" name="title" style="width: 100%;"
					value="${e:forHtml(board.title) }" required autofocus />
			</p>
			<p>
				<textarea name="content" style="width: 100%; height: 200px;">${e:forHtml(board.content) }</textarea>
			</p>
			<p>
				<button type="submit">저장</button>
				<a href="./app/board/boardInfo?seq=${param.seq }" class="button">취소</a>
			</p>
			<input type="hidden" name="seq" value="${board.seq }" />
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>