package br.com.workspace.exception;

import javax.ejb.ApplicationException;

/**
 * Exception when id is null
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class IdNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Message
	 * 
	 * @param message
	 */
	public IdNotFoundException(String message) {
		super(message);
	}
}
