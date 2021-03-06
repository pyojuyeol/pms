<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.js" integrity="sha512-Lii3WMtgA0C0qmmkdCpsG0Gjr6M0ajRyQRQSbTF6BsrVh/nhZdHpVZ76iMIPvQwz1eoXC3DmAg9K51qT5/dEVg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="<%=request.getContextPath()%>/resources/js/chartjs-plugin-datalabels.js"></script>

<style></style>

</head>
<title>Home</title>

<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prj.prjNm }&nbsp;&nbsp;<i class="fa-solid fa-gear pt-1" onclick="location.href='<%=request.getContextPath() %>/prjSetting/modifyForm.do?prjNo=${prj.prjNo }'" style="color:gray; cursor:pointer"></i></h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prj.prjNm }</a></li>
					<li class="breadcrumb-item active">홈</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-body">

			<!-- Home 내용 -->
			<div class="row">
				<div class="col-12 col-md-12 col-lg-10 order-2 order-md-1">
					<div class="row">
						<div class="col-12 col-sm-4">
							<div class="info-box bg-light">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted">전체 일감</span>
									<span class="info-box-number text-center text-muted mb-0" onclick="location.href='<%=request.getContextPath() %>/project/work/list.do'" style="cursor:pointer">${workTotalCount }개</span>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-4">
							<div class="info-box bg-light">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted">해결 되지 않은 이슈</span>
									<span class="info-box-number text-center text-muted mb-0" onclick="location.href='<%=request.getContextPath() %>/project/issue/list.do'" style="cursor:pointer">${unresolvedIssueCnt }개</span>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-4">
							<div class="info-box bg-light">
								<div class="info-box-content">
									<span class="info-box-text text-center text-muted">신규 공지사항</span>
									<span class="info-box-number text-center text-muted mb-0" onclick="location.href='<%=request.getContextPath() %>/project/notice/list.do'" style="cursor:pointer">${newNoticeCount }개</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-3">
					
						<div class="col-4 mb-2" id="chartDiv">
							<h4 style="font-size:16px; font-weight:bolder;"><i class="fa-solid fa-chevron-right pr-2" style="font-size:13px; color:#777; line-height:22px;"></i>진척도</h4>
							<canvas id="myChart" width="500vw" height="500vh"></canvas>
						</div>

						<div class="col-8">
							<h4 style="font-size:16px; font-weight:bolder;" class="mb-3"><i class="fa-solid fa-chevron-right pr-2" style="font-size:13px; color:#777; line-height:22px;"></i>최근 이슈 <i class="nav-icon fas fa-search pt-1 pl-2" onclick="location.href='<%=request.getContextPath() %>/project/issue/list.do'" style="color:gray; cursor:pointer; font-size:0.75em;"></i></h4>
							
							<c:if test="${empty issueList }">
								<div class="post">
									<div class="user-block mb-2">
										<span>등록된 이슈가 없습니다.</span>
									</div>
								</div>
							</c:if>
							
							<jsp:useBean id="now" class="java.util.Date" />
							<fmt:formatDate var="today" value="${now}" pattern="yyyyMMdd" />
							
							<c:if test="${!empty issueList }">
								<c:forEach items="${issueList }" var="issue" end="2">
								
									<div class="post clearfix" style="padding-bottom:0px;">
										<div class="user-block d-flex" style="margin-bottom:0px">
										
											<div class="" style="min-width:130px">
												<img class="img-circle manPicture" data-id="${issue.issueRegister }" style="display:block; width:40px; height:40px;"></img>
												<span class="username"> 
													<a href="#">${issue.userNm }</a> 
												</span> 
												<span class="description">
												
													<fmt:formatDate var="itDate" value="${issue.regDt}" pattern="yyyyMMdd" />
												
													<!-- 날짜 계산 -->
											        <c:if test="${itDate < today}">	
											        	<c:set var="resultDate" value="${today - itDate }"/>							        	
											        	<c:if test="${resultDate <= 6 }">${resultDate}일 전</c:if>
											        	<c:if test="${resultDate > 6 }"><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd"/></c:if>
											        </c:if>
													<c:if test="${itDate == today}">
														<fmt:formatDate var="nowTime" value="${now}"  pattern="HHmmss"/>  
														<fmt:formatDate var="itTime" value="${item.regDt}"  pattern="HHmmss"/>
														<c:set var="result" value="${nowTime - itTime }"/>
														<c:if test="${result < 60 }">방금 전</c:if>
														<c:if test="${result >= 60 && result < 6000 }"><fmt:formatNumber type="number" maxFractionDigits="0"  value="${result/100 }" />분 전</c:if>
														<c:if test="${result >= 6000 }"><fmt:formatNumber type="number" maxFractionDigits="0"  value="${result/10000 }" />시간 전</c:if>
											        </c:if>
