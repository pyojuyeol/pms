<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="noticeList" value="${dataMap.noticeList }" /> 
<c:set var="cri" value="${pageMaker.cri }" />

<head>

<style></style>

</head>
<title>PROSPEC'S</title>
<body>


    <!-- Content Wrapper. Contains page content -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                	<h1>${prjNm}</h1>
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
                        <li class="breadcrumb-item">공지사항</li>
                        <li class="breadcrumb-item active">목 록</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>


<!-- Content Wrapper. Contains page content -->
<!-- Main content -->

	<section class="content">
		<div class="card card-secondary card-outline">
			<div class="card-header with-border">
				<button type="submit" class="btn btn-primary" onclick="location.href='registForm.do?prjNo=${cri.prjNo}'">
					등 록
				</button>
				<div id="keyword" class="card-tools" style="width: 550px;"> <!-- 분류&검색 -->
				  <div class="input-group row" style="top:5px">
		            <select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1, '', '${cri.prjNo }');">
				  		<option value="10" ${cri.perPageNum==10 ? 'selected':'' } >정렬개수</option>
				  		<option value="20" ${cri.perPageNum==20 ? 'selected':'' }>20개씩</option>
				  		<option value="30" ${cri.perPageNum==30 ? 'selected':'' }>30개씩</option>
				  		<option value="50" ${cri.perPageNum==50 ? 'selected':'' }>50개씩</option>
				  		
				  	</select>		
					<select class="form-control col-md-3" name="searchType" id="searchType">
						<option value="tcw" ${cri.searchType eq 'tcw' ? 'selected':'' }>검색구분</option>
						<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
						<option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>작성자</option>
						<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
					</select>	
					
					<!-- keyword -->
					<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }" /> <span class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span> <!-- end : search bar -->
				  </div>
				</div> <!-- 분류&검색 -->
			</div>
		          
	 
	            <!-- /.card-header -->
	            <div class="card-body ">
	            	
	            	<section class="content">
	
					<div class="card">
						<div class="card-body p-0">
			                <table class="table table-hover text-nowrap">
			                   <tr style="font-size:0.95em;">
									<th style="width:10%;">번 호</th>
									<th style="width:40%;">제 목</th>
									<th style="">첨부파일</th>
									<th style="width:15%;">작성자</th>
									<th>등록일</th>
									<th style="width:10%;">조회수</th>
								</tr>
								<c:if test="${!empty noticeList }" >	
									<c:forEach items="${noticeList }" var="notice" varStatus="index">
										
										<jsp:useBean id="now" class="java.util.Date" />
										<fmt:formatDate value="${notice.regDt}" pattern="yyyy-MM-dd" var="regDate" />
										<fmt:parseDate value="${regDate }" pattern="yyyy-MM-dd" var="regDateT"/>
										<fmt:parseNumber value="${now.time / (1000*60*60*24) }" var="todayTime" integerOnly="true" />
										<fmt:parseNumber value="${regDateT.time / (1000*60*60*24) }" var="regDateTime" integerOnly="true" />
									
									
										<tr style='font-size:0.85em;'>
											<td>${index.count }</td>
											<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
														 white-space: nowrap; text-overflow: ellipsis;">
												<a href="detail.do?from=list&ntcNo=${notice.ntcNo }">
													<span class="col-sm-12 ">${notice.title }
													<c:if test="${regDateTime - todayTime < 1 }">&nbsp;&nbsp;<span class="badge badge-warning">new</span></c:if>
													</span>
												</a>
											</td>
											<td>
												<c:if test="${!empty notice.attachList }">
													<i class="nav-icon fas fa-file"></i>
												</c:if>
												<c:if test="${empty notice.attachList }">
													<span>-</span>
												</c:if>
											</td>
											<td>${notice.userNm }</td>
											<td>
												<fmt:formatDate value="${notice.regDt }" pattern="yyyy-MM-dd"/>
											</td>
											<td><span class="badge bg-red">${notice.viewCnt }</span></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty noticeList }" >
				            		<tr>
				            			<td colspan="7" class="text-center">
				            				해당내용이 없습니다.
				            			</td>
				            		</tr>
				            	</c:if>
			                </table>
	                
       					</div>
					</div>
				</section>  
					
	            </div>
	            <div class="card-footer">
					<nav aria-label="pds list Navigation">
						<ul class="pagination justify-content-center m-0">
							<%@ include file="/WEB-INF/views/common/pagination.jsp" %>	
						</ul>
					</nav>
				</div>
			    <!-- 공지사항 본문끝 -->
			    <!-- 페이지번호 -->
	    </div>
	</section>

<script>
window.addEventListener('DOMContentLoaded', listProjectNotice);

function listProjectNotice() {

	<c:if test="${from eq 'regist' }">
		alertC("정상 등록되었습니다.");
	</c:if>
	
	<c:if test="${from eq 'remove' }">
		alertC("삭제되었습니다.");
	</c:if>
	
}

</script>

</body>
