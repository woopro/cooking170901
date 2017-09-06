package recipe.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.member.MemberDaoImpl;
import recipe.model.member.MemberDto;
import recipe.model.menu.MenuDaoImpl;
import recipe.model.menu.MenuDto;
import recipe.model.order.Order;
import recipe.model.order.OrderDaoImpl;

@Controller
public class OrderController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDaoImpl orderDao;

	@Autowired
	private MemberDaoImpl memberDao; // 멤버정보 확인
	
	@Autowired
	private MenuDaoImpl menuDao; //메뉴정보 확인

	// 주문페이지
	@RequestMapping("/order")
	public String order(@RequestParam int no, Model m) {
		m.addAttribute("no", no); //메뉴번호 전송
		return "order/order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(int menu_cnt, Model m, HttpServletRequest request, int no_menu) {
		// 로그인 여부 확인
		Cookie[] list = request.getCookies();
		for (Cookie c : list) {
			if (c.getName().equals("memberEmail")) {
				m.addAttribute("menu_cnt", menu_cnt);
				m.addAttribute("no_menu", no_menu);
				return "redirect:write";
			}
		}
		return "member/login";
	}

	// 주문 내역 확인 페이지
	@RequestMapping("/orderlist")
	public String orderList(HttpServletRequest request,Model m) {
		Cookie[] list = request.getCookies();
		String email = null;
		for (Cookie c : list) {
			if (c.getName().equals("memberEmail")) {
				email = c.getValue();
			}
		}
		MemberDto memberInfo = memberDao.info(email).get(0);
//		List<Order> orderList = orderDao.orderList(memberInfo.getNo());
		Order orderInfo = orderDao.orderList(memberInfo.getNo());
		MenuDto menuInfo = menuDao.info(orderInfo.getNo_menu());
		
		m.addAttribute("orderInfo", orderInfo);
		m.addAttribute("memberInfo", memberInfo);
		m.addAttribute("menuInfo", menuInfo);
		
		
		return "order/orderlist";
	}

	// 주문서 작성 페이지
	@RequestMapping("/write")
	public String write(HttpServletRequest request, int menu_cnt, Model m
			,int no_menu) {

		String email = null; // 이메일 정보 저장
		Cookie[] list = request.getCookies();
		for (Cookie c : list) {
			if (c.getName().equals("memberEmail")) {
				email = c.getValue();
				break;
			}
		}

		if (email != null) {
			MemberDto memberInfo = memberDao.info(email).get(0);
			MenuDto menuInfo = menuDao.info(no_menu);
			
			//최근배송지 정보 첨부
			Order recentOrder = orderDao.orderList(memberInfo.getNo());
			m.addAttribute("recentOrder", recentOrder);
			
			m.addAttribute("memberInfo", memberInfo);//멤버 정보
			m.addAttribute("menuInfo", menuInfo);//메뉴 정보 전송
		}
		m.addAttribute("menu_cnt", menu_cnt);
		
		return "order/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session, int no_member,int no_menu,
			int menu_cnt, int menu_price) {

		// request정보를 session에 저장해서 session으로 넘겨준 후, 다시 삭제!
		Order order = new Order();
		order.setNo_member(no_member);
		order.setNo_menu(no_menu);// 메뉴 번호(임시)
		order.setOrder_name(request.getParameter("name"));
		order.setTel(request.getParameter("tel"));
		order.setMenu_cnt(menu_cnt);
		order.setPost(request.getParameter("post"));
		order.setAddr1(request.getParameter("addr1"));
		order.setAddr2(request.getParameter("addr2"));
		order.setWant_date(request.getParameter("want_date"));
		order.setComments(request.getParameter("comments"));
		order.setTotalprice(Integer.parseInt(request.getParameter("totalprice")));

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

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String pay(HttpSession session, Model m, HttpServletRequest request) throws Exception {
		
		Order orderInfo = (Order) session.getAttribute("orderInfo");

		if (request.getParameter("pay") != null) {
			
			boolean result = orderDao.order(orderInfo);
			if (result) 
				return "redirect:orderlist";
			 else
				throw new Exception("결제중 오류");

		}
		// 결제 취소하면 session 정보 삭제
		else if(request.getParameter("cancle") != null){
			session.removeAttribute("orderInfo");
			String returnUrl = "redirect:write?menu_cnt="+orderInfo.getMenu_cnt()+"&no_menu="+orderInfo.getNo_menu();
			return returnUrl;
		}else 
			throw new Exception("결제중 오류");
	}
	@RequestMapping("/olist")
	public String olist(Model model) {
		List<Order> olist = orderDao.olist();
		model.addAttribute("olist", olist);
		return "admin/orderlist";
	}
	@RequestMapping("/complete")
	public String complete(Model model,@RequestParam int orderNo) {
		orderDao.complete(orderNo);
		List<Order> olist = orderDao.olist();
		model.addAttribute("olist", olist);
		return "admin/orderlist";
	}
}
