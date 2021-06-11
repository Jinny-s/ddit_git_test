package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ProductVO;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<ProductVO> selectSearchProductList(SqlSession session, SearchCriteria cri) throws SQLException {
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();		
		RowBounds rowBounds=new RowBounds(offset,limit);		
		
		List<ProductVO> productList=
				session.selectList("Product-Mapper.selectSearchProductList",cri,rowBounds);
		
		return productList;
	}

	@Override
	public int selectSearchProductListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count=session.selectOne("Product-Mapper.selectSearchProductListCount",cri);
		return count;
	}

	@Override
	public ProductVO selectProductByPno(SqlSession session, int pno) throws SQLException {
		ProductVO product=session.selectOne("Product-Mapper.selectProductByPno",pno);
		return product;
	}

	@Override
	public void increaseViewCount(SqlSession session, int pno) throws SQLException {
		session.update("Product-Mapper.increaseViewCount",pno);
		
	}

	@Override
	public int selectProductSequenceNextValue(SqlSession session) throws SQLException {
		int seq_num=session.selectOne("Product-Mapper.selectProductSequenceNextValue");
		return seq_num;
	}

	@Override
	public void insertProduct(SqlSession session, ProductVO product) throws SQLException {
		System.out.println(product);
		session.update("Product-Mapper.insertProduct",product);
		
	}
	
	@Override
	public void updateProduct(SqlSession session,ProductVO product) throws SQLException {
		session.update("Product-Mapper.updateProduct",product);
	}

	@Override
	public void deleteProduct(SqlSession session,int pno) throws SQLException {
		session.update("Product-Mapper.deleteProduct",pno);
	}
}

