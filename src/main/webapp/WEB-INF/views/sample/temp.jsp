<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
<!-- 필요 리소스 추가 -->
</head>
<title>타이틀 설정</title>
<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>프로젝트이름</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath() %>/dashboard.do">Home</a></li>
					<li class="breadcrumb-item"><a href="#">${prj.prjNm }</a></li>
					<li class="breadcrumb-item"><a href="#">이슈</a></li>
					<li class="breadcrumb-item active">등록</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-body">전체 복사하여 여기에 원하는 모든것 추가</div>
	</div>
</section>

</body>