package support;

public class NoAnalyzerException extends Exception{
	
	public NoAnalyzerException() {
		super();
	}
	
	public NoAnalyzerException(String message) { 
		super(message); 
	}
	
	public NoAnalyzerException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public NoAnalyzerException(Throwable cause) { 
		super(cause);
	}
}
