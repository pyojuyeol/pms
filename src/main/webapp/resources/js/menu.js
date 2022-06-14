(function(){
  let currentLocation = location.href;
  let mType = "";
  if(currentLocation.indexOf("/admin/")>-1){
    mType = 'A';
  } else if(currentLocation.indexOf("/project/")>-1){
    mType = 'P';
  } else {
    mType = 'C';
  }
  getMenuList(mType);

  // 메뉴리스트 가져오는 함수
  function getMenuList(mType){
    $.ajax({ 
      url: "/pms/getMenuList.do?mType="+mType, 
      type: "POST",
      success: function(result){ 
        let mainMenu = document.getElementById("mainMenu");
        for(item of result){
          let menu = document.createElement("li");
          menu.classList.add("nav-item");
          let link = document.createElement("a");
          link.classList.add("nav-link");
          link.innerText = item.mName;
          link.href = "/pms"+item.mUrl;
          menu.append(link);
          mainMenu.append(menu);
        }
        addClassActiveOnTargetMenu();
      }, 
      error: function (request, status, error){ 
        Swal.fire({
            text: '메뉴를 불러올 수 없습니다. 에러코드' + error,
            icon: 'error',
            confirmButtonColor: '#007bff',
            confirmButtonText: '확인'
          });
      }
    });
  
  }
  // 현재 메뉴 표시하는 함수
  function addClassActiveOnTargetMenu(){
    let currentLocation = location.href;
    let currMenu = currentLocation.substring(0,currentLocation.lastIndexOf("/")+1);
  
    let menuItems = document.querySelectorAll("#mainMenu li");
  
    let isDash = currentLocation.substring(currentLocation.lastIndexOf("/")+1).indexOf("dash")>-1;
    if(isDash) return;
  
    for(li of menuItems){
      let link = li.children[0].href;
      if(link.indexOf(currMenu)>-1){
        li.classList.add('active');
        return;
      }
    }
  }
})();