<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="teamList" value="${dataMap.teamList }" />

<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/modal.css" />

</head>
<title>팀 목록</title>
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
					<li class="breadcrumb-item"><a href="#">팀</a></li>
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
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registModal">생 성</button>
			<div id="keyword" class="card-tools" style="width: 700px;">
				<div class="input-group row" style="top: 5px">
					<!-- search bar -->
					
					<div class="col-4">
					</div>
					<!-- sort num -->
					<select class="form-control col-md-2" name="perPageNum" id="perPageNum" onchange="list_go(1);">
						<option value="10" >정렬개수</option>
						<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
						<option value="30" ${cri.perPageNum == 30 ? 'selected':''}>30개씩</option>
						<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
					</select>
					<select class="form-control col-md-2" name="searchType" id="searchType">
						<option value="tnm" ${param.searchType eq 'tnm' ? 'selected' : ''}>이름</option>
						<option value="unm" ${param.searchType eq 'unm' ? 'selected' : ''}>팀장</option>
					</select>

					<!-- keyword -->
					<input class="form-control col-md-4" type="text" name="keyword" placeholder="검색어를 입력하세요." onkeyup="enterkey()" value="${cri.keyword }" />
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
								<tr class="text-center">
									<th style="width: 5%">#</th>
									<th style="width: 12%">이름</th>
									<th style="width: 22%">팀원</th>
									<th style="width: 10%">생성일자</th>
									<th style="width: 5%">진행</th>
									<th style="width: 5%">완료</th>
									<th style="width: 15%">최근 프로젝트</th>
<!-- 									<th>프로젝트</th> -->
								</tr>
							</thead>
							
							<tbody>
								<c:if test="${empty teamList }">
									<tr>
			     						<td colspan="7" class="text-center">
			     							해당 내용이 없습니다.
			     						</td>
			     					</tr>
								</c:if>
							
								<c:if test="${!empty teamList }">
									<c:forEach items="${teamList }" var="team" varStatus="status">
										
										<tr onclick="location.href='<%=request.getContextPath()%>/team/detail.do?from=list&teamNo=${team.teamNo}'" style="cursor:pointer">
											<td>${status.count }</td>
											<td>
												<a href="<%=request.getContextPath()%>/team/detail.do?from=list&teamNo=${team.teamNo}">${team.teamNm }</a>
											</td>
											
											<!-- Users -->
											<c:if test="${empty team.teamMemberList }">
					     						<td colspan="1">
					     							냉무.
					     						</td>
											</c:if>
											<c:if test="${!empty team.teamMemberList }">
												<td>
													<ul class="list-inline">
														<c:if test="${team.teamMemberList.size() > 5 }">
															<c:forEach items="${team.teamMemberList }" var="teamMember" end="4">
																<li class="list-inline-item">
																	<c:if test="${teamMember.leader eq 'y' }">
																		<span class="img-circle img-bordered-sm border-primary manPicture" data-id="${teamMember.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 title="${teamMember.userNm } - <c:forEach items="${positionList }" var="position">${position.code eq teamMember.positionCode ? position.nm : '' }</c:forEach>" ></span>
																	</c:if>
																	<c:if test="${teamMember.leader eq 'n' }">
																		<span class="img-circle manPicture" data-id="${teamMember.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 title="${teamMember.userNm } - <c:forEach items="${positionList }" var="position">${position.code eq teamMember.positionCode ? position.nm : '' }</c:forEach>" ></span>
																	</c:if>
																</li>
															</c:forEach>
																<a>..외 총 ${team.teamMemberList.size() }명 </a>
														</c:if>
														<c:if test="${team.teamMemberList.size() <= 5 }">
															<c:forEach items="${team.teamMemberList }" var="teamMember" end="4">
																<li class="list-inline-item">
																	<c:if test="${teamMember.leader eq 'y' }">
																		<span class="img-circle img-bordered-sm border-primary manPicture" data-id="${teamMember.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 title="${teamMember.userNm } - <c:forEach items="${positionList }" var="position">${position.code eq teamMember.positionCode ? position.nm : '' }</c:forEach>" ></span>
																	</c:if>
																	<c:if test="${teamMember.leader eq 'n' }">
																		<span class="img-circle manPicture" data-id="${teamMember.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;" data-toggle="tooltip"
																		 title="${teamMember.userNm } - <c:forEach items="${positionList }" var="position">${position.code eq teamMember.positionCode ? position.nm : '' }</c:forEach>" ></span>
																	</c:if>
																</li>
															</c:forEach>
														</c:if>
													</ul>
												</td>
											</c:if>
											
											<td class="text-center"><a><fmt:formatDate value="${team.regDt }" pattern="yyyy-MM-dd" /></a></td>
											<td class="text-center"><a>${team.proceedingCnt }개</a></td>	
											<td class="text-center"><a>${team.completeCnt }개</a></td>
											
											<c:if test="${empty team.prjList }">
												<td class="text-center">
