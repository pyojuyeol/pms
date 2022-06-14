var checkbox_flag = false;
var checkbox_btn = document.querySelector(".checkbox-toggle");
var checkbox_icon = document.querySelector("#checkbox");
var cloud_items = document.querySelectorAll(".cloud-item");
var input_labels = document.querySelectorAll(".input-label");
var card_body = document.querySelector(".card-body");

function getCloudList() {

  listData.mainCategory = $("a[name='mainCategory']").data("value");
  listData.subCategory = $("a[name='subCategory']").data("value");
  listData.cldKeyCode = $("select[name='cloudKeyCode'] option:selected").data("key");
  
  if (listData.cldKeyCode == 1) {
    listData.prjNo = $("select[name='cloudKeyCode'] option:selected").val();  
  }
  
  listData.dirNo = $(".path_item > a:last").data("dirno");
  
  $.ajax ({
    url : "list",
    data : JSON.stringify(listData),
    type : "POST",
    contentType : "application/json;charset=utf-8",
    success : function (dataMap) {
      
      if ($("div.btn_view_cover>button.selected").hasClass("list")) {
        printData(dataMap, $("#view"), $("#cloud-list-template"));
      } else {
        printData(dataMap, $("#view"), $("#cloud-grid-template"));
      }
    },
    error : function (error) {
      AjaxErrorSecurityRedirectHandler(error.status);
    }
  });
}


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
      text: '권한이 유호하지 않습니다.',
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

function printData(cloudArr, target, templateObject) {
  var template=Handlebars.compile(templateObject.html());
  var html = template(cloudArr);
  target.html("");
  target.html(html);
}

// cloudlist click 이벤트
function cdblClick(click, dblClick, el) {
  let clickedTimes = 0;
  const incrementClick = function () {
    clickedTimes++;
  };
  const reset = function () {
    clickedTimes = 0;
  };
  el.addEventListener('click', function(e) {
    incrementClick();

      setTimeout(function () {
        if (clickedTimes === 1) {
          click(e);
        } else if (clickedTimes === 2) {
          dblClick(e);
        }
        reset();
      }, 200);
  });
}


// 클릭 이벤트 / 더블클릭 이벤트
function cloudListClickEvent(e) {
  if ($(e.target).parents(".cloud-item").hasClass("cloud-item")) {
    if ($(e.target).parents(".cloud-item").hasClass("selected")) {
      displaySelect(e.target, true);
      headerBar(false);
      $(e.target).parents(".cloud-item").find(".checkbox").prop("checked", false);
    } else {
      displaySelect(e.target, false);
      headerBar(true);
      $(e.target).parents(".cloud-item").find(".checkbox").prop("checked", true);
    }

    var checkSelected = false;
    for (var i = 0; i < document.querySelectorAll(".cloud-item").length; i++) {
      if (!document.querySelectorAll(".cloud-item")[i].classList.contains("selected")) {
        checkSelected = true;
      } else {
        checkSelected = false;
        break;
      }
    }
    headerBar(checkSelected);
  }
}

function cloudListDblClickEvent(e) {
  if ($(e.target).parents(".cloud-list") && $(e.target).parents(".cloud-item").find(".folder").length > 0) {
    rollback();
    setCloudDirectoryPath(e.target);
    getCloudList();
  }
}

function setCloudDirectoryPath(element) {
  var dirNo = $(element).parents(".cloud-item").val();
  var folderNm = "";

  folderNm = $(element).parents(".cloud-item").find(".folder_name").text();

  var mk_a = document.createElement("a");
  var mk_li = document.createElement("li");

  $(mk_li).attr({
    "class" : "path_item"
  })
  $(mk_a).attr({
    "class" : "depth",
    "data-dirno" : dirNo,
    "data-dirpath" : folderNm
  });
  $(mk_a).text(folderNm)
  mk_li.append(mk_a);

  document.querySelector(".path_list").append(mk_li);
  $(mk_li).prev().addClass("addNext");
}

