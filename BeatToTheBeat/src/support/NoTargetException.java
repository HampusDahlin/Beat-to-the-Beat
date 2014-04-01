package support;

public class NoTargetException extends Exception{
	
	public NoTargetException() {
		super();
	}
	
	public NoTargetException(String message) { 
		super(message); 
	}
	
	public NoTargetException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public NoTargetException(Throwable cause) { 
		super(cause);
	}
	  
}
