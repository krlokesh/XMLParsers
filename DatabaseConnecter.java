package com.basics.xmlParsers;

import java.sql.Connection;
import java.sql.SQLException;

/**
* 
* Creates a connection to a database.
* 
* @author Tino
* @created 03.12.2008
* 
*/
public interface DatabaseConnecter {

	/**
	* Establishes a new connection to the database
	* 
	* @return A new connection to the database
	* @throws SQLException
	*/
	public Connection createConnection() throws SQLException;
	
	/**
	* Returns the connection url
	* 
	* @return
	*/
	public String getConnectionUrl();
}