package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtil {
	
	static {
		try {
			//1. 드라이버 로딩(옵션)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	/*
	커넥션 객체 생성(연결)하기
	@return

*/
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"C##SHJ",
					"java");
		} catch (SQLException e) {
			e.printStackTrace();
		return null;
	}

}
	/*
		자원반남 메서드
		@
	*/
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		// 6. 종료(사용했던 자원을 모두 반납한다.)
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
			}
	}
	}

