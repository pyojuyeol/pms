<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="workList" value="${dataMap.myWorkList }" />

<head>
<!-- 필요 리소스 추가 -->
</head>
<title>내 일감</title>
<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1></h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="<%=request.getContextPath() %>/dashboard.do">Home</a></li>
					<li class="breadcrumb-item"><a href="#">마이페이지</a></li>
					<li class="breadcrumb-item active">내 일감</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-primary card-outline">
		
		<div class="card-header">
			<h3 class="card-title p-2">내 일감 목록</h3>
			<div id="keyword" class="card-tools" style="width: 650px;">
				<div class="input-group row" style="top: 5px">
					<!-- sort num -->
					<select class="form-control col-md-2" name="perPageNum" id="perPageNum" onchange="list_go(1);">
						<option value="10" >정렬개수</option>
						<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
						<option value="30" ${cri.perPageNum == 30 ? 'selected':''}>30개씩</option>
						<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
					</select>
					
					<!-- search bar -->
					<select class="form-control col-md-2" name="sttCode" id="sttCode" onchange="list_go(1);">
						<option value="">상태</option>
						<c:forEach items="${workSttList}" var="workStt">
							<option value="${workStt.code}" ${param.sttCode eq workStt.code ? 'selected' : ''}>${workStt.nm }</option>
						</c:forEach>
					</select>
					<select class="form-control col-md-2" name="ipcrCode" id="ipcrCode" onchange="list_go(1);">
						<option value="">우선순위</option>
						<c:forEach items="${ipcrList}" var="ipcr">
							<option value="${ipcr.code}" ${param.ipcrCode eq ipcr.code ? 'selected' : ''}>${ipcr.nm }</option>
						</c:forEach>
					</select>
					<select class="form-control col-md-2" name="searchType" id="searchType">
							<option value="t" ${param.searchType eq 't' ? 'selected' : ''}>제목</option>
						<option value="c" ${param.searchType eq 'c' ? 'selected' : ''}>내용</option>
						<option value="uno" ${param.searchType eq 'uno' ? 'selected' : ''}>상위일감</option>
					</select>

					<!-- keyword -->
					<input class="form-control col-md-4" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }" />
					<span class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span> <!-- end : search bar -->
				</div>
			</div>
		</div>
		<div class="card-body" style="text-align: center;">
			<div class="row">
				<div class="col-sm-12">
					<table class="table table-bordered table-hover text-nowrap">
						<thead>
							<tr >
								<th style="width: 5%">#</th>
								<th style="width: 7%">일감번호</th>
								<th style="width: 7%">상태</th>
								<th style="width: 7%">우선순위</th>
								<th>제목</th>
