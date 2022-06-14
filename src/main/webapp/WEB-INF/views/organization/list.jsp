<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--   <meta http-equiv="content-type" content="text/html; charset=UTF-8"> -->
<head>
<link rel="stylesheet"
	href=<c:url value="/resources/css/zTreeStyle.css"/> type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cloud/cloud.css" />
</head>
<body>
	<script type="text/javascript"
		src=<c:url value="/resources/js/zTree/jquery-1.4.4.min.js"/>></script>
	<script>
		var $j144 = jQuery.noConflict();
	</script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/zTree/jquery.ztree.all.js"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/zTree/jquery.ztree.core.js"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/zTree/jquery.ztree.excheck.js"/>></script>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prj.prjNm }</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active">조직도</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<section class="content">
	<div class="card card-info card-outline">
		<div class="card-header">
			<h3 class="card-title p-2">조직도</h3>
			<div class="card-tools">
			
<!-- 				<button type="button" class="btn btn-primary" id="modifyBtn" -->
<!-- 					onclick="transferUserList();">저 장</button> -->
<!-- 				&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 				<button type="button" class="btn btn-warning" id="cancelBtn" -->
<!-- 					onclick="window.close();">취 소</button> -->
			</div>
		</div>
		<!--end card-header  -->
		<!-- html 부분 -->
		<div class="container col-md-12 m-0" style="min-height: 550px;">
			<div class="row">
				<div
					class="card card-secondary card-outline card-outline-tabs col-md-4 m-0 pl-0 pr-0"
					style="min-height: 100%; max-height: 100%;">
					<div class="mt-0 pl-0 pr-0"
						style="min-height: 550px; max-height: 550px;">
						<div class="card-header p-0 border-bottom-0">
							<ul class="nav nav-tabs nav-pills" id="custom-tabs-four-tab"
								role="tablist">
								<li class="nav-item ml-2"><a class="nav-link active"
									id="custom-tabs-four-home-tab" data-toggle="pill" href="#dept"
									role="tab" aria-controls="dept" aria-selected="true"><b>부서</b></a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="custom-tabs-four-messages-tab" data-toggle="pill"
									href="#team" role="tab" aria-controls="team"
									aria-selected="false"><b>팀</b></a></li>
<!-- 								<li class="nav-item"><a class="nav-link" -->
<!-- 									id="custom-tabs-four-messages-tab" data-toggle="pill" -->
<!-- 									href="#etc" role="tab" aria-controls="etc" -->
<!-- 								aria-selected="false"><b>프로젝트 멤버</b></a></li> -->
<!-- 							<li type="button" class="btn btn-outline-secondary mt-1 mr-2 ml-2" style="width:auto;" onclick="loadOrg();"> -->
<!-- 								<i class="fa-solid fa-arrows-rotate" style="color:#1cc242;"></i><b></b> -->
<!-- 							</li> -->
							<li type="button" class="btn btn-outline-secondary mt-1 mr-2 ml-2" style="width:100%;" onclick="addDeptOrTeam();">
								<i class="fa-solid fa-plus mr-1" style="color:#1cc242;"></i><b>생성</b>
							</li>
							</ul>
						</div>
						<div class="ztree tab-content"
							style="overflow: scroll; min-height: 480px; max-height: 480px;">
							<div class="tab-pane fade active show pt-2 pl-2" id="dept"
								role="tabpanel" aria-labelledby="dept">
								<div id="treeDept"></div>
							</div>
							<div class="tab-pane fade pt-2 pl-2" id="team"
								role="tabpanel" aria-labelledby="team">
								<div id="treeTeam"></div>
							</div>
