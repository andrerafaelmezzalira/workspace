package br.com.workspace.exception;

import javax.ejb.ApplicationException;

/**
 * Exception when foreign key is null
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class ForeignKeyException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Message
	 * 
	 * @param message
	 */
	public ForeignKeyException(String message) {
		super(message);
	}
}
