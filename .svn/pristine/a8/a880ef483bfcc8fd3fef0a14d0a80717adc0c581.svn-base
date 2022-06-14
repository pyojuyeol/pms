<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login/login.css" />
</head>
<title>PROSPEC'S | 비밀번호 변경</title>
<body>
<div class="center">
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1><img src="<%=request.getContextPath()%>/resources/images/common/favicon_b.svg" style="width:30px;margin-right:5px;"/>Prospec's</h1>
			</div>
			<div class="form-content">
				<form action="./recoverPwd" method="post" id="recoverPwdForm" method="post">
					<input type="hidden" name="key" value="${param.key }">
					<div class="form-group">
						<label for="password">비밀번호</label> 
						<input type="password" id="pwd" name="pwd" placeholder="새로운 비밀번호를 입력하세요." value="${param.userId }" required="required">
					    <p class="info" id="checkPassInfo">8자리 ~ 20자리(영문, 숫자, 특수문자 포함) 이내로 입력해주세요.</p>
					</div>
					<div class="form-group">
						<label for="pwdCheck">비밀번호 확인</label> 
						<input type="password" id="pwdCheck" name="pwdCheck" placeholder="비밀번호를 다시 한번 입력하세요." required="required">
					    <p class="info" id="checkEqualInfo"></p>
					</div>
					<div class="form-group">
						<button type="button" id="submitBtn" class="disabled">비밀번호 변경</button>
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two"></div>
	</div>
</div>
	<script type="text/javascript">
		//유효성 검사 제거
// 		let submitBtn = document.querySelector("#submitBtn");
// 		let validationCheck = true;
// 		submitBtn.classList.remove("disabled");
// 		submitBtn.addEventListener('click', submitForm);
		
		window.addEventListener('DOMContentLoaded', init);
		let validationCheck1 = false;
		let validationCheck = false;
		let inputPwd = document.querySelector("input[name=pwd]");
		let inputPwd2 = document.querySelector("input[name=pwdCheck]");
		let submitBtn = document.querySelector("#submitBtn");
		function init(){
			//패스워드 유효성 검사
			inputPwd.addEventListener('keyup', checkUserPass);
			//패스워드 확인 검사
			inputPwd2.addEventListener('keyup', checkEqualPass);
			//서브밋 등록
			submitBtn.addEventListener('click', submitForm);
		}
		
		function checkUserPass(){
			let tar = document.querySelector("#checkPassInfo");
			var pw = inputPwd.value;
			let num = pw.search(/[0-9]/g);
			let eng = pw.search(/[a-z]/ig);
			let spe = pw.search(/[`~!@@#$%^&*|₩₩₩;:₩/?]/gi);
			let msg = "";
			validationCheck1 = false;
			validationCheck = false;
			submitBtn.classList.add("disabled");
			
			if(pw.length < 8 || pw.length > 20){
				tar.style.color = 'red';
				msg="8자리 ~ 20자리 이내로 입력해주세요.";
				tar.innerText = msg;
				return false;
			}else if(pw.search(/\s/) != -1){
				tar.style.color = 'red';
				msg="비밀번호는 공백 없이 입력해주세요.";
				tar.innerText = msg;
				return false;
			}else if(num < 0 || eng < 0 || spe < 0 ){
				tar.style.color = 'red';
				msg="영문, 숫자, 특수문자를 혼합하여 입력해주세요.";
				tar.innerText = msg;
				return false;
			}else {
				tar.style.color = 'blue';
				msg="사용가능한 비밀번호 입니다.";
				tar.innerText = msg;
				validationCheck1 = true;
 				checkEqualPass();
			   	return true;
			}
		}
		function checkEqualPass(e){
			
			let tar = document.querySelector("#checkEqualInfo");
			var pw = inputPwd.value;
			var pw2 = inputPwd2.value;
			let msg = "";
			
			if(!validationCheck1) return;
			if(pw==pw2){
				tar.style.color = 'blue';
				msg="비밀번호와 비밀번호 확인이 일치합니다.";
				tar.innerText = msg;
				validationCheck = true;
				submitBtn.classList.remove("disabled");
			   	return true;
			}else {
				tar.style.color = 'red';
				msg="비밀번호와 비밀번호 확인이 일치하지 않습니다.";
				tar.innerText = msg;
				validationCheck = false;
				submitBtn.classList.add("disabled");
				return false;
			}
			
		}
		function submitForm(){
			if(validationCheck){				
				document.getElementById('recoverPwdForm').submit();
			} else {
				Swal.fire({
					  text: '입력값을 확인해주세요.',
					  icon: 'error',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
					});
			}
		}
	</script>
</body>