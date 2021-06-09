package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.OrderDAO;
import kr.or.ddit.dao.OrderdetailDAO;
import kr.or.ddit.dto.OrderVO;

public class OrderServiceImpl implements OrderService {
	
	private OrderDAO orderDAO;
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	private OrderdetailDAO orderdetailDAO;
	public void setOrderdetailDAO(OrderdetailDAO orderdetailDAO) {
		this.orderdetailDAO = orderdetailDAO;
	}

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public Map<String, Object> getOrderList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<OrderVO> orderList = orderDAO.selectSearchOrderList(session, cri);
			
			// 상품명 출력
			for (OrderVO order : orderList) {
				String pname = orderdetailDAO.selectPnameByOno(session, order.getOno());
				int pcount = orderdetailDAO.selectPnameCountByOno(session, order.getOno());
				order.setPname(pname);
				order.setPcount(pcount);
				System.out.println("카운트" + pcount + " ? " + order.getOno());
			}
			
			int totalCount = orderDAO.selectSearchOrderListCount(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("orderList", orderList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
			
		} finally {
			session.close();
		}
	}

	@Override
	public OrderVO getOrder(int ono) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			OrderVO order = orderDAO.selectOrderByOno(session, ono);
			
			return order;
		} finally {
			session.close();
		}
	}

	@Override
	public void order(OrderVO order) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int ono = orderDAO.selectOrderSequenceNextValue(session);
			order.setOno(ono);
			orderDAO.insertOrder(session, order);
		} finally {
			session.close();
		}
		
		
	}

	@Override
	public void modify(OrderVO order) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			orderDAO.updateOrderStatus(session, order);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int ono) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			orderDAO.deleteOrder(session, ono);
		} finally {
			session.close();
		}

	}

}