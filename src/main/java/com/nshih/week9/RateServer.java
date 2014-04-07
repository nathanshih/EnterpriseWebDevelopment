/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week9;

import java.io.IOException;
import java.net.ServerSocket;

import com.nshih.utils.ClassServer;

/**
 * @author Nathan Shih
 */
public class RateServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		int portNumber = ClassServer.MY_PORT;
		
		if (args.length != 1) {
			System.err.println("Usage: java RateServer <port number>");
			System.out.println("Defaulting to port: " + portNumber);
		} else {	
			try {
				portNumber = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				System.err.println("Invalid port number");
				System.out.println("Defaulting to port: " + portNumber);
			}
		}
		
		boolean listening = true;
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				RateServerThread thread = new RateServerThread(serverSocket.accept());
				thread.run();
			}
		} catch (IOException ie) {
			System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
		}
	}
}
