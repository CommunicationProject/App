import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class SimpleServer {

  
    Socket individualServer = null;
    static String portNumber = null;
   
    public static List<DataOutputStream> OUTALL 
            = new ArrayList<DataOutputStream>();
    

    
    synchronized public static void add(DataOutputStream opStream){
        OUTALL.add(opStream);
    }
    
     synchronized public static void remove(DataOutputStream opStream) {
        OUTALL.remove(opStream);
    }
    synchronized  public static void sendToAll(String msg){
        for(DataOutputStream dop:OUTALL)
        {
            try {
                dop.writeUTF(msg);
            } catch (IOException ex) {
            	ex.printStackTrace();
                System.exit(1);
            }
        }    
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        // TODO code application logic here
        SimpleServer listener = new SimpleServer();  
        listener.setUp();
        listener.Threads(7777);
        
    }

    
    private void setUp() {
         portNumber = JOptionPane.showInputDialog("Enter the port number "
                 + "to start the server: ");
    }
    
    private void Threads(int port) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            
            while(true)
                    {
                    individualServer = ss.accept();
                    Thread clientThread = new Thread
                            (new ClientHandler(individualServer));
                    clientThread.start();
                    }
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
    }   
}