<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="prjList" value="${dataMap.prjList }" />

<head>

<style>
th {
 text-align: center;
}
</style>

</head>
<title>프로젝트 목록</title>
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
					<li class="breadcrumb-item active">목록</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-info card-outline">
		<div class="card-header with-border">
			<button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/prjSetting/registForm.do'">생 성</button>
			<div id="keyword" class="card-tools" style="width: 700px;">
				<div class="input-group row" style="top: 5px">
					<!-- search bar -->

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
						<c:forEach items="${prjSttList}" var="prjStt">
							<option value="${prjStt.code}" ${param.sttCode eq prjStt.code ? 'selected' : ''}>${prjStt.nm }</option>
						</c:forEach>
						
					</select>
					<select class="form-control col-md-2" name="searchType" id="searchType">
						<option value="">검색구분</option>
						<option value="nm" ${param.searchType eq 'nm' ? 'selected' : ''}>팀명</option>
						<option value="l" ${param.searchType eq 'l' ? 'selected' : ''}>팀장명</option>
					</select>

					<!-- keyword -->
					<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." onkeyup="enterkey()" value="${cri.keyword }" />
					<span class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span> <!-- end : search bar -->
				</div>
			</div>
		</div>
	
	
		<div class="card-body">
			<section class="content">
				<div class="card">
					<div class="card-body p-0">
						<table class="table table-striped projects">
							<thead>
								<tr>
									<th style="width:5%">#</th>
									<th style="width:8%">상태</th>
									<th>이름</th>
<!-- 									<th style="width: 10%">리더</th> -->
									<th style="width:30%">구성원</th>
									<th style="width:15%">진척도</th>
									<th style="width:18%">기간</th>
									<th style="width:10%"></th>
								</tr>
							</thead>
							
							<tbody>
							
								<c:if test="${empty prjList }">
									<tr>
			     						<td colspan="7" class="text-center">
			     							해당 내용이 없습니다.
			     						</td>
			     					</tr>
								</c:if>
								
								<c:if test="${!empty prjList }">
									<c:forEach items="${prjList }" var="prj" varStatus="status">
										<tr data-prjNo="${prj.prjNo}" style="cursor:pointer">
											<!-- 프로젝트 번호 -->
											<td>${status.count}</td>
											
											<!-- status Code -->
											<td class="project-state">
												<c:choose>
													<c:when test="${prj.sttCode eq 'p'}"><span class="badge badge-primary">진행</span></c:when>
													<c:when test="${prj.sttCode eq 'h'}"><span class="badge badge-secondary">보류</span></c:when>
													<c:when test="${prj.sttCode eq 'c'}"><span class="badge badge-success">완료</span></c:when>
												</c:choose>
											</td>
											
											<!-- 프로젝트명, regDt -->
											<td>
												<a href="<%=request.getContextPath()%>/project/home.do?from=list&prjNo=${prj.prjNo}"> ${prj.prjNm}&nbsp;</a> 
												<br> 
												<small><fmt:formatDate value="${prj.regDt }" pattern="yyyy-MM-dd" /></small>
											</td>
											
											<!-- 프로젝트리더 -->