document.querySelector(".path_list").addEventListener("click", function(e) {
  if (e.target.classList.contains("depth")) {
    $(e.target).parents(".path_item").nextAll().remove();
    $(e.target).parents(".path_item").removeClass("addNext");
    getCloudList();
  }
});

cdblClick(cloudListClickEvent, cloudListDblClickEvent, document.querySelector("#view"));

function displaySelect(target, ifselected) {
  if (ifselected) {
    $(target).parents(".cloud-item").removeClass("selected");
  } else {
    $(target).parents(".cloud-item").addClass("selected");
  }
}

function headerBar(isSelected) {
  if (isSelected) {
    document.querySelector(".noSelected").style.display = "block";
    document.querySelector(".isSelected").style.display = "none";
  } else {
    document.querySelector(".noSelected").style.display = "none";
    document.querySelector(".isSelected").style.display = "block";
    var select_em = document.querySelector(".select > em");
    if (select_em.firstChild) {
      select_em.firstChild.remove();
    }
    select_em.appendChild(document.createTextNode(document.querySelectorAll(".cloud-list .selected").length));
  }
}

// 전체선택 이벤트
checkbox_btn.addEventListener("click", check_all);
function check_all() {
  if (checkbox_flag) {
    
    checkbox_icon.setAttribute("class", "far fa-square");
    $(".cloud-item").removeClass("selected");
    displaySelect($(".cloud-item"), true);
    $(".checkbox").prop("checked", false);
    headerBar(true);

    checkbox_flag = false;
  } else {
    
    checkbox_icon.setAttribute("class", "far fa-check-square");
    $(".cloud-item").addClass("selected");
    displaySelect($(".cloud-item"), false);
    $(".checkbox").prop("checked", true);
    headerBar(false);

    checkbox_flag = true;
  }
}
// 클라우드 body 마우스 우클릭 이벤트 삭제
card_body.addEventListener("contextmenu", function (e) {
  e.preventDefault();
  customContextEvent(e);
});
document.body.addEventListener("click", function (e) {
  hiddenMenu();
});

function hiddenMenu(){
  $(".cloud-item-menu").css({
    visibility: "hidden",
    top: "-9999px",
    left: "-9999px"
  });
  $(".up_file_on").css({
    visibility: "hidden",
    top: "-9999px",
    left: "-9999px"
  });
}
// card-body 우클릭 이벤트 주기
function customContextEvent(event) {
  var x = event.pageX;
  var y = event.pageY;
  if (event.button == 2 || event.which == 3) {
    if ($(event.target).parents().hasClass("cloud-item")) {
      $(".cloud-item-menu").css({
        visibility: "visible",
        top: y,
        left: x
      });
      cloudListClickEvent(event);
    } else {
      $(".up_file_on").css({
        visibility: "visible",
        top: y,
        left: x
      });
    }
  } 
}

// 보기 창 띄우기
var bu_rightmenu_on = document.querySelector(".bu_rightmenu_on");
var rightmenu_on = document.querySelector(".rightmenu_on");

var on_parent_li = bu_rightmenu_on.parentElement.parentElement;

on_parent_li.addEventListener("mouseover", () =>
  rightmenubar_on(on_parent_li, rightmenu_on)
);
on_parent_li.addEventListener("mouseout", () => rightmenubar_off(rightmenu_on));
rightmenu_on.addEventListener("mouseover", () =>
  rightmenubar_on(on_parent_li, rightmenu_on)
);
rightmenu_on.addEventListener("mouseout", () => rightmenubar_off(rightmenu_on));

// 정렬기준 창 띄우기
var bu_rightmenu_first = document.querySelector(".bu_rightmenu_first");
var rightmenu_first = document.querySelector(".rightmenu_first");

var first_parent_li = bu_rightmenu_first.parentElement.parentElement;

