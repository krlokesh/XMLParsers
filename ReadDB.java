package com.basics.xmlParsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ReadDB {
	
	static Map<String, ActionalLogAnalyzer> readDbMapperFromDB() throws SQLException {
		
		Map<String, ActionalLogAnalyzer> resultMap = new HashMap<String, ActionalLogAnalyzer>();
			
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=root;database=LogAnalyzer");
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
            
            resultMap.put(exceptionCode, dbMapper);
        }
        conn.close();
		    
		return new HashMap<String, ActionalLogAnalyzer>();
	}

}
