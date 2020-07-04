<%@ taglib uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project"
	prefix="e"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
<script type="text/javascript">
	window.onload = function() { // 브라우저가 페이지 로딩을 끝낸 후
		document.getElementById("btnDelete").onclick = function() {
			if (!confirm("정말 삭제하시겠습니까?"))
				return false;
		}
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>게시글 보기</h2>
		<p class="tl">
			<span>${board.seq }</span>. <span style="font-weight: bold;">${e:forHtml(board.title) }</span>
		</p>
		<p>
			<span>${board.regdate }</span> | <span><a
				href="./app/user/userInfo?id=${board.id}">${board.writer }</a></span> | <span>조회수:
				${board.cnt }</span>
		</p>
		<p class="tl bl">${board.contentHtml }</p>
		<p>
			<a href="./app/board/boardList" class="button">목록</a><a
				href="./app/board/s/boardEdit?seq=${param.seq }" class="button">편집</a><a
				id="btnDelete" href="./app/board/s/deleteBoard?seq=${param.seq }"
				class="button">삭제</a>
		</p>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>