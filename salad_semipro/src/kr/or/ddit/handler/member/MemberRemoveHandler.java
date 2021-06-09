package kr.or.ddit.handler.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.utils.GetUploadPath;

public class MemberRemoveHandler implements Handler {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/delete_success";
		
		String id = request.getParameter("id");

		MemberVO member = memberService.getMember(id);
		
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		String fileName = member.getPicture();
		File picture = new File(savedPath, fileName);
		
		if(picture.exists()) {
			picture.delete();
		}
		
		// DB삭제
		memberService.remove(id);
		
		//로그인 사용자 확인
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			request.getSession().invalidate(); // session 갱신
		}

		request.setAttribute("member", member);
		
		return url;
	}

}
