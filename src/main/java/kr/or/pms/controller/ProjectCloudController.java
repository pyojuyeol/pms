package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.pms.command.CloudDirectoryCommand;
import kr.or.pms.command.CloudDownloadCommand;
import kr.or.pms.command.CloudModifyCommand;
import kr.or.pms.command.CloudRemoveCommand;
import kr.or.pms.command.CloudVOCommand;
import kr.or.pms.command.CloudViewCriteria;
import kr.or.pms.dto.CloudDirectoryVO;
import kr.or.pms.dto.ProjectVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.CloudService;

@Controller
@RequestMapping("/project/cloud")
public class ProjectCloudController {

	@Autowired
	private CloudService cloudService;

	@RequestMapping(value = "/main")
	public void myCloud(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		String loginUserId = user.getUserId();
		int prjNo = (int) session.getAttribute("currentPrjNo");

		CloudDirectoryVO cloudDirectory = cloudService.getCloudItemByUserId(loginUserId);
		List<ProjectVO> projectList = cloudService.getProjectListById(loginUserId);
		
		for (ProjectVO project : projectList) {
			if (project.getPrjNo() == prjNo) {
				model.addAttribute("project", project);
			}
		}
		model.addAttribute("cloudDirectory", cloudDirectory);
		
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> list(@RequestBody CloudViewCriteria cvc, HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> dataMap = null;

		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String loginUserId = loginUser.getUserId();
		
		cvc.setUserId(loginUserId);

		try {
			dataMap = cloudService.getCloudItems(cvc);
			entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			throw new Exception();
		}

		return entity;
	}

	@RequestMapping(value = "/registDirectory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CloudDirectoryVO> registDirectory(@RequestBody CloudDirectoryCommand cdc, HttpServletRequest request) throws Exception {
		ResponseEntity<CloudDirectoryVO> entity = null;
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		CloudDirectoryVO cdv = cdc.toCloudDirectoryVO();

		cdv.setUserId(loginUser.getUserId());
		
		System.out.println("regist dir" + cdc.getDirPath());
		
		try {
			int dirNo = cloudService.registCloudDirectory(cdv);

			cdv = cloudService.getCloudDirectoryByDirNo(dirNo);
			entity = new ResponseEntity<CloudDirectoryVO>(cdv, HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception();
		}

		return entity;
	}

	@RequestMapping(value = "/registFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registFile(CloudVOCommand cvc, HttpServletRequest request) throws Exception {
		ResponseEntity<String> entity = null;

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		cvc.setUserId(user.getUserId());
		
		String result = "";
		try {
			cloudService.registCloudFile(cvc);
			result = "success";
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception();
		}

		return entity;
	}

	@RequestMapping(value = "/registFolderFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registFolderFile(CloudVOCommand cvc, HttpServletRequest request) throws Exception {
		ResponseEntity<String> entity = null;

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		cvc.setUserId(user.getUserId());
		String result = ""; 
		try {
			cloudService.registFolder(cvc);
			result = "success";
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception();
		}
		
		return entity;
	}

	@RequestMapping(value = "/downloadMultiFile", method = RequestMethod.POST, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public void downloadMultiFile(@RequestBody List<CloudDownloadCommand> downloadInfoList, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		String loginUserId = user.getUserId();
		String ip = request.getRemoteAddr();
		
		for (CloudDownloadCommand cdc : downloadInfoList) {
			cdc.setIp(ip);
		}
		
		try {
			byte[] bytes = cloudService.downloadMultiFile(downloadInfoList, loginUserId);
			response.setHeader("Content-Disposition", "attachment;filename=cloud.zip");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setContentLength((int) bytes.length);
			
			FileCopyUtils.copy(bytes, response.getOutputStream());
			response.flushBuffer();
		
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	@ResponseBody
	public void downloadFile(@RequestBody List<CloudDownloadCommand> fileInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		String loginUserId = user.getUserId();
		String ip = request.getRemoteAddr();
		
		try {
			for (CloudDownloadCommand cdc : fileInfo) {
				cdc.setIp(ip);
				byte[] bytes = cloudService.downloadFile(cdc, loginUserId);
				response.setHeader("Content-Disposition", "attachment;filename=cloud.zip");
				response.setContentType("application/octet-stream;charset=utf-8");
				response.setContentLength((int) bytes.length);
				System.out.println("왜터져");
				FileCopyUtils.copy(bytes, response.getOutputStream());
				response.flushBuffer();
			}
		
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	@RequestMapping(value = "/modifyCloudName", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modifyCloudName(@RequestBody CloudModifyCommand cmc, HttpServletRequest request) throws Exception{
		ResponseEntity<String> entity = null;
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		cmc.setUserId(user.getUserId());
		
		String result = "";
		try {
			cloudService.modifyCloudName(cmc);
			result = "success";
		} catch (Exception e) {
			throw new Exception();
		}
		entity = new ResponseEntity<String>(result, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/removeCloud", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> removeCloud(@RequestBody List<CloudRemoveCommand> crcList) throws Exception {
		ResponseEntity<String> entity = null ;
		String result = "";
		try {
			cloudService.removeCloud(crcList);
			System.out.println("controller" + crcList);
			result = "success";
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
//			throw new Exception();
		}
				
		return entity;
	}
}
