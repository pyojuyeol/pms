<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 캘린더 리스트 불러오기 -->
<c:set var="prjUserList" value="${dataMap.prjUserList }"/>

<!-- 일감 리스트 불러오기 -->
<c:set var="myWorkList" value="${myDataMap.myWorkList }"/>
<c:set var="pageMaker" value="${myDataMap.pageMaker }"/>
<c:set var="cri" value="${myDataMap.pageMaker.cri }"/>

<title>PROSPEC'S | 마이페이지</title>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/main.css" />
</head>
<body> 

<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3" style="margin-top: 36px;"><!-- 개인정보수정-->
				<div class="card card-primary card-outline" id="profile">
					<div class="card-body box-profile">
						<div class="text-center position-relative">
							<span class="img-circle manPicture" data-id="${user.userId }" style="display:block;width:150px;height:150px;margin:0 auto;"></span>
							<%-- <img class="profile-user-img img-fluid img-circle"
								src="<%=request.getContextPath()%>/resources/images/member/iu8.jpg"
								alt="User profile picture" style="width: 150px"> --%>
							<h3 class="profile-username text-center  mt-4 mb-1">${user.userNm }</h3>
							<p class="text-muted text-center">${user.position }</p>
						</div>
						<ul class="list-group list-group-unbordered mb-3">
							<li class="list-group-item"><label for="inputName"
								class="col-form-label">부서</label> <a
								class="col-form-label float-right">${user.dept }</a></li>
							<li class="list-group-item"><label for="inputName"
								class="col-form-label">전화번호</label> <a
								class="col-form-label float-right">${user.hp }</a></li>
							<li class="list-group-item"><label for="inputName"
								class="col-form-label">Email</label> <a
								class="col-form-label float-right">${user.email }</a></li>
						</ul>
						<button type="button" onclick="modifyForm_go();"
							class="btn btn-primary btn-block">
							<b>프로필 수정</b>
						</button>
					</div>
				</div>
				<form style="display: none;" name="modify_profile"
						action="modify.do" method="post" enctype="multipart/form-data">
					<div class="card card-primary card-outline">
						<div class="card-body box-profile">
							<div class="text-center">
								<div class="position-relative" >
									<span class="img-circle manPicture" data-id="${user.userId }" id="pictureView" style="display:block;width:150px;height:150px;margin:0 auto;">
									</span>
									<div class="position-absolute btn btn-default btn-sm"
										style="top: 80%; left: 60%;" onclick="input_file_click();">
										<i class="fa-solid fa-pen"></i> 수정
 										<input id="inputFileName" class="form-control" type="text" name="tempPicture" value="${user.picSavedNm }" hidden=""/> 
										<input id="picture" class="form-control" type="hidden" name="uploadPicture" />
									</div>
									<%-- <img class="profile-user-img img-fluid img-circle"
										src="<%=request.getContextPath()%>/resources/images/member/iu8.jpg"
										alt="User profile picture" style="width: 150px"> --%>
									
									<input type=file id="inputFile" onchange="changePicture_go();" name="picture" style="display:none">
								</div>
								<h3 class="profile-username text-center mt-4 mb-1">${user.userNm }</h3>
								<p class="text-muted text-center">${user.position }</p>
							</div>
							<ul class="list-group list-group-unbordered mb-3">
								<li class="list-group-item"><label for="inputName"
									class="col-form-label">부서</label> <a
									class="col-form-label float-right">${user.dept }</a></li>
								<li class="list-group-item"><label for="inputName"
									class="col-form-label">전화번호</label> <input type="tel"
									class="form-control form-control-sm col-form-label float-right col-sm-8"
									name="hp" value="${user.hp }"></li>
								<li class="list-group-item"><label for="inputName"
									class="col-form-label">Email</label> <input type="email"
									class="form-control form-control-sm col-form-label float-right col-sm-8"
									name="email" value="${user.email }"></li>
								<li class="list-group-item">
									<button type="button" onclick="password_modifyForm();"
										class="btn btn-primary btn-block">
										<b>비밀번호 변경하기</b>
									</button>
								</li>
							</ul>
							<div class="float-right">
								<button type="button" onclick="modify_go();" 
									class="btn btn-success">
									<b>저장</b>
								</button>
								<button type="button" onclick="modify_cancle();" 
									class="btn btn-default">
									<b>취소</b>
								</button>
							</div>
						</div>
					</div>
				</form>

				<div class="card card-primary" id="modify_password"
					style="display: none;">
					<div class="card-header">
						<h3 class="card-title">비밀번호 변경하기</h3>
					</div>
					<div class="card-body">
					  <form name="modify_password"
						action="modifyPw.do" method="post">
						<strong>현재 비밀번호</strong> <input type="password" name=currentPassword
							class="form-control mt-1" placeholder="현재 비밀번호를 입력하세요.">
						<hr>
						<strong>변경할 비밀번호</strong> <input type="password" name="newPassword1"
							class="form-control mt-1" placeholder="변경할 비밀번호를 입력하세요.">
						<hr>
						<strong>변경할 비밀번호 확인</strong> <input type="password" name="newPassword2"
							class="form-control mt-1" placeholder="변경할 비밀번호를 한번 더 입력하세요.">
					  </form>
						<div class="float-right">
							<button type="button" onclick="modifyPw_go();"	class="btn btn-success mt-3">
								<b>저장</b>
							</button>
							<button type="button" onclick="initialize_password();"	class="btn btn-default mt-3">
								<b>취소321</b>
							</button>
						</div>
					</div>
				</div>
			</div><!-- 개인정보수정-->
			<div class="col-md-9">
				<div class="" style="padding-top: 0px;padding-left: 5px;padding-right: 0px;padding-bottom: 5px;">
		    		<div class="d-flex justify-content-end">
						<div class="btn-group mr-2">
							<button type="button" class="btn btn-md btn-outline-secondary btn-flat"
								onclick="location.href='dash.do'">내 일정</button>
							<button type="button" class="btn btn-md btn-primary btn-flat"
								onclick="location.href='myworkDash.do'">내 일감</button>
						</div>
					</div>	
					<div class="row card card-primary card-outline" style="width: 930px;">
					    <div class="col-12">
					            <div class="card-body card-primary" style="padding-top: 0px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
					                <!-- <div class="tab-content" id="custom-tabs-one-tabContent"> -->
						 <!-- 일감 시작 --><div class="tab-pane fade active show" id="custom-tabs-one-home" role="tabpanel" aria-labelledby="custom-tabs-one-home-tab">
											<section class="content">
												<div>
													<div class="card-header with-border">
														<div id="keyword" class="card-tools" style="width: 650px;">
															<div class="input-group row" style="left:10px">
																<!-- sort num -->
																<select class="form-control col-md-2" name="perPageNum" id="perPageNum" onchange="list_go(1);">
																	<option value="10" >정렬개수</option>
																	<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
																	<option value="30" ${cri.perPageNum == 30 ? 'selected':''}>30개씩</option>
																	<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
																</select>
																
																<!-- search bar -->
																<select class="form-control col-md-2" name="sttCode" id="sttCode" onchange="list_go(1);">
																	<option value="">상태</option>
																	<c:forEach items="${workSttList}" var="workStt">
																		<option value="${workStt.code}" ${param.sttCode eq workStt.code ? 'selected' : ''}>${workStt.nm }</option>
																	</c:forEach>
																</select>
																<select class="form-control col-md-2" name="ipcrCode" id="ipcrCode" onchange="list_go(1);">
																	<option value="">우선순위</option>
																	<c:forEach items="${ipcrList}" var="ipcr">
																		<option value="${ipcr.code}" ${param.ipcrCode eq ipcr.code ? 'selected' : ''}>${ipcr.nm }</option>
																	</c:forEach>
																</select>
																<select class="form-control col-md-2" name="searchType" id="searchType">
																		<option value="t" ${param.searchType eq 't' ? 'selected' : ''}>제목</option>
																	<option value="c" ${param.searchType eq 'c' ? 'selected' : ''}>내용</option>
																	<option value="uno" ${param.searchType eq 'uno' ? 'selected' : ''}>상위일감</option>
																</select>
											
																<!-- keyword -->
																<input class="form-control col-md-4" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }" />
																<span class="input-group-append">
																	<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
																		<i class="fa fa-fw fa-search"></i>
																	</button>
																</span> <!-- end : search bar -->
															</div>
														</div>
													</div>
													<div class="card-body minH" style="text-align: center;padding-left: 0px;padding-right: 5px;padding-top: 0px;">
														<div class="row">
															<div class="col-sm-12">
																<table class="table table-bordered table-hover text-nowrap">
																	<thead>
																		<tr >
																			<th style="width: 5%">#</th>
																			<th style="width: 7%">일감번호</th>
																			<th style="width: 7%">상태</th>
																			<th style="width: 7%">우선순위</th>
																			<th>제목</th>
											<!-- 								<th style="width: 7%">담당자</th> -->
																			<!-- <th style="width: 9%">시작일자</th> -->
																			<th style="width: 9%">종료일자</th>
																			<th style="width: 12%">진척도</th>
																			<th style="width: 7%">상위일감</th>
																			<th>프로젝트명</th>
																		</tr>
																	</thead>
																	
																	<tbody>
																		
																		<c:if test="${empty myWorkList}">
																			<tr>
													     						<td colspan="9" class="text-center">
													     							내 일감이 존재하지 않습니다.
													     						</td>
													     					</tr>
																		</c:if>
																		
																		<c:if test="${!empty myWorkList}">
																			<c:forEach items="${myWorkList }" var="myWork" varStatus="status">
																			
																			<jsp:useBean id="now" class="java.util.Date" />
																			<fmt:formatDate value="${myWork.endDt}" pattern="yyyy-MM-dd" var="endDate" />
																			<fmt:parseDate value="${endDate }" pattern="yyyy-MM-dd" var="endDateT"/>
																			<fmt:parseNumber value="${now.time / (1000*60*60*24) }" var="todayTime" integerOnly="true" />
																			<fmt:parseNumber value="${endDateT.time / (1000*60*60*24) }" var="endDateTime" integerOnly="true" />
																			
																			<tr onclick="location.href='<%=request.getContextPath()%>/project/work/detail.do?workNo=${myWork.workNo }';" style="cursor: pointer;"
																				<c:if test="${endDateTime - todayTime <= 7 and endDateTime - todayTime > 3 and myWork.sttCode != 'c'}"> class="table-warning"</c:if>
																				<c:if test="${endDateTime - todayTime <= 3 and myWork.sttCode != 'c'}"> class="table-danger"</c:if>
																			>
																				<!-- 목록 카운트 -->
																				<td>${status.count }</td>
																				<!-- 일감번호 -->
																				<td>#${myWork.workNo }</td>
																				<!-- 상태 -->
																				<td style="padding:5px; margin:0;">
																					<c:choose>
																						<c:when test="${myWork.sttCode eq 'r'}"><h5><span class="badge badge-pill badge-info">요청</span></h5></c:when>
																						<c:when test="${myWork.sttCode eq 'p'}"><h5><span class="badge badge-pill badge-success">진행</span></h5></c:when>
																						<c:when test="${myWork.sttCode eq 'f'}"><h5><span class="badge badge-pill badge-danger">피드백</span></h5></c:when>
																						<c:when test="${myWork.sttCode eq 'c'}"><h5><span class="badge badge-pill badge-primary">완료</span></h5></c:when>
																						<c:when test="${myWork.sttCode eq 'h'}"><h5><span class="badge badge-pill badge-secondary">보류</span></h5></c:when>
																					</c:choose>
																				</td>
																				<!-- 우선순위 -->
																				<td>
																					<c:choose>
																						<c:when test="${myWork.ipcrCode eq 'l'}"><i class="fa-solid fa-arrow-down"></i>&nbsp;낮음</c:when>
																						<c:when test="${myWork.ipcrCode eq 'u'}"><i class="fa-solid fa-minus" style="color:green"></i>&nbsp;보통</c:when>
																						<c:when test="${myWork.ipcrCode eq 'h'}"><i class="fa-solid fa-arrow-up" style="color:orange"></i>&nbsp;높음</c:when>
																						<c:when test="${myWork.ipcrCode eq 'e'}"><i class="fa-solid fa-land-mine-on" style="color:red"></i>&nbsp;긴급</c:when>
																					</c:choose>
																				</td>
																				<!-- 제목 -->
																				<td style="text-align: left;">${myWork.workTitle }</td>
																				<!-- 담당자명 -->
																				<%-- <td>${myWork.userNm }</td> --%>
																				<!-- 시작일자 -->
																				<%-- <td><fmt:formatDate value="${myWork.bgnDt }" pattern="yyyy-MM-dd" /></td> --%>
																				<!-- 종료일자 -->
																				<td><fmt:formatDate value="${myWork.endDt }" pattern="yyyy-MM-dd" /></td>
																				<!-- 진척도 -->
																				<td class="project_progress">
																					<div class="progress progress-sm">
																						<div class="progress-bar bg-green" role="progressbar" aria-valuenow="${myWork.workRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${myWork.workRate }%"></div>
																					</div>
																					<small> ${myWork.workRate }% Complete </small>
																				</td>
																				<td class="text-center">
																					<c:if test="${myWork.upWork != 0}"> #${myWork.upWork}</c:if>
																				</td>
																				<td style="text-align:left;">${myWork.prjNm }</td>
																			</tr>
																			
																			</c:forEach>
																		</c:if>
																		
											
																	</tbody>
																</table>
															</div> <!-- col-sm-12 -->
														</div> <!-- row -->
													</div> <!-- card-body -->
												</div>
													<div class="card-footer"> 
														<%@ include file="/WEB-INF/views/common/pagination2.jsp" %>
													</div>	
											</section>
						  <!-- 일감 끝 --></div>
					                </div>
					            </div>
					        </div>
					    </div>
					</div>
				</div>
 			</div>
