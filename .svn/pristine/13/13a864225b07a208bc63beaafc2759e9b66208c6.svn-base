<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="historyList" value="${dataMap.historyList }" /> 
<c:set var="cri" value="${pageMaker.cri }" />

<head>

<style>
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/history/history.css" />

</head>
<title>PROSPEC'S</title>

<body>


    <!-- Content Wrapper. Contains page content -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                	<h1>${prjNm}</h1>
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
                        <li class="breadcrumb-item active">히스토리</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-header ">
			<div id="keyword" class="card-tools my-2" > <!-- 분류&검색 -->
			  <div class="input-group row" style="top:5px">
	            <select class="form-control col-md-2 float-left d-none" name="perPageNum" id="perPageNum" onchange="list_go(1);">
			  		<option value="10" ${cri.perPageNum==10 ? 'selected':'' } >정렬개수</option>
			  		<option value="20" ${cri.perPageNum==20 ? 'selected':'' }>20개씩</option>
			  		<option value="30" ${cri.perPageNum==30 ? 'selected':'' }>30개씩</option>
			  		<option value="50" ${cri.perPageNum==50 ? 'selected':'' }>50개씩</option>
			  		
			  	</select>		
				<select class="form-control col-md-2 float-left" name="category" id="category" onchange="list_go(1);">
					<option value="all" ${cri.category eq 'all' ? 'selected':'' }>분 류</option>
					<option value="n" ${cri.category eq 'n' ? 'selected':'' }>공지사항</option>
					<option value="w" ${cri.category eq 'w' ? 'selected':'' }>일 감</option>
					<option value="i" ${cri.category eq 'i' ? 'selected':'' }>이 슈</option>
					<option value="s" ${cri.category eq 's' ? 'selected':'' }>일 정</option>
					<option value="p" ${cri.category eq 'p' ? 'selected':'' }>회의록</option>
				</select>	
				
				<!-- keyword -->
<!-- 				<div class="form-control col-md-3"> -->
					<span class="input-group-text"> <i class="far fa-calendar-alt"></i> </span>
					<input type="text" class="form-control float-right" id="rangeDt" name="rangeDt" value="${cri.rangeDt }">
<!-- 				</div> -->
				<select class="form-control col-md-2" name="searchType" id="searchType">
					<option value="tc" ${cri.searchType eq 'tc' ? 'selected':'' }>검 색</option>
					<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
					<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
				</select>	
<!-- 				<div class="col-md-3 form-control"> -->
					<input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }"  />
					 <span class="input-group-append">
						<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
							<i class="fa fa-search"></i>
						</button>
					</span> <!-- end : search bar -->
<!-- 				</div> -->
			  </div>
			</div> <!-- 분류&검색 -->
		</div>
		<div class="card-body">
<!-- 		<section class="content"> -->
			<div class="row">

			  <c:if test="${!empty historyList }">
				<div class="timeline">
				  <div class="entry">
					      <c:set var="historyDate" value="0"></c:set>
					  <c:forEach items="${historyList }" var="history" varStatus="status">
					  	<div class="historyForm">
					  	
						    <div class="title">
							      <fmt:formatDate var="historyDate1" value="${history.regDt }" pattern="yyyy-MM-dd"/>
						      <c:if test="${historyDate ne historyDate1}">
							      <h3><fmt:formatDate value="${history.regDt }" pattern="yyyy-MM-dd"/></h3>
							      <fmt:formatDate var="historyDate" value="${history.regDt }" pattern="yyyy-MM-dd"/>
						      </c:if>
						    </div>
						    
						    <div class="body" style="margin-left:50px;">
						        <fmt:formatDate var="date" value="${history.regDt }" pattern="yyyy-MM-dd HH:mm:ss"/>
						        <span id="time"> <fmt:formatDate value="${history.regDt }" pattern="HH:mm:ss"/></span>
						        <span style="margin-left:20px;"><a href="<%=request.getContextPath() %>${history.url}">${history.title }</a></span>
						        <p>${history.content }</p>
						    </div>
						    
					    </div>
					  </c:forEach>
						<div id="forPrint"></div>
				  </div>
				</div> <!-- timeline -->
			  </c:if>
			  <c:if test="${empty historyList }"> 
				  <h6 class="col-12" style="text-align: center;">해당 내용이 없습니다.</h6> 
		      </c:if> 

		  	<button id="top" class="form-control d-none" type="button" onclick="goTop()">맨 위로 ↑</button>
			</div>
<!-- 		</section> -->
			<div class="card-footer">
					<nav aria-label="pds list Navigation">
						<ul class="pagination justify-content-center m-0">
							<%@ include file="/WEB-INF/views/common/historypagination.jsp" %>	
						</ul>
					</nav>
			</div>
		</div>
	</div>
</section>
<script>
window.addEventListener('DOMContentLoaded', historyList);

