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
		
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		
		if(type==null) type="name";
		if(key==null) key="";
		
		List<BoardDto> list = bdao.list(type, key);
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
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		model.addAttribute("pw", bdto.getPw());
		return "board/info";
	}
	

	@RequestMapping("/pw")
	public String checkpw(@RequestParam("next") String next, 
						  @RequestParam("no") int no, Model model) {
		model.addAttribute("next", next);
		model.addAttribute("no", no);
		model.addAttribute("fail", "no");
		return "board/pw";
	}
	
	@RequestMapping(value="/pw", method=RequestMethod.POST)
	public String checkPw(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("board_no"));
		String next = request.getParameter("next");
		String pw = request.getParameter("pw");
		boolean result = bdao.checkpw(no, pw);
		if(result) {
			model.addAttribute("success", "yes");
			model.addAttribute("next", next);
			return "board/pw";
		}else {
			model.addAttribute("fail", "yes");
			return "redirect:/pw?next="+next+"&no="+no;
		}
	}
	
	@RequestMapping("/bedit")
	public String edit() {
		return "board/bedit";
	}
	
	@RequestMapping("/bdelete")
	public String delete() {
		return "board/bdelete";
	}
}