<!-- 								<th style="width: 7%">담당자</th> -->
								<th style="width: 9%">시작일자</th>
								<th style="width: 9%">종료일자</th>
								<th style="width: 12%">진척도</th>
								<th style="width: 7%">상위일감</th>
								<th>프로젝트명</th>
							</tr>
						</thead>
						
						<tbody>
							
							<c:if test="${empty myWorkList}">
								<tr>
		     						<td colspan="9" class="text-center">
		     							내 일감이 존재하지 않습니다.
		     						</td>
		     					</tr>
							</c:if>
							
							<c:if test="${!empty myWorkList}">
								<c:forEach items="${myWorkList }" var="myWork" varStatus="status">
								
								<jsp:useBean id="now" class="java.util.Date" />
								<fmt:formatDate value="${myWork.endDt}" pattern="yyyy-MM-dd" var="endDate" />
								<fmt:parseDate value="${endDate }" pattern="yyyy-MM-dd" var="endDateT"/>
								<fmt:parseNumber value="${now.time / (1000*60*60*24) }" var="todayTime" integerOnly="true" />
								<fmt:parseNumber value="${endDateT.time / (1000*60*60*24) }" var="endDateTime" integerOnly="true" />
								
								<tr <c:if test="${endDateTime - todayTime <= 7 and endDateTime - todayTime > 3 and myWork.sttCode != 'c'}"> class="table-warning"</c:if>
									<c:if test="${endDateTime - todayTime <= 3 and myWork.sttCode != 'c'}"> class="table-danger"</c:if>
									onclick="location.href='<%=request.getContextPath()%>/project/work/detail.do?workNo=${myWork.workNo }';" style="cursor: pointer;">
									
									<!-- 목록 카운트 -->
									<td>${status.count }</td>
									<!-- 일감번호 -->
									<td>#${myWork.workNo }</td>
									<!-- 상태 -->
									<td style="padding:5px; margin:0;">
										<c:choose>
											<c:when test="${myWork.sttCode eq 'r'}"><h5><span class="badge badge-pill badge-info">요청</span></h5></c:when>
											<c:when test="${myWork.sttCode eq 'p'}"><h5><span class="badge badge-pill badge-success">진행</span></h5></c:when>
											<c:when test="${myWork.sttCode eq 'f'}"><h5><span class="badge badge-pill badge-danger">피드백</span></h5></c:when>
											<c:when test="${myWork.sttCode eq 'c'}"><h5><span class="badge badge-pill badge-primary">완료</span></h5></c:when>
											<c:when test="${myWork.sttCode eq 'h'}"><h5><span class="badge badge-pill badge-secondary">보류</span></h5></c:when>
										</c:choose>
									</td>
									<!-- 우선순위 -->
									<td>
										<c:choose>
											<c:when test="${myWork.ipcrCode eq 'l'}"><i class="fa-solid fa-arrow-down"></i>&nbsp;낮음</c:when>
											<c:when test="${myWork.ipcrCode eq 'u'}"><i class="fa-solid fa-minus" style="color:green"></i>&nbsp;보통</c:when>
											<c:when test="${myWork.ipcrCode eq 'h'}"><i class="fa-solid fa-arrow-up" style="color:orange"></i>&nbsp;높음</c:when>
											<c:when test="${myWork.ipcrCode eq 'e'}"><i class="fa-solid fa-land-mine-on" style="color:red"></i>&nbsp;긴급</c:when>
										</c:choose>
									</td>
									<!-- 제목 -->
									<td style="text-align: left;">${myWork.workTitle }</td>
									<!-- 담당자명 -->
<%-- 									<td>${myWork.userNm }</td> --%>
									<!-- 시작일자 -->
									<td><fmt:formatDate value="${myWork.bgnDt }" pattern="yyyy-MM-dd" /></td>
									<!-- 종료일자 -->
									<td><fmt:formatDate value="${myWork.endDt }" pattern="yyyy-MM-dd" /></td>
									<!-- 진척도 -->
									<td class="project_progress">
										<div class="progress progress-sm">
											<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${myWork.workRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${myWork.workRate }%"></div>
										</div>
										<small> ${myWork.workRate }% Complete </small>
									</td>
									<td class="text-center">
										<c:if test="${myWork.upWork != 0}"> #${myWork.upWork}</c:if>
									</td>
									<td style="text-align:left;">${myWork.prjNm }</td>
								</tr>
								
								</c:forEach>
							</c:if>
							

						</tbody>
					</table>
				</div> <!-- col-sm-12 -->
			</div> <!-- row -->
		</div> <!-- card-body -->
		
		<!-- pagination -->
		<div class="card-footer"> 
			<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>
		</div>		
		
	</div>
</section>


<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type="hidden" name="perPageNum" value="" />
	
	<input type='hidden' name="sttCode" value="" />
	<input type='hidden' name="ipcrCode" value="" />
	
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>


<script type="text/javascript">
function list_go(page, url) { 
	if (!url) url = "list.do";
	var jobForm = $('#jobForm');
	
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	
	jobForm.find("[name='sttCode']").val($('select[name="sttCode"]').val());
	jobForm.find("[name='ipcrCode']").val($('select[name="ipcrCode"]').val());
	
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	
	jobForm.attr({
		action : url,
		method : 'get'
	}).submit();
}
</script>



<script>
function CloseWindow() {
	window.opener.location.reload(true);		
	window.close();
}

function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width=" + WinWidth + ", " 
							+"height=" + WinHeight + ", top=" + wintop + ", left=" 
							+ winleft + ", resizable=yes, status=yes");
	win.focus() ; 
}
</script>

</body>