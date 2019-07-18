package org.computate.scolaire.enUS.request;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Stack;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.enUS.school.writer.AllWriter;
import org.computate.scolaire.enUS.user.SiteUser;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.KeycloakHelper;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.api.OperationRequest;

public class SiteRequestEnUS extends SiteRequestEnUSGen<Object> implements Serializable {

	private static final long serialVersionUID = -6737494107881513257L;

	/**	
	 *	The site context with global site information. 
	 **/
	protected void _siteContext_(Wrap<SiteContextEnUS> c) {
	}

	/**	
	 *	The site configuration. 
	 **/
	protected void _siteConfig_(Wrap<SiteConfig> c) {
		SiteConfig o = siteContext_.getSiteConfig();
		c.o(o);
	}

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) { 
		c.o(this);
	}

	protected void _vertx(Wrap<Vertx> c) {
		if(siteContext_ != null)
			c.o(siteContext_.getVertx());
	}

	protected void _jsonObject(Wrap<JsonObject> c) {
	}

	protected void _solrQuery(Wrap<SolrQuery> c) {
	}

	protected void _operationRequest(Wrap<OperationRequest> c) {
	}

	protected void _queryResponse(Wrap<QueryResponse> c) {
		if(solrQuery != null) {
			try {
				QueryResponse o = siteContext_.getSolrClient().query(solrQuery);
				c.o(o);
			} catch (SolrServerException | IOException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _searchResults(Wrap<SolrDocumentList> c) {
		if(queryResponse != null) {
			SolrDocumentList o = queryResponse.getResults();
			c.o(o);
		}
	}

	protected void _w(Wrap<AllWriter> c) {
	}

	protected void _userVertx(Wrap<JsonObject> c) {
		if(operationRequest != null) {
			JsonObject o = operationRequest.getUser();
			c.o(o);
		}

	}

	protected void _jsonPrincipal(Wrap<JsonObject> c) {
		if(userVertx != null) {
			JsonObject o = KeycloakHelper.parseToken(userVertx.getString("access_token"));
			c.o(o);
		}
	}

	protected void _userId(Wrap<String> c) {
		if(jsonPrincipal != null) {
			String o = jsonPrincipal.getString("sub");
			c.o(o);
		}
	}

	protected void _userName(Wrap<String> c) {
		if(jsonPrincipal != null) {
			String o = jsonPrincipal.getString("preferred_username");
			c.o(o);
		}
	}

	protected void _userLastName(Wrap<String> c) {
		if(jsonPrincipal != null) {
			String o = jsonPrincipal.getString("family_name");
			c.o(o);
		}
	}

	protected void _userFirstName(Wrap<String> c) { 
		if(jsonPrincipal != null) {
			String o = jsonPrincipal.getString("given_name");
			c.o(o);
		}
	}

	protected void _userFullName(Wrap<String> c) {
		if(jsonPrincipal != null) {
			String o = jsonPrincipal.getString("name");
			c.o(o);
		}
	}

	protected void _userRealmRoles(List<String> o) {
		if(siteConfig_ != null && jsonPrincipal != null) {
			JsonArray roles = jsonPrincipal.getJsonObject("realm_access").getJsonArray("roles");
			if(roles != null) {
				roles.stream().forEach(r -> {
					addUserRealmRoles((String)r);
				});
			}
		}
	}

	protected void _userResource(Wrap<JsonObject> c) {
		if(siteConfig_ != null && jsonPrincipal != null) {
			JsonObject ressource = jsonPrincipal.getJsonObject("resource_access").getJsonObject(siteRequest_.getSiteConfig_().getAuthResource());
			c.o(ressource);
		}
	}

	protected void _siteUser(Wrap<SiteUser> c) { 
		if(userId != null) {
			SiteUser o = new SiteUser();
			o.setUserName(userName);
			o.setUserFirstName(userFirstName);
			o.setUserLastName(userLastName);
			o.setUserId(userId);
			c.o(o);
		}
	}

	protected void _xmlStack(Stack<String> o) {}

	protected void _solrDocument(Wrap<SolrDocument> c) {  
	}

	protected void _pageAdmin(Wrap<Boolean> c) { 
		c.o(false);
	}

	protected void _requestPk(Wrap<Long> c) {
		if(operationRequest != null)
			c.o(operationRequest.getParams().getLong("pk"));
	}

	protected void _sqlConnection(Wrap<SQLConnection> c) {
	}

	protected void _encryptionPassword(Wrap<String> c) {
		c.o(siteConfig_.getEncryptionPassword());
	}

	protected void _encryptionCipher(Wrap<Cipher> c) {
		if(!StringUtils.isEmpty(encryptionPassword)) { 
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _decryptionCipher(Wrap<Cipher> c) {
		if(!StringUtils.isEmpty(encryptionPassword)) {
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _encryptionMessageDigest(Wrap<MessageDigest> c) {    
		if(!StringUtils.isEmpty(encryptionPassword)) {
			try {
				c.o(MessageDigest.getInstance("SHA-1"));
			} catch (NoSuchAlgorithmException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _encryptionKey(Wrap<byte[]> c) {
		if(!StringUtils.isEmpty(encryptionPassword)) {
			try {
				c.o(Arrays.copyOf(encryptionMessageDigest.digest((encryptionPassword).getBytes("UTF-8")), 16));
			} catch (UnsupportedEncodingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _secureRandom(SecureRandom o) {}

	protected void _secretKeySpec(Wrap<SecretKeySpec> c) {
		if(!StringUtils.isEmpty(encryptionPassword)) {
			try {
				SecretKeySpec o = new SecretKeySpec(encryptionKey, "AES");
				encryptionCipher.init(Cipher.ENCRYPT_MODE, o);
				decryptionCipher.init(Cipher.DECRYPT_MODE, o);
				c.o(o);
			} catch (InvalidKeyException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	public byte[] encryptBytes(String o) {
		byte[] bytesUnencrypted = o.getBytes();
		byte[] bytesEncrypted = null;
		try {
			bytesEncrypted = encryptionCipher.doFinal(bytesUnencrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return bytesEncrypted;
	}

	public String decryptBytes(byte[] bytesEncrypted) {
		String textUnencrypted = null;
		try {
			byte[] bytesUnencrypted = decryptionCipher.doFinal(bytesEncrypted);
			textUnencrypted = new String(bytesUnencrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return textUnencrypted;
	}

	public String encryptStr(String o) {
		String textEncrypted = null;     
		if(encryptionCipher != null) {
			byte[] bytesUnencrypted = o.getBytes();
			try {
				byte[] bytesEncrypted = encryptionCipher.doFinal(bytesUnencrypted);
				Base64.Encoder encoder = Base64.getEncoder();
				textEncrypted = encoder.encodeToString(bytesEncrypted);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return textEncrypted;
	}

	public String decryptStr(String o) {
		String textUnencrypted = null;
		if(o != null && decryptionCipher != null) {
			Base64.Decoder decoder = Base64.getDecoder();
			try {
				byte[] bytesEncrypted = decoder.decode(o);
				byte[] bytesUnencrypted = decryptionCipher.doFinal(bytesEncrypted);
				textUnencrypted = new String(bytesUnencrypted);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return textUnencrypted;
	}
}
