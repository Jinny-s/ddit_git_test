package kr.or.ddit.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundException;
import kr.or.ddit.service.MemberService;

public class LoginHandler implements Handler {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:index.do";

		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		try {
			memberService.login(id, password);
			MemberVO member = memberService.getMember(id);
			session.setAttribute("loginUser", member);
			session.setMaxInactiveInterval(6*60);	// 6*60초(6분)
			if(member.getEnabled() == 0) {
				url = "loginUser_disabled";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}catch (NotFoundException | InvalidPasswordException e) {
			url="redirect:";
		}
		return url;
	}

}
