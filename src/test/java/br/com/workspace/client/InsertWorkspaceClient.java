package br.com.workspace.client;

import br.com.workspace.entity.Person;
import br.com.workspace.entity.Workspace;

/**
 * 
 * Insert Workspace
 * 
 * @author andrerafaelmezzalira
 *
 */
public class InsertWorkspaceClient extends AbstractClient<Workspace> {

	public static void main(String[] args) {
		InsertWorkspaceClient client = new InsertWorkspaceClient();
		Workspace workspace = new Workspace();
		workspace.setPerson(new Person(34));
		client.log(client.post(workspace));
	}

	@Override
	protected String getPathEntity() {
		return "workspace";
	}
}
