package com.util.sqlMap;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {
	
	private static final SqlMapClient sqlMap;
	
	static {
		
		try {
			
			String source = "com/util/sqlMap/sqlMapConfig.xml";
			
			Reader reader = Resources.getResourceAsReader(source);
			
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException("Error");
		}		
		
	}
	
	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}

}
