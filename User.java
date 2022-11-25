
public class User {

    private String username;
    private String password;
    public String status;
    
    
    
    
    public User(String username, String password, String status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
    

	public void setStatus(String status) {
		this.status = status;
	}


   
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
