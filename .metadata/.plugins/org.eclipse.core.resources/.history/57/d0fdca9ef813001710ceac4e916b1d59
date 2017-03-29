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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (recievedNotifications == null) {
			if (other.recievedNotifications != null)
				return false;
		} else if (!recievedNotifications.equals(other.recievedNotifications))
			return false;
		if (sentNotifications == null) {
			if (other.sentNotifications != null)
				return false;
		} else if (!sentNotifications.equals(other.sentNotifications))
			return false;
		return true;
	}
	
	

}
