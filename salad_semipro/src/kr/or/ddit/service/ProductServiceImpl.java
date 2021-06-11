package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.ProductDAO;
import kr.or.ddit.dto.ProductVO;

public class ProductServiceImpl implements ProductService {
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public Map<String, Object> getProductList(SearchCriteria cri) throws SQLException {

		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();

			// 현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
			List<ProductVO> productList = productDAO.selectSearchProductList(session, cri);

			// 전체 board 개수
			int totalCount = productDAO.selectSearchProductListCount(session, cri);

			// PageMaker 생성.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);

			dataMap.put("productList", productList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public ProductVO getProduct(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ProductVO board = productDAO.selectProductByPno(session, pno);
			productDAO.increaseViewCount(session, pno);
			return board;
		} finally {
			session.close();
		}
	}

	@Override
	public ProductVO getProductForModify(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ProductVO board = productDAO.selectProductByPno(session, pno);
			return board;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(ProductVO product) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			int pno = productDAO.selectProductSequenceNextValue(session);
			product.setPno(pno);
			System.out.println("pno"+pno);
			System.out.println(product);
			productDAO.insertProduct(session, product);
		} finally {
			session.close();
		}
	}
	@Override
	public void modify(ProductVO product) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			productDAO.updateProduct(session, product);
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			productDAO.deleteProduct(session, pno);
		} finally {
			session.close();
		}
	}
	
	
}