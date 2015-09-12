import java.net.*;
import java.io.*;
import java.util.*;

public class TCP_Chat
{
	public static void main (String args[])
	{
		try
		{
			System.out.println("TCP_Chat startup");
		
			int my_port = Integer.parseInt(args[1]);
			int other_port = Integer.parseInt(args[2]);
			String ip = args[0];
		
			Server server = new Server(my_port);
			server.start();
		
			System.out.println("Press when server is online");
			System.in.read();
		
			Socket client = new Socket(ip, other_port);
		
			DataOutputStream writeToServer = new DataOutputStream(client.getOutputStream());
				
			System.out.println("Connected to Server at IP " + client.getLocalAddress());

			BufferedReader readFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
		
			String line;
		
			do
			{
				line = readFromKeyboard.readLine();
			
				System.out.println("Client: " + line);
				writeToServer.writeChars(line + "\n	");
				
			}while ( line.length() > 0 );
			
			readFromKeyboard.close();
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}

class Server extends Thread
{
	ServerSocket server;
	
	public Server(int port)
	{
		try
		{
			this.server = new ServerSocket(port);
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void run()
	{
		try
		{
			while (true)
			{
				Socket toClient = this.server.accept();
	
				System.out.println("Connection accepted from IP " + toClient.getLocalAddress());
	
				BufferedReader readFromClient = new BufferedReader(new InputStreamReader(toClient.getInputStream()));

				String line;

				do
				{
					line = readFromClient.readLine();
	
					System.out.println("From Client: " + line);
		
				}while ( line.length() > 0 );
	
				readFromClient.close();	
	
				toClient.close();			
			}
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
