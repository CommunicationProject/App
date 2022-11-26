import java.util.Scanner

public class GroupChat {
	public String groupID;
	public String groupName;
	private Message Message;
	private ChatHistory Chat;
	
	public void getMessage() {
		System.out.println(Message.getText());
	}
	
	public void setMessage() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter message type: ");
		String type = in.nextLine();
		Message.setType(type);
		System.out.println("Enter message status: ");
		String status = in.nextLine();
		message.setStatus(status);
		System.out.println("Enter message text: ");
		String text = in.nextLine();
		Message.setText(text);
		in.close();
	}
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
	
	public String search(String text) {
		for (int i = 0; i < Chat.messageList.length; i++) {
			if (Chat.messageList[i] == text) {
				return Chat.messageList[i];
			}
		}
		return null;
	}
}
