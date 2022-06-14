<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="now" value="<%=new Date() %>" />
<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>

<head>
	<link href="<%=request.getContextPath()%>/resources/spread/spreadjs/css/gc.spread.sheets.excel2013white.15.0.3.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/resources/spread/designer/css/gc.spread.sheets.designer.15.0.3.min.css" rel="stylesheet" type="text/css">
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
					<li class="breadcrumb-item active">변 경</li>
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
				<button type="button" class="btn btn-primary" id="registBtn" onclick="modify_go()">변 경</button>
				<button type="button" class="btn btn-warning ml-2" id="cancelBtn" onclick="history.go(-1)" >취 소</button>
			</div>
		</div>
		<!--end card-header  -->
		<div class="register-card-body">
			<form enctype="multipart/form-data" role="form" method="post" action="modify.do" name="modifyForm">
				<div class="row">
					<div class="form-group col-md-10">
						<label for="title">제 목</label> 
						<input type="hidden" id="prcdNo" name='prcdNo' class="form-control" value="${proceedings.prcdNo }">
						<input type="text" id="title" name='title' class="form-control" value="${proceedings.title }">
					</div>
<!-- 					<div class="form-group col-md-2"> -->
<!-- 						<input type="button" class="btn btn-sm btn-outline-success ml-4 mt-4" value="엑셀로 저장하기" id="saveExcel"> -->
<!-- 					</div> -->
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="register">작성자</label> 
						<input type="text" id="registerNm" readonly name="registerNm" class="form-control" value="${proceedings.userNm }">
						<input type="hidden" id="register" readonly name="register" class="form-control" value="${loginUser.userId }">
					</div>
					<div class="form-group col-md-6">
						<label for="meetDe">회의 일자</label> 
						<input type="text" id="meetDe" readonly name="meetDe" class="form-control" value="${proceedings.meetDe.split(" ")[0] }">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="prcdUser">상태 변경</label> 
					</div>
				</div>
				<div class="row mb-2">
					<c:if test="${!empty userList }" >
						<c:forEach items="${userList }" var="prcdUser" varStatus="index">
						  <div class="col-md-6 mb-2">
						    <c:if test="${prcdUser.userId eq loginUser.userId }">
								<span><b>${prcdUser.userNm }</b></span>
									<c:if test="${prcdUser.sttCode eq 1 }">
										<select id="${prcdUser.userId }" name="sttCode" class="form-control custom-select mt-2">
											<option value="2" >승 인</option>	
											<option value="3" >반 려</option>	
										</select>
									</c:if>
									<c:if test="${prcdUser.sttCode ne 1 }">
										<div class="form-control">이미 처리 되었습니다.</div>
									</c:if>
						    </c:if>	
						  </div>
						</c:forEach>
					</c:if>
					</div>
				
				<div>여기에 pdf 보이기</div>
				
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
function modify_go() {
    // alert("modify btn click !")

	var form = $('form[name="modifyForm"]');

	if ($("input[name='title']").val() == "") {  // form.title.value
		alertC("제목은 필수입니다.");
		$("input[name='title']").focus();
		return;
	}
	
	if($("#sttCode").val() == "3") {
		confirmS("반려로 변경하시겠습니까?","warning",returnModi)
		return;
	}
	
	form.submit();
}

function returnModi() {
	$('form[name="modifyForm"]').submit();
}
</script>
</body>
	