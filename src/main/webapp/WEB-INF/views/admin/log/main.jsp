<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="connectLogList" value="${logData.connectLogList }" />
<c:set var="connectPageMaker" value="${logData.pageMaker }" />
<body>
	<!-- 헤더 시작 -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-12 d-none d-sm-block">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/admin/dashboard.do">Home</a></li>
						<li class="breadcrumb-item active">이력관리</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<!-- 헤더 끝 -->
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!--현재 DB 접속 본문끝 -->
			<div class="col-12">
				<div class="card card-purple card-outline card-outline-tabs">
					<div class="card-header p-0 border-bottom-0">
						<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
							<li class="nav-item">
								<a class="nav-link active show" data-toggle="pill" href="#connectLog" role="tab" aria-controls="connectLog" aria-selected="true">접속 로그</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" data-toggle="pill" href="#fileLog" role="tab" aria-controls="fileLog" aria-selected="false">파일 다운로드 로그</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" data-toggle="pill" href="#systemLog" role="tab" aria-controls="systemLog" aria-selected="false">스케줄러 실행 로그</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" data-toggle="pill" href="#dbcnLog" role="tab" aria-controls="dbcnLog" aria-selected="false">DB 접속 로그</a>
							</li>
						</ul>
					</div>
					<div class="card-body" style="padding-top: 0px; padding-bottom: 0px; min-height: 580px;">
						<div class="tab-content" id="custom-tabs-four-tabContent">
							<div class="tab-pane fade active show" id="connectLog" role="tabpanel" aria-labelledby="connectLog">
								<div class="card-header px-0">
									<div class="d-flex justify-content-end">
										<div id="keyword" class="col-md-7">
											<div class="input-group">
												<!-- search bar -->
												<!-- 날짜 검색 -->
												<div class="input-group-prepend">
													<span class="input-group-text">
														<i class="far fa-calendar-alt"></i>
													</span>
												</div>
												<input type="text" class="form-control rangeDt" name="rangeDt">
												<!-- 날짜 검색 끝 -->
												<!-- search bar -->
												<select class="form-control col-md-3" name="searchType" id="searchType">
													<option value="">검색구분</option>
													<option value="u" ${param.searchType eq 'u'? 'selected':''}>사원 ID</option>
													<option value="i" ${param.searchType eq 'i'? 'selected':''}>접속 IP</option>
												</select>
			
												<!-- keyword -->
												<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword}">
												<span class="input-group-append">
													<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
														<i class="fa fa-fw fa-search"></i>
													</button>
												</span>
												<!-- end : search bar -->
											</div>
										</div>
									</div>
								</div>
								<table class="table table-striped">
									<thead>
										<tr style="text-align: center;">
											<th>NO</th>
											<th>구분</th>
											<th>사원 ID</th>
											<th>접속 IP</th>
											<th>로그인 시간</th>
											<th>로그아웃 시간</th>
											<th>사용 시간</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${connectLogList }" var="connectLog">
											<tr style="text-align: center;">
												<td>${connectLog.cnLogNo }</td>
												<c:choose>
													<c:when test="${empty connectLog.logoutDt }">
														<td>
															<span class="badge bg-green">Connect</span>
														</td>
													</c:when>
													<c:otherwise>
														<td>
															<span class="badge bg-red">Disconnect</span>
														</td>
													</c:otherwise>
												</c:choose>
												<td>${connectLog.userId }</td>
												<td>${connectLog.ip }</td>
												<td>
													<span class="tag tag-success">
														<fmt:formatDate value="${connectLog.loginDt }" pattern="yyyy-MM-dd HH:mm:ss" />
													</span>
												</td>
												<c:if test="${not empty connectLog.logoutDt }">
												<td>
													<span class="tag tag-success">
														<fmt:formatDate value="${connectLog.logoutDt }" pattern="yyyy-MM-dd HH:mm:ss" />
													</span>
												</td>
												<td>
													<span class="tag tag-success">
														${connectLog.cnTime }
													</span>
												</td>
												</c:if>
												<c:if test="${empty connectLog.logoutDt }">
												<td>
													<span class="tag tag-success">
														-
													</span>
												</td>
												<td>
													<span class="tag tag-success">
														-
													</span>
												</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- pagination -->
					<div class="card-footer">
						<nav aria-label="Navigation">
							<ul class="pagination justify-content-center m-0">
								<li class="page-item">
									<a class="page-link" href="javascript:list_go(1);">
										<i class="fas fa-angle-double-left"></i>
									</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="javascript:list_go('${connectPageMaker.prev ? connectPageMaker.startPage-1 : connectPageMaker.cri.page}');">
										<i class="fas fa-angle-left"></i>
									</a>
								</li>
								<c:forEach var="pageNum" begin="${connectPageMaker.startPage }" end="${connectPageMaker.endPage }" >
									<li class="page-item ${connectPageMaker.cri.page == pageNum?'active':''}">
										<a class="page-link" href="javascript:list_go('${pageNum}');" >${pageNum }</a>
									</li>
								</c:forEach>
								<li class="page-item">
									<a class="page-link" href="javascript:list_go('${connectPageMaker.next ? connectPageMaker.endPage+1 :connectPageMaker.cri.page}');">
										<i class="fas fa-angle-right" ></i>
									</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="javascript:list_go('${connectPageMaker.realEndPage}');">
										<i class="fas fa-angle-double-right"></i>
									</a>
								</li>	
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
	
		<script type="text/x-handlebars-template"  id="fileLog-list-template">
		<div class="tab-pane fade active show" id="fileLog" role="tabpanel" aria-labelledby=fileLog>
			<div class="card-header px-0">
				<div class="d-flex justify-content-end">
					<div id="keyword" class="col-md-7">
						<div class="input-group">
							<!-- search bar -->
							<!-- 날짜 검색 -->
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="far fa-calendar-alt"></i>
								</span>
							</div>
							<input type="text" class="form-control rangeDt" name="rangeDt">
							<!-- 날짜 검색 끝 -->
							<!-- search bar -->
							<select class="form-control col-md-3" name="searchType" id="searchType">
								<option value="">검색구분</option>
								<option value="f" ${param.searchType eq 'f'? 'selected':''}>파일명</option>
								<option value="u" ${param.searchType eq 'u'? 'selected':''}>사원 ID</option>
								<option value="i" ${param.searchType eq 'i'? 'selected':''}>다운로드 IP</option>
							</select>
	
							<!-- keyword -->
							<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword}">
							<span class="input-group-append">
								<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
									<i class="fa fa-fw fa-search"></i>
								</button>
							</span>
							<!-- end : search bar -->
						</div>
					</div>
				</div>
			</div>
			
			<table class="table table-striped">
				<thead>
					<tr style="text-align : center;">
						<th>NO</th>
						<th>파일명</th>
						<th>사원 ID</th>
						<th>다운로드 IP</th>
						<th>다운로드 일시</th>
					</tr>
				</thead>
				<tbody>
					{{#isNotNull .}}
					{{#each fileLogList}}
						<tr>
							<td style="width: 10%; text-align: center;">{{fileDownLogNo}}</td>
							<td class="ellipsis-box" style="width: 30%">
								<span class="ellipsis-text" style="max-width: 450px;">{{fileNm}}</span>
								<p class="arrow_box">{{fileNm}}</p>
							</td>
							<td style="width: 20%; text-align: center;">{{userId}}</td>
							<td style="width: 20%; text-align: center;">{{ip}}</td>
							<td style="width: 20%; text-align: center;">
								<span class="tag tag-success">{{dateFommater dwldTime}}</span>
							</td>
						</tr>
					{{/each}}
					{{else}}
						<tr style="text-align: center;">
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					{{/isNotNull}}
				</tbody>
			</table>
			

		</div>
	</script>

	<script type="text/x-handlebars-template"  id="connectLog-list-template" >
		<div class="tab-pane fade active show" id="connectLog" role="tabpanel" aria-labelledby="connectLog">
		<div class="card-header px-0">
			<div class="d-flex justify-content-end">
				<div id="keyword" class="col-md-7">
					<div class="input-group">
						<!-- search bar -->
						<!-- 날짜 검색 -->
						<div class="input-group-prepend">
							<span class="input-group-text">
								<i class="far fa-calendar-alt"></i>
							</span>
						</div>
						<input type="text" class="form-control rangeDt" name="rangeDt">
						<!-- 날짜 검색 끝 -->
						<!-- search bar -->
						<select class="form-control col-md-3" name="searchType"
							id="searchType">
							<option value="">검색구분</option>
							<option value="u" ${param.searchType eq 'u'? 'selected':''}>사원 ID</option>
							<option value="i" ${param.searchType eq 'i'? 'selected':''}>접속 IP</option>
						</select>
			
						<!-- keyword -->
						<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword}">
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
						<!-- end : search bar -->
					</div>
				</div>
			</div>
		</div>
		<table class="table table-striped">
			<thead>
				<tr style="text-align: center;">
					<th>NO</th>
					<th>구분</th>
					<th>사원 ID</th>
					<th>접속 IP</th>
					<th>로그인 시간</th>
					<th>로그아웃 시간</th>
					<th>사용 시간</th>
				</tr>
			</thead>
			<tbody>
				{{#isNotNull .}}
				{{#each connectLogList}}
					<tr style="text-align: center;">
						<td>{{cnLogNo}}</td>
						{{#if logoutDt}}
							<td>
								<span class="badge bg-red">Disconnect</span>
							</td>
						{{else}}
							<td>
								<span class="badge bg-green">Connect</span>
							</td>
						{{/if}}
						<td>{{userId}}</td>
						<td>{{ip}}</td>
						<td>
							<span class="tag tag-success">{{dateFommater loginDt}}</span>
						</td>
						{{#if logoutDt}}
						<td>
							<span class="tag tag-success">{{dateFommater logoutDt}}</span>
						</td>
						<td>
							<span class="tag tag-success">{{cnTime}}</span>
						</td>
						{{else}}
						<td>
							<span class="tag tag-success">-</span>
						</td>
						<td>
							<span class="tag tag-success">-</span>
						</td>
						{{/if}}
					</tr>
				{{/each}}
				{{else}}
					<tr style="text-align: center;">
						<td colspan="7">
							<strong>해당 내용이 없습니다.</strong>
						</td>
					</tr>
				{{/isNotNull}}
			</tbody>
		</table>
	</div>
	</script>
	
	<script type="text/x-handlebars-template"  id="systemLog-list-template" >
		<div class="tab-pane fade active show" id="sysSchedLog" role="tabpanel" aria-labelledby=sysSchedLog>
			<div class="card-header px-0">
				<div class="d-flex justify-content-between">
					<button type="button" class="btn btn-outline-primary" onclick="schedulerExec()">스케줄러 즉시 실행</button>
					<div id="keyword" class="col-md-7">
						<div class="input-group">
							<!-- search bar -->
							<!-- 날짜 검색 -->
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="far fa-calendar-alt"></i>
								</span>
							</div>
							<input type="text" class="form-control rangeDt" name="rangeDt">
							<!-- 날짜 검색 끝 -->
							<!-- search bar -->
							<select class="form-control col-md-3" name="searchType" id="searchType">
								<option value="">검색구분</option>
								<option value="n" ${param.searchType eq 'n'? 'selected':''}>스케줄러 이름</option>
								<option value="r" ${param.searchType eq 'r'? 'selected':''}>실행 결과</option>
							</select>
		
							<!-- keyword -->
							<input class="form-control" type="text" name="keyword"
								placeholder="검색어를 입력하세요." value="${param.keyword}"> <span
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
			</div>
			<table class="table table-striped">
				<thead>
					<tr style="text-align: center;">
						<th>NO</th>
						<th>스케줄러 이름</th>
						<th>시작 일시</th>
						<th>종료 일시</th>
						<th>소요 시간</th>
						<th>실행 결과</th>
					</tr>
				</thead>
				<tbody>
					{{#isNotNull . }}
					{{#each systemLogList}}
						<tr>
							<td style="text-align: center;">{{sysSchedNo}}</td>
							<td>{{nm}}</td>
							<td style="text-align: center;">
								<span class="tag tag-success">{{dateFommater bgnDt}}</span>
							</td>
							<td style="text-align: center;">
								<span class="tag tag-success">{{dateFommater endDt}}</span>
							</td>
							<td>{{takedTime}}</td>
							{{#isSuccess resultCode}}
								<td style="text-align: center;"><span class="badge bg-green">Success</span></td>
							{{else}}
								<td style="text-align: center;"><span class="badge bg-red">Fail</span></td>
							{{/isSuccess}}
						</tr>
					{{/each}}
					{{else}}
						<tr style="text-align: center;">
							<td colspan="6">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					{{/isNotNull}}
				</tbody>
			</table>
		</div>
	</script>
	
	<script type="text/x-handlebars-template"  id="dbcnLog-list-template" >
		<div class="tab-pane fade active show" id="dbcnLog" role="tabpanel" aria-labelledby=dbcnLog>
			<div class="card-header px-0">
				<div class="d-flex justify-content-end">
					<div id="keyword" class="col-md-7">
						<div class="input-group">
							<!-- search bar -->
							<!-- 날짜 검색 -->
							<div class="input-group-prepend">
								<span class="input-group-text">
									<i class="far fa-calendar-alt"></i>
								</span>
							</div>
							<input type="text" class="form-control rangeDt" name="rangeDt">
							<!-- 날짜 검색 끝 -->
							<!-- search bar -->
							<select class="form-control col-md-3" name="searchType" id="searchType">
								<option value="">검색구분</option>
								<option value="f" ${param.searchType eq 'f'? 'selected':''}>접속 파일명</option>
								<option value="t" ${param.searchType eq 'u'? 'selected':''}>종류</option>
							</select>
		
							<!-- keyword -->
							<input class="form-control" type="text" name="keyword"
								placeholder="검색어를 입력하세요." value="${param.keyword}"> <span
								class="input-group-append">
								<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
									<i class="fa fa-fw fa-search"></i>
								</button>
							</span>
							<!-- end : search bar -->
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped">
				<thead>
					<tr style="text-align: center;">
						<th>NO</th>
						<th>DB 이름</th>
						<th>접속 정보</th>
						<th>접속 파일 경로</th>
						<th>접속 파일명</th>
						<th>변경 일시</th>
					</tr>
				</thead>
				<tbody>
					{{#isNotNull .}}
					{{#each dbcnLogList}}
						<tr>
							<td style="text-align: center;">{{cngLogNo}}</td>
							<td>{{dbNm}}</td>
							<td>{{dbcnUser}}({{dbcnUrl}})</td>
							<td class="ellipsis-box" style="width: 15%">
								<span class="ellipsis-text" style="max-width: 180px;">{{path}}</span>
								<p class="arrow_box">{{path}}</p>
							</td>
							<td class="ellipsis-box" style="width: 30%">
								<span class="ellipsis-text" style="max-width: 450px;">{{fileNm}}</span>
								<p class="arrow_box">{{fileNm}}</p>
							</td>
							<td style="text-align: center;">
								<span class="tag tag-success">{{dateFommater cngDt}}</span>
							</td>
						</tr>
					{{/each}}
					{{else}}
						<tr style="text-align: center;">
							<td colspan="6">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					{{/isNotNull}}
				</tbody>
			</table>
		</div>
	</script>
	
	<script>
	window.addEventListener('load', initbabo)
	
	function initbabo() {
	
	    //Date range picker
		datePickerCall();
		
	    Handlebars.registerHelper({
	    	  "isSuccess" : function (result, options) {
	    		  	  if (result == "S") {
	    		  		  return options.fn(this);
	    		  	  } else {
	    		  		  return options.inverse(this);
	    		  	  }
	    	  },
			  "isNotNull" : function(dataMap, options) {
				  	
			          if (Object.values(dataMap)[0].length >= 1) {
			            return options.fn(this);
			          } else {
				          return options.inverse(this);
			          }
		      },
			  "dateFommater" : function(date) {
			    return new Date(date).format("yyyy-MM-dd a/p hh:mm:ss");
			  }
		});
	}
	function schedulerExec() {
		Swal.fire({
			  text: '스케줄러가 즉시 실행됩니다. 계속 진행 하시겠습니까?',
			  icon: 'warning',
			  confirmButtonColor: '#007bff',
			  cancelButtonColor: '#dc3545',
			  showCancelButton: true,
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((confirm) => {
			  /* Read more about isConfirmed, isDenied below */
			  if (confirm.isConfirmed) { 
				$.ajax({ 
					url: "<%=request.getContextPath()%>/admin/log/schedulerExec", 
					type: "POST",
					success: function(result){
						
						$("a[aria-controls='systemLog']").click();
					}
				});
			  }
			});
	}
	</script>
	
	<script src="<%=request.getContextPath()%>/resources/js/adminLog.js"></script>
</body>