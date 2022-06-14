<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>

	if('${loginUser}'){
		if('${loginUser.authGrpCode}'=='ROLE_ADMIN'){			
			location.href="admin/dbcn/list.do";
		}else {			
			location.href="dashboard.do";
		}
	}else{
		location.href="common/loginForm.do";
	}
</script>