<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="<%=new Date() %>" />
<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>

<head>
</head>
<body>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>${prjNm}</h1>
			</div>
			<div class="col-sm-6 d-none d-sm-block">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">${prj.prjNm }</a></li>
					<li class="breadcrumb-item"><a href="#">회의록</a></li>
					<li class="breadcrumb-item active">상세보기</li>
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
				<c:if test="${!empty userList }" >
					<c:forEach items="${userList }" var="prcdUser">
						<c:if test="${prcdUser.userId eq loginUser.userId }">
						
							<c:if test="${prcdUser.sttCode eq 1 }">
								<button type="button" class="btn btn-primary" id="registBtn" onclick="submit_go(2);">승 인</button>
								<button type="button" class="btn btn-danger ml-2 mr-5" id="cancelBtn" onclick="submit_go(3);" >반 려</button>	
							</c:if>
						
<!-- 							<button type="button" class="btn btn-primary" id="registBtn" onclick="modify_go();">변 경</button> -->
							<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="location.href='list.do';" >취 소</button>
							<c:if test="${proceedings.register eq loginUser.userId }">
								<c:if test="${proceedings.sttCode eq 3 }">
									<button type="button" class="btn btn-info ml-2" id="registBtn" onclick="reWrite_go();">재작성</button>
								</c:if>							
								<c:if test="${proceedings.sttCode ne 3 }">
									<button type="button" class="btn btn-danger ml-2" id="returnBtn" onclick="returnAll();" >회 수</button>
								</c:if>							
							</c:if>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<!--end card-header  -->
		<div class="register-card-body">
			<form enctype="multipart/form-data" id="modifyForm" role="form" method="post" action="modify.do" name="modifyForm">
				<div class="row">
					<div class="form-group col-md-10">
						<input type="hidden" id="check" name="sttCode" value="">
						<label for="title">제 목</label> 
						<input type="text" readonly id="title" name='title' class="form-control" value="${proceedings.title }">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="register">작성자</label> 
						<input type="text" id="registerNm" readonly name="registerNm" class="form-control" value="${proceedings.userNm }">
						<input type="hidden" id="register" readonly name="register" class="form-control" value="${proceedings.register }">
						<input type="hidden" id="prcdNo" readonly name="prcdNo" class="form-control" value="${proceedings.prcdNo }">
					</div>
					<div class="form-group col-md-6">
						<label for="meetDe">회의 일자</label> 
						<input type="text" id="meetDe" readonly name="meetDe" class="form-control" value="${proceedings.meetDe.split(" ")[0] }">
					</div>
				</div>
				<c:if test="${proceedings.sttCode eq 3}">
					<div class="row">
						<div class="form-group col-md-12">
							<label for="returnMsg">반려 사유</label> 
							<input type="text" readonly name="returnMsg" class="form-control" value="${proceedings.returnMsg }">
						</div>
					</div>
				</c:if>
				<c:if test="${proceedings.sttCode ne 3}">
					<input type="hidden" id="category" readonly name="category" class="form-control" value="">
				</c:if>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="prcdUser">회의 참여자</label> 
					</div>
				</div>
				<div class="row mb-2">
					<c:if test="${!empty userList }" >
						<c:forEach items="${userList }" var="prcdUser" varStatus="index">
						  <div class="col-md-2">	
							<span><b>${prcdUser.userNm }</b></span>
<%-- 							<c:if test="${prcdUser.userId eq loginUser.userId }"> --%>
<%-- 								<c:if test="${prcdUser.sttCode eq 1 }"> --%>
<!-- 									<select id="check" name="sttCode" class="form-control custom-select mt-2"> -->
<!-- 										<option value="2" >승 인</option>	 -->
<!-- 										<option value="3" >반 려</option>	 -->
<!-- 									</select>	 -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${prcdUser.sttCode ne 1 }"> --%>
<%-- 									<input type="hidden" id="sttCode" name="sttCode" value="${prcdUser.sttCode }"> --%>
<%-- 								</c:if> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${prcdUser.userId ne loginUser.userId }"> --%>
								<c:if test="${prcdUser.sttCode eq 1 }">
									<span class="badge bg-warning m-2" style="font-size: 0.9em;"><b>진행중</b></span>	
								</c:if>
<%-- 							</c:if> --%>
							<c:if test="${prcdUser.sttCode eq 2 }">
								<span class="badge bg-success m-2" style="font-size: 0.9em;"><b>승인</b></span>	
							</c:if>
							<c:if test="${prcdUser.sttCode eq 3 }">
								<span class="badge bg-red m-2" style="font-size: 0.9em;"><b>반려</b></span>	
							</c:if>
						  </div>
						</c:forEach>
					</c:if>
					</div>
				
				<div>
  					<iframe id="pdfFile" data-prcdno="${proceedings.prcdNo }" src="/pdf/test.pdf" style="width:100%;height:700px;"></iframe>
				</div>
			</form>
			<!--end card-footer  -->
		</div>
		<!-- end card -->

	</div>
	<!-- content-wrapper -->
