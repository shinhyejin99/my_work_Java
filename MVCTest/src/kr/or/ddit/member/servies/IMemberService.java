package kr.or.ddit.member.servies;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/*
	 Service Interface
*/
public interface IMemberService {
/*
	회원등록을 위한 메서드
	@param mv 등록할 회원정보를 담은 MemberVO객체
	@return DB작업이 성공히면 1, 실패하면 0 이 반환됨
	*/
	public int registerMember(MemberVO mv);
	
	/*
	회원정보를 수정하기 위한 메서드
	@param mv 등록할 회원정보를 담은 MemberVO객체
	@return 성공히면 1, 실패하면 0 이 반환됨
	*/
	
	public int modifyMember(MemberVO mv);
	
	/*
	회원정보를 삭제하기 위한 메서드
	@param mv 삭제할 회원 ID
	@return 성공히면 1, 실패하면 0 이 반환됨
	*/
	public int removeMember(String memId);
	
	/*
	 전체 회원정보를 조회하기 위한 메서드
	@return 전체 회원정보를 담은 LISt객체
	*/
	
	public List<MemberVO> displayAllMember();
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	