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

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.pms.command.Criteria;
import kr.or.pms.command.PageMaker;
import kr.or.pms.dto.ReplyVO;
import kr.or.pms.service.PdsReplyService;

@Controller
@RequestMapping("/pds/replies")
public class PdsReplyController {

	@Autowired
	private PdsReplyService service;
	
	@RequestMapping(value = "/{pno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> pdsReplyList(@PathVariable("pno") int pdsNo, @PathVariable("page") int page) throws Exception{

		ResponseEntity<Map<String, Object>> entity = null;

		Criteria cri = new Criteria();
		cri.setPage(page);

		Map<String, Object> dataMap = service.selectPdsReplyList(pdsNo, cri);
		entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		return entity;

	}
	
	@RequestMapping(value = "/regist/{pno}", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply, @PathVariable("pno") int pdsNo) throws Exception {
		ResponseEntity<String> entity = null;
		reply.setContent(HTMLInputFilter.htmlSpecialChars(reply.getContent()));
		
		service.addPdsReply(reply, pdsNo);
		
		Criteria cri = new Criteria();
		
		Map<String, Object> dataMap = service.selectPdsReplyList(reply.getReplyNo(), cri);

		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
		int realEndPage = pageMaker.getRealEndPage();
		
		System.out.println(realEndPage);
		
		entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);
		
		return entity;
	}
	
	@RequestMapping(value = "/modify/{replySeq}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply) throws Exception {

		ResponseEntity<String> entity = null;

		System.out.println(reply.getContent());
		
		service.modifyReply(reply);
		
		entity = new ResponseEntity<String>(HttpStatus.OK);

		return entity;
	}

	@RequestMapping(value = "/remove/{pno}/{replySeq}/{page}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> remove
	(@PathVariable("replySeq") int replySeq, @PathVariable("pno") int pdsNo,
	@PathVariable("page") int page) throws Exception {

		ResponseEntity<String> entity = null;

		// System.out.println(reply.getContent());
		
		service.removeReply(replySeq);
		
		Criteria cri = new Criteria();

		Map<String, Object> dataMap = service.selectPdsReplyList(pdsNo, cri);
		PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");

		int realEndPage = pageMaker.getRealEndPage();
		if (page > realEndPage) {
			page = realEndPage;
		}

		entity = new ResponseEntity<String>("" + page, HttpStatus.OK);


		return entity;
	}
	
}
