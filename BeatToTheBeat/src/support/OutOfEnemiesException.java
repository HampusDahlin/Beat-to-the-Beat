package support;

/** 
* Exception thrown when there are no enemies on the board.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-04-14
*/ 
public class OutOfEnemiesException extends RuntimeException{
	
	public OutOfEnemiesException() {
		super();
	}
	
	public OutOfEnemiesException(String message) { 
		super(message); 
	}
	
	public OutOfEnemiesException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public OutOfEnemiesException(Throwable cause) { 
		super(cause);
	}
	  
}
