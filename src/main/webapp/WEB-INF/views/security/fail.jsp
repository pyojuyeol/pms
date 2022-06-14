<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- sweetalert2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.css" />
<script
	src="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.js"></script>
<script>
	Swal.fire({
	  text: '${message}',
	  icon: 'warning',
	  confirmButtonColor: '#007bff',
	  confirmButtonText: '확인'
	}).then((result) => {
	  if (result.isConfirmed) {
		  history.go(-1);
	  }
	});
</script>
