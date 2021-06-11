package kr.or.ddit.handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.CartVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;
import kr.or.ddit.service.OrderService;
import kr.or.ddit.service.OrderdetailService;

public class OrderFormProductHandler implements Handler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "order/orderProForm";
		
		String pname = request.getParameter("pname");
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		int totalprice = Integer.parseInt(request.getParameter("totalprice"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		request.setAttribute("pname", pname);
		request.setAttribute("pno", pno);
		request.setAttribute("qty", qty);
		request.setAttribute("price", price);
		request.setAttribute("totalprice", totalprice);
		
		return url;
	}
}
