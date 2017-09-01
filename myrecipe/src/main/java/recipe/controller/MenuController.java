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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MenuController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private MenuDao mdao;
	
//	메뉴 리스트 이동
	@RequestMapping("menu/menulist")
	public String MenuList() {
		return "menu/menuList";
	}
	
	//메뉴 추가 버튼 클릭시
	@RequestMapping("menu/menuAdd")
	public String MenuAd() {
		return "menu/menuAdd";
	}
	
	//메뉴 추가 기능 수행
	@RequestMapping(value="menu/menuAdd", method=RequestMethod.POST)
	public String MenuAdd(HttpServletRequest req) throws SQLException {
		int no = mdao.add(new MenuDto(req));
		return "redirect:/menuDetail?no="+no;
	}
	
	//메뉴 상세정보로 이동
	@RequestMapping("menu/menuDetail")
	public String MenuDetail(@RequestParam("no") int no, Model model) {
		MenuDto mdto = mdao.info(no);
		model.addAttribute("mdto", mdto);
		return "menu/menuDetail";
	}
	
}
