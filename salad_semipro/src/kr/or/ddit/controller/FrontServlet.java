package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;

@WebServlet("/FrontServlet")
public class FrontServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String command = request.getRequestURI();
		if(command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		
		Handler handler = null;
		String view = null;
		
		if(handlerMapper != null) {
			handler = handlerMapper.getHandler(command);
			if(handler != null) {
				try {
					view = handler.process(request, response);
					if(view != null) {
						ViewResolver.view(request, response, view);
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Error
				}
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 Error(Page Not Found)
			}
		}else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Error
		}
	}

	private HandlerMapper handlerMapper;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String handlerMapperType = config.getInitParameter("handlerMapper");
		
		try {
			this.handlerMapper = (HandlerMapper) injectionBean(handlerMapperType);
			System.out.println("[FrontServlet] handlerMapper 가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet] handlerMapper 가 준비되지 않았습니다.");
		}
	}

	private Object injectionBean(String classType) throws Exception{
		Class<?> cls = Class.forName(classType);
		return cls.newInstance();
	}

}
