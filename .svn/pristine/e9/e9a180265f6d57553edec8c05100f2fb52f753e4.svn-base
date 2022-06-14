<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="recordList" value="${dataMap.recordList }" />

<head>

<style>
.fa-star {
	color : gold;
	font-size : 20px;
	cursor : pointer;
}
</style>

</head>
<title>Library</title>

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
					<li class="breadcrumb-item"><a href="#">Library</a></li>
					<li class="breadcrumb-item active">목록</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-10">
		
			<div class="card card-info card-outline">
				<div class="card-header with-border">
					<div id="keyword" class="card-tools" style="width:450px;">
						<div class="input-group row">
							<!-- search bar -->
							
							<!-- sort num -->
							<select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
								<option value="10">정렬개수</option>
								<option value="20">20개씩</option>
								<option value="30">30개씩</option>
								<option value="50">50개씩</option>
							</select>
		
							<select class="form-control col-md-2" name="searchType" id="searchType">
								<option value="">검색구분</option>
								<option value="pnm" ${param.searchType eq 'nm' ? 'selected' : ''}>프로젝트명</option>
								<option value="lnm" ${param.searchType eq 'l' ? 'selected' : ''}>팀장명</option>
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
								<table class="table table-striped projects text-center">
									<thead>
										<tr>
											<th style="width: 3%">#</th>
											<th style="width: 7%">상태</th>
											<th style="width: 15%">이름</th>
											<th>구성원</th>
											<th style="width: 20%">기간</th>
											<th style="width: 10%">즐겨찾기</th>
										</tr>
									</thead>
									
									<tbody>
									
										<c:if test="${empty recordList }">
											<tr>
					     						<td colspan="7" class="text-center">
					     							해당 내용이 없습니다.
					     						</td>
					     					</tr>
										</c:if>
										
										<c:if test="${!empty recordList }">
											<c:forEach items="${recordList }" var="record" varStatus="status">
												<tr>
													<td>${status.count }</td>
													<td>
														<c:choose>
															<c:when test="${record.sttCode eq 'p'}"><span class="badge badge-primary">진행</span></c:when>
															<c:when test="${record.sttCode eq 'h'}"><span class="badge badge-secondary">보류</span></c:when>
															<c:when test="${record.sttCode eq 'c'}"><span class="badge badge-success">완료</span></c:when>
														</c:choose>
													</td>
													
													<c:forEach items="${prjSttList}" var="prjStt">
														<option value="${prjStt.code}" ${param.sttCode eq prjStt.code ? 'selected' : ''}>${prjStt.nm }</option>
													</c:forEach>
													
													<!-- 프로젝트명, regDt -->
													<td style="text-align:left;">
														<a href="<%=request.getContextPath()%>/project/home.do?from=record&prjNo=${record.prjNo}"> ${record.prjNm}&nbsp;</a> 
														<br> 
<%-- 														<small><fmt:formatDate value="${prj.regDt }" pattern="yyyy-MM-dd" /></small> --%>
													</td>
													
													<!-- Users -->
													<c:if test="${empty record.prjUserList }">
							     						<td colspan="1">
							     							냉무.
							     						</td>
													</c:if>
													<c:if test="${!empty record.prjUserList }">
														<td class="text-center">
															<ul class="list-inline">
															
																<c:if test="${record.prjUserList.size() > 5 }">
																	<c:forEach items="${record.prjUserList }" var="prjUser" end="4">
																		<li class="list-inline-item">
																			<c:if test="${prjUser.prjRole eq 'PM' and prjUser.prjRole eq 'PL' }">
																				<img class="img-circle img-bordered-sm manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;"></img>
																			</c:if>
																			<img class="img-circle manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;"></img>
																		</li>
																	</c:forEach>
																	..외 총 ${record.prjUserList.size() }명
																</c:if>
															</ul>
														</td>
													</c:if>
													
													<td>
														<a><fmt:formatDate value="${record.bgnDt }" pattern="yyyy-MM-dd" />&nbsp;~&nbsp;<fmt:formatDate value="${record.endDt }" pattern="yyyy-MM-dd" /></a>
													</td>
													
													<!-- 즐겨찾기 -->
													<td class="project-actions text-center">
														<c:if test="${empty favoritesList }">
															<i class="fa-regular fa-star fRegist" onclick="regist_go('${record.prjNo}');"></i>
<!-- 															<i class="fa-regular fa-star fRegist"></i> -->
														</c:if>
														<c:set var="check" value="0" />
														<c:if test="${!empty favoritesList }">
															<c:forEach items="${favoritesList}" var="favorites">
																<c:if test="${record.prjNo eq favorites.prjNo }"><c:set var="check" value="${check + 1 }" /></c:if>
															</c:forEach>
															<c:if test="${check eq 1 }"><i class="fa-solid fa-star" onclick="remove_go('${record.prjNo}');"></i></c:if>
															<c:if test="${check eq 0 }"><i class="fa-regular fa-star" onclick="regist_go('${record.prjNo}');"></i></c:if>
														</c:if>
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
		</div>
		
		
		<div class="col-2 card card-info card-outline">
			<div class="card-header">
				<h3 class="card-title">즐겨찾기</h3>

