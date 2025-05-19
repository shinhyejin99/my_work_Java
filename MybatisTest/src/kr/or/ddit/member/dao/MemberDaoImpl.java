package kr.or.ddit.member.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao {

	
		private static IMemberDao memDao = new MemberDaoImpl();
		
		private MemberDaoImpl(){
			
		}
		public static IMemberDao getInstance() {
			return memDao;
		}
	@Override
	public int inserMember(MemberVO mv) {
		SqlSession session = MybatisUtil.getInstance();

		int cnt = 0;
		try {
			cnt = session.insert("member.insertMember", mv);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		SqlSession session = MybatisUtil.getInstance();

		int cnt = 0;
		try {
			cnt = session.update("member.updateMember", mv);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}
	@Override
	public int deleteMember(String memId) {
		SqlSession session = MybatisUtil.getInstance();

		int cnt = 0;
		try {
			cnt = session.delete("member.deleteMember", memId);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
		
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		SqlSession session = MybatisUtil.getInstance(true);		
		
		try {
			memList = session.selectList("member.getAllMember");
			
		} catch (PersistenceException ex) {
		ex.printStackTrace();
		}finally {
			session.close();
		}
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		SqlSession session = MybatisUtil.getInstance(true);
		boolean isExist = false;
		try {
			int cnt = session.selectOne("member.checkMember", memId);
			
			if(cnt>0) {
				isExist = true;
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return isExist;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		 
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MybatisUtil.getInstance(true);
		
		try {
			memList = session.selectList("member.searchMember", mv);
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return memList;
	}

	public static void main(String[] args) {

		MemberVO mv = new MemberVO("f001", "윤서현", "555-666", "대구시");

		int cnt = new MemberDaoImpl().inserMember(mv);
		if (cnt > 0) {
			System.out.println("성공!");

		} else {
			System.out.println("실패!!!");
		}
	}
}
