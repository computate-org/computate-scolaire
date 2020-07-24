package org.computate.scolaire.frFR.ecole;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true">Trouver la classe schoolCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class EcoleGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(Ecole.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String Ecole_UnNom = "une école";
	public static final String Ecole_Ce = "cette ";
	public static final String Ecole_CeNom = "cette école";
	public static final String Ecole_Un = "une ";
	public static final String Ecole_LeNom = "l'école";
	public static final String Ecole_NomSingulier = "école";
	public static final String Ecole_NomPluriel = "écoles";
	public static final String Ecole_NomActuel = "école actuelle";
	public static final String Ecole_Tous = "all ";
	public static final String Ecole_TousNom = "toutes les écoles";
	public static final String Ecole_RechercherTousNomPar = "rechercher écoles par ";
	public static final String Ecole_RechercherTousNom = "rechercher écoles";
	public static final String Ecole_Titre = "écoles";
	public static final String Ecole_LesNom = "les écoles";
	public static final String Ecole_AucunNomTrouve = "aucune école trouvée";
	public static final String Ecole_NomVar = "école";
	public static final String Ecole_DeNom = "d'école";
	public static final String Ecole_NomAdjectifSingulier = "école";
	public static final String Ecole_NomAdjectifPluriel = "écoles";
	public static final String Ecole_Couleur = "pink";
	public static final String Ecole_IconeGroupe = "regular";
	public static final String Ecole_IconeNom = "school";

	//////////////
	// ecoleCle //
	//////////////

	/**	 L'entité ecoleCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ecoleCle;
	@JsonIgnore
	public Couverture<Long> ecoleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/> L'entité ecoleCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleCle(Couverture<Long> c);

	public Long getEcoleCle() {
		return ecoleCle;
	}

	public void setEcoleCle(Long ecoleCle) {
		this.ecoleCle = ecoleCle;
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public Ecole setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (Ecole)this;
	}
	protected Ecole ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public Long solrEcoleCle() {
		return ecoleCle;
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String jsonEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return "clé";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	///////////////
	// anneeCles //
	///////////////

	/**	 L'entité anneeCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> anneeCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> anneeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/> L'entité anneeCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> o);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public Ecole addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (Ecole)this;
	}
	public Ecole setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (Ecole)this;
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
		return "années";
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	public void inputAnneeCles(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "années")
					.a("title", "Description.enUS: ")
					.a("class", "valeur suggereAnneeCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAnneeCles")
					.a("id", classeApiMethodeMethode, "_anneeCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereEcoleAnneeCles($(this).val() ? rechercherAnneeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'ecoleCle:" + pk + "'}", "], $('#listEcoleAnneeCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmAnneeCles());
			}
		}
	}

	public void htmAnneeCles(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleAnneeCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=ecoleCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
								sx("années");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette école");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAnneeCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEcoleAnneeCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AnneeScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AnneeScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
											.a("id", classeApiMethodeMethode, "_anneeCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postAnneeScolaireVals({ ecoleCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "anneeCles')); });")
											.f().sx("ajouter une année")
										.g("button");
									} g("div");
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// saisonCles //
	////////////////

	/**	 L'entité saisonCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> saisonCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> saisonClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/> L'entité saisonCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
	 * <br/>
	 * @param saisonCles est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCles(List<Long> o);

	public List<Long> getSaisonCles() {
		return saisonCles;
	}

	public void setSaisonCles(List<Long> saisonCles) {
		this.saisonCles = saisonCles;
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public Ecole addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (Ecole)this;
	}
	public Ecole setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addSaisonCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
	}

	public String strSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String jsonSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String nomAffichageSaisonCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipSaisonCles() {
		return null;
	}

	public String htmSaisonCles() {
		return saisonCles == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCles());
	}

	/////////////////
	// sessionCles //
	/////////////////

	/**	 L'entité sessionCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> sessionCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/> L'entité sessionCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> o);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public Ecole addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (Ecole)this;
	}
	public Ecole setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (Ecole)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipSessionCles() {
		return null;
	}

	public String htmSessionCles() {
		return sessionCles == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCles());
	}

	///////////////////
	// groupeAgeCles //
	///////////////////

	/**	 L'entité groupeAgeCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> groupeAgeCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> groupeAgeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("groupeAgeCles").o(groupeAgeCles);

	/**	<br/> L'entité groupeAgeCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:groupeAgeCles">Trouver l'entité groupeAgeCles dans Solr</a>
	 * <br/>
	 * @param groupeAgeCles est l'entité déjà construit. 
	 **/
	protected abstract void _groupeAgeCles(List<Long> o);

	public List<Long> getGroupeAgeCles() {
		return groupeAgeCles;
	}

	public void setGroupeAgeCles(List<Long> groupeAgeCles) {
		this.groupeAgeCles = groupeAgeCles;
		this.groupeAgeClesCouverture.dejaInitialise = true;
	}
	public Ecole addGroupeAgeCles(Long...objets) {
		for(Long o : objets) {
			addGroupeAgeCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addGroupeAgeCles(Long o) {
		if(o != null && !groupeAgeCles.contains(o))
			this.groupeAgeCles.add(o);
		return (Ecole)this;
	}
	public Ecole setGroupeAgeCles(JsonArray objets) {
		groupeAgeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGroupeAgeCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addGroupeAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGroupeAgeCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole groupeAgeClesInit() {
		if(!groupeAgeClesCouverture.dejaInitialise) {
			_groupeAgeCles(groupeAgeCles);
		}
		groupeAgeClesCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public List<Long> solrGroupeAgeCles() {
		return groupeAgeCles;
	}

	public String strGroupeAgeCles() {
		return groupeAgeCles == null ? "" : groupeAgeCles.toString();
	}

	public String jsonGroupeAgeCles() {
		return groupeAgeCles == null ? "" : groupeAgeCles.toString();
	}

	public String nomAffichageGroupeAgeCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipGroupeAgeCles() {
		return null;
	}

	public String htmGroupeAgeCles() {
		return groupeAgeCles == null ? "" : StringEscapeUtils.escapeHtml4(strGroupeAgeCles());
	}

	//////////////
	// blocCles //
	//////////////

	/**	 L'entité blocCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> blocCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> blocClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/> L'entité blocCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
	 * <br/>
	 * @param blocCles est l'entité déjà construit. 
	 **/
	protected abstract void _blocCles(List<Long> o);

	public List<Long> getBlocCles() {
		return blocCles;
	}

	public void setBlocCles(List<Long> blocCles) {
		this.blocCles = blocCles;
		this.blocClesCouverture.dejaInitialise = true;
	}
	public Ecole addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (Ecole)this;
	}
	public Ecole setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addBlocCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole blocClesInit() {
		if(!blocClesCouverture.dejaInitialise) {
			_blocCles(blocCles);
		}
		blocClesCouverture.dejaInitialise(true);
		return (Ecole)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipBlocCles() {
		return null;
	}

	public String htmBlocCles() {
		return blocCles == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCles());
	}

	////////////////
	// enfantCles //
	////////////////

	/**	 L'entité enfantCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enfantCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> enfantClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("enfantCles").o(enfantCles);

	/**	<br/> L'entité enfantCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCles">Trouver l'entité enfantCles dans Solr</a>
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
	public Ecole addEnfantCles(Long...objets) {
		for(Long o : objets) {
			addEnfantCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addEnfantCles(Long o) {
		if(o != null && !enfantCles.contains(o))
			this.enfantCles.add(o);
		return (Ecole)this;
	}
	public Ecole setEnfantCles(JsonArray objets) {
		enfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnfantCles(o);
		}
		return (Ecole)this;
	}
	public Ecole addEnfantCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnfantCles(p);
		}
		return (Ecole)this;
	}
	protected Ecole enfantClesInit() {
		if(!enfantClesCouverture.dejaInitialise) {
			_enfantCles(enfantCles);
		}
		enfantClesCouverture.dejaInitialise(true);
		return (Ecole)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEnfantCles() {
		return null;
	}

	public String htmEnfantCles() {
		return enfantCles == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCles());
	}

	/////////////////
	// scolaireTri //
	/////////////////

	/**	 L'entité scolaireTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer scolaireTri;
	@JsonIgnore
	public Couverture<Integer> scolaireTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/> L'entité scolaireTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _scolaireTri(Couverture<Integer> c);

	public Integer getScolaireTri() {
		return scolaireTri;
	}

	public void setScolaireTri(Integer scolaireTri) {
		this.scolaireTri = scolaireTri;
		this.scolaireTriCouverture.dejaInitialise = true;
	}
	public Ecole setScolaireTri(String o) {
		if(NumberUtils.isParsable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (Ecole)this;
	}
	protected Ecole scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public Integer solrScolaireTri() {
		return scolaireTri;
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String jsonScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String nomAffichageScolaireTri() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipScolaireTri() {
		return null;
	}

	public String htmScolaireTri() {
		return scolaireTri == null ? "" : StringEscapeUtils.escapeHtml4(strScolaireTri());
	}

	//////////////
	// ecoleTri //
	//////////////

	/**	 L'entité ecoleTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleTri;
	@JsonIgnore
	public Couverture<Integer> ecoleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/> L'entité ecoleTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleTri(Couverture<Integer> c);

	public Integer getEcoleTri() {
		return ecoleTri;
	}

	public void setEcoleTri(Integer ecoleTri) {
		this.ecoleTri = ecoleTri;
		this.ecoleTriCouverture.dejaInitialise = true;
	}
	public Ecole setEcoleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (Ecole)this;
	}
	protected Ecole ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public Integer solrEcoleTri() {
		return ecoleTri;
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String jsonEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String nomAffichageEcoleTri() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleTri() {
		return null;
	}

	public String htmEcoleTri() {
		return ecoleTri == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleTri());
	}

	//////////////
	// ecoleNom //
	//////////////

	/**	 L'entité ecoleNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNom;
	@JsonIgnore
	public Couverture<String> ecoleNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/> L'entité ecoleNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Couverture<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}

	public void setEcoleNom(String ecoleNom) {
		this.ecoleNom = ecoleNom;
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleNom() {
		return ecoleNom;
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String jsonEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return "nom de l'école";
	}

	public String htmTooltipEcoleNom() {
		return null;
	}

	public String htmEcoleNom() {
		return ecoleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNom());
	}

	public void inputEcoleNom(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "nom de l'école")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleNom");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleNom classEcole inputEcole", pk, "EcoleNom w3-input w3-border ");
					a("name", "setEcoleNom");
				} else {
					a("class", "valeurEcoleNom w3-input w3-border classEcole inputEcole", pk, "EcoleNom w3-input w3-border ");
					a("name", "ecoleNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNom')); }); ");
				}
				a("value", strEcoleNom())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleNom());
			}
		}
	}

	public void htmEcoleNom(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleNom").a("class", "").f().sx("nom de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleNom(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleNom')); $('#", classeApiMethodeMethode, "_ecoleNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNom')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// ecoleNumeroTelephone //
	//////////////////////////

	/**	 L'entité ecoleNumeroTelephone
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNumeroTelephone;
	@JsonIgnore
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/> L'entité ecoleNumeroTelephone
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public void setEcoleNumeroTelephone(String ecoleNumeroTelephone) {
		this.ecoleNumeroTelephone = ecoleNumeroTelephone;
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String jsonEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "numéro de téléphone";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	public void inputEcoleNumeroTelephone(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "numéro de téléphone")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleNumeroTelephone");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleNumeroTelephone classEcole inputEcole", pk, "EcoleNumeroTelephone w3-input w3-border ");
					a("name", "setEcoleNumeroTelephone");
				} else {
					a("class", "valeurEcoleNumeroTelephone w3-input w3-border classEcole inputEcole", pk, "EcoleNumeroTelephone w3-input w3-border ");
					a("name", "ecoleNumeroTelephone");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleNumeroTelephone', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNumeroTelephone')); }); ");
				}
				a("value", strEcoleNumeroTelephone())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleNumeroTelephone());
			}
		}
	}

	public void htmEcoleNumeroTelephone(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleNumeroTelephone").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleNumeroTelephone(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleNumeroTelephone')); $('#", classeApiMethodeMethode, "_ecoleNumeroTelephone').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleNumeroTelephone', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNumeroTelephone')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////
	// ecoleForm //
	///////////////

	/**	 L'entité ecoleForm
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleForm;
	@JsonIgnore
	public Couverture<String> ecoleFormCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleForm").o(ecoleForm);

	/**	<br/> L'entité ecoleForm
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleForm">Trouver l'entité ecoleForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleForm(Couverture<String> c);

	public String getEcoleForm() {
		return ecoleForm;
	}

	public void setEcoleForm(String ecoleForm) {
		this.ecoleForm = ecoleForm;
		this.ecoleFormCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleFormInit() {
		if(!ecoleFormCouverture.dejaInitialise) {
			_ecoleForm(ecoleFormCouverture);
			if(ecoleForm == null)
				setEcoleForm(ecoleFormCouverture.o);
		}
		ecoleFormCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleForm() {
		return ecoleForm;
	}

	public String strEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String jsonEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String nomAffichageEcoleForm() {
		return "école formulaire";
	}

	public String htmTooltipEcoleForm() {
		return null;
	}

	public String htmEcoleForm() {
		return ecoleForm == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleForm());
	}

	public void inputEcoleForm(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "école formulaire")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleForm");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleForm classEcole inputEcole", pk, "EcoleForm w3-input w3-border ");
					a("name", "setEcoleForm");
				} else {
					a("class", "valeurEcoleForm w3-input w3-border classEcole inputEcole", pk, "EcoleForm w3-input w3-border ");
					a("name", "ecoleForm");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleForm', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleForm')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleForm')); }); ");
				}
				a("value", strEcoleForm())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleForm());
			}
		}
	}

	public void htmEcoleForm(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleForm").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleForm").a("class", "").f().sx("école formulaire").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleForm(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleForm')); $('#", classeApiMethodeMethode, "_ecoleForm').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleForm', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleForm')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleForm')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// ecoleNumero //
	/////////////////

	/**	 L'entité ecoleNumero
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleNumero;
	@JsonIgnore
	public Couverture<Integer> ecoleNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleNumero").o(ecoleNumero);

	/**	<br/> L'entité ecoleNumero
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumero(Couverture<Integer> c);

	public Integer getEcoleNumero() {
		return ecoleNumero;
	}

	public void setEcoleNumero(Integer ecoleNumero) {
		this.ecoleNumero = ecoleNumero;
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public Ecole setEcoleNumero(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleNumero = Integer.parseInt(o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
		return (Ecole)this;
	}
	protected Ecole ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public Integer solrEcoleNumero() {
		return ecoleNumero;
	}

	public String strEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String jsonEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String nomAffichageEcoleNumero() {
		return "école numéro";
	}

	public String htmTooltipEcoleNumero() {
		return null;
	}

	public String htmEcoleNumero() {
		return ecoleNumero == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumero());
	}

	public void inputEcoleNumero(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "école numéro")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleNumero");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleNumero classEcole inputEcole", pk, "EcoleNumero w3-input w3-border ");
					a("name", "setEcoleNumero");
				} else {
					a("class", "valeurEcoleNumero w3-input w3-border classEcole inputEcole", pk, "EcoleNumero w3-input w3-border ");
					a("name", "ecoleNumero");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleNumero', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNumero')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNumero')); }); ");
				}
				a("value", strEcoleNumero())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleNumero());
			}
		}
	}

	public void htmEcoleNumero(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleNumero").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleNumero").a("class", "").f().sx("école numéro").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleNumero(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleNumero')); $('#", classeApiMethodeMethode, "_ecoleNumero').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleNumero', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleNumero')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleNumero')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////
	// ecoleAdministrateurNom //
	////////////////////////////

	/**	 L'entité ecoleAdministrateurNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAdministrateurNom;
	@JsonIgnore
	public Couverture<String> ecoleAdministrateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/> L'entité ecoleAdministrateurNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAdministrateurNom(Couverture<String> c);

	public String getEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public void setEcoleAdministrateurNom(String ecoleAdministrateurNom) {
		this.ecoleAdministrateurNom = ecoleAdministrateurNom;
		this.ecoleAdministrateurNomCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public String strEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String jsonEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String nomAffichageEcoleAdministrateurNom() {
		return "administrateur de l'école";
	}

	public String htmTooltipEcoleAdministrateurNom() {
		return null;
	}

	public String htmEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAdministrateurNom());
	}

	public void inputEcoleAdministrateurNom(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "administrateur de l'école")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleAdministrateurNom");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleAdministrateurNom classEcole inputEcole", pk, "EcoleAdministrateurNom w3-input w3-border ");
					a("name", "setEcoleAdministrateurNom");
				} else {
					a("class", "valeurEcoleAdministrateurNom w3-input w3-border classEcole inputEcole", pk, "EcoleAdministrateurNom w3-input w3-border ");
					a("name", "ecoleAdministrateurNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAdministrateurNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAdministrateurNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAdministrateurNom')); }); ");
				}
				a("value", strEcoleAdministrateurNom())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleAdministrateurNom());
			}
		}
	}

	public void htmEcoleAdministrateurNom(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleAdministrateurNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleAdministrateurNom").a("class", "").f().sx("administrateur de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleAdministrateurNom(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAdministrateurNom')); $('#", classeApiMethodeMethode, "_ecoleAdministrateurNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleAdministrateurNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAdministrateurNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAdministrateurNom')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////
	// ecoleMail //
	///////////////

	/**	 L'entité ecoleMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleMail;
	@JsonIgnore
	public Couverture<String> ecoleMailCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleMail").o(ecoleMail);

	/**	<br/> L'entité ecoleMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleMail">Trouver l'entité ecoleMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleMail(Couverture<String> c);

	public String getEcoleMail() {
		return ecoleMail;
	}

	public void setEcoleMail(String ecoleMail) {
		this.ecoleMail = ecoleMail;
		this.ecoleMailCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleMailInit() {
		if(!ecoleMailCouverture.dejaInitialise) {
			_ecoleMail(ecoleMailCouverture);
			if(ecoleMail == null)
				setEcoleMail(ecoleMailCouverture.o);
		}
		ecoleMailCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleMail() {
		return ecoleMail;
	}

	public String strEcoleMail() {
		return ecoleMail == null ? "" : ecoleMail;
	}

	public String jsonEcoleMail() {
		return ecoleMail == null ? "" : ecoleMail;
	}

	public String nomAffichageEcoleMail() {
		return "mail";
	}

	public String htmTooltipEcoleMail() {
		return null;
	}

	public String htmEcoleMail() {
		return ecoleMail == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleMail());
	}

	public void inputEcoleMail(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "mail")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleMail");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleMail classEcole inputEcole", pk, "EcoleMail w3-input w3-border ");
					a("name", "setEcoleMail");
				} else {
					a("class", "valeurEcoleMail w3-input w3-border classEcole inputEcole", pk, "EcoleMail w3-input w3-border ");
					a("name", "ecoleMail");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleMail', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMail')); }); ");
				}
				a("value", strEcoleMail())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleMail());
			}
		}
	}

	public void htmEcoleMail(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleMail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleMail").a("class", "").f().sx("mail").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleMail(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleMail')); $('#", classeApiMethodeMethode, "_ecoleMail').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleMail', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMail')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// ecoleMailDe //
	/////////////////

	/**	 L'entité ecoleMailDe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleMailDe;
	@JsonIgnore
	public Couverture<String> ecoleMailDeCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleMailDe").o(ecoleMailDe);

	/**	<br/> L'entité ecoleMailDe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleMailDe">Trouver l'entité ecoleMailDe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleMailDe(Couverture<String> c);

	public String getEcoleMailDe() {
		return ecoleMailDe;
	}

	public void setEcoleMailDe(String ecoleMailDe) {
		this.ecoleMailDe = ecoleMailDe;
		this.ecoleMailDeCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleMailDeInit() {
		if(!ecoleMailDeCouverture.dejaInitialise) {
			_ecoleMailDe(ecoleMailDeCouverture);
			if(ecoleMailDe == null)
				setEcoleMailDe(ecoleMailDeCouverture.o);
		}
		ecoleMailDeCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleMailDe() {
		return ecoleMailDe;
	}

	public String strEcoleMailDe() {
		return ecoleMailDe == null ? "" : ecoleMailDe;
	}

	public String jsonEcoleMailDe() {
		return ecoleMailDe == null ? "" : ecoleMailDe;
	}

	public String nomAffichageEcoleMailDe() {
		return "mail de l'école de";
	}

	public String htmTooltipEcoleMailDe() {
		return null;
	}

	public String htmEcoleMailDe() {
		return ecoleMailDe == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleMailDe());
	}

	public void inputEcoleMailDe(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "mail de l'école de")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleMailDe");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleMailDe classEcole inputEcole", pk, "EcoleMailDe w3-input w3-border ");
					a("name", "setEcoleMailDe");
				} else {
					a("class", "valeurEcoleMailDe w3-input w3-border classEcole inputEcole", pk, "EcoleMailDe w3-input w3-border ");
					a("name", "ecoleMailDe");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleMailDe', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMailDe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMailDe')); }); ");
				}
				a("value", strEcoleMailDe())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleMailDe());
			}
		}
	}

	public void htmEcoleMailDe(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleMailDe").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleMailDe").a("class", "").f().sx("mail de l'école de").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleMailDe(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleMailDe')); $('#", classeApiMethodeMethode, "_ecoleMailDe').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleMailDe', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMailDe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMailDe')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// ecoleMailA //
	////////////////

	/**	 L'entité ecoleMailA
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleMailA;
	@JsonIgnore
	public Couverture<String> ecoleMailACouverture = new Couverture<String>().p(this).c(String.class).var("ecoleMailA").o(ecoleMailA);

	/**	<br/> L'entité ecoleMailA
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleMailA">Trouver l'entité ecoleMailA dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleMailA(Couverture<String> c);

	public String getEcoleMailA() {
		return ecoleMailA;
	}

	public void setEcoleMailA(String ecoleMailA) {
		this.ecoleMailA = ecoleMailA;
		this.ecoleMailACouverture.dejaInitialise = true;
	}
	protected Ecole ecoleMailAInit() {
		if(!ecoleMailACouverture.dejaInitialise) {
			_ecoleMailA(ecoleMailACouverture);
			if(ecoleMailA == null)
				setEcoleMailA(ecoleMailACouverture.o);
		}
		ecoleMailACouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleMailA() {
		return ecoleMailA;
	}

	public String strEcoleMailA() {
		return ecoleMailA == null ? "" : ecoleMailA;
	}

	public String jsonEcoleMailA() {
		return ecoleMailA == null ? "" : ecoleMailA;
	}

	public String nomAffichageEcoleMailA() {
		return "mail de l'école à";
	}

	public String htmTooltipEcoleMailA() {
		return null;
	}

	public String htmEcoleMailA() {
		return ecoleMailA == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleMailA());
	}

	public void inputEcoleMailA(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "mail de l'école à")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleMailA");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleMailA classEcole inputEcole", pk, "EcoleMailA w3-input w3-border ");
					a("name", "setEcoleMailA");
				} else {
					a("class", "valeurEcoleMailA w3-input w3-border classEcole inputEcole", pk, "EcoleMailA w3-input w3-border ");
					a("name", "ecoleMailA");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleMailA', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMailA')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMailA')); }); ");
				}
				a("value", strEcoleMailA())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleMailA());
			}
		}
	}

	public void htmEcoleMailA(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleMailA").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleMailA").a("class", "").f().sx("mail de l'école à").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleMailA(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleMailA')); $('#", classeApiMethodeMethode, "_ecoleMailA').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleMailA', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleMailA')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleMailA')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// ecoleEmplacement //
	//////////////////////

	/**	 L'entité ecoleEmplacement
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleEmplacement;
	@JsonIgnore
	public Couverture<String> ecoleEmplacementCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleEmplacement").o(ecoleEmplacement);

	/**	<br/> L'entité ecoleEmplacement
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleEmplacement(Couverture<String> c);

	public String getEcoleEmplacement() {
		return ecoleEmplacement;
	}

	public void setEcoleEmplacement(String ecoleEmplacement) {
		this.ecoleEmplacement = ecoleEmplacement;
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleEmplacement() {
		return ecoleEmplacement;
	}

	public String strEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : ecoleEmplacement;
	}

	public String jsonEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : ecoleEmplacement;
	}

	public String nomAffichageEcoleEmplacement() {
		return "l'emplacement";
	}

	public String htmTooltipEcoleEmplacement() {
		return null;
	}

	public String htmEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleEmplacement());
	}

	public void inputEcoleEmplacement(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "l'emplacement")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleEmplacement");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleEmplacement classEcole inputEcole", pk, "EcoleEmplacement w3-input w3-border ");
					a("name", "setEcoleEmplacement");
				} else {
					a("class", "valeurEcoleEmplacement w3-input w3-border classEcole inputEcole", pk, "EcoleEmplacement w3-input w3-border ");
					a("name", "ecoleEmplacement");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleEmplacement', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleEmplacement')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleEmplacement')); }); ");
				}
				a("value", strEcoleEmplacement())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleEmplacement());
			}
		}
	}

	public void htmEcoleEmplacement(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleEmplacement").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleEmplacement").a("class", "").f().sx("l'emplacement").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleEmplacement(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleEmplacement')); $('#", classeApiMethodeMethode, "_ecoleEmplacement').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleEmplacement', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleEmplacement')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleEmplacement')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// ecoleAddresse //
	///////////////////

	/**	 L'entité ecoleAddresse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAddresse;
	@JsonIgnore
	public Couverture<String> ecoleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/> L'entité ecoleAddresse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}

	public void setEcoleAddresse(String ecoleAddresse) {
		this.ecoleAddresse = ecoleAddresse;
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleAddresse() {
		return ecoleAddresse;
	}

	public String strEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String jsonEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String nomAffichageEcoleAddresse() {
		return "addresse";
	}

	public String htmTooltipEcoleAddresse() {
		return null;
	}

	public String htmEcoleAddresse() {
		return ecoleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAddresse());
	}

	public void inputEcoleAddresse(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("textarea")
				.a("placeholder", "addresse")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_ecoleAddresse");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleAddresse classEcole inputEcole", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "setEcoleAddresse");
				} else {
					a("class", "valeurEcoleAddresse w3-input w3-border classEcole inputEcole", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "ecoleAddresse");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ");
				}
			f().sx(strEcoleAddresse()).g("textarea");

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				sx(htmEcoleAddresse());
			}
		}
	}

	public void htmEcoleAddresse(String classeApiMethodeMethode) {
		Ecole s = (Ecole)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EcoleEcoleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleAddresse(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); $('#", classeApiMethodeMethode, "_ecoleAddresse').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=pk]').val() }], 'setEcoleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// ecoleNomCourt //
	///////////////////

	/**	 L'entité ecoleNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNomCourt;
	@JsonIgnore
	public Couverture<String> ecoleNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomCourt").o(ecoleNomCourt);

	/**	<br/> L'entité ecoleNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomCourt">Trouver l'entité ecoleNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomCourt(Couverture<String> c);

	public String getEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public void setEcoleNomCourt(String ecoleNomCourt) {
		this.ecoleNomCourt = ecoleNomCourt;
		this.ecoleNomCourtCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleNomCourtInit() {
		if(!ecoleNomCourtCouverture.dejaInitialise) {
			_ecoleNomCourt(ecoleNomCourtCouverture);
			if(ecoleNomCourt == null)
				setEcoleNomCourt(ecoleNomCourtCouverture.o);
		}
		ecoleNomCourtCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public String strEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : ecoleNomCourt;
	}

	public String jsonEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : ecoleNomCourt;
	}

	public String nomAffichageEcoleNomCourt() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNomCourt() {
		return null;
	}

	public String htmEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomCourt());
	}

	/////////////////////
	// ecoleNomComplet //
	/////////////////////

	/**	 L'entité ecoleNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNomComplet;
	@JsonIgnore
	public Couverture<String> ecoleNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomComplet").o(ecoleNomComplet);

	/**	<br/> L'entité ecoleNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.ecole.Ecole&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomComplet(Couverture<String> c);

	public String getEcoleNomComplet() {
		return ecoleNomComplet;
	}

	public void setEcoleNomComplet(String ecoleNomComplet) {
		this.ecoleNomComplet = ecoleNomComplet;
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	protected Ecole ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (Ecole)this;
	}

	public String solrEcoleNomComplet() {
		return ecoleNomComplet;
	}

	public String strEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String jsonEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String nomAffichageEcoleNomComplet() {
		return "nom";
	}

	public String htmTooltipEcoleNomComplet() {
		return null;
	}

	public String htmEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcole = false;

	public Ecole initLoinEcole(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcole) {
			dejaInitialiseEcole = true;
			initLoinEcole();
		}
		return (Ecole)this;
	}

	public void initLoinEcole() {
		initEcole();
		super.initLoinCluster(requeteSite_);
	}

	public void initEcole() {
		ecoleCleInit();
		anneeClesInit();
		saisonClesInit();
		sessionClesInit();
		groupeAgeClesInit();
		blocClesInit();
		enfantClesInit();
		scolaireTriInit();
		ecoleTriInit();
		ecoleNomInit();
		ecoleNumeroTelephoneInit();
		ecoleFormInit();
		ecoleNumeroInit();
		ecoleAdministrateurNomInit();
		ecoleMailInit();
		ecoleMailDeInit();
		ecoleMailAInit();
		ecoleEmplacementInit();
		ecoleAddresseInit();
		ecoleNomCourtInit();
		ecoleNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEcole(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcole(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEcole(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcole(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcole(String var) {
		Ecole oEcole = (Ecole)this;
		switch(var) {
			case "ecoleCle":
				return oEcole.ecoleCle;
			case "anneeCles":
				return oEcole.anneeCles;
			case "saisonCles":
				return oEcole.saisonCles;
			case "sessionCles":
				return oEcole.sessionCles;
			case "groupeAgeCles":
				return oEcole.groupeAgeCles;
			case "blocCles":
				return oEcole.blocCles;
			case "enfantCles":
				return oEcole.enfantCles;
			case "scolaireTri":
				return oEcole.scolaireTri;
			case "ecoleTri":
				return oEcole.ecoleTri;
			case "ecoleNom":
				return oEcole.ecoleNom;
			case "ecoleNumeroTelephone":
				return oEcole.ecoleNumeroTelephone;
			case "ecoleForm":
				return oEcole.ecoleForm;
			case "ecoleNumero":
				return oEcole.ecoleNumero;
			case "ecoleAdministrateurNom":
				return oEcole.ecoleAdministrateurNom;
			case "ecoleMail":
				return oEcole.ecoleMail;
			case "ecoleMailDe":
				return oEcole.ecoleMailDe;
			case "ecoleMailA":
				return oEcole.ecoleMailA;
			case "ecoleEmplacement":
				return oEcole.ecoleEmplacement;
			case "ecoleAddresse":
				return oEcole.ecoleAddresse;
			case "ecoleNomCourt":
				return oEcole.ecoleNomCourt;
			case "ecoleNomComplet":
				return oEcole.ecoleNomComplet;
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
				o = attribuerEcole(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcole(String var, Object val) {
		Ecole oEcole = (Ecole)this;
		switch(var) {
			case "anneeCles":
				oEcole.addAnneeCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
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
					o = definirEcole(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcole(String var, String val) {
		switch(var) {
			case "ecoleNom":
				if(val != null)
					setEcoleNom(val);
				sauvegardes.add(var);
				return val;
			case "ecoleNumeroTelephone":
				if(val != null)
					setEcoleNumeroTelephone(val);
				sauvegardes.add(var);
				return val;
			case "ecoleForm":
				if(val != null)
					setEcoleForm(val);
				sauvegardes.add(var);
				return val;
			case "ecoleNumero":
				if(val != null)
					setEcoleNumero(val);
				sauvegardes.add(var);
				return val;
			case "ecoleAdministrateurNom":
				if(val != null)
					setEcoleAdministrateurNom(val);
				sauvegardes.add(var);
				return val;
			case "ecoleMail":
				if(val != null)
					setEcoleMail(val);
				sauvegardes.add(var);
				return val;
			case "ecoleMailDe":
				if(val != null)
					setEcoleMailDe(val);
				sauvegardes.add(var);
				return val;
			case "ecoleMailA":
				if(val != null)
					setEcoleMailA(val);
				sauvegardes.add(var);
				return val;
			case "ecoleEmplacement":
				if(val != null)
					setEcoleEmplacement(val);
				sauvegardes.add(var);
				return val;
			case "ecoleAddresse":
				if(val != null)
					setEcoleAddresse(val);
				sauvegardes.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerEcole(solrDocument);
	}
	public void peuplerEcole(SolrDocument solrDocument) {
		Ecole oEcole = (Ecole)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oEcole.setEcoleCle(ecoleCle);
			}

			List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
			if(anneeCles != null)
				oEcole.anneeCles.addAll(anneeCles);

			if(sauvegardes.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oEcole.saisonCles.addAll(saisonCles);
			}

			if(sauvegardes.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oEcole.sessionCles.addAll(sessionCles);
			}

			if(sauvegardes.contains("groupeAgeCles")) {
				List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
				if(groupeAgeCles != null)
					oEcole.groupeAgeCles.addAll(groupeAgeCles);
			}

			if(sauvegardes.contains("blocCles")) {
				List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
				if(blocCles != null)
					oEcole.blocCles.addAll(blocCles);
			}

			if(sauvegardes.contains("enfantCles")) {
				List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
				if(enfantCles != null)
					oEcole.enfantCles.addAll(enfantCles);
			}

			if(sauvegardes.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oEcole.setScolaireTri(scolaireTri);
			}

			if(sauvegardes.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oEcole.setEcoleTri(ecoleTri);
			}

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oEcole.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oEcole.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("ecoleForm")) {
				String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
				if(ecoleForm != null)
					oEcole.setEcoleForm(ecoleForm);
			}

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oEcole.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oEcole.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardes.contains("ecoleMail")) {
				String ecoleMail = (String)solrDocument.get("ecoleMail_stored_string");
				if(ecoleMail != null)
					oEcole.setEcoleMail(ecoleMail);
			}

			if(sauvegardes.contains("ecoleMailDe")) {
				String ecoleMailDe = (String)solrDocument.get("ecoleMailDe_stored_string");
				if(ecoleMailDe != null)
					oEcole.setEcoleMailDe(ecoleMailDe);
			}

			if(sauvegardes.contains("ecoleMailA")) {
				String ecoleMailA = (String)solrDocument.get("ecoleMailA_stored_string");
				if(ecoleMailA != null)
					oEcole.setEcoleMailA(ecoleMailA);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oEcole.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oEcole.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNomCourt")) {
				String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
				if(ecoleNomCourt != null)
					oEcole.setEcoleNomCourt(ecoleNomCourt);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oEcole.setEcoleNomComplet(ecoleNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.ecole.Ecole"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			Ecole o = new Ecole();
			o.requeteSiteEcole(requeteSite);
			o.initLoinEcole(requeteSite);
			o.indexerEcole();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerEcole();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerEcole(document);
	}

	public void indexerEcole(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerEcole(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerEcole() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerEcole(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerEcole(SolrInputDocument document) {
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCles != null) {
			for(java.lang.Long o : anneeCles) {
				document.addField("anneeCles_indexed_longs", o);
			}
			for(java.lang.Long o : anneeCles) {
				document.addField("anneeCles_stored_longs", o);
			}
		}
		if(saisonCles != null) {
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_indexed_longs", o);
			}
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_stored_longs", o);
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
		if(groupeAgeCles != null) {
			for(java.lang.Long o : groupeAgeCles) {
				document.addField("groupeAgeCles_indexed_longs", o);
			}
			for(java.lang.Long o : groupeAgeCles) {
				document.addField("groupeAgeCles_stored_longs", o);
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
		if(enfantCles != null) {
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_indexed_longs", o);
			}
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_stored_longs", o);
			}
		}
		if(scolaireTri != null) {
			document.addField("scolaireTri_indexed_int", scolaireTri);
			document.addField("scolaireTri_stored_int", scolaireTri);
		}
		if(ecoleTri != null) {
			document.addField("ecoleTri_indexed_int", ecoleTri);
			document.addField("ecoleTri_stored_int", ecoleTri);
		}
		if(ecoleNom != null) {
			document.addField("ecoleNom_indexed_string", ecoleNom);
			document.addField("ecoleNom_stored_string", ecoleNom);
		}
		if(ecoleNumeroTelephone != null) {
			document.addField("ecoleNumeroTelephone_indexed_string", ecoleNumeroTelephone);
			document.addField("ecoleNumeroTelephone_stored_string", ecoleNumeroTelephone);
		}
		if(ecoleForm != null) {
			document.addField("ecoleForm_indexed_string", ecoleForm);
			document.addField("ecoleForm_stored_string", ecoleForm);
		}
		if(ecoleNumero != null) {
			document.addField("ecoleNumero_indexed_int", ecoleNumero);
			document.addField("ecoleNumero_stored_int", ecoleNumero);
		}
		if(ecoleAdministrateurNom != null) {
			document.addField("ecoleAdministrateurNom_indexed_string", ecoleAdministrateurNom);
			document.addField("ecoleAdministrateurNom_stored_string", ecoleAdministrateurNom);
		}
		if(ecoleMail != null) {
			document.addField("ecoleMail_indexed_string", ecoleMail);
			document.addField("ecoleMail_stored_string", ecoleMail);
		}
		if(ecoleMailDe != null) {
			document.addField("ecoleMailDe_indexed_string", ecoleMailDe);
			document.addField("ecoleMailDe_stored_string", ecoleMailDe);
		}
		if(ecoleMailA != null) {
			document.addField("ecoleMailA_indexed_string", ecoleMailA);
			document.addField("ecoleMailA_stored_string", ecoleMailA);
		}
		if(ecoleEmplacement != null) {
			document.addField("ecoleEmplacement_indexed_string", ecoleEmplacement);
			document.addField("ecoleEmplacement_stored_string", ecoleEmplacement);
		}
		if(ecoleAddresse != null) {
			document.addField("ecoleAddresse_indexed_string", ecoleAddresse);
			document.addField("ecoleAddresse_stored_string", ecoleAddresse);
		}
		if(ecoleNomCourt != null) {
			document.addField("ecoleNomCourt_indexed_string", ecoleNomCourt);
			document.addField("ecoleNomCourt_stored_string", ecoleNomCourt);
		}
		if(ecoleNomComplet != null) {
			document.addField("ecoleNomComplet_indexed_string", ecoleNomComplet);
			document.addField("ecoleNomComplet_stored_string", ecoleNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerEcole() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinEcole(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeEcole(String entiteVar) {
		switch(entiteVar) {
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCles":
				return "anneeCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "groupeAgeCles":
				return "groupeAgeCles_indexed_longs";
			case "blocCles":
				return "blocCles_indexed_longs";
			case "enfantCles":
				return "enfantCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "ecoleForm":
				return "ecoleForm_indexed_string";
			case "ecoleNumero":
				return "ecoleNumero_indexed_int";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "ecoleMail":
				return "ecoleMail_indexed_string";
			case "ecoleMailDe":
				return "ecoleMailDe_indexed_string";
			case "ecoleMailA":
				return "ecoleMailA_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "ecoleNomCourt":
				return "ecoleNomCourt_indexed_string";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheEcole(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereEcole(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerEcole(solrDocument);
	}
	public void stockerEcole(SolrDocument solrDocument) {
		Ecole oEcole = (Ecole)this;

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oEcole.setEcoleCle(ecoleCle);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oEcole.anneeCles.addAll(anneeCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oEcole.saisonCles.addAll(saisonCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oEcole.sessionCles.addAll(sessionCles);

		List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
		if(groupeAgeCles != null)
			oEcole.groupeAgeCles.addAll(groupeAgeCles);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oEcole.blocCles.addAll(blocCles);

		List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
		if(enfantCles != null)
			oEcole.enfantCles.addAll(enfantCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oEcole.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oEcole.setEcoleTri(ecoleTri);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oEcole.setEcoleNom(ecoleNom);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oEcole.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
		if(ecoleForm != null)
			oEcole.setEcoleForm(ecoleForm);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oEcole.setEcoleNumero(ecoleNumero);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oEcole.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		String ecoleMail = (String)solrDocument.get("ecoleMail_stored_string");
		if(ecoleMail != null)
			oEcole.setEcoleMail(ecoleMail);

		String ecoleMailDe = (String)solrDocument.get("ecoleMailDe_stored_string");
		if(ecoleMailDe != null)
			oEcole.setEcoleMailDe(ecoleMailDe);

		String ecoleMailA = (String)solrDocument.get("ecoleMailA_stored_string");
		if(ecoleMailA != null)
			oEcole.setEcoleMailA(ecoleMailA);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oEcole.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oEcole.setEcoleAddresse(ecoleAddresse);

		String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
		if(ecoleNomCourt != null)
			oEcole.setEcoleNomCourt(ecoleNomCourt);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oEcole.setEcoleNomComplet(ecoleNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiEcole() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof Ecole) {
			Ecole original = (Ecole)o;
			if(!Objects.equals(anneeCles, original.getAnneeCles()))
				requeteApi.addVars("anneeCles");
			if(!Objects.equals(ecoleNom, original.getEcoleNom()))
				requeteApi.addVars("ecoleNom");
			if(!Objects.equals(ecoleNumeroTelephone, original.getEcoleNumeroTelephone()))
				requeteApi.addVars("ecoleNumeroTelephone");
			if(!Objects.equals(ecoleForm, original.getEcoleForm()))
				requeteApi.addVars("ecoleForm");
			if(!Objects.equals(ecoleNumero, original.getEcoleNumero()))
				requeteApi.addVars("ecoleNumero");
			if(!Objects.equals(ecoleAdministrateurNom, original.getEcoleAdministrateurNom()))
				requeteApi.addVars("ecoleAdministrateurNom");
			if(!Objects.equals(ecoleMail, original.getEcoleMail()))
				requeteApi.addVars("ecoleMail");
			if(!Objects.equals(ecoleMailDe, original.getEcoleMailDe()))
				requeteApi.addVars("ecoleMailDe");
			if(!Objects.equals(ecoleMailA, original.getEcoleMailA()))
				requeteApi.addVars("ecoleMailA");
			if(!Objects.equals(ecoleEmplacement, original.getEcoleEmplacement()))
				requeteApi.addVars("ecoleEmplacement");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), anneeCles, ecoleNom, ecoleNumeroTelephone, ecoleForm, ecoleNumero, ecoleAdministrateurNom, ecoleMail, ecoleMailDe, ecoleMailA, ecoleEmplacement, ecoleAddresse);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Ecole))
			return false;
		Ecole that = (Ecole)o;
		return super.equals(o)
				&& Objects.equals( anneeCles, that.anneeCles )
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( ecoleForm, that.ecoleForm )
				&& Objects.equals( ecoleNumero, that.ecoleNumero )
				&& Objects.equals( ecoleAdministrateurNom, that.ecoleAdministrateurNom )
				&& Objects.equals( ecoleMail, that.ecoleMail )
				&& Objects.equals( ecoleMailDe, that.ecoleMailDe )
				&& Objects.equals( ecoleMailA, that.ecoleMailA )
				&& Objects.equals( ecoleEmplacement, that.ecoleEmplacement )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("Ecole { ");
		sb.append( "anneeCles: " ).append(anneeCles);
		sb.append( ", ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", ecoleForm: \"" ).append(ecoleForm).append( "\"" );
		sb.append( ", ecoleNumero: " ).append(ecoleNumero);
		sb.append( ", ecoleAdministrateurNom: \"" ).append(ecoleAdministrateurNom).append( "\"" );
		sb.append( ", ecoleMail: \"" ).append(ecoleMail).append( "\"" );
		sb.append( ", ecoleMailDe: \"" ).append(ecoleMailDe).append( "\"" );
		sb.append( ", ecoleMailA: \"" ).append(ecoleMailA).append( "\"" );
		sb.append( ", ecoleEmplacement: \"" ).append(ecoleEmplacement).append( "\"" );
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
