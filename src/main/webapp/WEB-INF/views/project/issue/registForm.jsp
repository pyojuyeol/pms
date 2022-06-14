<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>PROSPEC'S | ISSUE</title>

<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prjNm }</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
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
	<div class="card-header">
		<div class="card-tools">
			<button type="button" class="btn btn-outline-success mr-1" id="sampleBtn" onclick="inputSampleText(textObj);">샘 플</button>
			<button type="button" class="btn btn-primary mr-1" id="registBtn" onclick="regist_go();">등 록</button>
			
			<c:set var="URL" value="${pageContext.request.requestURL}" />
			<c:set var="isOpen" value="${fn:indexOf(URL,'.open') lt 0}" />
			<c:if test="${!isOpen }">
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'">취 소</button>
			</c:if>
			<c:if test="${isOpen }">
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="history.go(-1);">취 소</button>
			</c:if>
		</div>
	</div>			

	<div class="card-body pad">
	<c:set var="from" value=""></c:set>
		<form enctype="multipart/form-data" role="form" method="post" action="regist" name="registForm">
			<input type="number" class="d-none" name="bookmarkNo" value="${param.bookmarkNo }">
			<input type="hidden" name="issueRegister" value="${loginUser.userId }">
			<%-- <input type="hidden" name="from" value="${fn:split(URL,'from=')[1]}"> --%>
			<div class="form-group col-12 ml-3 clearfix">
			
				<label class="form-group col-1" for="title"><b>제목</b></label>
				<input type="text" style="display:inline-block" id="title"
					name='title'  class="form-group form-control col-10"  placeholder="제목을 입력하세요 (*필수)"><br/>

				<label class="form-group col-1" for="title"><b>우선순위</b></label>
				<select name="gradeCode" class="form-control select2 select2-hidden-accessible col-2 mr-5" 
						style="display:inline-block" data-sortorder="">
					<option value="l">낮음</option>
					<option value="u">보통</option>
					<option value="h">높음</option>
					<option value="e">긴급</option>
				</select>
				<span class="mr-4"></span>
				<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
				<label class="form-group col-1 ml-3" for="title"><b>상태</b></label>
				<select name="sttCode" class="form-control select2 select2-hidden-accessible col-2 mr-5" 
						style="display:inline-block" data-sortorder="">
					<option value="open">열기</option>
					<option value="close">닫기</option>
				</select>
				<label class="form-group col-1 ml-4 mr-3" for="title"><b>일감</b></label>
				<input class="form-control col-2" style="display:inline-block" list="workList" name="workNo" id="workNo" value="${param.workNo }">
				<datalist id="workList">
				  <c:if test="${!empty workList }">
						<c:forEach items="${workList }" var="work">
							<option value="${work.workNo}">${work.workTitle}</option>
						</c:forEach>
					</c:if>
				</datalist>
				<%-- <select name="sttCode" class="form-control select2 select2-hidden-accessible col-2" 
						style="display:inline-block" data-sortorder="">
					<c:if test="${!empty workList }">
					<c:forEach items="${workList }" var="work">
						<option class="" id="${work.workNo}" value="${work.workNo}">${work.workTitle}</option>
					</c:forEach>
				</c:if>
				</select> --%>
				
				<%-- 
				<label class="form-group col-1" for="title"><b>책갈피</b></label>
				<select class="form-control select2 select2-hidden-accessible col-10" 
						style="display:inline-block" data-sortorder="">
					<c:if test="${!empty bmkList }">
						<c:forEach items="${bmkList }" var="bmk" >
							<option value="${bmk.bookmarkTitle}">${bmk.bookmarkTitle}</option>
						</c:forEach>
					</c:if>
				</select>
				 --%>
				<%-- <label class="form-group col-1" for="work-options"><b>참조자</b></label>
				<select id="work-options" class="form-control col-9" style="display:inline-block">
				<c:if test="${!empty prjMemList }">
					<c:forEach items="${prjMemList }" var="refMem">
						<option class="${refMem.userNm}" id="${refMem.userId}" value="${refMem.userId}"><span id="refNm" value="${refMem.userNm}">${refMem.userNm}</span>(${refMem.userId})</option>
					</c:forEach>
				</c:if>
				</select>
				
 
						<label class="form-group col-1" for="work-options"><b>참조자</b></label>
						<input type="text" list="work-options" >
	 						<datalist id="work-options">
							<c:if test="${!empty prjMemList }">
								<c:forEach items="${prjMemList }" var="refMem">
									<option value="${refMem.userId}">${refMem.userId}</option>
								</c:forEach>
							</c:if>
							</datalist> 
							
				<button type="button" id="select" class="btn btn-block btn-default col-1 mb-1" style="display:inline-block"
						onclick="addRefMem();">추가</button>
				<div class="col-11 align-self-end d-flex flex-wrap float-right" id ="refMem" >
				</div>	 --%>
			</div>
			

			<div class="form-group col-11 ml-4">
				<!-- <label for="content">내 용</label> -->
				<textarea class="form-control" name="content" id="content" rows="10"
					placeholder="내용을 작성하세요."></textarea>
			</div>
		</form>
	</div><!--end card-footer  -->
