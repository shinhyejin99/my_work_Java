package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImplForJDBC implements IMemberDao {
	
	private static IMemberDao memDao = new MemberDaoImplForJDBC();
	
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private MemberDaoImplForJDBC() {
	
	}
	
	public static IMemberDao getInstance() {
		return memDao;
		
	}
	@Override
public int inserMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {			
			conn = JDBCUtil3.getConnection();
			
			String sql = " insert into mymember "
				    + " (mem_id, mem_name, mem_tel, mem_addr) "
				    + " values (?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			 cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
			
		}
		return cnt;
}
@Override
	public int updateMember(MemberVO mv) {
		
		int cnt=0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " update mymember "
					+ " set mem_name = ?, "
					+ " mem_tel = ?,"
					+ " mem_addr = ?"
					+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			 cnt = pstmt.executeUpdate();
			
		} catch ( SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);		
	}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			conn =JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			 cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
		stmt = conn.createStatement();
		
		String sql = " select * from mymember ";
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			String memId = rs.getString("mem_id");
			String memName = rs.getString("mem_name");
			String memTel = rs.getString("mem_tel");
			String memAddr = rs.getString("mem_addr");
			
			LocalDate regDt = rs.getTimestamp("reg_dt").toLocalDateTime().toLocalDate();
			
					 
			 MemberVO mv = new MemberVO(memId, memName, memTel, memAddr, regDt) ;
			memList.add(mv);
		}
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql ="select count(*) as cnt from mymember "
					+ "where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
		
			while(rs.next()) {
				int cnt = rs.getInt("CNT");
				if(cnt>0) {
					isExist = true;
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}
	
	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * "
					+ "	from mymember  "
					+ "	where 1=1 ";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += "and mem_id = ? ";
			}
			if(mv.getMemId() != null && !mv.getMemName().equals("")) {
				sql += "and mem_name = ? ";
			}
			if(mv.getMemId() != null && !mv.getMemTel().equals("")) {
				sql += "and mem_tel = ? ";
			}
			if(mv.getMemId() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int paramIndex = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(paramIndex++, mv.getMemId());
			}
			
			if(mv.getMemId() != null && !mv.getMemName().equals("")) {
				pstmt.setString(paramIndex++, mv.getMemName());
			}
			
			if(mv.getMemId() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(paramIndex++, mv.getMemTel());
			}
			
			if(mv.getMemId() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(paramIndex++, mv.getMemAddr());
			}
			System.out.println("SQL");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				LocalDate regDt = rs.getTimestamp("reg_dt").toLocalDateTime().toLocalDate();
				System.out.println(" " + memId + "\t" + regDt + "\t"+ memName + "\t" + memTel+ "\t" + memAddr);
						 
				 MemberVO mv2 = new MemberVO(memId, memName, memTel, memAddr, regDt) ;
				memList.add(mv2);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}
	
}




