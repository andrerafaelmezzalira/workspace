package br.com.workspace.entity;

import java.io.Serializable;

/**
 * Represents an abstraction of database tables
 * 
 * @author andrerafaelmezzalira
 * 
 */
public interface AbstractEntity<T> extends Serializable {

	/**
	 * Gets a primary key from a table
	 * 
	 * @return T - Object type that represents the primary key
	 * 
	 */
	T getId();

	/**
	 * Sets the primary key of the table
	 * 
	 * @param id - Object type that represents the primary key
	 * 
	 */
	void setId(T id);
}
