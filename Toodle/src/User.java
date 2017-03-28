import java.util.List;
/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public abstract class User {

	/**
	 * 
	 */
	protected String fName;
	protected String lName;
	protected String password;
	protected String email;
	protected List<Notification> sentNotifications;
	protected List<Notification> recievedNotifications;
	
	
	public User(String fname,String lname,  String password, String email){
		this.fName = fname;
		this.lName = lname;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return fName;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword(){
		return password;
	}

	public List<Notification> getSentNotifications() {
		return sentNotifications;
	}

	public List<Notification> getRecievedNotifications() {
		return recievedNotifications;
	}

	public void setName(String name) {
		this.fName = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSentNotifications(List<Notification> sentNotifications) {
		this.sentNotifications = sentNotifications;
	}

	public void setRecievedNotifications(List<Notification> recievedNotifications) {
		this.recievedNotifications = recievedNotifications;
	}

	/**
	 * @param args
	 */
	public boolean ValidatePassword(String attempt){
		if (this.password.equals(attempt)) {
			return true;
		}
		else return false;
	}
	
	public void checkForNotifications(){
		
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

}
