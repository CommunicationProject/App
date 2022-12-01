import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



public class ClientHandler implements Runnable{
    Socket newClient;
    String name;
    DataInputStream IN;
    DataOutputStream OUT;
    
        
   public ClientHandler(Socket s)
	{
            this.newClient = s;
            this.name = name;
	}
   
    @Override
    public void run() {
        try {
            try {
                IN = new DataInputStream(newClient.getInputStream());
                OUT = new DataOutputStream(newClient.getOutputStream());
                name = JOptionPane.showInputDialog("Enter your name: ");
                String hostName = newClient.getInetAddress().getHostName();
                SimpleServer.add(OUT);
                String str;
                try{
                    while((str = IN.readUTF())!=null)
                    {
                    SimpleServer.sendToAll(name +": "+str);
                    }
                }catch(Exception ex){
                	System.out.println(ex);
                } 
                }catch (IOException ex) {
                	ex.printStackTrace();
                    System.exit(1);
                } 
        SimpleServer.remove(OUT);
                IN.close();
                OUT.close();
                newClient.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
     }
}