package kr.or.ddit.handler.product;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.ProductVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class ProductDetailHandler implements Handler {
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String url="meal/detail";

		int pno=Integer.parseInt(request.getParameter("pno"));
		System.out.println(pno);
		String from=request.getParameter("from");
		
		try {
			ProductVO product=null;
			if(from!=null && from.equals("modify")) {
				product = productService.getProductForModify(pno);
			}else {
				 product = productService.getProduct(pno);
			}
			
			request.setAttribute("product", product);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}