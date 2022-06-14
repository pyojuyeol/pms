<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="formModal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">DB 접속 정보</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">
				<form name="dbcnForm" method="post">
					<input type="hidden" name="dbType" value="oracle">
					<input type="hidden" name="dbcnNo">
					<input type="hidden" name="path">
					<input type="hidden" name="fileNm">
					<input type="hidden" name="sttCode">
					<div class="row">
<!-- 						<div class="form-group col-4"> -->
<!-- 							<label>DB 종류</label>  -->
<!-- 							<select name="dbType" class="form-control"> -->
<%-- 							<c:forEach items="${dbTypeList }" var="item"> --%>
<%-- 								<option value="${item.dbTypeId }">${item.dbTypeId }</option> --%>
<%-- 							</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
						<div class="form-group col-3">
							<label>접속 아이디</label> 
							<input type="text" name="dbcnUser"
								class="form-control" required=required />
						</div>
						<div class="form-group col-9">
							<label>접속 URL</label> <input type="text" name="dbcnUrl"
								class="form-control" required=required />
						</div>
					</div>
					<div class="row">
						<div class="form-group col-3">
							<label>접속 비밀번호</label> 
							<input type="password" name="dbcnPwd"
								class="form-control" required=required />
						</div>
						<div class="form-group col-3">
							<label>maxIdle</label> 
							<input type="number" name='maxIdle' class="form-control">
						</div>
						<div class="form-group col-3">
							<label>maxTotal</label> 
							<input type="number" name="maxTotal"
								class="form-control" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-danger" data-dismiss="modal">취 소</button>
				<button type="button" class="btn btn-primary mr-2" onclick="send_form();">저 장</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


	<script>
	let formName = "";

	function send_form() {
		let url = "";
		let message = "";

		if(formName=="regist") {
			url = "<%=request.getContextPath()%>/admin/dbcn/regist.do";
			message = '등록 되었습니다.';
		}
		if(formName=="modify") {
			url = "<%=request.getContextPath()%>/admin/dbcn/modify.do";
			message = '수정 되었습니다.';
		}

		if ($("#formModal input[name='dbcnNm']").val() == "") {
			Swal.fire({
				  text: '접속정보 이름은 필수입니다.',
				  icon: 'warning',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {							   
					$("#formModal input[name='dbcnNm']").focus();
				  }
				});
			return;
		}
		if ($("#formModal input[name='dbcnUser']").val() == "") {
			Swal.fire({
				  text: '접속 아이디는 필수입니다.',
				  icon: 'warning',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {							   
					$("#formModal input[name='dbcnUser']").focus();
				  }
				});
			return;
		}
		if ($("#formModal input[name='dbcnPwd']").val() == "") {
			Swal.fire({
				  text: '접속 비밀번호는 필수입니다.',
				  icon: 'warning',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {							   
					$("#formModal input[name='dbcnPwd']").focus();
				  }
				});
			return;
		}
		if ($("#formModal input[name='dbType']").val() == "") {
			Swal.fire({
				  text: 'DB 종류은 필수입니다.',
				  icon: 'warning',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {							   
					$("#formModal input[name='dbType']").focus();
				  }
				});
			return;
		}
		if ($("#formModal input[name='dbcnUrl']").val() == "") {
			Swal.fire({
				  text: '접속 URL은 필수입니다.',
				  icon: 'warning',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인',
				}).then((result) => {
				  if (result.isConfirmed) {							   
					$("#formModal input[name='dbcnUrl']").focus();
				  }
				});
			return;
		}
		
		$.ajax({ 
			url: url, 
			type: "POST",
			data: $("#formModal form[name='dbcnForm']").serialize(),
			success: function(result){
				  if(result=="success"){						
						Swal.fire({
							  text: message,
							  icon: 'success',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							}).then((answer) => {
							  /* Read more about isConfirmed, isDenied below */
							  if (answer.isConfirmed) {  
								location.href = "list.do";
							  }
							});
					} else if(result=="using"){						
						Swal.fire({
							  text: '사용중인 DB정보는 수정할 수 없습니다.',
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							}).then((answer) => {
							  /* Read more about isConfirmed, isDenied below */
							  if (answer.isConfirmed) {  
								  $('#formModal').modal('hide');
							  }
							});
					} else {						
						Swal.fire({
							  text: '예약 중인 DB정보는 수정할 수 없습니다.',
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							}).then((answer) => {
							  /* Read more about isConfirmed, isDenied below */
							  if (answer.isConfirmed) {  
								  $('#formModal').modal('hide');
							  }
							});
					}
			}
		});
	}
	function removeDbcn(dbcnNo){
		Swal.fire({
			  text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
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
						url: "<%=request.getContextPath()%>/admin/dbcn/remove.do?dbcnNo="+dbcnNo, 
						type: "POST",
						success: function(result){
							if(result=="success"){						
								Swal.fire({
									  text: '삭제되었습니다.',
									  icon: 'success',
									  confirmButtonColor: '#007bff',
									  confirmButtonText: '확인'
									}).then((result) => {
									  /* Read more about isConfirmed, isDenied below */
									  if (result.isConfirmed) {  
										location.href = "list.do";
									  }
									});
							} else if(result=="using"){						
								Swal.fire({
									  text: '사용중인 DB정보는 삭제할 수 없습니다.',
									  icon: 'error',
									  confirmButtonColor: '#007bff',
									  confirmButtonText: '확인'
									});
							} else {						
								Swal.fire({
									  text: '예약 중인 DB정보는 삭제할 수 없습니다.',
									  icon: 'error',
									  confirmButtonColor: '#007bff',
									  confirmButtonText: '확인'
									});
							}
						}
					});		  
			  }
			});
		
	}
</script>
