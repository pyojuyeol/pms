<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script>
/* 스윗얼럿 확인 시 진행 예시 */
Swal.fire({
  text: '삭제 후 복구가 불가능합니다. \n정말 삭제하시겠습니까?',
  icon: 'warning',
  confirmButtonColor: '#007bff',
  cancelButtonColor: '#dc3545',
  showCancelButton: true,
  confirmButtonText: '확인',
  cancelButtonText: '취소'
}).then((result) => {
  /* Read more about isConfirmed, isDenied below */
  if (result.isConfirmed) {
			   Swal.fire({
			      text: '저장되었습니다.',
				  icon: 'success',
				  confirmButtonColor: '#007bff',
				  confirmButtonText: '확인'
			    }); 
  }
});

/* 스윗얼럿 성공 예시 */
Swal.fire({
  text: '저장되었습니다.',
  icon: 'success',
  confirmButtonColor: '#007bff',
  confirmButtonText: '확인'
});

/* 스윗얼럿 에러 예시 */
Swal.fire({
  text: '취소되었습니다.',
  icon: 'error',
  confirmButtonColor: '#007bff',
  confirmButtonText: '확인'
});
</script>