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


}
