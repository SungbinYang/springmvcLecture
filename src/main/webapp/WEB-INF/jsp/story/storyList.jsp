<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib
	uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project"
	prefix="e"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" href="css/springmvc.css">
</head>
<body>
	<div class="main">
		<form action="./app/story/addStory" method="post">
			<p>
				<textarea  name="content" placeholder="스토리"
					style="width: 100%; height: 200px;" required autofocus ></textarea>
			</p>
			<p>
				<input type="text" name="name" placeholder="이름" style="width: 100%;"
					required />
			</p>
			<p>
				<button type="submit">저장</button>
				<a href="./app/story/storyList" class="button">취소</a>
			</p>
		</form>
	</div>
	<c:forEach var="story" items="${storyList }">
	<div class="main">
		<p class="tl">
			<span>${story.name }</span> | <span style="font-weight: bold;">${e:forHtml(story.date) }</span>
		</p>
		<hr>
		<p class="tl bl">${story.contentHtml }</p>
	</div>
	</c:forEach>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	
</body>
</html>