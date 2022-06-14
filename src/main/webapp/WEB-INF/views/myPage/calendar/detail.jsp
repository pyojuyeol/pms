<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="prjUserList" value="${dataMap.prjUserList }"/>
<head>

<style></style>

</head>
<title>PROSPEC'S</title>

<body>
				<section class="content-header">
				  	<div class="container-fluid">
				  		<div class="row mb-2">
				  			<div class="col-sm-6">
				  				<h1>일정 상세보기</h1>  				
				  			</div>
				  			<div class="col-sm-6">
				  				<ol class="breadcrumb float-sm-right">
							        <li class="breadcrumb-item">
							        	<a href="list.do">
								        	일정관리
								        </a>
							        </li>
							        <li class="breadcrumb-item active">
							        	상세보기
							        </li>		        
					    	  </ol>
				  			</div>
				  		</div>
				  	</div>
				</section>
				 
			  <!-- Main content -->
			    <section class="content container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-outline card-info">
								<div class="card-header">
									<h3 class="card-title p-1"></h3>
									<div class ="card-tools">
									  <c:if test="${loginUser.userId eq schedule.register }">
										<button type="button" class="btn btn-primary" id="registBtn" onclick="submit_go('modifyForm.open','${schedule.schedNo}');">수 정</button>
										<button type="button" class="btn btn-danger ml-2" id="removeBtn" onclick="removeNotice(${schedule.schedNo});" >삭 제</button>
									  </c:if>
										<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="CloseWindow();" >취 소</button>
									</div>
								</div><!--end card-header  -->
								<div class="card-body pad">
									<form role="form" method="post" action="modify.do" name="modifyForm">
											<input type="hidden" id="writer" readonly
												name="register" class="form-control" value="${loginUser.userId }">
											<input type="hidden" id="schedNo" readonly
												name="schedNo" class="form-control" value="${schedule.schedNo }">
										<div class="form-group">
											<label for="title">일정 제목</label> 
											<input type="text" id="title"
												name='title' class="form-control" placeholder="제목을 쓰세요" value="${schedule.title }" readonly>
										</div>			
										<div class="form-group row">
										  <div class="col-6">
											<label for="bgnDt">시작 날짜</label> 
											<input type="text" id="bgnDt"
												name='bgnDt' class="form-control" value="<fmt:formatDate value="${schedule.bgnDt }" pattern="yyyy-MM-dd"/>" readonly>
												
										  </div>
										  <div class="col-6">
											<label for="endDt">끝 날짜</label> 
											<input type="text" id="endDt"
												name='endDt' class="form-control" value="<fmt:formatDate value="${schedule.endDt }" pattern="yyyy-MM-dd"/>" readonly>
										  </div>
										</div>
										
										<div class="form-group row">
										  <div class="col-6">
											<label for="'register'">작성자</label> 
											<input type="text" id="'register'"
												name='register' class="form-control" value="${schedule.register }" readonly>
										  </div>
										  <div class="col-6">
											<label for="keyCode" style="font-size:0.9em;" >일정 구분</label>
											<select id="keyCode" name="keyCode" class="form-control" style="font-size:0.9em;" disabled>
												<option value="1" ${schedule.keyCode eq '1' ? 'selected':'' }>회사 일정</option>
												<option value="2" ${schedule.keyCode eq '2' ? 'selected':'' }>개인 일정</option>
											</select>
										  </div>
										</div>
										
										<div class="form-group row">
										  <div id="ipcrCode" class="col-6">
											<label for="ipcrCode" style="font-size:0.9em;" >개인일정 공유하기</label>
											<select id="ipcrSelect" name="ipcrCode" class="form-control" style="font-size:0.9em;" disabled>
												<option value="1" ${schedule.ipcrCode eq '1' ? 'selected':'' }>개인일정 미공유</option>
												<option value="2" ${schedule.ipcrCode eq '2' ? 'selected':'' }>개인일정 공유</option>
											</select>
										  </div>
										  <div id="project" class="col-6">
										  	<c:if test="${!empty prjUserList }" >	
												<label for="prjNo" style="font-size:0.9em;" >공유할 프로젝트</label>
													<select name="prjNo" class="form-control" style="font-size:0.9em;" disabled>
														<c:forEach items="${prjUserList }" var="list">
																	<option value="${list.prjNo}" selected>${list.prjNm}</option>
														</c:forEach>
													</select>
											</c:if>				
										  </div>
										</div>
														
										<div class="form-group row">
										  <div class="col-12">
											<label for="content">일정 내용</label>
											<div>${schedule.content }</div>
										  </div>
										</div>
									</form>
								</div><!--end card-body  -->
							</div><!-- end card -->				
						</div><!-- end col-md-12 -->
					</div><!-- end row -->
			    </section>
		    <!-- /.content -->
 


  
<script>
function submit_go(url,schedNo){	
	location.href=url+"?schedNo="+schedNo;
}
</script>

<script>
function removeNotice(schedNo) {
	
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
			   submit_go('remove.open',schedNo);
		  }
		});
	
}
</script>

<script>
window.addEventListener('DOMContentLoaded', detailSchedule);

function detailSchedule() {
	
	<c:if test="${from eq 'modify'}" >
		   	alertC("수정되었습니다.");
	</c:if>
	
	if($('#keyCode').val() == 2) {
		document.getElementById("ipcrCode").classList.add('d-block')
	} else {
		document.getElementById("ipcrCode").classList.add('d-none')
	}	
		
	if($('#ipcrSelect').val() == 2) {
		document.getElementById("project").classList.add('d-block')
	} else {
		document.getElementById("project").classList.add('d-none')
	}		
	
}

	
</script>

<script>

function CloseWindow() {
	window.opener.location.reload(true);		
	window.close();
}


</script>


    
 </body>
 
 
 
 
 
 
 
 
 
 
 