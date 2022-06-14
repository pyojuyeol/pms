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
	console.log("${message}");
	alertA("${message }","warning",moveToUrl);
}
function moveToUrl() {
	history.go(-2);

<%-- 	location.href = "<%=request.getContextPath()%>${url}"; --%>
}
</script>
</body>