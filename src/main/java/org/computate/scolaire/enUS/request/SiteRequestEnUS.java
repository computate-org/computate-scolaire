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
import org.apache.commons.lang3.ArrayUtils;
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

	protected void _utilisateurRolesRoyaume(List<String> o) {
		if(configSite_ != null && principalJson != null) {
			JsonArray roles = principalJson.getJsonObject("realm_access").getJsonArray("roles");
			if(roles != null) {
				roles.stream().forEach(r -> {
					addUtilisateurRolesRoyaume((String)r);
				});
			}
		}
	}

	protected void _utilisateurRessource(Wrap<JsonObject> c) {
		if(configSite_ != null && principalJson != null) {
			JsonObject ressource = principalJson.getJsonObject("resource_access").getJsonObject(requeteSite_.getConfigSite_().getAuthRessource());
			c.o(ressource);
		}
	}

	protected void _utilisateurSite(Wrap<UtilisateurSite> c) { 
		if(utilisateurId != null) {
			UtilisateurSite o = new UtilisateurSite();
			o.setUtilisateurNom(utilisateurNom);
			o.setUtilisateurPrenom(utilisateurPrenom);
			o.setUtilisateurNomFamille(utilisateurNomFamille);
			o.setUtilisateurId(utilisateurId);
			c.o(o);
		}
	}

	protected void _xmlStack(Stack<String> o) {}

	protected void _documentSolr(Wrap<SolrDocument> c) {  
	}

	protected void _pageAchete(Wrap<Boolean> c) { 
		c.o(false);
	}

	protected void _pageAdmin(Wrap<Boolean> c) { 
		c.o(false);
	}

	protected void _requetePk(Wrap<Long> c) {
		if(operationRequete != null)
			c.o(operationRequete.getParams().getLong("pk"));
	}

	protected void _connexionSql(Wrap<SQLConnection> c) {
	}

	protected void _cryptageMotDePasse(Wrap<String> c) {
		c.o(configSite_.getCryptageMotDePasse());
	}

	protected void _cryptageChiffreCrypter(Wrap<Cipher> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) { 
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _cryptageChiffreDecrypter(Wrap<Cipher> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _cryptageDigestMessage(Wrap<MessageDigest> c) {    
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(MessageDigest.getInstance("SHA-1"));
			} catch (NoSuchAlgorithmException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _cryptageCle(Wrap<byte[]> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(Arrays.copyOf(cryptageDigestMessage.digest((cryptageMotDePasse).getBytes("UTF-8")), 16));
			} catch (UnsupportedEncodingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	protected void _aleatoireSecurise(SecureRandom o) {}

	protected void _specCleSecrete(Wrap<SecretKeySpec> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				SecretKeySpec specCleSecrete = new SecretKeySpec(cryptageCle, "AES");
				cryptageChiffreCrypter.init(Cipher.ENCRYPT_MODE, specCleSecrete);
				cryptageChiffreDecrypter.init(Cipher.DECRYPT_MODE, specCleSecrete);
				c.o(specCleSecrete);
			} catch (InvalidKeyException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	public byte[] crypterOctets(String o) {
		byte[] octetsNonCrypte = o.getBytes();
		byte[] encryptedByte = null;
		try {
			encryptedByte = cryptageChiffreCrypter.doFinal(octetsNonCrypte);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return encryptedByte;
	}

	public String decrypterOctets(byte[] octetsCrypte) {
		String texteNonCrypte = null;
		try {
			byte[] decryptedByte = cryptageChiffreDecrypter.doFinal(octetsCrypte);
			texteNonCrypte = new String(decryptedByte);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return texteNonCrypte;
	}

	public String crypterStr(String o) {
		String texteCrypte = null;     
		if(cryptageChiffreDecrypter != null) {
			byte[] octetsNonCrypte = o.getBytes();
			try {
				byte[] encryptedByte = cryptageChiffreDecrypter.doFinal(octetsNonCrypte);
				Base64.Encoder codeur = Base64.getEncoder();
				texteCrypte = codeur.encodeToString(encryptedByte);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return texteCrypte;
	}

	public String decrypterStr(String o) {
		String texteNonCrypte = null;
		if(o != null && cryptageChiffreDecrypter != null) {
			Base64.Decoder decodeur = Base64.getDecoder();
			try {
				byte[] octetsCrypte = decodeur.decode(o);
				byte[] decryptedByte = cryptageChiffreDecrypter.doFinal(octetsCrypte);
				texteNonCrypte = new String(decryptedByte);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return texteNonCrypte;
	}
}