first_parent_li.addEventListener("mouseover", () =>
  rightmenubar_on(first_parent_li, rightmenu_first)
);
first_parent_li.addEventListener("mouseout", () =>
  rightmenubar_off(rightmenu_first)
);
rightmenu_first.addEventListener("mouseover", () =>
  rightmenubar_on(first_parent_li, rightmenu_first)
);
rightmenu_first.addEventListener("mouseout", () =>
  rightmenubar_off(rightmenu_first)
);

// rightmenu 띄우기
function rightmenubar_on(parent_li, div_element) {
  var parent_li_top =
    window.pageYOffset + parent_li.getBoundingClientRect().top;
  var parent_li_left =
    window.pageXOffset +
    parent_li.getBoundingClientRect().right -
    document.querySelector(".card-body").getBoundingClientRect().left +
    6;

  div_element.style.top = parent_li_top + "px";
  div_element.style.left = parent_li_left + "px";
  div_element.style.visibility = "visible";
}

// rightmenu 감추기
function rightmenubar_off(div_element) {
  div_element.style.top = "-9999px";
  div_element.style.left = "-9999px";
  div_element.style.visibility = "hidden";
}

// li click event
var rightmenu_lis = document.querySelectorAll(".min_right_ly > li");

for (var i = 0; i < rightmenu_lis.length; i++) {
  rightmenu_lis[i].addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();
    li_on(e.target);
  });
}

function li_on(eventTarget) {
  $(eventTarget).parent().siblings().removeClass("on");
  $(eventTarget).parent().addClass("on");

    if ($(eventTarget).hasClass("name")) {
      $(".task_view_area").find(".name").get(0).click();
    } 
    if ($(eventTarget).hasClass("filesize")) {
      $(".task_view_area").find(".filesize").get(0).click();
    }
    if ($(eventTarget).hasClass("updateDt")) {
      $(".task_view_area").find(".updateDt").get(0).click();
    }
    if ($(eventTarget).hasClass("registDt")) {
      $(".task_view_area").find(".registDt").get(0).click();
    }
    if ($(eventTarget).hasClass("list")) {
      $(".btn_view.list").click();
    }
    if ($(eventTarget).hasClass("grid")) {
      $(".btn_view.grid").click();
    }
  
  rightmenubar_off(eventTarget.parentElement.parentElement.parentElement);
}

// 새 폴더 만들기 Event
var mkdirs = document.querySelectorAll(".mkdir");
for (var i = 0; i < mkdirs.length; i++) {
  mkdirs[i].addEventListener("click", function () {
    var v_viewType = viewType();
    $(".cloud-list").removeClass("justify-content-center");
    $(".cloud-list").addClass("justify-content-start");
    $(".cloud-list").find("img").remove();
    $(".cloud-list").find(".item").remove();

    if (v_viewType == "list") {
      mkDetail(document.querySelector(".list_detail"))
    } else {
      mkdir(document.querySelector(".cloud-list"));
    }
    document.querySelector(".folder_name").focus();
  });
}

