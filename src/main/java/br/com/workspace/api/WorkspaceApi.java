package br.com.workspace.api;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.workspace.entity.Workspace;
import br.com.workspace.service.WorkspaceService;
import br.com.workspace.util.JsonUtil;

/**
 * 
 * Receives the requisitions for the Workspace entity
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/workspace")
public class WorkspaceApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private WorkspaceService service;

	/**
	 * Get all workspace's
	 * 
	 * @return Response
	 */
	@GET
	public Response get() {

		log.info("method get  ");

		return Response.ok().entity(service.get()).build();
	}

	/**
	 * Get workspace's by person
	 * 
	 * @param idPerson
	 * @return Response
	 */
	@GET
	@Path("/person")
	public Response getByPerson(@QueryParam("idPerson") Integer idPerson) {

		log.info("method getByPerson  " + idPerson);

		return Response.ok().entity(service.getByPerson(idPerson)).build();
	}

	/**
	 * insert workspace
	 * 
	 * @param workspace
	 * @return Response
	 * @throws JsonProcessingException
	 */
	@POST
	public Response persist(Workspace workspace) throws JsonProcessingException {

		log.info("method persist workspace " + JsonUtil.toJson(workspace));

		return Response.ok().entity(service.persist(workspace)).build();
	}

	/**
	 * Update workspace
	 * 
	 * @param workspace
	 * @return Response
	 * @throws JsonProcessingException
	 */
	@PUT
	public Response merge(Workspace workspace) throws JsonProcessingException {

		log.info("method merge workspace " + JsonUtil.toJson(workspace));

		return Response.ok().entity(service.merge(workspace)).build();
	}

	/**
	 * Remove workspace by id
	 * 
	 * @param id
	 * @return Response
	 */
	@DELETE
	public Response remove(@QueryParam("id") Integer id) {

		log.info("method remove workspace " + id);

		return Response.ok().entity(service.remove(id)).build();
	}

}