<!-- 				<div class="card-tools"> -->
<!-- 					<button type="button" class="btn btn-tool" data-card-widget="collapse"> -->
<!-- 						<i class="fas fa-plus"></i> -->
<!-- 					</button> -->
<!-- 				</div> -->
				<!-- /.card-tools -->
			</div>
			<div class="card-body" style="padding: 0.3rem;">
				<div class="card card-outline card-primary">
					<div class="card-header p-2">
						<c:if test="${empty favoritesList }">
	     						<td colspan="1" class="text-center">
	     							해당 내용이 없습니다.
	     						</td>
						</c:if>
						
						<c:if test="${!empty favoritesList }">
							<c:forEach items="${favoritesList }" var="favorites">
								<div class="m-2">
									<a href="<%=request.getContextPath()%>/project/home.do?from=record&prjNo=${favorites.prjNo}"> ${favorites.prjNm}&nbsp;</a> 
									<i class="fa-solid fa-star" onclick="remove_go('${favorites.prjNo }');" style="float:right;" data-prjno="${favorites.prjNo }"></i>
								</div>
							</c:forEach>
						</c:if>
					
					</div> <!-- /.card-header -->
				</div> <!-- /.card -->
			</div><!-- /.card-body -->
		</div>
		
	</div>
</section>


<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type="hidden" name="perPageNum" value="" />
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>


<script>

// let test = document.querySelector(".test");
// test.addEventListener("click", function(e) {
// 	if (!e.target.classList.contains("fa-star")) {
// 		return;
// 	}
// 	if(e.target.classList.contains("fa-solid")) {
// 		remove_go(e.target);
// 	}
// });

// function regist_go(eventTarget) {   
	
// 	let prjNo = eventTarget.dataset.prjno;
	
// 	$.ajax({
<%-- 		url : "<%=request.getContextPath()%>/library/favorites/regist.do", --%>
// 		type : "post",
// 		data : prjNo,
// 		contentType : 'text/plain;charset=utf-8',
// 		success : function(data) {
// 			Swal.fire({
// 				text: '즐겨찾기 추가되었습니다.',
// 				icon: 'success',
// 				confirmButtonColor: '#007bff',
// 				confirmButtonText: '확인'
// 			});
// 		},
// 		error : function(error) {
// 			Swal.fire({
// 				  text: '서버장애로 즐겨추가를 실패했습니다. 에러코드' + error,
// 				  icon: 'error',
// 				  confirmButtonColor: '#007bff',
// 				  confirmButtonText: '확인'
// 			});
// 			AjaxErrorSecurityRedirectHandler(error.status);		
// 		}
// 	});
	
// 	$(eventTarget).replaceClass
// }



function regist_go(prjNo) {
	$.ajax({
		url : "<%=request.getContextPath()%>/library/favorites/regist.do?prjNo=" + prjNo,
		type : "get",
		contentType : 'text/plain;charset=utf-8',
		success : function(data) {
			Swal.fire({
				text: '즐겨찾기 추가되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인'
			}).then((result) => {
				if (result.isConfirmed) {
					window.location.replace('<%=request.getContextPath()%>/library/list.do');
				}
			});
		},
		error : function(error) {
			Swal.fire({
				  text: '즐겨찾기 해제가 실패했습니다. 에러코드' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			});
			AjaxErrorSecurityRedirectHandler(error.status);		
		}
	});
}
function remove_go(prjNo) {
	console.log('remove  ' + prjNo);
	Swal.fire({
		text: '즐겨찾기를 해제하시겠습니까?',
		icon: 'warning',
		confirmButtonColor: '#007bff',
		cancelButtonColor: '#dc3545',
		showCancelButton: true,
		confirmButtonText: '확인',
		cancelButtonText: '취소',
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url : "<%=request.getContextPath()%>/library/favorites/remove.do?prjNo=" + prjNo,
				type : "get",
				contentType : 'text/plain;charset=utf-8',
				success : function(data) {
					Swal.fire({
						text: '즐겨찾기 해제되었습니다.',
						icon: 'success',
						confirmButtonColor: '#007bff',
						confirmButtonText: '확인'
					}).then((result) => {
						if (result.isConfirmed) {
							window.location.replace('<%=request.getContextPath()%>/library/list.do');
						}
					});
				},
				error : function(error) {
					Swal.fire({
						  text: '즐겨찾기 해제가 실패했습니다. 에러코드' + error,
						  icon: 'error',
						  confirmButtonColor: '#007bff',
						  confirmButtonText: '확인'
					});
					AjaxErrorSecurityRedirectHandler(error.status);		
				}
			});
		}
	});
}
</script>



<script>
function list_go(page, url) { 
	if (!url) url = "list.do";
	var jobForm = $('#jobForm');
	
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	
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


let project_div = document.querySelector(".fRemove");
project_div.addEventListener("click", function (e) {
	alert(e.target.tagName);
	
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