package oop;
//A Java program for a Server
import java.net.*;
import java.io.*;


public class Server
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private boolean timeOver=true;
	class NewThread implements Runnable{
		Thread t;
		NewThread(){
			t=new Thread(this);
		}
		public void run() {
			try{
				Thread.sleep(30000);
				if(timeOver)System.out.println("Connection closed since client didn't respond in 30 seconds");
			}
			catch(Exception e) {
				System.out.println("Thread interuptted");
			}
		}
	}
	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			NewThread nt= new NewThread();
			System.out.println("Client accepted");
			System.out.println("have you sent the request Packet?");
			nt.t.start();
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";
			line = in.readUTF();
			if(timeOver&&line.equalsIgnoreCase("Yes")){
				System.out.println("Respond Packet sent");
				System.out.println("Closing connection");
				timeOver=false;
				socket.close();
			}
			else if(timeOver&&line.equalsIgnoreCase("No")) {
				System.out.println("Respond packet will not be sent since no request packet is received from client");
				System.out.println("Closing connection");
				timeOver=false;
				socket.close();
			}
			else {
				System.out.println("Invalid response");
				System.out.println("Closing connection");
				timeOver=false;
				socket.close();
			}
			
			// close connection 
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
