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
		
		String cnoList = request.getParameter("cno");
		System.out.println("@@@@cno : " + cnoList);
		
		if(cnoList.contains(",")) {
			String[] cnoListArr = cnoList.split(",");
			for(String cno : cnoListArr) {
				int cnoNum = Integer.parseInt(cno);
				cartService.remove(cnoNum);
			}
		} else {
			String[] cnoListArr = cnoList.split(",");
			int cnoNum = Integer.parseInt(cnoListArr[0]);
			cartService.remove(cnoNum);
		}
		
		return url;
	}

}
