package kr.or.ddit.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;

public class MemberEnabledCheckFilter implements Filter {
	List<String> exUrls = new ArrayList<String>(); 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		// 제외 url 설정
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser.getEnabled() == 0) {
			if(reqUrl.contains("modify") || reqUrl.contains("remove") || reqUrl.contains("disabled") || reqUrl.contains("regist")) {
				httpResp.setContentType("text/html;charset=utf-8;");
				PrintWriter out = httpResp.getWriter();
				out.println("<script>");
				out.println("alert('접근권한이 없습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
		String excludeURLNames = fconfig.getInitParameter("exclude");
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while(st.hasMoreTokens()) {
			exUrls.add(st.nextToken().trim());
		}
	}
	
	private boolean excludeCheck(String url) {
		if(url.length() <= 1) {
			return true;
		}
		for(String exURL : exUrls) {
			if(url.contains(exURL)) {
				return true;
			}
		}
		return false;
	}
}
