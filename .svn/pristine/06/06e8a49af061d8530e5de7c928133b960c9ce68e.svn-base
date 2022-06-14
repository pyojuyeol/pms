<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="userList" value="${dataMap.userList }"/>
<c:set var="pageMaker" value="${dataMap.pageMaker }"/>
<c:set var="cri" value="${dataMap.pageMaker.cri }"/>

<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cloud/cloud.css" />

<title>PROSPEC'S | 사원관리</title>

</head>

<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
<!-- 				<h1>프로젝트이름</h1> -->
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item"><a href="#">사원관리</a></li>
					<li class="breadcrumb-item active">목록</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<!-- Content Wrapper. Contains page content -->
<section class="content">
	<div class="card card-info card-outline mb-1">
		<div class="card-header">
			<div class="d-flex justify-content-between align-items-center">
				<div class="input-group row" >
					<button type="button" onclick="location.href='registForm.do'"
						class="btn btn-primary mr-1">등록</button>
					<label class="btn btn-md btn-outline-success mb-0" for="fileInput">
					<i class="fa-solid fa-file-arrow-up mr-2"></i>엑셀 파일로 등록</label>
					<button class="btn btn-md btn-outline-secondary ml-2" type="button" onclick="doExcelDownloadProcess('Form')"><b><i class="fa-solid fa-file-arrow-down mr-2"></i>양식 다운로드</b></button>
						 
				</div>
				
				<div class="input-group row">
					<!-- search bar -->

					<!-- sort num -->
					<select class="form-control col-md-3" name="perPageNum"
						id="perPageNum" onchange="list_go(1);">
						<option value="10" ${cri.perPageNum eq 10 ? 'selected' : '' }>정렬개수</option>
						<option value="20" ${cri.perPageNum eq 20 ? 'selected' : '' }>20개씩</option>
						<option value="30" ${cri.perPageNum eq 30 ? 'selected' : '' }>30개씩</option>
						<option value="50" ${cri.perPageNum eq 50 ? 'selected' : '' }>50개씩</option>
					</select>

					<!-- search bar -->
					<select class="form-control col-md-3" name="searchType"
						id="searchType">
<!-- 						<option value="">검색구분</option> -->
						<option value="n" ${cri.searchType eq 'n' ? 'selected':'' }>이 름</option>
					</select>

					<!-- keyword -->
					<input class="form-control" type="text" name="keyword"
						placeholder="검색어를 입력하세요." value="${cri.keyword }" onkeyup="keword_go();"> <span
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
		<!-- /.card-header -->
		<div class="card-body">
		<section class="content">

				<div class="card">
					<div class="card-body p-0">
			<table id="userTable" class="table table-hover text-nowrap text-center">
				<thead>
					<tr class="text-center">
						<th style="width:5%"></th>
						<th style="width:17%">사원번호</th>
						<th style="width:12%">이름</th>
						<th style="width:8%">성별</th>
						<th style="width:10%">직급</th>
						<th style="width:20%">부서</th>
						<th style="width:8%">권한</th>
						<th style="width:15%">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
					<tr id="${user.userId }" class="user-tr">
						<td style="padding:3px;">
		            		<span class="img-circle manPicture" data-id="${user.userId }" style="display:block;width:40px;height:40px;margin:0 auto;"></span>
		            	</td>
						<td>${user.userId }</td>
						<td>${user.userNm }</td>
						<td>${user.gender }</td>
						<td>
						<c:forEach var="position" items="${positions}">
						  <c:if test="${position.code eq user.positionCode }">${position.nm }</c:if>
						</c:forEach>
						</td>
						<td>
						<c:forEach var="dept" items="${depts}">
						  <c:if test="${dept.code eq user.deptCode }">${dept.nm }</c:if>
						</c:forEach>
						</td>
						<td>
						<c:if test="${'ROLE_ADMIN' eq user.authGrpCode}">
							<i class="fa-solid fa-user fa-lg" style="color:gold" title="관리자(admin)">A</i>
<%-- 							${user.authGrpCode } --%>
						</c:if>
						<c:if test="${'ROLE_MANAGER' eq user.authGrpCode}">
							<i class="fa-solid fa-user fa-lg" style="color:silver" title="관리자(manager)">M</i>
<%-- 							${user.authGrpCode } --%>
						</c:if>
						<c:if test="${'ROLE_USER' eq user.authGrpCode}">
							<i class="fa-solid fa-user fa-lg" style="color:#8f784b" title="일반사용자(user)"></i>
<%-- 							${user.authGrpCode } --%>
						</c:if>
						</td>
						<td>
							<fmt:formatDate value="${user.regdate }" pattern="yyyy-MM-dd"/>
			            </td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="input-group row d-flex flex-row-reverse">
		<button class="btn btn-md btn-outline-success float-right mb-3" type="button" onclick="doExcelDownloadProcess('')"><strong><i class="fa-solid fa-file-arrow-down mr-2"></i>전체 명단 다운로드</strong></button>
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
	</div>
</section>
 <div class="popupLayer rightmenu_layer rightmenu_on">
 	<ul class="default min_right_ly">
	 	<li><a href="javascript:detail_member();" style="text-indent: 0px;">상세</a></li>
		<li><a href="javascript:modify_member();" style="text-indent: 0px;">수정</a></li>
	</ul>
 </div>
 	
<script>
function detail_member(){
	location.href = "detail.do?id="+idValueForMenu;
}
function modify_member(){
	location.href = "modifyForm.do?id="+idValueForMenu;
	
}
</script>
<script>
window.addEventListener('DOMContentLoaded', onloadMemberList);
var idValueForMenu ="";
function onloadMemberList() {
 	MemberPictureThumb('<%=request.getContextPath()%>');
	HideLoading();
	<c:if test="${from eq 'regist' }">
		alertN("정상 등록되었습니다.");
	</c:if>
	document.addEventListener('mousedown', function(e) {
	  	if ((event.button == 2) || (event.which == 3)) {
	  		console.log(e.target.closest("tr").getAttribute("id"));
	  		$("#layerContent").text(""+e.target.closest("tr").getAttribute("id"));
	  		idValueForMenu = e.target.closest("tr").getAttribute("id")
	  	}
	});

	var areaObj = $(".user-tr");
	var layerObj = $(".popupLayer");
	ShowLayer(areaObj,layerObj,"rclick");
	var areaObj2 = $(".content-wrapper");
	HideLayer(areaObj2,layerObj);
}
</script>
<script>
 function keword_go() {
     if (window.event.keyCode == 13) {
    	 list_go(1);
     }
}
 </script>
 <script>
  function doExcelUploadProcess(){
      var f = new FormData(document.getElementById('form1'));
      $.ajax({
          url: "uploadExcelFile",
          data: f,
//           async : false,
          processData: false,
          contentType: false,
          type: "POST",
          success: function(data){
		  	HideLoading();        	  
        	alertN("정상 등록 되었습니다.");
          },
      	  error: function(error) {
        	HideLoading();        	  
      		alertE("등록에 실패했습니다.");
      	  }
      });
      ShowLoading();

  }
	  
</script>
<script>
 
</script>
<script>

function doExcelDownloadProcess(index){
    var f = document.form1;
    f.action = "downloadExcelFile"+index;
    f.submit();
}
  
</script>

<form id="form1" name="form1" method="post" enctype="multipart/form-data">
    <input type="file" id="fileInput" name="fileInput" hidden="" onchange="doExcelUploadProcess()">
    
</form>

<div id="result">
</div>
<script>
 
  </script>
</body>