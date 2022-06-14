<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>

<style></style>

</head>
<title>프로젝트 설정</title>
<body>

	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<!-- 				<h1>프로젝트이름</h1> -->
				</div>
				<div class="col-sm-6 d-none d-sm-block">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">홈</a></li>
						<li class="breadcrumb-item"><a href="#">프로젝트</a></li>
						<li class="breadcrumb-item active">설정</li>
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
				<div class="card-tools">
					<button type="button" class="btn btn-primary" id="modifyBtn" onclick="modify_go('${prj.prjNo }');">저 장</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'">취 소</button>
				</div>
			</div>
			<!--end card-header  -->

			<div class="card-body pad">
				<form role="form" method="post" action="modify.do" name="modifyForm">
					<input type="hidden" id="prjNo" name="prjNo" class="form-control" value="${prj.prjNo }">

					<div class="form-group">
						<label for="prjNm">이름</label> <input type="text" id="prjNm" name='prjNm' class="form-control" placeholder="프로젝트명을 쓰세요" value="${prj.prjNm }">
					</div>

					<div class="row">
						<div class="form-group col-md-6">
							<label for="enabled">공개여부</label> <select id="enabled" name="enabled" class="form-control custom-select">
								<option value="y" ${prj.enabled eq 'y' ? 'selected' : '' }>공개</option>
								<option value="n" ${prj.enabled eq 'n' ? 'selected' : '' }>비공개</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="sttCode">상태</label> <select id="sttCode" name="sttCode" class="form-control custom-select">
								<option value="p" ${prj.sttCode eq 'p' ? 'selected' : '' }>진행중</option>
								<option value="h" ${prj.sttCode eq 'h' ? 'selected' : '' }>보류중</option>
								<option value="c" ${prj.sttCode eq 'c' ? 'selected' : '' }>완료</option>
							</select>
						</div>
					</div>

					<div class="row">
					
						<div class="form-group col-md-6">
							<label>기간</label>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">
										<i class="far fa-calendar-alt"></i>
									</span>
								</div>
								<input type="text" class="form-control float-right" id="rangeDt" name="rangeDt">
							</div>
						</div>

						<div class="form-group col-md-6">
							<label for="teamNo">주관팀</label> <select id="teamNo" name="teamNo" class="form-control custom-select">
								<c:if test="${!empty teamList }">
									<c:forEach items="${teamList }" var="team">
										<option value="${team.teamNo }" ${team.teamNo eq prj.teamNo ? 'selected' : '' }>${team.teamNm }</option>
									</c:forEach>
								</c:if>
							</select>

						</div>
					</div>

					<div class="form-group">
						<label for="">구성원</label>&nbsp;&nbsp;
						<button type="button" class="btn btn-xs btn-primary" onclick="OpenWindow('/pms/orgExplorer/list.open?name=addMembers&tag=div','사용자 탐색',800,600);"><i class="fas fa-plus"></i></button>
					</div>

					<div id="memberInput" class="d-flex flex-wrap">

						<c:forEach items="${prj.prjUserList }" var="user" varStatus="status">

							<div class="toast fade show mr-2 mt-2 addedMembers oldMembers thumb${user.userId }" role="alert" aria-atomic="true" name="addMembers" value="${user.userId }" style="flex-basis: auto; max-width: 180px; display: inline-block;">
								<input type="hidden" id="${status.index }" name="addMembers" value="${user.userId }">
								<div class="toast-header" style="padding: 0.75rem;">
									<span class="img-circle manPicture mr-3" data-id="${user.userId }" style="display: block; width: 38px; height: 38px;"></span>
									<div class="m-0 mr-2">
										<p class="mb-1">
											<strong>${user.userNm }</strong>
										</p>
										<select class="form-control p-1" name="prjRole" style="font-size: 13px; width: 50px; height: 1.75rem;">
											<c:if test="${!empty prjRoleList }">
												<c:forEach items="${prjRoleList }" var="prjRole">
													<option value="${prjRole.code}" ${prjRole.code eq user.prjRole ? 'selected' : ''}>${prjRole.nm }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<button class="ml-2 close align-self-start" type="button" data-dismiss="toast" onclick="removeOld('thumb${user.userId }');return false;" aria-label="Close">×</button>
								</div>
							</div>


						</c:forEach>

					</div>

					<br />

					<div class="form-group">
						<textarea class="textarea" id="prjComment" name="prjComment" rows="20" cols="70" placeholder="1000자 내외로 작성하세요."></textarea>
					</div>

				</form>
			</div>
			<!-- card-body End -->

		</div>
		<!-- card End -->
	</section>


<script>
window.onload = function() {
	$('#prjComment').val("${prj.prjComment }");
	summernote_go($('#prjComment'), '<%=request.getContextPath()%>');
	
    //Date range picker
	moment.locale('ko'); // 언어를 한국어로 설정함
    $('#rangeDt').daterangepicker({
	    "startDate": "<fmt:formatDate value='${prj.bgnDt}' pattern='yyyy/MM/dd'/>",
	    "endDate": "<fmt:formatDate value='${prj.endDt}' pattern='yyyy/MM/dd'/>",
        locale :{ 
	        format: 'YYYY-MM-DD',
	        applyLabel: "적용",
	        cancelLabel: "닫기",
	        separator: ' ~ ',
	     	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	   		daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
//         	showMonthAfterYear: true
        }
    });
	
	
	let addedMemList = $(".addedMembers");
	dataNum = addedMemList.length;
}
</script>


