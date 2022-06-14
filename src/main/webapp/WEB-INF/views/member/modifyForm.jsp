<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
					<li class="breadcrumb-item active">목록</li>
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
						onclick="modify_go()">저장</button>
					<button type="button" class="btn btn-outline-secondary" id="cancelBtn"
						onclick="location.href='list.do'">목록</button>
				</div>

			</div>
		</div>
		<!-- /.card-header -->
		<div class="register-card-body">
			<form role="form" class="form-horizontal mx-auto d-block"
				id="userMForm" action="modify.do" method="post">
<!-- 				<input type="hidden" name="picture" /> -->
				<div class="form-group row">
					<label for="userId" class="col-sm-1">사원 번호</label>
					<div class="col-sm-4 input-group">
						<input name="userId"
							type="text" class="form-control" id="userId"
							readonly value="${user.userId}"/>
					</div>
					
				</div>

				<div class="form-group row ml-6">
				<label for="userNm" class="col-sm-1">이 름</label>
					<div class="col-sm-4">
						<input class="form-control" name="userNm" type="text"
							class="form-control" id="userNm" placeholder="이름을 입력하세요"
							onkeyup="this.value=this.value.trim();"
							value="${user.userNm}"/>
					</div>
<!-- 					<label for="addr" class="col-sm-1">주 소</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<input class="form-control" name="addr" type="text" -->
<!-- 							class="form-control" id="addr" placeholder="주소를 입력하세요" -->
<!-- 							onkeyup="this.value=this.value.trim();"  -->
<%-- 							value="${user.addr}" /> --%>
<!-- 					</div> -->
					<label for="email" class="col-sm-1 ml-2">이메일</label>
					<div class="col-sm-4">
						<input name="email" type="email" class="form-control" id="email"
							placeholder="example@naver.com" 
							value="${user.email}">
					</div>
				</div>

<!-- 				<div class="form-group row ml-6"> -->
<!-- 					<label for="hp" class="control-label col-sm-1">전화번호</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<div class="input-group-sm"> -->
<!-- 							<select style="width: 75px;" name="hp" id="hp" -->
<!-- 								class="form-control float-left"> -->
<!-- 								<option value="">-선택-</option> -->
<%-- 								<option value="010" ${fn:split(user.hp, '-')[0] eq '010' ? 'selected':''}>010</option> --%>
<%-- 								<option value="011" ${fn:split(user.hp, '-')[0] eq '011' ? 'selected':''}>011</option> --%>
<%-- 								<option value="017" ${fn:split(user.hp, '-')[0] eq '017' ? 'selected':''}>017</option> --%>
<%-- 								<option value="018" ${fn:split(user.hp, '-')[0] eq '018' ? 'selected':''}>018</option> --%>
<!-- 							</select> <label class="float-left" -->
<!-- 								style="padding: 0; text-align: center;">&nbsp;&nbsp;-&nbsp;&nbsp;</label> -->
<!-- 							<input style="width: 68px;" name="hp" type="text" -->
<!-- 								class="form-control float-left" -->
<%-- 								value="${fn:split(user.hp, '-')[1]}" --%>
<!-- 								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/> <label class="float-left" -->
<!-- 								style="padding: 0; text-align: center;">&nbsp;&nbsp;-&nbsp;&nbsp;</label> -->
<!-- 							<input style="width: 68px;" name="hp" type="text" -->
<!-- 								class="form-control float-left"  -->
<%-- 								value="${fn:split(user.hp, '-')[2]}" --%>
<!-- 								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<label for="birth" class="col-sm-1 ml-2">생년월일</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<input class="form-control" name="birth" type="text" -->
<!-- 							class="form-control" id="birth" placeholder="생년월일을 입력하세요" -->
<!-- 							onkeyup="this.value=this.value.trim();"  -->
<!-- 							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" -->
<%-- 							value="${fn:replace(user.birth, '-', '')}"/> --%>
<!-- 					</div> -->
<!-- 				</div> -->

				<div class="form-group row ml-6">
					<label for="genderCode" class="col-sm-1">성 별</label>
					<div class="col-sm-4">
						<select name="genderCode" class="form-control">
							<c:forEach var="gender" items="${genders}">
							<option value="${gender.code }" ${gender.code eq user.genderCode ? 'selected' : ''}>${gender.nm }</option>
							</c:forEach>
						</select>
					</div>
					<label for="positionCode" class="col-sm-1 ml-2">직 급</label>
					<div class="col-sm-4">
						<select name="positionCode" class="form-control">
							<c:forEach var="position" items="${positions}">
							<option value="${position.code }" ${position.code eq user.positionCode ? 'selected' : ''}>${position.nm }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row ml-6">
					<label for="authGrpCode" class="col-sm-1">권 한</label>
					<div class="col-sm-4">
						<select name="authGrpCode" class="form-control">
							<option value="ROLE_ADMIN" ${'ROLE_ADMIN' eq user.authGrpCode ? 'selected' : ''}>관리자</option>
							<option value="ROLE_MANAGER" ${'ROLE_MANAGER' eq user.authGrpCode ? 'selected' : ''}>매니저</option>
							<option value="ROLE_USER" ${'ROLE_USER' eq user.authGrpCode ? 'selected' : ''}>일반 사용자</option>
						</select>
					</div>
					<label for="deptCode" class="col-sm-1 ml-2">부 서</label>
					<div class="col-sm-4">
						<select name="deptCode" class="form-control">
							<c:forEach var="dept" items="${depts}">
							<option value="${dept.code }" ${dept.code eq user.deptCode ? 'selected' : ''}>${dept.nm }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row ml-6">
					<label for="userSttCode" class="col-sm-1">상 태</label>
					<div class="col-sm-4">
						<select name="userSttCode" class="form-control">
							<c:forEach var="stt" items="${stts}">
							<c:if test="${stt.code ne '-1'}">
							<option value="${stt.code }" ${stt.code eq user.userSttCode ? 'selected' : ''}>${stt.nm }</option>
							</c:if>
							</c:forEach>
						</select>
					</div>
				</div>


			</form>
		</div>
		<!-- register-card-body -->
	</div>
