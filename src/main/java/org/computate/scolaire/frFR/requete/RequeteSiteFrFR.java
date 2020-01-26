package org.computate.scolaire.frFR.requete; 

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
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;

import io.vertx.core.Vertx;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.KeycloakHelper;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.api.OperationRequest; 

/**
 * MotCle: classeNomSimpleRequeteSite
 * NomCanonique.enUS: org.computate.scolaire.enUS.request.SiteRequestEnUS
 */   
public class RequeteSiteFrFR extends RequeteSiteFrFRGen<Object> implements Serializable {  

	private static final long serialVersionUID = -6737494107881513257L;

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteContext_
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * 
	 * enUS: The site context with global site information. 
	 * frFR: Le contexte du site pour obtenir des objets globals du contexte. 
	 **/    
	protected void _siteContexte_(Couverture<SiteContexteFrFR> c) {
	}

	private static final Pattern PATTERN_SESSION = Pattern.compile("vertx-web.session=(\\w+)");

	/**	
	 * Var.enUS: siteConfig_
	 * 
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 * 
	 * enUS: The site configuration. 
	 * frFR: L'objet de configuration du site. 
	 * **/
	protected void _configSite_(Couverture<ConfigSite> c) {
		ConfigSite o = siteContexte_.getConfigSite();
		c.o(o);
	}

	/**
	 * Var.enUS: siteRequest_
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) { 
		c.o(this);
	}

	/**
	 * Var.enUS: apiRequest_
	 */
	protected void _requeteApi_(Couverture<RequeteApi> c) { 
	}

	/**
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 */
	protected void _vertx(Couverture<Vertx> c) {
		if(siteContexte_ != null)
			c.o(siteContexte_.getVertx());
	}

	/**
	 * Var.enUS: jsonObject
	 */
	protected void _objetJson(Couverture<JsonObject> c) {
	}

	/**
	 * Var.enUS: solrQuery
	 */
	protected void _rechercheSolr(Couverture<SolrQuery> c) {
	}

	/**
	 * Var.enUS: operationRequest
	 */
	protected void _operationRequete(Couverture<OperationRequest> c) {
	}

