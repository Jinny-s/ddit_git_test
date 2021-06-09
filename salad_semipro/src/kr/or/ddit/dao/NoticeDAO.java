package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.Criteria;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;

public interface NoticeDAO {
	
	// 게시글리스트 조회
	List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 검색 결과의 전체 리스트 개수
	int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 게시글 조회
	NoticeVO selectNoticeByNno(SqlSession session, int nno) throws SQLException;
	
	// viewcnt 증가
	void increaseViewCount(SqlSession session, int nno) throws SQLException;
	
	// Notice_seq.nextVal 가져오기
	int selectNoticeSequenceNextValue(SqlSession session) throws SQLException;
	
	// 공지 작성
	public void insertNotice(SqlSession session, NoticeVO notice) throws SQLException;
	
	// 공지 수정
	public void updateNotice(SqlSession session, NoticeVO notice) throws SQLException;
	
	// 공지 삭제
	public void deleteNotice(SqlSession session, int nno) throws SQLException;
}