<!-- 		</div>
	</div>  -->
</section>

<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type="hidden" name="perPageNum" value="" />
	
	<input type='hidden' name="sttCode" value="" />
	<input type='hidden' name="ipcrCode" value="" />
	
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>


<div class="popupLayer btn-group-vertical" style="z-index: 100; display: none;">
<!-- 	id:<span id="layerContent"></span> -->
<!-- 	<button class="btn btn-sm btn-secondary" data-id="" type="button" onclick="detail_member();" value="상세">상세</button> -->
	<button class="btn btn-sm btn-warning" data-id="" type="button" onclick="modify_sched();">내용 변경</button>
	<button class="btn btn-sm btn-danger" data-id="" type="button" onclick="delete_sched();">삭제</button>
</div>

<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/main.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/locales-all.js"></script>
    <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
	<script src="https://unpkg.com/tippy.js@6"></script>

<script>
const map = new Map;
var schedNo = "";

window.addEventListener('DOMContentLoaded', myPageProfile);

function myPageProfile() {
	MemberPictureThumb('<%=request.getContextPath()%>');
	 <c:if test="${from eq 'modify' }">
			alertN("회원정보가 수정되었습니다.");
	 </c:if>
	 <c:if test="${from eq 'modifyFail' }">
			alertE("회원정보 수정 실패.");
	 </c:if>
	 <c:if test="${from eq 'modifyPw' }">
			alertN("비밀번호가 변경 되었습니다.");
	 </c:if>
	 <c:if test="${from eq 'modifyPwFail' }">
			alertE("${message}");
	 </c:if>
	 
	 <c:if test="${from eq 'remove'}" >
		Swal.fire({
		  text: '삭제되었습니다.',
		  icon: 'success',
		  confirmButtonColor: '#007bff',
		  confirmButtonText: '확인'
		}).then((result) => {
		  if (result.isConfirmed) {
			  window.opener.location.reload();	
			  window.close();
		  }
		});

	</c:if>
	 
	var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendar.Draggable;

    var calendarEl = document.getElementById('calendar');
	 
	/* initialize the external events
    -----------------------------------------------------------------*/
   function ini_events(ele) {
     ele.each(function () {

       // create an Event Object (https://fullcalendar.io/docs/event-object)
       // it doesn't need to have a start or end
       var eventObject = {
         title: $.trim($(this).text()) // use the element's text as the event title
       }

       // store the Event Object in the DOM element so we can get to it later
       $(this).data('eventObject', eventObject)

       // make the event draggable using jQuery UI
       $(this).draggable({
         zIndex        : 1070,
         revert        : true, // will cause the event to go back to its
         revertDuration: 0  //  original position after the drag
       })

     });
   }

	start();

	/* ADDING EVENTS */
	var currColor = '#3c8dbc' //Red by default
	
	// check
	$(".check").change(function(){
		
		checkAll();
		check();
		
	}); // check end
	
	
	
	function start() {
		
		$(".check").prop("checked", true);
		checkAll();
		check();
		
	}
	
	function checkAll() {
		var total = $("input[name=check]").length;
		var checked = $("input[name=check]:checked").length;
		
		$("#all").click(function(){
			if($("#all").is(":checked") ==false) {
				$(".check").prop("checked", false);
			}
			if($("#all").is(":checked")){
				$(".check").prop("checked", true);
			}
		});
		
		if(total != checked) {
			$("#all").prop("checked", false);
		} else {
			$("#all").prop("checked", true); 
			$(".check").prop("checked", true);
		}
	}
	
	function check() {
		
        if($("#personal").is(":checked")){
        	map.set($("#personal").val(),$("#personal").val());
        }else{
            map.delete($("#personal").val());
        }
        
        if($("#company").is(":checked")){
        	map.set($("#company").val(),$("#company").val());
        }else{
            map.delete($("#company").val());
        }
        
        <c:if test="${!empty prjUserList }" >	
			<c:forEach items="${prjUserList }" var="list">
        	if($("#"+${list.prjNo}).is(":checked")){
            	map.set($("#"+${list.prjNo}).val(),$("#"+${list.prjNo}).val());
            }else{
                map.delete($("#"+${list.prjNo}).val());
            }
		 	</c:forEach>
		</c:if>
		const mapJson = {};
		const iterator = map.values();
		for(i=1; i<=map.size; i++) {
			var value = iterator.next().value;
			mapJson[value]=value;
		} 
				
		       	
       $.ajax({
	         url        :   "<%=request.getContextPath()%>/myPage/check",
	        type        :   "post",
	        contentType :   "application/json; charset=UTF-8",
	        dataType    :   "json",
	         data       :   JSON.stringify(mapJson),
	        success     :   function(data){
			        		var calendar = new Calendar(calendarEl, {
					      	      headerToolbar: {
					      	        left  : 'prev,next today',
					      	        center: 'title',
					      	        right : 'dayGridMonth,timeGridWeek,timeGridDay'
					      	      },
					      	      themeSystem: 'bootstrap',
					      	      //Random default events
					      	      events: data,
						      	  eventClick: function(data) {
							    	    data.jsEvent.preventDefault(); // don't let the browser navigate
										
							    	    
							    	    if (data.event.url) {
							    	    	window.open(data.event.url , '제목 수정' , "top=210,left=570,width=600,height=350"  );
							    	        return false;
							    	    }
							      },
					      	      editable  : true,
					      	      droppable : true, // this allows things to be dropped onto the calendar !!!
					      	      drop      : function(info) {
					      	      },
					      	      eventDrop: function (info) {
					      	    	  modify(info);
							      }, // eventDrop
							      eventResize: function (info) {
							    	  modify(info);
					      		  }, // eventResize
					      		  eventDidMount: function(info) {
// 								      var tooltip = new Tooltip(info.el, {
// 								        title: info.event.extendedProps.description,
// 								        placement: 'top',
// 								        trigger: 'hover',
// 								        container: 'body'
// 								      });
					      			tippy(info.el, {
					      				title : info.event.title,
					                    content :  info.event.extendedProps.description,//이벤트 디스크립션을 툴팁으로 가져옵니다. 
					                    theme : 'yellow',
					                });
							      }
					      		  
					      		  
					      	});
				            calendar.render();
				            rclickMenu();
				          
				            
		    },
	        error       :   function(request, status, error){
	            alertE("서버에 문제가 있습니다.");
	        }
    	});
	}

	 
	 
}
</script>
<script>
	var passwords = document.querySelectorAll("input[type='password']");
	
	
	function modifyForm_go() {
		document.querySelector("#profile").style.display = "none";
		document.querySelector("form[name='modify_profile']").style.display = "block";
	}
	function modify_go() {
		var form = document.querySelector("form[name='modify_profile']")
		form.submit();
	}
	function modifyPw_go() {
		var form = document.querySelector("form[name='modify_password']")
		var input = $("input[type='password'");
		if(!input[0].value) {
			alertI("현재 비밀번호를 입력해주세요.");
			return;
		}
		if(!input[1].value || !input[2].value) {
			alertI("변경할 비밀번호를 입력해주세요.");
			return;
		}
		if(input[1].value != input[2].value) {
			alertI("변경할 비밀번호가 일치하지 않습니다.");
			return;
		}
		if(input[0].value == input[1].value) {
			alertI("현재 비밀번호와 다른 비밀번호를 입력해야 합니다.");
			return;
		}
		form.submit();
	}
