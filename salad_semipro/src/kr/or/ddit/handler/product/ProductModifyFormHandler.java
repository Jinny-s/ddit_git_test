package kr.or.ddit.handler.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.ProductVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class ProductModifyFormHandler implements Handler {
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="meal/modify";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		ProductVO product=productService.getProductForModify(pno);
		
		request.setAttribute("product", product);
		
		return url;
	}

}