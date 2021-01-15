package org.computate.scolaire.frFR.session;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.computate.scolaire.frFR.age.AgeScolaire;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe sessionCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class SessionScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SessionScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SessionScolaire_UnNom = "une session";
	public static final String SessionScolaire_Ce = "cette ";
	public static final String SessionScolaire_CeNom = "cette session";
	public static final String SessionScolaire_Un = "une ";
	public static final String SessionScolaire_LeNom = "la session";
	public static final String SessionScolaire_NomSingulier = "session";
	public static final String SessionScolaire_NomPluriel = "sessions";
	public static final String SessionScolaire_NomActuel = "session actuelle";
	public static final String SessionScolaire_Tous = "all ";
	public static final String SessionScolaire_TousNom = "toutes les sessions";
	public static final String SessionScolaire_RechercherTousNomPar = "rechercher sessions par ";
	public static final String SessionScolaire_RechercherTousNom = "rechercher sessions";
	public static final String SessionScolaire_Titre = "sessions";
	public static final String SessionScolaire_LesNom = "les sessions";
	public static final String SessionScolaire_AucunNomTrouve = "aucune session trouvée";
	public static final String SessionScolaire_NomVar = "session";
	public static final String SessionScolaire_DeNom = "de session";
	public static final String SessionScolaire_NomAdjectifSingulier = "session";
	public static final String SessionScolaire_NomAdjectifPluriel = "sessions";
	public static final String SessionScolaire_Couleur = "green";
	public static final String SessionScolaire_IconeGroupe = "duotone";
	public static final String SessionScolaire_IconeNom = "graduation-cap";

	////////////////
	// sessionCle //
	////////////////

	/**	 L'entité sessionCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long sessionCle;
	@JsonIgnore
	public Couverture<Long> sessionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("sessionCle").o(sessionCle);

	/**	<br/> L'entité sessionCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionCle(Couverture<Long> c);

	public Long getSessionCle() {
		return sessionCle;
	}

	public void setSessionCle(Long sessionCle) {
		this.sessionCle = sessionCle;
		this.sessionCleCouverture.dejaInitialise = true;
	}
	public void setSessionCle(String o) {
		this.sessionCle = SessionScolaire.staticSetSessionCle(requeteSite_, o);
		this.sessionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SessionScolaire sessionCleInit() {
		if(!sessionCleCouverture.dejaInitialise) {
			_sessionCle(sessionCleCouverture);
			if(sessionCle == null)
				setSessionCle(sessionCleCouverture.o);
		}
		sessionCleCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionCle(requeteSite_, SessionScolaire.staticSolrSessionCle(requeteSite_, SessionScolaire.staticSetSessionCle(requeteSite_, o)));
	}

	public Long solrSessionCle() {
		return SessionScolaire.staticSolrSessionCle(requeteSite_, sessionCle);
	}

	public String strSessionCle() {
		return sessionCle == null ? "" : sessionCle.toString();
	}

	public String jsonSessionCle() {
		return sessionCle == null ? "" : sessionCle.toString();
	}

	public String nomAffichageSessionCle() {
		return "clé";
	}

	public String htmTooltipSessionCle() {
		return null;
	}

	public String htmSessionCle() {
		return sessionCle == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCle());
	}

	/////////////////////
	// inscriptionCles //
	/////////////////////

	/**	 L'entité inscriptionCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> inscriptionCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> inscriptionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("inscriptionCles").o(inscriptionCles);

	/**	<br/> L'entité inscriptionCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
	 * <br/>
	 * @param inscriptionCles est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionCles(List<Long> o);

	public List<Long> getInscriptionCles() {
		return inscriptionCles;
	}

	public void setInscriptionCles(List<Long> inscriptionCles) {
		this.inscriptionCles = inscriptionCles;
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public void setInscriptionCles(String o) {
		Long l = SessionScolaire.staticSetInscriptionCles(requeteSite_, o);
		if(l != null)
			addInscriptionCles(l);
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SessionScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (SessionScolaire)this;
	}
	public SessionScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (SessionScolaire)this;
	}
	public void setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
	}
	public SessionScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (SessionScolaire)this;
	}
	protected SessionScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrInscriptionCles(requeteSite_, SessionScolaire.staticSolrInscriptionCles(requeteSite_, SessionScolaire.staticSetInscriptionCles(requeteSite_, o)));
	}

	public List<Long> solrInscriptionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : inscriptionCles) {
			l.add(SessionScolaire.staticSolrInscriptionCles(requeteSite_, o));
		}
		return l;
	}

	public String strInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String jsonInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String nomAffichageInscriptionCles() {
		return null;
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
	}

	/////////////
	// ageCles //
	/////////////

	/**	 L'entité ageCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ageCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> ageClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ageCles").o(ageCles);

	/**	<br/> L'entité ageCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
	 * <br/>
	 * @param ageCles est l'entité déjà construit. 
	 **/
	protected abstract void _ageCles(List<Long> o);

	public List<Long> getAgeCles() {
		return ageCles;
	}

	public void setAgeCles(List<Long> ageCles) {
		this.ageCles = ageCles;
		this.ageClesCouverture.dejaInitialise = true;
	}
	public void setAgeCles(String o) {
		Long l = SessionScolaire.staticSetAgeCles(requeteSite_, o);
		if(l != null)
			addAgeCles(l);
		this.ageClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SessionScolaire addAgeCles(Long...objets) {
		for(Long o : objets) {
			addAgeCles(o);
		}
		return (SessionScolaire)this;
	}
	public SessionScolaire addAgeCles(Long o) {
		if(o != null && !ageCles.contains(o))
			this.ageCles.add(o);
		return (SessionScolaire)this;
	}
	public void setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
	}
	public SessionScolaire addAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeCles(p);
		}
		return (SessionScolaire)this;
	}
	protected SessionScolaire ageClesInit() {
		if(!ageClesCouverture.dejaInitialise) {
			_ageCles(ageCles);
		}
		ageClesCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAgeCles(requeteSite_, SessionScolaire.staticSolrAgeCles(requeteSite_, SessionScolaire.staticSetAgeCles(requeteSite_, o)));
	}

	public List<Long> solrAgeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageCles) {
			l.add(SessionScolaire.staticSolrAgeCles(requeteSite_, o));
		}
		return l;
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

	public void inputAgeCles(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_ageCles_vider")
						.a("class", "ageCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_ageCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "âges")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("class", "valeur suggereAgeCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setAgeCles")
				.a("id", classeApiMethodeMethode, "_ageCles")
				.a("autocomplete", "off");
				a("oninput", "suggereSessionScolaireAgeCles($(this).val() ? rechercherAgeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'sessionCle:" + pk + "'}", "], $('#listSessionScolaireAgeCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSessionScolaire", pk, "AgeCles ").f().sx(htmAgeCles()).g("span");
			}
		}
	}

	public void htmAgeCles(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SessionScolaireAgeCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/age?fq=sessionCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue w3-hover-blue ").f();
								e("i").a("class", "fad fa-birthday-cake ").f().g("i");
								sx("âges");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette session");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAgeCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSessionScolaireAgeCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AgeScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AgeScolaire.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
												.a("id", classeApiMethodeMethode, "_ageCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postAgeScolaireVals({ sessionCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "ageCles')); });")
												.f().sx("ajouter un âge")
											.g("button");
										} g("div");
									}
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public void setScolaireTri(String o) {
		this.scolaireTri = SessionScolaire.staticSetScolaireTri(requeteSite_, o);
		this.scolaireTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrScolaireTri(requeteSite_, SessionScolaire.staticSolrScolaireTri(requeteSite_, SessionScolaire.staticSetScolaireTri(requeteSite_, o)));
	}

	public Integer solrScolaireTri() {
		return SessionScolaire.staticSolrScolaireTri(requeteSite_, scolaireTri);
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String jsonScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String nomAffichageScolaireTri() {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public void setEcoleTri(String o) {
		this.ecoleTri = SessionScolaire.staticSetEcoleTri(requeteSite_, o);
		this.ecoleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleTri(requeteSite_, SessionScolaire.staticSolrEcoleTri(requeteSite_, SessionScolaire.staticSetEcoleTri(requeteSite_, o)));
	}

	public Integer solrEcoleTri() {
		return SessionScolaire.staticSolrEcoleTri(requeteSite_, ecoleTri);
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String jsonEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String nomAffichageEcoleTri() {
		return null;
	}

	public String htmTooltipEcoleTri() {
		return null;
	}

	public String htmEcoleTri() {
		return ecoleTri == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleTri());
	}

	//////////////
	// anneeTri //
	//////////////

	/**	 L'entité anneeTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeTri;
	@JsonIgnore
	public Couverture<Integer> anneeTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeTri").o(anneeTri);

	/**	<br/> L'entité anneeTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeTri(Couverture<Integer> c);

	public Integer getAnneeTri() {
		return anneeTri;
	}

	public void setAnneeTri(Integer anneeTri) {
		this.anneeTri = anneeTri;
		this.anneeTriCouverture.dejaInitialise = true;
	}
	public void setAnneeTri(String o) {
		this.anneeTri = SessionScolaire.staticSetAnneeTri(requeteSite_, o);
		this.anneeTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAnneeTri(requeteSite_, SessionScolaire.staticSolrAnneeTri(requeteSite_, SessionScolaire.staticSetAnneeTri(requeteSite_, o)));
	}

	public Integer solrAnneeTri() {
		return SessionScolaire.staticSolrAnneeTri(requeteSite_, anneeTri);
	}

	public String strAnneeTri() {
		return anneeTri == null ? "" : anneeTri.toString();
	}

	public String jsonAnneeTri() {
		return anneeTri == null ? "" : anneeTri.toString();
	}

	public String nomAffichageAnneeTri() {
		return null;
	}

	public String htmTooltipAnneeTri() {
		return null;
	}

	public String htmAnneeTri() {
		return anneeTri == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeTri());
	}

	///////////////
	// saisonTri //
	///////////////

	/**	 L'entité saisonTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer saisonTri;
	@JsonIgnore
	public Couverture<Integer> saisonTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("saisonTri").o(saisonTri);

	/**	<br/> L'entité saisonTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonTri(Couverture<Integer> c);

	public Integer getSaisonTri() {
		return saisonTri;
	}

	public void setSaisonTri(Integer saisonTri) {
		this.saisonTri = saisonTri;
		this.saisonTriCouverture.dejaInitialise = true;
	}
	public void setSaisonTri(String o) {
		this.saisonTri = SessionScolaire.staticSetSaisonTri(requeteSite_, o);
		this.saisonTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire saisonTriInit() {
		if(!saisonTriCouverture.dejaInitialise) {
			_saisonTri(saisonTriCouverture);
			if(saisonTri == null)
				setSaisonTri(saisonTriCouverture.o);
		}
		saisonTriCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonTri(requeteSite_, SessionScolaire.staticSolrSaisonTri(requeteSite_, SessionScolaire.staticSetSaisonTri(requeteSite_, o)));
	}

	public Integer solrSaisonTri() {
		return SessionScolaire.staticSolrSaisonTri(requeteSite_, saisonTri);
	}

	public String strSaisonTri() {
		return saisonTri == null ? "" : saisonTri.toString();
	}

	public String jsonSaisonTri() {
		return saisonTri == null ? "" : saisonTri.toString();
	}

	public String nomAffichageSaisonTri() {
		return null;
	}

	public String htmTooltipSaisonTri() {
		return null;
	}

	public String htmSaisonTri() {
		return saisonTri == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonTri());
	}

	////////////////
	// sessionTri //
	////////////////

	/**	 L'entité sessionTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sessionTri;
	@JsonIgnore
	public Couverture<Integer> sessionTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sessionTri").o(sessionTri);

	/**	<br/> L'entité sessionTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionTri">Trouver l'entité sessionTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionTri(Couverture<Integer> c);

	public Integer getSessionTri() {
		return sessionTri;
	}

	public void setSessionTri(Integer sessionTri) {
		this.sessionTri = sessionTri;
		this.sessionTriCouverture.dejaInitialise = true;
	}
	public void setSessionTri(String o) {
		this.sessionTri = SessionScolaire.staticSetSessionTri(requeteSite_, o);
		this.sessionTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSessionTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire sessionTriInit() {
		if(!sessionTriCouverture.dejaInitialise) {
			_sessionTri(sessionTriCouverture);
			if(sessionTri == null)
				setSessionTri(sessionTriCouverture.o);
		}
		sessionTriCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrSessionTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrSessionTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionTri(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionTri(requeteSite_, SessionScolaire.staticSolrSessionTri(requeteSite_, SessionScolaire.staticSetSessionTri(requeteSite_, o)));
	}

	public Integer solrSessionTri() {
		return SessionScolaire.staticSolrSessionTri(requeteSite_, sessionTri);
	}

	public String strSessionTri() {
		return sessionTri == null ? "" : sessionTri.toString();
	}

	public String jsonSessionTri() {
		return sessionTri == null ? "" : sessionTri.toString();
	}

	public String nomAffichageSessionTri() {
		return null;
	}

	public String htmTooltipSessionTri() {
		return null;
	}

	public String htmSessionTri() {
		return sessionTri == null ? "" : StringEscapeUtils.escapeHtml4(strSessionTri());
	}

	///////////////
	// saisonCle //
	///////////////

	/**	 L'entité saisonCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long saisonCle;
	@JsonIgnore
	public Couverture<Long> saisonCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("saisonCle").o(saisonCle);

	/**	<br/> L'entité saisonCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonCle(Couverture<Long> c);

	public Long getSaisonCle() {
		return saisonCle;
	}

	public void setSaisonCle(Long saisonCle) {
		this.saisonCle = saisonCle;
		this.saisonCleCouverture.dejaInitialise = true;
	}
	public void setSaisonCle(String o) {
		this.saisonCle = SessionScolaire.staticSetSaisonCle(requeteSite_, o);
		this.saisonCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetSaisonCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SessionScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrSaisonCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSaisonCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonCle(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonCle(requeteSite_, SessionScolaire.staticSolrSaisonCle(requeteSite_, SessionScolaire.staticSetSaisonCle(requeteSite_, o)));
	}

	public Long solrSaisonCle() {
		return SessionScolaire.staticSolrSaisonCle(requeteSite_, saisonCle);
	}

	public String strSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String jsonSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String nomAffichageSaisonCle() {
		return "saison";
	}

	public String htmTooltipSaisonCle() {
		return null;
	}

	public String htmSaisonCle() {
		return saisonCle == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCle());
	}

	public void inputSaisonCle(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_saisonCle_vider")
						.a("class", "saisonCle_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_saisonCle_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "saison")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("class", "valeur suggereSaisonCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setSaisonCle")
				.a("id", classeApiMethodeMethode, "_saisonCle")
				.a("autocomplete", "off");
				a("oninput", "suggereSessionScolaireSaisonCle($(this).val() ? rechercherSaisonScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'sessionCles:" + pk + "'}", "], $('#listSessionScolaireSaisonCle_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSessionScolaire", pk, "SaisonCle ").f().sx(htmSaisonCle()).g("span");
			}
		}
	}

	public void htmSaisonCle(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SessionScolaireSaisonCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/saison?fq=sessionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-sun ").f().g("i");
								sx("saison");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une saison a cette session");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSaisonCle(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSessionScolaireSaisonCle_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), SaisonScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), SaisonScolaire.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
												.a("id", classeApiMethodeMethode, "_saisonCle_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postSaisonScolaireVals({ sessionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "saisonCle')); });")
												.f().sx("ajouter une saison")
											.g("button");
										} g("div");
									}
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// saisonRecherche //
	/////////////////////

	/**	 L'entité saisonRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<SaisonScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<SaisonScolaire> saisonRecherche = new ListeRecherche<SaisonScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<SaisonScolaire>> saisonRechercheCouverture = new Couverture<ListeRecherche<SaisonScolaire>>().p(this).c(ListeRecherche.class).var("saisonRecherche").o(saisonRecherche);

	/**	<br/> L'entité saisonRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<SaisonScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonRecherche">Trouver l'entité saisonRecherche dans Solr</a>
	 * <br/>
	 * @param saisonRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _saisonRecherche(ListeRecherche<SaisonScolaire> l);

	public ListeRecherche<SaisonScolaire> getSaisonRecherche() {
		return saisonRecherche;
	}

	public void setSaisonRecherche(ListeRecherche<SaisonScolaire> saisonRecherche) {
		this.saisonRecherche = saisonRecherche;
		this.saisonRechercheCouverture.dejaInitialise = true;
	}
	public static ListeRecherche<SaisonScolaire> staticSetSaisonRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SessionScolaire saisonRechercheInit() {
		if(!saisonRechercheCouverture.dejaInitialise) {
			_saisonRecherche(saisonRecherche);
		}
		saisonRecherche.initLoinPourClasse(requeteSite_);
		saisonRechercheCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	/////////////
	// saison_ //
	/////////////

	/**	 L'entité saison_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SaisonScolaire saison_;
	@JsonIgnore
	public Couverture<SaisonScolaire> saison_Couverture = new Couverture<SaisonScolaire>().p(this).c(SaisonScolaire.class).var("saison_").o(saison_);

	/**	<br/> L'entité saison_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saison_">Trouver l'entité saison_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saison_(Couverture<SaisonScolaire> c);

	public SaisonScolaire getSaison_() {
		return saison_;
	}

	public void setSaison_(SaisonScolaire saison_) {
		this.saison_ = saison_;
		this.saison_Couverture.dejaInitialise = true;
	}
	public static SaisonScolaire staticSetSaison_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SessionScolaire saison_Init() {
		if(!saison_Couverture.dejaInitialise) {
			_saison_(saison_Couverture);
			if(saison_ == null)
				setSaison_(saison_Couverture.o);
		}
		saison_Couverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public void setEcoleCle(String o) {
		this.ecoleCle = SessionScolaire.staticSetEcoleCle(requeteSite_, o);
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SessionScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleCle(requeteSite_, SessionScolaire.staticSolrEcoleCle(requeteSite_, SessionScolaire.staticSetEcoleCle(requeteSite_, o)));
	}

	public Long solrEcoleCle() {
		return SessionScolaire.staticSolrEcoleCle(requeteSite_, ecoleCle);
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String jsonEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return "école";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	//////////////
	// anneeCle //
	//////////////

	/**	 L'entité anneeCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long anneeCle;
	@JsonIgnore
	public Couverture<Long> anneeCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/> L'entité anneeCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeCle(Couverture<Long> c);

	public Long getAnneeCle() {
		return anneeCle;
	}

	public void setAnneeCle(Long anneeCle) {
		this.anneeCle = anneeCle;
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public void setAnneeCle(String o) {
		this.anneeCle = SessionScolaire.staticSetAnneeCle(requeteSite_, o);
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SessionScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Long staticSolrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAnneeCle(requeteSite_, SessionScolaire.staticSolrAnneeCle(requeteSite_, SessionScolaire.staticSetAnneeCle(requeteSite_, o)));
	}

	public Long solrAnneeCle() {
		return SessionScolaire.staticSolrAnneeCle(requeteSite_, anneeCle);
	}

	public String strAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String jsonAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String nomAffichageAnneeCle() {
		return "année";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Couverture<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}
	public void setEcoleNom(String o) {
		this.ecoleNom = SessionScolaire.staticSetEcoleNom(requeteSite_, o);
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleNom(requeteSite_, SessionScolaire.staticSolrEcoleNom(requeteSite_, SessionScolaire.staticSetEcoleNom(requeteSite_, o)));
	}

	public String solrEcoleNom() {
		return SessionScolaire.staticSolrEcoleNom(requeteSite_, ecoleNom);
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String jsonEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNom() {
		return null;
	}

	public String htmEcoleNom() {
		return ecoleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNom());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomComplet(Couverture<String> c);

	public String getEcoleNomComplet() {
		return ecoleNomComplet;
	}
	public void setEcoleNomComplet(String o) {
		this.ecoleNomComplet = SessionScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleNomComplet(requeteSite_, SessionScolaire.staticSolrEcoleNomComplet(requeteSite_, SessionScolaire.staticSetEcoleNomComplet(requeteSite_, o)));
	}

	public String solrEcoleNomComplet() {
		return SessionScolaire.staticSolrEcoleNomComplet(requeteSite_, ecoleNomComplet);
	}

	public String strEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String jsonEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String nomAffichageEcoleNomComplet() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNomComplet() {
		return null;
	}

	public String htmEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomComplet());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleEmplacement(Couverture<String> c);

	public String getEcoleEmplacement() {
		return ecoleEmplacement;
	}
	public void setEcoleEmplacement(String o) {
		this.ecoleEmplacement = SessionScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleEmplacement(requeteSite_, SessionScolaire.staticSolrEcoleEmplacement(requeteSite_, SessionScolaire.staticSetEcoleEmplacement(requeteSite_, o)));
	}

	public String solrEcoleEmplacement() {
		return SessionScolaire.staticSolrEcoleEmplacement(requeteSite_, ecoleEmplacement);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}
	public void setEcoleAddresse(String o) {
		this.ecoleAddresse = SessionScolaire.staticSetEcoleAddresse(requeteSite_, o);
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleAddresse(requeteSite_, SessionScolaire.staticSolrEcoleAddresse(requeteSite_, SessionScolaire.staticSetEcoleAddresse(requeteSite_, o)));
	}

	public String solrEcoleAddresse() {
		return SessionScolaire.staticSolrEcoleAddresse(requeteSite_, ecoleAddresse);
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
		SessionScolaire s = (SessionScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "addresse")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("id", classeApiMethodeMethode, "_ecoleAddresse");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleAddresse classSessionScolaire inputSessionScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "setEcoleAddresse");
				} else {
					a("class", "valeurEcoleAddresse w3-input w3-border classSessionScolaire inputSessionScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "ecoleAddresse");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ");
				}
				a("value", strEcoleAddresse())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSessionScolaire", pk, "EcoleAddresse ").f().sx(htmEcoleAddresse()).g("span");
			}
		}
	}

	public void htmEcoleAddresse(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SessionScolaireEcoleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); $('#", classeApiMethodeMethode, "_ecoleAddresse').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=pk]').val() }], 'setEcoleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}
	public void setEcoleNumeroTelephone(String o) {
		this.ecoleNumeroTelephone = SessionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, SessionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, SessionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o)));
	}

	public String solrEcoleNumeroTelephone() {
		return SessionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, ecoleNumeroTelephone);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleForm">Trouver l'entité ecoleForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleForm(Couverture<String> c);

	public String getEcoleForm() {
		return ecoleForm;
	}
	public void setEcoleForm(String o) {
		this.ecoleForm = SessionScolaire.staticSetEcoleForm(requeteSite_, o);
		this.ecoleFormCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleFormInit() {
		if(!ecoleFormCouverture.dejaInitialise) {
			_ecoleForm(ecoleFormCouverture);
			if(ecoleForm == null)
				setEcoleForm(ecoleFormCouverture.o);
		}
		ecoleFormCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleForm(requeteSite_, SessionScolaire.staticSolrEcoleForm(requeteSite_, SessionScolaire.staticSetEcoleForm(requeteSite_, o)));
	}

	public String solrEcoleForm() {
		return SessionScolaire.staticSolrEcoleForm(requeteSite_, ecoleForm);
	}

	public String strEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String jsonEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String nomAffichageEcoleForm() {
		return null;
	}

	public String htmTooltipEcoleForm() {
		return null;
	}

	public String htmEcoleForm() {
		return ecoleForm == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleForm());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
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
	public void setEcoleNumero(String o) {
		this.ecoleNumero = SessionScolaire.staticSetEcoleNumero(requeteSite_, o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleNumero(requeteSite_, SessionScolaire.staticSolrEcoleNumero(requeteSite_, SessionScolaire.staticSetEcoleNumero(requeteSite_, o)));
	}

	public Integer solrEcoleNumero() {
		return SessionScolaire.staticSolrEcoleNumero(requeteSite_, ecoleNumero);
	}

	public String strEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String jsonEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String nomAffichageEcoleNumero() {
		return null;
	}

	public String htmTooltipEcoleNumero() {
		return null;
	}

	public String htmEcoleNumero() {
		return ecoleNumero == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumero());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAdministrateurNom(Couverture<String> c);

	public String getEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}
	public void setEcoleAdministrateurNom(String o) {
		this.ecoleAdministrateurNom = SessionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		this.ecoleAdministrateurNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, SessionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, SessionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o)));
	}

	public String solrEcoleAdministrateurNom() {
		return SessionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, ecoleAdministrateurNom);
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

	////////////////
	// anneeDebut //
	////////////////

	/**	 L'entité anneeDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeDebut;
	@JsonIgnore
	public Couverture<Integer> anneeDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeDebut").o(anneeDebut);

	/**	<br/> L'entité anneeDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeDebut(Couverture<Integer> c);

	public Integer getAnneeDebut() {
		return anneeDebut;
	}

	public void setAnneeDebut(Integer anneeDebut) {
		this.anneeDebut = anneeDebut;
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public void setAnneeDebut(String o) {
		this.anneeDebut = SessionScolaire.staticSetAnneeDebut(requeteSite_, o);
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAnneeDebut(requeteSite_, SessionScolaire.staticSolrAnneeDebut(requeteSite_, SessionScolaire.staticSetAnneeDebut(requeteSite_, o)));
	}

	public Integer solrAnneeDebut() {
		return SessionScolaire.staticSolrAnneeDebut(requeteSite_, anneeDebut);
	}

	public String strAnneeDebut() {
		return anneeDebut == null ? "" : anneeDebut.toString();
	}

	public String jsonAnneeDebut() {
		return anneeDebut == null ? "" : anneeDebut.toString();
	}

	public String nomAffichageAnneeDebut() {
		return "début de l'année";
	}

	public String htmTooltipAnneeDebut() {
		return null;
	}

	public String htmAnneeDebut() {
		return anneeDebut == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeDebut());
	}

	//////////////
	// anneeFin //
	//////////////

	/**	 L'entité anneeFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeFin;
	@JsonIgnore
	public Couverture<Integer> anneeFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeFin").o(anneeFin);

	/**	<br/> L'entité anneeFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeFin(Couverture<Integer> c);

	public Integer getAnneeFin() {
		return anneeFin;
	}

	public void setAnneeFin(Integer anneeFin) {
		this.anneeFin = anneeFin;
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public void setAnneeFin(String o) {
		this.anneeFin = SessionScolaire.staticSetAnneeFin(requeteSite_, o);
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SessionScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Integer staticSolrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAnneeFin(requeteSite_, SessionScolaire.staticSolrAnneeFin(requeteSite_, SessionScolaire.staticSetAnneeFin(requeteSite_, o)));
	}

	public Integer solrAnneeFin() {
		return SessionScolaire.staticSolrAnneeFin(requeteSite_, anneeFin);
	}

	public String strAnneeFin() {
		return anneeFin == null ? "" : anneeFin.toString();
	}

	public String jsonAnneeFin() {
		return anneeFin == null ? "" : anneeFin.toString();
	}

	public String nomAffichageAnneeFin() {
		return "le fin de l'année";
	}

	public String htmTooltipAnneeFin() {
		return null;
	}

	public String htmAnneeFin() {
		return anneeFin == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeFin());
	}

	/////////////////////
	// saisonDateDebut //
	/////////////////////

	/**	 L'entité saisonDateDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate saisonDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> saisonDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonDateDebut").o(saisonDateDebut);

	/**	<br/> L'entité saisonDateDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonDateDebut(Couverture<LocalDate> c);

	public LocalDate getSaisonDateDebut() {
		return saisonDateDebut;
	}

	public void setSaisonDateDebut(LocalDate saisonDateDebut) {
		this.saisonDateDebut = saisonDateDebut;
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public void setSaisonDateDebut(Instant o) {
		this.saisonDateDebut = o == null ? null : LocalDate.from(o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSaisonDateDebut(String o) {
		this.saisonDateDebut = SessionScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	protected SessionScolaire saisonDateDebutInit() {
		if(!saisonDateDebutCouverture.dejaInitialise) {
			_saisonDateDebut(saisonDateDebutCouverture);
			if(saisonDateDebut == null)
				setSaisonDateDebut(saisonDateDebutCouverture.o);
		}
		saisonDateDebutCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Date staticSolrSaisonDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSaisonDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonDateDebut(requeteSite_, SessionScolaire.staticSolrSaisonDateDebut(requeteSite_, SessionScolaire.staticSetSaisonDateDebut(requeteSite_, o)));
	}

	public Date solrSaisonDateDebut() {
		return SessionScolaire.staticSolrSaisonDateDebut(requeteSite_, saisonDateDebut);
	}

	public String strSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSaisonDateDebut() {
		return "début de l'année";
	}

	public String htmTooltipSaisonDateDebut() {
		return null;
	}

	public String htmSaisonDateDebut() {
		return saisonDateDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonDateDebut());
	}

	///////////////
	// saisonEte //
	///////////////

	/**	 L'entité saisonEte
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonEte;
	@JsonIgnore
	public Couverture<Boolean> saisonEteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonEte").o(saisonEte);

	/**	<br/> L'entité saisonEte
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonEte(Couverture<Boolean> c);

	public Boolean getSaisonEte() {
		return saisonEte;
	}

	public void setSaisonEte(Boolean saisonEte) {
		this.saisonEte = saisonEte;
		this.saisonEteCouverture.dejaInitialise = true;
	}
	public void setSaisonEte(String o) {
		this.saisonEte = SessionScolaire.staticSetSaisonEte(requeteSite_, o);
		this.saisonEteCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetSaisonEte(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SessionScolaire saisonEteInit() {
		if(!saisonEteCouverture.dejaInitialise) {
			_saisonEte(saisonEteCouverture);
			if(saisonEte == null)
				setSaisonEte(saisonEteCouverture.o);
		}
		saisonEteCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Boolean staticSolrSaisonEte(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSaisonEte(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonEte(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonEte(requeteSite_, SessionScolaire.staticSolrSaisonEte(requeteSite_, SessionScolaire.staticSetSaisonEte(requeteSite_, o)));
	}

	public Boolean solrSaisonEte() {
		return SessionScolaire.staticSolrSaisonEte(requeteSite_, saisonEte);
	}

	public String strSaisonEte() {
		return saisonEte == null ? "" : saisonEte.toString();
	}

	public String jsonSaisonEte() {
		return saisonEte == null ? "" : saisonEte.toString();
	}

	public String nomAffichageSaisonEte() {
		return "été";
	}

	public String htmTooltipSaisonEte() {
		return null;
	}

	public String htmSaisonEte() {
		return saisonEte == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonEte());
	}

	/////////////////
	// saisonHiver //
	/////////////////

	/**	 L'entité saisonHiver
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonHiver;
	@JsonIgnore
	public Couverture<Boolean> saisonHiverCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonHiver").o(saisonHiver);

	/**	<br/> L'entité saisonHiver
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonHiver(Couverture<Boolean> c);

	public Boolean getSaisonHiver() {
		return saisonHiver;
	}

	public void setSaisonHiver(Boolean saisonHiver) {
		this.saisonHiver = saisonHiver;
		this.saisonHiverCouverture.dejaInitialise = true;
	}
	public void setSaisonHiver(String o) {
		this.saisonHiver = SessionScolaire.staticSetSaisonHiver(requeteSite_, o);
		this.saisonHiverCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetSaisonHiver(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SessionScolaire saisonHiverInit() {
		if(!saisonHiverCouverture.dejaInitialise) {
			_saisonHiver(saisonHiverCouverture);
			if(saisonHiver == null)
				setSaisonHiver(saisonHiverCouverture.o);
		}
		saisonHiverCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Boolean staticSolrSaisonHiver(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSaisonHiver(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonHiver(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonHiver(requeteSite_, SessionScolaire.staticSolrSaisonHiver(requeteSite_, SessionScolaire.staticSetSaisonHiver(requeteSite_, o)));
	}

	public Boolean solrSaisonHiver() {
		return SessionScolaire.staticSolrSaisonHiver(requeteSite_, saisonHiver);
	}

	public String strSaisonHiver() {
		return saisonHiver == null ? "" : saisonHiver.toString();
	}

	public String jsonSaisonHiver() {
		return saisonHiver == null ? "" : saisonHiver.toString();
	}

	public String nomAffichageSaisonHiver() {
		return "hiver";
	}

	public String htmTooltipSaisonHiver() {
		return null;
	}

	public String htmSaisonHiver() {
		return saisonHiver == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonHiver());
	}

	///////////////////////////
	// anneeFraisInscription //
	///////////////////////////

	/**	 L'entité anneeFraisInscription
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal anneeFraisInscription;
	@JsonIgnore
	public Couverture<BigDecimal> anneeFraisInscriptionCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("anneeFraisInscription").o(anneeFraisInscription);

	/**	<br/> L'entité anneeFraisInscription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeFraisInscription(Couverture<BigDecimal> c);

	public BigDecimal getAnneeFraisInscription() {
		return anneeFraisInscription;
	}

	public void setAnneeFraisInscription(BigDecimal anneeFraisInscription) {
		this.anneeFraisInscription = anneeFraisInscription;
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public void setAnneeFraisInscription(String o) {
		this.anneeFraisInscription = SessionScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public void setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	protected SessionScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Double staticSolrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, SessionScolaire.staticSolrAnneeFraisInscription(requeteSite_, SessionScolaire.staticSetAnneeFraisInscription(requeteSite_, o)));
	}

	public Double solrAnneeFraisInscription() {
		return SessionScolaire.staticSolrAnneeFraisInscription(requeteSite_, anneeFraisInscription);
	}

	public String strAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.toString();
	}

	public String nomAffichageAnneeFraisInscription() {
		return "le fin de l'année";
	}

	public String htmTooltipAnneeFraisInscription() {
		return null;
	}

	public String htmAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeFraisInscription());
	}

	////////////////////
	// saisonNomCourt //
	////////////////////

	/**	 L'entité saisonNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomCourt;
	@JsonIgnore
	public Couverture<String> saisonNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomCourt").o(saisonNomCourt);

	/**	<br/> L'entité saisonNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomCourt">Trouver l'entité saisonNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomCourt(Couverture<String> c);

	public String getSaisonNomCourt() {
		return saisonNomCourt;
	}
	public void setSaisonNomCourt(String o) {
		this.saisonNomCourt = SessionScolaire.staticSetSaisonNomCourt(requeteSite_, o);
		this.saisonNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire saisonNomCourtInit() {
		if(!saisonNomCourtCouverture.dejaInitialise) {
			_saisonNomCourt(saisonNomCourtCouverture);
			if(saisonNomCourt == null)
				setSaisonNomCourt(saisonNomCourtCouverture.o);
		}
		saisonNomCourtCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonNomCourt(requeteSite_, SessionScolaire.staticSolrSaisonNomCourt(requeteSite_, SessionScolaire.staticSetSaisonNomCourt(requeteSite_, o)));
	}

	public String solrSaisonNomCourt() {
		return SessionScolaire.staticSolrSaisonNomCourt(requeteSite_, saisonNomCourt);
	}

	public String strSaisonNomCourt() {
		return saisonNomCourt == null ? "" : saisonNomCourt;
	}

	public String jsonSaisonNomCourt() {
		return saisonNomCourt == null ? "" : saisonNomCourt;
	}

	public String nomAffichageSaisonNomCourt() {
		return null;
	}

	public String htmTooltipSaisonNomCourt() {
		return null;
	}

	public String htmSaisonNomCourt() {
		return saisonNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonNomCourt());
	}

	//////////////////////
	// saisonNomComplet //
	//////////////////////

	/**	 L'entité saisonNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomComplet;
	@JsonIgnore
	public Couverture<String> saisonNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomComplet").o(saisonNomComplet);

	/**	<br/> L'entité saisonNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomComplet">Trouver l'entité saisonNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomComplet(Couverture<String> c);

	public String getSaisonNomComplet() {
		return saisonNomComplet;
	}
	public void setSaisonNomComplet(String o) {
		this.saisonNomComplet = SessionScolaire.staticSetSaisonNomComplet(requeteSite_, o);
		this.saisonNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire saisonNomCompletInit() {
		if(!saisonNomCompletCouverture.dejaInitialise) {
			_saisonNomComplet(saisonNomCompletCouverture);
			if(saisonNomComplet == null)
				setSaisonNomComplet(saisonNomCompletCouverture.o);
		}
		saisonNomCompletCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSaisonNomComplet(requeteSite_, SessionScolaire.staticSolrSaisonNomComplet(requeteSite_, SessionScolaire.staticSetSaisonNomComplet(requeteSite_, o)));
	}

	public String solrSaisonNomComplet() {
		return SessionScolaire.staticSolrSaisonNomComplet(requeteSite_, saisonNomComplet);
	}

	public String strSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String jsonSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String nomAffichageSaisonNomComplet() {
		return null;
	}

	public String htmTooltipSaisonNomComplet() {
		return null;
	}

	public String htmSaisonNomComplet() {
		return saisonNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonNomComplet());
	}

	//////////////////////
	// sessionDateDebut //
	//////////////////////

	/**	 L'entité sessionDateDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateDebut").o(sessionDateDebut);

	/**	<br/> L'entité sessionDateDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateDebut">Trouver l'entité sessionDateDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionDateDebut(Couverture<LocalDate> c);

	public LocalDate getSessionDateDebut() {
		return sessionDateDebut;
	}

	public void setSessionDateDebut(LocalDate sessionDateDebut) {
		this.sessionDateDebut = sessionDateDebut;
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	public void setSessionDateDebut(Instant o) {
		this.sessionDateDebut = o == null ? null : LocalDate.from(o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionDateDebut(String o) {
		this.sessionDateDebut = SessionScolaire.staticSetSessionDateDebut(requeteSite_, o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	protected SessionScolaire sessionDateDebutInit() {
		if(!sessionDateDebutCouverture.dejaInitialise) {
			_sessionDateDebut(sessionDateDebutCouverture);
			if(sessionDateDebut == null)
				setSessionDateDebut(sessionDateDebutCouverture.o);
		}
		sessionDateDebutCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Date staticSolrSessionDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionDateDebut(requeteSite_, SessionScolaire.staticSolrSessionDateDebut(requeteSite_, SessionScolaire.staticSetSessionDateDebut(requeteSite_, o)));
	}

	public Date solrSessionDateDebut() {
		return SessionScolaire.staticSolrSessionDateDebut(requeteSite_, sessionDateDebut);
	}

	public String strSessionDateDebut() {
		return sessionDateDebut == null ? "" : sessionDateDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSessionDateDebut() {
		return sessionDateDebut == null ? "" : sessionDateDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionDateDebut() {
		return "début de la session";
	}

	public String htmTooltipSessionDateDebut() {
		return null;
	}

	public String htmSessionDateDebut() {
		return sessionDateDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSessionDateDebut());
	}

	public void inputSessionDateDebut(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSessionDateDebut classSessionScolaire inputSessionScolaire", pk, "SessionDateDebut w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_sessionDateDebut")
					.a("title", "L'année scolaire de la saison scolaire.  (DD-MM-YYYY)")
					.a("value", sessionDateDebut == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(sessionDateDebut));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionDateDebut', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_sessionDateDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_sessionDateDebut')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSessionScolaire", pk, "SessionDateDebut ").f().sx(htmSessionDateDebut()).g("span");
			}
		}
	}

	public void htmSessionDateDebut(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SessionScolaireSessionDateDebut").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_sessionDateDebut").a("class", "").f().sx("début de la session").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionDateDebut(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_sessionDateDebut')); $('#", classeApiMethodeMethode, "_sessionDateDebut').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=pk]').val() }], 'setSessionDateDebut', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_sessionDateDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_sessionDateDebut')); }); ")
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

	////////////////////
	// sessionDateFin //
	////////////////////

	/**	 L'entité sessionDateFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateFin;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateFin").o(sessionDateFin);

	/**	<br/> L'entité sessionDateFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateFin">Trouver l'entité sessionDateFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionDateFin(Couverture<LocalDate> c);

	public LocalDate getSessionDateFin() {
		return sessionDateFin;
	}

	public void setSessionDateFin(LocalDate sessionDateFin) {
		this.sessionDateFin = sessionDateFin;
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	public void setSessionDateFin(Instant o) {
		this.sessionDateFin = o == null ? null : LocalDate.from(o);
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionDateFin(String o) {
		this.sessionDateFin = SessionScolaire.staticSetSessionDateFin(requeteSite_, o);
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	protected SessionScolaire sessionDateFinInit() {
		if(!sessionDateFinCouverture.dejaInitialise) {
			_sessionDateFin(sessionDateFinCouverture);
			if(sessionDateFin == null)
				setSessionDateFin(sessionDateFinCouverture.o);
		}
		sessionDateFinCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static Date staticSolrSessionDateFin(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateFin(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionDateFin(requeteSite_, SessionScolaire.staticSolrSessionDateFin(requeteSite_, SessionScolaire.staticSetSessionDateFin(requeteSite_, o)));
	}

	public Date solrSessionDateFin() {
		return SessionScolaire.staticSolrSessionDateFin(requeteSite_, sessionDateFin);
	}

	public String strSessionDateFin() {
		return sessionDateFin == null ? "" : sessionDateFin.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSessionDateFin() {
		return sessionDateFin == null ? "" : sessionDateFin.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionDateFin() {
		return "fin de la session";
	}

	public String htmTooltipSessionDateFin() {
		return null;
	}

	public String htmSessionDateFin() {
		return sessionDateFin == null ? "" : StringEscapeUtils.escapeHtml4(strSessionDateFin());
	}

	public void inputSessionDateFin(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSessionDateFin classSessionScolaire inputSessionScolaire", pk, "SessionDateFin w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_sessionDateFin")
					.a("title", "L'année scolaire de la saison scolaire.  (DD-MM-YYYY)")
					.a("value", sessionDateFin == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(sessionDateFin));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionDateFin', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_sessionDateFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_sessionDateFin')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSessionScolaire", pk, "SessionDateFin ").f().sx(htmSessionDateFin()).g("span");
			}
		}
	}

	public void htmSessionDateFin(String classeApiMethodeMethode) {
		SessionScolaire s = (SessionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SessionScolaireSessionDateFin").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_sessionDateFin").a("class", "").f().sx("fin de la session").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionDateFin(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_sessionDateFin')); $('#", classeApiMethodeMethode, "_sessionDateFin').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SessionScolaireForm :input[name=pk]').val() }], 'setSessionDateFin', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_sessionDateFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_sessionDateFin')); }); ")
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

	/////////////////////
	// sessionNomCourt //
	/////////////////////

	/**	 L'entité sessionNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sessionNomCourt;
	@JsonIgnore
	public Couverture<String> sessionNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("sessionNomCourt").o(sessionNomCourt);

	/**	<br/> L'entité sessionNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionNomCourt">Trouver l'entité sessionNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionNomCourt(Couverture<String> c);

	public String getSessionNomCourt() {
		return sessionNomCourt;
	}
	public void setSessionNomCourt(String o) {
		this.sessionNomCourt = SessionScolaire.staticSetSessionNomCourt(requeteSite_, o);
		this.sessionNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetSessionNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire sessionNomCourtInit() {
		if(!sessionNomCourtCouverture.dejaInitialise) {
			_sessionNomCourt(sessionNomCourtCouverture);
			if(sessionNomCourt == null)
				setSessionNomCourt(sessionNomCourtCouverture.o);
		}
		sessionNomCourtCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrSessionNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSessionNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionNomCourt(requeteSite_, SessionScolaire.staticSolrSessionNomCourt(requeteSite_, SessionScolaire.staticSetSessionNomCourt(requeteSite_, o)));
	}

	public String solrSessionNomCourt() {
		return SessionScolaire.staticSolrSessionNomCourt(requeteSite_, sessionNomCourt);
	}

	public String strSessionNomCourt() {
		return sessionNomCourt == null ? "" : sessionNomCourt;
	}

	public String jsonSessionNomCourt() {
		return sessionNomCourt == null ? "" : sessionNomCourt;
	}

	public String nomAffichageSessionNomCourt() {
		return null;
	}

	public String htmTooltipSessionNomCourt() {
		return null;
	}

	public String htmSessionNomCourt() {
		return sessionNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strSessionNomCourt());
	}

	///////////////////////
	// sessionNomComplet //
	///////////////////////

	/**	 L'entité sessionNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sessionNomComplet;
	@JsonIgnore
	public Couverture<String> sessionNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("sessionNomComplet").o(sessionNomComplet);

	/**	<br/> L'entité sessionNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.session.SessionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionNomComplet">Trouver l'entité sessionNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionNomComplet(Couverture<String> c);

	public String getSessionNomComplet() {
		return sessionNomComplet;
	}
	public void setSessionNomComplet(String o) {
		this.sessionNomComplet = SessionScolaire.staticSetSessionNomComplet(requeteSite_, o);
		this.sessionNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetSessionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SessionScolaire sessionNomCompletInit() {
		if(!sessionNomCompletCouverture.dejaInitialise) {
			_sessionNomComplet(sessionNomCompletCouverture);
			if(sessionNomComplet == null)
				setSessionNomComplet(sessionNomCompletCouverture.o);
		}
		sessionNomCompletCouverture.dejaInitialise(true);
		return (SessionScolaire)this;
	}

	public static String staticSolrSessionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSessionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return SessionScolaire.staticSolrStrSessionNomComplet(requeteSite_, SessionScolaire.staticSolrSessionNomComplet(requeteSite_, SessionScolaire.staticSetSessionNomComplet(requeteSite_, o)));
	}

	public String solrSessionNomComplet() {
		return SessionScolaire.staticSolrSessionNomComplet(requeteSite_, sessionNomComplet);
	}

	public String strSessionNomComplet() {
		return sessionNomComplet == null ? "" : sessionNomComplet;
	}

	public String jsonSessionNomComplet() {
		return sessionNomComplet == null ? "" : sessionNomComplet;
	}

	public String nomAffichageSessionNomComplet() {
		return "nom";
	}

	public String htmTooltipSessionNomComplet() {
		return null;
	}

	public String htmSessionNomComplet() {
		return sessionNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strSessionNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSessionScolaire = false;

	public SessionScolaire initLoinSessionScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSessionScolaire) {
			dejaInitialiseSessionScolaire = true;
			initLoinSessionScolaire();
		}
		return (SessionScolaire)this;
	}

	public void initLoinSessionScolaire() {
		initSessionScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initSessionScolaire() {
		sessionCleInit();
		inscriptionClesInit();
		ageClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		sessionTriInit();
		saisonCleInit();
		saisonRechercheInit();
		saison_Init();
		ecoleCleInit();
		anneeCleInit();
		ecoleNomInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		ecoleFormInit();
		ecoleNumeroInit();
		ecoleAdministrateurNomInit();
		anneeDebutInit();
		anneeFinInit();
		saisonDateDebutInit();
		saisonEteInit();
		saisonHiverInit();
		anneeFraisInscriptionInit();
		saisonNomCourtInit();
		saisonNomCompletInit();
		sessionDateDebutInit();
		sessionDateFinInit();
		sessionNomCourtInit();
		sessionNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSessionScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSessionScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(saisonRecherche != null)
			saisonRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSessionScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSessionScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSessionScolaire(String var) {
		SessionScolaire oSessionScolaire = (SessionScolaire)this;
		switch(var) {
			case "sessionCle":
				return oSessionScolaire.sessionCle;
			case "inscriptionCles":
				return oSessionScolaire.inscriptionCles;
			case "ageCles":
				return oSessionScolaire.ageCles;
			case "scolaireTri":
				return oSessionScolaire.scolaireTri;
			case "ecoleTri":
				return oSessionScolaire.ecoleTri;
			case "anneeTri":
				return oSessionScolaire.anneeTri;
			case "saisonTri":
				return oSessionScolaire.saisonTri;
			case "sessionTri":
				return oSessionScolaire.sessionTri;
			case "saisonCle":
				return oSessionScolaire.saisonCle;
			case "saisonRecherche":
				return oSessionScolaire.saisonRecherche;
			case "saison_":
				return oSessionScolaire.saison_;
			case "ecoleCle":
				return oSessionScolaire.ecoleCle;
			case "anneeCle":
				return oSessionScolaire.anneeCle;
			case "ecoleNom":
				return oSessionScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oSessionScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oSessionScolaire.ecoleEmplacement;
			case "ecoleAddresse":
				return oSessionScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oSessionScolaire.ecoleNumeroTelephone;
			case "ecoleForm":
				return oSessionScolaire.ecoleForm;
			case "ecoleNumero":
				return oSessionScolaire.ecoleNumero;
			case "ecoleAdministrateurNom":
				return oSessionScolaire.ecoleAdministrateurNom;
			case "anneeDebut":
				return oSessionScolaire.anneeDebut;
			case "anneeFin":
				return oSessionScolaire.anneeFin;
			case "saisonDateDebut":
				return oSessionScolaire.saisonDateDebut;
			case "saisonEte":
				return oSessionScolaire.saisonEte;
			case "saisonHiver":
				return oSessionScolaire.saisonHiver;
			case "anneeFraisInscription":
				return oSessionScolaire.anneeFraisInscription;
			case "saisonNomCourt":
				return oSessionScolaire.saisonNomCourt;
			case "saisonNomComplet":
				return oSessionScolaire.saisonNomComplet;
			case "sessionDateDebut":
				return oSessionScolaire.sessionDateDebut;
			case "sessionDateFin":
				return oSessionScolaire.sessionDateFin;
			case "sessionNomCourt":
				return oSessionScolaire.sessionNomCourt;
			case "sessionNomComplet":
				return oSessionScolaire.sessionNomComplet;
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
				o = attribuerSessionScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSessionScolaire(String var, Object val) {
		SessionScolaire oSessionScolaire = (SessionScolaire)this;
		switch(var) {
			case "ageCles":
				oSessionScolaire.addAgeCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "saisonCle":
				if(oSessionScolaire.getSaisonCle() == null)
					oSessionScolaire.setSaisonCle((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			default:
				return super.attribuerCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetSessionScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetSessionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "sessionCle":
			return SessionScolaire.staticSetSessionCle(requeteSite_, o);
		case "inscriptionCles":
			return SessionScolaire.staticSetInscriptionCles(requeteSite_, o);
		case "ageCles":
			return SessionScolaire.staticSetAgeCles(requeteSite_, o);
		case "scolaireTri":
			return SessionScolaire.staticSetScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return SessionScolaire.staticSetEcoleTri(requeteSite_, o);
		case "anneeTri":
			return SessionScolaire.staticSetAnneeTri(requeteSite_, o);
		case "saisonTri":
			return SessionScolaire.staticSetSaisonTri(requeteSite_, o);
		case "sessionTri":
			return SessionScolaire.staticSetSessionTri(requeteSite_, o);
		case "saisonCle":
			return SessionScolaire.staticSetSaisonCle(requeteSite_, o);
		case "ecoleCle":
			return SessionScolaire.staticSetEcoleCle(requeteSite_, o);
		case "anneeCle":
			return SessionScolaire.staticSetAnneeCle(requeteSite_, o);
		case "ecoleNom":
			return SessionScolaire.staticSetEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return SessionScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return SessionScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return SessionScolaire.staticSetEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return SessionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return SessionScolaire.staticSetEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return SessionScolaire.staticSetEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return SessionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return SessionScolaire.staticSetAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return SessionScolaire.staticSetAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return SessionScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		case "saisonEte":
			return SessionScolaire.staticSetSaisonEte(requeteSite_, o);
		case "saisonHiver":
			return SessionScolaire.staticSetSaisonHiver(requeteSite_, o);
		case "anneeFraisInscription":
			return SessionScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		case "saisonNomCourt":
			return SessionScolaire.staticSetSaisonNomCourt(requeteSite_, o);
		case "saisonNomComplet":
			return SessionScolaire.staticSetSaisonNomComplet(requeteSite_, o);
		case "sessionDateDebut":
			return SessionScolaire.staticSetSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return SessionScolaire.staticSetSessionDateFin(requeteSite_, o);
		case "sessionNomCourt":
			return SessionScolaire.staticSetSessionNomCourt(requeteSite_, o);
		case "sessionNomComplet":
			return SessionScolaire.staticSetSessionNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrSessionScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrSessionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "sessionCle":
			return SessionScolaire.staticSolrSessionCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return SessionScolaire.staticSolrInscriptionCles(requeteSite_, (Long)o);
		case "ageCles":
			return SessionScolaire.staticSolrAgeCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return SessionScolaire.staticSolrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return SessionScolaire.staticSolrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return SessionScolaire.staticSolrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return SessionScolaire.staticSolrSaisonTri(requeteSite_, (Integer)o);
		case "sessionTri":
			return SessionScolaire.staticSolrSessionTri(requeteSite_, (Integer)o);
		case "saisonCle":
			return SessionScolaire.staticSolrSaisonCle(requeteSite_, (Long)o);
		case "ecoleCle":
			return SessionScolaire.staticSolrEcoleCle(requeteSite_, (Long)o);
		case "anneeCle":
			return SessionScolaire.staticSolrAnneeCle(requeteSite_, (Long)o);
		case "ecoleNom":
			return SessionScolaire.staticSolrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return SessionScolaire.staticSolrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return SessionScolaire.staticSolrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return SessionScolaire.staticSolrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return SessionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return SessionScolaire.staticSolrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return SessionScolaire.staticSolrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return SessionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return SessionScolaire.staticSolrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return SessionScolaire.staticSolrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return SessionScolaire.staticSolrSaisonDateDebut(requeteSite_, (LocalDate)o);
		case "saisonEte":
			return SessionScolaire.staticSolrSaisonEte(requeteSite_, (Boolean)o);
		case "saisonHiver":
			return SessionScolaire.staticSolrSaisonHiver(requeteSite_, (Boolean)o);
		case "anneeFraisInscription":
			return SessionScolaire.staticSolrAnneeFraisInscription(requeteSite_, (BigDecimal)o);
		case "saisonNomCourt":
			return SessionScolaire.staticSolrSaisonNomCourt(requeteSite_, (String)o);
		case "saisonNomComplet":
			return SessionScolaire.staticSolrSaisonNomComplet(requeteSite_, (String)o);
		case "sessionDateDebut":
			return SessionScolaire.staticSolrSessionDateDebut(requeteSite_, (LocalDate)o);
		case "sessionDateFin":
			return SessionScolaire.staticSolrSessionDateFin(requeteSite_, (LocalDate)o);
		case "sessionNomCourt":
			return SessionScolaire.staticSolrSessionNomCourt(requeteSite_, (String)o);
		case "sessionNomComplet":
			return SessionScolaire.staticSolrSessionNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrSessionScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrSessionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "sessionCle":
			return SessionScolaire.staticSolrStrSessionCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return SessionScolaire.staticSolrStrInscriptionCles(requeteSite_, (Long)o);
		case "ageCles":
			return SessionScolaire.staticSolrStrAgeCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return SessionScolaire.staticSolrStrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return SessionScolaire.staticSolrStrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return SessionScolaire.staticSolrStrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return SessionScolaire.staticSolrStrSaisonTri(requeteSite_, (Integer)o);
		case "sessionTri":
			return SessionScolaire.staticSolrStrSessionTri(requeteSite_, (Integer)o);
		case "saisonCle":
			return SessionScolaire.staticSolrStrSaisonCle(requeteSite_, (Long)o);
		case "ecoleCle":
			return SessionScolaire.staticSolrStrEcoleCle(requeteSite_, (Long)o);
		case "anneeCle":
			return SessionScolaire.staticSolrStrAnneeCle(requeteSite_, (Long)o);
		case "ecoleNom":
			return SessionScolaire.staticSolrStrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return SessionScolaire.staticSolrStrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return SessionScolaire.staticSolrStrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return SessionScolaire.staticSolrStrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return SessionScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return SessionScolaire.staticSolrStrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return SessionScolaire.staticSolrStrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return SessionScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return SessionScolaire.staticSolrStrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return SessionScolaire.staticSolrStrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return SessionScolaire.staticSolrStrSaisonDateDebut(requeteSite_, (Date)o);
		case "saisonEte":
			return SessionScolaire.staticSolrStrSaisonEte(requeteSite_, (Boolean)o);
		case "saisonHiver":
			return SessionScolaire.staticSolrStrSaisonHiver(requeteSite_, (Boolean)o);
		case "anneeFraisInscription":
			return SessionScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, (Double)o);
		case "saisonNomCourt":
			return SessionScolaire.staticSolrStrSaisonNomCourt(requeteSite_, (String)o);
		case "saisonNomComplet":
			return SessionScolaire.staticSolrStrSaisonNomComplet(requeteSite_, (String)o);
		case "sessionDateDebut":
			return SessionScolaire.staticSolrStrSessionDateDebut(requeteSite_, (Date)o);
		case "sessionDateFin":
			return SessionScolaire.staticSolrStrSessionDateFin(requeteSite_, (Date)o);
		case "sessionNomCourt":
			return SessionScolaire.staticSolrStrSessionNomCourt(requeteSite_, (String)o);
		case "sessionNomComplet":
			return SessionScolaire.staticSolrStrSessionNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqSessionScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqSessionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "sessionCle":
			return SessionScolaire.staticSolrFqSessionCle(requeteSite_, o);
		case "inscriptionCles":
			return SessionScolaire.staticSolrFqInscriptionCles(requeteSite_, o);
		case "ageCles":
			return SessionScolaire.staticSolrFqAgeCles(requeteSite_, o);
		case "scolaireTri":
			return SessionScolaire.staticSolrFqScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return SessionScolaire.staticSolrFqEcoleTri(requeteSite_, o);
		case "anneeTri":
			return SessionScolaire.staticSolrFqAnneeTri(requeteSite_, o);
		case "saisonTri":
			return SessionScolaire.staticSolrFqSaisonTri(requeteSite_, o);
		case "sessionTri":
			return SessionScolaire.staticSolrFqSessionTri(requeteSite_, o);
		case "saisonCle":
			return SessionScolaire.staticSolrFqSaisonCle(requeteSite_, o);
		case "ecoleCle":
			return SessionScolaire.staticSolrFqEcoleCle(requeteSite_, o);
		case "anneeCle":
			return SessionScolaire.staticSolrFqAnneeCle(requeteSite_, o);
		case "ecoleNom":
			return SessionScolaire.staticSolrFqEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return SessionScolaire.staticSolrFqEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return SessionScolaire.staticSolrFqEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return SessionScolaire.staticSolrFqEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return SessionScolaire.staticSolrFqEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return SessionScolaire.staticSolrFqEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return SessionScolaire.staticSolrFqEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return SessionScolaire.staticSolrFqEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return SessionScolaire.staticSolrFqAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return SessionScolaire.staticSolrFqAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return SessionScolaire.staticSolrFqSaisonDateDebut(requeteSite_, o);
		case "saisonEte":
			return SessionScolaire.staticSolrFqSaisonEte(requeteSite_, o);
		case "saisonHiver":
			return SessionScolaire.staticSolrFqSaisonHiver(requeteSite_, o);
		case "anneeFraisInscription":
			return SessionScolaire.staticSolrFqAnneeFraisInscription(requeteSite_, o);
		case "saisonNomCourt":
			return SessionScolaire.staticSolrFqSaisonNomCourt(requeteSite_, o);
		case "saisonNomComplet":
			return SessionScolaire.staticSolrFqSaisonNomComplet(requeteSite_, o);
		case "sessionDateDebut":
			return SessionScolaire.staticSolrFqSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return SessionScolaire.staticSolrFqSessionDateFin(requeteSite_, o);
		case "sessionNomCourt":
			return SessionScolaire.staticSolrFqSessionNomCourt(requeteSite_, o);
		case "sessionNomComplet":
			return SessionScolaire.staticSolrFqSessionNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSolrFqCluster(entiteVar,  requeteSite_, o);
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
					o = definirSessionScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSessionScolaire(String var, String val) {
		switch(var) {
			case "ecoleAddresse":
				if(val != null)
					setEcoleAddresse(val);
				sauvegardes.add(var);
				return val;
			case "sessionDateDebut":
				if(val != null)
					setSessionDateDebut(val);
				sauvegardes.add(var);
				return val;
			case "sessionDateFin":
				if(val != null)
					setSessionDateFin(val);
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
		peuplerSessionScolaire(solrDocument);
	}
	public void peuplerSessionScolaire(SolrDocument solrDocument) {
		SessionScolaire oSessionScolaire = (SessionScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("sessionCle")) {
				Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
				if(sessionCle != null)
					oSessionScolaire.setSessionCle(sessionCle);
			}

			if(sauvegardes.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oSessionScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
			if(ageCles != null)
				oSessionScolaire.ageCles.addAll(ageCles);

			if(sauvegardes.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oSessionScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardes.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oSessionScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardes.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oSessionScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardes.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oSessionScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardes.contains("sessionTri")) {
				Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
				if(sessionTri != null)
					oSessionScolaire.setSessionTri(sessionTri);
			}

			Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
			if(saisonCle != null)
				oSessionScolaire.setSaisonCle(saisonCle);

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oSessionScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardes.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oSessionScolaire.setAnneeCle(anneeCle);
			}

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oSessionScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oSessionScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oSessionScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oSessionScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oSessionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("ecoleForm")) {
				String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
				if(ecoleForm != null)
					oSessionScolaire.setEcoleForm(ecoleForm);
			}

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oSessionScolaire.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oSessionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardes.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oSessionScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardes.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oSessionScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardes.contains("saisonDateDebut")) {
				Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
				if(saisonDateDebut != null)
					oSessionScolaire.setSaisonDateDebut(saisonDateDebut);
			}

			if(sauvegardes.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oSessionScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardes.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oSessionScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardes.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oSessionScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardes.contains("saisonNomCourt")) {
				String saisonNomCourt = (String)solrDocument.get("saisonNomCourt_stored_string");
				if(saisonNomCourt != null)
					oSessionScolaire.setSaisonNomCourt(saisonNomCourt);
			}

			if(sauvegardes.contains("saisonNomComplet")) {
				String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
				if(saisonNomComplet != null)
					oSessionScolaire.setSaisonNomComplet(saisonNomComplet);
			}

			if(sauvegardes.contains("sessionDateDebut")) {
				Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
				if(sessionDateDebut != null)
					oSessionScolaire.setSessionDateDebut(sessionDateDebut);
			}

			if(sauvegardes.contains("sessionDateFin")) {
				Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
				if(sessionDateFin != null)
					oSessionScolaire.setSessionDateFin(sessionDateFin);
			}

			if(sauvegardes.contains("sessionNomCourt")) {
				String sessionNomCourt = (String)solrDocument.get("sessionNomCourt_stored_string");
				if(sessionNomCourt != null)
					oSessionScolaire.setSessionNomCourt(sessionNomCourt);
			}

			if(sauvegardes.contains("sessionNomComplet")) {
				String sessionNomComplet = (String)solrDocument.get("sessionNomComplet_stored_string");
				if(sessionNomComplet != null)
					oSessionScolaire.setSessionNomComplet(sessionNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.session.SessionScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			SessionScolaire o = new SessionScolaire();
			o.requeteSiteSessionScolaire(requeteSite);
			o.initLoinSessionScolaire(requeteSite);
			o.indexerSessionScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerSessionScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerSessionScolaire(document);
	}

	public void indexerSessionScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerSessionScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerSessionScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerSessionScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerSessionScolaire(SolrInputDocument document) {
		if(sessionCle != null) {
			document.addField("sessionCle_indexed_long", sessionCle);
			document.addField("sessionCle_stored_long", sessionCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
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
		if(scolaireTri != null) {
			document.addField("scolaireTri_indexed_int", scolaireTri);
			document.addField("scolaireTri_stored_int", scolaireTri);
		}
		if(ecoleTri != null) {
			document.addField("ecoleTri_indexed_int", ecoleTri);
			document.addField("ecoleTri_stored_int", ecoleTri);
		}
		if(anneeTri != null) {
			document.addField("anneeTri_indexed_int", anneeTri);
			document.addField("anneeTri_stored_int", anneeTri);
		}
		if(saisonTri != null) {
			document.addField("saisonTri_indexed_int", saisonTri);
			document.addField("saisonTri_stored_int", saisonTri);
		}
		if(sessionTri != null) {
			document.addField("sessionTri_indexed_int", sessionTri);
			document.addField("sessionTri_stored_int", sessionTri);
		}
		if(saisonCle != null) {
			document.addField("saisonCle_indexed_long", saisonCle);
			document.addField("saisonCle_stored_long", saisonCle);
		}
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(ecoleNom != null) {
			document.addField("ecoleNom_indexed_string", ecoleNom);
			document.addField("ecoleNom_stored_string", ecoleNom);
		}
		if(ecoleNomComplet != null) {
			document.addField("ecoleNomComplet_indexed_string", ecoleNomComplet);
			document.addField("ecoleNomComplet_stored_string", ecoleNomComplet);
		}
		if(ecoleEmplacement != null) {
			document.addField("ecoleEmplacement_indexed_string", ecoleEmplacement);
			document.addField("ecoleEmplacement_stored_string", ecoleEmplacement);
		}
		if(ecoleAddresse != null) {
			document.addField("ecoleAddresse_indexed_string", ecoleAddresse);
			document.addField("ecoleAddresse_stored_string", ecoleAddresse);
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
		if(anneeDebut != null) {
			document.addField("anneeDebut_indexed_int", anneeDebut);
			document.addField("anneeDebut_stored_int", anneeDebut);
		}
		if(anneeFin != null) {
			document.addField("anneeFin_indexed_int", anneeFin);
			document.addField("anneeFin_stored_int", anneeFin);
		}
		if(saisonDateDebut != null) {
			document.addField("saisonDateDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("saisonDateDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(saisonEte != null) {
			document.addField("saisonEte_indexed_boolean", saisonEte);
			document.addField("saisonEte_stored_boolean", saisonEte);
		}
		if(saisonHiver != null) {
			document.addField("saisonHiver_indexed_boolean", saisonHiver);
			document.addField("saisonHiver_stored_boolean", saisonHiver);
		}
		if(anneeFraisInscription != null) {
			document.addField("anneeFraisInscription_indexed_double", anneeFraisInscription.doubleValue());
			document.addField("anneeFraisInscription_stored_double", anneeFraisInscription.doubleValue());
		}
		if(saisonNomCourt != null) {
			document.addField("saisonNomCourt_indexed_string", saisonNomCourt);
			document.addField("saisonNomCourt_stored_string", saisonNomCourt);
		}
		if(saisonNomComplet != null) {
			document.addField("saisonNomComplet_indexed_string", saisonNomComplet);
			document.addField("saisonNomComplet_stored_string", saisonNomComplet);
		}
		if(sessionDateDebut != null) {
			document.addField("sessionDateDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionDateFin != null) {
			document.addField("sessionDateFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionNomCourt != null) {
			document.addField("sessionNomCourt_indexed_string", sessionNomCourt);
			document.addField("sessionNomCourt_stored_string", sessionNomCourt);
		}
		if(sessionNomComplet != null) {
			document.addField("sessionNomComplet_indexed_string", sessionNomComplet);
			document.addField("sessionNomComplet_stored_string", sessionNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerSessionScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinSessionScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeSessionScolaire(String entiteVar) {
		switch(entiteVar) {
			case "sessionCle":
				return "sessionCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "ageCles":
				return "ageCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "anneeTri":
				return "anneeTri_indexed_int";
			case "saisonTri":
				return "saisonTri_indexed_int";
			case "sessionTri":
				return "sessionTri_indexed_int";
			case "saisonCle":
				return "saisonCle_indexed_long";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "ecoleForm":
				return "ecoleForm_indexed_string";
			case "ecoleNumero":
				return "ecoleNumero_indexed_int";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "saisonDateDebut":
				return "saisonDateDebut_indexed_date";
			case "saisonEte":
				return "saisonEte_indexed_boolean";
			case "saisonHiver":
				return "saisonHiver_indexed_boolean";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "saisonNomCourt":
				return "saisonNomCourt_indexed_string";
			case "saisonNomComplet":
				return "saisonNomComplet_indexed_string";
			case "sessionDateDebut":
				return "sessionDateDebut_indexed_date";
			case "sessionDateFin":
				return "sessionDateFin_indexed_date";
			case "sessionNomCourt":
				return "sessionNomCourt_indexed_string";
			case "sessionNomComplet":
				return "sessionNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheSessionScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereSessionScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerSessionScolaire(solrDocument);
	}
	public void stockerSessionScolaire(SolrDocument solrDocument) {
		SessionScolaire oSessionScolaire = (SessionScolaire)this;

		Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
		if(sessionCle != null)
			oSessionScolaire.setSessionCle(sessionCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oSessionScolaire.inscriptionCles.addAll(inscriptionCles);

		List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
		if(ageCles != null)
			oSessionScolaire.ageCles.addAll(ageCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oSessionScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oSessionScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oSessionScolaire.setAnneeTri(anneeTri);

		Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
		if(saisonTri != null)
			oSessionScolaire.setSaisonTri(saisonTri);

		Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
		if(sessionTri != null)
			oSessionScolaire.setSessionTri(sessionTri);

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oSessionScolaire.setSaisonCle(saisonCle);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oSessionScolaire.setEcoleCle(ecoleCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oSessionScolaire.setAnneeCle(anneeCle);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oSessionScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oSessionScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oSessionScolaire.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oSessionScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oSessionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
		if(ecoleForm != null)
			oSessionScolaire.setEcoleForm(ecoleForm);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oSessionScolaire.setEcoleNumero(ecoleNumero);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oSessionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oSessionScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oSessionScolaire.setAnneeFin(anneeFin);

		Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
		if(saisonDateDebut != null)
			oSessionScolaire.setSaisonDateDebut(saisonDateDebut);

		Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
		if(saisonEte != null)
			oSessionScolaire.setSaisonEte(saisonEte);

		Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
		if(saisonHiver != null)
			oSessionScolaire.setSaisonHiver(saisonHiver);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oSessionScolaire.setAnneeFraisInscription(anneeFraisInscription);

		String saisonNomCourt = (String)solrDocument.get("saisonNomCourt_stored_string");
		if(saisonNomCourt != null)
			oSessionScolaire.setSaisonNomCourt(saisonNomCourt);

		String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
		if(saisonNomComplet != null)
			oSessionScolaire.setSaisonNomComplet(saisonNomComplet);

		Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
		if(sessionDateDebut != null)
			oSessionScolaire.setSessionDateDebut(sessionDateDebut);

		Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
		if(sessionDateFin != null)
			oSessionScolaire.setSessionDateFin(sessionDateFin);

		String sessionNomCourt = (String)solrDocument.get("sessionNomCourt_stored_string");
		if(sessionNomCourt != null)
			oSessionScolaire.setSessionNomCourt(sessionNomCourt);

		String sessionNomComplet = (String)solrDocument.get("sessionNomComplet_stored_string");
		if(sessionNomComplet != null)
			oSessionScolaire.setSessionNomComplet(sessionNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiSessionScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SessionScolaire) {
			SessionScolaire original = (SessionScolaire)o;
			if(!Objects.equals(sessionCle, original.getSessionCle()))
				requeteApi.addVars("sessionCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(ageCles, original.getAgeCles()))
				requeteApi.addVars("ageCles");
			if(!Objects.equals(scolaireTri, original.getScolaireTri()))
				requeteApi.addVars("scolaireTri");
			if(!Objects.equals(ecoleTri, original.getEcoleTri()))
				requeteApi.addVars("ecoleTri");
			if(!Objects.equals(anneeTri, original.getAnneeTri()))
				requeteApi.addVars("anneeTri");
			if(!Objects.equals(saisonTri, original.getSaisonTri()))
				requeteApi.addVars("saisonTri");
			if(!Objects.equals(sessionTri, original.getSessionTri()))
				requeteApi.addVars("sessionTri");
			if(!Objects.equals(saisonCle, original.getSaisonCle()))
				requeteApi.addVars("saisonCle");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
			if(!Objects.equals(ecoleNom, original.getEcoleNom()))
				requeteApi.addVars("ecoleNom");
			if(!Objects.equals(ecoleNomComplet, original.getEcoleNomComplet()))
				requeteApi.addVars("ecoleNomComplet");
			if(!Objects.equals(ecoleEmplacement, original.getEcoleEmplacement()))
				requeteApi.addVars("ecoleEmplacement");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			if(!Objects.equals(ecoleNumeroTelephone, original.getEcoleNumeroTelephone()))
				requeteApi.addVars("ecoleNumeroTelephone");
			if(!Objects.equals(ecoleForm, original.getEcoleForm()))
				requeteApi.addVars("ecoleForm");
			if(!Objects.equals(ecoleNumero, original.getEcoleNumero()))
				requeteApi.addVars("ecoleNumero");
			if(!Objects.equals(ecoleAdministrateurNom, original.getEcoleAdministrateurNom()))
				requeteApi.addVars("ecoleAdministrateurNom");
			if(!Objects.equals(anneeDebut, original.getAnneeDebut()))
				requeteApi.addVars("anneeDebut");
			if(!Objects.equals(anneeFin, original.getAnneeFin()))
				requeteApi.addVars("anneeFin");
			if(!Objects.equals(saisonDateDebut, original.getSaisonDateDebut()))
				requeteApi.addVars("saisonDateDebut");
			if(!Objects.equals(saisonEte, original.getSaisonEte()))
				requeteApi.addVars("saisonEte");
			if(!Objects.equals(saisonHiver, original.getSaisonHiver()))
				requeteApi.addVars("saisonHiver");
			if(!Objects.equals(anneeFraisInscription, original.getAnneeFraisInscription()))
				requeteApi.addVars("anneeFraisInscription");
			if(!Objects.equals(saisonNomCourt, original.getSaisonNomCourt()))
				requeteApi.addVars("saisonNomCourt");
			if(!Objects.equals(saisonNomComplet, original.getSaisonNomComplet()))
				requeteApi.addVars("saisonNomComplet");
			if(!Objects.equals(sessionDateDebut, original.getSessionDateDebut()))
				requeteApi.addVars("sessionDateDebut");
			if(!Objects.equals(sessionDateFin, original.getSessionDateFin()))
				requeteApi.addVars("sessionDateFin");
			if(!Objects.equals(sessionNomCourt, original.getSessionNomCourt()))
				requeteApi.addVars("sessionNomCourt");
			if(!Objects.equals(sessionNomComplet, original.getSessionNomComplet()))
				requeteApi.addVars("sessionNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), sessionCle, inscriptionCles, ageCles, scolaireTri, ecoleTri, anneeTri, saisonTri, sessionTri, saisonCle, ecoleCle, anneeCle, ecoleNom, ecoleNomComplet, ecoleEmplacement, ecoleAddresse, ecoleNumeroTelephone, ecoleForm, ecoleNumero, ecoleAdministrateurNom, anneeDebut, anneeFin, saisonDateDebut, saisonEte, saisonHiver, anneeFraisInscription, saisonNomCourt, saisonNomComplet, sessionDateDebut, sessionDateFin, sessionNomCourt, sessionNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SessionScolaire))
			return false;
		SessionScolaire that = (SessionScolaire)o;
		return super.equals(o)
				&& Objects.equals( sessionCle, that.sessionCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( ageCles, that.ageCles )
				&& Objects.equals( scolaireTri, that.scolaireTri )
				&& Objects.equals( ecoleTri, that.ecoleTri )
				&& Objects.equals( anneeTri, that.anneeTri )
				&& Objects.equals( saisonTri, that.saisonTri )
				&& Objects.equals( sessionTri, that.sessionTri )
				&& Objects.equals( saisonCle, that.saisonCle )
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNomComplet, that.ecoleNomComplet )
				&& Objects.equals( ecoleEmplacement, that.ecoleEmplacement )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( ecoleForm, that.ecoleForm )
				&& Objects.equals( ecoleNumero, that.ecoleNumero )
				&& Objects.equals( ecoleAdministrateurNom, that.ecoleAdministrateurNom )
				&& Objects.equals( anneeDebut, that.anneeDebut )
				&& Objects.equals( anneeFin, that.anneeFin )
				&& Objects.equals( saisonDateDebut, that.saisonDateDebut )
				&& Objects.equals( saisonEte, that.saisonEte )
				&& Objects.equals( saisonHiver, that.saisonHiver )
				&& Objects.equals( anneeFraisInscription, that.anneeFraisInscription )
				&& Objects.equals( saisonNomCourt, that.saisonNomCourt )
				&& Objects.equals( saisonNomComplet, that.saisonNomComplet )
				&& Objects.equals( sessionDateDebut, that.sessionDateDebut )
				&& Objects.equals( sessionDateFin, that.sessionDateFin )
				&& Objects.equals( sessionNomCourt, that.sessionNomCourt )
				&& Objects.equals( sessionNomComplet, that.sessionNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SessionScolaire { ");
		sb.append( "sessionCle: " ).append(sessionCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", ageCles: " ).append(ageCles);
		sb.append( ", scolaireTri: " ).append(scolaireTri);
		sb.append( ", ecoleTri: " ).append(ecoleTri);
		sb.append( ", anneeTri: " ).append(anneeTri);
		sb.append( ", saisonTri: " ).append(saisonTri);
		sb.append( ", sessionTri: " ).append(sessionTri);
		sb.append( ", saisonCle: " ).append(saisonCle);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
		sb.append( ", anneeCle: " ).append(anneeCle);
		sb.append( ", ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNomComplet: \"" ).append(ecoleNomComplet).append( "\"" );
		sb.append( ", ecoleEmplacement: \"" ).append(ecoleEmplacement).append( "\"" );
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", ecoleForm: \"" ).append(ecoleForm).append( "\"" );
		sb.append( ", ecoleNumero: " ).append(ecoleNumero);
		sb.append( ", ecoleAdministrateurNom: \"" ).append(ecoleAdministrateurNom).append( "\"" );
		sb.append( ", anneeDebut: " ).append(anneeDebut);
		sb.append( ", anneeFin: " ).append(anneeFin);
		sb.append( ", saisonDateDebut: " ).append(saisonDateDebut);
		sb.append( ", saisonEte: " ).append(saisonEte);
		sb.append( ", saisonHiver: " ).append(saisonHiver);
		sb.append( ", anneeFraisInscription: " ).append(anneeFraisInscription);
		sb.append( ", saisonNomCourt: \"" ).append(saisonNomCourt).append( "\"" );
		sb.append( ", saisonNomComplet: \"" ).append(saisonNomComplet).append( "\"" );
		sb.append( ", sessionDateDebut: " ).append(sessionDateDebut);
		sb.append( ", sessionDateFin: " ).append(sessionDateFin);
		sb.append( ", sessionNomCourt: \"" ).append(sessionNomCourt).append( "\"" );
		sb.append( ", sessionNomComplet: \"" ).append(sessionNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
