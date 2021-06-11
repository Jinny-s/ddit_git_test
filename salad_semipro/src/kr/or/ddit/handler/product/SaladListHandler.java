package kr.or.ddit.handler.product;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class SaladListHandler implements Handler {
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "meal/salad";
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = "t";
		String keyword = "샐러드";
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);		
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		Map<String,Object> dataMap=null;
		
		try {
			dataMap=productService.getProductList(cri);
			request.setAttribute("dataMap", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			url=null;
		}		
		
		
		
		
		return url;
	}

}