/* 		var form = document.querySelector("form[name='modify_profile']")
		form.submit(); */
	function modify_cancle() {
		document.querySelector("#profile").style.display = "block";
		document.querySelector("form[name='modify_profile']").style.display = "none";
	}
	function input_file_click() {
		document.querySelector("input[type='file']").click();
	}
	function password_modifyForm() {
		var btn = document.querySelector("#modify_password");
		if(btn.style.display == "block") {
			initialize_password();
			return;
		}
		btn.style.display = "block";
	}
	function initialize_password() {
		document.querySelector("#modify_password").style.display = "none";
		for (var i = 0; i < passwords.length; i++) {
			passwords[i].value = "";
		}
	}
	
</script>
<script>
  	function changePicture_go(){
//   		alert("file change");
  		
  		var picture = $('input#inputFile')[0];
  		
  		var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
  		
  		// 이미지 확장자 jpg 확인
// 		if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
// 			alert("이미지는 jpg 형식만 가능합니다.");
// 			return;
// 		} 
		//이미지 파일 용량 체크
		if(picture.files[0].size>1024*1024*1){
			alert("사진 용량은 1MB 이하만 가능합니다.");
			return;
		};
		
		document.getElementById('inputFileName').value=picture.files[0].name;
		
		if (picture.files && picture.files[0]) {
 			var reader = new FileReader();
			 
			 reader.onload = function (e) {
		        	//이미지 미리보기	        	
		        	$('span#pictureView')
		        	.css({'background-image':'url('+e.target.result+')',
						  'background-position':'center',
						  'background-size':'cover',
						  'background-repeat':'no-repeat'
		        		});
		      }
		        
		       reader.readAsDataURL(picture.files[0]);
		}
		
		// 이미지 변경 확인
		$('input[name="uploadPicture"]').val(picture.files[0].name);
  	}
 </script>
	
