<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login/login.css" />
</head>
<title>PROSPEC'S | 비밀번호 찾기</title>

<body>
<!-- loading -->
<div id="loader" class="loader">
	<div id="container">
        <div class="divider" aria-hidden="true"></div>
        <p class="loading-text" aria-label="Loading">
          <span class="letter" aria-hidden="true">L</span>
          <span class="letter" aria-hidden="true">o</span>
          <span class="letter" aria-hidden="true">a</span>
          <span class="letter" aria-hidden="true">d</span>
          <span class="letter" aria-hidden="true">i</span>
          <span class="letter" aria-hidden="true">n</span>
          <span class="letter" aria-hidden="true">g</span>
        </p>
    </div>
</div>
<div class="center">
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1><img src="<%=request.getContextPath()%>/resources/images/common/favicon_b.svg" style="width:30px;margin-right:5px;"/>Prospec's</h1>
			</div>
			<div class="form-content">
				<form action="./findPwd.do" id="form" method="post">
					<div class="form-group">
						<label for="id">사원번호</label> <input type="text"
							id="id" name="id" placeholder="사원번호를 입력하세요." value="${param.userId }" required="required">
					</div>
					<div class="form-group">
						<label for="email">이메일</label> 
						<input type="text" id="email" name="email" placeholder="사원정보와 동일한 이메일을 입력해주세요." required="required">
					</div>
					<div class="form-group">
						<a class="form-recovery" href="./loginForm.do">Log in</a>
						<a class="form-recovery" href="./forgotId.do">Forgot id?</a>
					</div>
					<div class="form-group">
						<button type="button" onclick="sendForm()">비밀번호 찾기</button>
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two"></div>
	</div>
</div>
<script type="text/javascript">
function sendForm(){
	//로딩 애니매이션 출력
	$("#loader").addClass("loading");
	$("#form").submit();
}

</script>



</body>