package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/*
	실제 DB에 연결하여 SQL문을 수행한 후 결과를 받아와
	서비스에 전달하는 DAO Interface
*/
public interface IMemberDao {
/*
	MemberVO에 담겨진 데이터를 DB에 insert 하기 위한 메서드
	@param mv 등록할 회원정보를 담은 MemberVO객체
	@return DB작업이 성공히면 1, 실패하면 0 이 반환됨
	*/
	public int inserMember(MemberVO mv);
	
	/*
	MemberVO에 담겨진 데이터를 DB에 update 하기 위한 메서드
	@param mv 등록할 회원정보를 담은 MemberVO객체
	@return DB작업이 성공히면 1, 실패하면 0 이 반환됨
	*/
	
	public int updateMember(MemberVO mv);
	
	/*
	회원정보를 삭제하기 위한 메서드
	@param mv 삭제할 회원 ID
	@return DB작업이 성공히면 1, 실패하면 0 이 반환됨
	*/
	public int deleteMember(String memId);
	
	/*
	DB에 존재하는 전체 회원정보를 조회하기 위한 메서드
	@return 전체 회원정보를 담은 LISt객체
	*/
	
	public List<MemberVO> getAllMember();
	
	/*
	회원이 존재하는지 확인하기 위한 메서드
	@param memId
	@return 회원이 존재하면 true, 존재하지않으면 false 값이 반환됨
	*/
	public boolean checkMember(String memId);
	
	/*
	회원정보를 검색하기 위한 메서드
	@param mv 검색할 회원정보를 담은 MemberVo객체
	@return 검색된 회원정보를 딤은List 객체
	*/
	public List<MemberVO> searchMember(MemberVO mv);
}
	
	
	
	
	
	
	
	
	
	
	
	
	