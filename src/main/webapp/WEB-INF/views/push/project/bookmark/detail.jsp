<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<title>PROSPEC'S | 책갈피</title>
<head>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
</head>
<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
 				<%-- <h1>${projectNm.projectNm }</h1>  --%>
 				<h1>${prjNm}</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
					<li class="breadcrumb-item"><a href="list.do">책갈피</a></li>
					<li class="breadcrumb-item active">상세</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<section>

</section>

<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">

	<div class="d-flex justify-content-end">
		<div class="btn-group mb-1">
			<button type="button" class="btn btn-md btn-outline-secondary"
				onclick="location.href='<%=request.getContextPath() %>/project/issue/list.do'">이슈</button>
			<button type="button" class="btn btn-md btn-secondary"
				onclick="location.href='<%=request.getContextPath() %>/project/bookmark/list.do'">책갈피</button>
		</div>
	</div>	
	<div class="card card-secondary card-outline">
		    <div class="card-body p-0">
			    <div class="card-header with-border">
					<button type="button" onclick="location.href='<%=request.getContextPath() %>/project/issue/registForm.do?bookmarkNo=${bookmark.bookmarkNo }'" class="btn btn-primary ">이슈등록</button>
					<div class="btn-group">
						<button type="button" class="btn btn-block bg-gradient-info"
							onclick="openIssue();"><i class="fa-solid fa-lock-open"></i></button>							
						<button type="button" class="btn btn-block btn-outline-secondary mt-0"
							onclick="closeIssue();"><i class="fa-solid fa-lock"></i></button>
					</div>
					<button class="btn btn-warning float-right" type="button" id="searchBtn"
						data-card-widget="search" onclick="location.href='<%=request.getContextPath() %>/project/bookmark/list.do'">
						뒤 로
					</button>
				</div>
		        <table class="table table-striped">
		            <tbody>
		                <tr>
		                    <td style="width:60%">
		                   		<div>
			                   		<h4>
			                   			<b class="mr-3">${bookmark.bmkTitle }</b>
				                   		<span style="font-size:15px">
				                        	[등록일자 : <fmt:formatDate value="${bookmark.regDt }" pattern="yyyy-MM-dd"/>]
				                        </span>
			                   		</h4>
			                   		<small>
			                   			${bookmark.bmkContent }
			                   		</small>
		                   		</div>
		                    </td>
		                    <td>
		                    	<div>
			                        <div class="progress progress-lg">
			                        	<c:if test="${bookmark.totalIssueCnt eq 0}" >
												<div class="progress-bar bg-secondary disabled" role="progressbar" aria-valuenow="${bookmark.progress }" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
													<span>이슈 없음</span>
												</div>
										</c:if>
											<c:if test="${bookmark.totalIssueCnt > 0 and bookmark.progress < 1}" >
												<div class="progress-bar bg-danger" role="progressbar" aria-valuenow="${bookmark.progress }" aria-valuemin="0" aria-valuemax="100" style="width: 7%">
													<span>${bookmark.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bookmark.progress <= 30 }" >
												<div class="progress-bar bg-danger" role="progressbar" aria-valuenow="${bookmark.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bookmark.progress }%">
													<span>${bookmark.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bookmark.progress <= 60 and bookmark.progress > 30}" >
												<div class="progress-bar bg-warning" role="progressbar" aria-valuenow="${bookmark.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bookmark.progress }%">
													<span>${bookmark.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bookmark.progress > 60}" >
												<div class="progress-bar bg-success" role="progressbar" aria-valuenow="${bookmark.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bookmark.progress }%">
													<span>${bookmark.progress }%</span>
												</div>
											</c:if>
			                        </div>
			                        <span>
			                        	전체이슈: ${bookmark.totalIssueCnt } | 미완료: ${bookmark.totalOpenedIssueCnt } | 완료: ${bookmark.totalClosedIssueCnt }
			                        </span>
			                	</div>
		                    </td>
		                </tr>
		            </tbody>
		        </table>
		</div>
	</div>
            <!-- /.card-header -->
    <div class="card-body table-responsive p-0">
        <table class="table table-hover text-nowrap">
            <thead>
                <tr>
                    <th style="vertical-align:middle;text-align: center">
                    	<input type="checkbox" name="" id="cbx_chkAll" />
                    </th>
                    <th style="vertical-align:middle;">제목</th>
                    <th style="vertical-align:middle;text-align: center">상태</th>
                    <th style="vertical-align:middle;text-align: center" width="100">긴급도</th>
                    <th style="vertical-align:middle;text-align: center">작성자</th>
                    <th style="vertical-align:middle;text-align: center">등록일자</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${empty issueList}" >
            	<td colspan="6" align='center'>
            		<h6>등록된 이슈가 없습니다.</h6>
           		</td>
            </c:if>
           	<c:if test="${!empty issueList}">
              <c:forEach items="${issueList }" var="issue">
                 <tr>
                     <td style="vertical-align:middle;text-align: center">
                     	<input type="checkbox" name="issueNo" value=${issue.issueNo }>
                     </td> 
                     <td style="width: 30%; overflow: hidden;">
                     	<a href="<%=request.getContextPath() %>/project/issue/detail.do?issueNo=${issue.issueNo }">${issue.title }</a><br/>
                     	<c:if test="${!empty issue.bookmarkTitle }">
	                      	<small>
		                       	<i class="fa-solid fa-tag"></i>
		                       	${issue.bookmarkTitle }
	                      	</small>
                      	</c:if>
                     </td>
                     
                     <c:if test="${issue.sttCode eq 'open'}">
                     	<td style="vertical-align:middle;text-align: center"><i class="fa-solid fa-lock-open"></i></td>
                     </c:if>
                     <c:if test="${issue.sttCode eq 'close'}">
                     	<td style="vertical-align:middle;text-align: center"><i class="fa-solid fa-lock"></i></td>
                     </c:if>
                     
                     <td style="vertical-align:middle">
	                      <c:if test="${issue.gradeCode eq 'urgent' }">
	                      	<span class="bg-danger px-5 py-1" style="font-size:12px">긴급</span>
	 	                    </c:if>
	                      <c:if test="${issue.gradeCode eq 'ordinary' }">
	                       <span class="bg-warning px-5 py-1" style="font-size:12px">보통</span>
	                      </c:if>
	                      <c:if test="${issue.gradeCode eq 'low' }">
	                       <span class="bg-success px-5 py-1" style="font-size:12px">낮음</span>
	                      </c:if>
                     </td>
                     <td style="vertical-align:middle;text-align: center">${issue.userNm }</td>
                     <td style="vertical-align:middle;text-align: center">
                     	<fmt:formatDate value="${issue.regDt }" pattern="yyyy-MM-dd"/>
                     </td>
                 </tr>
              </c:forEach>  
            </c:if>  
            </tbody>
        </table>
        
		<div class="card-footer">
			<nav aria-label="pds list Navigation">
				<ul class="pagination justify-content-center m-0">
						<%-- <%@ include file="/WEB-INF/views/common/issuePagination.jsp" %> --%>	
				</ul>
			</nav>
		</div>
	<!-- pagenation end -->
	</div>
