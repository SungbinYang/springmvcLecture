<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<link rel="stylesheet" type="text/css" href="css/springmvc.css" />
<script type="text/javascript">
	window.onload = function() { // 브라우저가 페이지 로딩을 끝낸 후
		passwordForm.onsubmit = function() { // passwordForm을 submit 할때 실행
			var newPassword = passwordForm.newPassword.value;
			var newPasswordConfirm = passwordForm.newPasswordConfirm.value;
			if (newPassword != newPasswordConfirm) { // 새비밀번호가 서로 다를 경우
				alert("새 비밀번호가 다릅니다.");
				passwordForm.newPassword.value = "";
				passwordForm.newPasswordConfirm.value = "";
				passwordForm.newPassword.focus();
				return false; // submit을 진행하지 않고 멈춘다.
			}
		}
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="main">
		<h2>비밀번호 수정</h2>
		<c:if test="${param.mode == 'ERROR'}">
			<p class="warn">현재 비밀번호가 틀립니다.</p>
		</c:if>
		<form name="passwordForm" action="./app/user/s/updatePassword" method="post">
			<p>
				<input type="password" name="password" placeholder="현재 비밀번호" autofocus
					required />
			</p>
			<p>
				<input type="password" name="newPassword" placeholder="새 비밀번호" required />
			</p>
			<p>
				<input type="password" name="newPasswordConfirm" placeholder="새 비밀번호 확인"
					required />
			</p>
			<p>
				<button type="submit">수정</button>
				<a href="./app/user/s/userInfo" class="button">취소</a>
			</p>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>