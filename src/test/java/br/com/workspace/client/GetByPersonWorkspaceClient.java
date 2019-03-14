package br.com.workspace.client;

import br.com.workspace.entity.Workspace;

/**
 * 
 * Get by person Workspaces
 * 
 * @author andrerafaelmezzalira
 *
 */
public class GetByPersonWorkspaceClient extends AbstractClient<Workspace> {

	public static void main(String[] args) {
		GetByPersonWorkspaceClient client = new GetByPersonWorkspaceClient();
		client.log(client.get("/person?idPerson=0"));
	}

	@Override
	protected String getPathEntity() {
		return "workspace";
	}
}
