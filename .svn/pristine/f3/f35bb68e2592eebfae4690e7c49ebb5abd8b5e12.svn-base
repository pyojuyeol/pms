<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="bmkList" value="${dataMap.bmkList }" /> 
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
					<li class="breadcrumb-item"><a href="#">책갈피</a></li>
					<li class="breadcrumb-item active">목록</li>
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
			<button type="button" class="btn btn-md btn-outline-secondary"
				onclick="location.href='<%=request.getContextPath() %>/project/issue/list.do'">이슈</button>
			<button type="button" class="btn btn-md btn-secondary"
				onclick="location.href='<%=request.getContextPath() %>/project/bookmark/list.do'">책갈피</button>
		</div>
	</div>
	<div class="card card-secondary card-outline">
		<div class="card-header with-border">
					<button type="button" onclick="location.href='registForm.do'"
						class="btn btn-primary ">책갈피 등록</button>
					
					<!-- <div class="float-right"> -->
						<div class="btn-group">
							<button type="button" class="btn btn-block bg-gradient-info"
								onclick="openBMK();"><i class="fa-solid fa-lock-open"></i></button>							
							<button type="button" class="btn btn-block btn-outline-secondary mt-0"
								onclick="closeBMK();"><i class="fa-solid fa-lock"></i></button>
						</div>
					<!-- </div> -->
			
			<div id="keyword" class="card-tools" style="width: 550px;">
				<div class="input-group row" style="top: 5px">
					<select name="sttCode" class="form-control col-md-3" onchange="list_go(1);">
						<option value="" ${cri.sttCode eq '' ? 'selected' : '' }>책갈피 상태</option>
						<option value="open" ${cri.sttCode eq 'open' ? 'selected' : '' }>열림</option>
						<option value="close" ${cri.sttCode eq 'close' ? 'selected' : '' }>닫힘</option>
					</select>
					<!-- search bar -->
				
					<select class="form-control col-md-3" name="searchType" id="searchType">
						<option value="tcw"  ${cri.searchType eq 'tcw' ? 'selected':''}>검색구분</option>
						<option value="t" ${cri.searchType eq 't' ? 'selected':''}>제목</option>
						<option value="w" ${cri.searchType eq 'w' ? 'selected':''}>작성자</option>
						<option value="c" ${cri.searchType eq 'c' ? 'selected':''}>내용</option>
					</select>   
					                                 

					<!-- keyword -->
					<input class="form-control" type="text" name="keyword"
						placeholder="검색어를 입력하세요." value="${param.keyword }"> 
						<span class="input-group-append">
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
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th style="vertical-align:middle;text-align: center;width:85px">
                            	<input type="checkbox" name="" id="cbx_chkAll" />
                            </th>
                            <!-- <th colspan="2" >제목</th> -->
                            <th style="vertical-align:middle;">책갈피</th>
                            <th style="vertical-align:middle;text-align: center">상태</th>
                            <th style="vertical-align:middle;text-align: center">진행상태</th>
                            <th style="vertical-align:middle;text-align: center"></th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:if test="${empty bmkList }">
                    		<td colspan="6" align='center'><h6>등록된 책갈피가 없습니다.</td>
                    	</c:if>
                    	<c:if test="${!empty bmkList }">
                    		<c:forEach items="${bmkList}" var="bmk">
		                        <tr>
		                            <td style="vertical-align:middle;text-align: center"><input type="checkbox" name="bookmarkNo" value=${bmk.bookmarkNo }></td>
		                            <td style="width: 30%; overflow: hidden;">
		                            	<h6><b><a href="detail.do?bookmarkNo=${bmk.bookmarkNo }">${bmk.bmkTitle }</a></b></h6>
		                            	<small>등록일자: 
		                            		<fmt:formatDate value="${bmk.regDt }" pattern="yyyy-MM-dd"/> 
		                            	</small>
		                            </td>
		                           	<td style="vertical-align:middle;text-align: center">
			                            <c:if test="${bmk.sttCode eq 'open'}">
				                           		<i class="fa-solid fa-lock-open"></i>
				                        </c:if>
			                            <c:if test="${bmk.sttCode eq 'close'}">
				                           		<i class="fa-solid fa-lock"></i>
				                        </c:if>
		                           	</td>
		                            <td class="project_progress" style="width:25%">
										<div class="progress progress-lg">
											<c:if test="${bmk.totalIssueCnt eq 0}" >
												<div class="progress-bar bg-secondary disabled" role="progressbar" aria-valuenow="${bmk.progress }" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
													<span>이슈 없음</span>
												</div>
											</c:if>
											<c:if test="${bmk.totalIssueCnt gt 0 and bmk.progress eq 0}" >
												<div class="progress-bar bg-danger" role="progressbar" aria-valuenow="${bmk.progress }" aria-valuemin="0" aria-valuemax="100" style="width: 7%">
													<span>${bmk.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bmk.progress le 30 }" >
												<div class="progress-bar bg-danger" role="progressbar" aria-valuenow="${bmk.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bmk.progress }%">
													<span>${bmk.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bmk.progress le 60 and bmk.progress gt 30}" >
												<div class="progress-bar bg-warning" role="progressbar" aria-valuenow="${bmk.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bmk.progress }%">
													<span>${bmk.progress }%</span>
												</div>
											</c:if>
											<c:if test="${bmk.progress gt 60}" >
												<div class="progress-bar bg-success" role="progressbar" aria-valuenow="${bmk.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${bmk.progress }%">
													<span>${bmk.progress }%</span>
												</div>
											</c:if>
										</div>
										<small>
											전체 이슈: ${bmk.totalIssueCnt } | 
										</small>
										<small>
											열림: ${bmk.totalOpenedIssueCnt } |
										</small>
										<small>
											닫힘: ${bmk.totalClosedIssueCnt }
										</small>
									</td>
									<td style="vertical-align:middle;text-align: center">
										<a class="btn btn-info btn-sm" onclick="location.href='<%=request.getContextPath() %>/project/bookmark/modifyForm.do?bookmarkNo=${bmk.bookmarkNo }'">
											<i class="fa-solid fa-gear"></i>
										</a>
										<a class="btn btn-danger btn-sm" onclick="deleteBMK(${bmk.bookmarkNo });"> <i class="fa-solid fa-trash-can"></i> </a>
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
		<nav aria-label="pds list Navigation">
			<ul class="pagination justify-content-center m-0">
					<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>	
			</ul>
		</nav>
	</div>
