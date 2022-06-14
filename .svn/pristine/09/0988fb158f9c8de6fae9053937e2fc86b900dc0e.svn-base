<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>

<style>
.table-woo td {
	border:1px solid #dee2e6;
}
.table-woo > td {
	border:1px solid #dee2e6;
}
</style>


<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/history/history.css" />

</head>
<title>일감 상세보기</title>

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
					<li class="breadcrumb-item active">상세보기</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
	
		<div class="card-header">
			<h3 class="card-title p-2"></h3>
			<div class="card-tools">
			  <c:if test="${empty open }">
				<button type="button" id="registIssueBtn" class="btn btn-primary" onclick="registIssue_go('${work.workNo}');">이슈 등록</button>
				<button type="button" id="modifyBtn" class="btn btn-warning" onclick="modifyForm_go();">설 정</button>
				<button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go();">삭 제 </button>
				<button type="button" id="listBtn" class="btn btn-secondary" onclick="location.href='list.do'">목 록</button> 
			  </c:if>
			  <c:if test="${not empty open }">
				<button type="button" id="registIssueBtn" class="btn btn-primary" onclick="registIssue_go('${work.workNo}');">이슈 등록</button>
				<button type="button" id="modifyBtn" class="btn btn-warning" onclick="modifyForm_go(1);">설 정</button>
				<button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go(1);">삭 제</button>
				<button type="button" id="listBtn" class="btn btn-secondary" onclick="location.href='javascript:window.close();'">닫 기</button>
			  </c:if>
			</div>
		</div><!-- card-header end -->	
	
		<div class="card-body">

			<table class="table table-bordered table-woo" style="height:150px; text-align:center; color: #505050">
				<COLGROUP style="width: 10%; background-color: #EEEEEE;">
				<COLGROUP style="width: 20%;">
				<COLGROUP style="width: 10%; background-color: #EEEEEE;">
				<COLGROUP style="width: 20%;">
				<COLGROUP style="width: 10%; background-color: #EEEEEE;">
				<COLGROUP style="width: 20%;">
				<tr>
					<td style="font-weight: bold;">제목</td>
					<td>${work.workTitle }</td>
					<td style="font-weight: bold;">담당자</td>
					<td>${work.userNm}</td>
					<td style="font-weight: bold;">시작일자</td>
					<td><fmt:formatDate value="${work.bgnDt }" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td style="font-weight: bold;">상태</td>
					<td>
						<c:choose>
							<c:when test="${work.sttCode eq 'r'}">요청</c:when>
							<c:when test="${work.sttCode eq 'p'}">진행</c:when>
							<c:when test="${work.sttCode eq 'f'}">피드백</c:when>
							<c:when test="${work.sttCode eq 'c'}">완료</c:when>
							<c:when test="${work.sttCode eq 'h'}">보류</c:when>
						</c:choose>
					</td>
					<td style="font-weight: bold;">우선순위</td>
					<td>
						<c:choose>
							<c:when test="${work.ipcrCode eq 'l'}">낮음</c:when>
							<c:when test="${work.ipcrCode eq 'u'}">보통</c:when>
							<c:when test="${work.ipcrCode eq 'h'}">높음</c:when>
							<c:when test="${work.ipcrCode eq 'e'}">긴급</c:when>
						</c:choose>
					</td>
					<td style="font-weight: bold;">종료일자</td>
					<td><fmt:formatDate value="${work.endDt }" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td style="font-weight: bold;">진척도</td>
					<td class="project_progress text-center" style="width:18%;' text-align: center;">
						<div class="progress progress-sm" style="width:75%; margin-top:7px; float:left;">
							<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${work.workRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${work.workRate }%"></div>
						</div>
						<span id="workRateOut" style="width:25%; float:left;">${work.workRate }%</span>
					</td> 
					<td style="font-weight: bold;">상위일감</td>
					<td>
						<c:if test="${work.upWork ne '0' }">
							#${work.upWork }
						</c:if>
					</td>
					<td style="font-weight: bold;">수정일시</td>
					<td><fmt:formatDate value="${work.updDt }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td style="font-weight: bold;">내용</td>
					<td colspan="5" style="text-align: left; vertical-align:center;">
						<div>${work.workComment }</div>
					</td>
				</tr>
			</table>


			<div class="form-group mt-4" >
				<label for="">하위일감</label>&nbsp;&nbsp;
				<button class="btn btn-xs btn-primary" onclick="addSubWork_go('${work.workNo}');" type="button" id="addUserBtn"><i class="fas fa-plus pt-1" style="cursor:pointer"></i></button>
				
				<!-- 하위 일감 목록 -->
				
				<c:if test="${!empty work.subWorkList }">
					<c:forEach items="${work.subWorkList }" var="subWork">
					
						<div class="m-2" style="">
							<table class="table table-hover text-nowrap" style="border:1px solid #dee2e6; margin-bottom: 0.5rem;">
								
								<jsp:useBean id="now" class="java.util.Date" />
								<fmt:formatDate value="${subWork.endDt}" pattern="yyyy-MM-dd" var="endDate" />
								<fmt:parseDate value="${endDate }" pattern="yyyy-MM-dd" var="endDateT"/>
								<fmt:parseNumber value="${now.time / (1000*60*60*24) }" var="todayTime" integerOnly="true" />
								<fmt:parseNumber value="${endDateT.time / (1000*60*60*24) }" var="endDateTime" integerOnly="true" />
							
								<tr onclick="location.href='<%=request.getContextPath()%>/project/work/detail.do?workNo=${subWork.workNo }';" style="cursor: pointer;"
									<c:if test="${endDateTime - todayTime <= 7 and endDateTime - todayTime > 3 and subWork.sttCode != 'c'}">class="table-warning"</c:if>
									<c:if test="${endDateTime - todayTime <= 3 and subWork.sttCode != 'c'}"> class="table-danger"</c:if>
								>
									<!-- 상태 -->
									<td style="width:12%;">
										<c:choose>
											<c:when test="${subWork.sttCode eq 'r'}"><h5><span class="badge badge-pill badge-info" style="margin-top : 10px;">요청</span></h5></c:when>
											<c:when test="${subWork.sttCode eq 'p'}"><h5><span class="badge badge-pill badge-success" style="margin-top : 10px;">진행</span></h5></c:when>
											<c:when test="${subWork.sttCode eq 'f'}"><h5><span class="badge badge-pill badge-danger" style="margin-top : 10px;">피드백</span></h5></c:when>
											<c:when test="${subWork.sttCode eq 'c'}"><h5><span class="badge badge-pill badge-primary" style="margin-top : 10px;">완료</span></h5></c:when>
											<c:when test="${subWork.sttCode eq 'h'}"><h5><span class="badge badge-pill badge-secondary" style="margin-top : 10px;">보류</span></h5></c:when>
										</c:choose>
									</td>
									<td >
										${subWork.workTitle }
									</td>
									
									<!-- 우선순위 -->
									<td style="width:5%">
										<c:choose>
											<c:when test="${subWork.ipcrCode eq 'l'}"><i class="fa-solid fa-arrow-down"></i></c:when>
											<c:when test="${subWork.ipcrCode eq 'u'}"><i class="fa-solid fa-minus" style="color:green"></i></c:when>
											<c:when test="${subWork.ipcrCode eq 'h'}"><i class="fa-solid fa-arrow-up" style="color:orange"></i></c:when>
											<c:when test="${subWork.ipcrCode eq 'e'}"><i class="fa-solid fa-land-mine-on" style="color:red"></i></c:when>
										</c:choose>
									</td>
									<!-- 종료일자 -->
									<td style="width:10%">
										<fmt:formatDate value="${subWork.endDt }" pattern="yyyy-MM-dd" />
									</td>
									<!-- 진척도 -->
									<td class="project_progress text-center" style="width:18% ">
										<div class="progress progress-sm ">
											<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${subWork.workRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${subWork.workRate }%"></div>
										</div>
<!-- 										<div> -->
<%-- 											<small> ${subWork.workRate }%</small> --%>
<!-- 										</div> -->
									</td> 
									<td class="p-0 m-0" style="width:5%">${subWork.workRate }%</td>
									<td style="width:10%">
										<img class="img-square manPicture" data-id="${subWork.worker }" style="display: block; width: 38px; height: 38px; margin: 0 auto;"></img>
									</td>
								</tr>
							</table>
						</div>
						
					</c:forEach>	
				</c:if>

			</div>
		</div><!-- card body end -->

		<!-- Comments -->
		<div class="card-footer card-comments">
			<div id="repliesDiv">
				<!-- Comment List -->
				
				
			</div>
		</div>
		<!-- Comment Write -->
		<div class="card-footer d-flex">
			<span class="img-circle manPicture mr-2" data-id="${loginUser.userId }" style="display:block; width:35px; height:35px;"></span>
			<div class="input-group">
				<input type="text" id="newReplyText" class="form-control" onkeyup="enterkey()" placeholder="댓글을 입력하세요.">
				<button type="button" id="replyAddBtn" class="btn btn-primary" onclick="replyRegist_go('${work.workNo}');">등록</button>
			</div>
		</div>
		<!-- Comments Pagination -->
		<div class='text-center mt-3'>
			<ul id="pagination" class="pagination justify-content-center m-0" > 
			</ul>
		</div>
		<!-- Comments End -->

		<hr>

		<!-- history -->
		<div class="row ml-2">
			<div class="col-md-12">
			
				<c:if test="${empty work.workLogList }">
					<h5 style="margin-left:10px; margin-bottom:15px;">일감 변경 내역이 없습니다.</h5>
			    </c:if>

				<c:if test="${!empty work.workLogList }">
					<div class="timeline">
						<div class="entry">
							<c:set var="logDate" value="0"></c:set>
							<c:forEach items="${work.workLogList }" var="workLog" varStatus="status">
								<div class="historyForm">
									<div class="title">
										<fmt:formatDate var="logDate1" value="${workLog.updDt }" pattern="yyyy-MM-dd" />
										<c:if test="${logDate ne logDate1}">
											<h3 class="">
												<fmt:formatDate value="${workLog.updDt }" pattern="yyyy-MM-dd" />
											</h3>
											<fmt:formatDate var="logDate" value="${workLog.updDt }" pattern="yyyy-MM-dd" />
										</c:if>

									</div>
									<div class="body">
										<span><fmt:formatDate value="${workLog.updDt }" pattern="HH:mm" /></span>
										<span style="margin-left: 20px;">
											<a href=""> 
												<strong>${workLog.userNm }</strong>님이 
												<strong>${workLog.what}</strong>을(를) 
												<em>${workLog.before}</em> 에서 
												<em>${workLog.after}</em> (으)로 변경
											</a>
										</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>

			</div>
		</div><!-- history body end -->
		
		
	</div><!-- card outline end -->
</section><!-- content end -->


<!-- Reply Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" style="display: none;"></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" data-replySeq>
				<p><input type="text" id="content" class="form-control"></p>
			</div>
			<div class="modal-footer">
				<c:if test="${loginUser.userId eq work.workRegister }">
				</c:if>
				<button type="button" class="btn btn-info" id="replyModBtn" onclick="replyModify_go();">변 경</button>
				<button type="button" class="btn btn-danger" id="replyDelBtn" onclick="replyRemove_go();">삭 제</button>
				<button type="button" class="btn btn-warning" data-dismiss="modal">취 소</button>
			</div>
		</div>
	</div>
</div>


<form role="form">
	<input type="hidden" name="workNo" value="${work.workNo }" />
</form>

<%@ include file="./reply_js.jsp" %>

<script>
	function modifyForm_go(key){
		// alert("modify btn click !")
		location.href= "<%=request.getContextPath() %>/project/work/modifyForm." + GetReqStr(1) + "workNo=${work.workNo}"
	}
	
	function remove_go(key){
		// alert("remove btn click !")
		location.href= "<%=request.getContextPath() %>/project/work/remove." + GetReqStr(1) + "workNo=${work.workNo}"
	}
	
	function enterkey() {
		if (window.event.keyCode == 13) {
			replyRegist_go('${work.workNo}');
	    }
	}
	
	function addSubWork_go(subUpWork) {
		location.href= "<%=request.getContextPath() %>/project/work/registForm." + GetReqStr(1) + "subUpWork=" + subUpWork
	}
	function registIssue_go(workNo) {
		location.href= "<%=request.getContextPath() %>/project/issue/registForm." + GetReqStr(1) + "workNo=" + workNo	
	}
</script>

</body>