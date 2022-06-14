<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
					<li class="breadcrumb-item"><a href="#">사원관리</a></li>
					<li class="breadcrumb-item active">상세</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-header">

			<div class="float-right">
				<div>
					<button type="button" class="btn btn-primary mr-1"
						onclick="location.href='modifyForm.do?id=${user.userId}'">수정</button>
					<button type="button" class="btn btn-outline-secondary" id="cancelBtn"
						onclick="location.href='list.do'">목록</button>
				</div>

			</div>
		</div>
		<div class="card-body">

			<div class="row" style="height: 200px;">
				<div class="mailbox-attachments clearfix col-md-12"
					style="text-align: center;">
					<%-- 							<img src="<%=request.getContextPath() %>/resources/suzy.jpg" style="border: 1px solid green; height: 200px; width: 140px; m-1: 0 auto;">														 --%>
					<i class="fas fa-user fa-10x mt-2"></i>
				</div>
			</div>
			<br />
			<div class="form-group row">
				<label for="userId" class="col-sm-2 control-label text-right">사원번호</label>
				<div class="col-sm-4">
					<input name="id" type="text" class="form-control" id="userId"
						value="${user.userId}" readonly>
				</div>
			</div>

			<div class="form-group row">
				<label for="userNm"
					class="col-sm-2 control-label text-right">이 름</label>
				<div class="col-sm-4">
					<input name="pwd" type="text" class="form-control"
						id="userNm" value="${user.userNm}" readonly>
				</div>
<!-- 				<label for="hp" -->
<!-- 					class="col-sm-2 control-label text-right">전화번호</label> -->
<!-- 				<div class="col-sm-4"> -->
<!-- 					<input name="hp" type="text" class="form-control" -->
<%-- 						id="hp" value="${fn:replace(user.hp, '--', '-')}" readonly> --%>
<!-- 				</div> -->
				<label for="genderCode"
					class="col-sm-2 control-label text-right">성별</label>
				<div class="col-sm-4">
					<c:forEach var="gender" items="${genders}">
					  <c:if test="${gender.code eq user.genderCode }">
						<input name="genderCode" type="text" class="form-control"
							id="genderCode" value="${gender.nm}" readonly>
					  </c:if>
					</c:forEach>
				</div>
			</div>

			<div class="form-group row">
				<label for="email"
					class="col-sm-2 control-label text-right">이메일</label>
				<div class="col-sm-4">
					<input name="email" type="email" class="form-control"
						id="email" value="${user.email}" readonly>
				</div>
<!-- 				<label for="addr" -->
<!-- 					class="col-sm-2 control-label text-right">주소</label> -->
<!-- 				<div class="col-sm-4"> -->
<!-- 					<input name="addr" type="text" class="form-control" -->
<%-- 						id="addr" value="${user.addr}" readonly> --%>
<!-- 				</div> -->
			</div>

			<div class="form-group row">
<!-- 				<label for="birth" -->
<!-- 					class="col-sm-2 control-label text-right">생년월일</label> -->
<!-- 				<div class="col-sm-4"> -->
<%-- 						<c:set var="birth" value="${user.birth }"/> --%>
<%-- 					<c:if test="${user.birth ne '-' }"> --%>
<%-- 						<c:set var="birth" value="${fn:substring(user.birth,0,4) }-${fn:substring(user.birth,4,6) }-${fn:substring(user.birth,6,9) }"/> --%>
<%-- 					</c:if> --%>
<!-- 					<input name="birth" type="text" class="form-control" -->
<%-- 						id="birth" value="${birth }" readonly> --%>
<!-- 				</div> -->
				<label for="positionCode"
					class="col-sm-2 control-label text-right">직급</label>
				<div class="col-sm-4">
					<c:forEach var="position" items="${positions}">
					  <c:if test="${position.code eq user.positionCode }">
						<input name="positionCode" type="text" class="form-control"
							id="positionCode" value="${position.nm }" readonly>
					  </c:if>
					</c:forEach>
				</div>
				<label for="deptCode"
					class="col-sm-2 control-label text-right">부서</label>
				<div class="col-sm-4">
					<c:forEach var="dept" items="${depts}">
					  <c:if test="${dept.code eq user.deptCode }">
						<input name="deptCode" type="text" class="form-control"
							id="deptCode" value="${dept.nm}" readonly>
					  </c:if>
					</c:forEach>
				</div>
			</div>

			<div class="form-group row">
				<label for="authGrpCode"
					class="col-sm-2 control-label text-right">권한</label>
				<div class="col-sm-4">
					<input name="authGrpCode" type="text" class="form-control"
						id="authGrpCode" value="${user.authGrp}" readonly>
				</div>
				<label for="userSttCode"
					class="col-sm-2 control-label text-right">상태</label>
				<div class="col-sm-4">
					<c:forEach var="stt" items="${stts}">
					  <c:if test="${stt.code eq user.userSttCode }">
						<input name="userSttCode" type="text" class="form-control"
							id="userSttCode" value="${stt.nm}" readonly>
					  </c:if>
					</c:forEach>
				</div>
			</div>

			<div class="form-group row">
				<label for="regdate"
					class="col-sm-2 control-label text-right">등록일</label>
				<div class="col-sm-4">
					<fmt:formatDate var="regDate" value="${user.regdate }" pattern="yyyy-MM-dd"/>
					<input name="regdate" type="text" class="form-control"
						id="addr" value="${regDate}" readonly>
				</div>
			</div>
		</div>
	</div>
	
</section>
<script>
window.onload = function(){
	 <c:if test="${from eq 'modify' }">
			alertN("정상 처리되었습니다.");
	 </c:if>
	  }
</script>

</body>
<jsp:include page="../../include/footer.jsp"></jsp:include>