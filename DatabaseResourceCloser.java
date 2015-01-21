package com.basics.xmlParsers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseResourceCloser {
	
	static void close(){
		
	}

	public static void close(ResultSet resultSet, Statement statement,
			Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		connection.close();
	}

}
