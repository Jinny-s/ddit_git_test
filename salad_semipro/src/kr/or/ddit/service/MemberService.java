package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundException;

public interface MemberService {
	// 로그인
	void login(String id, String pwd) throws SQLException, NotFoundException, InvalidPasswordException;

	// 회원정보조회
	MemberVO getMember(String id) throws SQLException;
	
	// 회원리스트 조회
	List<MemberVO> getMemberList() throws SQLException;
	List<MemberVO> getMemberList(Criteria cri) throws SQLException;
	Map<String, Object> getMemberList(SearchCriteria cri) throws SQLException;
	
	public void regist(MemberVO member) throws SQLException;
	
	public void modify(MemberVO member) throws SQLException;
	
	public void remove(String id) throws SQLException;
	
	public void disabled(String id) throws SQLException;
	
	public void enabled(String id) throws SQLException;
	
}
