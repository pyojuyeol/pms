<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="pushList" value="${dataMap.pushList }" />
<c:set var="cri" value="${pageMaker.cri }" />
<head>

</head>
<body>
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>알림</h1>
				</div>
				<div class="col-sm-6 d-none d-sm-block">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a
							href="<%=request.getContextPath()%>/dashboard.do">Home</a></li>
						<li class="breadcrumb-item active">알림</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="card card-info  card-outline">
			<div class="card-header">
				<div class="d-flex justify-content-between">
					<!-- Check all button -->
					<div class="btn-box">
						<button type="button" class="btn btn-default" onclick="removePush()">
							<i class="far fa-trash-alt"></i> 영구삭제
						</button>
						<button type="button" class="btn btn-default" onclick="modifyReadCode()">
							<i class="fas fa-sync-alt"></i> 읽음 처리
						</button>
					</div>
					<div class="btn-box d-flex" style="width: 250px;">
						<!-- sort num -->
						<select class="form-control mr-1" style="”display: inline-block“"
							name="perPageNum" id="perPageNum" onchange="list_go(1);">
							<option value="10" ${cri.perPageNum eq 10 ? 'selected' : '' }>정렬개수</option>
							<option value="20" ${cri.perPageNum eq 20 ? 'selected' : '' }>20개씩</option>
							<option value="50" ${cri.perPageNum eq 50 ? 'selected' : '' }>50개씩</option>
							<option value="100" ${cri.perPageNum eq 100 ? 'selected' : '' }>100개씩</option>
						</select>
						<!-- sort searchType -->
						<select class="form-control mr-1" style="”display: inline-block“"
							name="searchType" id="searchType" onchange="list_go(1);">
							<option value="A" ${cri.searchType eq 'A' ? 'selected' : ''}>전체</option>
							<option value="N" ${cri.searchType eq 'N' ? 'selected' : ''}>읽지않음</option>
							<option value="Y" ${cri.searchType eq 'Y' ? 'selected' : ''}>읽음</option>
						</select>
					</div>
					<!-- /.float-right -->

				</div>
			</div>
			<div class="card-body minH">


				<div class="table-responsive">
					<table class="table table-hover text-center">
						<thead>
							<tr>
								<th width="20px">
									<button type="button" style="background-color:transparent; border:none;" class="p-0 btn btn-default checkbox-toggle">
										<i id="checkbox" class="far fa-square"></i>
									</button>
								</th>
								<th>알림 내용</th>
								<th>확인 여부</th>
								<th>알림 일자</th>
							</tr>
						</thead>
						<tbody>
							<jsp:useBean id="now" class="java.util.Date" />
							<fmt:formatDate var="today" value="${now}" pattern="yyyyMMdd" />
							
							<c:forEach var="item" items="${pushList }">
								<tr>
									<td>
										<div class="icheck-primary pl-2">
											<input type="checkbox" value="${item.pushNo }" name="pushNo"
												id="check${item.pushNo }"> <label
												for="check${item.pushNo }"></label>
										</div>
									</td>
									<td class="text-left"><a
										href="javascript:moveToPushUrl('${item.pushNo }', '${item.url }', '${item.prjNo }')">${item.message }</a></td>
									<td class="align-middle">
										<c:if test="${item.readNyCode eq 'Y' }"><i class="fa-solid fa-envelope-open text-primary"></i></c:if> 
										<c:if test="${item.readNyCode eq 'N' }"><i class="fa-solid fa-envelope text-primary"></i></c:if></td>
									<td class="mailbox-date align-middle">
									<fmt:formatDate var="itDate" value="${item.regDt}" pattern="yyyyMMdd" />
									
										<!-- 날짜 계산 -->
								        <c:if test="${itDate < today}">	
								        	<c:set var="resultDate" value="${today - itDate }"/>							        	
								        	<c:if test="${resultDate <= 6 }">${resultDate}일 전</c:if>
								        	<c:if test="${resultDate > 6 }"><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd"/></c:if>
								        </c:if>
										<c:if test="${itDate == today}">
											<fmt:formatDate var="nowTime" value="${now}"  pattern="HHmmss"/>  
											<fmt:formatDate var="itTime" value="${item.regDt}"  pattern="HHmmss"/>
											<c:set var="result" value="${nowTime - itTime }"/>
											<c:if test="${result < 60 }">방금 전</c:if>
											<c:if test="${result >= 60 && result < 6000 }"><fmt:formatNumber type="number" maxFractionDigits="0"  value="${result/100 }" />분 전</c:if>
											<c:if test="${result >= 6000 }"><fmt:formatNumber type="number" maxFractionDigits="0"  value="${result/10000 }" />시간 전</c:if>
								        </c:if>
									
									
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty pushList }">
								<tr>
									<td colspan="100" class="text-muted text-center">
									<c:choose>
										<c:when test="${cri.searchType eq 'Y' }">
										읽은 알림이 없습니다.
										</c:when>
										<c:when test="${cri.searchType eq 'A' }">
										받은 알림이 없습니다.
										</c:when>
										<c:otherwise>
										읽지 않은 알림이 없습니다.
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<!-- /.table -->
				</div>
				<!-- /.mail-box-messages -->
			</div>
			<!-- /.card-body -->
			<!-- pagination -->
			<div class="card-footer">
				<div class="<c:if test="${empty pushList }">d-none</c:if>">
					<%@ include file="/WEB-INF/views/common/pagination2.jsp"%>
					<form id="jobForm">	
						<input type='hidden' name="page" value="" />
						<input type='hidden' name="perPageNum" value=""/>
						<input type='hidden' name="searchType" value="" />
					</form>
				</div>

			</div>
		</div>
	</section>
	<script>
		function list_go(page,url){
			
	 		if(!url) url="list.do";
			var jobForm=$('#jobForm');
			
			jobForm.find("[name='page']").val(page);
			jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
			jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		
			jobForm.attr({
				action:url,
				method:'get'
			}).submit();
		}
	</script>
	<script>
		window.onload = function() {
			//Enable check and uncheck all functionality
			$('.checkbox-toggle').click(
					function() {
						var clicks = $(this).data('clicks')
						if (clicks) {
							//Uncheck all checkboxes
							$('.icheck-primary input[type=\'checkbox\']')
									.prop('checked', false)
							$('.checkbox-toggle .far.fa-check-square')
									.removeClass('fa-check-square').addClass(
											'fa-square')
						} else {
							//Check all checkboxes
							$('.icheck-primary input[type=\'checkbox\']')
									.prop('checked', true)
							$('.checkbox-toggle .far.fa-square').removeClass(
									'fa-square').addClass('fa-check-square')
						}
						$(this).data('clicks', !clicks)
					});

		}
		
		/* 선택된 알림 읽음 표시 */
		function modifyReadCode(){
			
			let targetPushNoList = [];
			
			let checkedPushList = document.querySelectorAll("input[name=pushNo]:checked");
			if(checkedPushList.length==0){
				Swal.fire({
							  text: '선택된 알림이 없습니다.',
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
				});
				return;
			}

			for(item of checkedPushList){
				targetPushNoList.push(item.value);
			}
			
			
			$.ajax({ 
				url: "<%=request.getContextPath()%>/push/modifyReadCode.do", 
				type: "POST",
				data: JSON.stringify(targetPushNoList),
				contentType: "application/json",
				success: function(result){ 
					if(result){
						Swal.fire({
							  text: '읽음 처리되었습니다.',
							  icon: 'success',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
						}).then((result) => {
							location.reload();
						});
					}
				}, 
				error: function (request, status, error){ 
					Swal.fire({
						  text: '에러가 발생되었습니다. 에러코드' + error,
						  icon: 'error',
						  confirmButtonColor: '#007bff',
						  confirmButtonText: '확인'
						});
				}
			});
			
		}
		
		
		
		function removePush(){

			let targetPushNoList = [];
			let checkedPushList = document.querySelectorAll("input[name=pushNo]:checked");
			if(checkedPushList.length==0){
				Swal.fire({
							  text: '선택된 알림이 없습니다.',
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
				});
				return;
			}

			for(item of checkedPushList){
				targetPushNoList.push(item.value);
			}
			
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
				
				
				$.ajax({ 
					url: "<%=request.getContextPath()%>/push/remove.do", 
					type: "POST", 
					data: JSON.stringify(targetPushNoList),
					contentType: "application/json",
					success: function(result){ 
						if(result){
						     Swal.fire({
						      text: '삭제되었습니다.',
							  icon: 'success',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인',
						    }).then((result) => {
								  /* Read more about isConfirmed, isDenied below */
								  if (result.isConfirmed) {
						    
									  location.reload();
								  }
							});
						}
					}, 
					error: function (request, status, error){ 
						Swal.fire({
							  text: '에러가 발생되었습니다. 에러코드' + error,
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							});
					}
				});
				  
				  
			  } 
			});

		}
	</script>
</body>