</section>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.all.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.charts.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.shapes.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.print.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.barcode.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.pdf.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.pivot.pivottables.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.tablesheet.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.sheets.resources.ko.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/spreadjs/scripts/gc.spread.excelio.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/designer/scripts/gc.spread.sheets.designer.resource.ko.15.0.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/spread/designer/scripts/gc.spread.sheets.designer.all.15.0.3.min.js"></script>
	
	<script type="text/javascript">
		
		var designer = new GC.Spread.Sheets.Designer.Designer(document.getElementById("gc-designer-container"));
		var spread = designer.getWorkbook();
		spread.setSheetCount(1);
		
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
		// 작성된 excel 자료 저장
		
		var regist_go = function () {
			var data = JSON.stringify(spread.toJSON());
			spread.savePDF(
					function (blob) {
// 							saveAs(blob, '제목이랑 회의 날짜 받아서 맹글기.pdf');
							console.log(blob);
					});
			
// 			var doc = new jsPDF('p', 'mm', 'a4');
// 			console.log(doc, 1);
			
// 			var canvas = document.querySelector("#vp_vp");
			
// 			var imgData = canvas.toDataURL('image/png');
// 			doc.addImage(imgData, "PNG", 0, 0);
			
// 			console.log(doc, 2);
// 			doc.save('sample-file.pdf'); //pdf저장

			
// 			var stream = new blobStream();
			
// 			var ctx = new canvas.PdfContext(stream);
			
// 			ctx.stream.on('finish', function () {
// 			    var blob = ctx.stream.toBlob('application/pdf');
// 			    console.log(blob);
// 			});
			
			
// 			console.log(ctx)
// 			ctx.end();
// 			console.log(canvas);

// 			$.ajax({
// 				url : "regist",
// 			    type : "POST",
// 			    data : data,
// 			    contentType : "application/json;charset=utf-8",
// 			    success : function (data) {
// 			    	alert("성공");
// 			    },
// 			    error : function (e) {
// 			    	alert("실패");
// 			    }
// 			});
			
		}
		
	</script>
	
<script>
var returnMsgg = "";
window.addEventListener('DOMContentLoaded', detailProceedings);

function detailProceedings() {
	
	<c:if test="${from eq 'modify'}" >
		alertC("변경되었습니다.");
	</c:if>    
	<c:if test="${from eq 'return'}" >
		alertC("반려되었습니다.");
	</c:if>
	let target = document.querySelector("#pdfFile");
	getPdfFile("<%=request.getContextPath()%>", target);
}

function returnAll() {
	confirmS("반려하시겠습니까?", "warning", returnModi);
}

function getPdfFile(contextPath, target) {
	let prcdNo = target.dataset.prcdno;
	
	target.src = contextPath + "/project/proceedings/getPdf.open?prcdNo=" + prcdNo;
}

function modify_go() {
    // alert("modify btn click !")

	var form = $('form[name="modifyForm"]');

	if ($("input[name='title']").val() == "") {  // form.title.value
		alertC("제목은 필수입니다.");
		$("input[name='title']").focus();
		return;
	}
	
	if($("#check").val() == "3") {
		confirmS("반려로 변경하시겠습니까?","warning",returnModi)
		return;
	}
	
	form.submit();
}

function returnModi() {
	
	
	
	 promptSW('반려사유를 입력해 주세요.',reasonn);
	
}

function reasonn(result) {
	returnMsgg = result
	$('#category').val(returnMsgg);
	$("#sttCode").val("3");
	$('form[name="modifyForm"]').submit();
}

function reWrite_go() {
	
	var form = $('form[name="modifyForm"]');

	if ($("input[name='title']").val() == "") {  // form.title.value
		alertC("제목은 필수입니다.");
		$("input[name='title']").focus();
		return;
	}
	
	form.attr('action','reWrite.do');
	
	form.submit();
	
}

function submit_go(num) {
	
	var form = $('form[name="modifyForm"]');

	if(num==2) {
		$("#check").val("2");
	} 
	if(num==3) {
		$("#check").val("3");
	} 
	
	if($("#check").val() == "3") {
		confirmS("반려로 변경하시겠습니까?","warning",returnModi)
		return;
	}
	
	form.submit();
	
}
</script>
</body>
	