function mkDetail(v_element) {
  var mk_li = document.createElement("li");
    var mk_div1 = document.createElement("div");
    var mk_input1 = document.createElement("input");
    var mk_label = document.createElement("label");
    var mk_div2 = document.createElement("div");
    var mk_span1 = document.createElement("span");
    var mk_div3 = document.createElement("div");
    var mk_div4 = document.createElement("div");
    var mk_span2 = document.createElement("span");
    var mk_input2 = document.createElement("input");
    var mk_div5 = document.createElement("div");
    var mk_div6 = document.createElement("div");

    mk_li.setAttribute("class", "cloud-item d-flex");
    mk_div1.setAttribute("class", "cell check");
    mk_input1.setAttribute("type", "checkbox");
    mk_input1.setAttribute("class", "checkbox");
    mk_label.setAttribute("class", "input-label");
    mk_label.setAttribute("style", "display: none");
    mk_div2.setAttribute("class", "cell type");
    mk_span1.setAttribute("class", "etc folder");
    mk_div3.setAttribute("class", "cell info");
    mk_div4.setAttribute("class", "inner");
    mk_span2.setAttribute("class", "fnc");
    mk_input2.setAttribute("class", "folder_name");
    mk_input2.setAttribute("value", "새 폴더");
    mk_div5.setAttribute("class", "cell size");
    mk_div6.setAttribute("class", "cell date_edit");

    var times = getTimes();
    mk_div6.innerText = times;
    mk_div4.appendChild(mk_span2);
    mk_div4.appendChild(mk_input2);
    mk_div3.appendChild(mk_div4);
    mk_div2.appendChild(mk_span1);
    mk_div1.appendChild(mk_input1);
    mk_div1.appendChild(mk_label);
    mk_li.appendChild(mk_div1);
    mk_li.appendChild(mk_div2);
    mk_li.appendChild(mk_div3);
    mk_li.appendChild(mk_div5);
    mk_li.appendChild(mk_div6);

    v_element.prepend(mk_li);
}
function detailEvent(eventTarget) {
  var mk_a = document.createElement("a");

  mk_a.setAttribute("class", "title uploader _target file_name");
  mk_a.innerText = eventTarget.value;
  registData.dirNm = eventTarget.value;

  eventTarget.replaceWith(mk_a);
}

function mkdir(v_element) {
    var mk_li = document.createElement("li");
    var mk_div1 = document.createElement("div");
    var mk_div2 = document.createElement("div");
    var mk_span1 = document.createElement("span");
    var mk_div3 = document.createElement("div");
    var mk_input1 = document.createElement("input");
    var mk_span2 = document.createElement("span");
    var mk_input2 = document.createElement("input");
    var mk_label = document.createElement("label");
    var mk_textNode = document.createTextNode("선택");

    mk_li.setAttribute("class", "cloud-item m-2 text-center");
    mk_div1.setAttribute("class", "item-thumb border mb-2");
    mk_div2.setAttribute("class", "thumb");
    mk_span1.setAttribute("class", "folder");
    mk_span2.setAttribute("class", "checkbox");
    mk_input1.setAttribute("type", "text");
    mk_input1.setAttribute("class", "folder_name");
    mk_input1.setAttribute("value", "새 폴더");
    mk_span2.setAttribute("class", "checkbox");
    mk_input2.setAttribute("id", "");
    mk_input2.setAttribute("class", "input_check prevent_drag_select");
    mk_input2.setAttribute("type", "checkbox");
    mk_input2.setAttribute("style", "display: none;");
    mk_label.setAttribute("for", "");
    mk_label.setAttribute("class", "input-label");

    mk_div2.appendChild(mk_span1);
    mk_div1.appendChild(mk_div2);
    mk_label.appendChild(mk_textNode);
    mk_span2.appendChild(mk_input2);
    mk_span2.appendChild(mk_label);
    mk_li.appendChild(mk_div1);
    mk_li.appendChild(mk_input1);
    mk_li.appendChild(mk_span2);

    v_element.prepend(mk_li);

}

function dirEvent(eventTarget) {
  var mk_div = document.createElement("div");
  var mk_textNode = document.createTextNode(eventTarget.value);
  registData.dirNm = eventTarget.value;

  mk_div.setAttribute("class", "folder_name");
  mk_div.appendChild(mk_textNode);

  eventTarget.replaceWith(mk_div);
}


function fileUpload() {
  document.querySelector("#fileUpload").click();
}

function folderUpload() {
  document.querySelector("#folderUpload").click();
}

