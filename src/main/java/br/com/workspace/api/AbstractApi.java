package br.com.workspace.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * Defines the path 'api'. That is, every request for the application must have
 * 'api' defined in the path
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationPath("api")
public class AbstractApi extends Application {

}
