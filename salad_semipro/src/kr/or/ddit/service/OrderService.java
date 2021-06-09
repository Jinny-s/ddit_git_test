package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.OrderVO;

public interface OrderService {
	
	// 목록 조회
	Map<String, Object> getOrderList(SearchCriteria cri) throws SQLException;
	
	// 주문 선택
	OrderVO getOrder(int ono) throws SQLException;
		
	// 주문 추가
	void order(OrderVO order) throws SQLException;
	
	// 주문 상태 변경
	void modify(OrderVO order) throws SQLException;
	
	// 주문 삭제
	void remove(int ono) throws SQLException;
}