// 정렬기준 이벤트 등록
document.querySelector(".task_view_area").addEventListener("click", function (e) {
  if (e.target.classList.contains("array_option")) {
    displaySortBy(e.target);
  }
});
document.querySelector(".task_view_area").addEventListener("click", function (e) {
  if (e.target.tagName == "A" && $(e.target).closest(".ly_task").length > 0) {
    selectSortBy(e.target);
  }
});

	// 보기기준 이벤트 등록
	document.querySelector(".task_view_area").addEventListener("click", function (e) {
    if (e.target.classList.contains("btn_view")) {
      viewSelector(e.target);
    }
	});

  // 정렬기준 띄워주기
  function displaySortBy(eventTarget) {
    if (eventTarget.parentElement.classList.contains("open")) {
      eventTarget.parentElement.classList.remove("open");
    } else {
      eventTarget.parentElement.classList.add("open");
    }
  }

  // 정렬기준 바꾸기
  function selectSortBy(eventTarget) {
    for (var i = 0; i < eventTarget.parentElement.parentElement.children.length; i++ ) {
      eventTarget.parentElement.parentElement.children[i].classList.remove("selected");
    }
    if ($(eventTarget).hasClass("name")) {
      $(".rightmenu_first").find(".name").get(0).click();
    }
    if ($(eventTarget).hasClass("filesize")) {
      $(".rightmenu_first").find(".filesize").get(0).click();
    }
    if ($(eventTarget).hasClass("updateDt")) {
      $(".rightmenu_first").find(".updateDt").get(0).click();
    }
    if ($(eventTarget).hasClass("registDt")) {
      $(".rightmenu_first").find(".registDt").get(0).click();
    }

    eventTarget.parentElement.classList.add("selected");
    $(eventTarget).closest(".array_cover").removeClass("open");
    
    $(eventTarget).parents(".array_cover").find(".array_option").data("value", $(eventTarget).data("value"));

    var text = $(eventTarget).text();
    $(eventTarget).parents(".array_cover").find(".array_option").html(text);

    rollback();
    getCloudList();
  }

  // 보기기준 바꾸기
  function viewSelector(eventTarget) {
    eventTarget.classList.add("selected");
    $(eventTarget).parent().siblings(".btn_view_cover").children(".btn_view").removeClass("selected");
    if (eventTarget.classList.contains("list")) {
      $(".rightmenu_on").find(".list").get(0).click();
    } else {
      $(".rightmenu_on").find(".grid").get(0).click();
    }
    rollback();
    getCloudList();
  }

  // 보기기준 설정
  function viewType() {
    if($(".btn_view.list").hasClass("selected")) {
      return "list";
    } else if($(".btn_view.grid").hasClass("selected")) {
      return "grid";
    }
  }

  // 화면 선택 시 설정 초기화
  function rollback() {
    checkbox_icon.setAttribute("class", "far fa-square");
      $(".cloud-item").removeClass("selected");
      displaySelect($(".cloud-item"), true);
      $(".checkbox").prop("checked", false);
      headerBar(true);
      

      checkbox_flag = false;
      
    headerBar(true);
  }
// 현재시간 가져오기(시간)
function getTimes() {
  var today = new Date();

  var hours = today.getHours();
  var minutes = today.getMinutes();
  if (minutes < 10) {
    minutes = "0" + minutes;
  }

  if (hours > 12) {
    return "오늘 오후 " + (hours-12) + " : " + minutes;
  } else {
    return "오늘 오전 " + hours + " : " + minutes;
  }
}

// 디렉토리 등록
function registDirectory(eventTarget) {
  registData.cldKeyCode = $("select[name='cloudKeyCode'] option:selected").data("key");
  registData.upDir = $(".path_item > a:last").data("dirno");
  if (listData.cldKeyCode == 1) {
    registData.prjNo = $("select[name='cloudKeyCode'] option:selected").val();
  }
  registData.dirPath = "";
  for (var dir of $(".path_item > a")) {
    registData.dirPath = registData.dirPath + "/" + dir.dataset.dirpath;
  }
  registData.dirPath = registData.dirPath = registData.dirPath + "/" + eventTarget.value;
  
  
  $.ajax ({
    url : "registDirectory",
    data : JSON.stringify(registData),
    type : "POST",
    contentType : "application/json;charset=utf-8",
    success : function (data) {
      getCloudList();
    },
    error : function (error) {
      AjaxErrorSecurityRedirectHandler(error.status);
    },
  });
  
}

