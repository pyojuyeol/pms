///발표용 텍스트 input
function inputSampleText(textObj){
	for(obj in textObj){
		let target = document.querySelector("[name='"+obj+"']");
		console.log(target.tagName);
		if(target.tagName == "TEXTAREA"){
			var str = textObj[obj];
			str.replace(/(?:\r\n|\r|\n)/g, '\n');
			var strArr = str.split("\n");
			console.log(strArr);

			$(target).text(str);
			for (var i = 0; i < strArr.length; i++) {
				if (i == 0) {
					$(target).siblings(".note-editor").find(".note-editable p").text(strArr[i]);
				} else {
					var p = $("<p>").text(strArr[i]);
					console.log(p.text());
					$(target).siblings(".note-editor").find(".note-editable").append(p);
				}
			}
			$(target).siblings(".note-editor").find(".note-placeholder").css("display","none");
			continue;
		}
		target.value = textObj[obj];
	}
}


/* 데이트 객체 포맷 함수 */
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

/* 데이트 객체 포맷 함수 예시*/
//2011년 09월 11일 오후 03시 45분 42초
//console.log(new Date().format("yyyy년 MM월 dd일 a/p hh시 mm분 ss초"));
 
//2011-09-11
//console.log(new Date().format("yyyy-MM-dd"));
 
//'11 09.11
//console.log(new Date().format("'yy MM.dd"));
 
//2011-09-11 일요일
//console.log(new Date().format("yyyy-MM-dd E"));
 
//현재년도 : 2011
//console.log("현재년도 : " + new Date().format("yyyy"));

/* byte 포맷 함수 */
function fileSizeFomatter(bytes, si=false, dp=1) {
  const thresh = si ? 1000 : 1024;

  if (Math.abs(bytes) < thresh) {
    return bytes + ' B';
  }

  const units = si
    ? ['KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']
    : ['KiB', 'MiB', 'GiB', 'TiB', 'PiB', 'EiB', 'ZiB', 'YiB'];
  let u = -1;
  const r = 10**dp;

  do {
    bytes /= thresh;
    ++u;
  } while (Math.round(Math.abs(bytes) * r) / r >= thresh && u < units.length - 1);


  return bytes.toFixed(dp) + ' ' + units[u];
}

/* byte 포맷 함수 예시*/
// console.log(humanFileSize(5000, true))  // 5.0 kB
// console.log(humanFileSize(5000, false))  // 4.9 KiB
// console.log(humanFileSize(-10000000000000000000000000000))  // -8271.8 YiB
// console.log(humanFileSize(999949, true))  // 999.9 kB
// console.log(humanFileSize(999950, true))  // 1.0 MB
// console.log(humanFileSize(999950, true, 2))  // 999.95 kB
// console.log(humanFileSize(999500, true, 0))  // 1 MB



/* 사용자 사진 넣기 */
function MemberPictureThumb(contextPath) { // (대상, 이미지 파일명)

	for(var target of document.querySelectorAll('.manPicture')) {
	
		var id = target.getAttribute('data-id');
	
		target.style.backgroundImage = "url('" + contextPath + "/getPicture.do?id=" + id + "')";
		target.style.backgroundPosition = "center";
		target.style.backgroundRepeat = "no-repeat";
		target.style.backgroundSize = "cover";
	}
}

function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
		winleft = (screen.width - WinWidth) / 2;
		wintop = (screen.height - WinHeight) / 2;
		var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
								+"height="+ WinHeight +", top="+ wintop +", left=" 
								+ winleft +", resizable=yes, status=yes"  );
		win.focus() ; 
	}
	
//summernote
var contextPath = "";

function summernote_go(target,context){
	contextPath = context;
	
	target.summernote({
		placeholder:'여기에 내용을 적으세요.',
		lang:'ko-KR',
		height:250,
		disableResizeEditor: true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				for(var file of files){
					//alert(file.name);
					
					if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
						alert("JPG 이미지형식만 가능합니다.");
						return;
					}
					if(file.size > 1024*1024*5){
						alert("이미지는 5MB 미만입니다.");
						return;
					}		
				}
				
				//alert("선택된 파일은 유효합니다.");
				
				for (var file of files) {
					sendFile(file,this);
				}
			},
			onMediaDelete : function(target) {
				//alert("image delete");
				//alert(target[0].src);
				//alert(target.attr("src"));
				deleteFile(target[0].src);	
			}
		}
	});	
}


function sendFile(file, el) {
	var form_data = new FormData();
	form_data.append("file", file); 
	$.ajax({
    	url: contextPath+'/uploadImg.do',
    	data: form_data,
    	type: "POST",	    	
    	contentType: false,	    	
    	processData: false,
    	success: function(img_url) {
    		//alert(img_url);
      		$(el).summernote('editor.insertImage', img_url);
    	},
    	error:function(error){
    		//alert(file.name+" 업로드에 실패했습니다.");
    		 AjaxErrorSecurityRedirectHandler(error.status);		
    	}
  	});
}


function deleteFile(src) {		
	var splitSrc= src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
	
	var fileData = {fileName:fileName};
	
	$.ajax({
		url:contextPath+"/deleteImg.do",
		data : JSON.stringify(fileData),
		type:"post",
		contentType:"application/json",
		success:function(res){
			console.log(res);
		},
		error:function(error){
			//alert("이미지 삭제가 불가합니다.");
			 AjaxErrorSecurityRedirectHandler(error.status);		
		}
	});
 }

function LoadObject(e,layerObj) {
			
			var offset = $(".layout-top-nav").width() - $("div.wrapper").width();
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = layerObj.width();
			var oHeight = layerObj.height();

			// 레이어가 나타날 위치를 셋팅한다.
			var divLeft =  e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft-offset/2; 
			var divTop = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
			/*console.log("left"+divLeft,"top"+divTop);*/
			if(layerObj.attr("style").includes("none")){
				layerObj.css({
					"top": divTop,
					"left": divLeft,
					"position": "absolute"
				}).show();
			} else {
				layerObj.hide();
			}
}

 function HideLayer(areaObj,layerObj) {
	areaObj.on('contextmenu', function(e) {
		return false;
	});
	areaObj.contextmenu( function(e){
	 	if(!layerObj.attr("style").includes("none")){
	 		layerObj.hide();
	 	}
	} );
	areaObj.click( function(e){
	 	if(!layerObj.attr("style").includes("none")){
	 		layerObj.hide();
	 	}
	} );
}

 function ShowLayer(areaObj,layerObj,mouseEventStr) {
	 layerObj.hide();
	/* 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
	$(function(){
		if(mouseEventStr=="click") {
			areaObj.click( function(e){ LoadObject(e, layerObj) } );
		}
		if(mouseEventStr=="dblclick") {
			areaObj.dblclick( function(e){ LoadObject(e, layerObj) } );
		}
		if(mouseEventStr=="hover") {
			areaObj.hover( function(e){	LoadObject(e, layerObj); } );
		}
		if(mouseEventStr=="rclick") {
			 areaObj.on('contextmenu', function(e) {
				return false;
			 });
			areaObj.contextmenu( function(e){	LoadObject(e, layerObj); } );
		}
		
	});
}

function ShowLoading() {
	$("#loader").addClass("loading");
}

function HideLoading() {
	$("#loader").removeClass("loading");
}

function IsOpen() {
	if(location.href.indexOf(".open") > 0) {
		return true;
	} else {
		return false;
	}
}

function GetReqStr(str) {
	if(IsOpen()) {
		if(str) {
			return "open?open=1&";
		}
			return "open?open=1";
	} else {
		if(str) {
			return "do?";
		}
			return "do";
	}
}
	