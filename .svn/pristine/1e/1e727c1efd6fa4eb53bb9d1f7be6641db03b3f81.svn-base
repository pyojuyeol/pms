<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- 핸들바스 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>

<script  type="text/x-handlebars-template"  id="menuPush-list-template">
	{{#each .}}
		<div class="dropdown-divider"></div>
		<a href="javascript:moveToPushUrl('{{pushNo }}', '{{url }}', '{{prjNo}}')" class="dropdown-item" style="font-size:12px;">
			<div class="d-flex justify-content-between text-muted pb-1">
				<span class="font-weight-bold" style="font-size:13px;">{{fromWhere }}</span>
				<span>
					{{displayedAt regDt }}
					{{#isN readNyCode}}
						<span class="badge badge-danger" style="font-size: .5rem; font-weight: 400; padding: 2px 4px;">N</span>
  					{{/isN}}
				</span>
			</div>
			<span>{{message }}</span>
		</a>
	{{/each}}

</script>
<!-- 표주열 나의 프로젝트 추가 시작 -->
<script  type="text/x-handlebars-template"  id="myProject-template">
	{{#each .}}
		<div class="dropdown-divider"></div>
		<a href="javascript:moveToProject('{{url }}')" class="dropdown-item" style="font-size:12px;">
			<div class="d-flex justify-content-between text-muted pb-1">
				<span class="font-weight-bold" style="font-size:13px;">{{prjNm }}</span>
			</div>
		</a>
	{{/each}}

</script>
<!-- 표주열 나의 프로젝트 추가 끝 -->
<script>

	
	
</script>

<script>
	window.addEventListener('load', initMenuPush)
	
	function initMenuPush() {
		
		/* 표주열 나의 프로젝트 추가 시작*/
		myProject();
		function myProject() {
			 $.ajax({
			        url        :   "<%=request.getContextPath()%>/myProject",
			        type        :   "post",
			        contentType :   "application/json; charset=UTF-8",
			        dataType    :   "json",
			        success     :   function(data){
			        	printProject(data, $('#myProject'), $('#myProject-template'));
		  	    	},
			        error       :   function(request, status, error){
			        	alertE("서버에 문제가 있습니다.");
			        }
			});
		}
		/* 표주열 나의 프로젝트 추가 끝 */
		
		
		getMenuPushList();
		function getMenuPushList() {
			
			$.getJSON("<%=request.getContextPath() %>/push/getMenuPushList.do", function(dataMap){
				if(dataMap.nCnt){
					$("#nCnt").text(dataMap.nCnt);
					$("#nCnt").removeClass("d-none");
				}
				printData(dataMap.menuPushList, $('#menuPushList'), $('#menuPush-list-template'));
			});
		
		}
		
		// handlebars printElement (args : data Array, append target, handlebars template)
		function printData(dataArr, target, templateObject) {
			target.html("");
			
			let html = "";
				
			if(!dataArr.length){
				html = `<div class="dropdown-divider"></div>
						<div class="d-flex justify-content-center text-muted pb-1">
							<span style="font-size:12px;">
								알림 내역이 없습니다.
							</span>
						</div>`;
			} else{			
				var template = Handlebars.compile(templateObject.html());		
				html = template(dataArr);
			}
			
			target.append(html);
		}
		
		
		/* 표주열 나의 프로젝트 추가 시작*/
		function printProject(dataArr, target, templateObject) {
			target.html("");
			
			let html = "";
				
			if(!dataArr.length){
				html = `<div class="dropdown-divider"></div>
						<div class="d-flex justify-content-center text-muted pb-1">
							<span style="font-size:12px;">
								참여중인 프로젝트가 없습니다.
							</span>
						</div>`;
			} else{			
				var template = Handlebars.compile(templateObject.html());		
				html = template(dataArr);
			}
			
			target.html(html);
		}
		/* 표주열 나의 프로젝트 추가 끝*/
		
		
		Handlebars.registerHelper({
			"isN": function(readNyCode, options) {
					  if(readNyCode == 'N') {
					    return options.fn(this);
					  }
					  return options.inverse(this);
			},
			"displayedAt": function displayedAt(createdAt) {
				
				const milliSeconds = new Date().getTime() - createdAt;
				
				const seconds = milliSeconds / 1000;
				if (seconds < 60) return `방금 전`;
				
				const minutes = Math.floor(seconds / 60);
				if (minutes < 60) return minutes+"분 전";
				
				const hours = Math.floor(minutes / 60);
				if (hours < 24) return hours+"시간 전";
				
				const days = Math.floor(hours / 24);
				if (days < 7) return days+"일 전";
				
				return new Date(createdAt).format("yyyy.MM.dd");
				
				/* 핸들바스일때는 백틱이 에러가 나네..... 변수를 못가져와
				const weeks = days / 7;
				if (weeks < 5) return `${Math.floor(weeks)}주 전`;
				
				const months = days / 30
				if (months < 12) return `${Math.floor(months)}개월 전`;
				
				const years = days / 365;
				return `${Math.floor(years)}년 전`; */
				
			}
			
		});
		/* setTimeout(function refleshMenuPushList(){
			getMenuPushList();
			setTimeout(refleshMenuPushList, 1000);
		}, 1000) */
	}
</script>




<!-- 메뉴바 알림 항목 페이지 이동 -->
<script>
	function moveToPushUrl(pushNo, targetUrl, prjNo) {
		$.ajax({ 
			url: "<%=request.getContextPath()%>/push/modifyStatus.do?prjNo="+prjNo+"&pushNo="+pushNo, 
			type: "POST",
			success: function(result){
				if(result == "fail"){
					Swal.fire({
						  text: "존재하지 않는 프로젝트 입니다.",
						  icon: 'error',
						  confirmButtonColor: '#007bff',
						  confirmButtonText: '확인'
						});
				} else {					
					location.href = "<%=request.getContextPath()%>"+targetUrl;
				}
			}
		});
	}
	
	function moveToProject(url) {
		location.href = "<%=request.getContextPath()%>"+url;
	}
</script>