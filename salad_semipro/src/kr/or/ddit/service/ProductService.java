package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ProductVO;

public interface ProductService {
	
	// 목록조회
	Map<String, Object> getProductList(SearchCriteria cri) throws SQLException;
	
	// 상세보기
	ProductVO getProduct(int pno) throws SQLException;
	
	// 수정화면 상세
	ProductVO getProductForModify(int pno) throws SQLException;

	// 등록
	void regist(ProductVO product)throws SQLException;
	
	// 수정
	void modify(ProductVO board) throws SQLException;

	// 삭제
	void remove(int pno) throws SQLException;
}