package support;

/** 
* Exception thrown when npc dies.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-04-14
*/ 
public class RemoveActorException extends RuntimeException{
	
	public RemoveActorException() {
		super();
	}
	
	public RemoveActorException(String message) { 
		super(message); 
	}
	
	public RemoveActorException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public RemoveActorException(Throwable cause) { 
		super(cause);
	}
	  
}
