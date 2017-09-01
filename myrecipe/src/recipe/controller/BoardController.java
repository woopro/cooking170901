package recipe.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.board.BoardDao;
import recipe.model.board.BoardDto;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao bdao;
	
	@RequestMapping("/blist")
	public String list(Model model) {
		List<BoardDto> list = bdao.list();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping("/bwrite")
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="bwrite", method=RequestMethod.POST)
	public String write(HttpServletRequest request) {
		int no = bdao.write(new BoardDto(request));
		return "board/info?no="+no;
	}
	
	@RequestMapping("/binfo")
	public String info(@RequestParam("no") int no, Model model) {
		BoardDto bdto = bdao.info(no);
		model.addAttribute("bdto", bdto);
		return "board/info";
	}
}















