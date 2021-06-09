package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.mybatis.OracleMyBatisSqlSessionFactory;

public class TestMenuDAOImpl {

	private SqlSession session;
	private MenuDAO menuDao;
	
	@Before
	public void init() {
		session = new OracleMyBatisSqlSessionFactory().openSession(false);
		menuDao = new MenuDAOImpl();
	}
	
	@Test
	public void testSelectMainMenu() throws Exception {
		List<MenuVO> menuList = menuDao.selectMainMenu(session);
		Assert.assertEquals(5, menuList.size());
	}
	
	@Test
	public void testSelectSubMenu() throws Exception {
		List<MenuVO> menuList = menuDao.selectSubMenu(session, "M000000");
		Assert.assertEquals(5, menuList.size());
	}
	
	@Test
	public void testSelectMenuByMcode() throws Exception {
		MenuVO menu = menuDao.selectMenuByMcode(session, "M010200");
		Assert.assertEquals("회원등록", menu.getMname());
	}
	
	@After
	public void complete() {
		session.rollback();
		session.close();
	}
	
}
