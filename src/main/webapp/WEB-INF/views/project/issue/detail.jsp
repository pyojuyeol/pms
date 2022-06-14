<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>PROSPEC'S | Issue</title>

<body>

<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
	 				<h1>${prjNm}</h1> 
				</div>
				<div class="col-sm-6 d-none d-sm-block">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
						<li class="breadcrumb-item"><a onclick="location.href='list.do'">이슈</a></li>
						<li class="breadcrumb-item active">상세</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<div class="card card-secondary card-outline">
		<div class="card-body">

	      <!-- Content Wrapper. Contains page content -->
          <div>
          	<div class="ml-3">
          		일감: <a href="<%=request.getContextPath() %>/project/work/detail.do?workNo=${issue.workNo }"><span>${workTitle}</span></a>
          	</div>
			<div class="card-body">
				<div id="originTitleDiv" class="d-flex justify-content-between">
					<h5 class="d-flex align-items-center" id="originTitle">
						<c:if test="${issue.gradeCode eq 'l' }">				
							<span id="gradeCode" class="bg-success px-3 py-2 mr-2" style="font-size:15px">낮음</span>
						</c:if>
						<c:if test="${issue.gradeCode eq 'u' }">				
							<span id="gradeCode" class="bg-info px-3 py-2 mr-2" style="font-size:15px">보통</span>
						</c:if>
						<c:if test="${issue.gradeCode eq 'h' }">				
							<span id="gradeCode" class="bg-warning px-3 py-2 mr-2" style="font-size:15px">높음</span>
						</c:if>
						<c:if test="${issue.gradeCode eq 'e' }">				
							<span id="gradeCode" class="bg-danger px-3 py-2 mr-2" style="font-size:15px">긴급</span>
						</c:if>
						<b>${issue.title }</b>    
					</h5>
					<div>
						<c:if test="${issue.sttCode eq 'open' }" >
							<div class="float-right">
								<i class="fa-solid fa-lock-open mr-3" id="onOff" style="color:#6c757d;font-size:17px"></i>
								<c:if test="${issue.issueRegister eq loginUser.userId}">
									<button type="button" id="originTitleBtn" class="btn btn-info" onclick="modifyDivOriToModi()">
										<i class="fa-solid fa-pen-to-square"></i>
									</button>
								</c:if>
							</div>
						</c:if>
					  	<c:if test="${issue.sttCode eq 'close' }" >
							<i class="fa-solid fa-lock mr-3" id="onOff"></i>
							<c:if test="${issue.issueRegister eq loginUser.userId}">
								<button type="button" id="originTitleBtn" class="btn btn-info"
										onclick="modifyDivOriToModi()">
									<i class="fa-solid fa-pen-to-square"></i>
								</button>
							</c:if>
						</c:if>
					</div>
				</div>
				
				<div id="modifyTitleDiv" class="d-none">
					<select name="gradeCode" id="modifygradeCode" class="custom-select rounded-0 col-1 mr-2 mb-1" style="display:inline-block;">
						<option id="modifygradeCode" value="l">낮음</option>
						<option id="modifygradeCode" value="u">보통</option>
						<option id="modifygradeCode" value="h">높음</option>
						<option id="modifygradeCode" value="e">긴급</option>
					</select>
					<input class="form-control rounded-0 col-10 mr-4" name="title" id="modifyTitle" style="display:inline-block;" type="text" value="${issue.title }">    
					<button type="button" id="modifyTitleBtn" class="btn btn-info mb-1"
							onclick="modifyDivModiToOri(${issue.issueNo},${issue.prjNo})">
						<i class="fa-solid fa-pen-to-square"></i>
					</button>
				</div>
	
			</div>
		  </div>
		  
			<hr/>
			<br/>
	         
	         <div class="row" >
	            <div class="col-md-12">
	               <div class="timeline">

                      <!-- 본문 오리지날 --> 
                      <div id="originContent" class="">
                        <img class="fas fa-comments bg-yellow manPicture" data-id="${issue.issueRegister }" style="display:block;width:30px;height:30px;margin:0 auto;"/>
                        <div class="timeline-item card-warning card-outline">
                           <span class="time">
                               <i class="fas fa-clock"></i>
                               <fmt:formatDate value="${issue.regDt}" pattern="yyyy-MM-dd HH:mm"/> 
                               <!-- <span class="float-right mr-2"> -->
                               <span class="btn-group">
                                   <c:if test="${issue.issueRegister eq loginUser.userId}">
                                       <button type="button" id="originTitleBtn" class="btn btn-block btn-info btn-xs ml-1"
                                               onclick="originContentDiv()">
                                           <i class="fa-solid fa-pen-to-square"></i>
                                       </button>
                                   </c:if>
                               </span>
                           </span>
                           <div class="timeline-header">
                              <b>${issue.userNm} </b> 님이 이슈를 등록했습니다.
                           </div>
                           <div id="originContent" class="">
                               <div id="originContent" class="timeline-body">
                                   <div class="ml-3 mt-3 mb-3" style="display:inline-block">${issue.content}</div>
                               </div>
                           </div>
                       </div>
                     </div>
                      <!-- 본문 오리지날 -->

                      <!-- 수정본문 --> 
	                  <div id="modifyContentDiv" class="d-none" >
	                     <img class="fas fa-comments bg-yellow manPicture" data-id="${issue.issueRegister }" style="display:block;width:30px;height:30px;margin:0 auto;"/>
	                     <div class="timeline-item card-warning card-outline ">
	                        <span class="time">
	                        	<i class="fas fa-clock"></i>
	                        	<fmt:formatDate value="${issue.regDt}" pattern="yyyy-MM-dd HH:mm"/> 
		                        <span class="btn-group">
		                        	<c:if test="${issue.issueRegister eq loginUser.userId}">
										<button type="button" id="originTitleBtn" class="btn btn-block btn-info btn-xs ml-1"
												onclick="modifyContentDiv(${issue.issueNo},${issue.prjNo})">
											<i class="fa-solid fa-pen-to-square"></i>
										</button>
									</c:if>
		                        </span>
	                        </span>
	                        <h3 class="timeline-header">
	                           <b>${issue.userNm}</b> 님이 이슈를 등록했습니다.
	                        </h3>
		                	<div class="timeline-body" id="modifyContentDiv" style="vertical-align:middel;">
		                        <div id="" class="timeline-body">
		                        	<textarea name="content" id="modifyContent" class="form-control" rows="3">${issue.content}</textarea>
		                        </div>
	                        </div>
                		  </div>
	                  </div>
                     <!-- 수정본문 -->
	                     

