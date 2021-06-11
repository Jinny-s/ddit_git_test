package kr.or.ddit.handler.cart;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.command.CartModifyCommand;
import kr.or.ddit.dao.CartDAO;
import kr.or.ddit.dto.CartVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;

public class CartRegistHandler implements Handler {
	
	private CartService cartService;
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "cart/regist_success";
		
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String pname = request.getParameter("pname");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String id = request.getParameter("sessionId");
		
		CartVO cart = new CartVO();
		
		cart.setPname(pname);
		cart.setPno(pno);
		cart.setId(id);
		cart.setQty(qty);
		
		try {
			cartService.regist(cart);
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return url;
	}

}
