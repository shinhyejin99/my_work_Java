package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class JDBCUtil2 {
	/*
		db.properties 파일의 내용으로 DB정보를 설정하기
		
		방법1)properties 객체 이용하기
	*/
	private static Properties prop;
	static {
		
			try {
				prop = new Properties();
				
				FileInputStream fis = new FileInputStream("res/db.properties");
				prop.load(fis);
				
				//1. 드라이버 로딩(옵션)
				Class.forName(prop.getProperty("driver"));
				System.out.println("드라이버 로딩 성공!");
				
			} catch (FileNotFoundException e) {	
				e.printStackTrace();
					
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	커넥션 객체 생성(연결)하기
	@return

*/
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
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