// 파일 등록
function registFile() {
  var formData = new FormData();
  var registObj = {};
  var cldKeyCode = $("select[name='cloudKeyCode'] option:selected").data("key");
  var dirNo = $(".path_item > a:last").data("dirno");
  var uploadFile = $("input[name='uploadFile']")[0].files;
  registObj.dirPath = "";
  
  for(var file of uploadFile) {
    formData.append("uploadFile", file);
  }
  for (var dir of $(".path_item > a")) {
    registObj.dirPath = registObj.dirPath + "/" + dir.dataset.dirpath;
  }
  
  formData.append("dirPath", registObj.dirPath);
    
  formData.append("cldKeyCode", cldKeyCode);
  formData.append("dirNo", dirNo);
  
  var url = "registFile";
  ajaxMultipartForm(formData, url);
  
  getCloudList();
}

// 폴더 채로 등록 ...완료!
function registFolder() {
  const folder = document.querySelector("input[name='folder']");
  var files = folder.files;
  
  for (var file of files) {
    var formData = new FormData();
    
    var registObj = {};
    registObj.cldKeyCode = $("select[name='cloudKeyCode'] option:selected").data("key");
    registObj.upDir = $(".path_item > a:last").data("dirno");
    registObj.dirPath = "";
    for (var dir of document.querySelectorAll(".path_item > a")) {
      registObj.dirPath = registObj.dirPath + "/" + dir.dataset.dirpath;
    }
    registObj.filePath = file.webkitRelativePath;
    
    if (listData.cldKeyCode == 1) {
      registObj.prjNo = $("select[name='cloudKeyCode'] option:selected").val();
    }
    registObj.uploadFile = file;
    formData.append("cldKeyCode", registObj.cldKeyCode);
    formData.append("upDir", registObj.upDir);
    formData.append("dirPath", registObj.dirPath);
    formData.append("filePath", registObj.filePath);
    
    if (listData.cldKeyCode == 1) {
      formData.append("prjNo", registObj.prjNo);
    }
    
    formData.append("uploadFile", registObj.uploadFile);
    var url = "registFolderFile";
    ajaxMultipartForm(formData, url);
    
  }
  getCloudList();
}
// 파일/폴더 download 함수 이벤트 등록 및 함수 선언
document.querySelectorAll(".download");
for (let i = 0; i < document.querySelectorAll(".download").length; i++) {
  document.querySelectorAll(".download")[i].addEventListener("click", download);
}

