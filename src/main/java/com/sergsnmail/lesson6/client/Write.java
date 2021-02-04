package com.sergsnmail.lesson6.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Write implements Runnable{

	private DataOutputStream out;
	
	public Write(DataOutputStream out){
		this.out= out;
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String outMessage = sc.nextLine();
			try {
				out.writeUTF(outMessage);
				out.flush();
			} catch (IOException e) {
				System.err.println("[DEBUG] Write: Error while send message");				
				break;
			}
		}		
		sc.close();		
	}
	
}
