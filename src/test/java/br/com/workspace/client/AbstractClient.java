package br.com.workspace.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.com.workspace.entity.AbstractEntity;
import br.com.workspace.util.JsonUtil;

/**
 *
 * Client abstract
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class AbstractClient<T> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	protected static final String SERVER = "http://localhost:8080/workspace/api/";

	protected String post(AbstractEntity<?> t) {
		final HttpPost httpPost = new HttpPost(SERVER + getPathEntity());
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(JsonUtil.toJson(t)));
			return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	protected String put(AbstractEntity<?> t) {
		final HttpPut httpPost = new HttpPut(SERVER + getPathEntity());
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(JsonUtil.toJson(t)));
			return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	protected String get(String... params) {
		try {
			final HttpGet httpGet = new HttpGet(SERVER + getPathEntity() + (params.length == 1 ? params[0] : ""));
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			return EntityUtils.toString(new DefaultHttpClient().execute(httpGet).getEntity());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	protected String remove(Integer id) {
		final HttpDelete httpDelete = new HttpDelete(SERVER + getPathEntity() + "?id=" + id.toString());
		httpDelete.setHeader("Accept", "application/json");
		httpDelete.setHeader("Content-type", "application/json");
		try {
			return EntityUtils.toString(new DefaultHttpClient().execute(httpDelete).getEntity());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	protected void log(String msg) {
		log.log(Level.INFO, msg);
	}

	protected abstract String getPathEntity();

}
