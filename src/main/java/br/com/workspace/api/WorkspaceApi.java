package br.com.workspace.api;

import java.util.logging.Level;
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
import br.com.workspace.exception.ForeignKeyException;
import br.com.workspace.exception.IdFoundException;
import br.com.workspace.exception.IdNotFoundException;
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

		log.log(Level.INFO, "method get");

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

		log.log(Level.INFO, "method getByPerson {0} ", idPerson);

		return Response.ok().entity(service.getByPerson(idPerson)).build();
	}

	/**
	 * insert workspace
	 * 
	 * @param workspace
	 * @return Response
	 */
	@POST
	public Response persist(Workspace workspace) {

		try {
			log.log(Level.INFO, "method persist workspace {0} ", JsonUtil.toJson(workspace));
			return Response.ok().entity(service.persist(workspace)).build();
		} catch (ForeignKeyException | JsonProcessingException | IdFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/**
	 * Update workspace
	 * 
	 * @param workspace
	 * @return Response
	 */
	@PUT
	public Response merge(Workspace workspace) {

		try {
			log.log(Level.INFO, "method merge workspace {0} ", JsonUtil.toJson(workspace));
			return Response.ok().entity(service.merge(workspace)).build();
		} catch (IdNotFoundException | ForeignKeyException | JsonProcessingException e) {
			log.log(Level.SEVERE, e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/**
	 * Remove workspace by id
	 * 
	 * @param id
	 * @return Response
	 */
	@DELETE
	public Response remove(@QueryParam("id") Integer id) {

		log.log(Level.INFO, "method remove workspace {0} ", id);

		try {
			return Response.ok().entity(service.remove(id)).build();
		} catch (IdNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
