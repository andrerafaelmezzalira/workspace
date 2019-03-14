package br.com.workspace.client;

import br.com.workspace.entity.Workspace;

/**
 * 
 * Remove Workspace
 * 
 * @author andrerafaelmezzalira
 *
 */
public class RemoveWorkspaceClient extends AbstractClient<Workspace> {

	public static void main(String[] args) {
		RemoveWorkspaceClient client = new RemoveWorkspaceClient();
		client.log(client.remove(10));
	}

	@Override
	protected String getPathEntity() {
		return "workspace";
	}
}
