<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${(dataMap.type eq 'dbcn') or (empty dataMap.type)}" >
	<c:set var="pageMaker" value="${dataMap.pageMaker }" />
	<c:set var="cri" value="${dataMap.pageMaker.cri }" />
	<c:set var="dbcnList" value="${dataMap.dbcnList }" />
</c:if>
<c:if test="${dataMap.type eq 'dbRsrv' }" >
	<c:set var="pageMaker" value="${dataMap.pageMaker }" />
	<c:set var="cri" value="${dataMap.pageMaker.cri }" />
	<c:set var="dbRsrvList" value="${dataMap.dbRsrvList }" />
</c:if>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/modal.css" />
</head>
<body>
	<!-- 헤더 시작 -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-12 d-none d-sm-block">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="/pms/admin/dashboard.do">Home</a></li>
						<li class="breadcrumb-item active">DB관리</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<!-- 헤더 끝 -->
	<!-- 본문 시작 -->
	<section class="content">
		<div class="row">
			<!--현재 DB 접속 본문끝 -->
			<div class="col-12">
				<div class="card card-purple card-outline card-outline-tabs">
					<div class="card-header p-0 border-bottom-0">
						<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
							<li class="nav-item"><a class="nav-link ${dataMap.type == 'dbcn' ? 'active' : '' }" role="tab" aria-controls="dbcn" aria-selected="${dataMap.type == 'dbcn' ? 'true' : 'false' }">DB 접속 정보</a></li>
							<li class="nav-item"><a class="nav-link ${dataMap.type == 'dbRsrv' ? 'active' : '' }"role="tab" aria-controls="dbRsrv" aria-selected="${dataMap.type == 'dbRsrv' ? 'true' : 'false' }">DB 변경</a></li>
						</ul>
					</div>
					<div class="card-body minH" style="padding-top: 0px;">
						<div class="tab-content" id="custom-tabs-four-tabContent">
							<div class="tab-pane fade ${dataMap.type == 'dbcn' ? 'active show' : '' }" id="dbcn" role="tabpanel" aria-labelledby="dbcn">

								<%@ include file="/WEB-INF/views/admin/dbcn/formModal.jsp"%>

								<div class="card-header px-0">
									<div class="d-flex justify-content-between">
										<div>
											<button type="button" class="btn btn-primary"
												onclick="openForm()" data-toggle="modal"
												data-target="#formModal">등 록</button>
											<button type="button" class="btn btn-outline-secondary"
												onclick="goBackup()">즉시 백업</button>
											<button type="button" class="btn btn-outline-success"
												onclick="goRecover()">즉시 복구</button>
										</div>
										<div id="keyword" style="width: 420px;">
											<div class="input-group">
												<select class="form-control col-md-3" name="searchType"
													id="searchType">
													<option value="">검색구분</option>
													<option value="u" ${param.searchType eq 'u'? 'selected':''}>접속 URL</option>
													<option value="i" ${param.searchType eq 'i'? 'selected':''}>접속 아이디</option>
												</select>

												<!-- keyword -->
												<input class="form-control" type="text" name="keyword"
													placeholder="검색어를 입력하세요." value="${param.keyword}">
												<span class="input-group-append">
													<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
														<i class="fa fa-fw fa-search"></i>
													</button>
												</span>
												<!-- end : search bar -->
											</div>
										</div>
									</div>
								</div>
								<table class="table table-striped text-center">
									<thead>
										<tr>
											<th style="width: 150px">사용 여부</th>
											<th>접속 아이디</th>
											<th>접속 URL</th>
											<th>MAX IDLE</th>
											<th>MAX TOTAL</th>
											<th style="width: 100px"></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty dbcnList }">
											<tr>
												<td colspan="100">접속 계정이 존재하지 않습니다.</td>
											</tr>
										</c:if>
										<c:forEach items="${dbcnList }" var="item">
											<tr>
												<td class="text-center"><c:if
														test="${item.sttCode eq 'Y' }">
														<span>${item.dbNm}</span>
													</c:if> <c:if test="${item.sttCode eq 'R' }">
														<span>예약(${item.rsrvDbNm})</span>
													</c:if> <c:if test="${item.sttCode eq 'N' }">
														<span>-</span>
													</c:if></td>
												<td class="text-left">${item.dbcnUser }</td>
												<td class="text-left">${item.dbcnUrl }</td>
												<td>${item.maxIdle }</td>
												<td>${item.maxTotal }</td>
												<td class="text-center">
													<button class="btn btn-info btn-sm"
														onclick="openForm('${item.dbcnNo }')">
														<i class="fa-solid fa-gear"></i>
													</button>
													<button class="btn btn-danger btn-sm"
														onclick="removeDbcn('${item.dbcnNo }')">
														<i class="fa-solid fa-trash-can"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="tab-pane fade ${dataMap.type == 'dbRsrv' ? 'active show' : '' }" id="dbRsrv" role="tabpanel" aria-labelledby="dbRsrv">

								<%@ include file="/WEB-INF/views/admin/dbcn/settingModal.jsp"%>

								<div class="card-header px-0">
									<div class="d-flex justify-content-between">
										<div>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#settingModal">예 약</button>
											<button type="button" class="btn btn-outline-primary"
												onclick="schedulerExec()">스케줄러 즉시 실행</button>
										</div>
										<div id="keyword" style="width: 420px;">
											<div class="input-group">
												<select class="form-control col-md-3" name="searchDbNm" id="searchDbNm" onchange="list_go(1);">
											  		<option value="" ${param.searchDbNm eq "" ? 'selected':'' } >DB이름</option>
											  		<option value="origin" ${param.searchDbNm eq "origin" ? 'selected':'' }>origin</option>
											  		<option value="backup" ${param.searchDbNm eq "backup" ? 'selected':'' }>backup</option>
											  		
											  	</select>	
												<select class="form-control col-md-3" name="searchType"
													id="searchType">
													<option value="">검색구분</option>
													<option value="u" ${param.searchType eq 'u'? 'selected':''}>접속
														URL</option>
													<option value="i" ${param.searchType eq 'i'? 'selected':''}>접속
														아이디</option>
												</select>

												<!-- keyword -->
												<input class="form-control" type="text" name="keyword"
													placeholder="검색어를 입력하세요." value="${param.keyword}">
												<span class="input-group-append">
													<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
														<i class="fa fa-fw fa-search"></i>
													</button>
												</span>
												<!-- end : search bar -->
											</div>
										</div>
									</div>
								</div>
								<table class="table table-striped text-center">
									<thead>
										<tr>
											<th style="width: 100px">상태</th>
											<th style="width: 120px">DB</th>
											<th>접속 아이디 (접속 URL)</th>
											<th>등록일시</th>
											<th>처리 일시</th>
											<th style="width: 100px"></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty dbRsrvList }">
											<tr>
												<td colspan="100" class="text-center">DB 변경 내역이 존재하지 않습니다.</td>
											</tr>
										</c:if>
										<c:forEach items="${dbRsrvList }" var="item">
											<tr>
												<td><c:if test="${item.stt eq 0 }">
														<span>예약</span>
													</c:if> <c:if test="${item.stt eq 1 }">
														<span>완료</span>
													</c:if> <c:if test="${item.stt eq 2 }">
														<span>실패</span>
													</c:if> <c:if test="${item.stt eq 10 }">
														<span>취소</span>
													</c:if></td>
												<td>${item.dbNm }</td>
												<td>${item.dbcnUser }(${item.dbcnUrl })</td>
												<td><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
												<td><c:if test="${item.updDt == null}">-</c:if>
													<fmt:formatDate value="${item.updDt}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
												<td class="text-center"><c:if test="${item.stt eq 0 }">
														<button class="btn btn-danger btn-sm"
															onclick="cancelReservation('${item.dbRsrvNo }')">
															취 소
														</button>
													</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- pagination -->
					<div class="card-footer">
						<%@ include file="/WEB-INF/views/common/pagination2.jsp"%>

						<form id="jobForm">
							<input type='hidden' name="page" value="" />
							<input type='hidden' name="searchDbNm" value="" />
							<input type='hidden' name="searchType" value="" />
							<input type='hidden' name="keyword" value="" />
							<input type='hidden' name="type" value="${dataMap.type }" />
						</form>
					</div>
				</div>
			</div>

		</div>
	</section>

	<script>
	let nav_links = document.querySelectorAll(".nav-link");
	for (let i = 0; i < nav_links.length; i++) {
		if(nav_links[i].dataset.toggle) {
			continue;
		}
		nav_links[i].addEventListener("click", function(e) {
			list_go(1, e);
		});
	}
	
	function list_go(page,event,url){
		let type = "";
		let nav_link = document.querySelector(".nav-link.active");
		
		if (!event) {
			type = document.querySelector("input[name='type']").value;
		} else {
			type = event.target.getAttribute("aria-controls");
		}
		
		console.log(type);
		 
 		if(!url) url="list.do";
		var jobForm=$('#jobForm');
		
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='searchDbNm']").val($('select[name="searchDbNm"]').val());
		jobForm.find("[name='searchType']").val($('#'+type+' select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('#'+type+' input[name="keyword"]').val());
		jobForm.find("[name='type']").val(type);
		
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
	</script>
	<script>
				
	function openForm(dbcnNo) {

		if(!dbcnNo) {				
			dbcnNo = "";
			$("#formModal .modal-title").text("DB 접속 정보 등록");
			formName = "regist";
			
		} else {
			$("#formModal .modal-title").text("DB 접속 정보 수정");
			formName = "modify";
			
			$.ajax({ 
				url: "<%=request.getContextPath()%>/admin/dbcn/getModifyFormData.do?dbcnNo="+dbcnNo, 
				type: "POST",
				dataType: "json",
				success: function(result){
					if(result){
						for(let key in result){
							$('#formModal [name='+key+']').val(result[key]);
							$('#formModal').modal('show',{backdrop: false});
						}
					}
				}
			});
		}
		
	}
	function schedulerExec() {
		Swal.fire({
			  text: '스케줄러가 즉시 실행됩니다. 계속 진행 하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((confirm) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (confirm.isConfirmed) { 
				$.ajax({ 
					url: "<%=request.getContextPath()%>/admin/dbcn/schedulerExec", 
					type: "POST",
					success: function(result){
						location.href="<%=request.getContextPath()%>/admin/dbcn/list.do";
					}
				});
				location.href="<%=request.getContextPath()%>/security/systemLock";
			  }
			});
	}
	function goBackup(){
		Swal.fire({
			  text: '백업 진행시 서비스가 중단됩니다. 계속 진행 하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((confirm) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (confirm.isConfirmed) { 
				  $.ajax({ 
						url: "<%=request.getContextPath()%>/admin/dbcn/executeBackup.do", 
						type: "POST",
						success: function(result){
							if(result == "denied"){
								location.href="<%=request.getContextPath()%>/security/accessDenied";
							} else {
								location.href="<%=request.getContextPath()%>/admin/dbcn/list.do";
							}
						}
					});
				  	location.href="<%=request.getContextPath()%>/security/systemLock";
				}
			});
	}
	function goRecover(){
		Swal.fire({
			  text: '복구 진행시 서비스가 중단됩니다. 계속 진행 하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((confirm) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (confirm.isConfirmed) { 
				  $.ajax({ 
						url: "<%=request.getContextPath()%>/admin/dbcn/executeRecover.do?", 
						type: "POST",
						success: function(result){
							if(result == "denied"){
								location.href="<%=request.getContextPath()%>/security/accessDenied";
							} else {
								location.href="<%=request.getContextPath()%>/admin/dbcn/list.do";
							}
						}
					});
				  	location.href="<%=request.getContextPath()%>/security/systemLock";
				}
			});
	}
		
	</script>


	<script>

		window.addEventListener('DOMContentLoaded', initDbPage);
		
		function initDbPage() {			
			// 모달 닫힐때 내용 제거
			$('.modal').on('hide.bs.modal', function(){
				let forms = $(this).find("form");
				for(form of forms){
					form.reset();					
				}
			});
						
		}
		
		
	</script>

</body>