package br.com.workspace.client;

import br.com.workspace.entity.Person;
import br.com.workspace.entity.Workspace;

/**
 * 
 * Update Workspace
 * 
 * @author andrerafaelmezzalira
 *
 */
public class UpdateWorkspaceClient extends AbstractClient<Workspace> {

	public static void main(String[] args) {
		UpdateWorkspaceClient client = new UpdateWorkspaceClient();
		Workspace workspace = new Workspace(7);
		workspace.setPerson(new Person(2));
		workspace.setAddress("RUA OTAVIANO TEIXEIRA");
		client.log(client.put(workspace));
	}

	@Override
	protected String getPathEntity() {
		return "workspace";
	}
}
