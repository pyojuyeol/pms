(function(){
  let currentLocation = location.href;
  let target = document.getElementById("mainMenu");
 
  let menu = {
    'admin' : `
              <li class="nav-item"><a class="nav-link" href="/pms/admin/log/main.do">이력관리</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/admin/db/list.do">DB서버 관리</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/admin/scheduler/list.do">스케줄러 관리</a></li>
            `,
    'common' : `
              <li class="nav-item"><a class="nav-link" href="/pms/prjSetting/list.do">프로젝트</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/team/list.do">Team</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/notice/list.do">공지사항</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/pds/list.do">자료게시판</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/record/list.do">Record</a></li>
       		    <li class="nav-item"><a class="nav-link" href="/pms/member/list.do">사원 관리</a></li>
       		    <li class="nav-item"><a class="nav-link" href="/pms/organization/list.do">조직도 관리</a></li>
              `,
    'project' : `						
              <li class="nav-item"><a class="nav-link" href="/pms/project/home.do">Home</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/work/list.do">일감</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/gantt/list.do">간트차트</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/issue/list.do">이슈</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/calendar/main.do">일정</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/cloud/main.do">클라우드</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/history/list.do">History</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/report/list.do">업무보고</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/proceedings/list.do">회의록</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/project/notice/list.do">공지사항</a></li>
            `,  
    'mypage' : `						
              <li class="nav-item"><a class="nav-link" href="/pms/myPage/myworks/list.do">내 업무관리</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/myPage/calendar/main.do">내 일정관리</a></li>
              <li class="nav-item"><a class="nav-link" href="/pms/myPage/cloud/main.do">클라우드</a></li>
            `
  }

  if(currentLocation.indexOf("/admin/")>-1){
    target.innerHTML=menu.admin;
  } else if(currentLocation.indexOf("/project/")>-1){
    target.innerHTML=menu.project;
  } else if(currentLocation.indexOf("/myPage/")>-1){
    target.innerHTML=menu.mypage;
  } else {
    target.innerHTML=menu.common;
  }

  addClassActiveOnTargetMenu();
  function addClassActiveOnTargetMenu(){
    let menuItems = document.querySelectorAll("#mainMenu li");
    let currMenu = currentLocation.substring(0,currentLocation.lastIndexOf("/")+1);
  
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
})()