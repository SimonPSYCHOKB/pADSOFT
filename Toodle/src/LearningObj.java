import java.io.Serializable;


/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This abstract class makes reference to all the learning objects that are in a course
 *
 */
public abstract class LearningObj implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean visibility;
	
	
	/**
	 * Constructor
	 * @param visibility - boolean that sets whether the LearningObject is visible
	 */
	public LearningObj(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * This method returns whether a LearningObject is visible
	 * @return visibility - true if visible, false if not
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * This method sets the visibility of a LearningObject
	 * @param visibility - boolean
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	
}
