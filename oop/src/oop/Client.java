package oop;
//A Java program for a Client
import java.net.*;
import java.io.*;
import java.util.*;

interface requestPacketGenerator{
	String ip;
	void msg();
}
interface respondPacketGenerator{
	String ip;
	void msg();
}

public class Client
{
	String IP;
	private Socket socket		 = null;
	private DataInputStream input = null;
	private DataOutputStream out	 = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{	
		IP=address;
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");
			input = new DataInputStream(System.in);
			
			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
		System.out.println("Request sent with source IP address "+address);
		System.out.println("Server:have you sent a request packet(please respond in 'Yes'/'No')");
		String line = "";

		try
		{	
			line = input.readLine();
			out.writeUTF(line);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		// close the connection
		
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}
