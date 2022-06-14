<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"  ></script>
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi" >
	<div class="user-block">
<%-- 		<img src="<%=request.getContextPath()%>/member/getPicture.do?id={{replyer}}" class="img-circle img-bordered-sm"/> --%>
    </div>	
	<div class="timeline-item" >
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regDt}}
	 		<a class="btn btn-primary btn-xs {{replySeq}}-a" id="modifyReplyBtn" data-replySeq={{replySeq}}
				onclick="replyModifyModal_go('{{replySeq}}');"				
				style="display:{{VisibleByLoginCheck register}};"
	    		data-register={{register}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{replySeq}}</strong>{{register}}</h3>
  		<div class="timeline-body" id="{{replySeq}}-content">{{content}} </div>
	</div>
</div>

{{/each}}
</script>

<script type="text/x-handlebars-template"  id="reply-pagination-template" >
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
var replyPage=1;

window.onload=function(){
	getPage("<%=request.getContextPath()%>/notice/replies/${notice.replyNo}/"+replyPage);
	
	$('ul.pagination').on('click','li a',function(event){		
		//alert($(this).attr("href"));
		replyPage=$(this).attr("href");
		
		getPage("<%=request.getContextPath()%>/notice/replies/${notice.replyNo}/"+replyPage);
		
		return false;
	});
	
}

function getPage(pageInfo){	 
	$.getJSON(pageInfo,function(data){	
		printData(data.replyList,$('#repliesDiv'),$('#reply-list-template'));
		printPagination(data.pageMaker,$('ul#pagination'),$('#reply-pagination-template'));
	});
}

function printData(replyArr,target,templateObject){
	var template=Handlebars.compile(templateObject.html());
	var html = template(replyArr);	
	$('.replyLi').remove();
	target.after(html);
}
function printPagination(pageMaker,target,templateObject){
	var pageNumArray = new Array(pageMaker.endPage-pageMaker.startPage+1);
	for(var i=0;i<pageMaker.endPage-pageMaker.startPage+1;i++){
		pageNumArray[i]=pageMaker.startPage+i;
	}	
	pageMaker.pageNum=pageNumArray;  
	pageMaker.prevPageNum=pageMaker.startPage-1;
	pageMaker.nextPageNum=pageMaker.endPage+1;
	
	var template=Handlebars.compile(templateObject.html());	
	var html = template(pageMaker);	
	target.html("").html(html);
}

Handlebars.registerHelper({
	"prettifyDate":function(timeValue){ //Handlbars에 날짜출력함수 등록
		var dateObj=new Date(timeValue);
		var year=dateObj.getFullYear();
		var month=dateObj.getMonth()+1;
		var date=dateObj.getDate();
		return year+"/"+month+"/"+date;
	},
	"VisibleByLoginCheck":function(register){
		var result="none";		
		if(register == "${loginUser.userId}") result="visible";		
		return result;						  
	},
	"signActive":function(pageNum){
		if(pageNum == replyPage) return 'active';
	}
});

</script>

<script>
function replyRegist_go(ntcNo){
	//alert("reply regist btn");	
	
	var content=$('#newReplyText').val();
	var replyNo=$('input[name="replyNo"]').val();
	
	if(!content){
		alertC("내용은 필수입니다.");
		return;
	}
	
	var data={
			"replyNo":replyNo,
			"register":"${loginUser.userId}",
			"content":content,
			"sttCode":ntcNo
	}
	
// 	var ntcNo ={ 
// 		"ntcNo":ntcNo
// 	};
	
	$.ajax({
		url:"<%=request.getContextPath()%>/notice/replies",
		type:"post",
		data:JSON.stringify(data),	
		contentType:'application/json',
		success:function(data){

			Swal.fire({
				  text: '댓글이 등록되었습니다.\n마지막페이지로 이동합니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then((result) => {
					  if (result.isConfirmed) {
						replyPage=data; //페이지이동
						getPage("<%=request.getContextPath()%>/notice/replies/"+replyNo+"/"+data); //리스트 출력
						$('#newReplyText').val("");
						location.reload();
					  }
		  		  });
			
		},
		error:function(error){
			//alert('댓글이 등록을 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);		
		}
		
	});
}

//댓글 수정
function replyModifyModal_go(replySeq){	
	//alert("reply modify modal");
	$('div#modifyModal div.modal-body #content').val($('div#'+replySeq+'-content').text());
	$('div#modifyModal div.modal-header h4.modal-title').text(replySeq);
}

function replyModify_go(){
	//alert("modify by modal");
	
	var replySeq=$('.modal-title').text();
	var content=$('#content').val();
	
	var sendData={
			"replySeq":replySeq,
			"content":content
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/notice/replies/"+replySeq,
		type:"PUT",
		data:JSON.stringify(sendData),
		contentType:"application/json",
		headers:{			
			"X-HTTP-Method-Override":"PUT"
		},
		success:function(result){
			
			Swal.fire({
				  text: '수정되었습니다',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then((result) => {
					  if (result.isConfirmed) {
						  getPage("<%=request.getContextPath()%>/notice/replies/${notice.replyNo}/"+replyPage);
						  location.reload();
					  }
		  		  });
			
		},
		error:function(error){
			//alert('수정 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);		
		},
		complete:function(){
			$('#modifyModal').modal('hide');
		}
		
	});
}


function replyRemove_go(){
	//alert("reply remove btn");
	
	var replySeq=$('.modal-title').text();
	
	$.ajax({
		url:"<%=request.getContextPath()%>/notice/replies/${notice.replyNo}/"+ replySeq +"/"+replyPage,
		type:"DELETE",
		headers:{			
			"X-HTTP-Method-Override":"DELETE"
		},
		success:function(page){
			
			Swal.fire({
				  text: '삭제되었습니다',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
				}).then((result) => {
					  if (result.isConfirmed) {
						  getPage("<%=request.getContextPath()%>/notice/replies/${notice.replyNo}/"+page);
						  replyPage=page;
						  location.reload();
					  }
		  		  });
			
		},
		error:function(error){
			//alert('삭제 실패했습니다.');
			 AjaxErrorSecurityRedirectHandler(error.status);		
		},
		complete:function(){
			$('#modifyModal').modal('hide');
		}
	});
}


</script> 





