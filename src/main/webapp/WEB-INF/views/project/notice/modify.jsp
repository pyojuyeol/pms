<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <li class="breadcrumb-item active">수 정</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
<div class="content">
            <!-- /.card-header -->

				
		<!-- Main content -->
		<div class="card card-outline card-info">
			<div class="card-header">
				<div class ="card-tools">
					<button type="button" class="btn btn-primary" id="registBtn" onclick="modify_submit();">저 장</button>
					<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="location.href='detail.do?ntcNo=${notice.ntcNo}'">취 소</button>
				</div>
			</div><!--end card-header  -->
			<div class="card-body pad">
						<form enctype="multipart/form-data" role="form" method="post" action="modify.do" name="modifyForm">
							<input type="hidden" name="attachNo" value="${notice.attachNo }"/>
							<input type="hidden" name="ntcNo" value="${notice.ntcNo }" />
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control"value="${notice.title }">
							</div>
							<div class="form-group">
								<label for="register">작성자</label> 
								<input type="hidden" id="register"
									name="register" class="form-control" value="${notice.register }" readonly>
								<input type="text" id="userNm"
									name="userNm" class="form-control" value="${notice.userNm }" readonly>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-info">
									<div class="card-header">
										<h3 style="display:inline;line-height:40px;">첨부파일 : </h3>
										<button class="btn btn-primary ml-2"	onclick="addFile_go()" type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
										<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
											
											<c:forEach items="${notice.attachList }" var="attach" >
											<li class="attach-item thumb${attach.attachSeq }" file-name="${attach.nm }" target-attachSeq="${attach.attachSeq }">																			
												<div class="mailbox-attachment-info ">
													<a class="mailbox-attachment-name" name="attachedFile" attach-fileName="${attach.nm }" attach-attachSeq="${attach.attachSeq }" href="<%=request.getContextPath()%>/notice/getFile.do?attachSeq=${attach.attachSeq }"  >													
														<i class="fas fa-paperclip"></i>
														${attach.nm }
														<button type="button" onclick="removeFile_go('thumb${attach.attachSeq}');return false;" style="border:0;outline:0;" 
																class="badge bg-red ml-2">X</button>																									
													</a>													
												</div>
											</li>	
											</c:forEach>
										</ul>
										<br/>														
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="15"></textarea>
							</div>
						</form>
<!--				</div>end card-body  -->
<!--				<div class="card-footer" style="display:none"> -->
			
			</div><!--end card-footer  -->
		</div><!-- end card -->


    </div>
    <!-- 공지사항 본문끝 -->

<script>
window.addEventListener('DOMContentLoaded', modifyProjectNotice);
	
	
	function modifyProjectNotice() {
		$('#content').val("${notice.content }");
		summernote_go($('#content'),'<%=request.getContextPath()%>'); 
		
	}
	
</script>

<script>
function removeFile_go(className){
	//alert("X btn click");
	var li = $('li.'+className);
	
	Swal.fire({
		  text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
		  icon: 'warning',
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  cancelButtonText: '취소'
		}).then((result) => {
		  /* Read more about isConfirmed, isDenied below */
		  if (result.isConfirmed) {
		li.remove();
		  }
		});
	
	var input=$('<input>').attr({"type":"hidden",
		 "name":"deleteFile",
		 "value":li.attr("target-attachSeq")
		}); 
	$('form[role="form"]').prepend(input);
}

var dataNum=0;    	
function addFile_go(){
	//alert("click add file btn");
	
	var attachedFile=$('a[name="attachedFile"]').length; //기존파일
	var inputFile=$('input[name="uploadFile"]').length;	 //추가된 파일
	var attachCount=attachedFile+inputFile; //기존파일 + 추가된파일 개수
	
	if(attachCount >=5){
		alertE("파일추가는 5개까지만 가능합니다.");
		return;
	}
	
	var div=$('<div>').addClass("inputRow").attr("data-no",dataNum);
	var input=$('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
	
	div.append(input).append("<button onclick='remove_go("+dataNum+");' style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");    		   		
	$('.fileInput').append(div);
	
	dataNum++;
	
}
function remove_go(dataNum) {
   $('div[data-no="' + dataNum + '"]').remove();
}
//submit =============================================
function modify_submit(){
	//alert("modify btn click");
	
	var form = $('form[name="modifyForm"]');
	//제목 유효성확인
	if($("input[name='title']").val()==""){
		alertE("제목은 필수입니다.");
		$("input[name='title']").focus();
		return;
	}
	if($("#content").val()==""){
		alertE("내용은 필수입니다.");
		$("#content").focus();
		return;
	}
	
	//파일 첨부확인
	var files = $('input[name="uploadFile"]');
	for(var file of files){
		console.log(file.name+" : "+file.value);
		if(file.value==""){
			alertE("파일을 선택하세요.");
			file.focus();
			return false;
		}
	}	
	
	form.submit();
}
</script>

</body>