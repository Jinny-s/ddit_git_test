package kr.or.ddit.handler.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.XSSResolver;
import kr.or.ddit.dto.ProductVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.ProductService;

public class ProductModifyHandler implements Handler {

	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:meal/detail.do?from=modify&pno="+request.getParameter("pno");
		
		XSSResolver.parseXSS(request);
		
		// 파라메터 저장
		int pno = Integer.parseInt(request.getParameter("pno"));
		//String title = request.getParameter("title");
		String pname = request.getParameter("pname");
		int price = Integer.parseInt(request.getParameter("price"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		
		ProductVO product = new ProductVO();
		product.setPno(pno);
		product.setPname(pname);
		product.setPrice(price);
		product.setWriter(writer);
		product.setContent(content);
		
		
		productService.modify(product);
		
		return url;
	}

}