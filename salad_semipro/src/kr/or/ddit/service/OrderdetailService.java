package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dto.OrderdetailVO;

public interface OrderdetailService {
	
	// 주문 상세 내역 조회 (해당 주문번호의 상세내역 전체)
	List<OrderdetailVO> getDetailList(int ono) throws SQLException;
	
	// 주문 상세 내역 조회 (해당 주문번흐 중 한 개만)
	String getPname(int ono) throws SQLException;
	
	int getPcount(int ono) throws SQLException;
	
	void modify(OrderdetailVO detail) throws SQLException;
	
	void orderdetail(OrderdetailVO detail) throws SQLException;
}