<!-- ============================================================================================================= -->	        
             	<c:forEach varStatus="i" var="issueReply" items="${issueReplyList}">
	                  <div id="originReply${i.count}" class="">
	                     <img class="fas fa-comments bg-yellow manPicture" data-id="${issueReply.register }" style="display:block;width:30px;height:30px;margin:0 auto;"/>
	                     <div class="timeline-item card-outline">
	                        <span class="time">
	                        	<i class="fas fa-clock"></i>
	                        	<fmt:formatDate value="${issueReply.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/> 
		                        <span class="btn-group ml-1">
		                        	<c:if test="${issueReply.register eq loginUser.userId}">
										<button type="button" id="originTitleBtn" class="btn btn-block btn-danger btn-xs"
												onclick="deleteReply(${issueReply.replySeq});">
											<i class="fa-solid fa-x"></i>
										</button>
										<button type="button" id="originTitleBtn" class="btn btn-block btn-info btn-xs mt-0"
												onclick="originReplyDiv(${i.count});">
											<i class="fa-solid fa-pen-to-square"></i>
										</button>
									</c:if>
		                        </span>
	                        </span>
	                        <h3 class="timeline-header">
	                           <b>${issueReply.userNm} </b> 님이 코멘트를 등록했습니다.
	                        </h3>
	                        			                        
		                	<div>
		                        <div id="originReplyTxT" class="timeline-body mt-3">
		                        	<div class="ml-3" style="display:inline-block">${issueReply.content}</div>
		                        </div>
	                        </div>
	                     </div> 
					  </div>     

	                  <div id="modifyReply${i.count}" class="d-none">
	                     <img class="fas fa-comments bg-yellow manPicture" data-id="${issueReply.register }" style="display:block;width:30px;height:30px;margin:0 auto;"/>
	                     <div class="timeline-item card-outline">
	                        <span class="time">
	                        	<i class="fas fa-clock"></i>
	                        	<fmt:formatDate value="${issueReply.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/> 
		                        <span class="btn-group ml-1">
		                        	<c:if test="${issueReply.register eq loginUser.userId}">
										<button type="button" id="originTitleBtn" class="btn btn-block btn-info btn-xs"
											onclick="modifyReplyDiv(${i.count},${issueReply.replySeq});">
										<i class="fa-solid fa-pen-to-square"></i>
									</button>
									</c:if>
		                        </span>
	                        </span>
	                        <h3 class="timeline-header">
	                           <b>${issueReply.userNm} </b> 님이 코멘트를 등록했습니다.
	                        </h3>
	                        			                        
		                	<div class="timeline-body">
		                        <div id="" class="form-group">
		                        	<textarea name="content" id="modifyContent${i.count}" class="form-control" rows="3">${issueReply.content}</textarea>
		                        </div>
	                        </div>
	                     </div> 
					  </div>     
             	</c:forEach>
	                     
