package org.computate.frFR.scolaire.requete;  

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
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
import org.computate.frFR.scolaire.config.ConfigSite;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.KeycloakHelper;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.api.OperationRequest; 

public class RequeteSite extends RequeteSiteGen<Object> implements Serializable {   
	private static final long serialVersionUID = -6737494107881513257L;

	/**	L'ecouteur de contexte du site pour obtenir des objets globals du contexte. **/
	protected void _siteContexte_(Couverture<SiteContexte> c) {
		c.o(new SiteContexte());
	}

	/**	L'objet de configuration du site. **/
	protected void _configSite_(Couverture<ConfigSite> c) {
		ConfigSite o = siteContexte_.getConfigSite();
		c.o(o);
	}

	protected void _requeteSite_(Couverture<RequeteSite> c) { 
		c.o(this);
	}

	protected void _vertx(Couverture<Vertx> c) {
		if(siteContexte_ != null)
			c.o(siteContexte_.getVertx());
	}

	protected void _objetJson(Couverture<JsonObject> c) {
	}

	protected void _rechercheSolr(Couverture<SolrQuery> c) {
	}

	/**
	 * Var.enUS: operationRequest
	 */
	protected void _operationRequete(Couverture<OperationRequest> c) {
	}

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

	protected void _utilisateurVertx(Couverture<JsonObject> c) {
		if(operationRequete != null) {
			JsonObject o = operationRequete.getUser();
			c.o(o);
		}

	}

	protected void _principalJson(Couverture<JsonObject> c) {
		if(utilisateurVertx != null) {
			JsonObject o = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));
			c.o(o);
		}
	}
	
	protected void _utilisateurNomDomaine(Couverture<String> c) {
		String o = principalJson == null ? "example.com" : "example.com";
		c.o(o);
	}
	
	protected void _utilisateurNomEnsemble(Couverture<String> c) {
		String[] partis = StringUtils.split(utilisateurNomDomaine, ".");
		ArrayUtils.reverse(partis);
		String o = StringUtils.join(partis, ".");
		c.o(o);
	}

	/**	Le sujet d'acces Keycloak pour l'utilisateur. 
	 \*	Aussi l'ID d'utilisateur cle primaire dans la base de donnees Keycloak. **/
	protected void _utilisateurId(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("sub");
			c.o(o);
		}
	}

	/**	Le nom d'utilisateur prefere de l'utilisateur. **/
	protected void _utilisateurNom(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("preferred_username");
			c.o(o);
		}
	}

	/**	Le nom de famille de l'utilisateur. **/
	protected void _utilisateurNomFamille(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("family_name");
			c.o(o);
		}
	}

	/**	Le prenom de l'utilisateur. **/
	protected void _utilisateurPrenom(Couverture<String> c) { 
		if(principalJson != null) {
			String o = principalJson.getString("given_name");
//			String o = KeycloakHelper.name(principalJson);
			c.o(o);
		}
	}

	/**	Le nom complet de l'utilisateur. **/
	protected void _utilisateurNomComplet(Couverture<String> c) {
		if(principalJson != null) {
			String o = principalJson.getString("name");
			c.o(o);
		}
	}
//
//	/**	Les rôles du royaume de l'utilisateur. **/ 
//	protected void _utilisateurRolesRoyaume(HashSet<String> o) {  
//		if(jetonAcces != null) {
//			Set<String> s = jetonAcces.getRealmAccess().getRoles();
//			o.addAll(s);
//		}
//	}
//	public boolean utilisateurRolesRoyaumeContient(String role) {
//		boolean o = false;
//		if(utilisateurRolesRoyaume != null) { 
//			o = utilisateurRolesRoyaume.contains(role);
//		}
//		return o;
//	}
//
//	/**	Les rôles de la ressource de l'utilisateur. **/
//	protected void _utilisateurRolesRessource(HashSet<String> o) {
//		if(configSite_ != null) {
//			if(siteIdKeycloak != null && jetonAcces != null) {
//				Access utilisateurAccesRessource = jetonAcces.getResourceAccess(siteIdKeycloak);
//				if(utilisateurAccesRessource != null && jetonAcces != null) {
//					Set<String> s = utilisateurAccesRessource.getRoles();
//					o.addAll(s);
//				}
//			}
//		}
//	}
//	public boolean utilisateurRolesRessourceContient(String role) {
//		boolean o = false;
//		if(utilisateurRolesRessource != null) {
//			o = utilisateurRolesRessource.contains(role);
//		}
//		return o;
//	}
//
//	public boolean utilisateurRolesContient(String role) {
//		boolean o = 
//			utilisateurRolesRoyaume != null && utilisateurRolesRoyaume.contains(role)
//			|| utilisateurRolesRessource != null && utilisateurRolesRessource.contains(role)
//		;
//		return o;
//	}

	/**	L'utilisateur de la requête. **/
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

	protected void _xmlPile(Stack<String> o) {}
