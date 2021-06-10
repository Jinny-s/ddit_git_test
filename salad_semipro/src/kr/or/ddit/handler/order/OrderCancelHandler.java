package kr.or.ddit.handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.OrderService;

public class OrderCancelHandler implements Handler {
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "order/cancel_success";
		
		int ono = Integer.parseInt(request.getParameter("ono"));
		orderService.modify(ono);
	
		return url;
	}

}
