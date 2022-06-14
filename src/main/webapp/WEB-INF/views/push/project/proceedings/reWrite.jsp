<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="<%=new Date() %>" />
<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>

<head>
	<link href="<%=request.getContextPath()%>/resources/spread/SpreadJS/css/gc.spread.sheets.excel2013white.15.0.6.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/resources/spread/Designer/Designer Component/css/gc.spread.sheets.designer.15.0.6.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prjNm}</h1>
				<input type="hidden" name="prjNo" value="${prjNo }"/>
				<input type="hidden" name="prjNm" value="${prjNm }"/>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prjNm }</a></li>
					<li class="breadcrumb-item"><a href="#">회의록</a></li>
					<li class="breadcrumb-item active">등록</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<!-- Main content -->
<section class="content">
	<div class="card card-secondary card-outline">

		<div class="card-header">
			<div class ="card-tools">
					<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">저 장</button>
					<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="history.go(-1);" >취 소</button>
					<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="test_go(-1);" >테스트</button>
			</div>
		</div>
		<!--end card-header  -->
		<div class="register-card-body">
			<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
				<div class="row">
					<div class="form-group col-md-12">
						<label for="title">제 목</label> 
						<input type="text" id="title" name='title' class="form-control" value="${proceedings.title }">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="register">작성자</label> 
						<input type="text" id="registerNm" readonly name="registerNm" class="form-control" value="${loginUser.userNm }">
						<input type="hidden" id="register" readonly name="register" class="form-control" value="${loginUser.userId }">
					</div>
					<div class="form-group col-md-6">
						<label for="meetDe">회의 일자</label> 
						<input type="text" readonly id="meetDe" name="meetDe" class="form-control" value="${proceedings.meetDe.split(" ")[0]  }" onchange="">
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label for="">회의 참여자</label>&nbsp;&nbsp;
	<!-- 					<i class="fa-solid fa-user-plus"></i> -->
						<button type="button" class="btn btn-xs btn-primary" onclick="OpenWindow('/pms/orgExplorer/list.open?name=addMembers&tag=div','사용자 탐색',800,600);"><i class="fas fa-plus"></i></button>
					</div>
					
					<div id="memberInput" class="d-flex flex-wrap">
						<c:forEach items="${userList }" var="user" varStatus="status">
					
							<div class="toast fade show mr-2 mt-2 addedMembers oldMembers thumb${user.userId }" name="addMembers" value="${user.userId }" role="alert" aria-atomic="true" style="flex-basis:auto; max-width:130px;">
								<input type="hidden" id="${status.index }" name="addMembers" value="${tmem.userId }">
								<div class="toast-header" style="padding: 0.5rem 0.75rem;">
									<span class="img-circle manPicture mr-2" data-id="${user.userId }" style="display:block; width:38px; height:38px;"></span>
									<p class="m-0">
										<strong>${user.userNm }</strong>
									</p>
									<button type="button" class="ml-2 close align-self-start" data-dismiss="toast" onclick="removeOld('thumb${user.userId }');return false;" aria-label="Close">×</button>
								</div>
							</div>
	
						</c:forEach>
					</div>
				</div>
				<div class="row justify-content-between mb-2">
					<div class="col-12 d-flex justify-content-end align-items-center">
					<div class="mr-2">
						<label class="btn btn-sm btn-outline-success m-0" for="fileInput"><i class="fas fa-file pr-2"></i>양식 불러오기</label> 
						<input type="file" class="d-none" id="fileInput">
					</div>
						<input type="button" class="btn btn-sm btn-outline-success mr-2" value="엑셀로 저장" id="saveExcel">
						<input type="button" class="btn btn-sm btn-outline-danger" value="PDF로 저장" id="savePDF"> 
					</div>
				</div>
				
				<div id="gc-designer-container" style="min-height:600px"></div>
				
			</form>
			<!--end card-footer  -->
		</div>
		<!-- end card -->

	</div>
	<!-- content-wrapper -->
</section>

