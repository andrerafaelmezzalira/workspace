package br.com.workspace.client;

import br.com.workspace.entity.Workspace;

/**
 * 
 * Get Workspaces
 * 
 * @author andrerafaelmezzalira
 *
 */
public class GetWorkspaceClient extends AbstractClient<Workspace> {

	public static void main(String[] args) {
		GetWorkspaceClient client = new GetWorkspaceClient();
		client.log(client.get());
	}

	@Override
	protected String getPathEntity() {
		return "workspace";
	}
}
