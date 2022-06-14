<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<style type="text/css">
	td{
		border: 1px solid gray;
		/* text-align : center; */
		border-collapse: collapse;
		color:black;
	}
</style>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>

<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prjNm}</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
					<li class="breadcrumb-item"><a href="list.do">업무보고</a></li>
					<li class="breadcrumb-item active">상세</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">

		<div class="card-header">
			<div class="row">
				<div class="form-group col-9 d-flex justify-content-left mb-0" style="vertical-align:middle;">
					<%-- <span>${report.title }</span> --%>
					<h5 style="padding-top: 5px;"><b>${report.title }</b></h5>
				</div>
				<c:if test="${param.from eq 'temp' }">
					<div class="card-tools col-3 d-flex justify-content-end align-items-center">
						<button type="button" class="btn btn-danger mr-1" id="deleteBtn" onclick="removeTempReport('del')">삭 제</button>
						<button type="button" class="btn btn-secondary mr-1" id="tempBtn" onclick="registTempReport('temp')">임시저장</button>
						<button type="button" class="btn btn-primary mr-1" id="registBtn" onclick="registReport('send')">등 록</button>
						<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'">목 록</button>
					</div>
				</c:if>
					<div class="card-tools col-3 d-flex justify-content-end align-items-center">
						<c:if test="${param.from eq 'mine'}">
							<button type="button" class="btn btn-warning" id="cancelBtn"
								onclick="location.href='list.do'">뒤 로</button>
						</c:if>
						<c:if test="${param.from eq 'receive' }">
							<button type="button" class="btn btn-warning" id="cancelBtn"
								onclick="location.href='getList.do'">뒤 로</button>
						</c:if>
					</div>
			</div>
		</div>
		<!--end card-header  -->
		<div class="register-card-body" style="padding-left:123px;padding-right:123px;min-height: 500px;">
			<c:if test="${param.from eq 'temp' }">
				<form class="" id="monthForm"  enctype="multipart/form-data" role="form" method="post" action="registMonthly.do" name="registMonthlyForm">
				<!-- 일업무보고 양식 영역 시작 -->
				<div>
				<!-- 일일업무보고 > pl -->
					<c:if test="${plpm eq 'PL'}">
						<input type="hidden" name="title" value="${report.title }">
						<input type="hidden" name="approver" value="${report.approver }">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						<input type="hidden" name="repNo" value="${report.repNo }">
						<table style ="border-collapse: collapse" id="dailyForm" class="col-12">
							<tr>
						        <td rowspan="3" style="text-align: center;font-size:20px">
						        	<b>
						        		<fmt:formatDate value="${report.submitDt}" pattern="MM" var="month"/>
						        		${month}월 업무보고
						        	</b>
						        </td>
						        <td style="text-align: center;width:100px"><b>소     속</b></td>
						        <td style="text-align: center;">${teamNm }</td>
						    </tr>
						    <tr>
						        <td style="text-align: center;"><b>작 성 자</b></td>
						        <td style="text-align: center;">${userNm }</td> 
						    </tr>
						    <tr>
						        <td style="text-align: center;"><b>작 성 일</b></td>
						        <td style="text-align: center;">
						        	<jsp:useBean id="now" class="java.util.Date" />
									<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
									<c:out value="${today}"/>
						        </td>
	  					    </tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>이번달 업무내용</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="repContent" id="today" rows="5">${report.repContent}</textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>다음달 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="tomorrow" rows="5" >${report.subContent}</textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="etc" rows="5" >${report.etcContent}</textarea>
								</td>
							</tr>
						</table>
					</c:if>
				<!-- 일일업무보고 > 일반직원 -->
					<c:if test="${plpm ne 'PL' and plpm ne 'PM'}">
						<input type="hidden" name="title" value="${report.title }">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						<input type="hidden" name="repNo" value="${report.repNo }">
						<table style ="border-collapse: collapse" id="monthlyForm"  class="col-12">
							<tr>
						        <td rowspan="4" style="text-align: center;font-size:20px">
						        	<b>
						        		<fmt:formatDate value="${report.submitDt}" pattern="MM" var="month"/>
						        		${month}월 업무보고
						        	</b>
						        </td>
						        <td style="text-align: center;width:100px;height:35px"><b>소     속</b></td>
						        <td style="text-align: center;">${teamNm }</td>
						    </tr>
						    <tr>
						        <td style="text-align: center;height:35px"><b>작 성 자</b></td> <!-- <td style="text-align: center;">데이터3</td> -->
						        <td style="text-align: center;">${userNm }</td> <!-- <td style="text-align: center;">데이터3</td> -->
						    </tr>
						    <tr>
						        <td style="text-align: center;height:35px"><b>작 성 일</b></td>
						        <td style="text-align: center;">
						        	<jsp:useBean id="now2" class="java.util.Date" />
									<fmt:formatDate value="${now2}" pattern="yyyy-MM-dd" var="today" />
									<c:out value="${today}"/>
						        </td>
	  					    </tr>
						    <tr>
						        <td style="text-align: center;"><b>수신자</b></td>
						        <td style="text-align: center;">
						        	<select class="custom-select rounded-0" role="textbox" name="approver">
							        	<c:if test="${!empty plIdList}">
						        			<c:forEach items="${plIdList}" var="plList">
								        		<option style="text-align:center" value="${plList.userId}">${plList.userNm}</option>
							        		</c:forEach>
								        </c:if>
								        <c:if test="${empty plIdList }">
							        		<option style="text-align:center">pl없음</option>
								        </c:if>
						        	</select>
						        </td>
	  					    </tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>이번달 업무내용</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="repContent" id="today" rows="5">${report.repContent}</textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>다음달 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="tomorrow" rows="5" placeholder="내용을 작성하세요.">${report.subContent}</textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="etc" rows="5">${report.etcContent}</textarea>
								</td>
							</tr>
						</table>
					</c:if>
					<!-- <div class="form-group">								
						<div class="card card-outline ">
							<div class="card-header">
								<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
								&nbsp;&nbsp;<button class="btn btn-xs btn-primary"
								onclick="addFile_go();"	type="button" id="addFileBtn">Add File</button>
							</div>									
							<div class="card-footer fileInput">
							</div>
						</div>
					</div> -->
				</div>
			</form>
			</c:if>
			
			
			
			<c:if test="${param.from ne 'temp' }">
				<form class="" id="monthForm"  enctype="multipart/form-data" role="form" method="post" action="registMonthly.do" name="registMonthlyForm">
					<!-- 업무보고 양식 영역 시작 -->
					<div class="col-12">
							<table style ="border-collapse: collapse" id="dailyForm"  class="col-12">
								<tr>
							        <td rowspan="4" style="text-align: center;font-size:20px">
							        	<b>
							        		<fmt:formatDate value="${report.submitDt}" pattern="MM" var="month"/>
							        		${month}월 업무보고
							        	</b>
							        </td>
							        <td style="text-align: center;width:100px;height:35px"><b>소     속</b></td>
							        <td style="text-align: center;">${teamNm }</td>
							    </tr>
							    <tr>
							        <td style="text-align: center;height:35px"><b>작 성 자</b></td> <!-- <td style="text-align: center;">데이터3</td> -->
							        <td style="text-align: center;">${registerNm }</td> <!-- <td style="text-align: center;">데이터3</td> -->
							    </tr>
							    <tr>
							        <td style="text-align: center;height:35px"><b>작 성 일</b></td>
							        <td style="text-align: center;">
							        	<fmt:formatDate value="${report.submitDt}" pattern="yyyy-MM-dd"/>
							        </td>
		  					    </tr>
							    <tr>
							        <td style="text-align: center;"><b>수신자</b></td>
							        <td style="text-align: center;">${approverNm}</td>
		  					    </tr>
								<tr style="background-color:#dadfe4;height:30px;text-align:center">
									<td colspan="3"><b>이번달 업무내용</b></td>
								</tr>
								<tr>
									<td colspan="3">
										<div style="min-height:250px;padding-left: 20px;padding-top: 20px;padding-right: 20px;padding-bottom: 20px;">${report.repContent}</div>
									</td>
								</tr>
								<tr style="background-color:#dadfe4;height:30px;text-align:center">
									<td colspan="3"><b>다음달 업무계획</b></td>
								</tr>
								<tr>
									<td colspan="3">
										<div style="min-height:150px;padding-left: 20px;padding-top: 20px;padding-right: 20px;padding-bottom: 20px;">${report.subContent}</div>
									</td>
								</tr>
								<tr style="background-color:#dadfe4;height:30px;text-align:center">
									<td colspan="3"><b>업무 참고사항</b></td>
								</tr>
								<tr>
									<td colspan="3">
										<div style="min-height:150px;padding-left: 20px;padding-top: 20px;padding-right: 20px;padding-bottom: 20px;">${report.etcContent}</div>
									</td>
								</tr>
							</table>
					</div>
					<!-- 업무보고 양식 영역 끝 -->
				</form>
			</c:if>
				<!--end card-footer  -->
		</div>
		<!-- end card -->
	</div>
	<!-- content-wrapper -->
