package recipe.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.member.MemberDaoImpl;
import recipe.model.member.MemberDto;


@Controller
public class MemberController {
   private Logger log = LoggerFactory.getLogger(getClass());
   @Autowired
   MemberDaoImpl memberDaoImpl;
   /*회원 가입 컨트롤러*/
   @RequestMapping(value="/signcheck",method=RequestMethod.POST)
   public String SignPage(@RequestParam String email,@RequestParam String name,Model model) {
	   log.info("이메일:"+email+"이름:"+name);
	   boolean res = memberDaoImpl.signcheck(email,name);
	   if(res)return "redirect:/login";
	   model.addAttribute("email", email);
	   model.addAttribute("name", name);
      return "member/sign";
   }
   @RequestMapping(value="/sign",method=RequestMethod.POST)
   public String Sign(MemberDto dto) {
      log.info(dto.toString());
      memberDaoImpl.sign(dto);
      return "redirect:/";
   }
   /*회원 정보 컨트롤러*/
   @RequestMapping("/info")
   public String Info(HttpServletRequest request,Model model) {
      Cookie[] cookies = request.getCookies();
      String email = null;
      if(cookies != null) {
         for(int i=0;i<cookies.length;i++) {
            Cookie c = cookies[i];
            String cName = c.getName();
            if(cName.equalsIgnoreCase("memberEmail")) {
               email = c.getValue();
            }
         }
      }
      if(email==null) {return "member/login";}else {
      log.info(email);
      List<MemberDto> dto = memberDaoImpl.info(email);
      model.addAttribute("dto", dto.get(0));
      log.info(dto.get(0).toString());
      return "member/info";}
   }
   /*회원 정보 수정 컨트롤러*/
   @RequestMapping(value="/edit",method=RequestMethod.GET)
   public String EditPage(HttpServletRequest request,Model model) {
      Cookie[] cookies = request.getCookies();
      String email = null;
      if(cookies != null) {
         for(int i=0;i<cookies.length;i++) {
            Cookie c = cookies[i];
            String cName = c.getName();
            if(cName.equalsIgnoreCase("memberEmail")) {
               email = c.getValue();
            }
         }
      }
      List<MemberDto> dto = memberDaoImpl.info(email);
      model.addAttribute("dto", dto.get(0));
      return "member/edit";
   }
   @RequestMapping(value="/edit",method=RequestMethod.POST)
   public String Edit(MemberDto dto) {
      boolean result = memberDaoImpl.edit(dto);
      if(result)log.info("수정 완료");
      else log.info("수정 실패");
      return "redirect:/info";
   }
   /*로그인 페이지 컨트롤러*/
   @RequestMapping(value="/login",method=RequestMethod.GET)
   public String LoginPage() {
      return "member/login";
   }
   @RequestMapping(value="/login",method=RequestMethod.POST)
   public String Login(HttpServletResponse response,@RequestParam String email, @RequestParam String password,HttpSession session) {
   log.info("이메일: "+email+"  비밀번호:"+password);
   String go = "redirect:/";
   boolean result = memberDaoImpl.login(email, password);
   if(!result) {log.info("연결 실패 혹은 계정 없음");}else{log.info("로그인 성공"+result);
   Cookie c = new Cookie("memberEmail",email);
   c.setComment("회원 이메일");
   c.setMaxAge(60*60*24);
   response.addCookie(c);
   if(email.equals("admin@myrecipe.com")) {
	   go = "admin/adminmenu";
   }
   }
      return go;
   }
   /*로그아웃 컨트롤러*/
   @RequestMapping("/logout")
   public String Logout(HttpServletResponse response) {
      // 전체 쿠키 삭제하기
/*       Cookie[] cookies = request.getCookies() ;
        
       if(cookies != null){
           for(int i=0; i < cookies.length; i++){
                
               // 쿠키의 유효시간을 0으로 설정하여 만료시킨다
               cookies[i].setMaxAge(0) ;
                
               // 응답 헤더에 추가한다
               response.addCookie(cookies[i]) ;
           }
       }
        */
       // 특정 쿠키만 삭제하기
       Cookie kc = new Cookie("memberEmail", null) ;
       kc.setMaxAge(0) ;
       response.addCookie(kc) ;

      return "redirect:/";
   }
   @RequestMapping("tac")
   public String Check() {
      return "member/tac";
   }
   @RequestMapping("memberlist")
   public String List(Model model) {
	   List<MemberDto> list = memberDaoImpl.list();
	   model.addAttribute("memberlist",list);
	   return "admin/memberlist";
   }
   @RequestMapping("admin")
   public String admenu() {
	   return "admin/adminmenu";
   }
}