<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<body>
<%-- exception: ${exception } --%>
<!-- <br/> -->
<%-- message: ${message } --%>
<!-- <br/> -->
<%-- url: ${url } --%>
<script>
window.addEventListener('DOMContentLoaded', errorNavi);
function errorNavi() {
	alertA("${message }","warning",moveToUrl);
// 	alertW("${message }");
}
function moveToUrl() {
	var url = location.href;
	if(url.includes("modify") || url.includes("remove")){
		history.go(-1);
	} else {
		history.go(-1);
		window.close();
	}
<%-- 	location.href = "<%=request.getContextPath()%>${url}"; --%>
}
</script>
</body>