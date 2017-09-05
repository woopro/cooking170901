package recipe.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.board.BoardDao;
import recipe.model.board.BoardDto;
import recipe.model.member.MemberDao;
import recipe.model.member.MemberDto;

@Controller
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardDao bdao;
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/blist")
	public String list(HttpServletRequest request, Model model) {
		String ckValue=null;
		Cookie[] ck =  request.getCookies();
		if(ck != null) {
			for(Cookie c : ck) {
//				log.info("쿠키 이름 : "+c.getName());
				if(c.getName().equals("memberEmail")) {
					ckValue = c.getValue();
//					log.info("쿠키 값  = "+ckValue);
					model.addAttribute("ckValue", ckValue);
					break;
				}
			}
		}
		String type = request.getParameter("type");
		String key = request.getParameter("key");
//		log.info("type = "+type);
//		log.info("key = "+key);
		String pageStr = request.getParameter("page");
		int pageNo;
		try {
			pageNo = Integer.parseInt(pageStr);
			if(pageNo <= 0) throw new Exception();
		}catch(Exception e) {
			pageNo=1;
		}
		
		int boardSize = 10;
		int boardCount = bdao.count(type, key);
		
//		System.out.println("boardCount = "+boardCount);
		int start = boardSize * pageNo - boardSize+1;
		int end = start+boardSize-1;
		if(end > boardCount) end = boardCount;
		
		
		List<BoardDto> list = bdao.list(type, key, start, end);
		
		
		int blockSize = 10;
		int blockTotal = (boardCount + boardSize -1)/boardSize;
		int startBlock = (pageNo -1) / blockSize * blockSize+1;
		int endBlock = startBlock + blockSize -1;
		if(endBlock > blockTotal) endBlock = blockTotal;
		
		String url = "blist?";
		if(type!=null && key!=null) 
			url += "type="+type+"&key="+key;
		
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("url", url);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("endBlock", endBlock);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("blockTotal", blockTotal);
		
//		if(type==null) type="name";
//		if(key==null) key="";
		
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping("/me")
	public String list(@RequestParam("email") String email, Model model) {
		List<BoardDto> list = bdao.myQnA(email);
		model.addAttribute("list", list);
		return "board/list";
	}

	
	@RequestMapping("/bwrite")
	public String write(HttpServletRequest request, Model model) {
		String ckValue=null;
		Cookie[] ck =  request.getCookies();
		if(ck != null) {
			for(Cookie c : ck) {
				if(c.getName().equals("memberEmail")) {
					ckValue = c.getValue();
					List<MemberDto> mdto = memberDao.info(ckValue);
				    model.addAttribute("mdto", mdto.get(0));
					model.addAttribute("ckValue", ckValue);
					break;
				}
			}
		}
		return "board/write";
	}
	
	@RequestMapping(value="/bwrite", method=RequestMethod.POST)
	public String write(HttpServletRequest request) {
		int no = bdao.write(new BoardDto(request));
		return "redirect:/binfo?no="+no;
	}
	
	@RequestMapping("/binfo")
	public String info(@RequestParam("no") int no, Model model) {
		bdao.read(no);
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		model.addAttribute("pw", bdto.getPw());
		return "board/info";
	}
	
	@RequestMapping("/bedit")
	public String edit(@RequestParam("no") int no,
					   Model model) {
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		model.addAttribute("no", no);
		return "board/bedit";
	}
	
	@RequestMapping(value="/bedit", method=RequestMethod.POST)
	public String edit(HttpServletRequest request,
					   @RequestParam("board_no") int num) {
		int no = bdao.edit(new BoardDto(request));
		return "redirect:binfo?no="+no;
	}
	
	@RequestMapping("/bdelete")
	public String delete(@RequestParam("no") int no, Model model) {
		bdao.delete(no);
		return "redirect:/blist";
	}
}















