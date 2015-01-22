package com.basics.xmlParsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ReadDB {

	static String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;user=sa;password=root;database=LogAnalyzer";

	static Map<String, ActionalLogAnalyzer> readDbMapperFromDB() throws SQLException {

		Map<String, ActionalLogAnalyzer> resultMap = new HashMap<String, ActionalLogAnalyzer>();

		Connection conn = DriverManager.getConnection(CONNECTION_URL);
		Statement stmt = conn.createStatement();
		ResultSet rs;

		rs = stmt.executeQuery("SELECT * FROM ActionalLogAnalyzer");
		while ( rs.next() ) {

			ActionalLogAnalyzer dbMapper = new ActionalLogAnalyzer();
			String exceptionCode = rs.getString("ExceptionMsgCode");
			dbMapper.setExceptionMsg(exceptionCode);
			dbMapper.setExceptionMsgCode(rs.getString("ExceptionMsg"));
			dbMapper.setType(rs.getString("Type"));
			dbMapper.setCause(rs.getString("Cause"));
			dbMapper.setResolution(rs.getString("Resolution"));
			dbMapper.setNotes(rs.getString("Notes"));
			dbMapper.setReviewed(Integer.parseInt(rs.getString("Reviewed")));
			
			resultMap.put(exceptionCode, dbMapper);
		}
		conn.close();

		return resultMap;
	}


	static void writeDbMapperToDB(ActionalLogAnalyzer actionalLogAnalyzer) throws SQLException {

		Map<String, ActionalLogAnalyzer> resultMap = new HashMap<String, ActionalLogAnalyzer>();

		Connection conn = DriverManager.getConnection(CONNECTION_URL);
		PreparedStatement stmt = null;
		ResultSet rs;

		StringBuilder insertQuery = new StringBuilder();

		final String COMMA = ",";
		final String QUOTE = "'";

		try {
			insertQuery.append("INSERT INTO ActionalLogAnalyzer values (?, ?, ?, ?, ?, ?, ?)");
			/*.append(QUOTE).append(actionalLogAnalyzer.getExceptionMsgCode()).append(QUOTE).append(COMMA)
			.append(QUOTE).append(actionalLogAnalyzer.getExceptionMsg()).append(QUOTE).append(COMMA)
			.append(QUOTE).append(actionalLogAnalyzer.getType()).append(QUOTE).append(COMMA)
			.append(QUOTE).append(actionalLogAnalyzer.getCause()).append(QUOTE).append(COMMA)
			.append(QUOTE).append(actionalLogAnalyzer.getResolution()).append(QUOTE).append(COMMA)
			.append(QUOTE).append(actionalLogAnalyzer.getNotes()).append(QUOTE).append(COMMA)
			.append(actionalLogAnalyzer.getReviewed())
			.append(")");*/

			System.out.println(insertQuery.toString());
			
			stmt = conn.prepareStatement(insertQuery.toString());
			
			stmt.setString(1, actionalLogAnalyzer.getExceptionMsgCode());
			stmt.setString(2, actionalLogAnalyzer.getExceptionMsg());
			stmt.setString(3, actionalLogAnalyzer.getType());
			stmt.setString(4, actionalLogAnalyzer.getCause());
			stmt.setString(5, actionalLogAnalyzer.getResolution());
			stmt.setString(6, actionalLogAnalyzer.getNotes());
			stmt.setInt(7, actionalLogAnalyzer.getReviewed());
			

			stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			conn.close();
		}

	}



}
