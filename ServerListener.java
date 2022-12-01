import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


class ServerListener implements Runnable {

    public ServerListener() {
    }

    String message;

    @Override
    public void run() {
        try {
            while (true) {
                message = PracticeClient.IN.readUTF();
                SimpleGUI.convoArea.append("\n" + message);
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
        try {
            PracticeClient.IN.close();
            PracticeClient.OUT.close();
            PracticeClient.client.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
    }

}