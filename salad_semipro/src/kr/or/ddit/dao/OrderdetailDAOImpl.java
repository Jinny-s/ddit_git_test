package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.OrderdetailVO;

public class OrderdetailDAOImpl implements OrderdetailDAO {

	@Override
	public List<OrderdetailVO> selectOrderdetailListByOno(SqlSession session, int ono) throws SQLException {
		List<OrderdetailVO> orderdetailList = session.selectList("Orderdetail-Mapper.selectOrderdetailListByOno", ono);
		return orderdetailList;
	}

	@Override
	public String selectPnameByOno(SqlSession session, int ono) throws SQLException {
		String pName = session.selectOne("Orderdetail-Mapper.selectPnameByOno", ono);
		return pName;
	}

	@Override
	public int selectPnameCountByOno(SqlSession session, int ono) throws SQLException {
		int count = session.selectOne("Orderdetail-Mapper.selectPnameCountByOno", ono);
		return count;
	}

	@Override
	public void updateOrderInfoByOno(SqlSession session, OrderdetailVO detail) throws SQLException {
		session.update("Orderdetail-Mapper.updateOrderInfoByOno", detail);
	}

	@Override
	public void insertOrderdetail(SqlSession session, OrderdetailVO detail) throws SQLException {
		session.update("Orderdetail-Mapper.insertOrderdetail", detail);
	}

}
