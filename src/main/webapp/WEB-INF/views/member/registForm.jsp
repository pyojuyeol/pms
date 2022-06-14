<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
					<li class="breadcrumb-item active">수정</li>
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
						onclick="regist_go()">저장</button>
					<button type="button" class="btn btn-danger" id="cancelBtn"
						onclick="location.href='list.do'">취소</button>
				</div>

			</div>
		</div>
		<!-- /.card-header -->
		<div class="register-card-body">
			<form role="form" class="form-horizontal mx-auto d-block"
				action="regist.do" method="post" id="userRForm">
<!-- 				<input type="hidden" name="picture" /> -->
<!-- 				<div class="form-group row ml-6"> -->
<!-- 						<label for="userId" class="col-sm-1">사원번호<span class="text-danger">*</span></label> -->
<!-- 						<div class="col-sm-4"> -->
<!-- 							<input name="userId" -->
<!-- 								onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);" -->
<!-- 								type="text" class="form-control" id="userId" onchange="setIdCheckStr()" -->
<!-- 								placeholder="사원번호를 입력하세요" required/> -->
<!-- 						</div> -->
<!-- 				<div class="col-sm-4"> -->
<!-- 					<span class="input-group-append-sm">	 -->
<!-- 							<button type="button" onclick="idCheck_go();"  class="btn btn-info btn-sm btn-append">중복확인</button> -->
<!-- 							<span id=idCheck class="text-danger ml-2">중복체크를 해주세요.</span> -->
<!-- 					</span> -->
<!-- 				</div>	 -->
<!-- 				</div> -->
				<div class="form-group row ml-6">
					<label for="userNm" class="col-sm-1">이 름<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<input class="form-control" name="userNm" type="text"
							class="form-control" id="userNm" placeholder="이름을 입력하세요"
							onkeyup="this.value=this.value.trim();"/>
					</div>
										<label for="email" class="col-sm-1 ml-2">이메일<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<input name="email" type="email" class="form-control" id="email"
							placeholder="example@naver.com" required>
					</div>
				</div>



<!-- 				<div class="form-group row ml-6"> -->
<!-- 					<label for="hp" class="control-label col-sm-1">전화번호</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<div class="input-group-sm"> -->
<!-- 							<select style="width: 75px;" name="hp" id="hp" -->
<!-- 								class="form-control float-left"> -->
<!-- 								<option value="">-선택-</option> -->
<!-- 								<option value="010">010</option> -->
<!-- 								<option value="011">011</option> -->
<!-- 								<option value="017">017</option> -->
<!-- 								<option value="018">018</option> -->
<!-- 							</select> <label class="float-left" -->
<!-- 								style="padding: 0; text-align: center;">&nbsp;&nbsp;-&nbsp;&nbsp;</label> -->
<!-- 							<input style="width: 68px;" name="hp" type="text" -->
<!-- 								class="form-control float-left"  -->
<!-- 								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" -->
<!-- 								required/> <label class="float-left" -->
<!-- 								style="padding: 0; text-align: center;">&nbsp;&nbsp;-&nbsp;&nbsp;</label> -->
<!-- 							<input style="width: 68px;" name="hp" type="text" -->
<!-- 								class="form-control float-left"  -->
<!-- 								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<label for="birth" class="col-sm-1 ml-2">생년월일</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<input class="form-control" name="birth" type="text" -->
<!-- 							class="form-control" id="birth" placeholder="생년월일을 입력하세요(숫자8자리)" -->
<!-- 							onkeyup="this.value=this.value.trim();" -->
<!-- 							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" -->
<!-- 							required/> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
<!-- 								<div class="form-group row ml-6"> -->
<!-- 					<label for="addr" class="col-sm-1">주 소</label> -->
<!-- 					<div class="col-sm-4"> -->
<!-- 						<input class="form-control" name="addr" type="text" -->
<!-- 							class="form-control" id="addr" placeholder="주소를 입력하세요" -->
<!-- 							onkeyup="this.value=this.value.trim();"/> -->
<!-- 					</div> -->
<!-- 				</div> -->

				<div class="form-group row ml-6">
					<label for="genderCode" class="col-sm-1">성 별<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<select name="genderCode" class="form-control">
							<c:forEach var="gender" items="${genders}">
							<option value="${gender.code }">${gender.nm }</option>
							</c:forEach>
						</select>
					</div>
					<label for="positionCode" class="col-sm-1 ml-2">직 급<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<select name="positionCode" class="form-control">
							<c:forEach var="position" items="${positions}">
							<option value="${position.code }">${position.nm }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row ml-6">
					<label for="authGrpCode" class="col-sm-1">권 한<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<select name="authGrpCode" class="form-control">
							<option value="ROLE_ADMIN">관리자</option>
							<option value="ROLE_MANAGER">매니저</option>
							<option value="ROLE_USER" selected>일반 사용자</option>
						</select>
					</div>
					<label for="deptCode" class="col-sm-1 ml-2">부 서<span class="text-danger">*</span></label>
					<div class="col-sm-4">
						<select name="deptCode" class="form-control">
							<c:forEach var="dept" items="${depts}">
							<option value="${dept.code }">${dept.nm }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group row ml-6" style="display:none">
					<label for="userSttCode" class="col-sm-1">상 태</label>
					<div class="col-sm-4">
						<select name="userSttCode" class="form-control">
							<c:forEach var="stt" items="${stts}">
							<option value="${stt.code }">${stt.nm }</option>
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
	function regist_go(){
// 		   if(!$('input[name="userId"]').val()){
// 			      alertI("사원번호는 필수입니다.");
// 			       return;
// 			   }
			   
// 			   if($('input[name="userId"]').val()!=checkedID){
// 			      alertI("사원번호는 중복 확인이 필요합니다.");
// 			      return;
// 			   }
			   
// 			   if(!$('input[name="pwd"]').val()){
// 				      alertI("패스워드는 필수입니다.");
// 				      return;
// 			   }
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
			      alertI("부서를 선택해 주세요.");
			      return;
			   }