</section>

<script>

/* 이슈 전체 선택*/
window.onload = function(){
	$(document).ready(function() {
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")){
				$("input[name=issueNo]").prop("checked", true);
			}
			else $("input[name=issueNo]").prop("checked", false);
		});
		
		$("input[name=issueNo]").click(function() {
			var total = $("input[name=issueNo]").length;
			var checked = $("input[name=issueNo]:checked").length;
			
			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
	});

} 

/* 선택된 이슈 닫기 */
function closeIssue(prjNo){
	
	let targetIssueNoList = [];
	
	
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    return results[1] || 0;
	}

	$.urlParam('bookmarkNo'); 
	
	let checkedIssueList = document.querySelectorAll("input[name=issueNo]:checked");
	if(checkedIssueList.length==0){
		Swal.fire({
					  text: '선택된 이슈가 없습니다.',
					  icon: 'error',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
		});
		return;
	}

	for(item of checkedIssueList){
		targetIssueNoList.push(parseInt(item.value));
	}
	
	
	$.ajax({ 
		url: "<%=request.getContextPath()%>/project/issue/modifyStt/close.do",
		type: "POST",
		data: JSON.stringify(targetIssueNoList),
		contentType: "application/json",
		success: function(result){ 
			if(result){
				Swal.fire({
					  text: '선택 이슈가 정상적으로 닫혔습니다.',
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
				  text: '이슈 닫기에 실패했습니다.' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				});
		}
	});
	
}

/* 선택된 이슈 열기 */
function openIssue(prjNo){
	
	// alert(prjNo);
	let targetIssueNoList = [];
	
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    return results[1] || 0;
	}

	$.urlParam('bookmarkNo');  
	
	let checkedIssueList = document.querySelectorAll("input[name=issueNo]:checked");
	if(checkedIssueList.length==0){
		Swal.fire({
					  text: '선택된 이슈가 없습니다.',
					  icon: 'error',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
		});
		return;
	}

	for(item of checkedIssueList){
		targetIssueNoList.push(parseInt(item.value));
	}
	
	$.ajax({ 
		url: "<%=request.getContextPath()%>/project/issue/modifyStt/open.do",
		type: "POST",
		data: JSON.stringify(targetIssueNoList),
		contentType: "application/json",
		success: function(result){ 
			if(result){
				Swal.fire({
					  text: '선택 이슈가 정상적으로 열렸습니다.',
					  icon: 'success',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
				}).then((result) => {
					location.reload(true);
				});
			}
		}, 
		error: function (request, status, error){ 
			Swal.fire({
				  text: '이슈 열기에 실패했습니다.' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				});
		}
	});
};

</script>


</body>

