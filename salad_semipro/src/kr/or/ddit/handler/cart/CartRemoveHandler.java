package kr.or.ddit.handler.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;

public class CartRemoveHandler implements Handler {

	private CartService cartService;
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "cart/remove_success";
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		System.out.println(cno);
		
		cartService.remove(cno);
		
		return url;
	}

}
