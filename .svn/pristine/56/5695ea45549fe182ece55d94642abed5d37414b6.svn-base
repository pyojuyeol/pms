<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--   <meta http-equiv="content-type" content="text/html; charset=UTF-8"> -->
<head>
<link rel="stylesheet"
	href=<c:url value="/resources/css/zTreeStyle.css"/> type="text/css">
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
<section class="content">
	<div class="card card-info card-outline">
		<div class="card-header">
			<h3 class="card-title p-2">조직도</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-primary" id="modifyBtn"
					onclick="transferUserList();">저 장</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-warning" id="cancelBtn"
					onclick="window.close();">취 소</button>
			</div>
		</div>
		<!--end card-header  -->
		<!-- html 부분 -->
		<div class="container col-md-12 m-0" style="min-height: 445px;">
			<div class="row">
				<div
					class="card card-secondary card-outline card-outline-tabs col-md-4 m-0 pl-0 pr-0"
					style="min-height: 90%; max-height: 90%;">
					<div class="mt-0 pl-0 pr-0"
						style="min-height: 445px; max-height: 445px;">
						<div class="card-header p-0 border-bottom-0">
							<ul class="nav nav-tabs nav-pills" id="custom-tabs-four-tab"
								role="tablist">
								<li class="nav-item ml-2"><a class="nav-link active"
									id="custom-tabs-four-home-tab" data-toggle="pill" href="#news"
									role="tab" aria-controls="news" aria-selected="true"><b>부서</b></a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="custom-tabs-four-messages-tab" data-toggle="pill"
									href="#ntc" role="tab" aria-controls="ntc"
									aria-selected="false"><b>팀</b></a></li>
<!-- 							<li class="nav-item"><a class="nav-link" -->
<!-- 									id="custom-tabs-four-messages-tab" data-toggle="pill" -->
<!-- 									href="#etc" role="tab" aria-controls="etc" -->
<!-- 									aria-selected="false"><b>프로젝트 멤버</b></a></li> -->
							</ul>
						</div>
						<div class="ztree tab-content"
							style="overflow: scroll; min-height: 445px; max-height: 445px;">
							<div class="tab-pane fade active show pt-2 pl-2" id="news"
								role="tabpanel" aria-labelledby="news">
								<div id="treeDept"></div>
							</div>
							<div class="tab-pane fade pt-2 pl-2" id="ntc"
								role="tabpanel" aria-labelledby="ntc">
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
					style="overflow: scroll; min-height: 486px; max-height: 480px;">
					<table class="table table-sm text-center">
						<thead>
							<tr>
								<th width="5%" scope="col"></th>
								<th width="25%" scope="col">이름</th>
								<th width="15%" scope="col">성별</th>
								<th width="15%" scope="col">직책</th>
								<th width="40%" scope="col">부서</th>
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
		var tagtype = location.href.split("tag=")[1].split("&")[0];
		var tagname = location.href.split("name=")[1].split("&")[0];
		var openerTag = opener.$(tagtype + "[name='" + tagname
				+ "']");
		for (i = 0; i < openerTag.length; i++) {
			CheckedUserIdList.push(openerTag[i].getAttribute("value"));
		}
		// 		CheckedUserIdList.push("aaa");
		// 		CheckedUserIdList.push("3333");
		$.ajax({
			type : 'post', // 타입 (get, post, put 등등)
			url : 'list.do', // 요청할 서버url
			async : false, // 비동기화 여부 (default : true)
			contentType : "application/json; charset=UTF-8",
			dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
			data : // 보낼 데이터 (Object , String, Array)
			JSON.stringify(CheckedUserIdList),

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
				//체크박스에 이벤트 추가
				addOnclickEventToCheckBox();
				//초기 체크된 리스트 작성
				var checkList = [];
// 				console.log(orgDept);
				for (i = 0; i < orgDept.length; i++) {
					if (orgDept[i].user != '1') {
						continue;
					}
					if (!orgDept[i].checked) {
						continue;
					}
					checkList.push(orgDept[i]);
				}
				userTransList = checkList;
// 				console.log(checkList);
				printData(checkList, $("tbody.checkList"),
						$("#checkList-template"));
			},
			error : function(request, status, error) { // 결과 에러 콜백함수
				console.log(error)
			}
		});
	}
</script>
<script>
	function onCheck(e, treeId, treeNode) {
		count();
	}

	function setTitle(node) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = node ? [ node ] : zTree.transformToArray(zTree
				.getNodes());
		for (var i = 0, l = nodes.length; i < l; i++) {
			var n = nodes[i];
			n.title = "[" + n.id + "] isFirstNode = "
					+ n.isFirstNode + ", isLastNode = "
					+ n.isLastNode;
			zTree.updateNode(n);
		}
	}
	function count() {
		function isForceHidden(node) {
			if (!node.parentTId)
				return false;
			var p = node.getParentNode();
			return !!p.isHidden ? true : isForceHidden(p);
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), checkCount = zTree
				.getCheckedNodes(true).length, nocheckCount = zTree
				.getCheckedNodes(false).length, hiddenNodes = zTree
				.getNodesByParam("isHidden", true), hiddenCount = hiddenNodes.length;

		for (var i = 0, j = hiddenNodes.length; i < j; i++) {
			var n = hiddenNodes[i];
			if (isForceHidden(n)) {
				hiddenCount -= 1;
			} else if (n.isParent) {
				hiddenCount += zTree.transformToArray(n.children).length;
			}
		}

		$("#isHiddenCount").text(hiddenNodes.length);
		$("#hiddenCount").text(hiddenCount);
		$("#checkCount").text(checkCount);
		$("#nocheckCount").text(nocheckCount);
	}
