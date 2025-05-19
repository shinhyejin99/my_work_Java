package kr.or.ddit.member.servies;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;

import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private static IMemberService memService = new MemberServiceImpl();
	
	private IMemberDao memDao;
	
	private MemberServiceImpl() {
		// memDao = MemberDaoImplForJDBC.getInstance(); // JDBC버전 DAO
		memDao = MemberDaoImpl.getInstance(); //MyBatis 버전의 DAO
		
	}
	
	public static IMemberService getInstance() {
		return memService;
	}

	@Override
	public int registerMember(MemberVO mv) {
		
		 int cnt = memDao.inserMember(mv); // 회원등록 처리하기
		 
		 if(cnt > 0) {
			 // 회원등록 환영 메일 발송하기...
			 
		 }
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		 
		return memDao.updateMember(mv);
	}

	@Override
	public int removeMember(String memId) {
		 
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> displayAllMember() {
	
		return memDao.getAllMember();
	}

	@Override
	public boolean checkMember(String memId) {
		
		return  memDao.checkMember(memId);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		return memDao.searchMember(mv);
	}
	
}
