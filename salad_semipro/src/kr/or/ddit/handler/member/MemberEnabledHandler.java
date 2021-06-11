package kr.or.ddit.handler.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;

public class MemberEnabledHandler implements Handler {
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//아이디 받고 정지(disabled) -> 사용자 로그인 상태 확인 -> 세션 내보내기
		String url = "member/enabled_success";
		
		String id = request.getParameter("id");
		
		memberService.enabled(id);
		
		request.setAttribute("member", memberService.getMember(id));
		
		return url;
	}

}
