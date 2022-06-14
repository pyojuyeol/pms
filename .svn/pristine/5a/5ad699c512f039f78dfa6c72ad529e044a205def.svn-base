<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-body">

			<div class="row">
				<div class="col-12">
					<div class="card card-outline card-info">

						<div class="card-header">
							<h3 class="card-title">진행 프로젝트 현황</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool">더보기</button>
							</div>
						</div>
						<div class="card-body p-3">
							<!-- 프로젝트 진행도 body -->

							<div class="card-body">
								<div class="chart">
									<div class="chartjs-size-monitor">
										<div class="chartjs-size-monitor-expand">
											<div class=""></div>
										</div>
										<div class="chartjs-size-monitor-shrink">
											<div class=""></div>
										</div>
									</div>
									<canvas id="barChart"
										style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%; display: block; width: 605px;"
										width="605" height="250" class="chartjs-render-monitor"></canvas>
								</div>
							</div>

						</div>


						<!-- 프로젝트 진행도 body -->
					</div>
				</div>
			</div>


					<div class="row">
						<div class="col-6">
							<!-- Default box -->
							<div class="card card-outline card-info">
								<div class="card-header">
									<h3 class="card-title">확인하지 않은 보고</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-tool">더보기</button>
									</div>
								</div>
								<div class="card-body p-3">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>분류</th>
												<th>제목</th>
												<th>상태</th>
												<th>결재자</th>
												<th>작성일</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>일일</td>
												<td><a href="./detail.jsp">이소망 일일 업무보고</a></td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
											<tr>
												<td>주간</td>
												<td>이소망 주간 업무보고</td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
											<tr>
												<td>월간</td>
												<td>이소망 월간 업무보고</td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
											<tr>
												<td>일일</td>
												<td>이소망 일일 업무보고</td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
											<tr>
												<td>주간</td>
												<td>이소망 주간 업무보고</td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
											<tr>
												<td>월간</td>
												<td>이소망 월간 업무보고</td>
												<td>상신</td>
												<td>허리보</td>
												<td>2022-01-01 14:00</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>

					</div>
					<!-- /.card-body -->



		</div>
	</div>
</section>



<script>
	$(function() {
		/* ChartJS
		 * -------
		 * Here we will create a few charts using ChartJS
		 */

		var areaChartData = {
			labels : [ '소다소프트', '화해', '쏘카', '나도 집에 가고싶어', '쏘카', '나도 집에 가고싶어', '소다소프트', '화해' ],
			datasets : [ {
				label : '프로젝트 진행도',
				backgroundColor : 'rgba(60,141,188,0.9)',
				borderColor : 'rgba(60,141,188,0.8)',
				pointRadius : false,
				pointColor : '#3b8bba',
				pointStrokeColor : 'rgba(60,141,188,1)',
				pointHighlightFill : '#fff',
				pointHighlightStroke : 'rgba(60,141,188,1)',
				data : [ 28, 48, 40, 19, 24, 95, 34, 25 ]
			}, ]
		}

		//-------------
		//- BAR CHART -
		//-------------
		var barChartCanvas = $('#barChart').get(0).getContext('2d')
		var barChartData = $.extend(true, {}, areaChartData)
		var temp0 = areaChartData.datasets[0]
		var temp1 = areaChartData.datasets[0]
		barChartData.datasets[0] = temp1
// 		barChartData.datasets[1] = temp0

		var barChartOptions = {
			responsive : true,
			maintainAspectRatio : false,
			datasetFill : false
		}

		new Chart(barChartCanvas, {
			type : 'bar',
			data : barChartData,
			options : barChartOptions
		})

	})
</script>

