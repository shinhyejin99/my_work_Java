/**
 * 
 */
/**
 * 
 */
module MybatisTest {
	requires java.sql;
	requires org.mybatis;
	
	opens kr.or.ddit.member.vo;
	
	opens config; // myBatis가 mybatis-config.xml파일에 접근할 수 있도록 한다. 
	opens mappers; // myBatis가 mybatis-config.xml파일에 접근할 수 있도록 한다. 
}