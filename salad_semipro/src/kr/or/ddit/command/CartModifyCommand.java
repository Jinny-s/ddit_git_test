package kr.or.ddit.command;

import kr.or.ddit.dto.CartVO;

public class CartModifyCommand {
	private int cno;
	private int qty;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public CartVO toCartVO() {
		CartVO cart = new CartVO();
		cart.setCno(cno);
		cart.setQty(qty);
		
		return cart;
	}
}
