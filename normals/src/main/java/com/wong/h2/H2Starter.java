package com.wong.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Configuration;

/**
 * create by: HuangZhiBin
 * 2018年12月17日 下午2:23:15
 */

@Configuration
public class H2Starter {

	public static void main(String[] args) throws SQLException {
		JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:file:D:/data/sample;PAGE_SIZE=10240;MODE=MySQL", "sample", "sample");
		
		Connection conn = cp.getConnection();
		
		Statement stat = conn.createStatement();
		
		// create full text search
		//stat.execute("CREATE ALIAS IF NOT EXISTS FTL_INIT FOR \"org.h2.fulltext.FullTextLucene.init\"");
		//stat.execute("CALL FTL_INIT()");
		
		// create table
		//stat.execute("CREATE TABLE IF NOT EXISTS TEST(ID INT PRIMARY KEY, NAME VARCHAR)");
		
//		stat.execute("CALL FTL_CREATE_INDEX('PUBLIC', 'TEST', NULL)");
		
		// insert table
//		stat.execute("INSERT INTO TEST VALUES(5, '中文')");
//		stat.execute("INSERT INTO TEST VALUES(6, '发牢骚')");
//		stat.execute("INSERT INTO TEST VALUES(7, '党发')");
//		stat.execute("INSERT INTO TEST VALUES(8, '爱上的发问')");
//		stat.execute("INSERT INTO TEST VALUES(9, '1 2 3 4 a b c d f')");
		
		
		// retrive data
		ResultSet result = stat.executeQuery("SELECT * FROM GOODS_INFO LIMIT 50");
		while (result.next()) {
		    System.out.println(result.getInt("ID") + ":" + result.getString("NAME") + ":" + result.getString("STYLE_NUM") + ":" + result.getString("FK_ORG_ID"));
		}
		
		// drop table
		//stat.execute("DROP TABLE TEST");
		//result = FullTextLucene.searchData(conn, "John", 0, 0);
		System.out.println("==================================================");
		result = stat.executeQuery("SELECT T.* FROM FTL_SEARCH_DATA('TB1320*', 50, 0) FT, GOODS_INFO T WHERE FT.TABLE = 'GOODS_INFO' AND T.ID = FT.KEYS[0] AND T.FK_ORG_ID = 1500008 ORDER BY FT.SCORE DESC");
		while (result.next()) {
			System.out.println(result.getInt("ID") + ":" + result.getString("NAME") + ":" + result.getString("STYLE_NUM") + ":" + result.getString("FK_ORG_ID"));
		}
		
		result.close();
		stat.close();
		conn.close();
		
		
		
		cp.dispose();
	}
}
