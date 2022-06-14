<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="procList" value="${dataMap.procList }" /> 
<c:set var="confirmList" value="${dataMap.confirmList }" /> 
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
                        <li class="breadcrumb-item active">목록</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>

<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-9">
			<div class="card card-secondary card-outline">
				<div class="card-header with-border">
					<div class="float-left">
						<button type="submit" class="btn btn-md btn-primary" onclick="location.href='registForm.do'">회의록 작성</button>
					</div>
					<div id="keyword" class="card-tools" style="width: 550px;"> <!-- 분류&검색 -->
					  <div class="input-group row" style="top:5px">
			            <select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
					  		<option value="10" ${cri.perPageNum==10 ? 'selected':'' } >정렬개수</option>
					  		<option value="20" ${cri.perPageNum==20 ? 'selected':'' }>20개씩</option>
					  		<option value="30" ${cri.perPageNum==30 ? 'selected':'' }>30개씩</option>
					  		<option value="50" ${cri.perPageNum==50 ? 'selected':'' }>50개씩</option>
					  	</select>		
<!-- 						<select class="form-control col-md-3" name="category" id="category" onchange="list_go(1);"> -->
<%-- 							<option value="rm" ${cri.category eq 'rm' ? 'selected':'' }>전 체</option> --%>
<%-- 							<option value="r" ${cri.category eq 'r' ? 'selected':'' }>받은 회의록</option> --%>
<%-- 							<option value="m" ${cri.category eq 'm' ? 'selected':'' }>내가 작성한 회의록</option> --%>
<!-- 						</select>	 -->
<!-- 						<select class="form-control col-md-3" name="sttCode" id="sttCode" onchange="list_go(1);"> -->
<%-- 							<option value="ticr" ${cri.sttCode eq 'ticr' ? 'selected':'' }>진행상태</option> --%>
<%-- 							<option value="t" ${cri.sttCode eq 't' ? 'selected':'' }>임시 저장</option> --%>
<%-- 							<option value="i" ${cri.sttCode eq 'i' ? 'selected':'' }>진행중</option> --%>
<%-- 							<option value="c" ${cri.sttCode eq 'c' ? 'selected':'' }>완 료</option> --%>
<%-- 							<option value="r" ${cri.sttCode eq 'r' ? 'selected':'' }>반 려</option> --%>
<!-- 						</select>	 -->
						<select class="form-control col-md-3" name="searchType" id="searchType">
							<option value="trc" ${cri.searchType eq 'trc' ? 'selected':'' }>검 색</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
							<option value="r" ${cri.searchType eq 'r' ? 'selected':'' }>작성자</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
						</select>	
						
						<!-- keyword -->
<!-- 						<span class="input-group-text"> <i class="far fa-calendar-alt"></i> </span> -->
<%-- 						<input type="text" class="form-control float-right" id="meetDe" name="meetDe" value="${cri.meetDe }">  --%>
						<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }" /> <span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span> <!-- end : search bar -->
					  </div>
					</div> <!-- 분류&검색 -->
				</div>
				<div class="card-body">
					<table class="table table-hover text-nowrap">
			                   <tr>
									<th style="width:10%;">번 호</th>
									<th style="width:40%;">제 목</th>
									<th style="">상태</th>
									<th style="width:15%;">작성자</th>
									<th>회의 일자</th>
									<th style="width:10%;">구분</th>
								</tr>
								<c:if test="${!empty procList }" >	
									<c:forEach items="${procList }" var="proc" varStatus="index">
										<tr>
											<td>${index.count }</td>
											<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
														 white-space: nowrap; text-overflow: ellipsis;">
												<a href="detail.do?from=list&prcdNo=${proc.prcdNo }">
													<span class="col-sm-12 ">${proc.title }</span>
												</a>
											</td>
											<td>
												<c:if test="${proc.sttCode eq 1}">
													<span class="badge bg-warning" style="font-size: 0.9em;">진행</span>
												</c:if>
												<c:if test="${proc.sttCode eq 2}" >
													<span class="badge bg-success" style="font-size: 0.9em;">완료</span>
												</c:if>
												<c:if test="${proc.sttCode eq 3}" >
													<span class="badge bg-red" style="font-size: 0.9em;">반려</span>
												</c:if>
											</td>
											<td>${proc.userNm }</td>
											<td>
												${proc.meetDe.split(" ")[0] }
											</td>
											<td>
												<c:if test="${proc.register eq loginUser.userId}">
													<span class="badge bg-secondary">내가 작성한 회의록</span>
												</c:if>
												<c:if test="${proc.register ne loginUser.userId}">
													<span class="badge bg-info">받은 회의록</span>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty procList }" >
				            		<tr>
				            			<td colspan="7" class="text-center">
				            				해당내용이 없습니다.
				            			</td>
				            		</tr>
				            	</c:if>
			                </table>
				</div>
				<div class="card-footer">
					<nav aria-label="pds list Navigation">
						<ul class="pagination justify-content-center m-0">
							<%@ include file="/WEB-INF/views/common/pagination.jsp" %>	
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="card card-outline card-secondary">
				<div class="card-header pt-3 pb-3">
					<h3 class="card-title pt-2">확인해야 할 회의록</h3>
				</div>
				<div class="card-body p-2" style="overflow: auto;">
					<c:if test="${!empty confirmList }" >	
						<c:forEach items="${confirmList }" var="conf" varStatus="index">
							<div class="card card-outline card-secondary collapsed-card">
								<div class="card-header">
									<h3 class="card-title">
<%-- 										<a onclick="location.href='detail.do?from=list&prcdNo='"${conf.prcdNo}>${conf.title }</a> --%>
										<a href="detail.do?from=list&prcdNo=${conf.prcdNo}">
											<span class="col-sm-12 ">${conf.title }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${conf.meetDe.split(" ")[0] }</span>
										</a>
									</h3>
								</div>	<!-- /.card-header -->
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty confirmList }" >
            			<div class="text-center">
            				해당내용이 없습니다.
            			</div>
	            	</c:if>
					
				</div>
			</div>
			<!-- /.card-body -->
		</div>





	</div>
</section>
<script>
window.addEventListener('DOMContentLoaded', proceedingsList);

function proceedingsList() {
	
	moment.locale('ko'); // 언어를 한국어로 설정함
    $('#meetDe').daterangepicker({
        singleDatePicker: false,
        locale :{ 
	        format: 'YYYYMMDD',
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
