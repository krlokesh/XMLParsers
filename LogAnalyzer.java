package com.basics.xmlParsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LogAnalyzer {
		
	public static void doAnalyze(String outCSVFilePath, String outFilePath,
			ArrayList<FileAndServerName> fileAndServerNames) throws FileNotFoundException, SQLException {
		
		boolean firstWrite = true;
		
		File outFile = new File(outFilePath), 
				outCSVFile = new File(outCSVFilePath);
		
		PrintWriter writer = new PrintWriter(outFile);
		writer.print("");
		writer.close();
		writer = new PrintWriter(outCSVFile);
		writer.print("");
		writer.close();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		FileOutputStream out = new FileOutputStream(outFilePath);
		FileOutputStream outCSVFileOS = new FileOutputStream(outCSVFilePath);
		
		//TODO: populate dbMap from db
		Map<String, ActionalLogAnalyzer> dbMapper = ReadDB.readDbMapperFromDB();
		System.out.println("dbMapper.size() : " + dbMapper.size());
		
		Set<String> exceptionCodeList = new HashSet<String>();
		
		BufferedReader br = null;
					
		try {
			
			for(FileAndServerName fileAndServerName : fileAndServerNames){
				
				String inputFilePath = fileAndServerName.fileName,
								serverName = fileAndServerName.serverName;
				
				File inputFile = new File(inputFilePath);
						
				if(firstWrite){
					outCSVFileOS.write((new String("\"Server Name\",\"Log File Name\",\"ExceptionCode\",\"Errors/Exceptions\",\"Type\",\"Cause\",\"Resolutions\",\"Notes\"\r\n").getBytes()));
					firstWrite = false;
				}
				
				String sCurrentLine;
				String messageId;
				ExceptionMessageType errorType;
				br = new BufferedReader(new FileReader(inputFilePath));
				while ((sCurrentLine = br.readLine()) != null) {
					if (sCurrentLine.indexOf("<severity>ERROR</severity>") != -1 || sCurrentLine.indexOf("<severity>WARNING</severity>") != -1) {
						
						// get the exception type
						if(sCurrentLine.indexOf("<severity>ERROR</severity>") != -1)
							errorType = ExceptionMessageType.ERROR;
						else
							errorType = ExceptionMessageType.WARNING;
							
						//get the message id
						String msgIdStartTag = "<msgid>";
						String msgIdEndTag = "</msgid>";
						messageId = sCurrentLine.substring(sCurrentLine.indexOf(msgIdStartTag) + msgIdStartTag.length(), sCurrentLine.indexOf(msgIdEndTag));
												
						//get the message
						String msgStartTag = "<msg>";
						String msgEndTag = "</msg>"	;	
						String msg = "";
						
						String nextLine = br.readLine();
						if(nextLine == null) {
							nextLine = "";
						}
						
						
						while(nextLine.indexOf(msgStartTag) == -1)
							nextLine = br.readLine();
						// Case 1: Single Line Message
						if(nextLine.indexOf(msgEndTag) != -1){
							msg = nextLine.substring(nextLine.indexOf(msgStartTag) + msgStartTag.length(), nextLine.indexOf(msgEndTag));
							
						}
						// Case 2: Multiple Line Message
						else{
							nextLine = nextLine.substring(nextLine.indexOf(msgStartTag) + msgStartTag.length());
							while(nextLine.indexOf(msgEndTag) == -1 ){
								msg += ("\n" + nextLine);
								nextLine = br.readLine();
							}
							//In case we have some message part before the end tag in the same line
							if(nextLine.indexOf(msgEndTag) != -1 && nextLine.trim().length() > msgEndTag.length())
								msg += ("\n" + nextLine.substring(0, nextLine.indexOf(msgEndTag)));
						}	
							
												
						if(dbMapper.keySet().contains(messageId)){
		            		if(!exceptionCodeList.contains(messageId)){
		            			outCSVFileOS.write(new String("\"" + serverName + "\",\"" + inputFile.getName() + "\",\"" + messageId + "\",\"" 
		            		+ msg.trim() + "\"," + dbMapper.get(messageId).toCSVString() + "\r\n").getBytes());
		            			
		            			//if the Review == 0 then write to the unknown error list
		            			if(dbMapper.get(messageId).getReviewed() == 0)
		            				out.write(new String("ServerName : " +serverName + " \n FileName : " + inputFile.getName()  +" \n Severity : " + errorType  + " \n MessageId : " + messageId + "\n Message : " + msg
			            				+ "\n ------------------------------------------------------- \n").getBytes());
		            	   		exceptionCodeList.add(messageId);
		            		}
		            	}
		            	else{
		            		if(!exceptionCodeList.contains(messageId)){
		            			//writing to txt file
		            			out.write(new String("ServerName : " +serverName + " \n FileName : " + inputFile.getName()  +" \n Severity : " + errorType  + " \n MessageId : " + messageId + "\n Message : " + msg
		            				+ "\n ------------------------------------------------------- \n").getBytes());
		            			exceptionCodeList.add(messageId);
		            			
		            			//writing to the db
		            			ActionalLogAnalyzer actionalLogAnalyzer = new ActionalLogAnalyzer();
		            			actionalLogAnalyzer.setCause("");
		            			actionalLogAnalyzer.setExceptionMsg(msg);
		            			actionalLogAnalyzer.setExceptionMsgCode(messageId);
		            			actionalLogAnalyzer.setNotes("");
		            			actionalLogAnalyzer.setResolution("");
		            			actionalLogAnalyzer.setReviewed(0);
		            			actionalLogAnalyzer.setType(errorType.toString());
		            			
		            			ReadDB.writeDbMapperToDB(actionalLogAnalyzer);
		            		}
		            	}
						nextLine = nextLine.trim();
						String str = sCurrentLine + "\n" + nextLine + "\n\n";
						Integer count = map.get(nextLine);
						if( count != null) {
							map.put(nextLine, ++count);
						} else {
							map.put(nextLine, 1);
						}
					}
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {br.close();}
				out.close();
				outCSVFileOS.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("Done");
		
	}
	
}