<!-- 							<div class="tab-pane fade pt-2 pl-2" id="etc" -->
<!-- 								role="tabpanel" aria-labelledby="etc"> -->
<!-- 								<div id="treePrj"></div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
				<div class="col-md-8 overflow-scroll"
					style="overflow: scroll; min-height: 561px; max-height: 561px;">
					<table class="table table-sm text-center">
						<thead>
							<tr>
								<th width="5%" scope="col"></th>
								<th width="15%" scope="col">사원번호</th>
								<th width="15%" scope="col">이름</th>
								<th width="10%" scope="col">성별</th>
								<th width="15%" scope="col">직책</th>
								<th width="30%" scope="col">부서</th>
							<tr>
						</thead>
						<tbody class="checkList">

						</tbody>
					</table>
				</div>
			</div>

		</div>
		<!-- html 부분 끝-->
	</div>
		<!-- outline 끝-->

</section>
<!--우클릭 메뉴 -->
 <div class="orgMenu btn-group-vertical rightmenu_layer rightmenu_on" style="display:none;">
 	<ul class="default min_right_ly">
	 	<li><a class="orgMenuChange" href="javascript:modifyDept();" data-id="" style="text-indent: 0px;">이름변경</a></li>
		<li><a class="orgMenuRemove" href="javascript:removeDept();" data-id=""  style="text-indent: 0px;">해체</a></li>
	</ul>
<!-- 	<button class="btn btn-sm btn-warning orgMenuChange" type="button" onclick="modifyDept(this);" >이름변경</button> -->
<!-- 	<button class="btn btn-sm btn-danger orgMenuRemove" type="button" onclick="removeDept(this);">해체</button> -->
 </div>
 <div class="orgMenuTeam rightmenu_layer rightmenu_on" style="display:none;">
 	<ul class="default min_right_ly">
 	  <li><a class="orgMenuTeamChange" href="javascript:modifyTeam();" data-id="" style="text-indent: 0px;">수정</a></li>
	  <li><a class="orgMenuTeamRemove" href="javascript:removeTeam();" data-id="" style="text-indent: 0px;">해체</a></li>
<!-- 	  <button class="btn btn-sm btn-warning orgMenuTeamChange" type="button" onclick="modifyTeam(this);" >수정</button> -->
<!-- 	  <button class="btn btn-sm btn-danger orgMenuTeamRemove" type="button" onclick="removeTeam(this);">해체</button> -->
	</ul>
 </div>
 
	<!-- 스크립트 부분 -->
<script>
	window.addEventListener('DOMContentLoaded', onloadOrg);

	function onloadOrg() {
		loadOrg();
	}
</script>
<script>
	var zTreeNodesDept = [];
	var zTreeNodesTeam = [];
	var zTreeNodesPrj = [];
	var zTreeNodesMap = {};
	var CheckedUserIdList = [];
	var userTransList = [];
	function loadOrg() {
		$.ajax({
			type : 'post', // 타입 (get, post, put 등등)
			url : 'list.do', // 요청할 서버url
			async : false, // 비동기화 여부 (default : true)
			contentType : "application/json; charset=UTF-8",
			dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
			success : function(result) { // 결과 성공 콜백함수
// 				console.log(result);
				var orgDept = result.orgDept;
				var orgTeam = result.orgTeam;
				zTreeNodesDept = orgDept;
				zTreeNodesTeam = orgTeam;
// 				console.log(orgDept);
				//id로 dto를 찾을 수 있는 맵의 값 세팅
				for (i = 0; i < orgDept.length; i++) {
					zTreeNodesMap[orgDept[i].id] = orgDept[i];
				}
				drawOrg(orgDept,$("#treeDept"));
				drawOrg(orgTeam,$("#treeTeam"));
				//체크박스 요소 name="id" 설정
				idExtractor();
				//폴더에 이벤트 추가
				addRightClickEvent();
				
				document.querySelector(".ico_open").click();
				
			},
			error : function(request, status, error) { // 결과 에러 콜백함수
				console.log(error)
			}
		});
	}