<!-- 			     									진행중인 프로젝트가 없습니다. -->
			     								</td>
											</c:if>
											<c:if test="${!empty team.prjList }">
<%-- 												<c:if test="${team.prjList.size() == 1}"> --%>
<%-- 													<c:forEach items="${team.prjList }" var="prj" end="0" varStatus="status"> --%>
<!-- 														<td> -->
<%-- 															<a href="<%=request.getContextPath()%>/project/home.do?from=list&prjNo=${prj.prjNo}"> ${prj.prjNm}&nbsp; --%>
<%-- 																<c:choose> --%>
<%-- 																	<c:when test="${prj.sttCode eq 'p'}"><span class="badge badge-primary">진행</span></c:when> --%>
<%-- 																	<c:when test="${prj.sttCode eq 'h'}"><span class="badge badge-secondary">보류</span></c:when> --%>
<%-- 																	<c:when test="${prj.sttCode eq 'c'}"><span class="badge badge-success">완료</span></c:when> --%>
<%-- 																</c:choose> --%>
<!-- 															</a>  -->
<!-- 															<br>  -->
<%-- 															<small><fmt:formatDate value="${prj.regDt }" pattern="yyyy-MM-dd" /></small> --%>
<!-- 														</td> -->
<%-- 													</c:forEach>	 --%>
<%-- 												</c:if> --%>
												
												<c:if test="${team.prjList.size() >= 1}">
													<c:forEach items="${team.prjList }" var="prj" end="0" varStatus="status">
														<td class="text-left">
															<a href="<%=request.getContextPath()%>/project/home.do?from=list&prjNo=${prj.prjNo}"> ${prj.prjNm}&nbsp;
																<c:choose>
																	<c:when test="${prj.sttCode eq 'p'}"><span class="badge badge-primary">진행</span></c:when>
																	<c:when test="${prj.sttCode eq 'h'}"><span class="badge badge-secondary">보류</span></c:when>
																	<c:when test="${prj.sttCode eq 'c'}"><span class="badge badge-success">완료</span></c:when>
																</c:choose>
															</a> 
															<br> 
															<small><fmt:formatDate value="${prj.regDt }" pattern="yyyy-MM-dd" /></small>
														</td>
													</c:forEach>
												</c:if>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
								
							</tbody>
						</table>
					</div>
				</div>
				
			</section>
		</div>
		
		<!-- pagination -->
		<div class="card-footer"> 
			<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>
		</div>
	</div>
</section>

<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type="hidden" name="perPageNum" value="" />
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>
<%@ include file="/WEB-INF/views/team/registModal.jsp"%>

<script type="text/javascript">
function list_go(page, url) { 
	if (!url) url = "list.do";
	var jobForm = $('#jobForm');
	
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	
	jobForm.find("[name='leaderId']").val($('select[name="leaderId"]').val());
	
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
	$('.modal').on('hide.bs.modal', function(){
		let forms = $(this).find("form");
		for(form of forms){
			form.reset();					
		}
		// 취소버튼 클릭 시 팀원 목록 초기화.
		var memInput = $('#memberInput').empty();

		$("input[name=addMembers]").each(function(index, item){
			$(item).remove();
		});
		
	});
	<c:if test="${from eq 'regist' }">
		Swal.fire({
			text: '등록되었습니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>
		
	<c:if test="${from eq 'remove' }">
		Swal.fire({
			text: '정상적으로 해제되었습니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>	

	// 이미지 툴팁.
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	  return new bootstrap.Tooltip(tooltipTriggerEl)
	});
	
}
</script>


</body>