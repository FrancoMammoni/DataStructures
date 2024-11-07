package Excepciones;

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String s) {
		super(s);
	}
}