<!-- 											<td> -->
<!-- 												<a href="#">   -->
<%-- 													<c:if test="${!empty prj.prjUserList }"> --%>
<%-- 					     								<c:forEach items="${prj.prjUserList }" var="prjUser"> --%>
<%-- 					     									<c:if test="${prjUser.leader eq 'y' }">${prjUser.userNm }</c:if> --%>
<%-- 					     								</c:forEach> --%>
<%-- 													</c:if> --%>
<!-- 												</a>  -->
<!-- 											</td> -->
											
											<!-- Users -->
											<c:if test="${empty prj.prjUserList }">
					     						<td colspan="1">
					     							냉무.
					     						</td>
											</c:if>
											<c:if test="${!empty prj.prjUserList }">
												<td>
													<ul class="list-inline">
													
														<c:if test="${prj.prjUserList.size() > 5 }">
															<c:forEach items="${prj.prjUserList }" var="prjUser" end="4">
																<li class="list-inline-item">
																	<c:choose>
																		<c:when test="${prjUser.prjRole eq 'PM' or prjUser.prjRole eq 'PL'}">
																			<span class="img-circle img-bordered-sm manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 	title="${prjUser.userNm } - <c:forEach items="${prjRoleList }" var="prjRole">${prjRole.code eq prjUser.prjRole ? prjRole.nm : '' }</c:forEach>" ></span>
																		</c:when>
																		<c:otherwise>
																			<span class="img-circle manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 	title="${prjUser.userNm } - <c:forEach items="${prjRoleList }" var="prjRole">${prjRole.code eq prjUser.prjRole ? prjRole.nm : '' }</c:forEach>" ></span>
																		</c:otherwise>
																	</c:choose>
																</li>
															</c:forEach>
															&nbsp;..외 총 ${prj.prjUserList.size() }명
														</c:if>
														<c:if test="${prj.prjUserList.size() <= 5 }">
															<c:forEach items="${prj.prjUserList }" var="prjUser">
																<li class="list-inline-item">
																	<c:choose>
																		<c:when test="${prjUser.prjRole eq 'PM' or prjUser.prjRole eq 'PL'}">
																			<span class="img-circle img-bordered-sm manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 	title="${prjUser.userNm } - <c:forEach items="${prjRoleList }" var="prjRole">${prjRole.code eq prjUser.prjRole ? prjRole.nm : '' }</c:forEach>" ></span>
																		</c:when>
																		<c:otherwise>
																			<span class="img-circle manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 	title="${prjUser.userNm } - <c:forEach items="${prjRoleList }" var="prjRole">${prjRole.code eq prjUser.prjRole ? prjRole.nm : '' }</c:forEach>" ></span>
																		</c:otherwise>
																	</c:choose>
																</li>
															</c:forEach>
														</c:if>
													</ul>
												</td>
											</c:if>
											
											<td class="project_progress">
												<div class="progress progress-sm">
													<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${prj.prjRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${prj.prjRate }%"></div>
												</div>
												<small> ${prj.prjRate }% Complete </small>
											</td>
											
											<td class="text-center">
												<a><fmt:formatDate value="${prj.bgnDt }" pattern="yyyy-MM-dd" />&nbsp;~&nbsp;<fmt:formatDate value="${prj.endDt }" pattern="yyyy-MM-dd" /></a>
											</td>
											<td class="project-actions text-right">
<!-- 												<a class="btn btn-primary btn-sm" href="#"> <i class="fas fa-folder"> </i> View </a> -->
												<a class="btn btn-info btn-sm" href="<%=request.getContextPath() %>/prjSetting/modifyForm.do?prjNo=${prj.prjNo }"> <i class="fa-solid fa-gear"></i> </a>
												<a class="btn btn-danger btn-sm"> <i class="fa-solid fa-trash-can" onclick="remove_go('remove.do', '${prj.prjNo}');"></i> </a>
											</td>
										</tr>
										
									</c:forEach>
								</c:if>
							
							
							</tbody>	
						</table>
					</div>
				</div>
			</section><!-- content end -->
		</div><!-- card-body -->
		
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
	
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>

<script>
function list_go(page, url) { 
	if (!url) url = "list.do";
	var jobForm = $('#jobForm');
	
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	
	jobForm.find("[name='sttCode']").val($('select[name="sttCode"]').val());
	
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	
	jobForm.attr({
		action : url,
		method : 'get'
	}).submit();
}

function enterkey() {
	if (window.event.keyCode == 13) {
		list_go(1);
    }
}
</script>



<script>
window.onload = function() {
	
	MemberPictureThumb('<%=request.getContextPath()%>');
	
	<c:if test="${from eq 'regist' }">
		Swal.fire({
			text: '등록되었습니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>
		
	<c:if test="${from eq 'modify' }">
		Swal.fire({
			text: '정상적으로 수정되었습니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>

	<c:if test="${from eq 'remove' }">
		Swal.fire({
			text: '삭제되었습니다.\n팀 목록으로 이동합니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>	

	
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
	
	// 이미지 툴팁.
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	});
	
}
</script>

<script>
	function modifyForm_go(url, prjNo) {
		location.href = url + "?prjNo=" + prjNo;
	}
	
	function remove_go(url, prjNo) {
		/* 스윗얼럿 */
		Swal.fire({
			text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
			icon: 'warning',
			confirmButtonColor: '#007bff',
			cancelButtonColor: '#dc3545',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소',
		}).then((result) => {
			/* Read more about isConfirmed, isDenied below */
			if (result.isConfirmed) {
				location.href = url + "?prjNo=" + prjNo;
			}
		});
	}
	
	// 프로젝트 디테일 이벤트
	 let project_table = document.querySelector(".projects");
	 project_table.addEventListener("click", function (e) {
		 
		 if (e.target.tagName == "TD") {
			 projectDetail(e.target.parentElement);
		 }
	 });
	function projectDetail(target) {
		let prjNo = target.dataset.prjno;
		location.href='<%=request.getContextPath()%>/project/home.do?prjNo=' + prjNo;
		
	}
	
</script>

</body>