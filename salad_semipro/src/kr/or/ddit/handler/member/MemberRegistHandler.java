package kr.or.ddit.handler.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.FileDownloadResolver;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MenuService;
import kr.or.ddit.utils.GetUploadPath;

public class MemberRegistHandler implements Handler {
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/regist_success";
		
		String picture = request.getParameter("picture");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String authority = request.getParameter("authority");

		String phone = "";
		for(String data : request.getParameterValues("phone")) phone += data;
		
		MemberVO member = new MemberVO();
		member.setPicture(picture);
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		member.setEmail(email);
		member.setAddress(address);
		member.setAuthority(authority);
		member.setPhone(phone);
		
		try {
			memberService.regist(member);
		} catch (Exception e) {
			e.printStackTrace();
			url = "member/regist_fail";
		}
		return url;
	}

}
