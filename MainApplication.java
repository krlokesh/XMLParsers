package com.basics.xmlParsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainApplication {
	
	public static void main(String[] args) throws FileNotFoundException, SQLException{
	
		String folderName = "D:\\work\\loganalyzer\\input\\AMSSupport";
		
	
		ArrayList<File> files = new ArrayList<File>();
		ArrayList<FileAndServerName> listToProbe = new ArrayList<FileAndServerName>();
		
	    listf(folderName, files);
	    for(File file: files){
	    	String fileAbsolutePath = file.getAbsolutePath();
	    	if(!fileAbsolutePath.contains("console") && fileAbsolutePath.contains(".log")){
	    		System.out.println(file.getAbsolutePath() + file.getParentFile().getName());
	    		listToProbe.add(new FileAndServerName(file.getParentFile().getName(), file.getAbsolutePath()));
	    	}
	    }
	    
	    String outCSVFilePath = "D:/work/loganalyzer/output/client_output.csv",
				outFilePath = "D:/work/loganalyzer/output/UnknownErrors.txt";
	    
	    doAnalyze(outCSVFilePath, outFilePath, listToProbe);
	}
	
	public static void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	
	public static void doAnalyze(String outCSVFilePath, String outFilePath, ArrayList<FileAndServerName> fileAndServerNames) throws FileNotFoundException, SQLException{
		
		LogAnalyzer.doAnalyze(outCSVFilePath, outFilePath, fileAndServerNames);
		
	}
	
	

}
