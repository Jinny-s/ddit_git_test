package kr.or.ddit.handler.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.ProductVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class ProductRegistHandler implements Handler {
	

	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="meal/regist_success";

//		XSSResolver.parseXSS(request);
		
		String pname = request.getParameter("pname");
		System.out.println("pname>>>>>>>>>>>>>>"+pname);
		int price = Integer.parseInt(request.getParameter("price"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		ProductVO product = new ProductVO();
		product.setPname(pname);
		product.setPrice(price);
		product.setWriter(writer);
		product.setContent(content);
		
		productService.regist(product);
		
		
		return url;
	}

}
