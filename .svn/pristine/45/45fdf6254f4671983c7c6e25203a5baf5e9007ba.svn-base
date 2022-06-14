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
</head>

<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prjNm }</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
					<li class="breadcrumb-item"><a href="list.do">업무보고</a></li>
					<li class="breadcrumb-item active">등록</li>
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
				<div class="form-group col-2 d-flex justify-content-end mb-0">
					<select id="selectbox" onchange="chkReportForm();"  class="form-control col-12" style="display: inline;">
						<option value="day">일일보고</option>
						<option value="month">월간보고</option>
					</select>
				</div>
				<div class="card-tools col-10 d-flex justify-content-end align-items-center">
					<button type="button" class="btn btn-outline-success mr-1" id="sampleBtn" onclick="inputSampleText(textObj);">샘 플</button>
					<button type="button" class="btn btn-secondary mr-1" id="registBtn"
						onclick="registTempReport('temp');">임시저장</button>
					<button type="button" class="btn btn-primary mr-1" id="registBtn"
						onclick="registReport();">보 고</button>
					<button type="button" class="btn btn-warning" id="cancelBtn"
						onclick="location.href='list.do'">취 소</button>
				</div>
			</div>
		</div>
		<!--end card-header  -->
		<div class="register-card-body" style="padding-left:123px;padding-right:123px;min-height: 500px;">
			<form class="" id="dayForm"  enctype="multipart/form-data" role="form" method="post" action="registDaily.do" name="registDailyForm">
				<!-- 일업무보고 양식 영역 시작 -->
				<div>
				<!-- 일일업무보고 > pl -->
					<c:if test="${plpm eq 'PL'}">
						<input type="hidden" name="approver" value="${pmId }">
						<input type="hidden" name="title" value="[일일업무보고] ${userNm } 일일 업무보고 입니다.">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						
						<table style ="border-collapse: collapse" id="dailyForm" class="col-12">
							<tr>
						        <td rowspan="3" style="text-align: center;font-size:20px"><b>일일업무보고</b></td>
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
								<td colspan="3"><b>금일 업무내용</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="repContent" id="today" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>내일 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="tomorrow" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="etc" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
						</table>
					</c:if>
				<!-- 일일업무보고 > 일반직원 -->
					<c:if test="${plpm ne 'PL' and plpm ne 'PM'}">
						<input type="hidden" name="title" value="[일일업무보고] ${userNm } 일일 업무보고 입니다.">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						<table style ="border-collapse: collapse" id="monthlyForm"  class="col-12">
							<tr>
						        <td rowspan="4" style="text-align: center;font-size:20px"><b>일일업무보고</b></td>
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
								<td colspan="3"><b>금일 업무내용</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="repContent" id="today" rows="5" placeholder="내용을 작성하세요." ></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>내일 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="tomorrow" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="etc" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
						</table>
					</c:if>
				</div>
			</form>
				<!-- 일일업무보고 양식 영역 끝 -->
				
				<!-- 월간무보고 양식 영역 시작 -->
			<form class="d-none" id="monthForm" enctype="multipart/form-data" role="form" method="post" action="registMonthly.do" name="registMonthlyForm">
				<div >
				<!-- 일일업무보고 > pl -->
					<c:if test="${plpm eq 'PL'}">
						<jsp:useBean id="month" class="java.util.Date" />
						<fmt:formatDate value="${month}" pattern="MM" var="month" />
						<%-- <c:out value="${month}"/> --%>
						<input type="hidden" name="approver" value="${pmId }">
						<input type="hidden" name="title" value="[${month}월 업무보고] ${userNm } 월간 업무보고 입니다.">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						<table style ="border-collapse: collapse" id="dailyForm" class="col-12">
							<tr>
						        <td rowspan="3" style="text-align: center;font-size:20px"><b>${month}월 업무보고</b></td>
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
						        	<jsp:useBean id="now3" class="java.util.Date" />
									<fmt:formatDate value="${now3}" pattern="yyyy-MM-dd" var="today" />
									<c:out value="${today}"/>
						        </td>
	  					    </tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>이번달 업무내용</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="repContent" id="a" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>다음달 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="b" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="c" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
						</table>
					</c:if>
				<!-- 일일업무보고 > 일반직원 -->
					<c:if test="${plpm ne 'PL' and plpm ne 'PM'}">
						<jsp:useBean id="month1" class="java.util.Date" />
						<fmt:formatDate value="${month1}" pattern="MM" var="month1" />
						<%-- <c:out value="${month1}"/> --%>
						<input type="hidden" name="title" value="[${month1}월 업무보고] ${userNm } 월간 업무보고 입니다.">
						<input type="hidden" name="sttCode" value="" id="sttCode">
						<table style ="border-collapse: collapse" id="monthlyForm"  class="col-12">
							<tr>
						        <td rowspan="4" style="text-align: center;font-size:20px"><b>${month1}월 업무보고</b></td>
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
						        	<jsp:useBean id="now4" class="java.util.Date" />
									<fmt:formatDate value="${now4}" pattern="yyyy-MM-dd" var="today" />
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
									<textarea class="form-control" name="repContent" id="this" rows="5" placeholder="내용을 작성하세요." ></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>다음달 업무계획</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="subContent" id="next" rows="5" placeholder="내용을 작성하세요."></textarea>
								</td>
							</tr>
							<tr style="background-color:#dadfe4;height:30px;text-align:center">
								<td colspan="3"><b>업무 참고사항</b></td>
							</tr>
							<tr>
								<td colspan="3">
									<textarea class="form-control" name="etcContent" id="etc2" rows="5" placeholder="내용을 작성하세요." style="text-align:left"></textarea>
								</td>
							</tr>
						</table>
					</c:if>
				</div>
			</form>
			<!-- 월간무보고 양식 영역 끝 -->
			<!--end card-footer  -->
		</div>
		<!-- end card -->
	</div>
	<!-- content-wrapper -->

