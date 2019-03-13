package br.com.workspace.api;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.workspace.entity.Person;
import br.com.workspace.service.PersonService;
import br.com.workspace.util.JsonUtil;

/**
 * 
 * Receives the requisitions for the Person entity
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/person")
public class PersonApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonService service;

	/**
	 * Validate the person's login
	 * 
	 * @param person
	 * @return Response
	 * @throws JsonProcessingException
	 */
	@POST
	@Path("/login")
	public Response login(Person person) throws JsonProcessingException {

		log.info("method login person " + JsonUtil.toJson(person));

		return Response.ok().entity(service.login(person)).build();
	}
}
