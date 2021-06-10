package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.OrderVO;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<OrderVO> selectSearchOrderList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<OrderVO> orderList = session.selectList("Order-Mapper.selectSearchOrderList", cri, rowBounds);
		return orderList;
	}

	@Override
	public int selectSearchOrderListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = 0;
		count = session.selectOne("Order-Mapper.selectSearchOrderListCount", cri);
		return count;
	}

	@Override
	public OrderVO selectOrderByOno(SqlSession session, int ono) throws SQLException {
		OrderVO order = session.selectOne("Order-Mapper.selectOrderByOno", ono);
		return order;
	}

	@Override
	public int selectOrderSequenceNextValue(SqlSession session) throws SQLException {
		int seq_num = session.selectOne("Order-Mapper.selectOrderSequenceNextValue");
		return seq_num;
	}

	@Override
	public void insertOrder(SqlSession session, OrderVO order) throws SQLException {
		session.update("Order-Mapper.insertOrder", order);
	}

	@Override
	public void updateOrderStatus(SqlSession session, int ono) throws SQLException {
		session.update("Order-Mapper.updateOrderStatus", ono);
	}

	@Override
	public void deleteOrder(SqlSession session, int ono) throws SQLException {
		session.update("Order-Mapper.deleteOrder", ono);
	}

}
