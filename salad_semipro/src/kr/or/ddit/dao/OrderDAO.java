package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.OrderVO;

public interface OrderDAO {
	
	// 목록 조회
	List<OrderVO> selectSearchOrderList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 목록 개수
	int selectSearchOrderListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 주문 선택
	OrderVO selectOrderByOno(SqlSession session, int ono) throws SQLException;
	
	// 시퀀스 증가
	int selectOrderSequenceNextValue(SqlSession session) throws SQLException;
	
	// 주문 추가
	public void insertOrder(SqlSession session, OrderVO order) throws SQLException;
	
	// 주문 상태 변경
	public void updateOrderStatus(SqlSession session, int ono) throws SQLException;
	
	// 주문 삭제
	public void deleteOrder(SqlSession session, int ono) throws SQLException;
	
}
