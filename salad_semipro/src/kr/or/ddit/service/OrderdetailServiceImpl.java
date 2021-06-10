package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.OrderdetailDAO;
import kr.or.ddit.dto.OrderdetailVO;

public class OrderdetailServiceImpl implements OrderdetailService {
	
	private OrderdetailDAO orderdetailDAO;
	public void setOrderdetailDAO(OrderdetailDAO orderdetailDAO) {
		this.orderdetailDAO = orderdetailDAO;
	}
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<OrderdetailVO> getDetailList(int ono) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<OrderdetailVO> orderdetailList = orderdetailDAO.selectOrderdetailListByOno(session, ono);
			return orderdetailList;
		} finally {
			session.close();
		}
	}

	@Override
	public String getPname(int ono) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			String pName = orderdetailDAO.selectPnameByOno(session, ono);
			return pName;
		} finally {
			session.close();
		}
	}

	@Override
	public int getPcount(int ono) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int pcount = orderdetailDAO.selectPnameCountByOno(session, ono);
			return pcount;
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(OrderdetailVO detail) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			orderdetailDAO.updateOrderInfoByOno(session, detail);;
		} finally {
			session.close();
		}
	}

}
