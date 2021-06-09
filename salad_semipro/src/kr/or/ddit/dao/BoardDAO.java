package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;

public interface BoardDAO {
	
	// 목록 조회
	List<BoardVO> selectSearchBoardList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 목록 개수
	int selectSearchBoardListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 게시글 조회
	BoardVO selectBoardByBno(SqlSession session, int bno) throws SQLException;
	
	// 시퀀스 가져오기
	int selectBoardSequenceNextValue(SqlSession session) throws SQLException;

	// 조회수 증가
	void increaseViewCount(SqlSession session, int bno) throws SQLException;
	
	// 작성
	public void insertBoard(SqlSession session, BoardVO board) throws SQLException;
	
	// 수정
	public void updateBoard(SqlSession session, BoardVO board) throws SQLException;
	
	// 삭제
	public void deleteBoard(SqlSession session, int bno) throws SQLException;
	
}
