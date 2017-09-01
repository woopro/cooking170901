package recipe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.member.MemberDaoImpl;

@Controller
public class MemberController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	MemberDaoImpl memberDaoImpl;
	/*회원 가입 컨트롤러*/
	@RequestMapping(value="/sign",method=RequestMethod.GET)
	public String SignPage() {
		return "member/sign";
	}
	@RequestMapping(value="/sign",method=RequestMethod.POST)
	public String Sign() {
		
		return "redirect:/";
	}
	/*회원 정보 컨트롤러*/
	@RequestMapping("/info")
	public String Info(Model model) {
		
		return "member/info";
	}
	/*회원 정보 수정 컨트롤러*/
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String EditPage() {
		
		return "member/edit";
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String Edit() {
		
		return "member/info";
	}
	/*로그인 페이지 컨트롤러*/
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String LoginPage() {
		return "member/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String Login(@RequestParam String email, @RequestParam String password) {
	log.info("이메일: "+email+"  비밀번호:"+password);	
	String result = memberDaoImpl.login(email, password);
	if(result==null) {log.info("연결 실패 혹은 계정 없음");}else{log.info("로그인 성공"+result);}
		return "/home";
	}
}
