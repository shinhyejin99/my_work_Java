package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	public static void main(String[] args) {
		
		// myBatis를 이용하여 DB작업을 처리하는 작업 순서 
		
		// 1. myBAtis의 환경설정파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sessionFactory = null;
		
		try {
			//1-1. xml 설정파일 읽어오기
			//설정파일의 인코딩 정보 설정하기(한글깨짐 방지용)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// Reader객체를 이용하여 SqlSessionFactory 객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}	
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
		
		// 2-1. insert작업 연습
		System.out.println("insert작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담는다
		MemberVO mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("김주민");
		mv.setMemTel("555-666");
		mv.setMemAddr("진주시");
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memId", "d001");
		paramMap.put("memName", "김주민");
		paramMap.put("memTel", "555-6666");
		paramMap.put("memAddr", "진주시");
		
		
		// 2)SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		// ex) SqlSession 객체 .insert("namespace값.id값",파라미터객체)
		// 반환값 : 성공한 레코드 수
		
		SqlSession sqlSession = sessionFactory.openSession(false); // 오토커밋여부
		
		try {
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			
			if(cnt>0) {
				sqlSession.commit();  //커밋
				System.out.println("insert 작업 성공!");
				
			}else{
				System.out.println("insert작업 실패");
				
			}
		} catch (PersistenceException ex) {
			sqlSession.rollback(); // 롤백
			ex.printStackTrace();
		}finally {
			sqlSession.close();
			
		}
		
		
		System.out.println("--------------------------------------------");
		
		// 2-2. update 연습
		System.out.println("updatr작업 시작....");
		
		mv.setMemId("d001");
		mv.setMemName("최건우");
		mv.setMemTel("5555-6666");
		mv.setMemAddr("부산시");
		
		sqlSession = sessionFactory.openSession(false);
		
		
		
		try {
		int cnt = sqlSession.update("memberTest.updateMember", mv);
		
		if(cnt>0) {
			sqlSession.commit(); // 커밋
			System.out.println("update작업 성공!");
		}else {
			System.out.println("update작업 실패!!");
		}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			
		}finally {
			sqlSession.close();
		}
		System.out.println("-----------------------------------");
		
		/*
		 * //2-3. delete 연습 System.out.println("delete 작업 시작...."); sqlSession =
		 * sessionFactory.openSession(); try { int cnt =
		 * sqlSession.delete("memberTest.deleteMember", "d001");
		 * 
		 * if(cnt > 0) { sqlSession.commit(); System.out.println("삭제 작업 성공!"); }else {
		 * System.out.println("삭제 작업 실패!!!"); }
		 * 
		 * } catch (PersistenceException ex) { ex.printStackTrace(); }finally {
		 * sqlSession.close(); }
		 */
		
		System.out.println("-------------------------------------------");
	
		// 2-4) select 연습
		// 1) 응답결과가 여러개일 경우 
			System.out.println("select 연습 시작(결과가 여러개일 경우)...");
			
			sqlSession = sessionFactory.openSession(true);
			
			// 응답결과가 여러개가 확실한 경우에는 selectList메서드를 사용한다.
			 
			try {
				/*
				 * List<MemberVO> memList = sqlSession.selectList("memberTest.getAllMember");
				 * 
				 * for(MemberVO mv2 : memList) { System.out.println("ID : " + mv2.getMemId());
				 * System.out.println("이름 : " + mv2.getMemName()); System.out.println("전화 : " +
				 * mv2.getMemTel()); System.out.println("주소 : " + mv2.getMemAddr());
				 * System.out.println("==================================");
				 * 
				 * }
				 */
				
				List<Map<String, Object>> memList  = sqlSession.selectList("memberTest.getAllMember2");
				
				for(Map<String, Object> mv2 : memList) {
					System.out.println("ID : " + mv2.get("MEM_ID"));
					System.out.println("이름 : " + mv2.get("MEM_NAME"));
					System.out.println("전화 : " + mv2.get("MEM_TEL"));
					System.out.println("주소 : " + mv2.get("MEM_ADDR"));
					System.out.println("==================================");
					
				}
				System.out.println("전체 회원정보 출력 끝....");
				
			} catch (PersistenceException ex) {
				ex.printStackTrace();
			}finally {
				sqlSession.close();
			}
			

		// 2) 응답결과가 1개인 경우
			 System.out.println("select 연습 시작(결과가 1개일 경우)...");
			 
			  sqlSession =  sessionFactory.openSession(true);
			  
			  try {
				
				  MemberVO mv2 = sqlSession.selectOne("memberTest.getMember", "d001");
				  System.out.println("ID : " + mv2.getMemId());
					System.out.println("이름 : " + mv2.getMemName());
					System.out.println("전화 : " + mv2.getMemTel());
					System.out.println("주소 : " + mv2.getMemAddr());
					System.out.println("==================================");
					
				System.out.println("1건 조회 완료....");
				
				
			} catch (PersistenceException ex) {
				ex.printStackTrace();
			}finally {
				sqlSession.close();
			}
	
	
	}

}

















