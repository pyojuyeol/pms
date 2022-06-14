<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js" integrity="sha512-RNLkV3d+aLtfcpEyFG8jRbnWHxUqVZozacROI4J2F1sTaDqo1dPQYs01OMi1t1w9Y2FdbSCDSQ2ZVdAC8bzgAg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/x-handlebars-template" id="reply-list-template" >

{{#each .}}
	<div class="card-comment replyLi d-flex">
		<span class="img-circle manPicture mr-3" data-id={{register}} style="display:block; width:38px; height:38px;"></span>
		<div class="flex-grow-1" style="color: #78838e;">
	    	<span class="username">{{userNm}}
	       		<span class="text-muted float-right"> {{prettifyDate regDt}}
					<a class="btn-md px-2 {{replySeq}}-a" id="modifyReplyBtn" data-replySeq={{replySeq}}
						onclick="replyModifyModal_go('{{replySeq}}','{{content}}');"				
						style="display:{{VisibleByLoginCheck register}};"
						data-register={{register}} data-toggle="modal" data-target="#modifyModal">
						<i class="fas fa-pencil-alt" style="font-size:13px; cursor:pointer;"></i>
					</a>
				</span>
  	  		</span>{{content}}
		</div>
	</div>
{{/each}}

</script>


<script type="text/x-handlebars-template" id="reply-pagination-template" >
<li class="paginate_button page-item">
	<a href="1" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-double-left'></i>
	</a>
</li>
<li class="paginate_button page-item">
	<a href="{{#if prev}}{{prevPageNum}}{{/if}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-left'></i>
	</a>
</li>
{{#each pageNum}}
<li class="paginate_button page-item {{signActive this}} ">
	<a href="{{this}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		{{this}}
	</a>
</li>
{{/each}}

<li class="paginate_button page-item ">
	<a href="{{#if next}}{{nextPageNum}}{{/if}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-right'></i>
	</a>
</li>
<li class="paginate_button page-item">
	<a href="{{realEndPage}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-double-right'></i>
	</a>
</li>
</script>

<script>
var replyPage = 1;

window.onload = function() {
	getPage("<%=request.getContextPath()%>/replies/${work.workNo}/" + replyPage);
	
	$('ul.pagination').on('click','li a',function(event) {
//     	alert("!!!!!!!!!!!!!!!!!!!!");

		replyPage = $(this).attr("href");
		
		getPage("<%=request.getContextPath()%>/replies/${work.workNo}/" + replyPage);
		
		return false;
	});
}

function getPage(pageInfo) {
	$.getJSON(pageInfo, function(data) {
		printData(data.workReplyList, $("#repliesDiv"), $("#reply-list-template"));
		printPagination(data.pageMaker, $("ul#pagination"), $("#reply-pagination-template"))
		
		// 사진 넣기
		MemberPictureThumb('<%=request.getContextPath() %>');
		
		// 상세보기에서 하위 일감 추가 완료 후 성공 얼럿창.
		<c:if test="${from eq 'subWorkRegist' }">
			Swal.fire({
				text: '등록되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인',
			});
		</c:if>
		
	});
}

function printData(replyArr, target, templateObject) {
	var template = Handlebars.compile(templateObject.html());
	var html = template(replyArr);
	$('.replyLi').remove();
	target.after(html);
}

function printPagination(pageMaker, target, templateObject) {
	var pageNumArray = new Array(pageMaker.endPage - pageMaker.startPage + 1);
	
	for (var i = 0; i < pageMaker.endPage - pageMaker.startPage + 1; i++) {
		pageNumArray[i] = pageMaker.startPage + i
	}
	pageMaker.pageNum = pageNumArray;
	pageMaker.prevPageNum = pageMaker.startPage - 1;
	pageMaker.nextPageNum = pageMaker.endPage + 1;

	var template = Handlebars.compile(templateObject.html());
	var html = template(pageMaker);
	target.html("").html(html);
}

Handlebars.registerHelper({
	"prettifyDate" : function(timeValue) { //Handlbars에 날짜출력함수 등록
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	},
	"VisibleByLoginCheck" : function(register) {
		var result = "none";		
		if (register == "${loginUser.userId}") result = "visible";		
		return result;						  
	},
	"signActive" : function(pageNum) {
		if (pageNum == replyPage) return 'active';
	}
});
</script>

<script>
function replyRegist_go(workNo) {
// 	alert("reply regist btn");
	
	var replytext = $('#newReplyText').val();
	var workNo = $('input[name="workNo"]').val();
	
	if (!replytext) {
// 		alert("내용은 필수입니다.");
		Swal.fire({
			  text: '내용은 필수입니다.',
			  icon: 'info',
			  confirmButtonColor: '#007bff',
			  confirmButtonText: '확인'
			});
		return;
	}
	
	var data = {
		"workNo" : workNo,
		"register" : "${loginUser.userId}",
		"content" : replytext
	}
	
	$.ajax({
		url : "<%=request.getContextPath()%>/replies/regist",
		type : "post",
		data : JSON.stringify(data),	
		contentType : 'application/json',
		success : function(data) {
			Swal.fire({
				text: '댓글이 등록되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인'
			}).then((result) => {
				if (result.isConfirmed) {
					replyPage = data; //페이지이동
					getPage("<%=request.getContextPath()%>/replies/" + workNo + "/" + data); // 리스트 출력
					$('#newReplyText').val("");
				}
			});
		},
		error : function(error) {
			Swal.fire({
				  text: '댓글 등록이 실패했습니다. 에러코드' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			});
			AjaxErrorSecurityRedirectHandler(error.status);		
		}
	});
}

// 댓글 수정
function replyModifyModal_go(replySeq, content) {	
// 	alert("reply modify modal");
	$('div#modifyModal div.modal-header h4.modal-title').text(replySeq);
	$('div#modifyModal div.modal-body #content').val(content);
}

function replyModify_go() {
// 	alert("modify by modal");
	
	var replySeq = $('.modal-title').text();
	var content = $('#content').val();
	
	var sendData = {
		"replySeq" : replySeq,
		"content" : content
	}
	
	$.ajax({
		url : "<%=request.getContextPath()%>/replies/modify/" + replySeq,
		type : "put",
		data : JSON.stringify(sendData),
		contentType : "application/json",
		headers : {			
			"X-HTTP-Method-Override" : "PUT"
		},
		success : function(result) {
			Swal.fire({
				text: '댓글이 수정되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인'
			}).then((result) => {
				if (result.isConfirmed) {
					getPage("<%=request.getContextPath()%>/replies/${work.workNo}/" + replyPage);
// 					location.reload();
				}
			});
		},
		error : function(error) {
// 			alert('수정 실패했습니다.');
			Swal.fire({
				  text: '댓글 수정이 실패했습니다. 에러코드' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			});
			AjaxErrorSecurityRedirectHandler(error.status);		
		},
		complete:function() {
			$('#modifyModal').modal('hide');
		}
		
	});
}

// 댓글 삭제
function replyRemove_go() {
// 	alert("reply remove btn");
	
	var replySeq = $('.modal-title').text();
	
	$.ajax({
		url : "<%=request.getContextPath()%>/replies/remove/${work.workNo}/" + replySeq + "/" + replyPage,
		type : "put",
		headers : {			
			"X-HTTP-Method-Override" : "put"
		},
		success : function(page) {
			Swal.fire({
				text: '댓글이 삭제되었습니다.',
				icon: 'success',
				confirmButtonColor: '#007bff',
				confirmButtonText: '확인'
			}).then((result) => {
				if (result.isConfirmed) {
					replyPage = page;
					getPage("<%=request.getContextPath()%>/replies/${work.workNo}/" + page);
// 					location.reload();
				}
			});
		},
		error : function(error) {
			Swal.fire({
				  text: '에러가 발생되었습니다. 에러코드' + error,
				  icon: 'error',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			});
			AjaxErrorSecurityRedirectHandler(error.status);		
		},
		complete : function() {
			$('#modifyModal').modal('hide');
		}
	});
}
</script> 
