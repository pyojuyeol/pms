<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="prjUserList" value="${dataMap.prjUserList }"/>


<head>
	<!-- fullCalendar -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/main.css" />
</head>
<title>PROSPEC'S</title>
<body>


<section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                	<h1>${prjNm}</h1>
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">${prjNm}</a></li>
                        <li class="breadcrumb-item active">일정 관리</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">
		<div class="card-body">

			<div class="row">
				<div class="col-md-3 margin-top">

					<div class="sticky-top mb-2">
						
						<div class="card">
							<button type="submit"
								onclick="OpenWindow('registForm.open','일정 등록',800,700);"
								class="btn btn-lg btn-secondary col-12 mb-2">
								<i class="fas fa-calendar-alt fa-1.5x mr-2"></i>일정 추가
							</button>
							<div class="card-body">
								<label class="mr-2">
									<input id="all" class="m-1 check" type="checkbox" name="all" value="all">
									전체
								</label>
								<hr>
								<label class="mr-2">
									<input id="company" class="m-1 check" type="checkbox" name="check" value="company">
									회사 일정
								</label> 
								<i class="fas fa-square" style="color:#52915e"></i><br>
								<label class="mr-2">
									<input id="personal" class="m-1 check" type="checkbox" name="check" value="personal">
									나의 개인 일정
								</label>
								<i class="fas fa-square " style="color:#8fc7cb"></i>
								<label class="mr-2">
									<input id="orderPersonal" class="m-1 check" type="checkbox" name="check" value="orderPersonal">
									다른 사람의 개인 일정
								</label>
								<i class="fas fa-square " style="color:#e26b48"></i>
								<hr>
								<label class="mr-2">
									<input id="project" class="m-1 check" type="checkbox" name="check" value="project">
									프로젝트 일정
								</label>
								<i class="fas fa-square " style="color:#eec44b"></i>
							</div>
						</div>
					</div>
					
					
				</div>
					
					
					
					<!-- calendar -->
					<div id="calendar" class="col-md-9"></div>
					
					
					
					
			</div>
		</div>
	</div>


</section>
 <div class="popupLayer btn-group-vertical" style="z-index: 100; display: none;">
<!-- 	id:<span id="layerContent"></span> -->
<!-- 	<button class="btn btn-sm btn-secondary" data-id="" type="button" onclick="detail_member();" value="상세">상세</button> -->
	<button class="btn btn-sm btn-warning" data-id="" type="button" onclick="modify_sched();">내용 변경</button>
	<button class="btn btn-sm btn-danger" data-id="" type="button" onclick="delete_sched();">삭제</button>
 </div>

    <!-- fullCalendar 2.2.5 -->
    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/main.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/fullcalendar5/locales-all.js"></script>
    <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
	<script src="https://unpkg.com/tippy.js@6"></script>

<!-- Page specific script -->
<script>
let datas = [];
const map = new Map;

window.addEventListener('DOMContentLoaded', initCalPage);

function initCalPage() {
	
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
        
        if($("#orderPersonal").is(":checked")){
        	map.set($("#orderPersonal").val(),$("#orderPersonal").val());
        }else{
            map.delete($("#orderPersonal").val());
        }
        
        if($("#company").is(":checked")){
        	map.set($("#company").val(),$("#company").val());
        }else{
            map.delete($("#company").val());
        }
        
        if($("#project").is(":checked")){
        	map.set($("#project").val(),$("#project").val());
        }else{
            map.delete($("#project").val());
        }
        
		const mapJson = {};
		const iterator = map.values();
		for(i=1; i<=map.size; i++) {
			var value = iterator.next().value;
			mapJson[value]=value;
		} 
				
		       	
       $.ajax({
	         url         :   "<%=request.getContextPath()%>/project/calendar/check",
	        type        :   "post",
	        contentType :   "application/json; charset=UTF-8",
	        dataType    :   "json",
	         data        :   JSON.stringify(mapJson),
	        success     :   function(data){
	        	console.log(data);
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
							
				    	    var loginUser = "${loginUser.userId}"
				    	    	
				    	    if(data.event.id != loginUser) {
				    	    	alertA("권한이 없습니다.","error",re);
				    	    } else {
					    	    if (data.event.url) {
					    	    	window.open(data.event.url , '제목 수정' , "top=210,left=570,width=600,height=350"  );
					    	        return false;
					    	    }
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
//					      var tooltip = new Tooltip(info.el, {
//					        title: info.event.extendedProps.description,
//					        placement: 'top',
//					        trigger: 'hover',
//					        container: 'body'
//					      });
		      			tippy(info.el, {
		                    content :  info.event.extendedProps.description,//이벤트 디스크립션을 툴팁으로 가져옵니다. 
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
	
	var loginUser = "${loginUser.userId}"
	
	if(info.event.id != loginUser) {
		Swal.fire({
	        text: "권한이 없습니다.",
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
		   	         url        :   "<%=request.getContextPath()%>/project/calendar/dragModify",
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
	
	var loginUser = "${loginUser.userId}"
	    	
	var areaObj = $(".fc-daygrid-event-harness");
  	var layerObj = $(".popupLayer");
  	ShowLayer(areaObj,layerObj,"rclick");
  	var areaObj2 = $(".content-wrapper");
    HideLayer(areaObj2,layerObj);
  	
  	document.addEventListener('mousedown', function(e) {
//   		event.stopPropagation();
  	  	if ((event.button == 2) || (event.which == 3)) {
  	  		schedNo = e.target.closest(".fc-daygrid-event").getAttribute("href").split("=")[1];
  	  		console.log(e.id);
//   	  		$("#layerContent").text(""+e.target.closest("tr").getAttribute("id"));
//   	  		idValueForMenu = e.target.closest("tr").getAttribute("id")
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

function re() {
	return;
}

</script>
</body>