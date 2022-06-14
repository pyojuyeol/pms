<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<head>

<style></style>

</head>
<title>PROSPEC'S</title>

<body>
				<section class="content-header">
				  	<div class="container-fluid">
				  		<div class="row mb-2">
				  			<div class="col-sm-6">
				  				<h1>일정수정</h1>  				
				  			</div>
				  			<div class="col-sm-6">
				  				<ol class="breadcrumb float-sm-right">
							        <li class="breadcrumb-item">
							        	<a href="list.do">
								        	일정관리
								        </a>
							        </li>
							        <li class="breadcrumb-item active">
							        	수정
							        </li>		        
					    	  </ol>
				  			</div>
				  		</div>
				  	</div>
				</section>
				 
			  <!-- Main content -->
			    <section class="content container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-outline card-info">
								<div class="card-header">
									<h3 class="card-title p-1"></h3>
									<div class ="card-tools">
										<button type="button" class="btn btn-primary" id="registBtn" onclick="modify_submit();">저 장</button>
										<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="history.go(-1);" >취 소</button>
									</div>
								</div><!--end card-header  -->
								<div class="card-body pad">
									<form role="form" method="post" action="modify" name="modifyForm">
											<input type="hidden" id="register" readonly
												name="register" class="form-control" value="${loginUser.userId }">
											<input type=hidden id="schedNo" readonly
											name="schedNo" class="form-control" value="${schedule.schedNo }">
										<div class="form-group">
											<label for="title">일정 제목</label> 
											<input type="text" id="title"
												name='title' class="form-control" placeholder="제목을 쓰세요" value="${schedule.title }">
										</div>			
										<div class="form-group row">
										  <div class="form-group col-md-6">
												<label>일정 기한</label>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text"> <i class="far fa-calendar-alt"></i> </span>
													</div>
													<input type="text" class="form-control float-right" id="rangeDt" name="rangeDt" value="rangeDt">
												</div>
											</div>
											<div class="col-6">
												<label for="keyCode" style="font-size:0.9em;" >일정 구분</label>
												<select id="keyCode" name="keyCode" class="form-control" style="font-size:0.9em;">
													<option value="1" ${schedule.keyCode eq '1' ? 'selected':'' }>회사 일정</option>
													<option value="2" ${schedule.keyCode eq '2' ? 'selected':'' }>개인 일정</option>
													<option value="3" ${schedule.keyCode eq '3' ? 'selected':'' }>프로젝트 일정</option>
												</select>
											  </div>
										</div>
										
										<div class="form-group row">
										  <div id="ipcrCode" class="col-12">
											<label for="ipcrCode" style="font-size:0.9em;" >개인일정 공유하기</label>
											<select name="ipcrCode" class="form-control" style="font-size:0.9em;">
												<option value="1" ${schedule.ipcrCode eq '1' ? 'selected':'' }>개인일정 미공유</option>
												<option value="2" ${schedule.ipcrCode eq '2' ? 'selected':'' }>개인일정 공유</option>
											</select>
										  </div>
										</div>
														
										<div class="form-group row">
										  <div class="col-12">
											<label for="content">일정 내용</label>
											<textarea class="textarea" name="content" id="content" rows="20"
												cols="90" placeholder="1000자 내외로 작성하세요.">${schedule.content }</textarea>
										  </div>
										</div>
									</form>
								</div><!--end card-body  -->
							</div><!-- end card -->				
						</div><!-- end col-md-12 -->
					</div><!-- end row -->
			    </section>
		    <!-- /.content -->
 


  
<script>
window.addEventListener('DOMContentLoaded', projectCalendarModify);

function projectCalendarModify() {
	
	moment.locale('ko'); // 언어를 한국어로 설정함
    $('#rangeDt').daterangepicker({
        singleDatePicker: false,
        locale :{ 
	        format: 'YYYY-MM-DD',
	        applyLabel: "적용",
	        cancelLabel: "닫기",
	        separator: ' ~ ',
	     	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	   		daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
        }
    });
	
		$('#content').summernote();	
		
		if($('#keyCode').val() != 2) {
			document.getElementById("ipcrCode").classList.add('d-none')
		}
		
		$('#keyCode').change(function() {
			
			if($('#keyCode').val() != 2) {
				document.getElementById("ipcrCode").classList.remove('d-block')
				document.getElementById("ipcrCode").classList.add('d-none')
			}	
			if($('#keyCode').val() == 2) {
				document.getElementById("ipcrCode").classList.remove('d-none')
				document.getElementById("ipcrCode").classList.add('d-block')
			}	
			
		});
	}
</script>  

   
<script>

  function modify_submit(){
		//alert("modify btn click");
		
	  var form = document.modifyForm;
	    if(form.title.value==""){
			alertE("제목을 입력해 주세요.");
			return;
		}
	    if(form.rangeDt.value==""){
			alertE("일정 기한을 입력해 주세요.");
			return;
		}
		if(form.content.value==""){
			alertE("내용을 입력해 주세요.");
			return;
		}
		
		
		form.submit();
  }
</script>
    
    
 </body>
 
 
 
 
 
 
 
 
 
 
 