<%-- 													<fmt:formatDate value="${issue.regDt }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> --%>
												
												</span>
											</div>
											
											<div class="flex-grow-1">
												<p class="">
													<a href="<%=request.getContextPath() %>/project/issue/detail.do?issueNo=${issue.issueNo}" style="cursor:pointer"><strong>${issue.title }</strong></a> 
<%-- 													<strong onclick="location.href='<%=request.getContextPath() %>/project/issue/detail.do?issueNo=${issue.issueNo}'" style="cursor:pointer">${issue.title }</strong> --%>
												</p>
												<p class="">
													${issue.content }
												</p>
											</div>
										</div>
									</div>
								
								</c:forEach>
							</c:if>
						</div>
						
					</div>
					
					
					<div class="row mt-4">
						<div class="col-12 p-0">
							<div class="card card-outline card-info">
								<div class="card-header">
									<h3 class="card-title">내 일감</h3>
									<i class="nav-icon fas fa-search float-right pt-1" onclick="location.href='<%=request.getContextPath() %>/project/work/list.do'" style="color:gray; cursor:pointer"></i>
									<div class="card-tools">
										<!-- <button type="button" class="btn btn-tool" onclick="projectList_go()">
											<i class="fa-solid fa-magnifying-glass"></i>
										</button> -->
									</div>
								</div>
								<div class="card-body table-responsive p-0" style="text-align: center;">
									<table class="table table-hover text-nowrap">
										<thead>
											<tr>
												<th style="width:5%">#</th>
												<th style="width:7%">일감번호</th>
												<th style="width:7%">상태</th>
												<th style="width:7%">우선순위</th>
												<th>제목</th>
												<th style="width: 15%">담당자</th>
												<th style="width:10%">진척도</th>
												<th style="width:15%">종료일자</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${empty myWorkList }">
												<tr>
						     						<td colspan="8" class="text-center">
						     							담당 중인 일감이 없습니다.
						     						</td>
						     					</tr>
											</c:if>
											
											<c:if test="${!empty myWorkList }">
												<c:forEach items="${myWorkList }" var="myWork" end="4" varStatus="status">
													
													<fmt:formatDate value="${myWork.endDt}" pattern="yyyy-MM-dd" var="endDate" />
													<fmt:parseDate value="${endDate }" pattern="yyyy-MM-dd" var="endDateT"/>
													<fmt:parseNumber value="${now.time / (1000*60*60*24) }" var="todayTime" integerOnly="true" />
													<fmt:parseNumber value="${endDateT.time / (1000*60*60*24) }" var="endDateTime" integerOnly="true" />
												
												
													<tr <c:if test="${endDateTime - todayTime <= 7 and endDateTime - todayTime > 3 and myWork.sttCode != 'c'}"> class="table-warning"</c:if>
														<c:if test="${endDateTime - todayTime <= 3 and myWork.sttCode != 'c'}"> class="table-danger"</c:if>
															  onclick="location.href='<%=request.getContextPath() %>${myWork.workUrl}&from=dashboard'" style="cursor:pointer">
														<td>${status.count}</td>
														<td>#${myWork.workNo }</td>
														<td>
															<c:choose>
																<c:when test="${myWork.sttCode eq 'r'}"><h5><span class="badge badge-pill badge-info">요청</span></h5></c:when>
																<c:when test="${myWork.sttCode eq 'p'}"><h5><span class="badge badge-pill badge-success">진행</span></h5></c:when>
																<c:when test="${myWork.sttCode eq 'f'}"><h5><span class="badge badge-pill badge-danger">피드백</span></h5></c:when>
																<c:when test="${myWork.sttCode eq 'c'}"><h5><span class="badge badge-pill badge-primary">완료</span></h5></c:when>
																<c:when test="${myWork.sttCode eq 'h'}"><h5><span class="badge badge-pill badge-secondary">보류</span></h5></c:when>
															</c:choose>
														</td>	
														<td>
															<c:choose>
																<c:when test="${myWork.ipcrCode eq 'l'}"><i class="fa-solid fa-arrow-down"></i>&nbsp;</c:when>
																<c:when test="${myWork.ipcrCode eq 'u'}"><i class="fa-solid fa-minus" style="color:green"></i>&nbsp;</c:when>
																<c:when test="${myWork.ipcrCode eq 'h'}"><i class="fa-solid fa-arrow-up" style="color:orange"></i>&nbsp;</c:when>
																<c:when test="${myWork.ipcrCode eq 'e'}"><i class="fa-solid fa-land-mine-on" style="color:red"></i>&nbsp;</c:when>
															</c:choose>
														</td>
														<td style="text-align:left;">${myWork.workTitle }</td>
														<td>${myWork.userNm }</td>
														<td>
															<c:choose>
																<c:when test="${myWork.workRate >= 80 }"><span class="badge bg-success">${myWork.workRate }%</span></c:when>
																<c:when test="${myWork.workRate >= 60 and myWork.workRate < 80}"><span class="badge bg-primary">${myWork.workRate }%</span></c:when>
																<c:when test="${myWork.workRate >= 30 and myWork.workRate < 60}"><span class="badge bg-warning">${myWork.workRate }%</span></c:when>
																<c:when test="${myWork.workRate < 30}"><span class="badge bg-danger">${myWork.workRate }%</span></c:when>
															</c:choose>
														</td>
														<td><fmt:formatDate value="${myWork.endDt }" pattern="yyyy-MM-dd" /></td>
							     					</tr>
						     					</c:forEach>
											</c:if>
										
										</tbody>
									</table>
								</div>
							</div>
						</div>
					
					</div>
					
					
				</div>		
							
				<div class="col-12 col-md-12 col-lg-2 order-1 order-md-2">

					<!-- 개요 -->
					<div class="card card-outline card-secondary collapsed-card">
						<div class="card-header">
							<h3 class="card-title">개요</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-card-widget="collapse">
									<i class="fas fa-plus"></i>
								</button>
							</div>
						</div>
						<div class="card-body" style="display: none; padding: 0px;"> 
							<table class="table table-bordered table-woo" style="height:150px; text-align:center; color: #505050">
								<COLGROUP style="width: 45%; background-color: #EEEEEE;">
								<COLGROUP style="width: 55%;">
								<tr>
									<td style="font-weight: bold;">상태</td>
									<td>
										<c:choose>
											<c:when test="${prj.sttCode eq 'p'}">진행중</c:when>
											<c:when test="${prj.sttCode eq 'c'}">완료</c:when>
											<c:when test="${prj.sttCode eq 'h'}">보류</c:when>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td style="font-weight: bold;">공개여부</td>
									<td>
										<c:choose>
											<c:when test="${prj.enabled eq 'y'}">공개</c:when>
											<c:when test="${prj.enabled eq 'n'}">비공개</c:when>
										</c:choose>
									</td>
								</tr>
								<tr>	
									<td style="font-weight: bold;">시작일자</td>
									<td><fmt:formatDate value="${prj.bgnDt }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td style="font-weight: bold;">종료일자</td>
									<td><fmt:formatDate value="${prj.endDt }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td style="font-weight: bold;">주관팀</td>
									<td>${prj.teamNm }</td>
								</tr>
							</table>
						</div>
					</div>

					<!-- 팀원 목록 -->
					<table class="table">
						<thead>
							<tr>
								<th style="width: 30%; text-align: center">#</th>
								<th style="width: 35%; text-align: center">이름</th>
								<th style="width: 35%; text-align: center">역할</th>
							</tr>
						</thead>
						<tbody>

							<c:if test="${empty prj.prjUserList }">
								<tr>
									<td colspan="7" class="text-center">팀원이 없습니다.</td>
								</tr>
							</c:if>

							<c:if test="${!empty prj.prjUserList }">
								<c:forEach items="${prj.prjUserList }" var="prjUser">
									<tr>
										<td style="width: 30%; padding: 3px;">
											<img class="img-circle manPicture" data-id="${prjUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0 auto;"></img>
										</td>
										<td style="width: 30%; text-align: center"><a>${prjUser.userNm }</a></td>
										<td style="width: 30%; text-align: center">
											<c:choose>
												<c:when test="${prjUser.prjRole eq 'PM'}">
													<span class="badge badge-danger">PM</span>
												</c:when>
												<c:when test="${prjUser.prjRole eq 'PL'}">
													<span class="badge badge-warning">PL</span>
												</c:when>
												<c:when test="${prjUser.prjRole ne 'PL'}">
													<a>${prjUser.prjRole }</a>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</c:if>

						</tbody>
					</table>
				</div>
			</div>


		</div>
	</div>
