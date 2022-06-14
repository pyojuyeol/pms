<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>

<style></style>

</head>
<title>부서 생성</title>
<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
<!-- 				<h1>프로젝트이름</h1> -->
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item"><a href="#">조직도</a></li>
					<li class="breadcrumb-item active">생성</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content --> 
<section class="content">
	<div class="card card-outline card-info">
	
		<div class="card-header">
			<h2 class="card-title p-2"></h2>
			<div class ="card-tools">
				<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">생 성</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
		  <c:set var="URL" value="${pageContext.request.requestURL}" />
		  <c:set var="isOpen" value="${fn:indexOf(URL,'.open') lt 0}" />
		  <c:if test="${!isOpen }">
			<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'">취 소</button>
		  </c:if>
		  <c:if test="${isOpen }">
			<button type="button" class="btn btn-warning" id="cancelBtn" onclick="window.close()">닫 기</button>
	  	  </c:if>
			</div>
		</div><!--end card-header  -->
		
		<div class="card-body pad">
			<form role="form" method="post" action="regist.do" name="registForm">
<%-- 				<input type="hidden" id="register" name="register" class="form-control" value="${loginUser.userId }"> --%>
		  	  <c:if test="${isOpen }">
				<input type="hidden" id="open" name="open" class="form-control" value="1">
			  </c:if>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="deptNm">이름</label>
						<input type="text" id="deptNm" name="deptNm" class="form-control" placeholder="부서명을 쓰세요">
					</div>
				</div>
			</form>
		</div> <!-- card-body End -->
		
	</div> <!-- card End -->
</section>
<script>
window.addEventListener('DOMContentLoaded', onloadDeptRegist);
function onloadDeptRegist() {
	<c:if test="${from eq 'regist' }">
		alertA("정상 등록되었습니다.","success",alertCollback);
	</c:if>
}
</script>
<script>
function alertCollback() {
	window.close();
	opener.loadOrg();
}
</script>

<script>
function regist_go() {
// 	alert("regist btn click");
	
	if ($("input[name='deptNm']").val() == "") {  // form.title.value
		alertW("부서명은 필수입니다.");
		$("input[name='deptNm']").focus();
		return;
	}
	var deptName = $("input[name='deptNm']").val();
	var isAvailable = checkDeptName(deptName);
	if(isAvailable) {
		$("form[role='form']").submit();
	}
}
</script>
<script>
function checkDeptName(deptName) {
	var isPass = false;
	$.ajax({
	    type : 'GET', // 타입 (get, post, put 등등)
	    url : "check.do?name=" + deptName, // 요청할 서버url
	    async : false, // 비동기화 여부 (default : true)
// 	    dataType : 'text', // 데이터 타입 (html, xml, json, text 등등)
	    success : function(result) { // 결과 성공 콜백함수
	        console.log(result);
	        isPass = true;
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	        console.log(request);
	        alertW(request.responseText);
	    }
	});
	return isPass;
}
</script>
</body>