package br.com.workspace.client;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.com.workspace.entity.Person;
import br.com.workspace.util.JsonUtil;

/**
 *
 * Client to test person's api
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class PersonClient {

	private Logger log = Logger.getLogger(this.getClass().getName());

	protected String post(Person person) {
		final HttpPost httpPost = new HttpPost("http://localhost:8080/workspace/api/person");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(JsonUtil.toJson(person)));
			return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
		} catch (ParseException | IOException e) {
			log.severe(e.getMessage());
		}
		return null;
	}

	protected void log(String msg) {
		log.info(msg);
	}
}
