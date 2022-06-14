<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login/login.css" />
</head>
<title>PROSPEC'S | 시스템 점검중</title>

<body>
<div class="center">
	<div class="form" style="width:700px;">
		<div class="form-toggle"></div>
		<div class="form-panel one" style="padding: 100px calc(5% + 60px) 100px 60px">
			<div class="form-content">
				<form>
					<div class="form-group">
						<img src="<%=request.getContextPath()%>/resources/images/common/system/notice.png" style="width:100%; margin:auto;"/>
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two"></div>
	</div>
</div>
</body>