function download() {
  if (!$("#view").find(".cloud-item.selected")) {
    alert("선택된 폴더 또는 파일이 없습니다.");
    return;
  }
  var cloudViewEl = document.querySelector("#view");
  var selectedClouds = cloudViewEl.querySelectorAll(".cloud-item.selected");
  let downloadInfoArr = [];
  let url = "";
  let typeCheckFlag = false;
  let filePath = "";
  
  if(selectedClouds.length > 1) {
    for (let i = 0; i < selectedClouds.length; i++) {
      let downloadObj = {};
      
      if (selectedClouds[i].querySelector(".folder")) {
        downloadObj.type = "folder";
        downloadObj.path = selectedClouds[i].dataset.path;
        
        downloadInfoArr.push(downloadObj);
      }
      if (selectedClouds[i].querySelector(".file")) {
        downloadObj.type = "file";
        downloadObj.path = selectedClouds[i].dataset.path;
        
        downloadInfoArr.push(downloadObj);
      }
      url = "downloadMultiFile";
      typeCheckFlag = true;
    }
  } else {
    let downloadObj = {};
    for (let i = 0; i < selectedClouds.length; i++) {
      if (selectedClouds[i].querySelector(".folder")) {
        downloadObj.type = "folder";
        downloadObj.path = selectedClouds[i].dataset.path;
        
        downloadInfoArr.push(downloadObj);
        url = "downloadMultiFile";
        typeCheckFlag = true;
        
      }
      if (selectedClouds[i].querySelector(".file")) {
        downloadObj.type = "file";
        downloadObj.path = selectedClouds[i].dataset.path
        
        downloadInfoArr.push(downloadObj);
        url = "downloadFile";
        typeCheckFlag = false;
        filePath = downloadObj.path.substring(downloadObj.path.lastIndexOf("/") + 1).split("$$")[1]
      }
    }
  }
  
  $.ajax({
    url : url,
    type : "POST",
    contentType : "application/json;charset=UTF-8",
    data : JSON.stringify(downloadInfoArr),
    xhrFields : {
      responseType : "blob"
    },
    success : function(data, xhr) {
      let upDirNm = $(".path_item > a:last").text();
      console.log(upDirNm);

      let blob = new Blob([data]);
      let link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      if (typeCheckFlag) {
        link.download = upDirNm + ".zip";
      } else {
        link.download = filePath;
      }
      link.click();
    },
    error : function(error) {
      AjaxErrorSecurityRedirectHandler(error.status);
    }
  });
}

// ajax multipartform 함수
function ajaxMultipartForm(formData, url) {
  $.ajax({
    url : url,
    type : "POST",
    processData: false,
    contentType : false,
    async : false,
    enctype : 'multipart/form-data',
    data : formData,
    success : function(response) {
      
    },
    error : function(error) {
      AjaxErrorSecurityRedirectHandler(error.status);
    }
  });
}

function ajaxJsonData(object, url) {
  var result = "";
  $.ajax ({
    url : url,
    data : JSON.stringify(object),
    type : "POST",
    async : false,
    contentType : "application/json;charset=utf-8",
    success : function (data) {
      if (data == "success") {
        result = "success";
        
      }
    },
    error : function (error) {
      AjaxErrorSecurityRedirectHandler(error.status);
      result = "fail";
    }
  });

  return result;
}

// 수정버튼 클릭 이벤트
document.querySelector(".modify").addEventListener("click", modifyName);
		
function modifyName() {
  var target = $("#view").find(".cloud-item.selected");
  
  if (target.data("dirnm")) {
    var mk_input = $("<input>");
    mk_input.attr({"class" : "folder_name modify", "type" : "text", "value" : $(target).find(".folder_name").text()});
    $(target).find(".folder_name").replaceWith(mk_input);
  } else {
    
    var mk_input = $("<input>");
    mk_input.attr({"class" : "file_name modify", "type" : "text", "value" : $(target).find(".file_name").text()});
    $(target).find(".file_name").replaceWith(mk_input);
  }
}

// 파일 / 폴더 이름 변경 이벤트
document.querySelector("#view").addEventListener("keypress", function(e) {
  if (e.keyCode == 13) {
    if (!e.target.value) {
      Swal.fire({
        text : '변경할 이름을 입력하세요.',
        icon : 'error',
        confirmButtonColor : '#007bff',
        confirmButtonText : '확인'
      });
    return;
    }
    if (e.target.tagName == "INPUT" && $(e.target).hasClass("modify")) {
      var v_viewType = viewType();
      var oldName = "";
      if (v_viewType == "list") {
        if (e.target.classList.contains("file_name")) {
          oldName = $(e.target).parents(".cloud-item").data("storednm");
          modifyFile(e.target, oldName);
        } else {
          oldName = $(e.target).parents(".cloud-item").data("dirnm");
          modifyDirectory(e.target, oldName);
        }
        detailEvent(e.target);
      } else {
        if (e.target.classList.contains("file_name")) {
          oldName = $(e.target).parents(".cloud-item").data("storednm");
          modifyFile(e.target, oldName);
        } else {
          oldName = $(e.target).parents(".cloud-item").data("dirnm");
          modifyDirectory(e.target, oldName);
        }
        dirEvent(e.target);
      }
    } else if (e.target.tagName == "INPUT" && e.target.classList.contains("folder_name")) {
      if (e.keyCode == 13) {
        var v_viewType = viewType();
        if (v_viewType == "list") {
          detailEvent(e.target);
          registDirectory(e.target);
        } else {
          dirEvent(e.target);
          registDirectory(e.target);
        }
      }
    }
  }
  rollback();
});