</section>
<script>
	function modify_go(){
//			   if(!$('input[name="pwd"]').val()){
//				      alertI("패스워드는 필수입니다.");
//				      return;
//			   }
			   if(!$('input[name="userNm"]').val()){
			      alertI("이름은 필수입니다.");
			      return;
			   }
			   if(!$('input[name="email"]').val()){
			      alertI("이메일은 필수입니다.");
			      return;
			   }
			   var emailRegExp = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
			   if (!emailRegExp.test($('input[name="email"]').val())) {
				      alertI("유효한 이메일 형식이 아닙니다.");
				      return;
		       }
			   if($('select[name="genderCode"]').val() == '-1'){
				      alertI("성별을 선택해 주세요.");
				      return;
				   }
				   if($('select[name="positionCode"]').val() == '-1'){
				      alertI("직급을 선택해 주세요.");
				      return;
				   }
				   if($('select[name="authGrpCode"]').val() == '-1'){
				      alertI("권한을 선택해 주세요.");
				      return;
				   }
				   if($('select[name="deptCode"]').val() == '-1'){
				      alertI("부서을 선택해 주세요.");
				      return;
				}
// 			   var hpRegExp = /^[0-9]{4}$/
// 			   if( $('input[name="hp"]' ).val() || $('select[name="hp"]' ).val() ){
// // 				   alert($('select[name="hp"]').val());
// // 				   alert($('input[name="hp"]').val());
// 				   if (!hpRegExp.test($('input[name="hp"]').val())) {
// 				      alertI("유효한 전화번호 형식이 아닙니다.");
// 				      return;
// 				   }
// 			   }
// 			   var birthRegExp = /^[0-9]{8}$/; 
// 			   if($('input[name="birth"]').val()){
// 				   if (!birthRegExp.test($('input[name="birth"]').val())) {
// 				      alertI("생년월일은 8자리 숫자만 입력가능 합니다.");
// 				      return;
// 				   }
// 			   }
		
		let form = document.querySelector("#userMForm");
		form.submit();
	}
</script>
</body>