</section>


<script>
window.onload = function() {
	
	// 사진 넣기
	MemberPictureThumb('<%=request.getContextPath()%>');
	
	<c:if test="${from eq 'invitation' }">
		Swal.fire({
			text: '프로젝트 권한이 활성화 되었습니다.',
			icon: 'success',
			confirmButtonColor: '#007bff',
			confirmButtonText: '확인',
		});
	</c:if>
	
	
	// Chart.js
	const CHART_COLORS = [
			'rgb(255, 159, 64)',	// 주황
			'rgb(54, 162, 235)',	// 파랑
			'rgb(255, 99, 132)',	// 빨강
			'rgb(201, 203, 207)',	// 회색
			'rgb(75, 192, 192)'		// 초록
// 			'rgb(255, 205, 86)',	// 노랑
// 			'rgb(153, 102, 255)',	// 보라
		];

	var datas = [
			${sttCntList[0]},	// 요청
			${sttCntList[1]},	// 진행
			${sttCntList[2]},	// 피드백
			${sttCntList[4]},	// 보류
			${sttCntList[3]}	// 완료
		];
	
	var flag = false;
	console.log(${sttCntList[0]});
	console.log(${sttCntList[1]});
	console.log(${sttCntList[2]});
	console.log(${sttCntList[3]});
	console.log(${sttCntList[4]});
	
	if (${sttCntList[0]} != 0) flag = true;
	if (${sttCntList[1]} != 0) flag = true;
	if (${sttCntList[2]} != 0) flag = true;
	if (${sttCntList[3]} != 0) flag = true;
	if (${sttCntList[4]} != 0) flag = true;
	
	console.log(flag);
	
	if (flag == false) {
		document.getElementById('myChart').remove();
		
		var div = $('<div>').addClass("post m-3 text-center");
		var span = $('<span>').text("등록된 일감이 없습니다.");
		div.append(span);
		
		$('#chartDiv').append(div);
	}
	
	if (flag == true) {
		const ctx = document.getElementById('myChart').getContext('2d');
		var rateChart = new Chart(ctx, {
			type : 'pie',
			plugins : [ ChartDataLabels ],
			data : {
				labels : [ '요청', '진행', '피드백', '보류', '완료' ],
				datasets : [ {
					backgroundColor : CHART_COLORS,
	                borderColor: CHART_COLORS,
					data : datas,
					datalabels : {
						anchor : 'center',
						backgroundcolor : null,
						borderwidth : 0
					},
				} ]
			},
			options : {
				// core options
				responsive : true,
				cutoutpercentage : 32,
	// 			layout : {
	// 				padding : 32
	// 			},
				elements : {
					line : {
						fill : false
					},
					point : {
						hoverradius : 7,
						radius : 5
					}
				},
				plugins : {
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : '일감 상태별 현황'
					},
					datalabels : {
	// 					backgroundColor : function(context) {
	// 						return context.dataset.backgroundColor;
	// 					},
	// 					borderColor : 'white',
	// 					borderRadius : 25,
	// 					borderWidth : 2,
						color : 'white',
						display : function(context) {
							return context.dataset.data[context.dataIndex] > 0;
						},
						font : {
							weight : 'bold',
							size : '17'
						},
						padding : 6,
						formatter: (value, ctx) => {
			                let datasets = datas;
			                if (value != 0 ) {
			                    let sum = 0;
			                    dataArr = datas;
			                    dataArr.map(data => {
			                        sum += parseInt(data);
			                    });
			                    let percentage = Math.round((value * 100 / sum)) + "%";
			                    return percentage;
			                } else {
			                	let percentage = "";
			                    return percentage;
			                }
			            }
					}
				},
	
			}
		});
	}
}
</script>


</body>