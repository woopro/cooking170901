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
	private MenuDto mdto;
	
	//메뉴 리스트 이동(일반 권한)
	@RequestMapping("mlist")
	public String MenuList(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		if(type==null) type="name";
		if(key==null) key="";
//		log.info("type : "+type+" / key : "+key);
		List<MenuDto> mdto = mdao.list(type, key);
		model.addAttribute("mdto", mdto);
		
		return "menu/menuList";
	}
	
	//메뉴 관리 리스트 이동(관리자 권한)
	@RequestMapping("mmodi")
	public String MenuModi(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		if(type==null) type="name";
		if(key==null) key="";
//		log.info("type : "+type+" / key : "+key);
		List<MenuDto> mdto = mdao.list(type, key);
		model.addAttribute("mdto", mdto);
		return "admin/menuModi";
	}
	
	//메뉴 추가 버튼 클릭시 page전환(관리자 권한)
	@RequestMapping("madd")
	public String MenuAd() {
		return "menu/menuAdd";
	}
	
	//메뉴 추가 기능 수행
	@RequestMapping(value="madd", method=RequestMethod.POST)
	public String MenuAdd(HttpServletRequest req) throws SQLException {
		int no = mdao.add(new MenuDto(req));
		return "redirect:/mmodi?no="+no;
	}
	
	//메뉴 상세정보 page전환(관리자 권한)
	@RequestMapping("mdetail")
	public String MenuDetail(@RequestParam("no") int no, Model model) {
		mdto = mdao.info(no);
		model.addAttribute("mdto", mdto);
		return "menu/menuDetail";
	}
	
	//메뉴 수정 기능 수행
	@RequestMapping(value="mupdate", method=RequestMethod.POST)
	public String MenuUpdate(@RequestParam("no") int no, HttpServletRequest req) throws SQLException {
		log.debug(no+"번 메뉴 / 수정하러 와쩌염");
		mdao.update(new MenuDto(req));
		
		return "redirect:/mmodi";
	}
	
	//메뉴 삭제 기능 수행
	@RequestMapping("mdelete")
	public String MenuDelete(@RequestParam("no") int no) {
		log.debug("딜리트 와쪄염 (no: "+no+")");
		mdao.delete(no);
		return "redirect:/mmodi";
	}
		
}
