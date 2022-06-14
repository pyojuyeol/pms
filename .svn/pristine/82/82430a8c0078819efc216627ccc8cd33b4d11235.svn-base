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
<!--                     <h1>공지사항 등록</h1> -->
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item">공지사항</li>
                        <li class="breadcrumb-item active">등록</li>
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
					<button type="button" class="btn btn-outline-success mr-1" id="sampleBtn" onclick="inputSampleText(textObj);">샘 플</button>
					<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등 록</button>
					<button type="button" class="btn btn-warning ml-1" id="cancelBtn" onclick="history.go(-1);" >취 소</button>
				</div>
			</div><!--end card-header  -->
			<div class="card-body pad">
						<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 입력하세요 (*필수)">
							</div>
							<div class="form-group row">
						 	  <div class="col-md-6">
								<label for="register">작성자</label> 
								<input type="text" id="userNm" readonly
									name="userNm" class="form-control" value="${loginUser.userNm }">
								<input type="hidden" id="register" readonly
									name="register" class="form-control" value="${loginUser.userId }">
							  </div>
							  <div class="col-md-6">
								<label for="sttCode" style="font-size:0.9em;" >구 분</label>
								<select id="sttCode" name="sttCode" class="form-control" style="font-size:0.9em;">
									<option value="1">회사 소식</option>
									<option value="2">행사 일정</option>
									<option value="3">기타</option>
								</select>
							  </div>
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
let textObj = {
		 "title":"2022년 05월 인사발령 안내"
		,"content":"첨부 파일 확인 바랍니다."
};

window.addEventListener('DOMContentLoaded', registNotice);

function registNotice() {
	
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
		
}
</script>

<script>
var dataNum=0;

function addFile_go(){
	//alert('add file btn');
	
	if($('input[name="uploadFile"]').length >=5){
		alertE("파일추가는 5개까지만 가능합니다.");
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
			alertE("파일을 선택하세요.");
			file.focus();
			file.click();
			return;
		}
	}	
	
	if($("input[name='title']").val()==""){ //form.title.value
		alertE("제목은 필수입니다.");
		$("input[name='title']").focus();
		return;
	}
	
	if($('#content').val()==""){ //form.title.value
		alertE("내용은 필수입니다.");
		$('#content').focus();
		return;
	}
	
	$("form[role='form']").submit()

	
}	
</script>
 
</body>