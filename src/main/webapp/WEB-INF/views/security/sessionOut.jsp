<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%	response.setStatus(302); %>
	
<!-- sweetalert2 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.css" />
<script src="<%=request.getContextPath()%>/resources/sweetalert2/sweetalert2.min.js"></script>
<script>
	Swal.fire({
	  text: '${message}',
	  icon: 'warning',
	  confirmButtonColor: '#007bff',
	  confirmButtonText: '확인'
	}).then((result) => {
	  /* Read more about isConfirmed, isDenied below */
	  if (result.isConfirmed) {
				   
		if(window.opener) {
			window.opener.parent.location.reload();
		}else{
			window.location.href="<%=request.getContextPath()%>";
		}
		window.close();
	  }
	});
</script>
