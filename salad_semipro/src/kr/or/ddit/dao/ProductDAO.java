package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ProductVO;

public interface ProductDAO {
	
	
	List<ProductVO> selectSearchProductList(SqlSession session,SearchCriteria cri) 
				throws SQLException;

	int selectSearchProductListCount(SqlSession session,SearchCriteria cri) 
				throws SQLException;
	
	ProductVO selectProductByPno(SqlSession session,int pno) throws SQLException;
	
	// viewcnt 증가
	void increaseViewCount(SqlSession session,int pno) throws SQLException;

	// Product_seq.nextval 가져오기
	int selectProductSequenceNextValue(SqlSession session) throws SQLException;
	
	void insertProduct(SqlSession session,ProductVO Product) throws SQLException;
	
	void updateProduct(SqlSession session,ProductVO Product) throws SQLException;

	void deleteProduct(SqlSession session,int pno) throws SQLException;

}