package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.CartVO;

public class CartDAOImpl implements CartDAO {

	@Override
	public List<CartVO> selectCartListByUser(SqlSession session, String id)  {
		List<CartVO> cartList = session.selectList("Cart-Mapper.selectCartListByUser", id);
		return cartList;
	}

	@Override
	public int selectCartSeqNextValue(SqlSession session) throws SQLException {
		int seq_num = session.selectOne("Cart-Mapper.selectCartSeqNextValue");
		return seq_num;
	}
	
	@Override
	public void insertCart(SqlSession session, CartVO cart) throws SQLException {
		session.update("Cart-Mapper.insertCart", cart);
	}

	@Override
	public void updateCart(SqlSession session, CartVO cart) throws SQLException {
		session.update("Cart-Mapper.updateCart", cart);
	}
	
	@Override
	public void deleteCart(SqlSession session, int cno) throws SQLException {
		session.update("Cart-Mapper.deleteCart", cno);
	}

	@Override
	public void qtyChange(SqlSession session, CartVO cart) throws SQLException {
		session.update("Cart-Mapper.qtyChange", cart);
	}

	@Override
	public CartVO selectCartByCno(SqlSession session, int cno) throws SQLException {
		CartVO cart = session.selectOne("Cart-Mapper.selectCartByCno", cno);
		return cart;
	}

	@Override
	public int selectCartLictCount(SqlSession session) throws SQLException {
		int cnt = session.selectOne("Cart-Mapper.selectCartLictCount");
		return cnt;
	}
}
