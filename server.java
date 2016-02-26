package lab3;
import java.net.*;
import java.util.*;
import java.io.*;


public class Server {
	
	private static void storeData(Map<String, String> map,String key, String value){
            
		 map.put(key, value);
	}
        
        private static String printData(Map<String, String> map,String key){
            
		String result=map.get(key);
                return result;
	}
        
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		int port = 1025;
		ServerSocket listener = null;
		
                // Map declartion
                Map<String, String> map = new HashMap<String, String>();
                storeData( map,"hussain","kya haal hai");
	try {
			listener = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Running Server...");
		try{
			while(true){
				Socket cSocket = listener.accept();
				
				InputStream fromClient = cSocket.getInputStream();
				DataInputStream in_string = new DataInputStream(cSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(cSocket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(cSocket.getInputStream());
				//Check whether to send or receive
				int choice = fromClient.read();
				
				if(choice==0){
					//Send all notes according to username
					String uname = in_string.readUTF();
					System.out.println("Received: " + uname);
					String toSend = "";
					toSend= printData(map,uname);
                                         if (toSend == null)
                                         {
                                             toSend=" No such username and ";
                                         }
					System.out.println("toSend: " +toSend);
					
					out.writeUTF(toSend);	
				}
                                else if(choice==1){
                                    
                                    String key = in_string.readUTF();
                                    String value= in_string.readUTF();
                                    storeData( map, key,value);
       
				}	
				//System.out.println("Client says " +in.readUTF());
				out.writeUTF("You managed to connect. Congratulations! Now get out.");
				cSocket.close();
				}			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		listener.close();
	}
}
