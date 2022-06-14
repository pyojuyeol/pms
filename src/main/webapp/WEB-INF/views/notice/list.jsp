<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${pageType eq 'news' }">
	<c:set var="pageMaker" value="${dataMap.pageMaker }" />
	<c:set var="cri" value="${pageMaker.cri }" />
	<c:set var="newsList" value="${dataMap.newsList }" />
</c:if>
<c:if test="${pageType eq 'ntc' }">
	<c:set var="pageMaker" value="${dataMap.ntcpageMaker }" />
	<c:set var="cri" value="${pageMaker.cri }" />
	<c:set var="ntcList" value="${dataMap.ntcList }" />
</c:if>
<c:if test="${pageType eq 'etc' }">
	<c:set var="pageMaker" value="${dataMap.etcpageMaker }" />
	<c:set var="cri" value="${pageMaker.cri }" />
	<c:set var="etcList" value="${dataMap.etcList }" />
</c:if>

<head>
</head>
<title>PROSPEC'S</title>
<body>
    <!-- Content Wrapper. Contains page content -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
            	<div class="col-sm-6"></div>
                <div class="col-sm-6 d-none d-sm-block float-right">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/dashboard.do">Home</a></li>
                       <li class="breadcrumb-item"><a href="#">공지사항</a></li>
					<li class="breadcrumb-item active">목록</li>
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
				<button type="submit" class="btn btn-primary" onclick="location.href='registForm.do'">
					등 록
				</button>
				<div id="keyword" class="card-tools" style="width: 550px;"> <!-- 분류&검색 -->
				  <div class="input-group row" style="top:5px">
		            <select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
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
	<div class="row">
		<div class="col-12">						
					<div class="col-12 mt-2">
						<div class="card card-secondary card-outline card-outline-tabs">
							<div class="card-header p-0 border-bottom-0">
								<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
									<li class="nav-item">
										<a class="nav-link ${pageType == 'news' ? 'active' : '' }" role="tab" aria-controls="news" aria-selected="true">회사 소식</a>
									</li>
									<li class="nav-item">
										<a class="nav-link ${pageType == 'ntc' ? 'active' : '' }" role="tab" aria-controls="ntc" aria-selected="false">행사 일정</a>
									</li>
									<li class="nav-item">
										<a class="nav-link ${pageType == 'etc' ? 'active' : '' }" role="tab" aria-controls="etc" aria-selected="false">기타</a>
									</li>
					
								</ul>
							</div>
							
							<div class="card-body" style="padding-top: 0px;min-height:500px">
								<div class="tab-content" id="custom-tabs-four-tabContent">
									<div class="tab-pane fade ${pageType == 'news' ? 'active show' : '' }" id="news" role="tabpanel" aria-labelledby="news">
										 <table class="table table-hover text-nowrap text-center">
						                   <tr>
												<th style="width:10%;">번 호</th>
												<th style="width:40%;">제 목</th>
												<th style="">첨부파일</th>
												<th style="width:15%;">작성자</th>
												<th style="text-align:center;">등록일</th>
												<th style="width:10%;text-align:center;">조회수</th>
											</tr>
											<c:if test="${!empty newsList }" >	
												<c:forEach items="${newsList }" var="notice" varStatus="index">
													<tr>
														<td>${index.count }</td>
														<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
																	 white-space: nowrap; text-overflow: ellipsis;">
															<a href="detail.do?from=list&ntcNo=${notice.ntcNo }">
																<span class="col-sm-12 ">${notice.title }</span>
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
														<td style="text-align:center;">
															<fmt:formatDate value="${notice.regDt }" pattern="yyyy-MM-dd"/>
														</td>
														<td style="text-align:center;"><span class="badge bg-red">${notice.viewCnt }</span></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${empty newsList }" >
							            		<tr>
							            			<td colspan="7" class="text-center">
							            				해당내용이 없습니다.
							            			</td>
							            		</tr>
							            	</c:if>
						                </table>
									</div>
									
									
									<div class="tab-pane fade ${pageType == 'ntc' ? 'active show' : '' }" id="ntc" role="tabpanel" aria-labelledby="ntc">
										 <table class="table table-hover text-nowrap text-center">
						                   <tr>
												<th style="width:10%;">번 호</th>
												<th style="width:40%;">제 목</th>
												<th style="">첨부파일</th>
												<th>작성자</th>
												<th style="width:15%;text-align:center;">등록일</th>
												<th style="width:10%;text-align:center;">조회수</th>
											</tr>
											<c:if test="${!empty ntcList }" >	
												<c:forEach items="${ntcList }" var="notice" varStatus="index">
													<tr>
														<td>${index.count }</td>
														<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
																	 white-space: nowrap; text-overflow: ellipsis;">
															<a href="detail.do?from=list&ntcNo=${notice.ntcNo }">
																<span class="col-sm-12 ">${notice.title }</span>
															</a>
														</td>
														<td class="text-center">
															<c:if test="${!empty notice.attachList }">
																<i class="nav-icon fas fa-file"></i>
															</c:if>
															<c:if test="${empty notice.attachList }">
																<span>-</span>
															</c:if>
														</td>
														<td>${notice.userNm }</td>
														<td style="text-align:center;">
															<fmt:formatDate value="${notice.regDt }" pattern="yyyy-MM-dd"/>
														</td>
														<td style="text-align:center;"><span class="badge bg-red">${notice.viewCnt }</span></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${empty ntcList }" >
							            		<tr>
							            			<td colspan="7" class="text-center">
							            				해당내용이 없습니다.
							            			</td>
							            		</tr>
							            	</c:if>
						                </table>
									</div>
									
									
									<div class="tab-pane fade ${pageType == 'etc' ? 'active show' : '' }" id="etc" role="tabpanel" aria-labelledby="etc">
										 <table class="table table-hover text-nowrap text-center">
						                   <tr>
												<th style="width:10%;">번 호</th>
												<th style="width:40%;">제 목</th>
												<th style="">첨부파일</th>
												<th>작성자</th>
												<th style="width:15%;text-align:center;">등록일</th>
												<th style="width:10%;text-align:center;">조회수</th>
											</tr>
											<c:if test="${!empty etcList }" >	
												<c:forEach items="${etcList }" var="notice" varStatus="index">
													<tr>
														<td>${index.count }</td>
														<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
																	 white-space: nowrap; text-overflow: ellipsis;">
															<a href="detail.do?from=list&ntcNo=${notice.ntcNo }">
																<span class="col-sm-12 ">${notice.title }</span>
															</a>
														</td>
														<td style="text-align:center;">
															<c:if test="${!empty notice.attachList }">
																<i class="nav-icon fas fa-file"></i>
															</c:if>
															<c:if test="${empty notice.attachList }">
																<span>-</span>
															</c:if>
														</td>
														<td>${notice.userNm }</td>
														<td style="text-align:center;">
															<fmt:formatDate value="${notice.regDt }" pattern="yyyy-MM-dd"/>
														</td>
														<td style="text-align:center;"><span class="badge bg-red">${notice.viewCnt }</span></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${empty etcList }" >
							            		<tr>
							            			<td colspan="7" class="text-center">
							            				해당내용이 없습니다.
							            			</td>
							            		</tr>
							            	</c:if>
						                </table>
									</div>
									
									
								</div> <!-- tab content -->
							</div>  <!-- card body -->
						</div> <!-- card -->
					</div>  <!-- col-12 mt-2-->
				</div>  <!-- col-12 -->
			</div> <!-- row -->
				</section>  
					
	            </div>
	            <div class="card-footer">
					<nav aria-label="pds list Navigation">
						<ul class="pagination justify-content-center m-0">
							<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>	
						</ul>
					</nav>
				</div>
			    <form id="jobForm">
							<input type='hidden' name="page" value="" />
							<input type='hidden' name="searchType" value="" />
							<input type='hidden' name="keyword" value="" />
							<input type='hidden' name="pageType" value="${pageType }" />
							<input type='hidden' name="perPageNum" value="" />
						</form>
	    </div>
	</section>
