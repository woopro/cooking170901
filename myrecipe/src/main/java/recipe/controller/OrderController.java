package recipe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import recipe.model.menu.MenuDao;
import recipe.model.menu.MenuDto;
import recipe.model.order.Order;

@Controller
public class OrderController {
	private Logger log = LoggerFactory.getLogger(getClass());

	// 주문페이지
	@RequestMapping("/order")
	public String order() {
		return "order/order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(int menu_cnt, Model m) {
		m.addAttribute("menu_cnt", menu_cnt);
		return "redirect:write";
	}

	// 주문 내역 확인 페이지
	@RequestMapping("/orderlist")
	public String orderList() {
		return "order/orderlist";
	}

	// 주문서 작성 페이지
	@RequestMapping("/write")
	public String write(int menu_cnt, Model m) {
		m.addAttribute("menu_cnt", menu_cnt);
		return "order/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session) {
		// request정보를 session에 저장해서 session으로 넘겨준 후, 다시 삭제!

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

}
