<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="pdsList" value="${dataMap.pdsList }"/>
<c:set var="pageMaker" value="${dataMap.pageMaker }"/>
<c:set var="cri" value="${dataMap.pageMaker.cri }"/>

<title>PROSPEC'S | 자료게시판</title>

<body>
<!-- <script type="text/javascript">
</script> -->

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
<!-- 				<h1>프로젝트이름</h1> -->
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item"><a href="#">자료게시판</a></li>
					<li class="breadcrumb-item active">목 록</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">
	<div class="card card-info card-outline">
		<div class="card-header with-border">
					<button type="button" onclick="location.href='registForm.do'"
						class="btn btn-primary mr-1">등록</button>
			<div id="keyword" class="card-tools" style="width: 550px;">
				<div class="input-group row" style="top: 5px">
					<!-- search bar -->
				
					<!-- sort num -->
					<select class="form-control col-md-3" name="perPageNum"
						id="perPageNum" onchange="list_go(1);">
						<option value="10" ${cri.perPageNum==10 ? 'selected' : ''}>정렬개수</option>
						<option value="20"  ${cri.perPageNum==20 ? 'selected' : ''}>20개씩</option>
						<option value="30"  ${cri.perPageNum==30 ? 'selected' : ''}>30개씩</option>
						<option value="50"  ${cri.perPageNum==50 ? 'selected' : ''}>50개씩</option>
					</select>

					<!-- search bar -->
					<select class="form-control col-md-3" name="searchType"
						id="searchType">
						<option value="tcw"  ${cri.searchType eq 'tcw' ? 'selected':''}>검색구분</option>
						<option value="t" ${cri.searchType eq 't' ? 'selected':''}>제목</option>
						<option value="w" ${cri.searchType eq 'w' ? 'selected':''}>작성자</option>
						<option value="c" ${cri.searchType eq 'c' ? 'selected':''}>내용</option>
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
 
		<div class="card-body">
	            	
           	<section class="content row float-none">
				<c:if test="${!empty pdsList }" >
<!-- 					<div class="col-md-1"></div>	 -->
					<c:forEach items="${pdsList }" var="pds">
					<div class="col-md-1"></div>
						<div class="container-fluid col-md-3 m-1">
							<div class="card row card-default">
								<div class="card-header bg-secondary">
									<h3 class="card-title">${pds.title }</h3>
									<div class="card-tools">
									  <c:if test="${loginUser.userId eq pds.register }">
										<a class="btn btn-info btn-sm" onclick="submit_go('modifyForm.do','${pds.pdsNo}');" class="btn btn-tool"><i class="fas fa-pen"></i></a>
										<a class="btn btn-danger btn-sm" onclick="remove_go('${pds.pdsNo}')" class="btn btn-tool"><i class="fas fa-trash-can"></i></a>
									  </c:if>
									</div>
								</div>
								<div class="card-body">
<!-- 									<div class="card card-light card-outline"> -->
										<div class="card-header">
											<c:if test="${!empty pds.attachList }" >	
												<c:forEach items="${pds.attachList }" var="attachList">
													<a onclick="location.href='<%=request.getContextPath()%>/pds/getFile.do?attachSeq=${attachList.attachSeq }';" class="btn btn-tool btn-link">${attachList.nm }</a>
												</c:forEach>
											</c:if>
											
										</div>
										<div class="card-body" style="width:270px;">
											<p>${pds.content }</p>
										</div>
<!-- 									</div> -->
								</div>
							</div>
						</div>
					</c:forEach>
<!-- 					<div class="col-md-1"></div> -->
				</c:if>
				<c:if test="${empty pdsList }" >
            		<tr>
            			<td colspan="7" class="text-center">
            				해당내용이 없습니다.
            			</td>
            		</tr>
            	</c:if>
			</section>  
					
          </div>
	            <div class="card-footer">
					<nav aria-label="pds list Navigation">
						<ul class="pagination justify-content-center m-0">
							<%@ include file="/WEB-INF/views/common/pagination.jsp" %>	
						</ul>
					</nav>
				</div>
		<!-- pagenation end -->

           </div>
</section>

<script>
function submit_go(url,pdsNo){	
	location.href=url+"?pdsNo="+pdsNo;
}

function remove_go(pdsNo){
	 Swal.fire({
		  text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
		  icon: 'warning',
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  cancelButtonText: '취소',
		}).then((result) => {
			if (result.isConfirmed) {
	 			location.href="remove.do?pno="+pdsNo;
			} 
		  });
};	

</script>

<script>

window.addEventListener('DOMContentLoaded', listPds);

function listPds() {
	
	<c:if test="${from eq 'regist' }">
			alertC("등록되었습니다.");
	</c:if>
	
	<c:if test="${from eq 'remove' }">
			alertA("삭제되었습니다.","success",re);
	</c:if>
	
	<c:if test="${from eq 'modify'}" >
		alertC("수정되었습니다.");
	</c:if>    
	
}
</script>

<script>
function re() {
	location.reload();
}
</script>

</body>
