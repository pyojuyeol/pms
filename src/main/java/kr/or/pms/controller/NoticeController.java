package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.NoticeCriteria;
import kr.or.pms.command.NoticeModifyCommand;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.CloudService;
import kr.or.pms.service.NoticeService;
import kr.or.pms.service.UserService;
import kr.or.pms.util.MakeFileName;
import kr.or.pms.util.ServerToServerFileIo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Resource(name="noticeService")
	private NoticeService service;
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public ModelAndView list(NoticeCriteria cri, ModelAndView mnv, String type) throws Exception {
		String url = "notice/list";
		Map<String, Object> dataMap = null;
		
		if (cri.getPageType() == null || cri.getPageType().equals("") || cri.getPageType().equals("news")) {
			dataMap = service.getListNews(cri);
			List<NoticeVO> newsList = (List<NoticeVO>) dataMap.get("newsList");
			for(NoticeVO vo : newsList) {
				vo.setUserNm(userService.getUserNm(vo.getRegister()));
			}
			dataMap.put("newsList", newsList);
			mnv.addObject("pageType", "news");
		} else if (cri.getPageType().equals("ntc")) {
			dataMap = service.getListNtc(cri);
			List<NoticeVO> ntcList = (List<NoticeVO>) dataMap.get("ntcList");
			for(NoticeVO vo : ntcList) {
				vo.setUserNm(userService.getUserNm(vo.getRegister()));
			}
			dataMap.put("ntcList", ntcList);
			mnv.addObject("pageType", "ntc");
		} else {
			dataMap = service.getListEtc(cri);
			List<NoticeVO> etcList = (List<NoticeVO>) dataMap.get("etcList");
			for(NoticeVO vo : etcList) {
				vo.setUserNm(userService.getUserNm(vo.getRegister()));
			}
			dataMap.put("etcList", etcList);
			mnv.addObject("pageType", "etc");
		}
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("registForm")
	public String registForm() throws Exception {
		
		String url = "notice/regist";
		return url;
		
	}
	
	@Resource(name = "fileUploadPath")
	public String fileUploadPath;
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(NoticeVO notice, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/notice/list.do";
		
//		String savePath = this.fileUploadPath;
		UserVO user = (UserVO) request.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		List<AttachVO> attachList = null;
		if (notice.getUploadFile() != null && !notice.getUploadFile().isEmpty()) {
			 attachList = ServerToServerFileIo.getAttachesByUploadFiles(userId, notice.getUploadFile());
			 notice.setAttachList(attachList);
		}
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		service.regist(notice,user);
		
		rttr.addFlashAttribute("from", "regist");
		return url;
		
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int ntcNo, String from, ModelAndView mnv) throws Exception {
		
		String url = "notice/detail";
		
		NoticeVO notice = null;
		if(from != null && from.equals("list")) {
			notice = service.read(ntcNo);
			url = "redirect:/notice/detail.do?ntcNo=" + ntcNo;
		} else {
			notice = service.getNotice(ntcNo);
		}
		
		
		List<AttachVO> attachList = null;
		if(notice != null) {
			attachList = notice.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					String fileName = MakeFileName.parseFileNameFromUUID(attach.getNm(), "\\$\\$");
					attach.setNm(fileName);
				}
			}
		}
		
		notice.setUserNm(userService.getUserNm(notice.getRegister()));
		
