package com.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.sqlMap.SqlMapConfig;

public class CommonDAOImpl implements CommonDAO{
	
	private SqlMapClient sqlMap;
	
	public CommonDAOImpl() {
		this.sqlMap = SqlMapConfig.getSqlMapInstance();
	}
	public static CommonDAO getInstance() {
		
		//CommonDAO dao = new CommonDAOImpl();
		return new CommonDAOImpl();
	}
	
	@Override
	public void insertData(String id, Object value) throws SQLException {
		
		try {
			
			sqlMap.startTransaction();
			
			sqlMap.insert(id,value); //DB에 데이터를 넣는 실제 메소드 작업
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
			//insert하다가 에러가 발생하면 commint을 못하고 에러출력을 한다.
			//이렇게 되면 db에서 락이 걸리기 때문에 다른 사람이 접근할 수 없는 문제를 해결하기 위해
			//트랜젝션을 닫는 작업이 필요하다.
		}
		
	}

	@Override
	public int updateData(String id, Object value) throws SQLException {
		
		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.update(id,value);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
		}
		
		return result;
	}

	@Override
	public int updateData(String id, Map<String, Object> map) throws SQLException {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.update(id,map);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
		}
		
		return result;
	}

	@Override
	public int deleteData(String id, Object value) throws SQLException {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id,value);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
		}
		
		return result;
	}

	@Override
	public int deleteData(String id, Map<String, Object> map) throws SQLException {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id,map);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
		}
		
		return result;
	}

	@Override
	public int deleteAllData(String id) throws SQLException {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}finally {
			sqlMap.endTransaction();
		}
		
		return result;
	}

	@Override
	public Object getReadData(String id) {
				
		try {
			
			return sqlMap.queryForObject(id);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	@Override
	public Object getReadData(String id, Object value) {
		
		try {
			
			return sqlMap.queryForObject(id,value);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	@Override
	public Object getReadData(String id, Map<String, Object> map) {
		
		try {
			
			return sqlMap.queryForObject(id,map);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	@Override
	public int getIntValue(String id) {
		
		try {
			
			return ((Integer)sqlMap.queryForObject(id)).intValue();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	@Override
	public int getIntValue(String id, Object value) {

		try {
			
			return ((Integer)sqlMap.queryForObject(id,value)).intValue();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	@Override
	public int getIntValue(String id, Map<String, Object> map) {

		try {
			
			return ((Integer)sqlMap.queryForObject(id,map)).intValue();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListData(String id) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListData(String id, Object value) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id,value);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListData(String id, Map<String, Object> map) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id,map);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return null;
	}
	
	

}
