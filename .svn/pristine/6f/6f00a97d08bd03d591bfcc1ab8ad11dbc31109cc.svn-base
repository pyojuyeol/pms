<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login/login.css" />
</head>
<title>PROSPEC'S | 사원번호 찾기</title>

<body>
<div class="center">
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1><img src="<%=request.getContextPath()%>/resources/images/common/favicon_b.svg" style="width:30px;margin-right:5px;"/>Prospec's</h1>
			</div>
			<div class="form-content">
				<form action="./findId.do" method="post">
					<div class="form-group">
						<label for="email">이메일</label> 
						<input type="text" id="email" name="email" placeholder="사원정보와 동일한 이메일을 입력해주세요." required="required">
					</div>
					<div class="form-group">
						<button type="submit">사원번호 찾기</button>
					</div>
					
					<div class="form-group">
						<a class="form-recovery" href="./loginForm.do">Log in</a>
						<a class="form-recovery" href="./forgotPwd.do">Forgot Password?</a>
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two"></div>
	</div>
</div>
</body>