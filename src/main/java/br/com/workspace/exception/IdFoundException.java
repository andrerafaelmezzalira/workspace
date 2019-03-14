package br.com.workspace.exception;

import javax.ejb.ApplicationException;

/**
 * Exception when id is other than null
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class IdFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Message
	 * 
	 * @param message
	 */
	public IdFoundException(String message) {
		super(message);
	}
}
