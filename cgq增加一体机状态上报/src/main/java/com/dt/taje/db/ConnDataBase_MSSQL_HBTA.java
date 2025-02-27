package com.dt.taje.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@SuppressWarnings("all")
public class ConnDataBase_MSSQL_HBTA {
	//private static DataSource ds = null;

	public int update(String sql, String param) throws Exception {
		int i = 0;
		Connection localConnection = null;
		PreparedStatement localStatement = null;
		try {
			localConnection = getConn();
			localStatement = localConnection.prepareStatement(sql);
			if (!"".equals(param)) {
				localStatement.setString(1, param);
			}
			i = localStatement.executeUpdate();
		} catch (Exception localException) {
			localException.printStackTrace();
			System.out.println("数据库更新出错：" + sql);
		} finally {
			closeConnection(localConnection);
		}
		return i;
	}

	public void closeConnection(Connection paramConnection)
			throws Exception {
		if (paramConnection != null) {
			try {
				paramConnection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int executeUpdate(String paramString) throws Exception {
		System.out.println(paramString);
		int i = 0;
		Connection localConnection = null;
		Statement localStatement = null;
		try {
			localConnection = getConn();
			localStatement = localConnection.createStatement();
			i = localStatement.executeUpdate(paramString);
		} catch (Exception localException) {
			System.out.println("修改数据库时发生错误！"+paramString);
		} finally {
			closeConnection(localConnection);
		}
		return i;
	}
	public List executeQuery(String paramString) throws Exception {
		ArrayList localArrayList = new ArrayList();
		Connection localConnection = null;
		Statement localStatement = null;
		ResultSet localResultSet = null;
		try {
			localConnection = getConn();
			localStatement = localConnection.createStatement();
			localResultSet = localStatement.executeQuery(paramString);
			ResultSetMetaData localResultSetMetaData = localResultSet
					.getMetaData();
			while (localResultSet.next()) {
				HashMap localHashMap = new HashMap();
				for (int i = 1; i <= localResultSetMetaData.getColumnCount(); ++i) {
					localHashMap.put(localResultSetMetaData.getColumnName(i),
							(localResultSet.getString(i) == null) ? ""
									: localResultSet.getString(i));
					localHashMap.put(localResultSetMetaData.getColumnName(i)
							.toLowerCase(),
							(localResultSet.getString(i) == null) ? ""
									: localResultSet.getString(i));
				}
				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			System.out.println("提取数据时发生错误！"+paramString);
			localException.printStackTrace();
		} finally {
			if (localResultSet != null)
				localResultSet.close();
			closeConnection(localConnection);
		}
		return localArrayList;
	}
	
	public List executeQuery(String paramString,Connection conn) throws Exception {
		ArrayList localArrayList = new ArrayList();
		Connection localConnection = null;
		Statement localStatement = null;
		ResultSet localResultSet = null;
		try {
			localConnection = conn;
			localStatement = localConnection.createStatement();
			localResultSet = localStatement.executeQuery(paramString);
			ResultSetMetaData localResultSetMetaData = localResultSet
					.getMetaData();
			while (localResultSet.next()) {
				HashMap localHashMap = new HashMap();
				for (int i = 1; i <= localResultSetMetaData.getColumnCount(); ++i) {
					localHashMap.put(localResultSetMetaData.getColumnName(i),
							(localResultSet.getString(i) == null) ? ""
									: localResultSet.getString(i));
					localHashMap.put(localResultSetMetaData.getColumnName(i)
							.toLowerCase(),
							(localResultSet.getString(i) == null) ? ""
									: localResultSet.getString(i));
				}
				localArrayList.add(localHashMap);
			}
		} catch (Exception localException) {
			System.out.println("提取数据时发生错误！"+paramString);
			localException.printStackTrace();
		} finally {
			if (localResultSet != null)
				localResultSet.close();
			if (localStatement != null)
				localStatement.close();

		}
		return localArrayList;
	}
	

	public  Connection getConn() {
		Connection conn = null;
		Context context = null;
		DataSource dataSource = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("jdbc/oracle-mssql-hbta");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}