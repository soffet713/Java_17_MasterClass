package com.clientserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedSimpleServer {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(5000)) {

			while (true) {
				Socket socket = serverSocket.accept(); // accept() return socket. At this point, app will block here
					 // waiting for client to initiate a connection. Once connected, communication can occur by reading info
					 // from the socket or passing information to the socket.

				System.out.println("Server accepted client connection");
				socket.setSoTimeout(900_000);
				executorService.submit(() -> handleClientRequest(socket));
			}

		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
		}
	}

	private static void handleClientRequest (Socket socket) {

		try (socket;
			 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			 ) {

			while (true) {
				String echoString = input.readLine();
				System.out.println("Server got request data: " + echoString);
				if (echoString.equalsIgnoreCase("exit")) {
					break;
				}
				output.println("Echo from server: " + echoString);
			}

		} catch (Exception e) {
			System.out.println("Client socket shut down here.");
		}
	}
}
