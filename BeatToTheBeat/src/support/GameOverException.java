package support;

/** 
* Exception thrown when player dies.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-04-14
*/ 
public class GameOverException extends RuntimeException{
	
	public GameOverException() {
		super();
	}
	
	public GameOverException(String message) { 
		super(message); 
	}
	
	public GameOverException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public GameOverException(Throwable cause) { 
		super(cause);
	}
	  
}
