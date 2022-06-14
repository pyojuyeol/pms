<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- sweetalert2 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.css" />
<script src="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.js"></script>

<script>
	Swal.fire({
	  text: '권한이 없습니다.',
	  icon: 'error',
	  confirmButtonColor: '#007bff',
	  confirmButtonText: '확인'
	}).then((result) => {
		  if (result.isConfirmed) {
				if (window.opener) {
					window.close();
				} else {
					history.go(-1);
				}					   
		  }
	});
</script>