<!-- pagenation end -->
</section>


<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type='hidden' name="sttCode" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>

<script type="text/javascript">
 	function list_go(page,url){ 
		
 		if(!url) url="list.do";
		var jobForm=$('#jobForm');
		
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='sttCode']").val($('select[name="sttCode"]').val());
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
</script>

<script type="text/javascript">
/* 이슈 전체 선택 */
window.onload = function(){
	$(document).ready(function() {
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")){
				$("input[name=bookmarkNo]").prop("checked", true);
			}
			else $("input[name=bookmarkNo]").prop("checked", false);
		});
		
		$("input[name=bookmarkNo]").click(function() {
			var total = $("input[name=bookmarkNo]").length;
			var checked = $("input[name=bookmarkNo]:checked").length;
			
			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
	});

}

function deleteBMK(bmkNo){
	// alert("deleteBMK"+bmkNo);
	
	let bookmarkNo = parseInt(bmkNo);
	
	Swal.fire({
			  text: '해당 북마크를 정말 삭제하시겠습니까?.',
			  icon: 'info',
			  confirmButtonColor: '#007bff',
			  confirmButtonText: '확인',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  cancelButtonText: '취소'
		}).then((result) => {
			  if (result.isConfirmed) {
				$.ajax({ 
					url: "<%=request.getContextPath()%>/project/bookmark/remove?bookmarkNo="+bookmarkNo,
					type: "GET",
					//data: JSON.stringify(targetIssueNoList),
					contentType: "application/json",
					success: function(result){
							Swal.fire({
								  text: '북마크가 삭제됐습니다.',
								  icon: 'success',
								  confirmButtonColor: '#007bff',
								  confirmButtonText: '확인'
							}).then((result) => {
								location.href = 'list.do';
							});
					}, 
					error: function (request, status, error){ 
						Swal.fire({
							  text: '북마크 삭제에 실패했습니다.' + error,
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							});
					}
				});
				  
	 		 }
		});
}

/* 선택된 책갈피 닫기 */
function closeBMK(){
	
	// alert(prjNo);
	
	let targetBMKNoList = [];
	
	let checkedBMKList = document.querySelectorAll("input[name=bookmarkNo]:checked");
	if(checkedBMKList.length==0){
		Swal.fire({
					  text: '선택된 책갈피가 없습니다.',
					  icon: 'error',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
		});
		return;
	}

	for(item of checkedBMKList){
		targetBMKNoList.push(parseInt(item.value));
	}
	
	$.ajax({ 
		url: "<%=request.getContextPath()%>/project/bookmark/modifyStt/close.do",
		type: "POST",
		data: JSON.stringify(targetBMKNoList),
		contentType: "application/json",
		success: function(result){ 
			if(result){
				Swal.fire({
					  text: '선택 책갈피가 정상적으로 닫혔습니다.',
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
				  text: '책갈피 닫기에 실패했습니다.' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				});
		}
	});
	
}

/* 선택된 책갈피 열기 */
function openBMK(){
	
	// alert(prjNo);
	
	let targetBMKNoList = [];
	
	let checkedBMKList = document.querySelectorAll("input[name=bookmarkNo]:checked");
	if(checkedBMKList.length==0){
		Swal.fire({
					  text: '선택된 책갈피가 없습니다.',
					  icon: 'error',
					  confirmButtonColor: '#007bff',
					  confirmButtonText: '확인'
		});
		return;
	}

	for(item of checkedBMKList){
		targetBMKNoList.push(parseInt(item.value));
	}
	// console.log(targetIssueNoList)
	
	
	$.ajax({ 
		url: "<%=request.getContextPath()%>/project/bookmark/modifyStt/open.do",
		type: "POST",
		data: JSON.stringify(targetBMKNoList),
		contentType: "application/json",
		success: function(result){ 
			if(result){
				Swal.fire({
					  text: '선택 책갈피가 정상적으로 열렸습니다.',
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
				  text: '책갈피 닫기에 실패했습니다.' + error,
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
			  text: '책갈피가 정상적으로 등록되었습니다.',
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

<c:if test="${from eq 'modify' }">
<script>
	window.onload = function(){
			Swal.fire({
			  text: '책갈피가 정상적으로 수정되었습니다.',
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