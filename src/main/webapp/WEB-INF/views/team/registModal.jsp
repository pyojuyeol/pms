<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="registModal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">팀 생성</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<form role="form" method="post" action="regist.do" name="registForm">
		  	  <c:if test="${isOpen }">
				<input type="hidden" id="open" name="open" class="form-control" value="1">
			  </c:if>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="teamNm">이름</label>
						<input type="text" id="teamNm" name="teamNm" class="form-control" placeholder="팀명을 쓰세요">
					</div>
				</div>
				
				<div class="form-group">
					<label for="">팀원</label>
					<button type="button" class="btn btn-xs btn-primary ml-2" onclick="OpenWindow('/pms/orgExplorer/list.open?name=addMembers&tag=div','사용자 탐색',800,600);"><i class="fas fa-plus"></i></button>
				</div>
				
				<div id="memberInput" class="d-flex flex-wrap">

				</div>
				
			</form>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-danger" data-dismiss="modal">취 소</button>
				<button type="button" class="btn btn-primary mr-2" onclick="regist_go();">저 장</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<script>

var dataNum = 0;
function updateUserList(exMemArr) {
	
	console.log(exMemArr);

	// 기존 멤버 리스트 가져오기.
	let addedMemList = $(".addMembers");
	
	// 기존 멤버가 없기 때문에 거를 필요 없이 새롭게 멤버 추가.
	if (addedMemList.length == 0) {
		for (i = 0; i < exMemArr.length; i++) {
			addMember(exMemArr[i]);
		}
	}
		
	// 기존 멤버가 존재해서 밸리데이션 필요.
	var addedArr = [];
	if (addedMemList.length != 0) {
		
		console.log('else 타냐??');
		
		// 배열 some() 함수 사용을 위해 아이디만 뽑아서 배열에 추가 
		for (item of addedMemList) {
			let id = $(item).attr('value');
			addedArr.push(id);
		}
		
		console.log(addedArr);
		
		
		// 체크 해제로 인한 기존 멤버 삭제
		for (i = 0; i < addedArr.length; i++) {
			console.log(addedArr[i]);
			let result = exMemArr.every(val => { return val.id != addedArr[i] });
			console.log(result);
			
			if (result == true) {
				console.log($('div[value="' + addedArr[i] + '"]').data('no'));
				
				let dataNo = $('div[value="' + addedArr[i] + '"]').data('no');
				removeMember(dataNo);
			}
		}
		
		// 기존 멤버가 존재해서 밸리데이션이 필요한 경우. (중복 추가 방지);
		// some() 기존 멤버 ID 배열에 같은 아이디가 존재 하면 true 리턴.
		// false 리턴 시 기존 멤버에 없으므로 추가.
		for (i = 0; i < exMemArr.length; i++) {
			let result = addedArr.some(val => { return val == exMemArr[i].id });
			if (result == false) {
				addMember(exMemArr[i]);
			} 
		}	
		
	}
	
	// 이미지 출력.
	MemberPictureThumb('<%=request.getContextPath() %>');
}


// 팀원 추가
function addMember(addMem) {
	let id = addMem.id;
	let userName = addMem.userName;
	
	var toast = $('<div>').addClass("toast fade show mr-2 mt-2 addMembers").attr({"data-no":dataNum, "role":"alert", "aria-atomic":"true", "name":"addMembers", "value":id}).css({"flex-basis":"auto", "max-width":"130px"});
	var toastHeader = $('<div>').addClass("toast-header").css("padding","0.5rem 0.75rem");
	var span = $('<span>').addClass("img-circle manPicture mr-2").attr({"data-id":id}).css({"display":"block", "width":"38px", "height":"38px"});
	var p = $('<p>').addClass("m-0");
	var strong = $('<strong>').css("font-dize", "1.2em").text(userName);
	
	p.append(strong);
	toastHeader.append(span);
	toastHeader.append(p);
	toastHeader.append("<button class='ml-2 close align-self-start' type='button' data-dismiss='toast' onclick='removeMember(" + dataNum + "); return false;' aria-label='Close'>×</button>");
	toast.append(toastHeader);
	
	$('#memberInput').append(toast);
	
	var input = $('<input>').attr({"type":"hidden", "id":dataNum, "name":"addMembers", "value":id});
	$('form[role="form"]').prepend(input);

	dataNum++;
}

//팀원 제거
function removeMember(dataNum) {
	$('div[data-no="' + dataNum + '"]').remove();
	$('input[id="' + dataNum + '"]').remove();
}


function regist_go() {
// 	alert("regist btn click");

// 	if ($("input[name='teamNm']").val() == "") {  // form.title.value
// 		alert("팀명은 필수입니다.");
// 		$("input[name='teamNm']").focus();
// 		return;
// 	}
	
	$("form[role='form']").submit();
}
</script>