function modifyDirectory(eventTarget, oldName) {
  let modifyData = {};
  
  modifyData.dirNo = $(eventTarget).parents(".cloud-item").val();
  modifyData.oldFileName = oldName;
  modifyData.newFileName = eventTarget.value;
  modifyData.path = $(eventTarget).parents(".cloud-item").data("path");
  modifyData.type = "folder";
  
  var url = "modifyCloudName";
  ajaxJsonData(modifyData, url);
}

function modifyFile(eventTarget, oldName) {
  let modifyData = {};

  modifyData.cldFileNo = $(eventTarget).parents(".cloud-item").val();
  modifyData.oldFileName = oldName;
  modifyData.newFileName = eventTarget.value;
  modifyData.path = $(eventTarget).parents(".cloud-item").data("path");
  modifyData.type = "file";
  modifyData.extension = oldName.substring(oldName.lastIndexOf(".") + 1);
  var url = "modifyCloudName";
  ajaxJsonData(modifyData, url);
}

// 파일 / 폴더 삭제 이벤트
for (let i = 0; i < document.querySelectorAll(".remove").length; i++) {
  document.querySelectorAll(".remove")[i].addEventListener("click", removeCloudItem);
}

function removeCloudItem() {
  var removeItems = document.querySelectorAll(".cloud-item.selected");
  var removeItemArr = [];
  if (removeItems.length < 1) {
    Swal.fire({
      text: '한 개 이상 선택해주세요.',
      icon: 'error',
      confirmButtonColor: '#007bff',
      confirmButtonText: '확인'
    });
    return;
  }

  for (var removeItem of removeItems) {
    let removeItemContainer = {};
    if (removeItem.dataset.dirnm) {
      removeItemContainer.dirNo = removeItem.value;
      removeItemContainer.type = "folder";
      
    } else if (removeItem.dataset.storednm) {
      removeItemContainer.cldFileNo = removeItem.value;
      removeItemContainer.type = "file";
    }
    removeItemArr.push(removeItemContainer);
  }

  Swal.fire({
    text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
    icon: 'warning',
    confirmButtonColor: '#007bff',
    cancelButtonColor: '#dc3545',
    showCancelButton: true,
    confirmButtonText: '확인',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      var ajaxResult = ajaxJsonData(removeItemArr, "removeCloud");
      if (ajaxResult == "success") {
        Swal.fire({
          text: '삭제가 완료되었습니다.',
          icon: 'success',
          confirmButtonColor: '#007bff',
          confirmButtonText: '확인'
          });
          headerBar(true);
          getCloudList();
      }
    }
    });;
  }


// 클라우드 선택 기능
document.querySelector(".custom-select").addEventListener("change", function (e) {
  e.preventDefault();
  
  $(".path_list").html("");
  var mk_li = document.createElement("li");
  
  $(mk_li).attr({
    "class" : "path_item"
  });
  
  var mk_a = document.createElement("a");
  $(mk_a).attr({
      "class" : "depth",
      "data-dirno" : $("select[name='cloudKeyCode'] option:selected").data("dirno"),
      "data-dirpath" : $("select[name='cloudKeyCode'] option:selected").text()
    });
  $(mk_a).text($("select[name='cloudKeyCode'] option:selected").text());
  
  mk_li.append(mk_a);
  
  $(".path_list").html($(mk_li));
  
  rollback();
  getCloudList();
});