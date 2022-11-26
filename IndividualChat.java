import java.util.Scanner;

public class IndividualChat {
	private ContactList Contact;
	private Message Message;
	ChatHistory Chat;
	
	public void getMessage() {
		System.out.println(Message.getText());
	}
	
	public void setMessage() {
		Scanner in = new Scannner(System.in);
		System.out.println("Enter message type: ");
		String type = in.nextLine();
		Message.setType(type);
		System.out.println("Enter message status: ");
		String status = in.nextLine();
		Message.setStatus(status);
		System.out.println("Enter message text: ");
		String text = in.nextLine();
		Message.setText(text);
		in.close();
	}
	
	public void getNotification() {}
}
