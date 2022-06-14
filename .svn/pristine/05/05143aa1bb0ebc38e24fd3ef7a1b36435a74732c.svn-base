<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<link href="<%=request.getContextPath() %>/resources/css/jsgantt.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cloud/cloud.css" />
	<script src="<%=request.getContextPath() %>/resources/js/jsgantt.js" type="text/javascript"></script>
</head>
<style>

</style>
<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prj.prjNm }</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prj.prjNm }</a></li>
					<li class="breadcrumb-item active">간트 차트</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content" style="min-height: 700px;">
	<div class="card card-secondary card-outline">
	
	<!-- search bar -->
			<div  class="card-header with-border ">
				<button type="button" class="btn btn-primary" onclick="OpenWindow('/pms/project/work/registForm.open?open=1','일감등록',800,800);">일감등록</button>
			  <div class="input-group mt-2">
				<a class="btn btn-sm btn-outline-secondary ml-2 mb-1 mt-1" href="javascript:gantt_go();" ><i class="fa-solid fa-arrows-rotate"></i> </a>
				<a class='btn btn-sm btn-outline-secondary ml-2 mb-1 mt-1 todayLine' href="javascript:scrollToLine();" ><i class="fa-solid fa-hand-point-right"></i> 오늘 위치로 스크롤 이동</a>
			  <div class="col-sm-9 row">
				<label for="worker" class="col-md-0 ml-2 mt-2">담당자</label>
				<div class="col-sm-3">
					<select name="worker" class="form-control" onchange="gantt_go();">
						<option value="">전체</option>
					  <c:forEach var="prjUser" items="${prjUserList}">
						<option value="${prjUser.userId }">${prjUser.userNm }</option>
					  </c:forEach>
					</select>
				</div>
				<label for="sttCode" class="col-md-0 ml-2 mt-2">상 태</label>
				<div class="col-sm-2">
					<select name="sttCode" class="form-control" onchange="gantt_go();">
						<option value="">전체</option>
					  <c:forEach var="workStt" items="${workSttList}">
						<option value="${workStt.code }">${workStt.nm }</option>
					  </c:forEach>
					</select>
				</div>
				<label for="ipcrCode" class="col-sm-0 ml-2 mt-2">우선순위</label>
				<div class="col-sm-2">
					<select name="ipcrCode" class="form-control" onchange="gantt_go();">
						<option value="">전체</option>
					  <c:forEach var="ipcr" items="${ipcrList}">
						<option value="${ipcr.code }">${ipcr.nm }</option>
					  </c:forEach>
					</select>
				</div>
			  </div>
			  </div>

			</div>
			<div style="position:relative" class="gantt" id="GanttChartDIV"></div>
			<br/>
			<p style="margin: 0; color: #999"><i> Powered by </i><a href="http://www.jsgantt.com"><i>jsGantt</i></a>, <a href="https://jsganttimproved.github.io/jsgantt-improved/"><i>jsgantt-improved</i></a></p>
		</div>
</section>
<div class="rightmenu_layer rightmenu_on" style="left: -9999px; top: -99999px; position: absolute; z-index: 1000; visibility: hidden;">
	<ul class="default min_right_ly">
		<li><a class="registIssue" href="#" style="text-indent: 0px;">이슈 등록</a></li>
		<li><a class="goIssue" href="#" style="text-indent: 0px;">이슈 목록보기</a></li>
	</ul>
</div>

<script>
var g = new JSGantt.GanttChart(document.getElementById('GanttChartDIV'), 'day');

g.setOptions({
  vCaptionType: 'Complete',  // Set to Show Caption : None,Caption,Resource,Duration,Complete,     
  vQuarterColWidth: 36,
  vDateTaskDisplayFormat: 'yyyy-mm-dd ( day )', // Shown in tool tip box
  vDayMajorDateDisplayFormat: 'yyyy - mm - ww weeks ',// Set format to dates in the "Major" header of the "Day" view
  vWeekMinorDateDisplayFormat: 'mon dd', // Set format to display dates in the "Minor" header of the "Week" view
  vLang: 'ko',
  vShowTaskInfoLink: 1, // Show link in tool tip (0/1)
  vShowEndWeekDate: 0,  // Show/Hide the date for the last day of the week in header for daily
  vFormatArr: ['Day', 'Week', 'Month', 'Quarter'], // Even with setUseSingleCell using Hour format on such a large chart can cause issues in some browsers,
  
});

// Load from a Json url
</script>

<script>

<%-- 	JSGantt.parseJSON("<%=request.getContextPath()%>/resources/json/gantt.json", g); --%>

</script>

