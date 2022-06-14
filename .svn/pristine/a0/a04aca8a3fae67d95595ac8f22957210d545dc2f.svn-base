<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<title>PROSPEC'S | 책갈피</title>

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
					<li class="breadcrumb-item"><a href="#">${prj.prjNm }</a></li>
					<li class="breadcrumb-item"><a href="#">책갈피</a></li>
					<li class="breadcrumb-item active">수정</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-outline card-secondary">
		<div class="card-header">
			<h3 class="card-title p-2">책갈피 수정</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-primary mr-3" id="registBtn" onclick="modify_go();">등 록</button>
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'" >취 소</button>
			</div>
		</div>
			<div class="content-wrapper">
			    <!-- Content Wrapper. Contains page content -->
			    <div class="col-12">
			            <!-- /.card-header -->
			
					<!-- Main content -->
						<div class="card-body pad">
							<form enctype="multipart/form-data" role="form" method="post" action="modify.do" name="modifyForm">
								<div class="form-group">
									<input type="hidden" value=${bmk.bookmarkNo } name="bookmarkNo">
									<label class="form-group mr-3" for="title"><b>책갈피</b></label>
									<input type="text" id="title"
										name='bmkTitle'  class="form-control col-12"  value="${bmk.bmkTitle }">
			
			<!-- 						<label class="form-group col-1" for="title"><b>등록일자</b></label>
									<input type="date" class="form-group col-2" name="regDate"> -->
								</div>
			
								<div class="form-group">
									<label for="content">내 용</label>
									<textarea class="form-control" name="bmkContent" id="content" rows="30">${bmk.bmkContent }</textarea>
								</div>
							</form>
						</div><!--end card-footer  -->
			
			
			        </div>
		    </div>
    <!-- 공지사항 본문끝 -->


	</div>
</section>
<script>
window.onload = function() {
	//$('#content').summernote({placeholder:'내용을 입력하세요.',height:200});
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
}

function modify_go(){
	
	var form = document.modifyForm;
	if(form.bmkTitle.value==""){
		alertI("제목은 필수입니다.");
		return;
	}
	if(form.bmkContent.value==""){
		alertI("내용은 필수입니다.");
		return;
	}
	form.submit();
}
</script>
</body>