</script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template" id="checkList-template">
{{#each .}}
	<tr class="checkList" data-id={{id}}>
	  <td style="padding:3px;">
		<span class="img-circle manPicture" data-id={{id}} style="display:block;width:40px;height:40px;margin:0 auto;"></span>
	  </td>
	  <td>{{id}}</td>
	  <td>{{userName}}</td>
	  <td>{{gender}}</td>
	  <td>{{position}}</td>
	  <td>{{dept}}</td>
	<tr>
{{/each}}
</script>
<script>
	function drawOrg(orgResource, target) {
		var setting = {
			check : {
				enable : false
			},
			view : {
		//showIcon: showIconForTree,
				selectedMulti : true,
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		//조직도 그리기
		$j144.fn.zTree.init(target, setting, orgResource);
		
	}

	function printData(list, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());
		var html = template(list);
		target.html("");
		target.html(html);
	}
</script>

<script>
	function idExtractor() {
		var tag = document.querySelectorAll(".node_name");
		for (i = 0; i < tag.length; i++) {
			var idExtracted = tag[i].innerText.split(":")[1];
			var newName = tag[i].innerText.split(":")[0];
			tag[i].innerText = newName;
			tag[i].setAttribute('name', idExtracted);
			tag[i].setAttribute('title', newName);
			tag[i].parentNode.setAttribute('title', newName);
			tag[i].setAttribute('onclick', "showProfile(this)");
			tag[i].previousSibling.setAttribute('name', idExtracted);
			tag[i].previousSibling.setAttribute('onclick', "showProfile(this)");
			if(newName.indexOf("#") >= 0) {
				var dataId = newName.split("#")[1];
				newName = newName.split("#")[0];
				tag[i].innerText = newName;
				tag[i].parentNode.setAttribute('data-id', dataId);
				//부서,팀 항목에 우클릭 이벤트 용 클래스 추가
				tag[i].parentNode.classList.add('folder_rclick');
			}
		}
	}
</script>
<script>
	function showProfile(selector) {
// 		console.log("showProfile!");
// 		console.log(selector);
		var checker;
		checker = selector;
		var checkerName = selector.getAttribute("name");
		var checkerIdList = [];
		//모든 선택된 리스트 배열에 담음.
		if(checkerName != 'undefined') {
			checkerIdList.push( checkerName )
		} else{
			var checkerChildren = checker.parentNode.nextSibling.querySelectorAll("ul span.node_name");
			for(i=0; i<checkerChildren.length; i++) {
				checkerIdList.push( checkerChildren[i].getAttribute("name") );
			}
		}
// 		console.log(checkerIdList);
		writeCheckList(checkerIdList)
	}
</script>
<script>
	function writeCheckList(selectedIdList) {
		var checkList = [];
		for (i = 0; i < selectedIdList.length; i++) {
			checkList.push(zTreeNodesMap[selectedIdList[i]]);
		}
// 		console.log(checkList);
		printData(checkList, $("tbody.checkList"),
				$("#checkList-template"));
		//프로필 사진
		MemberPictureThumb('<%=request.getContextPath()%>');
		
	}
</script>
<script>
function addDeptOrTeam() {
	console.log("addDeptOrTeam!")
	var activeTab = $(".tab-pane.fade.active.show").attr("id");
	if (activeTab == 'dept') {
		OpenWindow("<c:url value='/organization/registForm.open?open=1' />", "부서 생성", 500, 500);
	} else if (activeTab == 'team') {
		OpenWindow("<c:url value='/team/registForm.open?open=1' />", "팀 생성", 500, 500);
	}
}
</script>
<script>
function addRightClickEvent() {
	var areaObj = $("#treeDept .folder_rclick");
	var areaObjT = $("#treeTeam .folder_rclick");
	var areaObj2 = $(".content-wrapper");
	var layerObj = $(".orgMenu");
	var layerObjT = $(".orgMenuTeam");
	areaObj.contextmenu( function(e){
	  	var dirId = e.target.closest("a").getAttribute("data-id");
	  	$(".orgMenuChange").attr('data-id',dirId);
	  	$(".orgMenuRemove").attr('data-id',dirId);
// 	  	console.log(layerObj[0]);
	});
	areaObjT.contextmenu( function(e){
	  	var dirId = e.target.closest("a").getAttribute("data-id");
	  	$(".orgMenuTeamChange").attr('data-id',dirId);
	  	$(".orgMenuTeamRemove").attr('data-id',dirId);
// 	  	console.log(layerObj[0]);
	});
	ShowLayer(areaObj,layerObj,"rclick");
	HideLayer(areaObj2,layerObj);
	ShowLayer(areaObjT,layerObjT,"rclick");
	HideLayer(areaObj2,layerObjT);
}
</script>
<script>
function modifyDept(selector) {
// 	var dataId = selector.getAttribute("data-id");
// 	console.log(dataId); 
	var dataId = document.querySelector(".orgMenuChange").getAttribute("data-id");
	promptSW("새로운 부서명",modifyDept_go, dataId);
}

</script>
<script>
function removeDept(selector) {
// 	var dataId = selector.getAttribute("data-id");
	var dataId = document.querySelector(".orgMenuRemove").getAttribute("data-id");
	confirmS("삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?", "warning", removeDept_go.bind(null,dataId) )
}
</script>
<script>
function modifyTeam(selector) {
// 	var dataId = selector.getAttribute("data-id");
	var dataId = document.querySelector(".orgMenuTeamChange").getAttribute("data-id");
// 	console.log(dataId);
	OpenWindow("<c:url value='/team/modifyForm.open?open=1&teamNo=" + dataId+"' />", "팀 수정", 500, 500);
}

</script>
<script>
function removeTeam(selector) {
// 	var dataId = selector.getAttribute("data-id");
	var dataId = document.querySelector(".orgMenuTeamRemove").getAttribute("data-id");
// 	console.log(dataId);
	confirmS('해체 후 복구가 불가능합니다. \n정말 해체하시겠습니까?', "warning", removeTeam_go.bind(null,dataId) )
}
</script>
<script>
function removeTeam_go(teamNo) {
	console.log(teamNo);
// 	OpenWindow("<c:url value='/organization/registForm.open?open=1&' />", "팀 생성", 500, 500);
	$.ajax({
	    type : 'GET', // 타입 (get, post, put 등등)
	    url : "<c:url value='/team/remove.open?open=1&teamNo=" + teamNo+"' />", // 요청할 서버url
	    async : true, // 비동기화 여부 (default : true)
// 	    dataType : 'text', // 데이터 타입 (html, xml, json, text 등등)
	    success : function(result) { // 결과 성공 콜백함수
// 	        console.log(result);
	    	alertA("정상 처리 되었습니다.","success", loadOrg);
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
		    alertE("요청 에러:" + request.status);
	    }
	});	
}
</script>
<script>
function modifyDept_go(deptName, dataId) {
// 	alert("modifyDept");
	$.ajax({
	    type : 'GET', // 타입 (get, post, put 등등)
	    url : "modify.do?name=" + deptName + "&code=" + dataId, // 요청할 서버url
	    async : true, // 비동기화 여부 (default : true)
// 	    dataType : 'text', // 데이터 타입 (html, xml, json, text 등등)
	    success : function(result) { // 결과 성공 콜백함수
// 	        console.log(result);
	    	alertC(result);
	    	loadOrg();
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	    	if(request.status >= 400 && request.status < 500 ) {
		        alertE("요청 에러:" + request.status);
		        return;
	        }
// 	        console.log(request);
	        alertE(request.responseText);
	    }
	});	
}
</script>
<script>
function removeDept_go(dataId) {
// 	alert("modifyDept");
// 	alert(dataId);
	$.ajax({
	    type : 'GET', // 타입 (get, post, put 등등)
	    url : "remove.do?code=" + dataId, // 요청할 서버url
// 	    async : true, // 비동기화 여부 (default : true)
// 	    dataType : 'text', // 데이터 타입 (html, xml, json, text 등등)
	    success : function(result) { // 결과 성공 콜백함수
	        console.log(result);
	    	alertC(result);
	    	loadOrg();
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	        console.log(request);
	        if(request.status >= 400 && request.status < 500 ) {
		        alertE("요청 에러:" + request.status);
		        return;
	        }
	        alertE(request.responseText);
	    }
	});	
}
</script>
</body>