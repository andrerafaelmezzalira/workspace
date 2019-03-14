package br.com.workspace.client;

import br.com.workspace.entity.Person;

/**
 * 
 * Login
 * 
 * @author andrerafaelmezzalira
 *
 */
public class LoginClient extends AbstractClient<Person> {

	public static void main(String[] args) {
		LoginClient client = new LoginClient();
		Person person = new Person();
		person.setName("andre1");
		client.log(client.post(person));
	}

	@Override
	protected String getPathEntity() {
		return "login";
	}
}