	/**
	 * Var.enUS: queryResponse
	 * r: rechercheSolr
	 * r.enUS: solrQuery
	 * r: siteContexte_
	 * r.enUS: siteContext_
	 * r: getClientSolr
	 * r.enUS: getSolrClient
	 */
	protected void _reponseRecherche(Couverture<QueryResponse> c) {
		if(rechercheSolr != null) {
			try {
				QueryResponse o = siteContexte_.getClientSolr().query(rechercheSolr);
				c.o(o);
			} catch (SolrServerException | IOException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	/**
	 * Var.enUS: searchResults
	 * r: reponseRecherche
	 * r.enUS: queryResponse
	 */
	protected void _resultatsRecherche(Couverture<SolrDocumentList> c) {
		if(reponseRecherche != null) {
			SolrDocumentList o = reponseRecherche.getResults();
			c.o(o);
		}
	} 

	/**
	 * frFR: L'écrivain pour écrirer le résultat du réponse. 
	 * r.enUS: requeteSite_
	 * siteRequest
	 * r.enUS: ecrivain
	 * writer
	 */  
	protected void _w(Couverture<ToutEcrivain> c) {
	}

	/**
	 * Var.enUS: userVertx
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */
	protected void _utilisateurVertx(Couverture<JsonObject> c) {
		if(operationRequete != null) {
			JsonObject o = operationRequete.getUser();
			c.o(o);
		}

	}

	/**
	 * Var.enUS: jsonPrincipal
	 * r: utilisateurVertx
	 * r.enUS: userVertx
	 */
	protected void _principalJson(Couverture<JsonObject> c) {
		if(utilisateurVertx != null) {
			JsonObject o = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userId
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le sujet d'acces Keycloak pour l'utilisateur. 
	 * Aussi l'ID d'utilisateur cle primaire dans la base de donnees Keycloak. 
	 ***/
	protected void _utilisateurId(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("sub");
			c.o(o);
		}
	}

	/**   
	 * {@inheritDoc}
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */                   
	protected void _sessionId(Couverture<String> c) {
		if(operationRequete != null) {
			String cookie = operationRequete.getHeaders().get("Cookie");
			if(StringUtils.isNotBlank(cookie)) {
				Matcher m = PATTERN_SESSION.matcher(cookie);
				if(m.matches()) {
					c.o(m.group(1));
				}
			}
		}
	}

	/**	
	 * Var.enUS: userName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom d'utilisateur prefere de l'utilisateur. 
	 * **/
	protected void _utilisateurNom(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("preferred_username");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userLastName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom de famille de l'utilisateur. 
	 * **/
	protected void _utilisateurNomFamille(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("family_name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userFirstName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le prenom de l'utilisateur. 
	 * **/
	protected void _utilisateurPrenom(Couverture<String> c) { 
		if(principalJson != null) {
			String o = principalJson.getString("given_name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userFullName
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * Le nom complet de l'utilisateur. 
	 * **/
	protected void _utilisateurNomComplet(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("name");
			c.o(o);
		}
	}

	/**	
	 * Var.enUS: userRealmRoles
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: addUtilisateurRolesRoyaume
	 * r.enUS: addUserRealmRoles
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
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

	/**	
	 * Var.enUS: userResource
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: principalJson
	 * r.enUS: jsonPrincipal
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: AuthRessource
	 * r.enUS: AuthResource
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
	protected void _utilisateurRessource(Couverture<JsonObject> c) {
		if(configSite_ != null && principalJson != null) {
			JsonObject ressource = principalJson.getJsonObject("resource_access").getJsonObject(requeteSite_.getConfigSite_().getAuthRessource());
			c.o(ressource);
		}
	}

	/**	
	 * Var.enUS: userResourceRoles
	 * r: configSite_
	 * r.enUS: siteConfig_
	 * r: utilisateurRessource
	 * r.enUS: userResource
	 * r: addUtilisateurRolesRessource
	 * r.enUS: addUserResourceRoles
	 * frFR: Les rôles de la ressource de l'utilisateur. 
	 * **/
	protected void _utilisateurRolesRessource(List<String> o) {
		if(configSite_ != null && utilisateurRessource != null) {
			JsonArray roles = utilisateurRessource.getJsonArray("roles");
			if(roles != null) {
				roles.stream().forEach(r -> {
					addUtilisateurRolesRessource((String)r);
				});
			}
		}
	}

	/**	
	 * Var.enUS: siteUser
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: utilisateurId
	 * r.enUS: userId
	 * r: utilisateurPrenom
	 * r.enUS: userFirstName
	 * r: utilisateurNomFamille
	 * r.enUS: userLastName
	 * r: utilisateurNom
	 * r.enUS: userName
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * L'utilisateur de la requête. 
	 * **/ 
	protected void _utilisateurSite(Couverture<UtilisateurSite> c) { 
		if(utilisateurId != null) {
			UtilisateurSite o = new UtilisateurSite();
			o.setUtilisateurNom(utilisateurNom);
			o.setUtilisateurPrenom(utilisateurPrenom);
			o.setUtilisateurNomFamille(utilisateurNomFamille);
			o.setUtilisateurId(utilisateurId);
			c.o(o);
		}
	}

	/**
	 * Var.enUS: xmlStack
	 */
	protected void _xmlPile(Stack<String> o) {}

	/**
	 * Var.enUS: solrDocument
	 */
	protected void _documentSolr(Couverture<SolrDocument> c) {  
	}

	/**	
	 * frFR: Si la page vu etait achete par l'utilisateur. 
	 * **/
	protected void _pageAdmin(Couverture<Boolean> c) { 
		c.o(false);
	} 
	
	/**
	 * Var.enUS: requestPk
	 * r: operationRequete
	 * r.enUS: operationRequest
	 */
	protected void _requetePk(Couverture<Long> c) {
		if(operationRequete != null)
			c.o(operationRequete.getParams().getLong("pk"));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sqlConnection
	 **/
	protected void _connexionSql(Couverture<SQLConnection> c) {
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: requestHeaders
	 **/
	protected void _requeteEnTetes(Couverture<CaseInsensitiveHeaders> c) {
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: encryptionPassword
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: CryptageMotDePasse
	 * r.enUS: EncryptionPassword
	 **/
	protected void _cryptageMotDePasse(Couverture<String> c) {
		c.o(configSite_.getCryptageMotDePasse());
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: encryptionCipher
	 * r: cryptageMotDePasse
	 * r.enUS: encryptionPassword
	 **/
	protected void _cryptageChiffreCrypter(Couverture<Cipher> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) { 
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 * Var.enUS: decryptionCipher
	 * r: cryptageMotDePasse
	 * r.enUS: encryptionPassword
	 **/
	protected void _cryptageChiffreDecrypter(Couverture<Cipher> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	/**
	 * Var.enUS: encryptionMessageDigest
	 * r: cryptageMotDePasse
	 * r.enUS: encryptionPassword
	 */
	protected void _cryptageDigestMessage(Couverture<MessageDigest> c) {    
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(MessageDigest.getInstance("SHA-1"));
			} catch (NoSuchAlgorithmException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	/**
	 * Var.enUS: encryptionKey
	 * r: cryptageMotDePasse
	 * r.enUS: encryptionPassword
	 * r: cryptageDigestMessage
	 * r.enUS: encryptionMessageDigest
	 */
	protected void _cryptageCle(Couverture<byte[]> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				c.o(Arrays.copyOf(cryptageDigestMessage.digest((cryptageMotDePasse).getBytes("UTF-8")), 16));
			} catch (UnsupportedEncodingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	/**
	 * Var.enUS: secureRandom
	 */
	protected void _aleatoireSecurise(SecureRandom o) {}  
	
	/**
	 * Var.enUS: secretKeySpec
	 * r: cryptageCle
	 * r.enUS: encryptionKey
	 * r: cryptageChiffreCrypter
	 * r.enUS: encryptionCipher
	 * r: cryptageChiffreDecrypter
	 * r.enUS: decryptionCipher
	 * r: cryptageMotDePasse
	 * r.enUS: encryptionPassword
	 */
	protected void _specCleSecrete(Couverture<SecretKeySpec> c) {
		if(!StringUtils.isEmpty(cryptageMotDePasse)) {
			try {
				SecretKeySpec o = new SecretKeySpec(cryptageCle, "AES");
				cryptageChiffreCrypter.init(Cipher.ENCRYPT_MODE, o);
				cryptageChiffreDecrypter.init(Cipher.DECRYPT_MODE, o);
				c.o(o);
			} catch (InvalidKeyException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	/**
	 * Var.enUS: encryptBytes
	 * r: octetsNonCryptes
	 * r.enUS: bytesUnencrypted
	 * r: octetsCryptes
	 * r.enUS: bytesEncrypted
	 * r: cryptageChiffreCrypter
	 * r.enUS: encryptionCipher
	 */
	public byte[] crypterOctets(String o) {
		byte[] octetsNonCryptes = o.getBytes();
		byte[] octetsCryptes = null;
		try {
			octetsCryptes = cryptageChiffreCrypter.doFinal(octetsNonCryptes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return octetsCryptes;
	}
	
	/**
	 * Var.enUS: decryptBytes
	 * Param1.var.enUS: bytesEncrypted
	 * r: texteNonCrypte
	 * r.enUS: textUnencrypted
	 * r: octetsCryptes
	 * r.enUS: bytesEncrypted
	 * r: octetsNonCryptes
	 * r.enUS: bytesUnencrypted
	 * r: cryptageChiffreDecrypter
	 * r.enUS: decryptionCipher
	 */
	public String decrypterOctets(byte[] octetsCryptes) {
		String texteNonCrypte = null;
		try {
			byte[] octetsNonCryptes = cryptageChiffreDecrypter.doFinal(octetsCryptes);
			texteNonCrypte = new String(octetsNonCryptes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return texteNonCrypte;
	}
	
	/**
	 * Var.enUS: encryptStr
	 * r: texteCrypte
	 * r.enUS: textEncrypted
	 * r: octetsNonCryptes
	 * r.enUS: bytesUnencrypted
	 * r: octetsCryptes
	 * r.enUS: bytesEncrypted
	 * r: cryptageChiffreCrypter
	 * r.enUS: encryptionCipher
	 * r: codeur
	 * r.enUS: encoder
	 */
	public String crypterStr(String o) {
		String texteCrypte = null;     
		if(cryptageChiffreCrypter != null) {
			byte[] octetsNonCryptes = o.getBytes();
			try {
				byte[] bytesEncrypted = cryptageChiffreCrypter.doFinal(octetsNonCryptes);
				Base64.Encoder codeur = Base64.getEncoder();
				texteCrypte = codeur.encodeToString(bytesEncrypted);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return texteCrypte;
	}
	
	/**
	 * Var.enUS: decryptStr
	 * r: texteNonCrypte
	 * r.enUS: textUnencrypted
	 * r: octetsCryptes
	 * r.enUS: bytesEncrypted
	 * r: octetsNonCryptes
	 * r.enUS: bytesUnencrypted
	 * r: octetsCryptes
	 * r.enUS: bytesEncrypted
	 * r: cryptageChiffreDecrypter
	 * r.enUS: decryptionCipher
	 * r: decodeur
	 * r.enUS: decoder
	 */ 
	public String decrypterStr(String o) {
		String texteNonCrypte = null;
		if(o != null && cryptageChiffreDecrypter != null) {
			Base64.Decoder decodeur = Base64.getDecoder();
			try {
				byte[] octetsCryptes = decodeur.decode(o);
				byte[] octetsNonCryptes = cryptageChiffreDecrypter.doFinal(octetsCryptes);
				texteNonCrypte = new String(octetsNonCryptes);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return texteNonCrypte;
	}
}
