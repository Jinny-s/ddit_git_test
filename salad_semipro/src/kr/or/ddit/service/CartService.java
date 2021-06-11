package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dto.CartVO;

public interface CartService {
	List<CartVO> getCartListByUser(String id) throws SQLException;
	
	void regist(CartVO cart) throws SQLException;
	
	void modify(CartVO cart) throws SQLException;
	
	void remove(int cno) throws SQLException;
	
	void qtyChange(CartVO cart) throws SQLException;
	
	int countList() throws SQLException;
	
	CartVO getCartByCno(int cno) throws SQLException;
}
