package br.com.todo.todo.exception;

public class UnprocessableEntity extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnprocessableEntity(String msg) {
		super(msg);
	}

	public UnprocessableEntity(String msg, Throwable cause) {
		super(msg, cause);
	}

}
