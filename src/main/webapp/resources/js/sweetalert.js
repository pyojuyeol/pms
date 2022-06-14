
function alertN(message) {

        Swal.fire({
        icon: 'success',
        text: message,
        showConfirmButton: false,
        timer: 1500
          })
}

function alertC(message) {

        Swal.fire({
        icon: 'success',
        text: message,
        confirmButtonColor: '#007bff',
        showConfirmButton: true,
        confirmButtonText: '확인'
          })
}

function alertE(message) {

        Swal.fire({
        icon: 'error',
        text: message,
        confirmButtonColor: '#007bff',
        showConfirmButton: true,
        confirmButtonText: '확인'
          })
}

function alertI(message) {

        Swal.fire({
        icon: 'info',
        text: message,
        confirmButtonColor: '#007bff',
        showConfirmButton: true,
        confirmButtonText: '확인'
          })
}

function alertW(message) {

        Swal.fire({
        icon: 'warning',
        text: message,
        confirmButtonColor: '#007bff',
        showConfirmButton: true,
        confirmButtonText: '확인'
          })
}

function confirmS(message,icon,func) {
		Swal.fire({
		  text: message,
		  icon: icon,
		  showCancelButton: true,
		  confirmButtonColor: '#007bff',
		  cancelButtonColor: '#dc3545',
		  confirmButtonText: '확인',
		  cancelButtonText: '취소'
		}).then((result) => {
			  if (result.isConfirmed) {
				  func()
			  }
  		  });

}

function alertA(message,icon,func,func2) {
		Swal.fire({
		  text: message,
		  icon: icon,
		  confirmButtonColor: '#007bff',
		  confirmButtonText: '확인'
		}).then((result) => {
			  if (result.isConfirmed) {
			  	if(func) {
				  func();
			  	}
				if(func2) {
				  func2();
				}
			  }
  		  });

}

function promptSW(message,fnc,fnc_p) {
    Swal.fire({
        title: message,
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소',
        confirmButtonColor: '#007bff',
		cancelButtonColor: '#dc3545',
        showLoaderOnConfirm: true,
    }).then((result) => {
        if (result.isConfirmed) {
            fnc(result.value,fnc_p);
        }
    })
}



