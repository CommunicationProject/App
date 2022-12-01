import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PracticeClient {
   public static DataOutputStream OUT = null;
          static Socket client = null;
          static DataInputStream IN = null;
          public static String name;

    
    public static void main(String[] args) {
        PracticeClient chatter = new PracticeClient();
      	chatter.run();
      	chatter.StartChat();
    }
    
    

    public static String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	private void run() {
        try {
            String ip = JOptionPane.showInputDialog("Enter the IP address server is on: ");
            String port = JOptionPane.showInputDialog("Enter the port number server is listening on:  ");
            
                        
            client = new Socket(ip, Integer.parseInt(port));
            OUT = new DataOutputStream(client.getOutputStream());
            IN = new DataInputStream(client.getInputStream());
            
        } catch (UnknownHostException ex) {
        	ex.printStackTrace();
            System.exit(1);
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
    }

    private void StartChat() {
        new SimpleGUI();
        Thread listenFromServer = new Thread(new ServerListener());
        listenFromServer.start();
        
        }
}