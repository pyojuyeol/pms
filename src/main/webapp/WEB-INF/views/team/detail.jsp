<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="tmemList" value="${team.teamMemberList }" />

<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/history/history.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/modal.css" />

<style>
/* .table-woo td { */
/* 	border:1px solid #909090; */
/* } */
/* .table-woo > td { */
/* 	border:1px solid #909090; */
/* } */
</style>

</head>
<title>팀 상세보기</title>
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
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modifyModal">수 정</button>
				<button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go('remove.do', '${team.teamNo}');">해 체</button>
				<button type="button" id="listBtn" class="btn btn-secondary" onclick="location.href='list.do'">목 록</button>
			</div>
		</div><!-- card-header end -->
	
		<div class="card-body">
			
			<div class="row">
				<div class="col-12 col-md-12 col-lg-9 order-2 order-md-1">
				
					  <table class="table table-bordered table-woo" style="height:120px; text-align:center; color: #505050" >
			               <COLGROUP style="width:20%; background-color:#EEEEEE;">
			               <COLGROUP style="width:30%;">
			               <COLGROUP style="width:20%; background-color:#EEEEEE;">
			               <COLGROUP style="width:30%;">
				           <tr>
				               <td style="font-weight:bold;">이름</td>
				               <td>${team.teamNm }</td>
				               <td style="font-weight:bold;">생성일자</td>
				               <td><fmt:formatDate value="${team.regDt }" pattern="yyyy-MM-dd" /></td>
				           </tr>
				           <tr>
				               <td style="font-weight:bold;">진행중인 프로젝트</td>
				               <td>${team.proceedingCnt }개</td>
				               <td style="font-weight:bold;">완료된 프로젝트</td>
				               <td>${team.completeCnt }개</td>
				           </tr>
				       </table>



						<div class="form-group mt-3" >
						<label for="">진행중인 프로젝트</label>&nbsp;&nbsp;
						
						<c:if test="${empty teamPrjList }">
						<br>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;진행중인 프로젝트가 없습니다.</td>
							</tr>
						</c:if>
						
						<!-- 진행중 프로젝트 목록 -->
						<c:if test="${!empty teamPrjList }">
							<c:forEach items="${teamPrjList }" var="teamPrj">
								<c:if test="${teamPrj.sttCode eq 'p' }">
								
									<div class="m-2">
										<table class="table table-hover text-nowrap" style="border:1px solid #dee2e6; margin-bottom: 0.5rem;">
											<tr onclick="location.href='<%=request.getContextPath()%>/project/home.do?prjNo=${teamPrj.prjNo }';" style="cursor: pointer;">
												<!-- 상태 -->
												<td style="width:12%;">
													<h5><span class="badge badge-pill badge-success" style="margin-top : 10px;">진행</span></h5>
												</td>
												<!-- 프로젝트명  -->
												<td >
													${teamPrj.prjNm }
												</td>
												
												<!-- 기한 -->
												<td style="width:10%">
													<fmt:formatDate value="${teamPrj.bgnDt }" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${teamPrj.endDt }" pattern="yyyy-MM-dd" />
												</td>
												
												<!-- 진척도 -->
												<td class="project_progress text-center" style="width:18% ">
													<div class="progress progress-sm ">
														<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${teamPrj.prjRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${teamPrj.prjRate }%"></div>
													</div>
												</td> 
												<!-- 진척도 % -->
												<td class="p-0 m-0" style="width:5%">${teamPrj.prjRate }%</td>
											</tr>
										</table>
									</div>
									
								</c:if>
							</c:forEach>	
						</c:if>
					</div>
					
					
					<div class="form-group mt-3" >
						<label for="">완료된 프로젝트</label>
						
						<c:if test="${empty teamComPrjList }">
							<br>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;완료된 프로젝트가 없습니다.</td>
							</tr>
						</c:if>
						
						<!-- 완료된 프로젝트 목록 -->
						<c:if test="${!empty teamComPrjList }">
							<c:forEach items="${teamComPrjList }" var="teamPrj">
								<c:if test="${teamPrj.sttCode eq 'c' }">
								
									<div class="m-2">
										<table class="table table-hover text-nowrap" style="border:1px solid #dee2e6; margin-bottom: 0.5rem;">
											<tr onclick="location.href='<%=request.getContextPath()%>/project/home.do?prjNo=${teamPrj.prjNo }';" style="cursor: pointer;">
												<!-- 상태 -->
												<td style="width:12%;">
													<h5><span class="badge badge-pill badge-success" style="margin-top : 10px;">진행</span></h5>
												</td>
												<!-- 프로젝트명  -->
												<td >
													${teamPrj.prjNm }
												</td>
												
												<!-- 시작일자 -->
												<td style="width:10%">
													<fmt:formatDate value="${teamPrj.bgnDt }" pattern="yyyy-MM-dd" />
												</td>
												<!-- 종료일자 -->
												<td style="width:10%">
													<fmt:formatDate value="${teamPrj.endDt }" pattern="yyyy-MM-dd" />
												</td>
												
												<!-- 진척도 -->
												<td class="project_progress text-center" style="width:18% ">
													<div class="progress progress-sm ">
														<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${teamPrj.prjRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${teamPrj.prjRate }%"></div>
													</div>
												</td> 
												<!-- 진척도 % -->
												<td class="p-0 m-0" style="width:5%">${teamPrj.prjRate }%</td>
											</tr>
										</table>
									</div>
									
								</c:if>
							</c:forEach>	
						</c:if>
					</div>
					
					<hr>

					<div class="row">
						<div class="col-md-12">
						
							<c:if test="${empty team.logList }">
	     						<h6 class="text-center">
	     							팀 이력이 존재하지 않습니다.
	     						</h6>
							</c:if>
								
							<c:if test="${!empty team.logList }">
								<div class="timeline">
									<div class="entry">
										<c:set var="logDate" value="0"></c:set>
										<c:forEach items="${team.logList }" var="teamLog" varStatus="status">
											<div class="historyForm">
												<div class="title">
													<fmt:formatDate var="logDate1" value="${teamLog.updDt }" pattern="yyyy-MM-dd" />
													<c:if test="${logDate ne logDate1}">
														<h3 class="">
															<fmt:formatDate value="${teamLog.updDt }" pattern="yyyy-MM-dd" />
														</h3>
														<fmt:formatDate var="logDate" value="${teamLog.updDt }" pattern="yyyy-MM-dd" />
													</c:if>

												</div>
												<div class="body mb-4">
													<span><fmt:formatDate value="${teamLog.updDt }" pattern="HH:mm" /></span> <span style="margin-left: 20px;">
														<c:if test="${teamLog.sttCode eq 'i' }">
															<a href="#">${teamLog.userNm } 님 팀원 참여</a>
														</c:if> 
														<c:if test="${teamLog.sttCode eq 'o' }">
															<a href="#">${teamLog.userNm } 님 팀원 제외</a>
														</c:if>
													</span>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:if>
								
						</div>
					</div>		
									
				</div>
				
				<!-- 멤버 -->
				<div class="col-12 col-md-12 col-lg-3 order-1 order-md-2">
					<table class="table">
						<thead>
							<tr class="text-center">
								<th style="width:30%; height:50px;">#</th>
								<th style="width:35%">이름</th>
								<th>직책</th>
							</tr>
						</thead>
						<tbody style="vertical-align: middle;">
							
							<c:if test="${empty team.teamMemberList }">
								<tr>
		     						<td colspan="7" class="text-center">
		     							팀원이 존재하지 않습니다.
		     						</td>
		     					</tr>	
							</c:if>
							
							<c:if test="${!empty team.teamMemberList }">
								<c:forEach items="${team.teamMemberList }" var="teamMember">
									<tr class="text-center">
										<td style="padding:3px;">
											<span class="img-circle manPicture" data-id="${teamMember.userId }" style="display:block; width:40px; height:40px; margin:0 auto;"></span>
										</td>
										<td>
											<c:choose>
												<c:when test="${teamMember.leader eq 'y' }"><a>${teamMember.userNm }&nbsp;<span class="badge badge-danger">팀장</span></a></c:when>
												<c:when test="${teamMember.leader ne 'y' }"><a>${teamMember.userNm }</a></c:when>
											</c:choose>
										</td>
										<td>
											<c:forEach items="${positionList }" var="position">
												<c:if test="${position.code eq teamMember.positionCode }"><a>${position.nm}</a></c:if>
											</c:forEach>
										</td>
			     					</tr>
		     					</c:forEach>
							</c:if>
								
						</tbody>
					</table>
				</div>
			</div>
		</div><!-- card-body -->
		
	</div><!-- card-outline end -->
</section><!-- content end -->
<%@ include file="/WEB-INF/views/team/modifyModal.jsp"%>

<script>
	window.onload = function() {
		
		<c:if test="${from eq 'modify' }">
			Swal.fire({
				text: '수정되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인',
			});
		</c:if>
		
		// 취소버튼 클릭 시 팀원 목록 초기화.
		$('.modal').on('hide.bs.modal', function(){
			window.location.reload();
		});
		
		// 사진 넣기
		MemberPictureThumb('<%=request.getContextPath() %>');
		
		
		<c:if test="${from eq 'modify' }">
			alertA('정상적으로 수정되었습니다.','success', window.close, opener.loadOrg);
		</c:if>
		
	}
</script>


<script>
	function modifyForm_go(url, teamNo) {
		location.href = url + "?teamNo=" + teamNo;
	}
	
	function remove_go(url, teamNo) {
		/* 스윗얼럿 */
		Swal.fire({
			text: '해체 후 복구가 불가능합니다. \n정말 해체하시겠습니까?',
			icon: 'warning',
			confirmButtonColor: '#007bff',
			cancelButtonColor: '#dc3545',
			showCancelButton: true,
			confirmButtonText: '확인',
			cancelButtonText: '취소',
		}).then((result) => {
			/* Read more about isConfirmed, isDenied below */
			if (result.isConfirmed) {
				location.href = url + "?teamNo=" + teamNo;
			}
		});
	}
</script>

</body>