package com.basics.xmlParsers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 *  Class for creating a sample gui for the application.
 */

public class AppTest extends JFrame {
	
	public AppTest(){
		super("My Test");
	}
	
	public static void main(String[] args){
		AppTest appTest = new AppTest();
		appTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appTest.add(new JButton("Click")).addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent mouseEvent) {
			      System.out.println("I'm pressed: " + mouseEvent);
			}
			
		});
		appTest.setSize(400,400);
		appTest.setVisible(true);
	}

}
