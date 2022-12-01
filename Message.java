import java.io.Serializable;

public class Message implements Serializable {
	static final int Active = 0, Message = 1, Logout = 2, CreateGroup = 3, GroupMsg = 4;
	//active users, message, logout, createGroup, GroupMessage
    protected String message;
    protected int type;

    public Message(int type, String message){
        this.message = message;
        this.type = type;
    }

   

    String getMessage(){
       return message;
    }


   int getType(){
       return type;
    }

}