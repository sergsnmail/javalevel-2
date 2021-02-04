package com.sergsnmail.lesson6.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Write implements Runnable {

	private DataOutputStream out;

	public Write(DataOutputStream out) {
		this.out = out;
	}

	public void run() {
		String outMessage;
		Scanner sc = new Scanner(System.in);
		try {
			while (true) {
				outMessage = sc.nextLine();
				out.writeUTF(outMessage);
				out.flush();
			}
		} catch (IOException e) {
			sc.close();
		}
	}
}
