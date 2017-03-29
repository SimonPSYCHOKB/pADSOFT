import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 */

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the notes
 *
 */
public class Note implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String text;
	LocalDate visibDate;

	/**
	 * Constructor
	 * 
	 * @param text - String with the notes
	 * @param date - LocalDate that sets when the notes will be visible
	 */
	public Note(String text, LocalDate date) {
		this.text= text;
		this.visibDate= date;
	}
	
	public String toString(){
		String str= "-->Note: "+ text;
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (visibDate == null) {
			if (other.visibDate != null)
				return false;
		} else if (!visibDate.equals(other.visibDate))
			return false;
		return true;
	}
	
	


}
