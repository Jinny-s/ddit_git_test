package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.CartVO;

public interface CartDAO {
	List<CartVO> selectCartListByUser(SqlSession session, String id)
			throws SQLException;
	
	int selectCartSeqNextValue(SqlSession session)
			throws SQLException;
	
	void insertCart(SqlSession session, CartVO cart)
			throws SQLException;
	
	void updateCart(SqlSession session, CartVO cart)
			throws SQLException;
	
	void deleteCart(SqlSession session, int cno)
			throws SQLException;
	
	void qtyChange(SqlSession session, CartVO cart)
			throws SQLException;
	
	CartVO selectCartByCno(SqlSession session, int cno)
			throws SQLException;
	
	int selectCartLictCount(SqlSession session)
			throws SQLException;
}