<script>

	function CloseWindow() {
		window.opener.location.reload(true);		
		window.close();
	}
	
	function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
		winleft = (screen.width - WinWidth) / 2;
		wintop = (screen.height - WinHeight) / 2;
		var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
								+"height="+ WinHeight +", top="+ wintop +", left=" 
								+ winleft +", resizable=yes, status=yes"  );
		win.focus() ; 
	}
	
	function formatDate(date) {
		
		var d = new Date(date), month = '' + (d.getMonth() + 1), day = '' + d.getDate(), year = d.getFullYear();
		
		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;
	
		return [year, month, day].join('-');
	}

</script>

<script>

	function modify(info) {
			
			if(info.event._def.url == false) {
				Swal.fire({
			        text: "프로젝트 일정은 수정할 수 없습니다.",
			        icon: 'error',
			        confirmButtonColor: '#007bff',
			        confirmButtonText: '확인'
		          }).then((result) => {
	    			  if (result.isConfirmed) {
	    				  location.reload();
	    					return;
	    			  }
	      		  });
				
			} else {
			
		    	Swal.fire({
	    			  text: formatDate(info.event._instance.range.start)+" ~ "+formatDate(info.event._instance.range.end)+" 로 수정하시겠습니까?" ,
	    			  icon: 'info',
	    			  confirmButtonColor: '#007bff',
		      		  cancelButtonColor: '#dc3545',
		      		  showCancelButton: true,
		      		  confirmButtonText: '확인',
		      		  cancelButtonText: '취소'
	    			}).then((result) => {
	    			  if (result.isConfirmed) {
	    				var jsonMap = {};
	    		  	    
	    		  	    jsonMap["url"]=info.event._def.url;
	    		  	    jsonMap["title"]=info.event._def.title;
	    		  	    jsonMap["start"]=info.event._instance.range.start;
	    		  	    jsonMap["end"]=info.event._instance.range.end;
	    		  	    
	    					console.log(jsonMap);
	    		        $.ajax({
	    		   	         url        :   "<%=request.getContextPath()%>/myPage/dragModify",
	    		   	        type        :   "post",
	    		   	        contentType :   "application/json; charset=UTF-8",
	    		   	        dataType    :   "json",
	    		   	         data       :   JSON.stringify(jsonMap),
	    		   	        success     :   function(data){
								alertC("수정되었습니다.");
	    		       	    },
	    		 	        error       :   function(request, status, error){
	    		 	        	alertE("서버에 문제가 있습니다.");
	    		 	        }
	    				});
	    			  } else {
	    				location.reload();
		    	  		return;
	    			  }
	    			});
		
			}
	}
	
	function rclickMenu() {
		var areaObj = $(".fc-daygrid-event-harness");
      	var layerObj = $(".popupLayer");
      	ShowLayer(areaObj,layerObj,"rclick");
      	document.addEventListener('mousedown', function(e) {
//       		event.stopPropagation();
      	  	if ((event.button == 2) || (event.which == 3)) {
      	  		schedNo = e.target.closest(".fc-daygrid-event").getAttribute("href").split("=")[1];
      	  		
//       	  		$("#layerContent").text(""+e.target.closest("tr").getAttribute("id"));
//       	  		idValueForMenu = e.target.closest("tr").getAttribute("id")
      	  	}
      	});
      	
	}
	
	
	
	function modify_sched(){
		OpenWindow("modifyContentName.open?schedNo="+schedNo,"내용 변경","800","600");
		$(".popupLayer").hide();
	}
	
	function delete_sched(){
		
		$(".popupLayer").hide();
		confirmS("삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?","warning",confirmDelete);
		
	}
	
	function confirmDelete() {
		location.href = "remove.do?schedNo="+schedNo;
	}
	
</script>	
	

<script>
function list_go(page, url) { 
	if (!url) url = "<%=request.getContextPath()%>/myPage/myworkDash.do";
	var jobForm = $('#jobForm');
	
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	
	jobForm.find("[name='sttCode']").val($('select[name="sttCode"]').val());
	jobForm.find("[name='ipcrCode']").val($('select[name="ipcrCode"]').val());
	
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	
	jobForm.attr({
		action : url,
		method : 'get'
	}).submit();
}
</script>	
</body>
