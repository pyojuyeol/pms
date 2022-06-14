<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- sweetalert2 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.css" />
	<!-- 파비콘설정  -->
	<link rel="icon" href="<%=request.getContextPath()%>/resources/images/common/favicon.svg">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/loading/loading.css" />
	
	<title><decorator:title default="PROSPEC'S"/></title>

	<decorator:head />
</head>
<body id="body">

	<decorator:body />


    <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- sweetalert2 -->
    <script src="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.js"></script>
	
</body>




</html>






