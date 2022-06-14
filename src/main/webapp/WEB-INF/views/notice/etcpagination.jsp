<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<li class="page-item">
			<a class="page-link" href="javascript:etc_list_go(1);">
				<i class="fas fa-angle-double-left"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:etc_list_go('${etcpageMaker.prev ? etcpageMaker.startPage-1 : etcpageMaker.cri.etcPage}');">
				<i class="fas fa-angle-left"></i>
			</a>
		</li>
		<c:forEach var="pageNum" begin="${etcpageMaker.startPage }" end="${etcpageMaker.endPage }" >
				
			<li class="page-item ${etcpageMaker.cri.etcPage == pageNum?'active':''}">
				<a class="page-link" href="javascript:etc_list_go('${pageNum}');" >${pageNum }</a>
			</li>
			
		</c:forEach>
		<li class="page-item">
			<a class="page-link" href="javascript:etc_list_go('${etcpageMaker.next ? etcpageMaker.endPage+1 :etcpageMaker.cri.etcPage}');">
				<i class="fas fa-angle-right" ></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:etc_list_go('${etcpageMaker.realEndPage}');">
				<i class="fas fa-angle-double-right"></i>
			</a>
		</li>	
	</ul>
</nav>


<form id="etcJobForm">	
	<input type='hidden' name="etcPage" value="" />
	<input type='hidden' name="etcPerPageNum" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
	<input type='hidden' name="pageType" value="" />
</form>

<script>
	function etc_list_go(page,url){
		
 		if(!url) url="list.do";
		var etcJobForm=$('#etcJobForm');
		
		etcJobForm.find("[name='etcPage']").val(page);
		etcJobForm.find("[name='etcPerPageNum']").val($('select[name="perPageNum"]').val());
		etcJobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		etcJobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
		etcJobForm.find("[name='pageType']").val("etc");
	
		etcJobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
</script>