//
//	protected void _xmlElementParent(Couverture<String> c) {}
//
//	protected void _xmlElement(Couverture<String> c) {}

	protected void _solrDocument(Couverture<SolrDocument> c) {  
	}

	/**	Si la page vu etait achete par l'utilisateur. **/
	protected void _pageAchete(Couverture<Boolean> c) { 
		c.o(false);
	}

	/**	Si la page vu etait achete par l'utilisateur. **/
	protected void _pageAdmin(Couverture<Boolean> c) { 
		c.o(false);
	}
	
	protected void _h(Couverture<String> c) {}
	
	protected void _chiffrementCrypter(Couverture<Cipher> c) {
		if(!StringUtils.isEmpty(h)) {
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	protected void _chiffrementDecrypter(Couverture<Cipher> c) {
		if(!StringUtils.isEmpty(h)) {
			try {
				c.o(Cipher.getInstance("AES"));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	protected void _digestMessage(Couverture<MessageDigest> c) {    
		if(!StringUtils.isEmpty(h)) {
			try {
				c.o(MessageDigest.getInstance("SHA-1"));
			} catch (NoSuchAlgorithmException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	protected void _crypterSel(Couverture<String> c) {
	}
	
	protected void _requetePk(Couverture<Long> c) {
		if(operationRequete != null)
			c.o(operationRequete.getParams().getLong("pk"));
	}

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _connexionSql(Couverture<SQLConnection> c) {
	}
	
	protected void _crypterCle(Couverture<byte[]> c) {
		if(!StringUtils.isEmpty(h)) {
			try {
				c.o(Arrays.copyOf(digestMessage.digest((crypterSel + h).getBytes("UTF-8")), 16));
			} catch (UnsupportedEncodingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}
	
	protected void _aleatoireSecurise(SecureRandom o) {}  
	
	protected void _specCleSecrete(Couverture<SecretKeySpec> c) {
		if(!StringUtils.isEmpty(h)) {
			try {
				SecretKeySpec specCleSecrete = new SecretKeySpec(crypterCle, "AES");
				chiffrementCrypter.init(Cipher.ENCRYPT_MODE, specCleSecrete);
				chiffrementDecrypter.init(Cipher.DECRYPT_MODE, specCleSecrete);
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
			encryptedByte = chiffrementCrypter.doFinal(octetsNonCrypte);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return encryptedByte;
	}
	
	public String decrypterOctets(byte[] octetsCrypte) {
		String texteNonCrypte = null;
		try {
			byte[] decryptedByte = chiffrementDecrypter.doFinal(octetsCrypte);
			texteNonCrypte = new String(decryptedByte);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			ExceptionUtils.rethrow(e);
		}
		return texteNonCrypte;
	}
	
	public String crypterStr(String o) {
		String texteCrypte = null;     
		if(chiffrementCrypter != null) {
			byte[] octetsNonCrypte = o.getBytes();
			try {
				byte[] encryptedByte = chiffrementCrypter.doFinal(octetsNonCrypte);
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
		if(o != null && chiffrementDecrypter != null) {
			Base64.Decoder decodeur = Base64.getDecoder();
			try {
				byte[] octetsCrypte = decodeur.decode(o);
				byte[] decryptedByte = chiffrementDecrypter.doFinal(octetsCrypte);
				texteNonCrypte = new String(decryptedByte);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				ExceptionUtils.rethrow(e);
			}
		}
		return texteNonCrypte;
	}
//
//	@org.junit.Test public void genererClasseChaine() {
//		SiteContexte SiteContexte = new SiteContexte();
//		SiteContexte.initialiserLoinSiteContexte();
//
//		LangueSite langue = new LangueSite();
//		langue.identifiant("frFR");
//		langue.initialiserLoinLangueSite();
//
//		RequeteSite requeteSite = new RequeteSite();
//		requeteSite.langue(langue);
//		requeteSite.initialiserLoinRequeteSite();
//
//		GenerateurClasse generateurClasse = new GenerateurClasse();
//		generateurClasse.configCheminuration(SiteContexte.configChemin);
//		String cheminRessource = SiteContexte.configSite.appliChemin + "/src/main/java/" + getClass().getCanonicalName().replace(".", "/") + ".java";
//		generateurClasse.requeteSite(requeteSite);
//		generateurClasse.cheminRessource(cheminRessource);
//		generateurClasse.initialiserLoinGenerateurClasse(requeteSite);
//		System.out.println(generateurClasse.texteClasse.toString());
//	}
}