//		mnv.addObject("attachList", attachList);
		mnv.addObject("notice", notice);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int ntcNo) throws Exception {
		
		String url = "notice/modify";
		
		NoticeVO notice = service.getNotice(ntcNo);
		
		if(notice != null) {
			List<AttachVO> attachList = notice.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					String fileName = MakeFileName.parseFileNameFromUUID(attach.getNm(), "\\$\\$");
					attach.setNm(fileName);
				}
			}
		}
		
		notice.setUserNm(userService.getUserNm(notice.getRegister()));
		
		mnv.addObject("notice", notice);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(NoticeModifyCommand modifyReq, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/notice/detail.do";
		
		String ip = request.getRemoteAddr();
		
		if(modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length > 0) {
			for(String attachSeqStr : modifyReq.getDeleteFile()) {
				int attachSeq = Integer.parseInt(attachSeqStr);
				AttachVO attach = service.getAttachByAttachSeq(attachSeq,ip);
				
//				File deleteFile = new File(attach.getAttachPath(), attach.getNm());
				
				ServerToServerFileIo.removeFile(attach.getAttachPath(), attach.getNm());
//				if(deleteFile.exists()) {
//					deleteFile.delete();
//				}
				service.removeAttachByAttachSeq(attachSeq);
			}
		}
		
//		List<AttachVO> addList = service.getAttachByAttachNo(modifyReq.getAttachNo());
		List<AttachVO> attachList = null;
		
//		notice = service.getNotice(notice.getNtcNo());
		
		if(modifyReq.getUploadFile() != null && !modifyReq.getUploadFile().isEmpty()) {
			attachList = ServerToServerFileIo.getAttachesByUploadFiles(modifyReq.getRegister(), modifyReq.getUploadFile());
		}
		
		NoticeVO notice = modifyReq.toNoticeVO();
		
//		List<AttachVO> joinedList = new ArrayList<AttachVO>();
//		joinedList.addAll(attachList);
//		joinedList.addAll(addList);
		
		notice.setAttachList(attachList);
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		
		service.modify(notice);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("ntcNo", notice.getNtcNo());
		
		return url;
		
	}
	
	@RequestMapping("/remove")
	public String remove(int ntcNo, RedirectAttributes rttr) throws Exception {
		
		String url = "redirect:/notice/list.do";
		
		List<AttachVO> attachList = service.getNotice(ntcNo).getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				ServerToServerFileIo.removeFile(attach.getAttachPath(), attach.getNm());
//				File target = new File(attach.getAttachPath(), attach.getNm());
//				if(target.exists()) {
//					target.delete();
//				}
			}
		}
		
		service.remove(ntcNo);
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("ntcNo", ntcNo);
		
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
	
	@RequestMapping(value = "/replies/{replyNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("replyNo") int replyNo, @PathVariable("page") int page)
			throws Exception {

		ResponseEntity<Map<String, Object>> entity = null;

		Criteria cri = new Criteria();
		cri.setPage(page);

		Map<String, Object> dataMap = service.getReplyList(replyNo, cri);
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);

		return entity;

	}
	
	@RequestMapping(value = "/replies", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply) throws Exception {

		ResponseEntity<String> entity = null;
		NoticeVO notice = new NoticeVO();
		notice = service.getNotice(Integer.parseInt(reply.getSttCode()));
		
		reply.setContent(HTMLInputFilter.htmlSpecialChars(reply.getContent()));
		
		if(notice.getReplyNo() == 0) {
			int replyNo = service.selectReplySeqNextValue();
			reply.setReplyNo(replyNo);
		} else {
			reply.setReplyNo(notice.getReplyNo());
		}
		
		service.registReply(reply);
		notice.setReplyNo(reply.getReplyNo());
		service.insertReplyNoToNotice(notice);
		
		Criteria cri = new Criteria();

		Map<String, Object> dataMap = service.getReplyList(reply.getReplyNo(), cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		int realEndPage = pageMaker.getRealEndPage();

		entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);

		return entity;
	}
	
	@RequestMapping(value = "/replies/{replySeq}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply) throws Exception {

		ResponseEntity<String> entity = null;

		service.modifyReply(reply);
		entity = new ResponseEntity<String>(HttpStatus.OK);

		return entity;

	}
	
	@RequestMapping(value = "/replies/{replyNo}/{replySeq}/{page}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("replySeq") int replySeq, @PathVariable("replyNo") int replyNo,
			@PathVariable("page") int page) throws Exception {
		ResponseEntity<String> entity = null;

		service.removeReply(replySeq);

		Criteria cri = new Criteria();

		Map<String, Object> dataMap = service.getReplyList(replyNo, cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");

		int realEndPage = pageMaker.getRealEndPage();
		if (page > realEndPage) {
			page = realEndPage;
		}

		entity = new ResponseEntity<String>("" + page, HttpStatus.OK);

		return entity;
	}
	
}
