package com.basics.xmlParsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecterImpl implements DatabaseConnecter {

	@Override
	public Connection createConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=root;database=LogAnalyzer");
		//return null;
	}

	@Override
	public String getConnectionUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}
