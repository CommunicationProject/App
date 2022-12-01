import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {

    private String screenName;

    // GUI stuff
    private JTextArea  enteredText = new JTextArea(10, 32);
    private JTextField typedText   = new JTextField(32);

    // socket for connection to chat server
    private Socket socket;
    private BufferedReader in;
	private PrintWriter out;

    // for writing to and reading from the server
    

    public Client(String screenName, String hostName) {

        // connect to server
        try {
            socket = new Socket(hostName, 4444);
       	    out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception ex) { ex.printStackTrace(); }
        this.screenName = screenName;

        // close output stream  - this will cause listen() to stop and exit
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    out.close();
//                    in.close();
//                    try                   { socket.close();        }
//                    catch (Exception ioe) { ioe.printStackTrace(); }
                }
            }
        );


        // create GUI stuff
        enteredText.setEditable(false);
        enteredText.setBackground(Color.LIGHT_GRAY);
        typedText.addActionListener(this);

        Container content = getContentPane();
        content.add(new JScrollPane(enteredText), BorderLayout.CENTER);
        content.add(typedText, BorderLayout.SOUTH);


        // display the window, with focus on typing box
        setTitle("Room [" + screenName + "]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        typedText.requestFocusInWindow();
        setVisible(true);

    }

    // TextField for sending the messages
    public void actionPerformed(ActionEvent e) {
        out.println("[" + screenName + "]: " + typedText.getText());
        typedText.setText("");
        typedText.requestFocusInWindow();
    }

    //to listen from socket in order to broadcast
    public void listen() throws IOException {
        String s;
        while ((s = in.readLine()) != null) {
            enteredText.insert(s + "\n", enteredText.getText().length());
            enteredText.setCaretPosition(enteredText.getText().length());
        }
        out.close();
        in.close();
        try { 
        	socket.close();      
        	}
        catch (Exception ex) {
        
        ex.printStackTrace();
        System.exit(1);
        }
        
    }

    public static void main(String[] args) throws IOException  {
        Client client = new Client("miguel","localhost");
        client.listen();
    }
}