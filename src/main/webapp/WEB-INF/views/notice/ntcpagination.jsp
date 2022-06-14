<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item">
			<a class="page-link" href="javascript:ntc_list_go(1);">
				<i class="fas fa-angle-double-left"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:ntc_list_go('${ntcpageMaker.prev ? ntcpageMaker.startPage-1 : ntcpageMaker.cri.ntcPage}');">
				<i class="fas fa-angle-left"></i>
			</a>
		</li>
		<c:forEach var="pageNum" begin="${ntcpageMaker.startPage }" end="${ntcpageMaker.endPage }" >
				
			<li class="page-item ${ntcpageMaker.cri.ntcPage == pageNum?'active':''}">
				<a class="page-link" href="javascript:ntc_list_go('${pageNum}');" >${pageNum }</a>
			</li>
			
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="javascript:ntc_list_go('${ntcpageMaker.next ? ntcpageMaker.endPage+1 :ntcpageMaker.cri.ntcPage}');">
				<i class="fas fa-angle-right" ></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:ntc_list_go('${ntcpageMaker.realEndPage}');">
				<i class="fas fa-angle-double-right"></i>
			</a>
		</li>	
	</ul>
</nav>


<form id="ntcJobForm">	
	<input type='hidden' name="ntcPage" value="" />
	<input type='hidden' name="ntcPerPageNum" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
	<input type='hidden' name="pageType" value="" />
</form>

<script>
	function ntc_list_go(page,url){
		
 		if(!url) url="list.do";
		var ntcJobForm=$('#ntcJobForm');
		
		ntcJobForm.find("[name='ntcPage']").val(page);
		ntcJobForm.find("[name='ntcPerPageNum']").val($('select[name="perPageNum"]').val());
		ntcJobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		ntcJobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
		ntcJobForm.find("[name='pageType']").val("ntc");
	
		ntcJobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
</script>