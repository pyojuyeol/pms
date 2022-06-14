<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

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
                    <h1>${prjNm }</h1>
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
                        <li class="breadcrumb-item">공지사항</li>
                        <li class="breadcrumb-item active">등 록</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
<div class="content">
    <div class="col-12">
        <div class="card">
            <!-- /.card-header -->
            <div class="card-body table-responsive p-0">

				
		<!-- Main content -->
		<div class="card card-outline card-info">
			<div class="card-header">
				<div class ="card-tools">
					<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">저 장</button>
					<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="location.href='list.do'" >취 소</button>
				</div>
			</div><!--end card-header  -->
			<div class="card-body pad">
						<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
							<input type="hidden" id="prjNo" name="prjNo" value="${prjNo}">
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 입력하세요 (*필수)">
							</div>
							<div class="form-group">
								<label for="register">작성자</label> 
								<input type="text" id="userNm" readonly
									name="userNm" class="form-control" value="${loginUser.userNm }">
								<input type="hidden" id="register" readonly
									name="register" class="form-control" value="${loginUser.userId }">
							</div>
							<div class="form-group">								
								<div class="card card-outline ">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										<button class="btn btn-xs btn-primary ml-2"
										onclick="addFile_go();"	type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="15"
									placeholder="1000자 내외로 작성하세요."></textarea>
							</div>
						</form>
<!--				</div>end card-body  -->
<!--				<div class="card-footer" style="display:none"> -->
			
			</div><!--end card-footer  -->
		</div><!-- end card -->

	</div><!-- content-wrapper -->

			</div>
        </div>
    </div>
    <!-- 공지사항 본문끝 -->

<script>
window.addEventListener('DOMContentLoaded', detailProjectNotice);

	function detailProjectNotice() {
		
		summernote_go($('#content'),'<%=request.getContextPath()%>'); 
		
	}
	
	
</script>    

<script>

	var dataNum=0;
	function addFile_go(){
		//alert('add file btn');
		
		if($('input[name="uploadFile"]').length >=5){
			alertC("파일추가는 5개까지만 가능합니다.");
			return;
		}
		
		var div=$('<div>').addClass("inputRow").attr("data-no",dataNum);
		var input=$('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
	
		div.append(input).append("<button onclick='remove_go("+dataNum+");' style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");  
		
		$('.fileInput').append(div);
		
		dataNum++;
	}
	
	function remove_go(dataNum){
		$('div[data-no="'+dataNum+'"]').remove();
	}
	
	function regist_go(){
		//alert("regist btn click");
		
		var files = $('input[name="uploadFile"]');
		for(var file of files){
			console.log(file.name+" : "+file.value);
			if(file.value==""){
				alertC("파일을 선택하세요.");
				file.focus();
				file.click();
				return;
			}
		}	
		
		if($("input[name='title']").val()==""){ //form.title.value
			alertC("제목은 필수입니다.");
			$("input[name='title']").focus();
			return;
		}
		if($("#content").val()==""){ //form.title.value
			alertC("내용은 필수입니다.");
			$("#content").focus();
			return;
		}
		
		$("form[role='form']").submit()
	
		
	}
</script>
 
</body>