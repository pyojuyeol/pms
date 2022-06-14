package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PdsModifyCommand;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.PdsVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.PdsService;
import kr.or.pms.service.UserService;
import kr.or.pms.util.MakeFileName;
import kr.or.pms.util.ServerToServerFileIo;

@Controller
@RequestMapping("/pds")
public class PdsController {

	@Resource(name = "pdsService")
	private PdsService service;
	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public ModelAndView pdsList(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "pds/list";

		Map<String, Object> dataMap = service.getPdsList(cri);

		@SuppressWarnings("unchecked")
		List<PdsVO> pdsList = (List<PdsVO>) dataMap.get("pdsList");
		for(PdsVO vo : pdsList) {
			vo.setUserNm(userService.getUserNm(vo.getRegister()));
		}
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		String url = "pds/regist";
		return url;
	}

//	@Resource(name="fileUploadPath")
//	private String fileUploadPath;

	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(PdsVO pds, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list";

//		pds.setTitle((String)req.getAttribute("XSStitle"));
//		pds.setContent((String)req.getAttribute("XSScontent"));
		
		UserVO user = (UserVO) req.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		List<AttachVO> attachList = null;
		if(pds.getUploadFile() != null && !pds.getUploadFile().isEmpty()) {
			attachList = ServerToServerFileIo.getAttachesByUploadFiles(userId, pds.getUploadFile());
			pds.setAttachList(attachList);
		}
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		service.regist(pds);
		rttr.addFlashAttribute("from", "regist");

		return url;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(defaultValue = "") String from, 
			@RequestParam(defaultValue = "0") int pdsNo,
			ModelAndView mnv) throws Exception {
		String url = "pds/detail";
		
		PdsVO pds = null;
		if(from != null && from.equals("list")) {
			pds = service.read(pdsNo);
			url = "redirect:/pds/detail.do?pdsNo=" + pdsNo;
		} else {
			pds = service.detail(pdsNo);
		}
			
		List<AttachVO> attachList = null;
		if(pds != null) {
			attachList = pds.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					String fileName = MakeFileName.parseFileNameFromUUID(attach.getNm(), "\\$\\$");
					attach.setNm(fileName);
				}
			}
		}
		
		pds.setUserNm(userService.getUserNm(pds.getRegister()));
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}

	
	@RequestMapping("/modifyForm") 
	public ModelAndView modifyForm(int pdsNo, ModelAndView mnv) throws Exception{ 
		String url = "pds/modify";
		
		PdsVO pds = service.getPds(pdsNo);
		
		if(pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					String fileName = MakeFileName.parseFileNameFromUUID(attach.getNm(), "\\$\\$");
					attach.setNm(fileName);
				}
			}
		}
		
		pds.setUserNm(userService.getUserNm(pds.getRegister()));
		
		mnv.addObject("pds",pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	

	@RequestMapping("/modify")
	public String modifyPost(PdsModifyCommand modifyReq, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list";
		
		String ip = req.getRemoteAddr();
		
		if(modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length > 0) {
			for(String attachSeqStr : modifyReq.getDeleteFile()) {
				int attachSeq = Integer.parseInt(attachSeqStr);
				AttachVO attach = service.getAttachByAttachSeq(attachSeq,ip);
				
				ServerToServerFileIo.removeFile(attach.getAttachPath(), attach.getNm());
				
				service.removeAttachByAttachSeq(attachSeq);
			}
		}
		
		List<AttachVO> attachList = null;
		
		if(modifyReq.getUploadFile() != null && !modifyReq.getUploadFile().isEmpty()) {
			attachList = ServerToServerFileIo.getAttachesByUploadFiles(modifyReq.getRegister(), modifyReq.getUploadFile());
		}
		PdsVO pds = modifyReq.toPdsVO();
		pds.setAttachList(attachList);
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		service.modify(pds);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pdsNo", pds.getPdsNo());

		return url;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list";
		
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				ServerToServerFileIo.removeFile(attach.getAttachPath(), attach.getNm());
			}
		}
		
		service.remove(pno);
		

		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("pno", pno);

		return url;
	}
	
	@RequestMapping("/getFile")
	public String getFile(int attachSeq, Model model,HttpServletRequest request) throws Exception {
		
		String url = "downloadFile";
		
		String ip = request.getRemoteAddr();
		
		AttachVO attach = service.getAttachByAttachSeq(attachSeq,ip);
		String savedPath = attach.getAttachPath();
		String fileName = attach.getStroredNm();
		
		
		byte[] bytes = ServerToServerFileIo.getFile(savedPath, fileName);
		
		model.addAttribute("bytes", bytes);
		model.addAttribute("fileName", fileName);
		
		return url;
		
	}
	
	
}