<script>
	let nav_links = document.querySelectorAll(".nav-link");
	for (let i = 0; i < nav_links.length; i++) {
		if(nav_links[i].dataset.toggle) {
			continue;
		}
		nav_links[i].addEventListener("click", function(e) {
			list_go(1, e);
		});
	}
	
	function list_go(page,event,url){
		let type = "";
		let nav_link = document.querySelector(".nav-link.active");
		
		if (!event) {
			type = document.querySelector("input[name='pageType']").value;
		} else {
			type = event.target.getAttribute("aria-controls");
		}
		
		console.log(type);
		 
			if(!url) url="list.do";
		var jobForm=$('#jobForm');
		
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
		jobForm.find("[name='pageType']").val(type);
		jobForm.find("input[name='perPageNum']").val($("#perPageNum").val());
		
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
</script>
<script>
window.addEventListener('DOMContentLoaded', listNotice);

function listNotice() {
	let pageType = "${pageType}";
	
	console.log(pageType);
	if (pageType === "ntc") {
		$("a[title = ntc]").trigger("click");
	} else if (pageType === "etc") {
		$("a[title = etc]").trigger("click");
	} else {
		$("a[title = news]").trigger("click");
	}
	
	<c:if test="${from eq 'regist' }">
			alertC("등록되었습니다.");
	</c:if>
	
	<c:if test="${from eq 'remove' }">
		alertC("삭제되었습니다.");
	</c:if>
	
}
</script>


</body>
