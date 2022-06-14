package kr.or.pms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.command.ReplyRegistCommand;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.service.ReplyService;

@Controller
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "/{workNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> workReplyList(@PathVariable("workNo") int workNo,
															 @PathVariable("page") int page) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		
		Map<String, Object> dataMap = replyService.getWorkReplyList(workNo, cri);
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ResponseEntity<String> regist(@RequestBody ReplyRegistCommand registCMD) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		ReplyVO reply = registCMD.toReplyVO();

		replyService.registReply(reply);
		
		Criteria cri = new Criteria();
		
		Map<String, Object> dataMap = replyService.getWorkReplyList(reply.getReplyNo(), cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		int realEndPage = pageMaker.getRealEndPage();
		
		entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/modify/{replySeq}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@PathVariable("replySeq") int replySeq,
										 @RequestBody ReplyVO reply) throws Exception {
		ResponseEntity<String> entity = null;
		
		System.out.println(reply.getContent());
		
		reply.setReplySeq(replySeq);
		replyService.modifyReply(reply);
		
		entity = new ResponseEntity<String>(HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/remove/{workNo}/{replySeq}/{page}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> remove(@PathVariable("workNo") int workNo,
										 @PathVariable("replySeq") int replySeq,
										 @PathVariable("page") int page) throws Exception {
		ResponseEntity<String> entity = null;
		
		replyService.removeReply(replySeq);
		
		Criteria cri = new Criteria();
		Map<String, Object> dataMap = replyService.getWorkReplyList(workNo, cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");

		int realEndPage = pageMaker.getRealEndPage();
		if (page > realEndPage) {
			page = realEndPage;
		}

		entity = new ResponseEntity<String> ("" + page, HttpStatus.OK);
		
		return entity;
	}
	
}