</section>

<script>
let textObj = {
		 "repContent":"- 클라우드 화면 css 수정"
		,"subContent":"간트차트 우클릭 시 해당 이슈 리스트 목록 이동, 일감명 -> 일감 번호로 수정이 필요합니다."
		,"etcContent":"지연 이슈 - 없음"
};
window.onload = function() {
	$('#today').summernote({placeholder:'내용을 입력하세요.',height:300});
	$('#tomorrow').summernote({placeholder:'내용을 입력하세요.',height:200});
	$('#etc').summernote({placeholder:'내용을 입력하세요.',height:100});
	$('#a').summernote({placeholder:'내용을 입력하세요.',height:300});
	$('#b').summernote({placeholder:'내용을 입력하세요.',height:200});
	$('#c').summernote({placeholder:'내용을 입력하세요.',height:100});
	$('#this').summernote({placeholder:'내용을 입력하세요.',height:300});
	$('#next').summernote({placeholder:'내용을 입력하세요.',height:200});
	$('#etc2').summernote({placeholder:'내용을 입력하세요.',height:100});
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
}

function chkReportForm(){
	let selectVal = $("#selectbox option:selected").val();
	
	if(selectVal=="month"){
		$('#monthForm').attr('class','');
		$('#dayForm').attr('class','d-none');
	} else {
		$('#monthForm').attr('class','d-none');
		$('#dayForm').attr('class','');
	}		
}

function registReport(){
	
	let selectVal = $("#selectbox option:selected").val();
	let dailyForm = document.registDailyForm;
	let monthlyForm = document.registMonthlyForm;
	
	if(selectVal=="day"){
		if(dailyForm.repContent.value==""){
			alertI("일 업무보고 내용을 입력하세요");
			return;
		}
		dailyForm.submit();
	}
	
	if(selectVal=="month"){
		if(monthlyForm.repContent.value==""){
			alertI("업무보고 내용을 입력하세요");
			return;
		}
		monthlyForm.submit();
	}
}

function registTempReport(str){
	
	let selectVal = $("#selectbox option:selected").val();
	let dailyForm = document.registDailyForm;
	let monthlyForm = document.registMonthlyForm;
	let sttCode = str;
	
	if(selectVal=="day"){
		$('input[name=sttCode]').attr('value',sttCode);
		if(dailyForm.repContent.value==""){
			alertI("일 업무보고 내용을 입력하세요");
			return;
		}
		dailyForm.submit();
	}
	
	if(selectVal=="month"){
		$('input[name=sttCode]').attr('value',sttCode);
		if(monthlyForm.repContent.value==""){
			alertI("업무보고 내용을 입력하세요");
			return;
		}
		monthlyForm.submit();
	}
}
</script>
</body>
	