<script>
// 	    async : true, // 비동기화 여부 (default : true)
function gantt_go(){
	var gantt = document.getElementById('GanttChartDIV')
	var worker = $("select[name='worker']").val();
	var ipcrCode = $("select[name='ipcrCode']").val();
	var sttCode = $("select[name='sttCode']").val();
 	$.ajax({
	    type : 'post', // 타입 (get, post, put 등등)
// 	    async : false, // 비동기화 여부 (default : true)
	    url : 'list.do', // 요청할 서버url
	    dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
	    data : { // 보낼 데이터 (Object , String, Array)
	      "worker" : worker,
	      "ipcrCode" : ipcrCode,
	      "sttCode" : sttCode
	    },
	    success : function(result) { // 결과 성공 콜백함수
			document.getElementById('GanttChartDIV').innerHTML = "";
	    	g.vTaskList = [];
	        JSGantt.parseJSONString( JSON.stringify(result) , g);
	    	g.Draw();	
	    	//스크롤 이동
	    	scrollToLine();
	    	createQuickBtn();
// 	    	showMenuInGtask();
	    	addEventOfSettingVaueWhenClick();
	    	HideLoading();
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	        console.log(error)
	        HideLoading();
	        alertE("간트차트를 불러오는데 실패했습니다.")
	    }
	});
	ShowLoading();
} 
</script>
<script>
//현재날짜 위치로 이동
function scrollToLine(){
	var gchartbody = document.querySelector("div#GanttChartDIVgchartbody");
	var line = document.querySelector("#GanttChartDIVline1");
	var client = gchartbody .clientWidth;
	gchartbody.scrollTo(line.offsetLeft-client/2,0)
}
</script>
<script>
function scrollToWork(target) {
	var gchartbody = document.querySelector("div#GanttChartDIVgchartbody");
	var client = gchartbody .clientWidth;
	gchartbody.scrollTo(target-client/2,0)
// 	console.log(target);
}
</script>

<script>
//해당 일감 막대 위치로 이동하는 버튼 생성
var quickBtns = {};
var workbar = {};
function createQuickBtn(){
	var workNames = document.querySelectorAll(".gname a.workName");
// 		console.log(workNames);
	for (let workName of workNames) {
		var id = workName.closest("tr").id;
		quickBtns[id] = document.createElement('a');
		workName.parentElement.appendChild(quickBtns[id] );
		quickBtns[id].innerHTML = "&nbsp&nbsp&nbsp<i class='fa-solid fa-hand-point-right'></i>";
		// 간트 막대
// 		console.log(id);
		var barId = id.replace("child","bardiv")
		
// 		console.log(id.replace("child","bardiv"));
		workbar[id] = document.querySelector("#"+barId);
		workbar[id].setAttribute("data-workNo", workName.id);
		workbar[id].setAttribute("data-workName", workName.dataset.workname);
		console.log(workName.dataset.workname);
		
// 		console.log(workbar[id]);
		// 간트 막대 좌표 계산
		var target = workbar[id].offsetLeft + (workbar[id].clientWidth)/2;
// 		console.log(target);
		// 계산값 세팅
		var quickId = id+"Quick"
// 		console.log(quickId);
		quickBtns[id].setAttribute('href','javascript:scrollToWork('+target+');');
		quickBtns[id].setAttribute("class",quickId);
	}
}
</script>

<script>
window.addEventListener('DOMContentLoaded', gantt_go);
var idValueForClickEvent ="";

//더블 클릭시 그 위치로 스크롤되는 기능 
function addEventOfSettingVaueWhenClick() {
	var gtaskcell = document.querySelectorAll(".gtaskcell,.gtaskcellwkend,.gtaskbarcontainer");
	for(var i=1; i<gtaskcell.length; i++) {
		gtaskcell[i].addEventListener('dblclick', function(e) {
			var target = e.target.offsetLeft + (e.target.clientWidth)/2;
			scrollToWork(target);
		});
	}
}
</script>
<script>
	var gantt = document.querySelector(".gantt");
	var section_content = document.querySelector("section.content");
	var body_table = document.querySelector("table");
	var rightmenu_on = document.querySelector(".rightmenu_on");
	var rightmenuFlag = false;
	
	// gantt에 이벤트 달쟈
	gantt.addEventListener("contextmenu", function (e) {
		e.preventDefault();
		displayRightMenu(e, rightmenuFlag);
	});
	// 우클릭 menu 지우기
	gantt.addEventListener("click", function(e) {
		if (rightmenu_on.style.visibility != "hidden") {
			rightmenu_on.style.visibility = "hidden";
		}
	});
	
	// 우클릭 화면 위치 잡기
	function displayRightMenu(e, flag) {
		if (e.target.tagName != "DIV") {
			$(".rightmenu_on").css({
				"visibility" : "hidden",
				"top" : "-9999px",
				"left" : "-9999px"
			});
			rightmenuFlag = false;
		}
		
		setHrefInfo(e.target);
		
		let rect = e.target.getBoundingClientRect();
		let section_rect = section_content.getBoundingClientRect();
		let x = rect.left - section_rect.left + e.offsetX;
		let y = e.pageY;
		
		if (!flag) {
			$(".rightmenu_on").css({
		        visibility: "visible",
		        top: y,
		        left: x
	        });
			rightmenuFlag = true;
		} else {
			$(".rightmenu_on").css({
				"visibility" : "hidden",
				"top" : "-9999px",
				"left" : "-9999px"
			});
			rightmenuFlag = false;
		}
	}
	// 우클릭 시 우클릭 메뉴 값 세팅
	function setHrefInfo(eventTarget) {
		let goIssueList = document.querySelector(".goIssue");
		let registIssueGo = document.querySelector(".registIssue");
		
		let target = eventTarget.closest(".gtaskbarcontainer");
		
		let keyword = target.dataset.workname;
		let workNo = target.dataset.workno;
		
		console.log(keyword);
		console.log(workNo);
		
		let href = location.href;
		href = href.substring(0, href.indexOf("gantt"));
		
		let issueListUrl = href + "/issue/list.do?page=1&sttCode=&gradeCode=&searchType=n&keyword=" + workNo;
		let issueRegistUrl = href + "/issue/registForm.do?workNo=" + workNo;
		 
		goIssueList.setAttribute("href", issueListUrl);
		registIssueGo.setAttribute("href", issueRegistUrl);
	}
</script>

</body>