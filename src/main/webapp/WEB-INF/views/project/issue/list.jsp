<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="issueList" value="${dataMap.issueList }" /> 
<c:set var="cri" value="${pageMaker.cri }" />


<title>PROSPEC'S | ISSUE</title>
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
					<li class="breadcrumb-item active">이슈</li>
				</ol>
			</div>
		</div>
	</div>
</section>


<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">

	<div class="d-flex justify-content-end">
		<div class="btn-group mb-1">
			<button type="button" class="btn btn-md btn-secondary"
				onclick="location.href='list.do'">이슈</button>
			<button type="button" class="btn btn-md btn-outline-secondary"
				onclick="location.href='<%=request.getContextPath() %>/project/bookmark/list.do'">책갈피</button>
		</div>
	</div>	
	<div class="card card-secondary card-outline">
		<div class="card-header with-border">
					<button type="button" onclick="location.href='registForm.do'"
						class="btn btn-primary ">등록</button>
					
					<!-- <div class="float-right"> -->
						<div class="btn-group">
							<button type="button" class="btn btn-block bg-gradient-info"
								onclick="openIssue();"><i class="fa-solid fa-lock-open"></i></button>							
							<button type="button" class="btn btn-block btn-outline-secondary mt-0"
								onclick="closeIssue();"><i class="fa-solid fa-lock"></i></button>
							<select name="bookmarkTitle" id="setBMK" class="custom-select rounded-0 col-md-11 col-md-11 ml-1">
<!-- 							<select name="prjTitle" class="form-control col-md-11" onchange=""> -->
								<c:if test="${empty bmkList }">
									<option value="nothing">북마크 없음</option>
								</c:if>
								<c:if test="${!empty bmkList}">
									<option value="0" id="bmkTitle">책갈피 선택</option>
									<c:forEach items="${bmkList }" var="bmk">
										<option  value="${bmk.bookmarkNo}" id="bmkTitle">${bmk.bookmarkTitle }</option>
									</c:forEach>
									<option value="0" id="bmkTitle">선택 안함</option>
								</c:if>
							</select>
						</div>
					<!-- </div> -->
			<div id="keyword" class="card-tools" style="width: 550px;">
				<div class="input-group row" style="top: 5px">
					<select name="sttCode" class="form-control col-md-3" onchange="list_go(1);">
						<option value="" ${cri.sttCode eq '' ? 'selected' : '' }>상태</option>
						<option value="open" ${cri.sttCode eq 'open' ? 'selected' : '' }>열림</option>
						<option value="close" ${cri.sttCode eq 'close' ? 'selected' : '' }>닫힘</option>
					</select>
					<!-- search bar -->
				
					<!-- sort num -->
					<select class="form-control col-md-3" name="gradeCode"
						id="gradeCode"  onchange="list_go(1);">
						<option value=""  ${cri.gradeCode eq '' ? 'selected':''}>우선순위</option>
						<option value="l" ${cri.gradeCode eq 'l' ? 'selected':''}>낮음</option>
						<option value="u" ${cri.gradeCode eq 'u' ? 'selected':''}>보통</option>
						<option value="h" ${cri.gradeCode eq 'h' ? 'selected':''}>높음</option>
						<option value="e" ${cri.gradeCode eq 'e' ? 'selected':''}>긴급</option>
					</select>                         

					<!-- search bar -->
					<select class="form-control col-md-3" name="searchType"
						id="searchType">
						<option value="tcw"  ${cri.searchType eq 'tcwd' ? 'selected':''}>검색구분</option>
						<option value="t" ${cri.searchType eq 't' ? 'selected':''}>제목</option>
						<option value="w" ${cri.searchType eq 'w' ? 'selected':''}>작성자</option>
						<option value="c" ${cri.searchType eq 'c' ? 'selected':''}>내용</option>
						<option value="d" ${cri.searchType eq 'd' ? 'selected':''}>일감제목</option>
						<option value="n" ${cri.searchType eq 'n' ? 'selected':''}>일감번호</option>
					</select>   
					                                 
					<!-- keyword -->
					<input class="form-control" type="text" name="keyword"
						placeholder="검색어를 입력하세요." value="${param.keyword }"> <span
						class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn"
							data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				</div>
			</div>
		</div>

            <!-- /.card-header -->
            <div class="card-body minH table-responsive p-0">
                <table class="table table-hover text-nowrap">
                    <thead>
                        <tr>
                            <th style="vertical-align:middle;text-align: center;width:8%">
                            	<input type="checkbox" name="" id="cbx_chkAll" />
                            </th>
                            <!-- <th colspan="2" >제목</th> -->
                            <th style="vertical-align:middle;width:35%">제목</th>
                            <th style="vertical-align:middle;text-align: center"">일감</th>
                            <th style="vertical-align:middle;text-align: center">상태</th>
                            <th style="vertical-align:middle;text-align: center" width="100">우선순위</th>
                            <th style="vertical-align:middle;text-align: center">작성자</th>
                            <th style="vertical-align:middle;text-align: center;width:15%">등록일자</th>
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
	                            	<a href="detail.do?issueNo=${issue.issueNo }">${issue.title }</a><br/>
	                            	<c:if test="${!empty issue.bookmarkTitle and issue.bookmarkNo ne 0 }">
		                            	<small>
			                            	<i class="fa-solid fa-tag"></i>
			                            	<a href="<%=request.getContextPath() %>/project//bookmark/detail.do?bookmarkNo=${issue.bookmarkNo }">${issue.bookmarkTitle }</a>
		                            	</small>
		                            </c:if>
	                            </td>
	                            <td style="width: 5%;text-align: center">
		                            <c:if test="${issue.workNo ne 0 }">
	                            		<a style="color:#007bff;" href="<%=request.getContextPath() %>/project/work/detail.do?workNo=${issue.workNo }"><i class="fa-solid fa-map"></i></a><br>
	                            		<small>#${issue.workNo }</small>
		                            </c:if>
		                            <c:if test="${issue.workNo eq 0 }">
		                            	<span>-</span>
		                            </c:if>
                            	</td>
	                            <c:if test="${issue.sttCode eq 'open'}">
	                            	<td style="vertical-align:middle;text-align: center"><i class="fa-solid fa-lock-open"></i></td>
	                            </c:if>
	                            <c:if test="${issue.sttCode eq 'close'}">
	                            	<td style="vertical-align:middle;text-align: center"><i class="fa-solid fa-lock"></i></td>
	                            </c:if>
	                            
	                            <td style="vertical-align:middle;text-align: center">
		                            <c:if test="${issue.gradeCode eq 'l' }">
			                            <span class="bg-success px-4 py-1" style="font-size:12px">낮음</span>
		                            </c:if>
		                            <c:if test="${issue.gradeCode eq 'u' }">
			                            <span class="bg-info px-4 py-1" style="font-size:12px">보통</span>
		                            </c:if>
		                            <c:if test="${issue.gradeCode eq 'h' }">
	    	                        	<span class="bg-warning px-4 py-1" style="font-size:12px">높음</span>
	        	                    </c:if>
		                            <c:if test="${issue.gradeCode eq 'e' }">
	    	                        	<span class="bg-danger px-4 py-1" style="font-size:12px">긴급</span>
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
        </div>
    </div>
