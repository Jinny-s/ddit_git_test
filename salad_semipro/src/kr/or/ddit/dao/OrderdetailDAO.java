package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.OrderdetailVO;

public interface OrderdetailDAO {
	
	// 주문 상세 내역 조회 (해당 주문번호의 상세내역 전체)
	List<OrderdetailVO> selectOrderdetailListByOno(SqlSession session, int ono) throws SQLException;
	
	// 주문 상세 내역 조회 (해당 주문번흐 중 한 개만)
	String selectPnameByOno(SqlSession session, int ono) throws SQLException;
	
	// 상세내역의 상품 수 조회
	int selectPnameCountByOno(SqlSession session, int ono) throws SQLException;
}
