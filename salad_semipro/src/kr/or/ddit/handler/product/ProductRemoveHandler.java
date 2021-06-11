package kr.or.ddit.handler.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class ProductRemoveHandler implements Handler {
	

	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="meal/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		productService.remove(pno);
		
		return url;
	}

}