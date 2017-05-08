package application;
import java.io.Serializable;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the notes
 *
 */
public class Note extends LearningObj implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	private Unit unit;

	/**
	 * Constructor
	 * 
	 * @param text - String with the notes
	 * @param visibility - boolean that sets whether a note is visible
	 */
	public Note(String text, boolean visibility) {
		super(visibility);
		this.text= text;
	}
	
	/**
	 * This method returns the text from the note
	 * @return String with the text
	 */
	public String getTextNotes(){
		return text;
	}
	
	/**
	 * This method returns the unit of the note
	 * @return Unit 
	 */
	public Unit getUnit(){
		return unit;
	}
	
	/**
	 * This methos modifies the text of the note
	 * @param text - new text of the note
	 */
	public void setTextNotes(String text){
		this.text = text;
	}
	
	/**
	 * This method modifies the unit of the note
	 * @param unit - new unit of the note
	 */
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	@Override
	public String toString(){
		String str= "-->Note: "+ text;
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
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
		return true;
	}

}
