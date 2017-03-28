import java.time.LocalDate;

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Note {
	
	String text;
	LocalDate visibDate;

	/**
	 * 
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
