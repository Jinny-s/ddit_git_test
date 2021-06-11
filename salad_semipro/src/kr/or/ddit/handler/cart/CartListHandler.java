package kr.or.ddit.handler.cart;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.CartVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.ProductVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.CartService;
import kr.or.ddit.service.ProductService;

public class CartListHandler implements Handler{

   private CartService cartService;
   public void setCartService(CartService cartService) {
      this.cartService = cartService;
   }
   
   private ProductService productService;
   public void setProductService(ProductService productService) {
      this.productService = productService;
   }
   
   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String url = "cart/list";
      
      HttpSession session = request.getSession();
      MemberVO member = (MemberVO) session.getAttribute("loginUser");
      String id = member.getId();
      List<CartVO> cartList = null;
      int listCnt = 0;
      try {
         cartList = cartService.getCartListByUser(id);

         for(CartVO cart : cartList) {
            int pno = cart.getPno();
            ProductVO prod = productService.getProduct(pno);
            int price = prod.getPrice();
            cart.setPrice(price);
         }
         listCnt = cartService.countList();
         
         request.setAttribute("cartList", cartList);
         request.setAttribute("listCnt", listCnt);
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      }
      return url;
   }

}