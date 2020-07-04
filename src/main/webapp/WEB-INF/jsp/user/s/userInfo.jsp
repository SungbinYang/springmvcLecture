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
<script type="text/javascript">
	window.onload = function() { // 브라우저가 페이지 로딩을 끝낸 후
		document.getElementById("btnDrop").onclick = function() {
			if (!confirm("정말 탈퇴하시겠습니까?"))
				return false; // 클릭이 진행이 안된다.
		}
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>개인정보</h2>
		<c:choose>
			<c:when test="${param.mode == 'UPDATE'}">
				<p class="msg">개인정보를 수정했습니다.</p>
			</c:when>
			<c:when test="${param.mode == 'PASSWORD'}">
				<p class="msg">비밀번호를 변경했습니다.</p>
			</c:when>
		</c:choose>
		<p class="tl">
			<label>회원번호</label> : ${sessionScope.user.id }
		</p>
		<p>
			<label>이메일</label> : ${sessionScope.user.email }
		</p>
		<p class="bl">
			<label>이름</label> : ${sessionScope.user.name }
		</p>
		<p>
			<a href="./app/user/s/userEdit" class="button">개인정보 수정</a><a
				href="./app/user/s/passwordEdit" class="button">비밀번호 수정</a><a id="btnDrop"
				href="./app/user/s/dropUser" class="button">회원탈퇴</a>
		</p>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
