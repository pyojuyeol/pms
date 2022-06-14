<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
<link rel="stylesheet"
	href="../../resources/bootstrap/plugins/jquery-ui/jquery-ui.css" />
<script src="../../resources/bootstrap/plugins/jquery-ui/jquery-ui.js"></script>
</head>
<body>
	<section class="content">
		<div class="card card-info card-outline">
			<div class="card-header">
				<div class="row">
					<div class="col-md-6 float-left m-2">
						<h3>스케줄러 관리</h3>
					</div>
					<div class="col-md-6 row mt-1">
						<div class="col-md-4"></div>
						<div class="col-md-4 float-right">
							<div>
								<input type="date" class="form-control" id="startdate"
									placeholder="시작일">
							</div>
						</div>
						<div class="col-md-4 float-right">
							<div>
								<input type="date" class="form-control" id="enddate"
									placeholder="종료일">
							</div>
						</div>
					</div>
				</div>
				<!-- <h1 class="card-title">공지사항</h1> -->
				<div class="row float-right">
					<!-- 분류&검색 -->
					<select class="col-md-3 custom-select" style="width: auto;"
						data-sortorder="">
						<option value="10">정렬 개수</option>
						<option value="20">20개씩</option>
						<option value="50">50개씩</option>
						<option value="100">100개씩</option>
					</select> <select class="col-md-3 custom-select" style="width: auto;"
						data-sortorder="">
						<option value="all">전 체</option>
						<option value="login">사 용</option>
						<option value="logout">비사용</option>
					</select>
					<!-- 					      	<div class="container-fluid">       searchbar -->
					<div class="col-md-6 float-right">
						<form action="simple-results.html">
							<div class="input-group">
								<input type="search" class="form-control form-control-md"
									placeholder="검색어 입력">
								<div class="input-group-append">
									<button type="submit" class="btn btn-md btn-default">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<!-- 						    </div>       searchbar -->
				</div>
				<!-- 분류&검색 -->

			</div>
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>NO</th>
							<th>이름</th>
							<th>정보</th>
							<th>구분</th>
							<td>시작일</td>
							<td>종료일</td>
							<td>간격</td>
							<td>사용여부</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>사용하지 않는 파일 삭제</td>
							<td>DB와 파일 저장소안에 사용되지 않는 파일을 일주일 간격으로 삭제</td>
							<td>정기</td>
							<td>2022-01-01</td>
							<td>2022-12-31</td>
							<td>7일</td>
							<td>사용</td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>

	</section>

</body>