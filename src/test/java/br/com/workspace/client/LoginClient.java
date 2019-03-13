package br.com.workspace.client;

import br.com.workspace.entity.Person;

/**
 * 
 * login test person
 * 
 * @author andrerafaelmezzalira
 *
 */
public class LoginClient extends PersonClient {

	public static void main(String[] args) {
		LoginClient client = new LoginClient();
		Person person = new Person();
		person.setName("joao");
		client.log(client.post(person));
	}
}
