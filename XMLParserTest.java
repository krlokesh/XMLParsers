package com.basics.xmlParsers;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;



public class XMLParserTest {
	
	
	public static void doAnalyze(String outCSVFilePath, String outFilePath, ArrayList<FileAndServerName> fileAndServerNames ) throws SQLException, FileNotFoundException{
		
		boolean firstWrite = true;
				
		File outFile = new File(outFilePath), 
				outCSVFile = new File(outCSVFilePath);
		
		PrintWriter writer = new PrintWriter(outFile);
		writer.print("");
		writer.close();
		writer = new PrintWriter(outCSVFile);
		writer.print("");
		writer.close();
		
		
		for(FileAndServerName fileAndServerName : fileAndServerNames){
					
				String inputFilePath = fileAndServerName.fileName,
								serverName = fileAndServerName.serverName;
			
		
			File inputFile = new File(inputFilePath);
				
			
			
			SAXReader reader = new SAXReader();
			BufferedWriter bufferedCSVWriter  = null,
					bufferedErrorWriter = null ;
			
			Set<String> exceptionCodeList = new HashSet<String>();
			
			Map<String, DBMapper> dbMapper = readDbMapperFromDB();
			
			
			
			try {
				
				
				bufferedCSVWriter = new BufferedWriter(new FileWriter(outCSVFile, true));
				bufferedErrorWriter = new BufferedWriter(new FileWriter(outFile, true));
				Document document = reader.read(inputFile);
				
				if(firstWrite){
					bufferedCSVWriter.write("\"Server Name\",\"Log File Name\",\"ExceptionCode\",\"Errors/Exceptions\",\"Type\",\"Cause\",\"Resolutions\",\"Notes\"\r\n");
					firstWrite = false;
				}
				
				System.out.println("Root element :" + document.getRootElement().getName());
	
		        Element classElement = document.getRootElement();
	
		        List<Node> nodes = document.selectNodes("/eventlog/event");
		        System.out.println("----------------------------");
		        System.out.println(dbMapper.size());
		        for (Node node : nodes) {
		           	            
		        	 String severity = node.selectSingleNode("severity").getText();
		        	 
		            if(severity.matches("(WARNING)||(ERROR)")){
		            	String messageId = node.selectSingleNode("msgid").getText();
			        	String message = node.selectSingleNode("msg").getText();
		            		            	
		            	if(dbMapper.keySet().contains(messageId)){
		            		if(!exceptionCodeList.contains(messageId)){
		            	   		bufferedCSVWriter.write("\"" + serverName + "\",\"" + inputFile.getName() + "\",\"" + messageId + "\",\"" 
		            		+ message + "\"," + dbMapper.get(messageId).toString() + "\r\n");
		            	   		System.out.println("Writing to CSV" + dbMapper.get(messageId).toString());
			            		exceptionCodeList.add(messageId);
		            		}
		            	}
		            	else{
		            		if(!exceptionCodeList.contains(messageId)){
		            			bufferedErrorWriter.write("ServerName : " +serverName + " FileName : " + inputFile.getName()  +" Severity : " + severity  + " MessageId : " + messageId + "\n Message : " + message
		            				+ "\n ------------------------------------------------------- \n");
		            			exceptionCodeList.add(messageId);
		            		}
		            	}
		            }
		            
		         }
		      } catch (DocumentException e) {
		         e.printStackTrace();
		      } catch (IOException e){
		    	  e.printStackTrace();
		      } finally{
		    	  try {
					bufferedCSVWriter.close();
					bufferedErrorWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  
		      }
			}
	   }
	
		private static Map<String, DBMapper> readDbMapperFromDB() throws SQLException {
		
		Map<String, DBMapper> resultMap = new HashMap<String, DBMapper>();
			
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=root;database=LogAnalyzer");
		Statement stmt = conn.createStatement();
        ResultSet rs;

        rs = stmt.executeQuery("SELECT * FROM ActionalLogAnalyzer");
        while ( rs.next() ) {
            
            DBMapper dbMapper = new DBMapper();
            String exceptionCode = rs.getString("ExceptionMsgCode");
            dbMapper.setExceptionMsgCode(exceptionCode);
            dbMapper.setExceptionMsg(rs.getString("ExceptionMsg"));
            dbMapper.setType(rs.getString("Type"));
            dbMapper.setCause(rs.getString("Cause"));
            dbMapper.setResolution(rs.getString("Resolution"));
            dbMapper.setNotes(rs.getString("Notes"));
            System.out.println(rs.getString("Notes"));
            
            resultMap.put(exceptionCode, dbMapper);
        }
        conn.close();
		    
		return resultMap;
	}

	
	
}
