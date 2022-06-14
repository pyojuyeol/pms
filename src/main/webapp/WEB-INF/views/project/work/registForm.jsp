<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

<style>
</style>

</head>
<title>일감 생성</title>
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
					<li class="breadcrumb-item"><a href="#">일감</a></li>
					<li class="breadcrumb-item active">생성</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-outline card-info">
	
		<div class="card-header">
			<h3 class="card-title p-2"></h3>
			<div class="card-tools">
				<button type="button" class="btn btn-outline-success mr-1" id="sampleBtn" onclick="inputSampleText(textObj);">샘 플</button>
				<button type="button" class="btn btn-primary mr-1" id="registBtn" onclick="regist_go();">생 성</button>
			  <c:if test="${empty open }">
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'">취 소</button>
			  </c:if>
			  <c:if test="${not empty open }">
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='javascript:window.close();'">닫 기</button>
			  </c:if>
			</div>
		</div> <!-- card-header End -->
		
		<div class="card-body pad">
			<form role="form" method="post" action="regist.do<c:if test="${!empty subUpWork}">?from=subWork</c:if>" name="registForm">
				<input type="hidden" id="prjNo" name="prjNo" value="${prj.prjNo }">
				<input type="hidden" id="open" name="open" value="${open }">
				<input type="hidden" id="workUrl" name="workUrl" value="/project/work/detail.do?workNo=">

				<div class="form-group">
					<label for="workTitle">제목</label>
					<input type="text" id="workTitle" name='workTitle' class="form-control" placeholder="제목을 쓰세요">
				</div>

				<!-- 담당자 select 선택 시 나타나는 option은 PL에 따라 다르게 나타남 -->
				<div class="row">
					<div class="form-group col-md-4">
						<label for="worker">담당자</label>
						<select id="worker" name="worker" class="form-control custom-select">
							<c:forEach items="${prj.prjUserList }" var="prjUser">
								<option value="${prjUser.userId }">${prjUser.userNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="sttCode">상태</label>
						<select id="sttCode" name="sttCode" class="form-control custom-select">
							<c:forEach items="${workSttList }" var="workStt">
								<option value="${workStt.code }">${workStt.nm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="ipcrCode">우선순위</label> 
						<select id="ipcrCode" name="ipcrCode" class="form-control custom-select">
							<c:forEach items="${ipcrList }" var="ipcr">
								<option value="${ipcr.code }">${ipcr.nm }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-4">
						<label>기간</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="far fa-calendar-alt"></i> </span>
							</div>
							<input type="text" class="form-control float-right" id="rangeDt" name="rangeDt">
						</div>
					</div>
					<div class="form-group col-md-4">
						<label for="upWork">상위일감</label>
						<input type="text" list="upWorkList" class="form-control" id="upWork" name="upWork" value="${subUpWork }" />
						<datalist id="upWorkList">
							<c:forEach items="${workList }" var="work">
								<option value="${work.workNo }">${work.workTitle }</option>
							</c:forEach>
						</datalist>
					</div>
					<div class="form-group col-md-4">
						<label for="workRate" class="float-left">진척도&nbsp; : </label>&nbsp;&nbsp;<span id="workRateOut">0%</span>
						<input type="range" id="workRate" name="workRate" value="0" class="form-range float-left" min="0" max="100" step="10"
							   oninput="document.getElementById('workRateOut').innerHTML=this.value + '%';" style="width:200px; clear:both;"/>
					</div>
				</div>

				<div class="row">
				</div>

				<div class="form-group">
<!-- 					<label for="workComment">내 용</label> -->
					<textarea class="textarea" name="workComment" id="workComment" rows="20" cols="70" placeholder="1000자 내외로 작성하세요."></textarea>
				</div>
			</form>
		</div> <!-- card-body End -->
	</div> <!-- card outline End -->
</section>


<script>
let textObj = {
	"workTitle":"샘플 버튼 기능",
	"worker":"20220003",
	"sttCode":"r",
	"ipcrCode":"e",
	"rangeDt":"2022-05-20 ~ 2022-05-30",
	"workComment":"발표 시간 절약과 효율을 위한 샘플 데이터"
};
</script>


<script>
	window.onload = function() {
		summernote_go($('#workComment'), '<%=request.getContextPath()%>');
		
	    //Date range picker
		moment.locale('ko'); // 언어를 한국어로 설정함
	    $('#rangeDt').daterangepicker({
		    "minDate": "<fmt:formatDate value='${prj.bgnDt}' pattern='yyyy/MM/dd'/>",
		    "maxDate": "<fmt:formatDate value='${prj.endDt}' pattern='yyyy/MM/dd'/>",
	        locale :{ 
		        format: 'YYYY-MM-DD',
		        applyLabel: "적용",
		        cancelLabel: "닫기",
		        separator: ' ~ ',
		     	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		   		daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
	        }
	    });
		
	}
</script>

<script>	
	function regist_go() {
// 		alert("regist btn click !");

		if ($("input[name='workTitle']").val() == "") {  // form.title.value
			alert("제목은 필수입니다.");
			$("input[name='workTitle']").focus();
			return;
		}
		
		$("form[role='form']").submit();
	}
</script>

</body>