</div><!-- end card -->
</section>

<script>
let textObj = {
		 "title":"간트차트 우클릭 시 해당 이슈 리스트 목록 이동"
		,"content":"간트차트 우클릭 시 해당 이슈 리스트 목록 이동 \n일감명 -> 일감 번호로 수정이 필요합니다."
};

window.onload = function() {
	//$('#content').summernote({placeholder:'내용을 입력하세요.',height:200});
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
}


// function addRefMem(){
// 	var refMem = $('#work-options').val();
// 	var refNm = $('#refNm').val();
// 	
// 	
// 	var appendStr = '<div class="ref">';
// 	appendStr +='<input type="hidden" name="refMem" value='+refMem+'>';
// 	appendStr +='<span class="mr-5">'+refMem;
// 	appendStr +='<button type="button" class="btn btn-tool  remove-btn" ><i class=" remove-btn fas fa-times"></i></button>';
// 	appendStr +='</span>';
// 	appendStr +='</div>';
// 	$("#refMem").append(appendStr);
// 
// 	
// 	var refMem = $('#work-options').val();
// 	var appendStr = '<div class="d-flex mr-1">';
// 	    appendStr+= '      <div class="toast fade show " role="alert" ';
// 	    appendStr+= '         aria-atomic="true" style="flex-basis:auto;">';
// 	    appendStr+= '         <div class="toast-header" style="padding: 0.5rem 0.75rem;">';
// 	    appendStr+= '            <img class="fas fa-comments bg-yellow manPicture" data-id="sdf" style="display:block;width:40px;height:40px;margin:0 auto;"/>';
// 	    appendStr+= '            <p class="m-0 mr-4">';
// 	    appendStr+= '               <strong class="">sdf</strong>';
// 	    appendStr+= '            </p>';
// 	    appendStr+= '            <button data-dismiss="toast" type="button" class="ml-2 close align-self-start" aria-label="Close">×';
// 	    appendStr+= '            </button>';
// 	    appendStr+= '         </div>';
// 	    appendStr+= '      </div>';
// 	    appendStr+= '</div>';
//
// 	    $("#refMem").append(appendStr);
//
//}

function remove(e){
	// var removeId = $('#work-options').val();
	// alert(removeId);
}

// let refMem = document.getElementById("refMem");
// refMem.addEventListener('click', initIssue);

function initIssue(){
	let target = event.target;
	if($(target).hasClass('remove-btn')){
		$(target).closest('.ref').remove();
	}
}
function regist_go(){
	
	var form = document.registForm;
	if(form.title.value==""){
		alertI("제목은 필수입니다.");
		return;
	}
	if(form.content.value==""){
		alertI("내용은 필수입니다.");
		return;
	}
	let a = '${param.from}';
	let b = '${param.bookmarkNo}';
	
 	if(a=="workList"){
		form.action = 'regist.do?from=workList';
	} else if(b>0){
		form.action = 'regist.do?from=issue'; 
	} else if(a=="gantt"){
		form.action = 'regist.do?from=gantt';
	} else {
		form.action = 'regist.do?from=issue'; 
	}
	form.submit();
}
</script>

</body>

