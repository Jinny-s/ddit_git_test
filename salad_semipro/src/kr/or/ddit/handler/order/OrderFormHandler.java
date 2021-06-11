package kr.or.ddit.handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.CartVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;
import kr.or.ddit.service.OrderService;
import kr.or.ddit.service.OrderdetailService;

public class OrderFormHandler implements Handler {
	
	private OrderService orderService;
	private OrderdetailService orderdetailService;
	private CartService cartService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setOrderdetailService(OrderdetailService orderdetailService) {
		this.orderdetailService = orderdetailService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "order/order";

		int cno = Integer.parseInt(request.getParameter("cno"));
		int price = Integer.parseInt(request.getParameter("price"));
		request.setAttribute("price", price);
		
		CartVO cart = null;
		try {
			cart = cartService.getCartByCno(cno);
			request.setAttribute("cart", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
}
