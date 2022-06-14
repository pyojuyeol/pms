<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modifyModal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">팀 </h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<form role="form" method="post" action="modify.do" name="modifyForm">
					<input type="hidden" id="teamNo" name="teamNo" value="${team.teamNo }" />
					
					<div class="row">
						<div class="form-group col-md-6">
							<label for="teamNm">이름</label>
							<input type="text" id="teamNm" name="teamNm" class="form-control" placeholder="팀명을 쓰세요" value="${team.teamNm }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="">팀원</label>
						<button type="button" class="btn btn-xs btn-primary ml-2" onclick="OpenWindow('/pms/orgExplorer/list.open?name=addMembers&tag=div','사용자 탐색',800,600);"><i class="fas fa-plus"></i></button>
					</div>
					
					<div id="memberInput" class="d-flex flex-wrap">
					
						<c:forEach items="${team.teamMemberList }" var="tmem" varStatus="status">
						
							<div class="toast fade show mr-2 mt-2 addedMembers oldMembers thumb${tmem.userId }" name="addMembers" value="${tmem.userId }" role="alert" aria-atomic="true" style="flex-basis:auto; max-width:130px;">
								<input type="hidden" id="${status.index }" name="addMembers" value="${tmem.userId }">
								<div class="toast-header" style="padding: 0.5rem 0.75rem;">
									<span class="img-circle manPicture mr-2" data-id="${tmem.userId }" style="display:block; width:38px; height:38px;"></span>
									<p class="m-0">
										<strong>${tmem.userNm }</strong>
									</p>
									<button type="button" class="ml-2 close align-self-start" data-dismiss="toast" onclick="removeOld('thumb${tmem.userId }');return false;" aria-label="Close">×</button>
								</div>
							</div>
	
						</c:forEach>
						
					</div>
					
				</form>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-danger" data-dismiss="modal">취 소</button>
				<button type="button" class="btn btn-primary mr-2" onclick="modify_go();">저 장</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script>
window.onload = function() {
	let addedMemList = $(".addedMembers");
	dataNum = addedMemList.length;
}
</script>
	
	
<script>

var dataNum = 0;

var saveArr = [];
function updateUserList(exMemArr) {
	
	console.log(exMemArr);

	// 기존 멤버 리스트 가져오기.
	let addedMemList = $(".addedMembers");
	let oldMemList = $(".oldMembers");
// 	let removeSize = $("input[name='removeMembers']").length;
	
	// 추가된 멤버가 전혀 없기 때문에 거를 필요 없이 바로 멤버 추가.
	if (addedMemList.length == 0) {
		for (i = 0; i < exMemArr.length; i++) {
			addMember(exMemArr[i]);
		}
	}
	
	// 기존 멤버가 존재해서 밸리데이션 필요.
	var addedArr = [];
	var oldArr = [];
	if (addedMemList.length != 0) {
		
		// 배열 some() 함수 사용을 위해 아이디만 뽑아서 배열에 추가
		for (item of addedMemList) {
			let id = $(item).attr('value');
			addedArr.push(id);
		}
		for (item of oldMemList) {
			let id = $(item).attr('value');
			oldArr.push(id);
		}
		
		// 체크 해제로 인한 added 멤버 삭제
		for (i = 0; i < addedArr.length; i++) {
			let result = exMemArr.every(val => { return val.id != addedArr[i] });
			
			if (result == true) {
				let dataNo = $('div[value="' + addedArr[i] + '"]').data('no');
				removeMember(dataNo);
			}
		}
		
		// 체크 해제로 인한 old 멤버 삭제
		for (i = 0; i < oldArr.length; i++) {
			let result = exMemArr.every(val => { return val.id != oldArr[i] });
			
			if (result == true) {
				var saveData = $('div[value="' + oldArr[i] + '"]');
				var userId = oldArr[i];
				
				let delClass = 'thumb' + userId
				removeOld(delClass);
			}
		}
		
	}
	
	// removeMembers에 있는 멤버를 복구하면 처음 변수에 담아 두었던 addedMemList에 오차가 생기므로 다시 가져와서 비교.
	addedMemList = $(".addedMembers");
	if (addedMemList.length != 0) {
		
		for (item of addedMemList) {
			let id = $(item).attr('value');
			addedArr.push(id);
		}
		// 기존 멤버가 존재해서 밸리데이션이 필요한 경우. (중복 추가 방지);
		// some() 기존 멤버 ID 배열에 같은 아이디가 존재 하면 true 리턴. false 리턴 시 기존 멤버에 없으므로 추가.
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


function addMember(addMem) {
	let id = addMem.id;
	let userName = addMem.userName;
	
	var toast = $('<div>').addClass("toast fade show mr-2 mt-2 addedMembers").attr({"data-no":dataNum, "role":"alert", "aria-atomic":"true", "name":"addMembers", "value":id}).css({"flex-basis":"auto", "max-width":"130px"});
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

// added x 버튼 클릭 삭제
function removeMember(dataNum) {
	$('div[data-no="' + dataNum + '"]').remove();
	$('input[id="' + dataNum + '"]').remove();
}


// oldMember x 버튼 클릭 삭제 (saveArr 백업.)
function removeOld(className) {
	var div = $('div.' + className);
	div.remove();
}



function modify_go() {
// 	alet("modify btn click");

	var form = $('form[name="modifyForm"]');

// 	if ($("input[name='teamNm']").val() == "") {  // form.title.value
// 		alert("팀명은 필수입니다.");
// 		$("input[name='teamNm']").focus();
// 		return;
// 	}

	form.submit();
}
</script>
