package recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.menu.MenuDao;
import recipe.model.menu.MenuDto;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MenuController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private MenuDao mdao;
	
//	메뉴 리스트 이동
	@RequestMapping("mlist")
	public String MenuList(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		
		if(type==null) type="name";
		if(key==null) key="";
		
		log.debug("type : "+type+" / key : "+key);
		
		List<MenuDto> mdto = mdao.list(type, key);
		model.addAttribute("mdto", mdto);
		
		log.debug("리스트 반환 완료");
		return "menu/menuList";
	}
	
	//메뉴 추가 버튼 클릭시
	@RequestMapping("madd")
	public String MenuAd() {
		return "menu/menuAdd";
	}
	
	//메뉴 추가 기능 수행
	@RequestMapping(value="madd", method=RequestMethod.POST)
	public String MenuAdd(HttpServletRequest req) throws SQLException {
		int no = mdao.add(new MenuDto(req));
		return "redirect:/mDetail?no="+no;
	}
	
	//메뉴 상세정보로 이동
	@RequestMapping("mdetail")
	public String MenuDetail(@RequestParam("no") int no, Model model) {
		MenuDto mdto = mdao.info(no);
		model.addAttribute("mdto", mdto);
		return "menu/menuDetail";
	}
	
}
