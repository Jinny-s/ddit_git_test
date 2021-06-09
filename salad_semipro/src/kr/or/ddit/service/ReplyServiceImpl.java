package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dao.ReplyDAO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<ReplyVO> replyList = replyDAO.selectReplyListPage(session, bno, cri);
			
			int count = replyDAO.countReply(session, bno);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
			
			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker", pageMaker);
			
			MemberVO member = new MemberVO();
			for(ReplyVO reply : replyList) {
				String id = (String) reply.getReplyer();
				member = memberDAO.selectMemberById(session, id);
				reply.setPicture(member.getPicture());
			}
			
			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public int getReplyListCount(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			replyDAO.insertReply(session, reply);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
	}

	@Override
	public void deleteReply(int rno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
	}
	

}
