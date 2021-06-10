package kr.or.ddit.handler.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.OrderVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.OrderService;

public class OrderDetailHandler implements Handler {
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "order/detail";
		
		int ono = Integer.parseInt(request.getParameter("ono"));
		Map<String, Object> dataMap = null;
		
		try {
			dataMap = orderService.getOrder(ono);
			request.setAttribute("dataMap", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}

}
