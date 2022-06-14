<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cloud/cloud.css" />
<title>자료실</title>
</head>
<!-- Content Wrapper. Contains page content -->
<!-- Main content -->
<body>
	<section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                </div>
                <div class="col-sm-6 d-none d-sm-block">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="/pms/dashboard.do">Home</a></li>
                        <li class="breadcrumb-item active">Cloud</li>
                    </ol>
                </div>
            </div>
        </div>
    </section>
	<section class="content">
		<div class="card card-primary card-outline">
			<div class="card-header">
			<div class="d-flex justify-content-between">
				<div class="">
					<select class="custom-select item" name="cloudKeyCode" style="width: 150px;">
						<option data-key="1" data-dirno="${project.dirNo}" value="${project.prjNo }">${project.prjNm }</option>
					</select>
				</div>
				<div class="d-flex">
					<!-- selector button -->
					<div class="btn-group item">
					<button type="button" class="btn btn-default mr-1 checkbox-toggle">
						<i id="checkbox" class="far fa-square"></i>
					</button>
					</div>
					<!-- upload div -->
					<div class="noSelected">
						<div class="btn-group item">
							<button type="button" class="btn btn-default dropdown-toggle dropdown-icon" data-toggle="dropdown">
								올리기 <span class="sr-only">Toggle Dropdown</span>
							</button>
							<div class="dropdown-menu" role="menu">
								<a class="dropdown-item" onclick="fileUpload();">파일 올리기</a>
								<a class="dropdown-item" onclick="folderUpload();">폴더 올리기</a>
							</div>
						</div>
						<div class="btn-group item">
							<button type="button" class="btn btn-default mkdir">새 폴더
								만들기</button>
						</div>
					</div>
					<div class="isSelected" style="display: none;">
						<div class="btn-group item mr-2">
							<button type="button" class="btn btn-default download">
								<span>내려받기</span>
							</button>
						</div>
						<div class="btn-group item mr-2">
							<button type="button" class="btn btn-default remove">
								<span>삭제</span>
							</button>
						</div>
						<span class="task_text select"> <em></em>개 선택</span>
					</div>
				</div>
			</div>
			</div>
			<!-- cloud body -->
			<div class="card-body pt-0">
				<div class="task_bottom_area">
					<div class="task_path_area">
						<div class="task_path_area">
							<div class="deep_path">
								<ul class="path_list">
									<li class="path_item"><a class="depth" data-dirno="${project.dirNo }" data-dirPath="${project.prjNm }">${project.prjNm }</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="task_view_area">
						<div class="array_cover">
							<a class="array_option" name="mainCategory" data-value="registDt">올린 날짜</a>
							<div class="ly_task">
								<ul>
									<li class="" ><a class="name" data-value="name">이름</a></li>
									<li class="" ><a class="filesize" data-value="filesize">크기</a></li>
									<li class="" ><a class="updateDt" data-value="updateDt">수정한 날짜</a></li>
									<li class="selected" ><a class="registDt" data-value="registDt">올린 날짜</a></li>
								</ul>
							</div>
						</div>
						<div class="array_cover ml-3">
							<a class="array_option" name="subCategory" data-value="desc">내림차순</a>
							<div class="ly_task">
								<ul>
									<li class=""><a data-value="asc">오름차순</a></li>
									<li class="selected"><a data-value="desc">내림차순</a></li>
								</ul>
							</div>
						</div>
						<div class="btn_view_cover">
							<button type="button" class="btn_view list" value="list">
								<span class="blind">자세히 보기</span>
							</button>
							<div class="ly_icon_tooltip">리스트 보기</div>
						</div>
						<div class="btn_view_cover">
							<button type="button" class="btn_view grid selected" value="grid">
								<span class="blind">썸네일로 보기</span>
							</button>
							<div class="ly_icon_tooltip">아이콘 보기</div>
						</div>
					</div>
				</div>
				<!-- 폴더화면 -->
				<div id="view" style="text-align : center;">
					
				</div>
			</div>
		</div>
		<div class="rightmenu_layer rightmenu_on"
			style="left: -9999px; top: -99999px; position: absolute; z-index: 1000; visibility: hidden;">
			<ul class="default min_right_ly">
				<li class="on"><a class="grid">아이콘 보기</a></li>
				<li class=""><a class="list">자세히 보기</a></li>
			</ul>
		</div>
		<div class="rightmenu_layer rightmenu_first"
			style="left: -9999px; top: -99999px; position: absolute; z-index: 1000; visibility: hidden;">
			<ul class="default min_right_ly">
				<li class=""><a class="name">이름</a></li>
				<li class=""><a class="filesize">크기</a></li>
				<li class=""><a class="updateDt">수정한 날짜</a></li>
				<li class="on"><a class="registDt">올린 날짜</a></li>
			</ul>
		</div>
		<div class="rightmenu_layer up_file_on"
			style="position: fixed; min-width: 114px; visibility: hidden; top: -9999px; left: -9999px;">
			<ul class="default min_right_ly">
				<li><a onclick="fileUpload();">파일 올리기</a></li>
				<li><a onclick="folderUpload();">폴더 올리기</a></li>
				<li class="partition"></li>
				<li class="mkdir"><a>새 폴더</a></li>
				<li><a>보기<span class="bu bu_supmenu bu_rightmenu_on"></span></a></li>
				<li class="partition"></li>
				<li><a>정렬 기준<span class="bu bu_supmenu bu_rightmenu_first"></span></a></li>
				<li class="partition"></li>
				<li><a onclick="check_all();">모두 선택</a></li>
			</ul>
		</div>
		<div class="rightmenu_layer cloud-item-menu" style="position: fixed; min-width: 114px; visibility: hidden; top: -9999px; left: -9999px;">
			<ul class="default min_right_ly">
				<li><a class="download">내려받기</a></li>
				<li class="partition"></li>
				<li><a class="remove">삭제</a></li>
				<li><a class="modify">이름 바꾸기</a></li>
			</ul>
		</div>
	</section>
	
	<input id="fileUpload" type="file" name="uploadFile" style="display: none;" onchange="registFile();" multiple />
	<input id="folderUpload" type="file" name="folder" webkitdirectory directory style="display: none;" onchange="registFolder();"/>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"  ></script>

	<script>
		window.addEventListener("load", cloud_init);
		
		var listData = {};
		var registData = {};
		
		function cloud_init() {
			Handlebars.registerHelper({
				  "isNotNull" : function(dataMap, options) {
				          if (Object.values(dataMap)[0].length >= 1 || Object.values(dataMap)[1].length >= 1) {
				            return options.fn(this);
				          } else {
					          return options.inverse(this);
				          }
			      },
				  "dateFommater" : function(date) {
				    return new Date(date).format("yyyy-MM-dd a/p hh시 mm분");
				  },
				  "sizeFommater" : function(size) {
				    return fileSizeFomatter(size, true, dp=2);
				  }
			});
			
			getCloudList();
		}
	</script>

	<script type="text/x-handlebars-template"  id="cloud-list-template" >
		<div id="list_area" class="list_area d-flex">
			<div class="list_type_detail" style="height: 100%;">
				<div class="sort_option m-0">
					<ul class="list_head">
						<li class="cell check"><i class="blind">check</i></li>
						<li class="cell type"><span class="btn_sort" aria-pressed="false">종류</span></li>
						<li class="cell info"><span class="btn_sort sort_down" aria-pressed="false">이름</span></li>
						<li class="cell size"><span class="btn_sort" aria-pressed="false">크기</span></li>
						<li class="cell date_edit"><span class="btn_sort" aria-pressed="false">수정한 날짜</span></li>
					</ul>
				</div>

				{{#isNotNull .}}
				<ul class="list_detail cloud-list d-flex flex-column" style="min-height:450px">

					{{#each cloudDirectoryList}}
					<li class="cloud-item d-flex" value="{{dirNo}}" style="width:100%;" data-path="{{dirPath}}" data-dirnm="{{dirNm}}">
						<div class="cell check">
							<input id="" class="checkbox" type="checkbox" style="display: block;">
							<label for="" class="input-label" style="display: none;"></label>
						</div>
						<div class="cell type">
							<span class="etc folder"></span>
						</div>
						<div class="cell info">
							<div class="inner">
								<span class="fnc"></span> 
								<a class="title uploader_target _draggable folder_name" title="{{dirNm}}">{{dirNm}}</a>
							</div>
						</div>
						<div class="cell size">{{filesize}}</div>
						<div class="cell date_edit">{{dateFommater updDt}}</div>
					</li>
					{{/each}}

					{{#each cloudList}}
					<li class="cloud-item d-flex" value="{{cldFileNo}}" data-path="{{path}}" data-storednm="{{storedNm}}" >
						<div class="cell check">
							<input id="" class="checkbox" type="checkbox" style="display: block;">
							<label for="" class="input-label" style="display: none;"></label>
						</div>
						<div class="cell type">
							<span class="etc file"></span>
						</div>
						<div class="cell info">
							<div class="inner">
								<span class="fnc"></span> 
								<a class="title uploader_target _draggable file_name" title="{{fileNm}}">{{fileNm}}</a>
							</div>
						</div>
						<div class="cell size">{{sizeFommater filesize}}</div>
						<div class="cell date_edit">{{dateFommater updDt}}</div>
					</li>
					{{/each}}

				</ul>

				{{else}}
				<ul class="list_detail cloud-list d-flex justify-content-center align-items-center" style="min-width : 450px;">
					<img class="item" src='/pms/resources/images/cloud/cloud_empty_icon2.png' style='width: 150px;'>
					<span class="item" style="color:#888;">등록된 파일이 없습니다</span>
				</ul>
				{{/isNotNull}}
			</div>
		</div>
	</script>
	
	<script type="text/x-handlebars-template"  id="cloud-grid-template" >
				{{#isNotNull .}}
				<ul class="cloud-list row d-flex justify-content-start" style="min-height:450px;">
					{{#each cloudDirectoryList}}
					<li class="cloud-item m-2 text-center" value="{{dirNo}}" data-path = "{{dirPath}}" data-dirnm="{{dirNm}}" >
						<div class="item-thumb border mb-2">
							<div class="thumb">
								<span class="folder"></span>
							</div>
						</div>
						<div class="folder_name">{{dirNm}}</div>
						<span class="checkbox">
							<input id="" class="input_check prevent_drag_select" type="checkbox" style="display: none;">
							<label for="" class="input-label">선택</label>
						</span>
					</li>
					{{/each}}

					{{#each cloudList}}
					<li class="cloud-item m-2 text-center" value="{{cldFileNo}}" data-path="{{path}}" data-storednm="{{storedNm}}" >
						<div class="item-thumb border mb-2">
							<div class="thumb">
								<span class="file"></span>
							</div>
						</div>
						<div class="file_name" data-strNm="{{storedNm}}">{{fileNm}}</div>
						<span class="checkbox">
							<input id="" class="input_check prevent_drag_select" type="checkbox" style="display: none;">
							<label for="" class="input-label">선택</label>
						</span>
					</li>
					{{/each}}
                </ul>

				{{else}}
                <ul class="cloud-list d-flex justify-content-center align-items-center" style="min-height:450px;">
				    <img src='/pms/resources/images/cloud/cloud_empty_icon2.png' style='width: 16%; height: auto;'>
				    <span class="align-self-center item" stlye="color:#888;">등록된 파일이 없습니다</span>
                </ul>
				{{/isNotNull}}
	</script>
	<script src="<%=request.getContextPath()%>/resources/js/cloud.js"></script>
</body>