<!-- ============================================================================================================= -->	                     
				<div></div>
	                  <div><!-- 코멘트 작성 시작 -->
	                     <img class="fas fa-comments bg-yellow manPicture" data-id="${loginUser.userId }" style="display:block;width:30px;height:30px;margin:0 auto;"/>
	                     <div class="timeline-item card-outline">
                           <button class="btn btn-primary btn-sm float-right mt-1 mr-3 mb-1" onclick="registReply(${issue.replyNo});"> 등 록 </button>
	                        <h3 class="timeline-header">
	                           	<b>코멘트 작성</b>
	                        </h3>
	                        <div class="timeline-body">
								<div class="form-group">
									<textarea class="form-control" name="replyContent" id="replyContent" rows="15"
										placeholder="내용을 작성하세요."></textarea>
		                        </div>
	                     </div>
	                  </div>
	            	</div><!-- 코멘트 작성 끝 -->
	               </div><!-- .timeline end -->
	                  </div>
	            </div>
	         </div>

		</div>
</section>


<script>

window.addEventListener('DOMContentLoaded', onloadMemberList);

function onloadMemberList() {
 	MemberPictureThumb('<%=request.getContextPath()%>');
}

window.onload = function() {
	$('#replyContent').summernote({placeholder:'내용을 입력하세요.',height:150});		
	$('#modifyContent').summernote();
<%-- 	summernote_go($('#replyContent'),'<%=request.getContextPath()%>');  --%>
<%-- 	summernote_go($('#modifyContent'),'<%=request.getContextPath()%>');  --%>
}

function modifyDivOriToModi(){
	$('#modifyTitleDiv').attr('class','');
	$('#originTitleDiv').attr('class','d-none');
}

function modifyDivModiToOri(issueNo,prjNo){
	
	// console.log(issueNo);
	// console.log(prjNo);
	
	
	$('#originTitleDiv').attr('class','');
	$('#modifyTitleDiv').attr('class','d-none');
	var title = $('#modifyTitle').val();
	var gradeCode = $('#modifygradeCode').val();
	
	//alert(gradeCode);
	//alert(title);
	
	var data = {
			"title":title,
			"gradeCode":gradeCode,
			"issueNo":issueNo,
			"prjNo":prjNo
	}
	
	$.ajax({
<%-- 		url:"<%=request.getContextPath()%>/project/issue/modifyTitle/"+issueNo, --%>
		url:"<%=request.getContextPath()%>/project/issue/modifyTitle",
		type:"PUT",
		data:JSON.stringify(data),
		contentType:"application/json",
		headers:{			
			"X-HTTP-Method-Override":"PUT"
		},
		success:function(result){
			Swal.fire({
				  text: '이슈 제목이 수정되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then	((result)=>{
					if(result.isConfirmed) {
						location.reload();
					}
				})
		},
		error:function(error){
			Swal.fire({
				  text: '이슈 제목이 수정에 실패했습니다.',
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				});
			 AjaxErrorSecurityRedirectHandler(error.status);		
		}
	});
}


