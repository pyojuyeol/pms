<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- loading -->
<div id="loader" class="loader">
	<div id="container">
        <div class="divider" aria-hidden="true"></div>
        <p class="loading-text" aria-label="Loading">
          <span class="letter" aria-hidden="true">L</span>
          <span class="letter" aria-hidden="true">o</span>
          <span class="letter" aria-hidden="true">a</span>
          <span class="letter" aria-hidden="true">d</span>
          <span class="letter" aria-hidden="true">i</span>
          <span class="letter" aria-hidden="true">n</span>
          <span class="letter" aria-hidden="true">g</span>
        </p>
    </div>
</div>

<!-- Navbar -->
<nav id="main-header"
	class="main-header navbar navbar-expand-lg navbar-light navbar-white">
	<div class="container-fluid">
		<a href="<%=request.getContextPath()%>/dashboard.do" class="navbar-brand ml-1" id="home"> 
		<img src="<%=request.getContextPath()%>/resources/images/common/logo_p.svg"
			alt="Logo" class="brand-image">
		</a>

		<button class="navbar-toggler order-1" type="button"
			data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse order-3" id="navbarCollapse">

			<ul class="navbar-nav" id="mainMenu">

				<!-- 여기에 메뉴추가 -->

			</ul>

		</div>

		<!-- Right navbar links -->
		<ul class="order-1 order-md-3 navbar-nav navbar-icon navbar-no-expand ml-auto d-flex align-items-center">
		
		      <li class="nav-item dropdown">
		        <a class="nav-link" data-toggle="dropdown" href="#">
		        	<i class="fa-solid fa-magnifying-glass"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right" style="min-width:230px">
					<div class="dropdown-header position-relative">
						<span class="font-weight-bold" style="font-size:13px;">프로젝트 선택</span>
					</div>
					<div id="myProject"></div>
					
				</div>
		      </li>
			

			<!-- Notifications Dropdown Menu -->
			<li class="nav-item dropdown mr-1">
				<a class="nav-link" data-toggle="dropdown" href="#"> 
					<i class="fa-solid fa-bell text-yellow"></i>
					<span id="nCnt" class="d-none badge badge-danger navbar-badge" style="right:0; top:5px;">0</span>
				</a>
				<div class="dropdown-menu dropdown-menu-right" style="min-width:280px">
					<div class="dropdown-header position-relative">
						<span class="font-weight-bold" style="font-size:13px;">알림</span>
						<a href="<%=request.getContextPath()%>/push/list.do" class="d-block position-absolute h6" style="padding:1rem;right:0;top:-16%;"><i class="fa-solid fa-ellipsis-vertical"></i></a>
					</div>
					
					<div id="menuPushList"></div>
					
					<%@ include file="/WEB-INF/views/push/newList.jsp" %>
				</div>
			</li>

	        <li class="nav-item">
			  <a class="nav-link" href="javascript:OpenWindow('<%=request.getContextPath()%>/cloud/main.open', 'cloud', 1200, 800);" style="color : pink;">
			    <i class="fa-solid fa-cloud"></i>
			  </a>
		    </li>
			<c:if test="${loginUser.authGrpCode eq 'ROLE_ADMIN' }">
				<li class="nav-item">
					<a class="nav-link px-2" href="<%=request.getContextPath()%>/admin/dbcn/list.do">
						<i class="fa-solid fa-user-gear" style="color : #4dc7fd;"></i>
					</a>
				</li>
			</c:if>

			<li class="nav-item user-panel">
				<a class="image" href="<%=request.getContextPath()%>/myPage/dash.do" style="padding-right: 0.8rem;"> 
					<span class="img-circle manPicture" data-id="${loginUser.userId }" style="display: block; width: 40px; height: 40px; margin: 0px auto;"></span>
				</a>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link" href="<%=request.getContextPath()%>/common/logout.do"> <i class="fa-solid fa-right-from-bracket"></i></a>
			</li>



		</ul>
	</div>
</nav>
<!-- /.navbar -->

<script>
window.addEventListener('DOMContentLoaded', getPictureIcon);
function getPictureIcon(){
	MemberPictureThumb('<%=request.getContextPath()%>');
}
</script>