</section>

<script>

window.onload = function() {
	$('#today').summernote({placeholder:'내용을 입력하세요.',height:300});
	$('#tomorrow').summernote({placeholder:'내용을 입력하세요.',height:100});
	$('#etc').summernote({placeholder:'내용을 입력하세요.',height:100});
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
}

</script>

<script>
function registReport(str){
	let monthlyForm = document.registMonthlyForm;
	let sttCode = str;
	
	$('input[name=sttCode]').attr('value',sttCode);
	
	if(monthlyForm.repContent.value==""){
		alertI("월 업무보고 내용을 입력하세요");
		return;
	}
	monthlyForm.submit();
}

function registTempReport(str){
	let monthlyForm = document.registMonthlyForm;
	let sttCode = str;
	
	$('input[name=sttCode]').attr('value',sttCode);
	if(monthlyForm.repContent.value==""){
		alertI("월 업무보고 내용을 입력하세요");
		return;
	}
	monthlyForm.submit();
}

function removeTempReport(str){
	let monthlyForm = document.registMonthlyForm;
	let sttCode = str;
	
	$('input[name=sttCode]').attr('value',sttCode);	
	
	 Swal.fire({
		  text: '임시저장된 업무보고서를 삭제하시겠습니까?',
		  icon: 'warning',
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  cancelButtonText: '취소',
		}).then((result) => {
			if (result.isConfirmed) {
				monthlyForm.submit();
			}
		})
}
	
</script>

</body>
	