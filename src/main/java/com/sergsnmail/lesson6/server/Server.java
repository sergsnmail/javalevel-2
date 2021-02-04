package com.sergsnmail.lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private final static int PORT = 5000;
	private ServerSocket server;

	public Server() {
		try (ServerSocket server = new ServerSocket(PORT);) {
			this.server = server;
			System.out.printf("[DEBUG] Server started at port \"%s\"\n", PORT);
			handleSocket(server.accept());
		} catch (IOException e) {
			System.err.printf("[DEBUG] Can not start listening on port \"%s\"\n", PORT);
		}
	}

	private void handleSocket(Socket socket) throws IOException {
		System.out.println("[DEBUG] Client accepted");
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());

		Thread writeThread = new Thread(new Write(out));
		writeThread.setDaemon(true);
		writeThread.start();

		try {
			while (true) {
				String inMessage = in.readUTF();
				System.out.println("Client say: " + inMessage);
				if (inMessage.equals("/quit")) {
					out.writeUTF("/disconnect");
				}
			}
		} catch (IOException e) {
			out.close();
			in.close();
			socket.close();
			server.close();
			System.out.println("[DEBUG] Client disconnected");
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}
