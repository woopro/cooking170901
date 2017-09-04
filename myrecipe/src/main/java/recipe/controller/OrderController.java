package recipe.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
import recipe.model.menu.MenuDao;
import recipe.model.menu.MenuDto;
import recipe.model.order.OrderDaoImpl;

@Controller
public class OrderController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderDaoImpl orderDao;
	
	@Autowired
	private MemberDaoImpl memberDao; //멤버정보 확인

	// 주문페이지
	@RequestMapping("/order")
	public String order() {
		return "order/order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(int menu_cnt, Model m, HttpServletRequest request) {
		
		//로그인 여부 확인
		Cookie[] list = request.getCookies();
		for(Cookie c : list) {
			if(c.getName().equals("memberEmail")) {
				m.addAttribute("menu_cnt", menu_cnt);
				return "redirect:write";
			}
		}
		return "member/login";
	}

	// 주문 내역 확인 페이지
	@RequestMapping("/orderlist")
	public String orderList() {
		return "order/orderlist";
	}

	// 주문서 작성 페이지
	@RequestMapping("/write")
	public String write(HttpServletRequest request, int menu_cnt, Model m) {
		
		String email = null; //이메일 정보 저장
		Cookie[] list = request.getCookies();
		for(Cookie c : list) {
			if(c.getName().equals("memberEmail")) {
				email = c.getValue();
				break;
			}
		}
		
		if(email != null) {
			MemberDto memberInfo = memberDao.info(email).get(0);
			m.addAttribute("memberInfo", memberInfo);
		}
		m.addAttribute("menu_cnt", menu_cnt);
		return "order/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session,
			int no_member, int menu_cnt) {
		
		// request정보를 session에 저장해서 session으로 넘겨준 후, 다시 삭제!
		Order order = new Order();
		order.setNo_member(no_member);
		order.setNo_menu(1);//메뉴 번호(임시)
		order.setName(request.getParameter("name"));
		order.setTel(request.getParameter("tel"));
		order.setMenu_cnt(menu_cnt);
		order.setOp1_cnt(0);
		order.setOp2_cnt(0);
		order.setOp3_cnt(0);
		order.setPost(request.getParameter("post"));
		order.setAddr1(request.getParameter("addr1"));
		order.setAddr2(request.getParameter("addr2"));
		order.setWant_date(request.getParameter("want_date"));
		order.setOrder_date("test");
		order.setReal_date("test");
		order.setStat("test");
		order.setComments(request.getParameter("comments"));
		
		session.setAttribute("orderInfo", order);
		
		return "redirect:pay";
	}

	// 장바구니 페이지
	@RequestMapping("/cart")
	public String cart() {
		return "order/cart";
	}

	// 결제페이지
	@RequestMapping("/pay")
	public String pay() {
		return "order/pay";
	}
	
	@RequestMapping(value="/pay", method=RequestMethod.POST)
	public String pay(HttpSession session) throws Exception {
		Order orderInfo = (Order) session.getAttribute("orderInfo");
		log.info(orderInfo.toString());
		
		boolean result = orderDao.order(orderInfo);
		if(result)
			return "order/orderlist";
		else
			throw new Exception("결제중 오류");
	}

}
