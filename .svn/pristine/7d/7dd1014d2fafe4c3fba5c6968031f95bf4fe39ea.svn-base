package kr.or.pms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.pms.command.AdminLogCriteria;
import kr.or.pms.dto.DbcnVO;
import kr.or.pms.scheduler.RemoveUnusedFileScheduler;
import kr.or.pms.service.LogService;

@Controller
@RequestMapping("/admin/log")
public class AdminLogController {
	
	@Autowired
	private LogService logService;
	@Autowired
	private RemoveUnusedFileScheduler removeUnusedFileScheduler;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void getMain(AdminLogCriteria adminCri, Model model) throws Exception {
		Map<String, Object> logData = null; 
		if (adminCri.getLogType().equals("connectLog")) {
			logData = logService.getConnectLog(adminCri);
		}
		
		model.addAttribute("logData", logData);
	}
	
	@RequestMapping(value = "/systemLog", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getSystemSchedulerLog(@RequestBody AdminLogCriteria adminCri) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> logData = null;
		try {
			logData = logService.getSystemSchedulerLog(adminCri);
			entity = new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/fileLog", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getFileLog(@RequestBody AdminLogCriteria adminCri) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> logData = null;
		try {
			logData = logService.getFileLog(adminCri);
			entity = new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/connectLog", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getConnectLog(@RequestBody AdminLogCriteria adminCri, Model model) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> logData = null;
		try {
			logData = logService.getConnectLog(adminCri);
			entity = new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value = "/dbcnLog", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getDbcnLog(@RequestBody AdminLogCriteria adminCri, Model model) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> logData = null;
		try {
			logData = logService.getDbcnLog(adminCri);
			entity = new ResponseEntity<Map<String, Object>>(logData, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping("/schedulerExec")
	@ResponseBody
	public ResponseEntity<String> test(DbcnVO dbcnVO) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String message = "";
		
		removeUnusedFileScheduler.removeUnusedFile();
		message = "success";
		entity = new ResponseEntity<String>(message, HttpStatus.OK);	
		return entity;
		
	}
}

