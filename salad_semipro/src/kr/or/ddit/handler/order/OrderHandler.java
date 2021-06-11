package kr.or.ddit.handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.OrderVO;
import kr.or.ddit.dto.OrderdetailVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;
import kr.or.ddit.service.OrderService;
import kr.or.ddit.service.OrderdetailService;

public class OrderHandler implements Handler {

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
		String url = "order/order_success";
		
		String id = request.getParameter("id");
		int totalprice = Integer.parseInt(request.getParameter("totalprice"));
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String pname = request.getParameter("pname");
		
		// orderVO setting
		OrderVO order = new OrderVO();
		order.setId(id);
		order.setTotalprice(totalprice);
		
		int ono = 0;
		try {
			ono = orderService.order(order);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("orderVO setting FAIL");
		}
		
		// orderdetailVO setting
		OrderdetailVO detail = new OrderdetailVO();
		detail.setOno(ono);
		detail.setPno(pno);
		detail.setQty(qty);
		detail.setPrice(price);
		detail.setName(name);
		detail.setAddress(address);
		detail.setPhone(phone);
		detail.setPname(pname);
		
		try {
			orderdetailService.orderdetail(detail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("orderdetailVO setting FAIL");
		}
		
		try {
			cartService.remove(cno);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("order 후 remove FAIL");
		}
		
		return url;
	}

}
