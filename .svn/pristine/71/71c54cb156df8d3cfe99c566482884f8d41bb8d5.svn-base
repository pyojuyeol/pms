<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="settingModal" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">DB 접속 변경</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<form name="settingDbcnForm" method="post">
					<div class="row">
						<div class="form-group col-6">
							<label for="dbNo">DB</label> <select name="dbNo"
								class="form-control">
								<c:forEach items="${dbList }" var="item">
									<option value="${item.dbNo }">${item.dbNm }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-12">
							<label>백업 계정</label> <select name="dbcnNo" class="form-control">
								<c:if test="${empty dbcnSttNList }">
									<option value="">현재 설정 가능한 접속 계정이 없습니다.</option>
								</c:if>
								<c:forEach items="${dbcnSttNList }" var="item">
									<option value="${item.dbcnNo }">${item.dbcnUser }(${item.dbcnUrl })</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-danger" data-dismiss="modal">취
					소</button>
				<button type="button" class="btn btn-primary mr-2"
					onclick="checkConfirm();">예 약</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script>
	function checkConfirm(){
		
		let dbNm = $("select[name='dbNo'] option:checked").text();
		let dbcnNm = $("select[name='dbcnNo'] option:checked").text();
		
		Swal.fire({
			  text: dbNm + '의 접속계정을 '+ dbcnNm +'으로 변경하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((result) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (result.isConfirmed) {  
				  sendSettingForm();
			  } else {
				  $("#settingModal").modal('hide');
			  }
		});
		
	}
	function sendSettingForm(pass){
		
		$.ajax({ 
			url: "<%=request.getContextPath()%>/admin/dbRsrv/regist.do?pass="+pass, 
			type: "POST",
			data: $("form[name='settingDbcnForm']").serialize(),
			async: false,
			success: function(result){
				/* 결과 출력 시작 */
				if(result=="success"){						
					  Swal.fire({
						  text: '예약되었습니다. 명일 오전 3시부터 적용됩니다.',
						  icon: 'success',
						  confirmButtonColor: '#007bff',
						  confirmButtonText: '확인'
						}).then((confirm) => {
						  /* Read more about isConfirmed, isDenied below */
						  if (confirm.isConfirmed) {  
							location.reload();
						  }
						});
				  } else if(result=="reserved") {
					  Swal.fire({
						  text: '이미 변경 예약이 있는 DB입니다. 변경 하시겠습니까?',
						  icon: 'warning',
						  confirmButtonColor: '#007bff',
						  cancelButtonColor: '#dc3545',
						  showCancelButton: true,
						  confirmButtonText: '확인',
						  cancelButtonText: '취소'
						}).then((confirm) => {
						  /* Read more about isConfirmed, isDenied below */
						  if (confirm.isConfirmed) {  
							  sendSettingForm("Y");
						  } 
						});
				  }
				/* 결과 출력 끝 */
			}
		  
		});	
	}
	
	function cancelReservation(dbRsrvNo){
		Swal.fire({
			  text: 'DB변경을 취소하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((result) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (result.isConfirmed) {  
				  $.ajax({ 
						url: "<%=request.getContextPath()%>/admin/dbRsrv/cancel.do?dbRsrvNo="+dbRsrvNo, 
						type: "POST",
						async: false,
						success: function(result){
							/* 결과 출력 시작 */
							if(result=="success"){						
								Swal.fire({
								    text: '취소되었습니다.',
								    icon: 'success',
									confirmButtonColor: '#007bff',
									confirmButtonText: '확인'
								}).then((confirm) => {
									/* Read more about isConfirmed, isDenied below */
									if (confirm.isConfirmed) {  
										location.reload();
								    }
							    });
							} 
							/* 결과 출력 끝 */
						}
					});	
			  } else {
				  $("#settingModal").modal('hide');
			  }
		});
		
	}
	
	</script>