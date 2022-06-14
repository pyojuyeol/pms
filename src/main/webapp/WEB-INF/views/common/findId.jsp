<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<form>
					<div class="form-group">
						<p class="info">${message }</p>
					</div>
					<div class="form-group content-right">
						<a class="form-recovery" href="./loginForm.do?userId=${userId }">로그인</a>	
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two"></div>
	</div>
</div>
</body>