</script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template" id="checkList-template">
{{#each .}}
	<tr class="checkList">
	  <td style="padding:3px;">
		<span class="img-circle manPicture" data-id={{id}} style="display:block;width:40px;height:40px;margin:0 auto;"></span>
	  </td>
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
				enable : true
			},
			view : {
		//showIcon: showIconForTree,
				selectedMulti : true,
			},
			data : {
				simpleData : {
					enable : true
				},
				callback : {
					onCheck : onCheck
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
	function writeCheckList() {
		var checkList = [];
		for (i = 0; i < CheckedUserIdList.length; i++) {
			checkList.push(zTreeNodesMap[CheckedUserIdList[i]]);
		}
// 		console.log(checkList);
		userTransList = checkList;
		printData(checkList, $("tbody.checkList"),
				$("#checkList-template"));
		//프로필 사진
		MemberPictureThumb('<%=request.getContextPath()%>');
	}
	
</script>
<script>
	function idExtractor() {
		var tag = document.querySelectorAll(".node_name");
		for (i = 0; i < tag.length; i++) {
			if (tag[i].innerText.indexOf(":")) {
				var idExtracted = tag[i].innerText.split(":")[1];
				var nameExtracted = tag[i].innerText.split(":")[1];
				var newName = tag[i].innerText.split(":")[0];
				tag[i].innerText = newName;
				var checkBox = tag[i].closest("a").previousSibling;
				checkBox.setAttribute('name', idExtracted);
				tag[i].setAttribute('title', nameExtracted);

			}
		}
	}
</script>
<script>
	function addOnclickEventToCheckBox() {
		$(".button.chk").click(function() {
			syncOrgs(this);			
		});
	}
	function checkCheckBox() {
		var userCheckedSet = new Set();
		var checkBox = $(".button.chk")
// 		console.log(checkBox.length);
		CheckedUserIdList = [];
		for (i = 0; i < checkBox.length; i++) {
			var name = checkBox[i].getAttribute("name");
			if (name == 'undefined') {
				continue;
			}
			if (checkBox[i].getAttribute("class").split("_")[1]
					.split("_")[0] == 'false') {
				continue;
			}
			if(!userCheckedSet.has(name)){
				CheckedUserIdList.push(name);
				userCheckedSet.add(name);
			}
		}
// 		console.log(CheckedUserIdList);
		writeCheckList();
	}
</script>

<script>
	function syncOrgs(selector) {
		var checker;
		checker = selector;
		console.log("syncOrgs!");
		var checkFlag = checker.getAttribute("class").split("checkbox_")[1].split("_")[0];
		var checkerName = selector.getAttribute("name");
		var checkerIdSet = new Set();
		var checkerPidSet = new Set();
		//모든 체크된 리스트 배열에 담음.
		if(checkerName != 'undefined') {
			checkerIdSet.add( checkerName )
		} else{
			var checkerChildren = checker.parentNode.querySelectorAll("ul span.button.chk");
			for(i=0; i<checkerChildren.length; i++) {
				checkerIdSet.add( checkerChildren[i].getAttribute("name") );
			}
		}
		//리스트에서 하나씩 꺼내와 체크상태 변경
		var changeFlag = checkFlag == 'true'? false:true;
		for(i=0; i<zTreeNodesDept.length; i++) {
			if( checkerIdSet.has(zTreeNodesDept[i].userId) ) {
				zTreeNodesDept[i].checked = changeFlag;
			}
			if( zTreeNodesDept[i].checked ) {
				checkerPidSet.add( zTreeNodesDept[i].pId );
			}
		}
		for(i=0; i<zTreeNodesDept.length; i++) {
// 			console.log(zTreeNodesDept[i].userId);
			if(zTreeNodesDept[i].userId != "") {
				continue;
			}
			if( checkerPidSet.has( zTreeNodesDept[i].id) ) {
				zTreeNodesDept[i].checked = true;
			} else {
				zTreeNodesDept[i].checked = false;
			}
		}
		for(i=0; i<zTreeNodesTeam.length; i++) {
			if( checkerIdSet.has(zTreeNodesTeam[i].userId) ) {
				zTreeNodesTeam[i].checked = changeFlag;
			}
			if( zTreeNodesTeam[i].checked ) {
				checkerPidSet.add( zTreeNodesTeam[i].pId );
			}
		}
			console.log( zTreeNodesDept);
			console.log( zTreeNodesTeam);
		for(i=0; i<zTreeNodesTeam.length; i++) {
			if(zTreeNodesTeam[i].userId != "") {
				continue;
			}
			if( checkerPidSet.has( zTreeNodesTeam[i].id) ) {
				zTreeNodesTeam[i].checked = true;
			} else {
				zTreeNodesTeam[i].checked = false;
			}
		}
// 		console.log(zTreeNodesDept);
// 		console.log(zTreeNodesTeam);
		setTimeout(function() {
			//다시 그리기
			drawOrg(zTreeNodesDept,$("#treeDept"));
			drawOrg(zTreeNodesTeam,$("#treeTeam"));
			//체크박스에 name="id" 설정
			idExtractor();
			//체크박스에 이벤트 추가
			addOnclickEventToCheckBox();
			//리스트 동기화
			checkCheckBox();
		}, 100);
	}
</script>

<script>
	function transferUserList() {
		if (!opener) {
			alertA("현재 창은 만료된 창입니다. 창을 닫습니다.", "warning",
					closeWindow);
			return;
		}
		if (!opener.updateUserList) {
			alertA("오류! 창을 닫습니다.", "warning", closeWindow);
			return;
		}
		opener.updateUserList(userTransList);
		closeWindow();
	}
	function closeWindow() {
		window.close();
	}
</script>
</body>