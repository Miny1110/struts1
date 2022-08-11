package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
	//연결자를 반환한다. 연결자가 반환되면 DB연결이 된 것이다.
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "suzi";
		String pwd = "a123";
		
		if(conn==null) {
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return conn;
	
	}
	
	public static void close() {
		
		if(conn==null) {
			return;
		}
		
		try {
			
			if(!conn.isClosed()) {
				conn.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		conn = null;
		//conn을 비워주지 않으면 두번째 연결부터는 어댑터 오류가 발생하면서 DB 연결아 되지 않는다.
	}
}