function originContentDiv(){
	$('#originContent').attr('class','d-none');
	$('#modifyContentDiv').attr('class','');
}

function modifyContentDiv(issueNo,prjNo){
	$('#originContent').attr('class','');
	$('#modifyContentDiv').attr('class','d-none');
	
	var content = $('#modifyContent').val();
	// console.log(content);
	
	var data = {
			"content":content,
			"issueNo":issueNo,
			"prjNo":prjNo
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/project/issue/modifyContent",
		type:"PUT",
		data:JSON.stringify(data),
		contentType:"application/json",
		headers:{			
			"X-HTTP-Method-Override":"PUT"
		},
		success:function(result){
			Swal.fire({
				  text: '이슈 본문이 수정되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then	((result)=>{
					if(result.isConfirmed) {
						location.reload();
					}
				})
		},
		error:function(error){
			alert('이슈 본문 수정에 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);	
		}
	});
}

function registReply(replyNo){
	
	var content = $('#replyContent').val();
	var register = "${loginUser.userId}";
	
	var data = {
			"replyNo":replyNo,
			"content":content,
			"register":register
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/project/issue/reply/regist",
		type:"PUT",
		data:JSON.stringify(data),
		contentType:"application/json",
		headers:{			
			"X-HTTP-Method-Override":"PUT"
		},
		success:function(result){
			Swal.fire({
				  text: '코멘트가 정상적으로 등록되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then	((result)=>{
					if(result.isConfirmed) {
						location.reload();
					}
				})
		},
		error:function(error){
			alert('코멘트 등록에 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);	
		}
	});
	
}

/* ===================================================================== */

function originReplyDiv(i){
	$('#originReply'+i).attr('class','d-none');
	$('#modifyReply'+i).attr('class','');
	$('#modifyContent'+i).summernote();	
}

function modifyReplyDiv(i,replySeq){
	// alert("잘 눌림");
	$('#originReply'+i).attr('class','');
	$('#modifyReply'+i).attr('class','d-none');
	
	var content = $('#modifyContent'+i).val();
	
	var data = {
			"content":content,
			"replySeq":replySeq
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/project/issue/reply/modify",
		type:"PUT",
		data:JSON.stringify(data),
		contentType:"application/json",
		headers:{			
			"X-HTTP-Method-Override":"PUT"
		},
		success:function(result){
			Swal.fire({
				  text: '코멘트가 수정되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then	((result)=>{
					if(result.isConfirmed) {
						location.reload();
					}
				})
		},
		error:function(error){
			alert('코멘트 수정에 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);	
		}
	});
}

function deleteReply(replySeq){
	var data = {
			replySeq:replySeq
			};
	
	// alert("삭제"+replySeq);
	 Swal.fire({
		  text: '코멘트를 삭제하시겠습니까?',
		  icon: 'warning',
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  cancelButtonText: '취소',
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:"<%=request.getContextPath()%>/project/issue/reply/remove?replySeq="+replySeq,
					type:"GET",
					dataType:'text',
					headers:{			
						"X-HTTP-Method-Override":"GET"
					},
					success:function(result){
						Swal.fire({
							  text: '코멘트가 삭제되었습니다.',
							  icon: 'success',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							}).then	((result)=>{
								if(result.isConfirmed) {
									location.reload();
								}
							})
					},
					error: function (request, status, error){ 
						Swal.fire({
							  text: '선택 이슈의 북마크가 변경에 실패했습니다.' + error,
							  icon: 'error',
							  confirmButtonColor: '#007bff',
							  confirmButtonText: '확인'
							});
						 AjaxErrorSecurityRedirectHandler(error.status);	
					}
					
				});
			} 
		});	
}

</script>
</body>