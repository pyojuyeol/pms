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
					<li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
					<li class="breadcrumb-item"><a href="#">책갈피</a></li>
					<li class="breadcrumb-item active">등록</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<!-- Main content -->
<section class="content">
	<div class="card card-outline card-secondary">
		<div class="card-header mr-3">
			<h3 class="card-title p-2">책갈피 등록</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-primary mr-3" id="registBtn" onclick="regist_go();">등 록</button>
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="location.href='list.do'" >취 소</button>
			</div>
		</div>
	

			<div class="content-wrapper">
			    <!-- Content Wrapper. Contains page content -->
			    <div class="col-12">
			            <!-- /.card-header -->
			
							
					<!-- Main content -->
						<div class="card-body pad">
							<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
								<div class="form-group">
			
									<label class="form-group mr-3" for="title"><b>책갈피</b></label>
									<input type="text" id="title"
										name='bmkTitle'  class="form-control col-12"  placeholder="제목을 입력하세요 (*필수)">
			
			<!-- 						<label class="form-group col-1" for="title"><b>등록일자</b></label>
									<input type="date" class="form-group col-2" name="regDate"> -->
								</div>
			
								<div class="form-group">
									<label for="content">내 용</label>
									<textarea class="form-control" name="bmkContent" id="content" rows="30"
										placeholder="내용을 작성하세요."></textarea>
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

function regist_go(){
	
	var form = document.registForm;
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