<script>	

var dataNum = 0;

function updateUserList(exMemArr) {
	console.log(exMemArr);

	// 기존 멤버 리스트 가져오기.
	let addedMemList = $(".addedMembers");
	let oldMemList = $(".oldMembers");
	
	// 기존 멤버가 없기 때문에 거를 필요 없이 새롭게 멤버 추가.
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
			console.log(addedArr[i]);
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


var dataNum = 0;

function addMember(addMem) {
	let id = addMem.id;
	let userName = addMem.userName;
	
	var toast = $('<div>').addClass("toast fade show mr-2 mt-2 addedMembers").attr({"data-no":dataNum, "role":"alert", "aria-atomic":"true", "name":"addMembers", "value":id}).css({"flex-basis":"auto", "max-width":"180px", "display":"inline-block"});
	var toastHeader = $('<div>').addClass("toast-header").css("padding","0.75rem");
	var span = $('<span>').addClass("img-circle manPicture mr-3").attr({"data-id":id}).css({"display":"block", "width":"38px", "height":"38px"});
	var div = $('<div>').addClass("m-0 mr-2");
	var p = $('<p>').addClass("mb-1");
	var strong = $('<strong>').text(userName);
	var select = $('<select>').addClass("form-control p-1").attr({"name":"prjRole"}).css({"font-size":"13px", "width":"50px", "height":"1.75rem"});
	var option1 = $('<option>').text('PM');
	var option2 = $('<option>').text('PL');
	var option3 = $('<option>').text('AA');
	var option4 = $('<option>').text('TA');
	var option5 = $('<option>').text('DA');
	var option6 = $('<option>').text('UA');
	var option7 = $('<option>').text('BA');
	
	p.append(strong);
	select.append(option1);
	select.append(option2);
	select.append(option3);
	select.append(option4);
	select.append(option5);
	select.append(option6);
	select.append(option7);
	div.append(p);
	div.append(select);
	toastHeader.append(span);
	toastHeader.append(div);
	toastHeader.append("<button class='ml-2 close align-self-start' type='button' data-dismiss='toast' onclick='removeMember(" + dataNum + "); return false;' aria-label='Close'>×</button>");
	toast.append(toastHeader);
	
	$('#memberInput').append(toast);
	
	var input = $('<input>').attr({"type":"hidden", "id":dataNum, "name":"addMembers", "value":id});
	$('form[role="form"]').prepend(input);

	dataNum++;
}
	
	
//added x 버튼 클릭 삭제
function removeMember(dataNum) {
	$('div[data-no="' + dataNum + '"]').remove();
	$('input[id="' + dataNum + '"]').remove();
}


// oldMember x 버튼 클릭 삭제 (saveArr 백업.)
function removeOld(className) {
	var div = $('div.' + className);
	div.remove();
}


// submit ===========================================
function modify_go(prjNo) {
	// 로딩 애니매이션 출력
	$("#loader").addClass("loading");
	
	// 유저 리스트 생성.
	let addMemberMap = {};
	let addMembers = $(".addedMembers");

	for (item of addMembers) {
		let userId = $(item).find("span.manPicture").data('id');
		let prjRole = $(item).find("select[name='prjRole']").val();
		
		addMemberMap[userId] = prjRole;
	}
	console.log(addMemberMap);
	
	
	let modifyCMD = {
		"prjNo" : $("input[name='prjNo']").val(),
		"prjNm" : $("input[name='prjNm']").val(),
		"prjComment" : $("textarea[name='prjComment']").val(),
		"teamNo" : $("select[name='teamNo']").val(),
		"enabled" : $("select[name='enabled']").val(),
		"sttCode" : $("select[name='sttCode']").val(),
		"rangeDt" : $("input[name='rangeDt']").val(),
		"addMemberMap" : addMemberMap
	}
		
	$.ajax({ 
		url: "<%=request.getContextPath()%>/prjSetting/modify.do?prjNo=" + prjNo,
		type: "POST",
		data: JSON.stringify(modifyCMD),
		contentType: "application/json",
		success: function(result) { 
			// 로딩 애니매이션 숨기기
			$("#loader").removeClass("loading");
			if (result) {
				Swal.fire({
					text: '수정되었습니다.',
					icon: 'success',
					confirmButtonColor: '#007bff',
					confirmButtonText: '확인'
				}).then((result) => {
					location.href = 'list.do';
				});
			}
		}, 
		error: function(request, status, error) { 
			Swal.fire({
				text: '에러가 발생되었습니다. 에러코드' + error,
				icon: 'error',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인'
			}).then((result) => {
				$("#loader").removeClass("loading");
				location.href = 'list.do';
			});
		}
	});
}
</script>


</body>