import java.net.*;
import java.io.*;
import java.util.*;

public class TCP_Chat
{
	public void envia (String IP, String msg)
	{
		try
		{
			//System.out.println("TCP_Chat startup");
		
			int my_port = 8080;//Integer.parseInt(args[1]);
			int other_port = 8080;//Integer.parseInt(args[2]);
			String ip = IP;
		
			Server server = new Server(my_port);
			server.start();
		
			//System.out.println("Press when server is online");
			//System.in.read();
		
			Socket client = new Socket(ip, other_port);
		
			DataOutputStream writeToServer = new DataOutputStream(client.getOutputStream());
				
			//System.out.println("Connected to Server at IP " + client.getLocalAddress());

//			BufferedReader readFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
		
			//String line;
		
//			do
//			{
				//line = readFromKeyboard.readLine();
			
				//System.out.println("Client: " + line);
				//writeToServer.writeChars(line + "\n	");
//			}while ( msg.length() > 0 );
			if( msg.length() > 0 ){writeToServer.writeChars(msg + "\n");}
			
//			readFromKeyboard.close();
			client.close();
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}
}

class Server extends Thread
{
	ServerSocket server;
	private Socket toClient;
	private BufferedReader buf;
	private String line;
	
	public Server(int port)
	{
		try
		{
			this.server = new ServerSocket(port);
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void run()
	{
		try
		{
			while(true)
			{
				toClient = this.server.accept();
	
				//System.out.println("Connection accepted from IP " + toClient.getLocalAddress());
	
				this.buf = new BufferedReader(new InputStreamReader(toClient.getInputStream()));

				do
				{
					line = buf.readLine();
	
					//System.out.println("From Client: " + line);
					
				}while ( line.length() > 0 );
	
				buf.close();	
	
				toClient.close();			
			}
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}

	
	public BufferedReader getBuf()
	{
		return buf;
	}
	
	public String getLine()
	{
		return line;
	}
}