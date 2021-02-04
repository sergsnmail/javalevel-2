package com.sergsnmail.lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private final static int PORT = 5000;
	private final static String HOST = "localhost";

	public Client() {
		try (Socket socket = new Socket(HOST, PORT)) {
			handler(socket);
		} catch (IOException e) {
			System.err.printf("[DEBUG] Could not connect to the server \"%s\" by port \"%s\"", HOST, PORT);
		}
	}

	public static void main(String[] args) {
		new Client();
	}

	private void handler(Socket socket) throws IOException {
		System.out.printf("[DEBUG] Connected to server %s:%s\n", HOST, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());

		Thread writeThread = new Thread(new Write(out));
		writeThread.setDaemon(true);
		writeThread.start();

		String inMessage;
		while (true) {
			try {
				inMessage = in.readUTF();
			} catch (IOException e) {
				System.err.println("[DEBUG] Connection lost. Server not responding");
				break;
			}

			System.out.println("Server say: " + inMessage);
			if (inMessage.equals("/disconnect")) {
				break;
			}
		}

		out.close();
		in.close();
		socket.close();

		System.out.println("[DEBUG] Client exit");
	}
}
