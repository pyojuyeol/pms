<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="replyList" value="${dataMap.pdsReplyList }" />

<title>PROSPEC'S | 자료게시판</title>

<body>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
 				<h1>자료게시판</h1> 
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item"><a href="#">자료게시판</a></li>
					<li class="breadcrumb-item active">상세보기</li>
				</ol>
			</div>
		</div>
	</div>
</section>

	<div class="content">	
		<!-- Main content -->
		<div class="card card-outline card-info">
			<div class="card-header">
				<div class ="card-tools">
					<c:if test="${loginUser.userId eq pds.register }">
						<button type="button" class="btn btn-primary" id="registBtn" onclick="submit_go('modifyForm.do','${pds.pdsNo}');">수 정</button>
						<button type="button" class="btn btn-danger ml-2" id="removeBtn" onclick="remove_go('${pds.pdsNo}')">삭 제</button>
					</c:if>
					<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="location.href='list.do'" >목 록</button>
				</div>
			</div><!--end card-header  -->
			<div class="card-header">
				<div class="form-group col-sm-12">
				<label for="title">제 목</label>
				<input type="text" class="form-control" id="title" 
					value="${pds.title }" readonly />							
				</div>	
			</div><!--end card-header  -->
			<div class="card-body">
				<div class="col-sm-12 row">
					<div class="form-group col-sm-4" >
						<label for="register">작성자</label>
						<input type="text" class="form-control" id="register" 
							 value="${pds.userNm }" readonly />
					</div>		
					
					<div class="form-group col-sm-4" >
						<label for="regDt">작성일</label>
						<input type="text" class="form-control" id="regDt" 
							value="<fmt:formatDate value="${pds.regDt }" pattern="yyyy-MM-dd" />" readonly />
					</div>	
					<div class="form-group col-sm-4" >
						<label for="viewCnt">조회수</label>
						<input type="text" class="form-control" id="viewCnt" value="${pds.viewCnt }"
							 readonly />
					</div>	
				</div>	
				<div class="form-group col-sm-12">
					<label for="content">내 용</label>
					<div >${pds.content }</div>	
				</div>
				<div class="form-group col-sm-12">
					<div class="card card-outline card-info">
						<div class="card-header">
							<h6>첨부파일</h6>
						</div>
									
						<div class="card-footer">
							<div class="row">
								<c:forEach items="${pds.attachList }" var="attach">
									<div class="col-md-4 col-sm-4 col-xs-12"  style="cursor:pointer;"
										 onclick="location.href='<%=request.getContextPath()%>/pds/getFile.do?attachSeq=${attach.attachSeq }';">
										<div class="info-box">	
										 	<span class="info-box-icon bg-yellow">
												<i class="fa fa-copy"></i>
											</span>
											<div class="info-box-content">
												<span class ="info-box-text">
													<fmt:formatDate value="${attach.regDt }" pattern="yyyy-MM-dd" />	
												</span>
												<span class ="info-box-number">${attach.nm }</span>
											</div>
										</div>
									 </div>														
								</c:forEach>
							</div>
						</div>				
					</div>
				</div>
										
			</div> <!-- card body -->
			
		</div><!-- end card -->
		<section class="content container-fluid">
	    	<!-- reply component start --> 
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					<div class="card card-info">					 -->
<!-- 						<div class="card-body"> -->
<!-- 							The time line -->
<!-- 							<div class="timeline"> -->
<!-- 								timeline time label -->
<!-- 								<div class="time-label" id="repliesDiv"> -->
<!-- 									<span class="bg-green">댓글 목록 </span>							 -->
<!-- 								</div> -->
								
								
<!-- 							</div> -->
<!-- 							<div class='text-center'> -->
<!-- 								<ul id="pagination" class="pagination justify-content-center m-0" > -->
									
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="card-footer"> -->
<!-- 							<label for="newReplyText">댓글 등록</label> -->
<!-- 							<input class="form-control" type="text"	placeholder="댓글을 입력하세요" id="newReplyText"> -->
<!-- 							<br/> -->
<%-- 							<button type="button" class="btn btn-primary" id="replyAddBtn" onclick="replyRegist_go(${pds.pdsNo});">등록</button> --%>
<!-- 						</div>				 -->
<!-- 					</div>			 -->
					
<!-- 				</div>end col-md-12 -->
<!-- 			</div>end row -->
	    </section>
	</div> <!-- content -->
    <!-- 자료게시판 본문끝 -->
    
<!-- Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" style="display:none;"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>        
      </div>
      <div class="modal-body" data-replyNo>
        <p><input type="text" id="content" class="form-control"></p>
      </div>
      <div class="modal-footer">
      	<c:if test="${loginUser.userId eq pds.register }">
	        <button type="button" class="btn btn-info" id="replyModBtn" onclick="replyModify_go();">수 정</button>
	        <button type="button" class="btn btn-danger" id="replyDelBtn" onclick="replyRemove_go();">삭 제</button>
        </c:if>
        <button type="button" class="btn btn-default" data-dismiss="modal">취 소</button>
      </div>
    </div>
  </div>
</div>


<script>
function submit_go(url,pdsNo){	
	location.href=url+"?pdsNo="+pdsNo;
}
</script>

<script>
window.addEventListener('DOMContentLoaded', detailPds);

function detailPds() {
	
	<c:if test="${from eq 'modify'}" >
		alertC("수정되었습니다.");
	</c:if>    
	<c:if test="${from eq 'remove'}" >
		alertC("삭제되었습니다.");
		location.href="list.do"
	</c:if>
	
}
</script>

<script>
function removeReply() {
	
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
			  replyRemove_go();
		  }
		});
	
}

function remove_go(pdsNo){
	 Swal.fire({
		  text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
		  icon: 'warning',
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  cancelButtonText: '취소',
		}).then((result) => {
			if (result.isConfirmed) {
	 			location.href="remove.do?pno="+pdsNo;
			} 
		  });
 };	
 
</script>

<%-- <c:if test="${from eq 'modify' }">
	<script>
		//window.onload = function(){
			// alert("자료를 정상적으로 등록했습니다.");
			Swal.fire({
				  text: '자료가 정상적으로 저장되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			}).then((result) => {
				  /* Read more about isConfirmed, isDenied below */
				  if (result.isConfirmed) {
			 		window.location.reload();
				  }
			});
		//};
	</script>
</c:if> --%>


<%@ include file="./reply_js.jsp" %> 

</body>
