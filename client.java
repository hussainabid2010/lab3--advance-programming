package lab3;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
	public static void main(String[] args){
		
		String serverName = "localhost";
		int port = 1025;
		
		try{
			Socket sSocket = new Socket(serverName, port);
			DataOutputStream toServer_string = new DataOutputStream(sSocket.getOutputStream());
			
			InputStream fromServer = sSocket.getInputStream();
			OutputStream toServer = sSocket.getOutputStream();
			DataInputStream in = new DataInputStream(fromServer);
			ObjectOutputStream oos = new ObjectOutputStream(toServer);
			
			Scanner cin = new Scanner(System.in);
			System.out.print("Enter (0) to Download Notes or (1) to Store to the server : ");
			String choice = cin.nextLine();
			
			if(choice.equals("0")){
			//Download Notes
				System.out.println("Enter username: ");
				String uName = cin.nextLine();
				
				toServer.write(0); //Tell server that we're downloading
				toServer_string.writeUTF(uName);
				//For checking
				try {
					  Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				
				System.out.println("Received: ");
				System.out.println(in.readUTF());
				
			}else if(choice.equals("1")){
                                toServer.write(1);
                                
				System.out.println("Enter username: ");
				String uname = cin.nextLine();
                                
                                System.out.println("Enter Data: ");
				String Data = cin.nextLine();
                                
                                toServer_string.writeUTF(uname);
                                toServer_string.writeUTF(Data);
				
				
			}else{
				System.out.println("Invalid Choice");
			}
			
			//toServer.writeUTF("Sup, I'm a client");
			//System.out.println("Server says: " +in.readUTF());
			
			sSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
