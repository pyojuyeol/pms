package kr.or.pms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import kr.or.pms.command.NoticeModifyCommand;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dto.AttachVO;
import kr.or.pms.dto.HistoryVO;
import kr.or.pms.dto.IssueVO;
import kr.or.pms.dto.NoticeVO;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.dto.UserVO;
import kr.or.pms.service.HistoryService;
import kr.or.pms.service.IssueService;
import kr.or.pms.service.NoticeService;
import kr.or.pms.service.PushService;
import kr.or.pms.service.UserService;
import kr.or.pms.util.MakeFileName;
import kr.or.pms.util.ServerToServerFileIo;

@Controller
@RequestMapping("/project/notice")
public class ProjectNoticeController {
	
	@Resource(name="noticeService")
	private NoticeService service;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
	@Resource(name="pushService")
	private PushService pushService;
	
	@Resource(name="issueService")
	private IssueService issueService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView list(Criteria cri, ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/notice/list";
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		cri.setPrjNo(prjNo);
		
		Map<String, Object> dataMap = service.getList(cri);
		@SuppressWarnings("unchecked")
		List<NoticeVO> noticeList = (List<NoticeVO>) dataMap.get("noticeList");
		for(NoticeVO vo : noticeList) {
			vo.setUserNm(userService.getUserNm(vo.getRegister()));
		}
		
		
		mnv.addObject("prjNo", prjNo);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/registForm")
	public ModelAndView registForm(int prjNo, ModelAndView mnv) throws Exception {
		
//		String url = "project/notice/regist?prjNo="+prjNo;
		String url = "project/notice/regist";
		
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		mnv.addObject("prjNo", prjNo);
		mnv.setViewName(url);
		return mnv;
		
	}
	
	@Resource(name = "fileUploadPath")
	public String fileUploadPath;
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(NoticeVO notice,HttpSession session, RedirectAttributes rttr) throws Exception {
		
		int prjNo = (int) session.getAttribute("currentPrjNo");
		String url = "redirect:/project/notice/list.do?prjNo="+prjNo;
		
		int ntcNo = service.selectNoticeSeqNext();
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		notice.setNtcNo(ntcNo);
		notice.setPrjNo(prjNo);
//		String savePath = this.fileUploadPath;
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		List<AttachVO> attachList = null;
		if(notice.getUploadFile() != null && !notice.getUploadFile().isEmpty()) {
			attachList = ServerToServerFileIo.getAttachesByUploadFiles(userId, notice.getUploadFile());
			notice.setAttachList(attachList);
		}
		service.regist(notice,loginUser);
		
		// 히스토리 등록
		HistoryVO history = new HistoryVO();
		history.setUserId(notice.getRegister());
		history.setPrjNo(prjNo);
		history.setFromWhere("공지");
		history.setUrl("/project/notice/detail.do?ntcNo="+ntcNo);
		history.setTitle(loginUser.getUserNm() +" 님이 "+notice.getTitle()+" 공지사항을 등록하였습니다.");
		history.setContent(notice.getContent());
		
		historyService.regist(history);
		
		/*
		 * List<UserVO> userList = service.selectAllUser();
		 * 
		 * PushVO push = new PushVO(); for(UserVO user : userList) {
		 * push.setReceiver(user.getUserNm()); push.setFromWhere("schedule");
		 * push.setPrjNo(Integer.toString(prjNo));
		 * push.setUrl("/project/notice/detail.do?ntcNo="+notice.getNtcNo());
		 * push.setMessage(loginUser.getUserNm()+" 님이 "+notice.getTitle()
		 * +" 공지사항을 등록하였습니다."); pushService.regist(push); }
		 */
		
		rttr.addFlashAttribute("from", "regist");
		return url;
		
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int ntcNo, String from, ModelAndView mnv, HttpSession session) throws Exception {
		
		String url = "project/notice/detail";
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
		
		NoticeVO notice = service.getNotice(ntcNo);
		
		if(from != null && from.equals("list")) {
			notice = service.read(ntcNo);
			url = "redirect:/project/notice/detail.do?ntcNo=" + ntcNo;
		} else {
			notice = service.getNotice(ntcNo);
		}
		
		if(notice == null) {
			mnv.addObject("message","삭제된 페이지 입니다.");
			url = "security/fail";
		} else {
			mnv.addObject("notice", notice);
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
			
			notice.setUserNm(userService.getUserNm(notice.getRegister()));
		} 
		
		
//		mnv.addObject("attachList", attachList);
		mnv.setViewName(url);
		
		return mnv;
		
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int ntcNo, HttpSession session) throws Exception {
		
		String url = "project/notice/modify";
		
		NoticeVO notice = service.getNotice(ntcNo);
		
		int prjNo = (int)session.getAttribute("currentPrjNo");
		
		IssueVO issue = issueService.getprjName(prjNo);
		mnv.addObject("prjNm", issue.getPrjNm());
		
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
		
		String ip = request.getRemoteAddr();
		
		String url = "redirect:/project/notice/detail.do";
		UserVO loginUser = (UserVO) request.getSession().getAttribute("loginUser");
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
		
//		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(modifyReq.getUploadFile(), fileUploadPath);
//		List<AttachVO> attachList = ServerToServerFileIo.getAttachesByUploadFiles(modifyReq.getRegister(), modifyReq.getUploadFile());
		List<AttachVO> attachList = null;
		if (modifyReq.getUploadFile() != null && !modifyReq.getUploadFile().isEmpty()) {
			attachList = ServerToServerFileIo.getAttachesByUploadFiles(modifyReq.getRegister(), modifyReq.getUploadFile());
		}
		
		NoticeVO notice = modifyReq.toNoticeVO();
				
		notice.setAttachList(attachList);
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		
		
		service.modify(notice);
		
		HistoryVO history = new HistoryVO();
		history.setUserId(notice.getRegister());
		history.setPrjNo(notice.getPrjNo());
		history.setFromWhere("notice");
		history.setUrl("/project/notice/detail.do?ntcNo="+notice.getNtcNo());
		history.setTitle(loginUser.getUserNm()+" 님이 "+notice.getTitle()+" 공지사항을 수정하였습니다.");
		history.setContent(notice.getContent());
		
		historyService.regist(history);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("ntcNo", notice.getNtcNo());
		
		return url;
		
	}
	
	@RequestMapping("/remove")
	public String remove(int ntcNo, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		String url = "redirect:/project/notice/list.do";
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
				
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
		
		NoticeVO notice = service.getNotice(ntcNo);
		
		HistoryVO history = new HistoryVO();
		history.setUserId(notice.getRegister());
		history.setPrjNo(notice.getPrjNo());
		history.setFromWhere("notice");
		history.setUrl("/project/notice/list.do?prjNo="+notice.getPrjNo());
		history.setTitle(loginUser.getUserNm()+"님이 "+notice.getTitle()+" 공지사항을 삭제하였습니다.");
		history.setContent(notice.getContent());
		
		historyService.regist(history);
		
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