function historyList() {
	
	
	$("#top").css({"position" : "fixed"
				  ,"width"  : "130px"
				  ,"right"  : ($(".layout-top-nav").width() - $("div.wrapper").width() - 150)
				  ,"bottom" : "20px"});
	
	
	moment.locale('ko'); // 언어를 한국어로 설정함
    $('#rangeDt').daterangepicker({
        singleDatePicker: false,
        locale :{ 
	        format: 'YYYYMMDD',
	        applyLabel: "적용",
	        cancelLabel: "닫기",
	        separator: ' ~ ',
	     	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	   		daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
        }
    
    });
	
    Handlebars.registerHelper({
		"prettifyDate":function(timeValue){ //Handlbars에 날짜출력함수 등록
//	 		var dateObj=new Date(timeValue);
//	 		var year=dateObj.getFullYear();
//	 		var month=dateObj.getMonth()+1;
//	 		var date=dateObj.getDate();
			return new Date(timeValue).format("yyyy-MM-dd");
		},
		"codeKey":function(num){
			if(num == "1") return '회사 일정';
			if(num == "2") return '개인 일정';
			if(num == "3") return '프로젝트 일정';
		},
		"color":function(num){
			if(num == "1") return '#52915e';
			if(num == "2") return '#8fc7cb';
			if(num == "3") return '#eec44b';
		}
		
	});
	
	
}
</script>
<script>
	function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
		winleft = (screen.width - WinWidth) / 2;
		wintop = (screen.height - WinHeight) / 2;
		var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
								+"height="+ WinHeight +", top="+ wintop +", left=" 
								+ winleft +", resizable=yes, status=yes"  );
		win.focus() ; 
	}
	

	
	function callList(page,category,searchType,keyword) {
		 
		$("#top").removeClass("d-none");
		
		 numJson = {};
		 numJson.num = page; 
		 numJson.category = category; 
		 numJson.searchType = searchType; 
		 numJson.keyword = keyword; 
		 
		 $.ajax({
		        url        :   "<%=request.getContextPath()%>/project/history/callList",
		        type        :   "post",
		        contentType :   "application/json; charset=UTF-8",
		        dataType    :   "json",
		         data       :   JSON.stringify(numJson),
		        success     :   function(data){
				        printData(data,$("#forPrint"),$("#scroll-template"));
	  	    	},
		        error       :   function(request, status, error){
		        	alertE("서버에 문제가 있습니다.");
		        }
		});
		
	}
	
</script>


<script>

function goTop(){
	$('html').scrollTop(0);
	// scrollTop 메서드에 0 을 넣어서 실행하면 끝 !!
	// 간혹 이 소스가 동작하지 않는다면
	// $('html, body') 로 해보세요~
}


    setTimeout(function(){
        // 구글의 자랑질 자신들의 엔진이 넘 빨라서 브라우져가 알지도 못할정도
        window.scrollTo(0,0);
    },10)
    // 이것도 거의 이런식으로 밖에 안 쓰니까 소스가지고 있다가 복붙
    var v_tuning = 100; // 이 값으로 바닥에서 떨어진 포인트 상대적 지정
    var v_number = 1000;
    var i=2;
    var v_wrapper = document.querySelector("#forPrint");
    var f_scroll = function() {
        var scrollTop = document.documentElement.scrollTop;
        var clientHeight = document.documentElement.clientHeight;
        var scrollHeight = document.documentElement.scrollHeight;
        // 스크롤바가 바닥을 치면 scrollTop + clientHeight = scrollHeight

        if((scrollTop + clientHeight) >= (scrollHeight - v_tuning)) {
            // 실질적으로 사용할 땐 여기에 AJAX가 와야 함!
            var link = document.location.href;
            if(link.indexOf("?") != -1) {
	            var category = link.split("category=")[1].split("&")[0];
	            var searchType = link.split("searchType=")[1].split("&")[0];
	            var keyword = link.split("keyword=")[1].split("&")[0];
            }
            callList(i,category,searchType,keyword);
            i++;
            console.log(i);
        }

    }
    window.addEventListener("scroll",f_scroll);
    
    
    function printData(list,target,templateObject){
    	var template=Handlebars.compile(templateObject.html());
    	var html = template(list);	
//     	target.html("");
    	target.append(html);
    }
    
</script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="scroll-template" >
{{#each .}}
	<div class="historyForm">
	<div class="title">
	   {{#if regDtStr}}<h3>{{regDtStr}}</h3>{{/if}}
    </div>
    
    <div class="body" style="margin-left:50px;">
        <span id="time">{{regDtTime}}</span>
        <span style="margin-left:20px;"><a href="<%=request.getContextPath() %>{{url}}">{{title }}</a></span>
        <p>{{content }}</p>
    </div>
	</div>
{{/each}}
</script>

</body>