package org.computate.scolaire.frFR.paiement;

import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import java.math.BigDecimal;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import java.time.ZoneId;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe paymentCompleteName dans Solr</a>
 * <br/>
 **/
public abstract class PaiementScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaiementScolaire.class);

	public static final String PaiementScolaire_UnNom = "un paiement";
	public static final String PaiementScolaire_Ce = "ce ";
	public static final String PaiementScolaire_CeNom = "ce paiement";
	public static final String PaiementScolaire_Un = "un ";
	public static final String PaiementScolaire_LeNom = "le paiement";
	public static final String PaiementScolaire_NomSingulier = "paiement";
	public static final String PaiementScolaire_NomPluriel = "paiements";
	public static final String PaiementScolaire_NomActuel = "paiement actuel";
	public static final String PaiementScolaire_Tous = "all ";
	public static final String PaiementScolaire_TousNom = "tous les paiements";
	public static final String PaiementScolaire_RechercherTousNomPar = "rechercher paiements par ";
	public static final String PaiementScolaire_RechercherTousNom = "rechercher paiements";
	public static final String PaiementScolaire_LesNoms = "les paiements";
	public static final String PaiementScolaire_AucunNomTrouve = "aucun paiement trouvé";
	public static final String PaiementScolaire_NomVar = "paiement";
	public static final String PaiementScolaire_DeNom = "de paiement";
	public static final String PaiementScolaire_NomAdjectifSingulier = "paiement";
	public static final String PaiementScolaire_NomAdjectifPluriel = "paiements";
	public static final String PaiementScolaire_Couleur = "green";
	public static final String PaiementScolaire_IconeGroupe = "solid";
	public static final String PaiementScolaire_IconeNom = "search-dollar";

	///////////////
	// ecoleCles //
	///////////////

	/**	L'entité « ecoleCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ecoleCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> ecoleClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ecoleCles").o(ecoleCles);

	/**	<br/>L'entité « ecoleCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCles">Trouver l'entité ecoleCles dans Solr</a>
	 * <br/>
	 * @param ecoleCles est l'entité déjà construit. 
	 **/
	protected abstract void _ecoleCles(List<Long> l);

	public List<Long> getEcoleCles() {
		return ecoleCles;
	}

	public void setEcoleCles(List<Long> ecoleCles) {
		this.ecoleCles = ecoleCles;
		this.ecoleClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addEcoleCles(Long...objets) {
		for(Long o : objets) {
			addEcoleCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addEcoleCles(Long o) {
		if(o != null && !ecoleCles.contains(o))
			this.ecoleCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setEcoleCles(JsonArray objets) {
		ecoleCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEcoleCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addEcoleCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEcoleCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ecoleClesInit() {
		if(!ecoleClesCouverture.dejaInitialise) {
			_ecoleCles(ecoleCles);
		}
		ecoleClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrEcoleCles() {
		return ecoleCles;
	}

	public String strEcoleCles() {
		return ecoleCles == null ? "" : ecoleCles.toString();
	}

	public String jsonEcoleCles() {
		return ecoleCles == null ? "" : ecoleCles.toString();
	}

	public String nomAffichageEcoleCles() {
		return "écoles";
	}

	public String htmTooltipEcoleCles() {
		return null;
	}

	public String htmEcoleCles() {
		return ecoleCles == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCles());
	}

	///////////////
	// anneeCles //
	///////////////

	/**	L'entité « anneeCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> anneeCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> anneeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/>L'entité « anneeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> l);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrAnneeCles() {
		return anneeCles;
	}

	public String strAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String jsonAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String nomAffichageAnneeCles() {
		return null;
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	///////////////
	// saisonCle //
	///////////////

	/**	L'entité « saisonCle »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> saisonCle = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> saisonCleCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCle").o(saisonCle);

	/**	<br/>L'entité « saisonCle »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
	 * <br/>
	 * @param saisonCle est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCle(List<Long> l);

	public List<Long> getSaisonCle() {
		return saisonCle;
	}

	public void setSaisonCle(List<Long> saisonCle) {
		this.saisonCle = saisonCle;
		this.saisonCleCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addSaisonCle(Long...objets) {
		for(Long o : objets) {
			addSaisonCle(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addSaisonCle(Long o) {
		if(o != null && !saisonCle.contains(o))
			this.saisonCle.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setSaisonCle(JsonArray objets) {
		saisonCle.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCle(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addSaisonCle(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCle(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCle);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrSaisonCle() {
		return saisonCle;
	}

	public String strSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String jsonSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String nomAffichageSaisonCle() {
		return "saisons";
	}

	public String htmTooltipSaisonCle() {
		return null;
	}

	public String htmSaisonCle() {
		return saisonCle == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCle());
	}

	/////////////////
	// sessionCles //
	/////////////////

	/**	L'entité « sessionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> sessionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/>L'entité « sessionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> l);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrSessionCles() {
		return sessionCles;
	}

	public String strSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String jsonSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String nomAffichageSessionCles() {
		return "sessions";
	}

	public String htmTooltipSessionCles() {
		return null;
	}

	public String htmSessionCles() {
		return sessionCles == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCles());
	}

	/////////////
	// ageCles //
	/////////////

	/**	L'entité « ageCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ageCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> ageClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ageCles").o(ageCles);

	/**	<br/>L'entité « ageCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
	 * <br/>
	 * @param ageCles est l'entité déjà construit. 
	 **/
	protected abstract void _ageCles(List<Long> l);

	public List<Long> getAgeCles() {
		return ageCles;
	}

	public void setAgeCles(List<Long> ageCles) {
		this.ageCles = ageCles;
		this.ageClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addAgeCles(Long...objets) {
		for(Long o : objets) {
			addAgeCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addAgeCles(Long o) {
		if(o != null && !ageCles.contains(o))
			this.ageCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ageClesInit() {
		if(!ageClesCouverture.dejaInitialise) {
			_ageCles(ageCles);
		}
		ageClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrAgeCles() {
		return ageCles;
	}

	public String strAgeCles() {
		return ageCles == null ? "" : ageCles.toString();
	}

	public String jsonAgeCles() {
		return ageCles == null ? "" : ageCles.toString();
	}

	public String nomAffichageAgeCles() {
		return "âges";
	}

	public String htmTooltipAgeCles() {
		return null;
	}

	public String htmAgeCles() {
		return ageCles == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCles());
	}

	//////////////
	// blocCles //
	//////////////

	/**	L'entité « blocCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> blocCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> blocClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/>L'entité « blocCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
	 * <br/>
	 * @param blocCles est l'entité déjà construit. 
	 **/
	protected abstract void _blocCles(List<Long> l);

	public List<Long> getBlocCles() {
		return blocCles;
	}

	public void setBlocCles(List<Long> blocCles) {
		this.blocCles = blocCles;
		this.blocClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addBlocCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocClesInit() {
		if(!blocClesCouverture.dejaInitialise) {
			_blocCles(blocCles);
		}
		blocClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrBlocCles() {
		return blocCles;
	}

	public String strBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String jsonBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String nomAffichageBlocCles() {
		return "sessions";
	}

	public String htmTooltipBlocCles() {
		return null;
	}

	public String htmBlocCles() {
		return blocCles == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCles());
	}

	/////////////////////
	// inscriptionCles //
	/////////////////////

	/**	L'entité « inscriptionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> inscriptionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> inscriptionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("inscriptionCles").o(inscriptionCles);

	/**	<br/>L'entité « inscriptionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
	 * <br/>
	 * @param inscriptionCles est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionCles(List<Long> l);

	public List<Long> getInscriptionCles() {
		return inscriptionCles;
	}

	public void setInscriptionCles(List<Long> inscriptionCles) {
		this.inscriptionCles = inscriptionCles;
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrInscriptionCles() {
		return inscriptionCles;
	}

	public String strInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String jsonInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String nomAffichageInscriptionCles() {
		return "inscriptions";
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
	}

	public void inputInscriptionCles(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "inscriptions")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("class", "valeur suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setInscriptionCles")
				.a("id", classeApiMethodeMethode, "_inscriptionCles")
				.a("autocomplete", "off")
				.a("oninput", "suggerePaiementScolaireInscriptionCles($(this).val() ? rechercherInscriptionScolaireFiltres($('#suggerePaiementScolaireInscriptionCles')) : [{'name':'fq','value':'paiementCles:", pk, "'}], $('#listPaiementScolaireInscriptionCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmInscriptionCles(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolaireInscriptionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
								sx("inscriptions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a ce paiement");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputInscriptionCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postInscriptionScolaireVals({ paiementCles: [ \"", pk, "\" ] }, function() { patchPaiementScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCles')); });")
										.f().sx("ajouter une inscription")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paiementCle //
	/////////////////

	/**	L'entité « paiementCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long paiementCle;
	@JsonIgnore
	public Couverture<Long> paiementCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("paiementCle").o(paiementCle);

	/**	<br/>L'entité « paiementCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCle">Trouver l'entité paiementCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementCle(Couverture<Long> c);

	public Long getPaiementCle() {
		return paiementCle;
	}

	public void setPaiementCle(Long paiementCle) {
		this.paiementCle = paiementCle;
		this.paiementCleCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementCle(String o) {
		if(NumberUtils.isParsable(o))
			this.paiementCle = Long.parseLong(o);
		this.paiementCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementCleInit() {
		if(!paiementCleCouverture.dejaInitialise) {
			_paiementCle(paiementCleCouverture);
			if(paiementCle == null)
				setPaiementCle(paiementCleCouverture.o);
		}
		paiementCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Long solrPaiementCle() {
		return paiementCle;
	}

	public String strPaiementCle() {
		return paiementCle == null ? "" : paiementCle.toString();
	}

	public String jsonPaiementCle() {
		return paiementCle == null ? "" : paiementCle.toString();
	}

	public String nomAffichagePaiementCle() {
		return "clé";
	}

	public String htmTooltipPaiementCle() {
		return null;
	}

	public String htmPaiementCle() {
		return paiementCle == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementCle());
	}

	////////////////
	// enfantCles //
	////////////////

	/**	L'entité « enfantCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> enfantCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> enfantClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("enfantCles").o(enfantCles);

	/**	<br/>L'entité « enfantCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCles">Trouver l'entité enfantCles dans Solr</a>
	 * <br/>
	 * @param enfantCles est l'entité déjà construit. 
	 **/
	protected abstract void _enfantCles(List<Long> o);

	public List<Long> getEnfantCles() {
		return enfantCles;
	}

	public void setEnfantCles(List<Long> enfantCles) {
		this.enfantCles = enfantCles;
		this.enfantClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addEnfantCles(Long...objets) {
		for(Long o : objets) {
			addEnfantCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addEnfantCles(Long o) {
		if(o != null && !enfantCles.contains(o))
			this.enfantCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setEnfantCles(JsonArray objets) {
		enfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnfantCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addEnfantCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnfantCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire enfantClesInit() {
		if(!enfantClesCouverture.dejaInitialise) {
			_enfantCles(enfantCles);
		}
		enfantClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrEnfantCles() {
		return enfantCles;
	}

	public String strEnfantCles() {
		return enfantCles == null ? "" : enfantCles.toString();
	}

	public String jsonEnfantCles() {
		return enfantCles == null ? "" : enfantCles.toString();
	}

	public String nomAffichageEnfantCles() {
		return "enfants";
	}

	public String htmTooltipEnfantCles() {
		return null;
	}

	public String htmEnfantCles() {
		return enfantCles == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCles());
	}

	//////////////
	// mereCles //
	//////////////

	/**	L'entité « mereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> mereCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> mereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("mereCles").o(mereCles);

	/**	<br/>L'entité « mereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCles">Trouver l'entité mereCles dans Solr</a>
	 * <br/>
	 * @param mereCles est l'entité déjà construit. 
	 **/
	protected abstract void _mereCles(List<Long> o);

	public List<Long> getMereCles() {
		return mereCles;
	}

	public void setMereCles(List<Long> mereCles) {
		this.mereCles = mereCles;
		this.mereClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addMereCles(Long...objets) {
		for(Long o : objets) {
			addMereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addMereCles(Long o) {
		if(o != null && !mereCles.contains(o))
			this.mereCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setMereCles(JsonArray objets) {
		mereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addMereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMereCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire mereClesInit() {
		if(!mereClesCouverture.dejaInitialise) {
			_mereCles(mereCles);
		}
		mereClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrMereCles() {
		return mereCles;
	}

	public String strMereCles() {
		return mereCles == null ? "" : mereCles.toString();
	}

	public String jsonMereCles() {
		return mereCles == null ? "" : mereCles.toString();
	}

	public String nomAffichageMereCles() {
		return "mères";
	}

	public String htmTooltipMereCles() {
		return null;
	}

	public String htmMereCles() {
		return mereCles == null ? "" : StringEscapeUtils.escapeHtml4(strMereCles());
	}

	//////////////
	// pereCles //
	//////////////

	/**	L'entité « pereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> pereCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> pereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("pereCles").o(pereCles);

	/**	<br/>L'entité « pereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereCles">Trouver l'entité pereCles dans Solr</a>
	 * <br/>
	 * @param pereCles est l'entité déjà construit. 
	 **/
	protected abstract void _pereCles(List<Long> o);

	public List<Long> getPereCles() {
		return pereCles;
	}

	public void setPereCles(List<Long> pereCles) {
		this.pereCles = pereCles;
		this.pereClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addPereCles(Long...objets) {
		for(Long o : objets) {
			addPereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPereCles(Long o) {
		if(o != null && !pereCles.contains(o))
			this.pereCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPereCles(JsonArray objets) {
		pereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPereCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire pereClesInit() {
		if(!pereClesCouverture.dejaInitialise) {
			_pereCles(pereCles);
		}
		pereClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrPereCles() {
		return pereCles;
	}

	public String strPereCles() {
		return pereCles == null ? "" : pereCles.toString();
	}

	public String jsonPereCles() {
		return pereCles == null ? "" : pereCles.toString();
	}

	public String nomAffichagePereCles() {
		return "pères";
	}

	public String htmTooltipPereCles() {
		return null;
	}

	public String htmPereCles() {
		return pereCles == null ? "" : StringEscapeUtils.escapeHtml4(strPereCles());
	}

	/////////////////
	// gardienCles //
	/////////////////

	/**	L'entité « gardienCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> gardienCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> gardienClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("gardienCles").o(gardienCles);

	/**	<br/>L'entité « gardienCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCles">Trouver l'entité gardienCles dans Solr</a>
	 * <br/>
	 * @param gardienCles est l'entité déjà construit. 
	 **/
	protected abstract void _gardienCles(List<Long> o);

	public List<Long> getGardienCles() {
		return gardienCles;
	}

	public void setGardienCles(List<Long> gardienCles) {
		this.gardienCles = gardienCles;
		this.gardienClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addGardienCles(Long...objets) {
		for(Long o : objets) {
			addGardienCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addGardienCles(Long o) {
		if(o != null && !gardienCles.contains(o))
			this.gardienCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setGardienCles(JsonArray objets) {
		gardienCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGardienCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addGardienCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGardienCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire gardienClesInit() {
		if(!gardienClesCouverture.dejaInitialise) {
			_gardienCles(gardienCles);
		}
		gardienClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrGardienCles() {
		return gardienCles;
	}

	public String strGardienCles() {
		return gardienCles == null ? "" : gardienCles.toString();
	}

	public String jsonGardienCles() {
		return gardienCles == null ? "" : gardienCles.toString();
	}

	public String nomAffichageGardienCles() {
		return "gardiens";
	}

	public String htmTooltipGardienCles() {
		return null;
	}

	public String htmGardienCles() {
		return gardienCles == null ? "" : StringEscapeUtils.escapeHtml4(strGardienCles());
	}

	/////////////////
	// contactCles //
	/////////////////

	/**	L'entité « contactCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> contactCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> contactClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("contactCles").o(contactCles);

	/**	<br/>L'entité « contactCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:contactCles">Trouver l'entité contactCles dans Solr</a>
	 * <br/>
	 * @param contactCles est l'entité déjà construit. 
	 **/
	protected abstract void _contactCles(List<Long> o);

	public List<Long> getContactCles() {
		return contactCles;
	}

	public void setContactCles(List<Long> contactCles) {
		this.contactCles = contactCles;
		this.contactClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addContactCles(Long...objets) {
		for(Long o : objets) {
			addContactCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addContactCles(Long o) {
		if(o != null && !contactCles.contains(o))
			this.contactCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setContactCles(JsonArray objets) {
		contactCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addContactCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addContactCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addContactCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire contactClesInit() {
		if(!contactClesCouverture.dejaInitialise) {
			_contactCles(contactCles);
		}
		contactClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public List<Long> solrContactCles() {
		return contactCles;
	}

	public String strContactCles() {
		return contactCles == null ? "" : contactCles.toString();
	}

	public String jsonContactCles() {
		return contactCles == null ? "" : contactCles.toString();
	}

	public String nomAffichageContactCles() {
		return "contacts d'urgence";
	}

	public String htmTooltipContactCles() {
		return null;
	}

	public String htmContactCles() {
		return contactCles == null ? "" : StringEscapeUtils.escapeHtml4(strContactCles());
	}

	//////////////////////////
	// inscriptionRecherche //
	//////////////////////////

	/**	L'entité « inscriptionRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<InscriptionScolaire> inscriptionRecherche = new ListeRecherche<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> inscriptionRechercheCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("inscriptionRecherche").o(inscriptionRecherche);

	/**	<br/>L'entité « inscriptionRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
	 * <br/>
	 * @param inscriptionRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l);

	public ListeRecherche<InscriptionScolaire> getInscriptionRecherche() {
		return inscriptionRecherche;
	}

	public void setInscriptionRecherche(ListeRecherche<InscriptionScolaire> inscriptionRecherche) {
		this.inscriptionRecherche = inscriptionRecherche;
		this.inscriptionRechercheCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire inscriptionRechercheInit() {
		if(!inscriptionRechercheCouverture.dejaInitialise) {
			_inscriptionRecherche(inscriptionRecherche);
		}
		inscriptionRecherche.initLoinPourClasse(requeteSite_);
		inscriptionRechercheCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	//////////////////
	// inscription_ //
	//////////////////

	/**	L'entité « inscription_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected InscriptionScolaire inscription_;
	@JsonIgnore
	public Couverture<InscriptionScolaire> inscription_Couverture = new Couverture<InscriptionScolaire>().p(this).c(InscriptionScolaire.class).var("inscription_").o(inscription_);

	/**	<br/>L'entité « inscription_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscription_">Trouver l'entité inscription_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscription_(Couverture<InscriptionScolaire> c);

	public InscriptionScolaire getInscription_() {
		return inscription_;
	}

	public void setInscription_(InscriptionScolaire inscription_) {
		this.inscription_ = inscription_;
		this.inscription_Couverture.dejaInitialise = true;
	}
	protected PaiementScolaire inscription_Init() {
		if(!inscription_Couverture.dejaInitialise) {
			_inscription_(inscription_Couverture);
			if(inscription_ == null)
				setInscription_(inscription_Couverture.o);
		}
		inscription_Couverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	/////////////////////////
	// paiementDescription //
	/////////////////////////

	/**	L'entité « paiementDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected String paiementDescription;
	@JsonIgnore
	public Couverture<String> paiementDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("paiementDescription").o(paiementDescription);

	/**	<br/>L'entité « paiementDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDescription">Trouver l'entité paiementDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDescription(Couverture<String> c);

	public String getPaiementDescription() {
		return paiementDescription;
	}

	public void setPaiementDescription(String paiementDescription) {
		this.paiementDescription = paiementDescription;
		this.paiementDescriptionCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementDescriptionInit() {
		if(!paiementDescriptionCouverture.dejaInitialise) {
			_paiementDescription(paiementDescriptionCouverture);
			if(paiementDescription == null)
				setPaiementDescription(paiementDescriptionCouverture.o);
		}
		paiementDescriptionCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementDescription() {
		return paiementDescription;
	}

	public String strPaiementDescription() {
		return paiementDescription == null ? "" : paiementDescription;
	}

	public String jsonPaiementDescription() {
		return paiementDescription == null ? "" : paiementDescription;
	}

	public String nomAffichagePaiementDescription() {
		return "description";
	}

	public String htmTooltipPaiementDescription() {
		return null;
	}

	public String htmPaiementDescription() {
		return paiementDescription == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementDescription());
	}

	public void inputPaiementDescription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "description")
			.a("title", "La clé primaire des enfants dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_paiementDescription");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementDescription w3-input w3-border ");
				a("name", "setPaiementDescription");
			} else {
				a("class", "valeurPaiementDescription w3-input w3-border ");
				a("name", "paiementDescription");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchPaiementScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDescription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ");
			}
			a("value", strPaiementDescription())
		.fg();

	}

	public void htmPaiementDescription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolairePaiementDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementDescription(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDescription')); $('#", classeApiMethodeMethode, "_paiementDescription').val(null); patchPaiementScolaireVal([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementDescription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// paiementDate //
	//////////////////

	/**	L'entité « paiementDate »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate paiementDate;
	@JsonIgnore
	public Couverture<LocalDate> paiementDateCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementDate").o(paiementDate);

	/**	<br/>L'entité « paiementDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDate">Trouver l'entité paiementDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDate(Couverture<LocalDate> c);

	public LocalDate getPaiementDate() {
		return paiementDate;
	}

	public void setPaiementDate(LocalDate paiementDate) {
		this.paiementDate = paiementDate;
		this.paiementDateCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementDate(Instant o) {
		this.paiementDate = LocalDate.from(o);
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setPaiementDate(String o) {
		this.paiementDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementDate(Date o) {
		this.paiementDate = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementDateInit() {
		if(!paiementDateCouverture.dejaInitialise) {
			_paiementDate(paiementDateCouverture);
			if(paiementDate == null)
				setPaiementDate(paiementDateCouverture.o);
		}
		paiementDateCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Date solrPaiementDate() {
		return paiementDate == null ? null : Date.from(paiementDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strPaiementDate() {
		return paiementDate == null ? "" : paiementDate.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonPaiementDate() {
		return paiementDate == null ? "" : paiementDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichagePaiementDate() {
		return "date de paiement";
	}

	public String htmTooltipPaiementDate() {
		return null;
	}

	public String htmPaiementDate() {
		return paiementDate == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementDate());
	}

	public void inputPaiementDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_paiementDate")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des enfants dans la base de données.  (DD-MM-YYYY)")
			.a("value", paiementDate == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(paiementDate))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchPaiementScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDate', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); } ")
			.fg();
	}

	public void htmPaiementDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolairePaiementDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementDate").a("class", "").f().sx("date de paiement").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPaiementDate(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDate')); $('#", classeApiMethodeMethode, "_paiementDate').val(null); patchPaiementScolaireVal([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementDate', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// paiementMontant //
	/////////////////////

	/**	L'entité « paiementMontant »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal paiementMontant;
	@JsonIgnore
	public Couverture<BigDecimal> paiementMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("paiementMontant").o(paiementMontant);

	/**	<br/>L'entité « paiementMontant »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementMontant">Trouver l'entité paiementMontant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementMontant(Couverture<BigDecimal> c);

	public BigDecimal getPaiementMontant() {
		return paiementMontant;
	}

	public void setPaiementMontant(BigDecimal paiementMontant) {
		this.paiementMontant = paiementMontant;
		this.paiementMontantCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementMontant(Double o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementMontant(Integer o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementMontantInit() {
		if(!paiementMontantCouverture.dejaInitialise) {
			_paiementMontant(paiementMontantCouverture);
			if(paiementMontant == null)
				setPaiementMontant(paiementMontantCouverture.o);
		}
		paiementMontantCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Double solrPaiementMontant() {
		return paiementMontant == null ? null : paiementMontant.doubleValue();
	}

	public String strPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.setScale(2).toString();
	}

	public String jsonPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.toString();
	}

	public String nomAffichagePaiementMontant() {
		return "montant";
	}

	public String htmTooltipPaiementMontant() {
		return null;
	}

	public String htmPaiementMontant() {
		return paiementMontant == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementMontant());
	}

	public void inputPaiementMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "montant")
			.a("title", "La clé primaire des enfants dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_paiementMontant");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementMontant w3-input w3-border ");
				a("name", "setPaiementMontant");
			} else {
				a("class", "valeurPaiementMontant w3-input w3-border ");
				a("name", "paiementMontant");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchPaiementScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementMontant', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ");
			}
			a("value", strPaiementMontant())
		.fg();

	}

	public void htmPaiementMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolairePaiementMontant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementMontant").a("class", "").f().sx("montant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementMontant(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementMontant')); $('#", classeApiMethodeMethode, "_paiementMontant').val(null); patchPaiementScolaireVal([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementMontant', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// paiementEspeces //
	/////////////////////

	/**	L'entité « paiementEspeces »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paiementEspeces;
	@JsonIgnore
	public Couverture<Boolean> paiementEspecesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementEspeces").o(paiementEspeces);

	/**	<br/>L'entité « paiementEspeces »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementEspeces">Trouver l'entité paiementEspeces dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementEspeces(Couverture<Boolean> c);

	public Boolean getPaiementEspeces() {
		return paiementEspeces;
	}

	public void setPaiementEspeces(Boolean paiementEspeces) {
		this.paiementEspeces = paiementEspeces;
		this.paiementEspecesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementEspeces(String o) {
		this.paiementEspeces = Boolean.parseBoolean(o);
		this.paiementEspecesCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementEspecesInit() {
		if(!paiementEspecesCouverture.dejaInitialise) {
			_paiementEspeces(paiementEspecesCouverture);
			if(paiementEspeces == null)
				setPaiementEspeces(paiementEspecesCouverture.o);
		}
		paiementEspecesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementEspeces() {
		return paiementEspeces;
	}

	public String strPaiementEspeces() {
		return paiementEspeces == null ? "" : paiementEspeces.toString();
	}

	public String jsonPaiementEspeces() {
		return paiementEspeces == null ? "" : paiementEspeces.toString();
	}

	public String nomAffichagePaiementEspeces() {
		return "espèces";
	}

	public String htmTooltipPaiementEspeces() {
		return null;
	}

	public String htmPaiementEspeces() {
		return paiementEspeces == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementEspeces());
	}

	public void inputPaiementEspeces(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("input")
			.a("type", "checkbox")
			.a("id", classeApiMethodeMethode, "_paiementEspeces")
			.a("value", "true");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementEspeces");
				a("name", "setPaiementEspeces");
			} else {
				a("class", "valeurPaiementEspeces");
				a("name", "paiementEspeces");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchPaiementScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementEspeces', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementEspeces')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementEspeces')); }); ");
			}
			;
			if(getPaiementEspeces() != null && getPaiementEspeces())
				a("checked", "checked");
		fg();

	}

	public void htmPaiementEspeces(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolairePaiementEspeces").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementEspeces").a("class", "").f().sx("espèces").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementEspeces(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// paiementCheque //
	////////////////////

	/**	L'entité « paiementCheque »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paiementCheque;
	@JsonIgnore
	public Couverture<Boolean> paiementChequeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementCheque").o(paiementCheque);

	/**	<br/>L'entité « paiementCheque »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCheque">Trouver l'entité paiementCheque dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementCheque(Couverture<Boolean> c);

	public Boolean getPaiementCheque() {
		return paiementCheque;
	}

	public void setPaiementCheque(Boolean paiementCheque) {
		this.paiementCheque = paiementCheque;
		this.paiementChequeCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementCheque(String o) {
		this.paiementCheque = Boolean.parseBoolean(o);
		this.paiementChequeCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementChequeInit() {
		if(!paiementChequeCouverture.dejaInitialise) {
			_paiementCheque(paiementChequeCouverture);
			if(paiementCheque == null)
				setPaiementCheque(paiementChequeCouverture.o);
		}
		paiementChequeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementCheque() {
		return paiementCheque;
	}

	public String strPaiementCheque() {
		return paiementCheque == null ? "" : paiementCheque.toString();
	}

	public String jsonPaiementCheque() {
		return paiementCheque == null ? "" : paiementCheque.toString();
	}

	public String nomAffichagePaiementCheque() {
		return "chèque";
	}

	public String htmTooltipPaiementCheque() {
		return null;
	}

	public String htmPaiementCheque() {
		return paiementCheque == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementCheque());
	}

	public void inputPaiementCheque(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		e("input")
			.a("type", "checkbox")
			.a("id", classeApiMethodeMethode, "_paiementCheque")
			.a("value", "true");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementCheque");
				a("name", "setPaiementCheque");
			} else {
				a("class", "valeurPaiementCheque");
				a("name", "paiementCheque");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchPaiementScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementCheque', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementCheque')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementCheque')); }); ");
			}
			;
			if(getPaiementCheque() != null && getPaiementCheque())
				a("checked", "checked");
		fg();

	}

	public void htmPaiementCheque(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggerePaiementScolairePaiementCheque").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementCheque").a("class", "").f().sx("chèque").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementCheque(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// paiementSysteme //
	/////////////////////

	/**	L'entité « paiementSysteme »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean paiementSysteme;
	@JsonIgnore
	public Couverture<Boolean> paiementSystemeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementSysteme").o(paiementSysteme);

	/**	<br/>L'entité « paiementSysteme »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementSysteme">Trouver l'entité paiementSysteme dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementSysteme(Couverture<Boolean> c);

	public Boolean getPaiementSysteme() {
		return paiementSysteme;
	}

	public void setPaiementSysteme(Boolean paiementSysteme) {
		this.paiementSysteme = paiementSysteme;
		this.paiementSystemeCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementSysteme(String o) {
		this.paiementSysteme = Boolean.parseBoolean(o);
		this.paiementSystemeCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementSystemeInit() {
		if(!paiementSystemeCouverture.dejaInitialise) {
			_paiementSysteme(paiementSystemeCouverture);
			if(paiementSysteme == null)
				setPaiementSysteme(paiementSystemeCouverture.o);
		}
		paiementSystemeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementSysteme() {
		return paiementSysteme;
	}

	public String strPaiementSysteme() {
		return paiementSysteme == null ? "" : paiementSysteme.toString();
	}

	public String jsonPaiementSysteme() {
		return paiementSysteme == null ? "" : paiementSysteme.toString();
	}

	public String nomAffichagePaiementSysteme() {
		return "authorisé";
	}

	public String htmTooltipPaiementSysteme() {
		return null;
	}

	public String htmPaiementSysteme() {
		return paiementSysteme == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementSysteme());
	}

	////////////////////////
	// paiementNomComplet //
	////////////////////////

	/**	L'entité « paiementNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String paiementNomComplet;
	@JsonIgnore
	public Couverture<String> paiementNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("paiementNomComplet").o(paiementNomComplet);

	/**	<br/>L'entité « paiementNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomComplet">Trouver l'entité paiementNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomComplet(Couverture<String> c);

	public String getPaiementNomComplet() {
		return paiementNomComplet;
	}

	public void setPaiementNomComplet(String paiementNomComplet) {
		this.paiementNomComplet = paiementNomComplet;
		this.paiementNomCompletCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementNomCompletInit() {
		if(!paiementNomCompletCouverture.dejaInitialise) {
			_paiementNomComplet(paiementNomCompletCouverture);
			if(paiementNomComplet == null)
				setPaiementNomComplet(paiementNomCompletCouverture.o);
		}
		paiementNomCompletCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementNomComplet() {
		return paiementNomComplet;
	}

	public String strPaiementNomComplet() {
		return paiementNomComplet == null ? "" : paiementNomComplet;
	}

	public String jsonPaiementNomComplet() {
		return paiementNomComplet == null ? "" : paiementNomComplet;
	}

	public String nomAffichagePaiementNomComplet() {
		return "nom";
	}

	public String htmTooltipPaiementNomComplet() {
		return null;
	}

	public String htmPaiementNomComplet() {
		return paiementNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementNomComplet());
	}

	public void inputPaiementNomComplet(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
	}

	public void htmPaiementNomComplet(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("nom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(strPaiementNomComplet()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			}
		} g("div");
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePaiementScolaire = false;

	public PaiementScolaire initLoinPaiementScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePaiementScolaire) {
			dejaInitialisePaiementScolaire = true;
			initLoinPaiementScolaire();
		}
		return (PaiementScolaire)this;
	}

	public void initLoinPaiementScolaire() {
		initPaiementScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initPaiementScolaire() {
		ecoleClesInit();
		anneeClesInit();
		saisonCleInit();
		sessionClesInit();
		ageClesInit();
		blocClesInit();
		inscriptionClesInit();
		paiementCleInit();
		enfantClesInit();
		mereClesInit();
		pereClesInit();
		gardienClesInit();
		contactClesInit();
		inscriptionRechercheInit();
		inscription_Init();
		paiementDescriptionInit();
		paiementDateInit();
		paiementMontantInit();
		paiementEspecesInit();
		paiementChequeInit();
		paiementSystemeInit();
		paiementNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPaiementScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePaiementScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(inscriptionRecherche != null)
			inscriptionRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePaiementScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPaiementScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPaiementScolaire(String var) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		switch(var) {
			case "ecoleCles":
				return oPaiementScolaire.ecoleCles;
			case "anneeCles":
				return oPaiementScolaire.anneeCles;
			case "saisonCle":
				return oPaiementScolaire.saisonCle;
			case "sessionCles":
				return oPaiementScolaire.sessionCles;
			case "ageCles":
				return oPaiementScolaire.ageCles;
			case "blocCles":
				return oPaiementScolaire.blocCles;
			case "inscriptionCles":
				return oPaiementScolaire.inscriptionCles;
			case "paiementCle":
				return oPaiementScolaire.paiementCle;
			case "enfantCles":
				return oPaiementScolaire.enfantCles;
			case "mereCles":
				return oPaiementScolaire.mereCles;
			case "pereCles":
				return oPaiementScolaire.pereCles;
			case "gardienCles":
				return oPaiementScolaire.gardienCles;
			case "contactCles":
				return oPaiementScolaire.contactCles;
			case "inscriptionRecherche":
				return oPaiementScolaire.inscriptionRecherche;
			case "inscription_":
				return oPaiementScolaire.inscription_;
			case "paiementDescription":
				return oPaiementScolaire.paiementDescription;
			case "paiementDate":
				return oPaiementScolaire.paiementDate;
			case "paiementMontant":
				return oPaiementScolaire.paiementMontant;
			case "paiementEspeces":
				return oPaiementScolaire.paiementEspeces;
			case "paiementCheque":
				return oPaiementScolaire.paiementCheque;
			case "paiementSysteme":
				return oPaiementScolaire.paiementSysteme;
			case "paiementNomComplet":
				return oPaiementScolaire.paiementNomComplet;
			default:
				return super.obtenirCluster(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerPaiementScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPaiementScolaire(String var, Object val) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		switch(var) {
			case "inscriptionCles":
				oPaiementScolaire.addInscriptionCles((Long)val);
				return val;
			default:
				return super.attribuerCluster(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirPaiementScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPaiementScolaire(String var, String val) {
		switch(var) {
			case "paiementDescription":
				setPaiementDescription(val);
				sauvegardesPaiementScolaire.add(var);
				return val;
			case "paiementDate":
				setPaiementDate(val);
				sauvegardesPaiementScolaire.add(var);
				return val;
			case "paiementMontant":
				setPaiementMontant(val);
				sauvegardesPaiementScolaire.add(var);
				return val;
			case "paiementEspeces":
				setPaiementEspeces(val);
				sauvegardesPaiementScolaire.add(var);
				return val;
			case "paiementCheque":
				setPaiementCheque(val);
				sauvegardesPaiementScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesPaiementScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerPaiementScolaire(solrDocument);
	}
	public void peuplerPaiementScolaire(SolrDocument solrDocument) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		sauvegardesPaiementScolaire = (List<String>)solrDocument.get("sauvegardesPaiementScolaire_stored_strings");
		if(sauvegardesPaiementScolaire != null) {

			if(sauvegardesPaiementScolaire.contains("ecoleCles")) {
				List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
				if(ecoleCles != null)
					oPaiementScolaire.ecoleCles.addAll(ecoleCles);
			}

			if(sauvegardesPaiementScolaire.contains("saisonCle")) {
				List<Long> saisonCle = (List<Long>)solrDocument.get("saisonCle_stored_longs");
				if(saisonCle != null)
					oPaiementScolaire.saisonCle.addAll(saisonCle);
			}

			if(sauvegardesPaiementScolaire.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oPaiementScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardesPaiementScolaire.contains("ageCles")) {
				List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
				if(ageCles != null)
					oPaiementScolaire.ageCles.addAll(ageCles);
			}

			if(sauvegardesPaiementScolaire.contains("blocCles")) {
				List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
				if(blocCles != null)
					oPaiementScolaire.blocCles.addAll(blocCles);
			}

			List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
			if(inscriptionCles != null)
				oPaiementScolaire.inscriptionCles.addAll(inscriptionCles);

			if(sauvegardesPaiementScolaire.contains("paiementCle")) {
				Long paiementCle = (Long)solrDocument.get("paiementCle_stored_long");
				if(paiementCle != null)
					oPaiementScolaire.setPaiementCle(paiementCle);
			}

			if(sauvegardesPaiementScolaire.contains("enfantCles")) {
				List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
				if(enfantCles != null)
					oPaiementScolaire.enfantCles.addAll(enfantCles);
			}

			if(sauvegardesPaiementScolaire.contains("mereCles")) {
				List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
				if(mereCles != null)
					oPaiementScolaire.mereCles.addAll(mereCles);
			}

			if(sauvegardesPaiementScolaire.contains("pereCles")) {
				List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
				if(pereCles != null)
					oPaiementScolaire.pereCles.addAll(pereCles);
			}

			if(sauvegardesPaiementScolaire.contains("gardienCles")) {
				List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
				if(gardienCles != null)
					oPaiementScolaire.gardienCles.addAll(gardienCles);
			}

			if(sauvegardesPaiementScolaire.contains("contactCles")) {
				List<Long> contactCles = (List<Long>)solrDocument.get("contactCles_stored_longs");
				if(contactCles != null)
					oPaiementScolaire.contactCles.addAll(contactCles);
			}

			if(sauvegardesPaiementScolaire.contains("paiementDescription")) {
				String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
				if(paiementDescription != null)
					oPaiementScolaire.setPaiementDescription(paiementDescription);
			}

			if(sauvegardesPaiementScolaire.contains("paiementDate")) {
				Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
				if(paiementDate != null)
					oPaiementScolaire.setPaiementDate(paiementDate);
			}

			if(sauvegardesPaiementScolaire.contains("paiementMontant")) {
				Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
				if(paiementMontant != null)
					oPaiementScolaire.setPaiementMontant(paiementMontant);
			}

			if(sauvegardesPaiementScolaire.contains("paiementEspeces")) {
				Boolean paiementEspeces = (Boolean)solrDocument.get("paiementEspeces_stored_boolean");
				if(paiementEspeces != null)
					oPaiementScolaire.setPaiementEspeces(paiementEspeces);
			}

			if(sauvegardesPaiementScolaire.contains("paiementCheque")) {
				Boolean paiementCheque = (Boolean)solrDocument.get("paiementCheque_stored_boolean");
				if(paiementCheque != null)
					oPaiementScolaire.setPaiementCheque(paiementCheque);
			}

			if(sauvegardesPaiementScolaire.contains("paiementSysteme")) {
				Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
				if(paiementSysteme != null)
					oPaiementScolaire.setPaiementSysteme(paiementSysteme);
			}

			if(sauvegardesPaiementScolaire.contains("paiementNomComplet")) {
				String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
				if(paiementNomComplet != null)
					oPaiementScolaire.setPaiementNomComplet(paiementNomComplet);
			}
		}

		super.peuplerCluster(solrDocument);
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			SolrQuery rechercheSolr = new SolrQuery();
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.paiement.PaiementScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			PaiementScolaire o = new PaiementScolaire();
			o.requeteSitePaiementScolaire(requeteSite);
			o.initLoinPaiementScolaire(requeteSite);
			o.indexerPaiementScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerPaiementScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerPaiementScolaire(document);
	}

	public void indexerPaiementScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPaiementScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPaiementScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPaiementScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPaiementScolaire(SolrInputDocument document) {
		if(sauvegardesPaiementScolaire != null)
			document.addField("sauvegardesPaiementScolaire_stored_strings", sauvegardesPaiementScolaire);

		if(ecoleCles != null) {
			for(java.lang.Long o : ecoleCles) {
				document.addField("ecoleCles_indexed_longs", o);
			}
			for(java.lang.Long o : ecoleCles) {
				document.addField("ecoleCles_stored_longs", o);
			}
		}
		if(saisonCle != null) {
			for(java.lang.Long o : saisonCle) {
				document.addField("saisonCle_indexed_longs", o);
			}
			for(java.lang.Long o : saisonCle) {
				document.addField("saisonCle_stored_longs", o);
			}
		}
		if(sessionCles != null) {
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_indexed_longs", o);
			}
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_stored_longs", o);
			}
		}
		if(ageCles != null) {
			for(java.lang.Long o : ageCles) {
				document.addField("ageCles_indexed_longs", o);
			}
			for(java.lang.Long o : ageCles) {
				document.addField("ageCles_stored_longs", o);
			}
		}
		if(blocCles != null) {
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_indexed_longs", o);
			}
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_stored_longs", o);
			}
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
			}
		}
		if(paiementCle != null) {
			document.addField("paiementCle_indexed_long", paiementCle);
			document.addField("paiementCle_stored_long", paiementCle);
		}
		if(enfantCles != null) {
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_indexed_longs", o);
			}
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_stored_longs", o);
			}
		}
		if(mereCles != null) {
			for(java.lang.Long o : mereCles) {
				document.addField("mereCles_indexed_longs", o);
			}
			for(java.lang.Long o : mereCles) {
				document.addField("mereCles_stored_longs", o);
			}
		}
		if(pereCles != null) {
			for(java.lang.Long o : pereCles) {
				document.addField("pereCles_indexed_longs", o);
			}
			for(java.lang.Long o : pereCles) {
				document.addField("pereCles_stored_longs", o);
			}
		}
		if(gardienCles != null) {
			for(java.lang.Long o : gardienCles) {
				document.addField("gardienCles_indexed_longs", o);
			}
			for(java.lang.Long o : gardienCles) {
				document.addField("gardienCles_stored_longs", o);
			}
		}
		if(contactCles != null) {
			for(java.lang.Long o : contactCles) {
				document.addField("contactCles_indexed_longs", o);
			}
			for(java.lang.Long o : contactCles) {
				document.addField("contactCles_stored_longs", o);
			}
		}
		if(paiementDescription != null) {
			document.addField("paiementDescription_indexed_string", paiementDescription);
			document.addField("paiementDescription_stored_string", paiementDescription);
		}
		if(paiementDate != null) {
			document.addField("paiementDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paiementMontant != null) {
			document.addField("paiementMontant_indexed_double", paiementMontant.doubleValue());
			document.addField("paiementMontant_stored_double", paiementMontant.doubleValue());
		}
		if(paiementEspeces != null) {
			document.addField("paiementEspeces_indexed_boolean", paiementEspeces);
			document.addField("paiementEspeces_stored_boolean", paiementEspeces);
		}
		if(paiementCheque != null) {
			document.addField("paiementCheque_indexed_boolean", paiementCheque);
			document.addField("paiementCheque_stored_boolean", paiementCheque);
		}
		if(paiementSysteme != null) {
			document.addField("paiementSysteme_indexed_boolean", paiementSysteme);
			document.addField("paiementSysteme_stored_boolean", paiementSysteme);
		}
		if(paiementNomComplet != null) {
			document.addField("paiementNomComplet_indexed_string", paiementNomComplet);
			document.addField("paiementNomComplet_stored_string", paiementNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerPaiementScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinPaiementScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			case "ecoleCles":
				return "ecoleCles_indexed_longs";
			case "saisonCle":
				return "saisonCle_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "ageCles":
				return "ageCles_indexed_longs";
			case "blocCles":
				return "blocCles_indexed_longs";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "paiementCle":
				return "paiementCle_indexed_long";
			case "enfantCles":
				return "enfantCles_indexed_longs";
			case "mereCles":
				return "mereCles_indexed_longs";
			case "pereCles":
				return "pereCles_indexed_longs";
			case "gardienCles":
				return "gardienCles_indexed_longs";
			case "contactCles":
				return "contactCles_indexed_longs";
			case "paiementDescription":
				return "paiementDescription_indexed_string";
			case "paiementDate":
				return "paiementDate_indexed_date";
			case "paiementMontant":
				return "paiementMontant_indexed_double";
			case "paiementEspeces":
				return "paiementEspeces_indexed_boolean";
			case "paiementCheque":
				return "paiementCheque_indexed_boolean";
			case "paiementSysteme":
				return "paiementSysteme_indexed_boolean";
			case "paiementNomComplet":
				return "paiementNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRecherchePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggerePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerPaiementScolaire(solrDocument);
	}
	public void stockerPaiementScolaire(SolrDocument solrDocument) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;

		List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
		if(ecoleCles != null)
			oPaiementScolaire.ecoleCles.addAll(ecoleCles);

		List<Long> saisonCle = (List<Long>)solrDocument.get("saisonCle_stored_longs");
		if(saisonCle != null)
			oPaiementScolaire.saisonCle.addAll(saisonCle);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oPaiementScolaire.sessionCles.addAll(sessionCles);

		List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
		if(ageCles != null)
			oPaiementScolaire.ageCles.addAll(ageCles);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oPaiementScolaire.blocCles.addAll(blocCles);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oPaiementScolaire.inscriptionCles.addAll(inscriptionCles);

		Long paiementCle = (Long)solrDocument.get("paiementCle_stored_long");
		if(paiementCle != null)
			oPaiementScolaire.setPaiementCle(paiementCle);

		List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
		if(enfantCles != null)
			oPaiementScolaire.enfantCles.addAll(enfantCles);

		List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
		if(mereCles != null)
			oPaiementScolaire.mereCles.addAll(mereCles);

		List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
		if(pereCles != null)
			oPaiementScolaire.pereCles.addAll(pereCles);

		List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
		if(gardienCles != null)
			oPaiementScolaire.gardienCles.addAll(gardienCles);

		List<Long> contactCles = (List<Long>)solrDocument.get("contactCles_stored_longs");
		if(contactCles != null)
			oPaiementScolaire.contactCles.addAll(contactCles);

		String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
		if(paiementDescription != null)
			oPaiementScolaire.setPaiementDescription(paiementDescription);

		Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
		if(paiementDate != null)
			oPaiementScolaire.setPaiementDate(paiementDate);

		Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
		if(paiementMontant != null)
			oPaiementScolaire.setPaiementMontant(paiementMontant);

		Boolean paiementEspeces = (Boolean)solrDocument.get("paiementEspeces_stored_boolean");
		if(paiementEspeces != null)
			oPaiementScolaire.setPaiementEspeces(paiementEspeces);

		Boolean paiementCheque = (Boolean)solrDocument.get("paiementCheque_stored_boolean");
		if(paiementCheque != null)
			oPaiementScolaire.setPaiementCheque(paiementCheque);

		Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
		if(paiementSysteme != null)
			oPaiementScolaire.setPaiementSysteme(paiementSysteme);

		String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
		if(paiementNomComplet != null)
			oPaiementScolaire.setPaiementNomComplet(paiementNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), inscriptionCles, paiementDescription, paiementDate, paiementMontant, paiementEspeces, paiementCheque);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PaiementScolaire))
			return false;
		PaiementScolaire that = (PaiementScolaire)o;
		return super.equals(o)
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( paiementDescription, that.paiementDescription )
				&& Objects.equals( paiementDate, that.paiementDate )
				&& Objects.equals( paiementMontant, that.paiementMontant )
				&& Objects.equals( paiementEspeces, that.paiementEspeces )
				&& Objects.equals( paiementCheque, that.paiementCheque );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaiementScolaire { ");
		sb.append( "inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", paiementDescription: \"" ).append(paiementDescription).append( "\"" );
		sb.append( ", paiementDate: " ).append(paiementDate);
		sb.append( ", paiementMontant: " ).append(paiementMontant);
		sb.append( ", paiementEspeces: " ).append(paiementEspeces);
		sb.append( ", paiementCheque: " ).append(paiementCheque);
		sb.append(" }");
		return sb.toString();
	}
}
