package kr.or.ddit.handler.cart;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.command.CartModifyCommand;
import kr.or.ddit.dto.CartVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;

public class CartQtyChangeHandler implements Handler {

	private CartService cartService;
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		CartModifyCommand cartReq = 
				mapper.readValue(request.getReader(), CartModifyCommand.class);
		
		CartVO cart = cartReq.toCartVO();
		
		try {
			cartService.qtyChange(cart);
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return url;
	}

}