<script>
var userArray = [];
</script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/gc.spread.sheets.all.15.0.6.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.charts.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.shapes.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.print.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.barcode.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.pdf.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.pivot.pivottables.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/plugins/gc.spread.sheets.tablesheet.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/resources/ko/gc.spread.sheets.resources.ko.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/SpreadJS/scripts/interop/gc.spread.excelio.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/Designer/Designer Component/scripts/gc.spread.sheets.designer.resource.ko.15.0.6.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/Designer/Designer Component/scripts/gc.spread.sheets.designer.all.15.0.6.min.js"></script>
	
	<script type="text/javascript">
		GC.Spread.Sheets.LicenseKey = "E282159147118715#B0dV4MZJja9ljVjhmMR9GNv94KRZ6MpZ6L8UWNUVHSHhTcUhjYOFleOFWN4gWckdkTxQ7U5I6RlZ4UzRnc0BVRpRmc696M8omVudWcygGR9ZGNv9kQyRzTiNjcytCSwBDSRV7ZSp4bxJTMrRncOl6S5xkdDZzROt6U7g5Y6FGV4xkYO5ERPJlQQVUUpJXZudmNolDeOBDZFJWRyk4UhZ7a0tCcMRGWxtWTSFnd9RTU4FGbQBHSFZmaHBnUhhmTxRUa6U6Tr3SZRhUaI9GUwMkMxdkY0Fma9tkYZZGa9dGRT9kaXJVYwYzKwl6RwUzRqFlNLV7VPBVMyMjcBlEVUJiOiMlIsICMykjNFN4NiojIIJCL6IjN7AjM7cDO0IicfJye#4Xfd5nIMFUUTJiOiMkIsISNx8idgMlSgQWYlJHcTJiOi8kI1tlOiQmcQJCLikjM4EDOwACMxUDMyIDMyIiOiQncDJCLikDM6AjMyAjMiojIwhXRiwiIQuJ1cC10cCr0s0J186J1V680Ay80iojIh94QiwSZ5JHd0ICb6VkIsISNxcDOxEzN4ETO5EjM8IjI0ICZJJCL35lIlxmYhRFdvZXaQJyW0IyZsZmIsU6csFmZ0IiczRmI1pjIs9WQisnOiQkIsISP3c6UMh6cxJ5SZJ7K5REbvcTSvpEZyREekJ7LxVjShhlUjVVcRt6KR9mcyJETrVEbtp5KBFzNhpVYIJnM9Z4QCNjU7hUV8MVRQNFZmV4SqhUSmpERJNXeZdOU";
