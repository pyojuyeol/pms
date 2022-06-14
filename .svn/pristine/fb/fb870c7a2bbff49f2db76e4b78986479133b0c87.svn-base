<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login/login2.css" />
</head>
<title>PROSPEC'S | 로그인</title>
<body>
<div class="center">
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1><img src="<%=request.getContextPath()%>/resources/images/common/favicon_b.svg" style="width:30px;margin-right:5px;"/>Prospec's</h1>
			</div>
			<div class="form-content">
				<form action="<%=request.getContextPath()%>/common/login.do"
						method="post">
					<div class="form-group">
						<label for="id">사원번호</label> <input type="text"
							id="id" name="id" placeholder="사원번호를 입력하세요." value="${param.userId }" required="required">
					</div>
					<div class="form-group">
						<label for="pwd">비밀번호</label> <input type="password"
							id="pwd" name="pwd" placeholder="비밀번호를 입력하세요." required="required">
					</div>
					<div class="form-group">
<!-- 						<label class="form-remember">  -->
<!-- 						<input type="checkbox">Remember Me -->
<!-- 						</label> -->
						<a class="form-recovery" href="./forgotId.do">Forgot id?</a>
						<a class="form-recovery" href="./forgotPwd.do">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit">Log In</button>
					</div>
				</form>
			</div>
		</div>
		<div class="form-panel two">
		    <div class="form-header">
		      <h1>Null 4 & ☀</h1>
		    </div>
		    <div class="form-content">
		      <form>
		        <div class="form-group">
		          <label for="username">DB 메이커 한범오빠</label>
		          <input type="text" id="username" name="username" value="디비 진짜 어려운건데 꼼꼼히 잘 짜주셔서 감사해요. 덕분에 우리조원들이 수월하게 프로젝트 진행하고 있어요" required="required">
		        </div>
		        <div class="form-group">
		          <label for="password">우리조 UI디자이너 상우오빠</label>
		          <input type="text" id="password" name="password" value="프로젝트 중심부인 프로젝트랑 팀 /* 그리고 UI디자인 */ 을 맡으셔서 고민이 많으셨죠. 묵묵히 그리고 열심히 만들어주셔서 감사해요." required="required">
		          <input type="password" id="password" name="password" style="margin-top:5px;" value="이건 커밋 메세지 안남길거예요." required="required">
		        </div>
		        <div class="form-group">
		          <label for="cpassword">우리조 웃음 담당 정은아</label>
		          <input type="text" id="cpassword" name="cpassword" value="너 아니였음 이슈랑 책갈피 그렇게 꼼꼼한 기능이 되지 못했을거야. 물론 디자인은 상우오빠한테 컨펌 꼭 받아..ㅋㅋㅋㅋㅋ" required="required">
		        </div>
		        <div class="form-group">
		          <label for="email">남은 2주동안 진정한 프론트 개발자가 되어갈 주열아</label>
		          <input type="email" id="email" name="email" value="취업은 백엔드로 할거지만 2주만 monroe랑 싸우자. 달력이랑 싸워서 이겼으니까 이번에도 너가 이길거야." required="required">
		          <input type="password" id="email" name="email" style="margin-top:5px;" value="그 싸움 구경은 꼭 갈게" required="required">
		          <input type="hidden" id="email" name="email" value="개발자 모드에서 보면 보이는데 파일 찾아서 들어왔지?" required="required">
		        </div>
		        <div class="form-group">
		          <label for="email">막내온탑 귀요미를 맡고있는 요셉아</label>
		          <input type="email" id="email" name="email" value="취업해서도 파일 문제는 너한테 throws IOException할게 클라우드는 우리 학원에서 전으로도 후로도 너가 만든게 짱일거야" required="required">
		        </div>
		        <div class="form-group">
		          <button type="submit">딱 2주만 열심히해서 후회없는 결과 만들어내자<br>submit</button>
		        </div>
		      </form>
		    </div>
		  </div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/login.js" ></script>
</body>