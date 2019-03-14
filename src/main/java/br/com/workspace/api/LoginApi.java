package br.com.workspace.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.workspace.entity.Person;
import br.com.workspace.exception.IdFoundException;
import br.com.workspace.exception.IdNotFoundException;
import br.com.workspace.service.LoginService;
import br.com.workspace.util.JsonUtil;

/**
 * 
 * Receives the requisitions for the Person entity
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/login")
public class LoginApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private LoginService service;

	/**
	 * Validate the person's login
	 * 
	 * @param person
	 * @return Response
	 */
	@POST
	public Response login(Person person) {

		try {
			log.log(Level.INFO, "method login person {0} ", JsonUtil.toJson(person));
			return Response.ok().entity(service.login(person)).build();
		} catch (IdNotFoundException | JsonProcessingException | IdFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