// 		var designer = new GC.Spread.Sheets.Designer.Designer(document.getElementById("gc-designer-container"));
		var spread =  new GC.Spread.Sheets.Workbook(document.getElementById("gc-designer-container"));
		
		
		initSpread(spread);
		
		// 초기 Spread에 dafault font 적용
		function initSpread(spread) {
		  var sheet = spread.getActiveSheet();
		    fetch("https://assets.codepen.io/975719/malgun.ttf")
		    .then(function(response) {
		    return response.arrayBuffer();
		  })
		    .then(function(arrayBuffer) {
		    var base64String = _arrayBufferToBase64(arrayBuffer);
		    var fonts = {
		      normal: base64String
		    };
		    GC.Spread.Sheets.PDF.PDFFontsManager.registerFont('malgun', fonts);
		    GC.Spread.Sheets.PDF.PDFFontsManager.fallbackFont = function (font) {
		      return fonts.normal;
		    }
		  });
		}
		
		// Base64로 읽어들이는 함수
		function _arrayBufferToBase64(buffer) {
		  var binary = "";
		  var bytes = new Uint8Array(buffer);
		  var len = bytes.byteLength;
		  for (var i = 0; i < len; i++) {
		    binary += String.fromCharCode(bytes[i]);
		  }
		  return window.btoa(binary);
		}
		
		// PDF 저장기능
		document.getElementById('savePDF').onclick = function () {
		    var sheet = spread.sheets[0];
		    var printInfo = sheet.printInfo();
		    printInfo.showGridLine(false);
		    printInfo.showRowHeader(GC.Spread.Sheets.Print.PrintVisibilityType.hide);
		    printInfo.showColumnHeader(GC.Spread.Sheets.Print.PrintVisibilityType.hide);
		    printInfo.blackAndWhite(true);
		    
				spread.savePDF(
						function (blob) {
								saveAs(blob, '제목이랑 회의 날짜 받아서 맹글기.pdf');
						},
						console.log,
						{
								title: 'Test Title',
								author: 'Test Author',
								subject: 'Test Subject',
								keywords: 'Test Keywords',
								creator: 'test Creator'
						});
		};

		// 엑셀파일 불러오기 기능
		var excelIo = new GC.Spread.Excel.IO();
		document.getElementById('fileInput').onchange = function () {
				var excelFile = this.files[0];
				
				// here is excel IO API
				excelIo.open(excelFile, function (json) {
						var workbookObj = json;
						
						spread.fromJSON(workbookObj);
						
						// remove spread js default sheet
						spread.removeSheet(0);
						
				}, function (e) {
						// process error
						alert(e.errorMessage);
						
				});
				
		};


		// 엑셀파일 저장하기 기능
		document.getElementById('saveExcel').onclick = function () {

				var fileName = "제목이랑 회의 날짜 받아서 맹글기";
				if (fileName.substr(-5, 5) !== '.xlsx') {
						fileName += '.xlsx';
				}

				var json = spread.toJSON();
				
				// here is excel IO API
				excelIo.save(json, function (blob) {
						saveAs(blob, fileName);
				}, function (e) {
						// process error
						console.log(e);
				});

		};
		// 작성된 excel 자료 DB 저장
		
		var regist_go = function () {
			let formData = new FormData();
			
			let title = document.querySelector("input[name='title']").value;
			let meetDe = document.querySelector("input[name='meetDe']").value;
			let register = document.querySelector("input[name='register']").value;
			let prjNo = document.querySelector("input[name='prjNo']").value;
			let prjNm = document.querySelector("input[name='prjNm']").value;
			
			formData.append("title", title);
			formData.append("meetDe", meetDe);
			formData.append("register", register);
			formData.append("prjNo", prjNo);
			formData.append("prjNm", prjNm);
			for(i=0; i < userArray.length; i++) {
				formData.append("userId", userArray[i]);
				console.log(userArray[i]);
			}
			
			
			var fileName = meetDe + "&&" + title;
			if (fileName.substr(-5, 5) !== '.pdf') {
					fileName += '.pdf';
			}
			
			spread.savePDF(
					function (blob) {
						let pdfFile = new File([blob], fileName, {
							  type: blob.type,
							});
						
						formData.append("pdfFile", pdfFile);
						
						var json = spread.toJSON();
						
						// here is excel IO API
						excelIo.save(json, function (blob) {
							let excelFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".xlsx"; 	
							
							let excelFile = new File([blob], excelFileName, {
								  type: blob.type,
								});
							formData.append("excelFile", excelFile);
							
							ajaxMultipartForm("regist", formData);
						});
					});
		}
		
		// multipartForm을 ajax로 보내자
		function ajaxMultipartForm(url, formData) {
			$.ajax({
				url : url,
				type : "POST",
				processData : false,
				async : false,
				contentType : false,
				enctype : "multipart/form-data",
				data : formData,
				success : function (result) {
					if (result == "success") {
						alert("성공이라능");
						location.href="<%=request.getContextPath()%>/project/proceedings/list.do"
					}
				},
				error : function(error) {
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
		}
		
		// Ajax 결과에 따른 alert창 함수
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
		function test_go() {
			
		}
	</script>
	
<script>
var dataNum = 0;
function updateUserList(exMemArr) {
	
// 	console.log(exMemArr);
	for(i=0; i<exMemArr.length; i++) {
		userArray.push(exMemArr[i].id);
	}

	var saveArr = [];
		
		// 기존 멤버 리스트 가져오기.
		let addedMemList = $(".addedMembers");
		let oldMemList = $(".oldMembers");
//	 	let removeSize = $("input[name='removeMembers']").length;
		
		// 추가된 멤버가 전혀 없기 때문에 거를 필요 없이 바로 멤버 추가.
		if (addedMemList.length == 0) {
			for (i = 0; i < exMemArr.length; i++) {
				addMember(exMemArr[i]);
			}
		}
		
		// 기존 멤버가 존재해서 밸리데이션 필요.
		var addedArr = [];
		var oldArr = [];
		if (addedMemList.length != 0) {
			
			// 배열 some() 함수 사용을 위해 아이디만 뽑아서 배열에 추가
			for (item of addedMemList) {
				let id = $(item).attr('value');
				addedArr.push(id);
			}
			for (item of oldMemList) {
				let id = $(item).attr('value');
				oldArr.push(id);
			}
			
			// 체크 해제로 인한 added 멤버 삭제
			for (i = 0; i < addedArr.length; i++) {
				let result = exMemArr.every(val => { return val.id != addedArr[i] });
				
				if (result == true) {
					let dataNo = $('div[value="' + addedArr[i] + '"]').data('no');
					removeMember(dataNo);
				}
			}
			
			// 체크 해제로 인한 old 멤버 삭제
			for (i = 0; i < oldArr.length; i++) {
				let result = exMemArr.every(val => { return val.id != oldArr[i] });
				
				if (result == true) {
					var saveData = $('div[value="' + oldArr[i] + '"]');
					var userId = oldArr[i];
					
					let delClass = 'thumb' + userId
					removeOld(delClass);
				}
			}
			
		}
		
		// removeMembers에 있는 멤버를 복구하면 처음 변수에 담아 두었던 addedMemList에 오차가 생기므로 다시 가져와서 비교.
		addedMemList = $(".addedMembers");
		if (addedMemList.length != 0) {
			
			for (item of addedMemList) {
				let id = $(item).attr('value');
				addedArr.push(id);
			}
			// 기존 멤버가 존재해서 밸리데이션이 필요한 경우. (중복 추가 방지);
			// some() 기존 멤버 ID 배열에 같은 아이디가 존재 하면 true 리턴. false 리턴 시 기존 멤버에 없으므로 추가.
			for (i = 0; i < exMemArr.length; i++) {
				let result = addedArr.some(val => { return val == exMemArr[i].id });
				if (result == false) {
					addMember(exMemArr[i]);
				} 
			}	
			
		}
		
		// 이미지 출력.
		MemberPictureThumb('<%=request.getContextPath() %>');
}


	function addMember(addMem) {
		let id = addMem.id;
		let userName = addMem.userName;
		
		var toast = $('<div>').addClass("toast fade show mr-2 mt-2 addedMembers").attr({"data-no":dataNum, "role":"alert", "aria-atomic":"true", "name":"addMembers", "value":id}).css({"flex-basis":"auto", "max-width":"130px"});
		var toastHeader = $('<div>').addClass("toast-header").css("padding","0.5rem 0.75rem");
		var span = $('<span>').addClass("img-circle manPicture mr-2").attr({"data-id":id}).css({"display":"block", "width":"38px", "height":"38px"});
		var p = $('<p>').addClass("m-0");
		var strong = $('<strong>').css("font-dize", "1.2em").text(userName);
		
		p.append(strong);
		toastHeader.append(span);
		toastHeader.append(p);
		toastHeader.append("<button class='ml-2 close align-self-start' type='button' data-dismiss='toast' onclick='removeMember(" + dataNum + "); return false;' aria-label='Close'>×</button>");
		toast.append(toastHeader);
		
		$('#memberInput').append(toast);

		var input = $('<input>').attr({"type":"hidden", "id":dataNum, "name":"addMembers", "value":id});
		$('form[role="form"]').prepend(input);

		dataNum++;
	}

	// added x 버튼 클릭 삭제
	function removeMember(dataNum) {
		$('div[data-no="' + dataNum + '"]').remove();
		$('input[id="' + dataNum + '"]').remove();
	}


	// oldMember x 버튼 클릭 삭제 (saveArr 백업.)
	function removeOld(className) {
		var div = $('div.' + className);
		div.remove();
	}


</script>
</body>
	