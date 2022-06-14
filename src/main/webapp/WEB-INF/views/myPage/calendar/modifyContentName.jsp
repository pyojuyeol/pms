<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="prjUserList" value="${dataMap.prjUserList }"/>
<head>

<style></style>

</head>
<title>PROSPEC'S</title>

<body>
			    <section class="content container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-outline card-info">
								<div class="card-header">
									<h3 class="card-title p-1">내용 변경</h3>
									<div class ="card-tools">
										<button type="button" class="btn btn-primary" id="registBtn" onclick="modify_submit();">저 장</button>
										<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="CloseWindow()" >취 소</button>
									</div>
								</div><!--end card-header  -->
								<div class="card-body pad">
									<form role="form" method="post" action="modifyTitle.open" name="modifyForm">
											<input type="hidden" id="register" readonly
												name="register" class="form-control" value="${loginUser.userId }">
											<input type=hidden id="schedNo" readonly
											name="schedNo" class="form-control" value="${schedNo }">
										<div class="form-group">
											<label for="content">내용</label>
											<textarea class="textarea" name="content" id="content" rows="20"
												cols="90" placeholder="1000자 내외로 작성하세요.">${schedule.content }</textarea>
										</div>			
									</form>
								</div><!--end card-body  -->
							</div><!-- end card -->				
						</div><!-- end col-md-12 -->
					</div><!-- end row -->
			    </section>
		    <!-- /.content -->
 


  
<script>
window.addEventListener('DOMContentLoaded', myPageCalendarModify);

function myPageCalendarModify() {
	
	summernote_go($('#content'),'<%=request.getContextPath()%>'); 
}	
</script>  

   
<script>
function modify_submit(){
	//alert("modify btn click");
	
  var form = document.modifyForm;
  	if(form.content.value==""){
		alertE("내용을 입력해 주세요.");
		return;
	}
	
	
	form.submit();
}
  
function CloseWindow() {
	window.opener.location.reload(true);		
	window.close();
}
 
</script>
    
    
 </body>
 
 
 
 
 
 
 
 
 
 
 