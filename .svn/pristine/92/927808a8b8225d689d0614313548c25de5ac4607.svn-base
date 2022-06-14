<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="receivedDay" value="${receivedDailyReportMap.receivedDailyReportList }"/>
<c:set var="receivedDayPageMaker" value="${receivedDailyReportMap.receivedDailyReportPageMaker }"/>
<c:set var="receivedMont" value="${receivedMonthReportMap.receivedMonthReportList }"/>
<c:set var="receivedMonthPageMaker" value="${receivedMonthReportMap.receivedMonthPageMaker }"/>

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
					<li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
					<li class="breadcrumb-item"><a href="#">업무보고</a></li>
					<li class="breadcrumb-item active">목록</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="">
		<div class="">
			<div class="card-header" style="padding-bottom: 0.25;padding-bottom: 2px;padding-right: 2px;">
				<div class="float-right">
					<div class="btn-group float-right">
						<c:if test="${plpm eq 'PL'}">
							<button type="button" class="btn btn-md btn-outline-secondary" onclick="location.href='./list.do'">나의 업무보고</button>
							<button type="button" class="btn btn-md btn-secondary" onclick="location.href='./getList.do'">받은 업무보고</button>
						</c:if>
						<c:if test="${plpm eq 'PM'}">
							<!-- <button type="button" class="btn btn-md btn-outline-secondary" onclick="location.href='./list.do'">나의 업무보고</button>
							<button type="button" class="btn btn-md btn-secondary" onclick="location.href='./getList.do'">받은 업무보고</button> -->
						</c:if>
					</div>
				</div>
			</div>
			<div class="card card-secondary card-outline">
				<div class="d-flex align-items-center justify-content-between" style="padding-top: 7px;padding-left: 7px;padding-bottom: 5px;">
					<div class="float-left">
						<!-- <button type="submit" class="btn btn-md btn-primary mr-1" onclick="location.href='registForm.do'">등 록</button> -->
					</div>
					<div>
						<div id="keyword" class="card-tools my-2" > <!-- 분류&검색 -->
							<div class="input-group row" >
								<!-- keyword -->
								<div class="col-md-5 pr-0">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i class="far fa-calendar-alt"></i> </span>
										</div>
										<input type="text" class="form-control float-right" id="rangeDt" name="rangeDt">
									</div>
								</div>
								<select class="form-control col-md-2" name="searchType" id="searchType">
									<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>검 색</option>
									<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
									<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
								</select>	
								<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }"  />
								 <span class="input-group-append">
									<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
										<i class="fa fa-search"></i>
									</button>
								</span> <!-- end : search bar -->
							</div>
						</div> 
					</div>
				</div>
	<div class="row">
		<div class="col-12">						
					<div class="col-12 mt-2">
						<div class="card card-info card-outline card-outline-tabs minH">
							<div class="card-header p-0 border-bottom-0">
								<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
									<li class="nav-item">
										<a class="nav-link active" id="custom-tabs-four-home-tab" data-toggle="pill" href="#dailyReport" role="tab" aria-controls="dailyReport" aria-selected="true">일일업무보고</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" id="custom-tabs-four-messages-tab" data-toggle="pill" href="#monthlyReport" role="tab" aria-controls="monthlyReport" aria-selected="false">월간업무보고</a>
									</li>
								</ul>
							</div>
							<div class="card-body" style="padding-top: 0px;min-height:500px">
								<div class="tab-content" id="custom-tabs-four-tabContent">
									<div class="tab-pane fade active show" id="dailyReport" role="tabpanel" aria-labelledby="dailyReport">
										<table class="table table-hover">
											<thead>
												<tr>
												    <th style="text-align:center" class="col-1">No</th>
												    <th style="text-align:center" class="col-2">열람여부</th>
												    <th style="text-align:center">제목</th>
												    <th style="text-align:center">작성자</th>
												    <th style="text-align:center" class="col-3">등록일</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${empty receivedDay }">
													<tr>
														<td colspan="5"><h6 style="text-align:center">받은 일일 업무보고가 없습니다.</h6></td>
													</tr>
												</c:if>
												<c:if test="${!empty receivedDay }">
													<c:forEach items="${receivedDay }" var="dailyRpt">
														<tr>
															<td style="text-align:center" name="repNo">${dailyRpt.rownum }</td>
															<td style="text-align:center" name="returnDt">
																<c:if test="${dailyRpt.returnDt eq 'read'}">
																	<i class="fa-solid fa-envelope-open text-primary"></i>
																</c:if>
																<c:if test="${dailyRpt.returnDt eq 'unread'}">
																	<i class="fa-solid fa-envelope text-primary"></i>
																</c:if>
															</td>
															<td style="text-align:left" name="title" class="col-5">
																<a href="<%=request.getContextPath() %>/project/report/detail?repNo=${dailyRpt.repNo }&from=receive">${dailyRpt.title }</a>
															</td>
															<td style="text-align:center" name="userNm">${dailyRpt.userNm }</td>
															<td style="text-align:center" name="submitDt">
																<fmt:formatDate value="${dailyRpt.submitDt }" pattern="yyyy-MM-dd"/>
															</td>
														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
										<div class="card-footer">
											<nav aria-label="Navigation">
												<ul class="pagination justify-content-center m-0">
													<li class="page-item">
														<a class="page-link" href="javascript:list_go(1);">
															<i class="fas fa-angle-double-left"></i>
														</a>
													</li>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedDayPageMaker.prev ? receivedDayPageMaker.startPage-1 : receivedDayPageMaker.cri.page}');">
															<i class="fas fa-angle-left"></i>
														</a>
													</li>
													<c:forEach var="pageNum" begin="${receivedDayPageMaker.startPage }" end="${receivedDayPageMaker.endPage }" >
															
														<li class="page-item ${receivedDayPageMaker.cri.page == pageNum?'active':''}">
															<a class="page-link" href="javascript:list_go('${pageNum}');" >${pageNum }</a>
														</li>
														
													</c:forEach>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedDayPageMaker.next ? receivedDayPageMaker.endPage+1 :receivedDayPageMaker.cri.page}');">
															<i class="fas fa-angle-right" ></i>
														</a>
													</li>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedDayPageMaker.realEndPage}');">
															<i class="fas fa-angle-double-right"></i>
														</a>
													</li>	
												</ul>
											</nav>
										</div>
									</div>
									<div class="tab-pane fade" id="monthlyReport" role="tabpanel" aria-labelledby="monthlyReport">
										<table class="table table-hover">
											<thead>
												<tr>
												    <th style="text-align:center" class="col-1">No</th>
												    <th style="text-align:center" class="col-2">열람여부</th>
												    <th style="text-align:center">제목</th>
												    <th style="text-align:center">작성자</th>
												    <th style="text-align:center" class="col-3">등록일</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${empty receivedMont }">
													<tr>
														<td colspan="5"><h6 style="text-align:center">받은 월간 업무보고가 없습니다.</h6></td>
													</tr>
												</c:if>
												<c:if test="${!empty receivedMont }">
													<c:forEach items="${receivedMont }" var="monthlyRpt">
														<tr>
															<td style="text-align:center" name="repNo">${monthlyRpt.rownum }</td>
															<td style="text-align:center" name="returnDt">
																<c:if test="${monthlyRpt.returnDt eq 'read'}">
																	<i class="fa-solid fa-envelope-open text-primary"></i>
																</c:if>
																<c:if test="${monthlyRpt.returnDt eq 'unread'}">
																	<i class="fa-solid fa-envelope text-primary"></i>
																</c:if>
															</td>
															<td style="text-align:left" name="title" class="col-5">
																<a href="<%=request.getContextPath() %>/project/report/detailm?repNo=${monthlyRpt.repNo }&from=receive">${monthlyRpt.title }</a>
															</td>
															<td style="text-align:center" name="userNm">${monthlyRpt.userNm }</td>
															<td style="text-align:center" name="submitDt">
																<fmt:formatDate value="${monthlyRpt.submitDt }" pattern="yyyy-MM-dd"/>
															</td>
														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
										<div class="card-footer">
											<nav aria-label="Navigation">
												<ul class="pagination justify-content-center m-0">
													<li class="page-item">
														<a class="page-link" href="javascript:list_go(1);">
															<i class="fas fa-angle-double-left"></i>
														</a>
													</li>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedMonthPageMaker.prev ? receivedMonthPageMaker.startPage-1 : receivedMonthPageMaker.cri.page}');">
															<i class="fas fa-angle-left"></i>
														</a>
													</li>
													<c:forEach var="pageNum" begin="${receivedMonthPageMaker.startPage }" end="${receivedMonthPageMaker.endPage }" >
															
														<li class="page-item ${receivedMonthPageMaker.cri.page == pageNum?'active':''}">
															<a class="page-link" href="javascript:list_go('${pageNum}');" >${pageNum }</a>
														</li>
														
													</c:forEach>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedMonthPageMaker.next ? receivedMonthPageMaker.endPage+1 :receivedMonthPageMaker.cri.page}');">
															<i class="fas fa-angle-right" ></i>
														</a>
													</li>
													<li class="page-item">
														<a class="page-link" href="javascript:list_go('${receivedMonthPageMaker.realEndPage}');">
															<i class="fas fa-angle-double-right"></i>
														</a>
													</li>	
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- pagination -->
  			<%-- <div class="card-footer">
				<nav aria-label="pds list Navigation">
					<ul class="pagination justify-content-center m-0">
 						<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>	
					</ul>
				</nav>
			</div> --%>
		<!-- pagenation end -->
			</div>
		</div>
	</div>
		<!-- /.card-body -->
	</section>
<script>
window.onload = function() {
    //Date range picker
	moment.locale('ko'); // 언어를 한국어로 설정함
    $('#rangeDt').daterangepicker({
// 	    "minDate": "<fmt:formatDate value='${prj.bgnDt}' pattern='yyyy/MM/dd'/>",
// 	    "maxDate": "<fmt:formatDate value='${prj.endDt}' pattern='yyyy/MM/dd'/>",
        locale :{ 
	        format: 'YYYY-MM-DD',
	        applyLabel: "적용",
	        cancelLabel: "닫기",
	        separator: ' ~ ',
	     	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	   		daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
        }
    });
}
</script>



</body>