// 			   var hpRegExp = /^[0-9]{4}$/
// 			   if( $('input[name="hp"]' ).val() || $('select[name="hp"]' ).val() ){
// 				   alert($('select[name="hp"]').val());
// 				   alert($('input[name="hp"]').val());
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
			   
		
		let form = document.querySelector("#userRForm");
		ShowLoading();
		form.submit();
	}
	
	var checkedID ="";
	function idCheck_go(){
		//alert("id check btn click");
		
		var input_ID=$('input[name="userId"]');
		
		if(!input_ID.val()){
	       alertI("사원번호를 입력하시오");
	       input_ID.focus();
	       return;
		}
		
		 $.ajax({
			 url : "idCheck.do?userId="+input_ID.val().trim(),
	    	 method : "get",	
	    	 success : function(result){
	   		   if(result.toUpperCase() == "DUPLICATED"){
	              alert("중복된 사원번호 입니다.");
	              $('input[name="userId"]').focus();
	              $('#idCheck').text("중복된 아이디 입니다.");	
	              $('#idCheck').attr("class","text-danger ml-2");	
	           }else{
	              alert("사용가능한 사원번호 입니다.");
	              checkedID=input_ID.val().trim();
	              $('input[name="userId"]').val(input_ID.val().trim());
	              setIdCheckStr();
	           } 
	    	 },
	         error:function(error){
	           alert("시스템장애로 가입이 불가합니다.");
	         }			 
		 });
	}
	function setIdCheckStr() {
		if($('input[name="userId"]').val()!=checkedID){
			$('#idCheck').text("중복체크를 해주세요.");	
	        $('#idCheck').attr("class","text-danger ml-2");
		} else {
			$('#idCheck').text("사용가능");	
            $('#idCheck').attr("class","text-success ml-2");
		}
	}
</script>
<script>
window.onload = function(){
	 HideLoading();
	 <c:if test="${from eq 'regist' }">
			alertE("등록에 실패했습니다.");
	 </c:if>
	  }
</script>
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}

{{/each}}
</script>
</body>