<!-- pagination -->
	<div class="card-footer">
		<nav aria-label="Navigation">
			<ul class="pagination justify-content-center m-0">
					<%@ include file="/WEB-INF/views/common/issuePagination.jsp" %>	
			</ul>
		</nav>
	</div>
<!-- pagenation end -->
</section>


<!-- <form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type='hidden' name="sttCode" value=""/>
	<input type='hidden' name="gradeCode" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form> -->

<!-- <script>
 	function list_go(page,url){ 
		
 		if(!url) url="list.do";
		var jobForm=$('#jobForm');
		
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='sttCode']").val($('select[name="sttCode"]').val());
		jobForm.find("[name='gradeCode']").val($('select[name="gradeCode"]').val());
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
</script> -->


<script type="text/javascript">
	/* 이슈 전체 선택 */
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
	
	
	/* 북마크 설정*/
	$(function() {
	    $("#setBMK").change(function() {
	        var bmkNo = $("#setBMK").val();
	        console.log("선택된 북마크 번호 : "+bmkNo);
			let targetIssueNoList = [];
			
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

			data = {
					"issueNoList" : targetIssueNoList,
					"bmkNo": bmkNo
			}
			
			$.ajax({ 
				url: "<%=request.getContextPath()%>/project/bookmark/addIntoBmk",
				type: "POST",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: function(result){ 
					if(result){
						Swal.fire({
							  text: '선택 이슈가 북마크에 추가되었습니다.',
							  icon: 'success',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
						}).then((result) => {
							location.href = 'list.do';
						});
					}
				}, 
				error: function (request, status, error){ 
					Swal.fire({
						  text: '선택 이슈의 북마크가 변경에 실패했습니다.' + error,
						  icon: 'error',
						  confirmButtonColor: '#007bff',
						  confirmButtonText: '확인'
						});
				}
			});
	    });
	});
	
	
	/* 선택된 이슈 닫기 */
	function closeIssue(){
		
		// alert(prjNo);
		
		let targetIssueNoList = [];
		
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
		// console.log(targetIssueNoList)
		
		
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
						location.href = 'list.do';
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
		// console.log(targetIssueNoList)
		
		
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
						location.href = 'list.do';
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
 <c:if test="${from eq 'regist' }">
	<script>
		window.onload = function(){
			 //alert("자료를 정상적으로 등록했습니다.");
				Swal.fire({
				  text: '이슈가 정상적으로 등록되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			}).then((result) => {
				  /* Read more about isConfirmed, isDenied below */
				  if (result.isConfirmed) {
			 		window.location.reload();
				  }
			}); 
		};
	</script>
</c:if>

</body>