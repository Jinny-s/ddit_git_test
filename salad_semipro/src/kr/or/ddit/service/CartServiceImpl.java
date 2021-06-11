package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.CartDAO;
import kr.or.ddit.dto.CartVO;

public class CartServiceImpl implements CartService {
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private CartDAO cartDAO;
	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}
	
	@Override
	public List<CartVO> getCartListByUser(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<CartVO> cartList = cartDAO.selectCartListByUser(session, id);
			System.out.println("carListsize" + cartList.size());
			
			return cartList;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(CartVO cart) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int cno = cartDAO.selectCartSeqNextValue(session);
			cart.setCno(cno);
			cartDAO.insertCart(session, cart);
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(CartVO cart) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			cartDAO.updateCart(session, cart);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int cno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			cartDAO.deleteCart(session, cno);
		} finally {
			session.close();
		}
	}


	@Override
	public void qtyChange(CartVO cart) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			cartDAO.qtyChange(session, cart);
		} finally {
			session.close();
		}
		
	}

	@Override
	public int countList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int cnt = cartDAO.selectCartLictCount(session);
			return cnt;
		} finally {
			session.close();
		}
	}

	@Override
	public CartVO getCartByCno(int cno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			CartVO cart = cartDAO.selectCartByCno(session, cno);
			return cart;
		} finally {
			session.close();
		}
	}
}
