var searchData = {};
// 페이지 네이션에 필요한 객체 선언


function list_go(page, url) {
  
  var target = document.querySelector("#custom-tabs-four-tabContent");
  var pagenationTarget = document.querySelector(".card-footer");
  
  var fileLogTemplate = document.querySelector("#fileLog-list-template");
  var connectLogTemplate = document.querySelector("#connectLog-list-template");
  var dbcnLogTemplate = document.querySelector("#dbcnLog-list-template");
  var systemLogTemplate = document.querySelector("#systemLog-list-template");
  
  var logType = document.querySelector(".nav-link.active").getAttribute("aria-controls");
  
  if (!url) {
    url = logType
  } else {
    logType = url;
  }
  
  if (page) {
    searchData.page = page;
  }
  
  searchData.searchType = $("#searchType").val();

  searchData.keyword = document.querySelector("input[name='keyword']").value;
  
  searchData.logType = logType;
  
  var rangeDt = document.querySelector(".rangeDt").value
  
  searchData.startDt = rangeDt.split(" ~ ")[0]; 
  searchData.endDt = rangeDt.split(" ~ ")[1];
  
  console.log(JSON.stringify(searchData));
  
  
  $.ajax({
    url : logType,
    type : "POST",
    data : JSON.stringify(searchData),
    dataType : "json",
    contentType : "application/json; charset=utf-8",
    success : function(data) {
      let params = getParameter();
      
      if (logType == "fileLog") {
        printData(data, target, fileLogTemplate);
      }
      if (logType == "connectLog") {
        printData(data, target, connectLogTemplate);
      }
      if (logType == "dbcnLog") {
        printData(data, target, dbcnLogTemplate);
      }
      if (logType == "systemLog") {
        printData(data, target, systemLogTemplate);
      }
      printPagenation(pagenationTarget, data);
      
      datePickerCall();
      
    },
    error : function (error) {
      AjaxErrorSecurityRedirectHandler(error.status);
    }
  });
}
      
function datePickerCall() {
  var now = new Date();
  now = now.setMonth(now.getMonth() - 3);
  
  var minDate = new Date(now).format("yyyy/MM/dd");
  var today = new Date();
  
  today.setMonth(today.getMonth() -1);
  var startDate = new Date(today).format("yyy/MM/dd");
  
  today.setMonth(today.getMonth() + 1);
  
  moment.locale('ko'); // 언어를 한국어로 설정함
    $('.rangeDt').daterangepicker({
      "startDate" : startDate,
      "minDate": minDate,
      "maxDate": today.format("yyyy/MM/dd"),
        locale :{ 
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          separator: ' ~ ',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"]
        }
    });
}
  
  // tab 클릭시 화면 출력 ajax
  var contentSection = document.querySelector(".content");
  
  contentSection.addEventListener("click", function(e) {
    var logType = e.target.getAttribute("aria-controls");
    
    if (e.target.tagName == "A" && e.target.classList.contains("nav-link")) {
      list_go(1, logType);
    }
  });
  
  // pagenation 화면 출력 함수
  function printPagenation(pagenationTarget, data) {
    let pageMaker = data.pageMaker;
    
    let elements = "";
      elements += '<nav aria-label="Navigation">';
      elements += '	<ul class="pagination justify-content-center m-0">';
      elements += '		<li class="page-item">';
      elements += '			<a class="page-link" href="javascript:list_go(1);">';
      elements += '				<i class="fas fa-angle-double-left"></i>';
      elements += '			</a>';
      elements += '		</li>';
      elements += '		<li class="page-item">';
      
      if (pageMaker.prev) {
        elements += '			<a class="page-link" href="javascript:list_go(' + (pageMaker.startPage -1) + ');">';
      } else {
        elements += '			<a class="page-link" href="javascript:list_go(' + pageMaker.cri.page + ');">';
      }
      
      elements += '				<i class="fas fa-angle-left"></i>';
      elements += '			</a>';
      elements += '		</li>';
      
    for (let i = pageMaker.startPage; i <= pageMaker.endPage; i++) {
      if (pageMaker.cri.page == i) {
        elements += '		<li class="page-item active">';
      } else {
        elements += '		<li class="page-item">';
      }
      elements += '			<a class="page-link" href="javascript:list_go('+ i + ');" >' + i + '</a>';
      elements += '		</li>';
    }
    
      elements += '		<li class="page-item">';
      if (pageMaker.next) {
        elements += '			<a class="page-link" href="javascript:list_go(' + (pageMaker.endPage + 1) + ');">';
      } else {
        elements += '			<a class="page-link" href="javascript:list_go(' + pageMaker.cri.page + ');">';
      }
      
      elements += '				<i class="fas fa-angle-right" ></i>';
      elements += '			</a>';
      elements += '		</li>';
      elements += '		<li class="page-item">';
      elements += '			<a class="page-link" href="javascript:list_go(' + pageMaker.realEndPage + ');">';
      elements += '				<i class="fas fa-angle-double-right"></i>';
      elements += '			</a>';
      elements += '		</li>';
      elements += '	</ul>';
      elements += '</nav>';
              
    pagenationTarget.innerHTML = elements;
  }
  
  // 파라미터 가져오기
  function getParameter() {
    let searchUrl = window.location.search;
    let params = new URLSearchParams(searchUrl);
    
    return params;
  }
  
  function maintainPage() {
    let params = getParameter();
    
    if (params) {
      let logType = params.get("logType");
      
      let nav_links = document.querySelectorAll(".nav-link");
      
      for (let i = 0; i < nav_links.length; i++) {
        if (nav_links[i].getAttribute("aria-controls") != logType) {
          nav_links[i].classList.remove("active");
          nav_links[i].setAttribute("aria-selected", "false");
        } else {
          nav_links[i].classList.add("active");
        }
      }
    }
  }

// Handlebar printData function
function printData(cloudArr, target, templateObject) {
  var template=Handlebars.compile(templateObject.innerHTML);
  var html = template(cloudArr);
  target.innerHTML = "";
  target.innerHTML = html;
}

// Ajax에러 화면 Swal
function AjaxErrorSecurityRedirectHandler(status) {
  if (status == "302") {
    Swal.fire({
      text: '세션이 만료되었습니다. \n 로그인 하세요.',
      icon: 'error',
      confirmButtonColor: '#007bff',
      confirmButtonText: '확인'
    }).then((result) => {
      if (result.isConfirmed) {
        location.reload();
      }
      });;
  } else if (status == "403") {
    Swal.fire({
      text: '권한이 유효하지 않습니다.',
      icon: 'error',
      confirmButtonColor: '#007bff',
      confirmButtonText: '확인'
    }).then((result) => {
      if (result.isConfirmed) {
        history.go(-1);
      }
      });
  } else {
    Swal.fire({
      text: '시스템 장애로 실행이 불가합니다.',
      icon: 'error',
      confirmButtonColor: '#007bff',
      confirmButtonText: '확인'
    }).then((result) => {
      if (result.isConfirmed) {
        history.go(-1);
      }
      });;
  }
}