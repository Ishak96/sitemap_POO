package SystemSolaire;

/**
 * SiteAlreadyExistsException represent an exception which is thrown when to instance of SiteMap are equals 
 * @author Ayad Ishak and Arhab Samira
 * @since JDK8.0
 * @see Exception
 * @version 1.8.0_144 created at 20 Dec,2017
 */

public class SiteAlreadyExistsException extends Exception {
	/**
	 * @deprecated
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contracts and initializes this exception with a message 
	 * @param message message of the exception
	 * @see Exception
	 */
	public SiteAlreadyExistsException(String message) {
		
		super(message);
		
	}
	
}
