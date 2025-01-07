package com.clientserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(5000)) {

			try (Socket socket = serverSocket.accept(); // accept() return socket. At this point, app will block here
			// waiting for client to initiate a connection. Once connected, communication can occur by reading info
			// from the socket or passing information to the socket.
			) {
				System.out.println("Server accepted client connection");
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String echoString = input.readLine();
					System.out.println("Server got request data: " + echoString);
					if (echoString.equalsIgnoreCase("exit")) {
						break;
					}
					output.println("Echo from server: " + echoString);
				}
			}

		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
		}
	}
}
