package org.computate.scolaire.frFR.contexte;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.vertx.ext.web.Router;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import io.vertx.core.WorkerExecutor;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.pgclient.PgPool;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.mail.MailClient;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class SiteContexteFrFRGen<DEV> extends Object {

/*
CREATE TABLE InscriptionScolaire(
	anneeCle bigint references AnneeScolaire(pk)
	, enfantCle bigint references EnfantScolaire(pk)
	, enfantNomComplet text
	, enfantNomCompletPrefere text
	, enfantDateNaissance date
	, ecoleAddresse text
	, inscriptionApprouve boolean
	, inscriptionImmunisations boolean
	, photo text
	, familleMarie boolean
	, familleSepare boolean
	, familleDivorce boolean
	, inscriptionMotDePasse text
	, familleAddresse text
	, familleCommentVousConnaissezEcole text
	, inscriptionConsiderationsSpeciales text
	, enfantConditionsMedicales text
	, enfantEcolesPrecedemmentFrequentees text
	, enfantDescription text
	, enfantObjectifs text
	, enfantPropre boolean
	, inscriptionNomGroupe text
	, inscriptionPaimentChaqueMois boolean
	, inscriptionPaimentComplet boolean
	, customerProfileId text
	, inscriptionDateFrais date
	, inscriptionNomsParents text
	, inscriptionSignature1 text
	, inscriptionSignature2 text
	, inscriptionSignature3 text
	, inscriptionSignature4 text
	, inscriptionSignature5 text
	, inscriptionSignature6 text
	, inscriptionSignature7 text
	, inscriptionSignature8 text
	, inscriptionSignature9 text
	, inscriptionSignature10 text
	, inscriptionDate1 date
	, inscriptionDate2 date
	, inscriptionDate3 date
	, inscriptionDate4 date
	, inscriptionDate5 date
	, inscriptionDate6 date
	, inscriptionDate7 date
	, inscriptionDate8 date
	, inscriptionDate9 date
	, inscriptionDate10 date
	, pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	);
CREATE TABLE Cluster(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	);
CREATE TABLE EnfantScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, personnePrenom text
	, personnePrenomPrefere text
	, familleNom text
	, personneDateNaissance date
	, photo text
	);
CREATE TABLE MereScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, personnePrenom text
	, personnePrenomPrefere text
	, familleNom text
	, personneOccupation text
	, personneNumeroTelephone text
	, personneMail text
	, personneSms boolean
	, personneRecevoirMail boolean
	, personneContactUrgence boolean
	, personneChercher boolean
	, photo text
	);
CREATE TABLE PereScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, personnePrenom text
	, personnePrenomPrefere text
	, familleNom text
	, personneOccupation text
	, personneNumeroTelephone text
	, personneMail text
	, personneSms boolean
	, personneRecevoirMail boolean
	, personneContactUrgence boolean
	, personneChercher boolean
	, photo text
	);
CREATE TABLE GardienScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, personnePrenom text
	, personnePrenomPrefere text
	, familleNom text
	, personneNumeroTelephone text
	, personneRelation text
	, personneContactUrgence boolean
	, personneChercher boolean
	, photo text
	);
CREATE TABLE PaiementScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, inscriptionCle bigint references InscriptionScolaire(pk)
	, inscriptionPaimentChaqueMois boolean
	, paiementDescription text
	, paiementDate date
	, fraisRetardDate date
	, paiementMontant double
	, paiementEspeces boolean
	, paiementCheque boolean
	, paiementECheck boolean
	, paiementSysteme boolean
	, paiementPar text
	, transactionId text
	, customerProfileId text
	, transactionStatus text
	, paiementRecu boolean
	, fraisMontant double
	, fraisPremierDernier boolean
	, fraisInscription boolean
	, fraisMois boolean
	, fraisRetard boolean
	, paiementNomCourt text
	);
CREATE TABLE RecuScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, ecoleCle bigint references Ecole(pk)
	, paiementDate date
	, paiementMontant double
	, paiementDescription text
	, paiementNomCourt text
	);
CREATE TABLE UtilisateurSite(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, utilisateurNom text
	, utilisateurMail text
	, customerProfileId1 text
	, customerProfileId2 text
	, customerProfileId3 text
	, customerProfileId4 text
	, customerProfileId5 text
	, customerProfileId6 text
	, customerProfileId7 text
	, customerProfileId8 text
	, customerProfileId9 text
	, customerProfileId10 text
	, utilisateurRecevoirCourriels boolean
	, voirArchive boolean
	, voirSupprime boolean
	);
CREATE TABLE DesignPage(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, designPageNomComplet text
	, designCache boolean
	, designAdmin boolean
	, designIgnorerNomEnfantVide boolean
	, designIgnorerPaiementsPasEnSouffrance boolean
	, designIgnorerPaiementsEnSouffrance boolean
	, designFiltrerInscriptionCle boolean
	, designInscriptionTriMoisJourDeNaissance boolean
	, designInscriptionTriNomGroupe boolean
	, designInscriptionTriNomEnfant boolean
	, rechercherAnnees boolean
	, rechercherPaiements boolean
	, rechercherPaiementsActuel boolean
	);
CREATE TABLE PartHtml(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, htmlLien text
	, htmlElement text
	, htmlId text
	, htmlClasses text
	, htmlStyle text
	, htmlAvant text
	, htmlApres text
	, htmlTexte text
	, htmlVar text
	, htmlVarSpan text
	, htmlVarForm text
	, htmlVarInput text
	, htmlIfVarEquals text
	, htmlVarForEach text
	, htmlVarHtml text
	, htmlExclure boolean
	, pdfExclure boolean
	, connecterDeconnecter boolean
	, tri1 double
	, tri2 double
	, tri3 double
	, tri4 double
	, tri5 double
	, tri6 double
	, tri7 double
	, tri8 double
	, tri9 double
	, tri10 double
	);
CREATE TABLE Ecole(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, ecoleNom text
	, ecoleNumeroTelephone text
	, ecoleForm text
	, ecoleNumero integer
	, ecoleAdministrateurNom text
	, ecoleMail text
	, ecoleMailDe text
	, ecoleMailA text
	, ecoleEmplacement text
	, ecoleAddresse text
	);
CREATE TABLE AnneeScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, ecoleCle bigint references Ecole(pk)
	, sessionDateDebut date
	, saisonDateDebut date
	, sessionDateFin date
	, anneeDebut integer
	, anneeFin integer
	, anneeFraisInscription double
	);
CREATE TABLE SaisonScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, anneeCle bigint references AnneeScolaire(pk)
	, saisonDateDebut date
	, saisonEte boolean
	, saisonHiver boolean
	, saisonFuture boolean
	);
CREATE TABLE SessionScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, saisonCle bigint references SaisonScolaire(pk)
	, ecoleAddresse text
	, sessionDateDebut date
	, sessionDateFin date
	);
CREATE TABLE AgeScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, sessionCle bigint references SessionScolaire(pk)
	, anneeCle bigint references AnneeScolaire(pk)
	, ecoleAddresse text
	, ageDebut integer
	, ageFin integer
	);
CREATE TABLE BlocScolaire(
	pk bigserial primary key
	, inheritPk bigint
	, cree timestamp with time zone
	, modifie timestamp with time zone
	, archive boolean
	, supprime boolean
	, sessionId text
	, utilisateurId text
	, utilisateurCle bigint
	, ageCle bigint references AgeScolaire(pk)
	, ecoleAddresse text
	, blocHeureDebut timestamp
	, blocHeureFin timestamp
	, blocPrixParMois double
	, blocLundi boolean
	, blocMardi boolean
	, blocMercredi boolean
	, blocJeudi boolean
	, blocVendredi boolean
	);
CREATE TABLE InscriptionScolaireBlocCles_BlocScolaireInscriptionCles(
	pk bigserial primary key
	, pk1 bigint references BlocScolaire(pk)
	, pk2 bigint references InscriptionScolaire(pk)
	);
CREATE TABLE InscriptionScolaireGardienCles_GardienScolaireInscriptionCles(
	pk bigserial primary key
	, pk1 bigint references GardienScolaire(pk)
	, pk2 bigint references InscriptionScolaire(pk)
	);
CREATE TABLE MereScolaireInscriptionCles_InscriptionScolaireMereCles(
	pk bigserial primary key
	, pk1 bigint references InscriptionScolaire(pk)
	, pk2 bigint references MereScolaire(pk)
	);
CREATE TABLE PereScolaireInscriptionCles_InscriptionScolairePereCles(
	pk bigserial primary key
	, pk1 bigint references InscriptionScolaire(pk)
	, pk2 bigint references PereScolaire(pk)
	);
CREATE TABLE UtilisateurSiteInscriptionCles_InscriptionScolaireUtilisateurCles(
	pk bigserial primary key
	, pk1 bigint references InscriptionScolaire(pk)
	, pk2 bigint references UtilisateurSite(pk)
	);
CREATE TABLE UtilisateurSitePaiementCles_PaiementScolaireUtilisateurCles(
	pk bigserial primary key
	, pk1 bigint references PaiementScolaire(pk)
	, pk2 bigint references UtilisateurSite(pk)
	);
CREATE TABLE DesignPageDesignEnfantCles_DesignPageDesignParentCles(
	pk bigserial primary key
	, pk1 bigint references DesignPage(pk)
	, pk2 bigint references DesignPage(pk)
	);
CREATE TABLE PartHtmlDesignPageCles_DesignPagePartHtmlCles(
	pk bigserial primary key
	, pk1 bigint references DesignPage(pk)
	, pk2 bigint references PartHtml(pk)
	);
*/

	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteContexteFrFR.class);

	///////////
	// vertx //
	///////////

	/**	 L'entité vertx
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Vertx vertx;
	@JsonIgnore
	public Couverture<Vertx> vertxCouverture = new Couverture<Vertx>().p(this).c(Vertx.class).var("vertx").o(vertx);

	/**	<br/> L'entité vertx
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertx(Couverture<Vertx> c);

	public Vertx getVertx() {
		return vertx;
	}

	public void setVertx(Vertx vertx) {
		this.vertx = vertx;
		this.vertxCouverture.dejaInitialise = true;
	}
	public static Vertx staticSetVertx(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR vertxInit() {
		if(!vertxCouverture.dejaInitialise) {
			_vertx(vertxCouverture);
			if(vertx == null)
				setVertx(vertxCouverture.o);
		}
		vertxCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////
	// usineRouteur //
	//////////////////

	/**	 L'entité usineRouteur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected OpenAPI3RouterFactory usineRouteur;
	@JsonIgnore
	public Couverture<OpenAPI3RouterFactory> usineRouteurCouverture = new Couverture<OpenAPI3RouterFactory>().p(this).c(OpenAPI3RouterFactory.class).var("usineRouteur").o(usineRouteur);

	/**	<br/> L'entité usineRouteur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:usineRouteur">Trouver l'entité usineRouteur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _usineRouteur(Couverture<OpenAPI3RouterFactory> c);

	public OpenAPI3RouterFactory getUsineRouteur() {
		return usineRouteur;
	}

	public void setUsineRouteur(OpenAPI3RouterFactory usineRouteur) {
		this.usineRouteur = usineRouteur;
		this.usineRouteurCouverture.dejaInitialise = true;
	}
	public static OpenAPI3RouterFactory staticSetUsineRouteur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR usineRouteurInit() {
		if(!usineRouteurCouverture.dejaInitialise) {
			_usineRouteur(usineRouteurCouverture);
			if(usineRouteur == null)
				setUsineRouteur(usineRouteurCouverture.o);
		}
		usineRouteurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////
	// routeur //
	/////////////

	/**	 L'entité routeur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Router routeur;
	@JsonIgnore
	public Couverture<Router> routeurCouverture = new Couverture<Router>().p(this).c(Router.class).var("routeur").o(routeur);

	/**	<br/> L'entité routeur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:routeur">Trouver l'entité routeur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _routeur(Couverture<Router> c);

	public Router getRouteur() {
		return routeur;
	}

	public void setRouteur(Router routeur) {
		this.routeur = routeur;
		this.routeurCouverture.dejaInitialise = true;
	}
	public static Router staticSetRouteur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR routeurInit() {
		if(!routeurCouverture.dejaInitialise) {
			_routeur(routeurCouverture);
			if(routeur == null)
				setRouteur(routeurCouverture.o);
		}
		routeurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////////
	// gestionnaireAuth //
	//////////////////////

	/**	 L'entité gestionnaireAuth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected OAuth2AuthHandler gestionnaireAuth;
	@JsonIgnore
	public Couverture<OAuth2AuthHandler> gestionnaireAuthCouverture = new Couverture<OAuth2AuthHandler>().p(this).c(OAuth2AuthHandler.class).var("gestionnaireAuth").o(gestionnaireAuth);

	/**	<br/> L'entité gestionnaireAuth
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gestionnaireAuth">Trouver l'entité gestionnaireAuth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gestionnaireAuth(Couverture<OAuth2AuthHandler> c);

	public OAuth2AuthHandler getGestionnaireAuth() {
		return gestionnaireAuth;
	}

	public void setGestionnaireAuth(OAuth2AuthHandler gestionnaireAuth) {
		this.gestionnaireAuth = gestionnaireAuth;
		this.gestionnaireAuthCouverture.dejaInitialise = true;
	}
	public static OAuth2AuthHandler staticSetGestionnaireAuth(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR gestionnaireAuthInit() {
		if(!gestionnaireAuthCouverture.dejaInitialise) {
			_gestionnaireAuth(gestionnaireAuthCouverture);
			if(gestionnaireAuth == null)
				setGestionnaireAuth(gestionnaireAuthCouverture.o);
		}
		gestionnaireAuthCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////////////
	// authFournisseur //
	/////////////////////

	/**	 L'entité authFournisseur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected OAuth2Auth authFournisseur;
	@JsonIgnore
	public Couverture<OAuth2Auth> authFournisseurCouverture = new Couverture<OAuth2Auth>().p(this).c(OAuth2Auth.class).var("authFournisseur").o(authFournisseur);

	/**	<br/> L'entité authFournisseur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authFournisseur">Trouver l'entité authFournisseur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authFournisseur(Couverture<OAuth2Auth> c);

	public OAuth2Auth getAuthFournisseur() {
		return authFournisseur;
	}

	public void setAuthFournisseur(OAuth2Auth authFournisseur) {
		this.authFournisseur = authFournisseur;
		this.authFournisseurCouverture.dejaInitialise = true;
	}
	public static OAuth2Auth staticSetAuthFournisseur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR authFournisseurInit() {
		if(!authFournisseurCouverture.dejaInitialise) {
			_authFournisseur(authFournisseurCouverture);
			if(authFournisseur == null)
				setAuthFournisseur(authFournisseurCouverture.o);
		}
		authFournisseurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////////////
	// executeurTravailleur //
	//////////////////////////

	/**	 L'entité executeurTravailleur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected WorkerExecutor executeurTravailleur;
	@JsonIgnore
	public Couverture<WorkerExecutor> executeurTravailleurCouverture = new Couverture<WorkerExecutor>().p(this).c(WorkerExecutor.class).var("executeurTravailleur").o(executeurTravailleur);

	/**	<br/> L'entité executeurTravailleur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:executeurTravailleur">Trouver l'entité executeurTravailleur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _executeurTravailleur(Couverture<WorkerExecutor> c);

	public WorkerExecutor getExecuteurTravailleur() {
		return executeurTravailleur;
	}

	public void setExecuteurTravailleur(WorkerExecutor executeurTravailleur) {
		this.executeurTravailleur = executeurTravailleur;
		this.executeurTravailleurCouverture.dejaInitialise = true;
	}
	public static WorkerExecutor staticSetExecuteurTravailleur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR executeurTravailleurInit() {
		if(!executeurTravailleurCouverture.dejaInitialise) {
			_executeurTravailleur(executeurTravailleurCouverture);
			if(executeurTravailleur == null)
				setExecuteurTravailleur(executeurTravailleurCouverture.o);
		}
		executeurTravailleurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	 L'entité configSite
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ConfigSite configSite = new ConfigSite();
	@JsonIgnore
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/> L'entité configSite
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
	 * <br/>
	 * @param configSite est l'entité déjà construit. 
	 **/
	protected abstract void _configSite(ConfigSite o);

	public ConfigSite getConfigSite() {
		return configSite;
	}

	public void setConfigSite(ConfigSite configSite) {
		this.configSite = configSite;
		this.configSiteCouverture.dejaInitialise = true;
	}
	public static ConfigSite staticSetConfigSite(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSite);
		}
		configSite.initLoinPourClasse(null);
		configSiteCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////
	// pgPool //
	////////////

	/**	 L'entité pgPool
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PgPool pgPool;
	@JsonIgnore
	public Couverture<PgPool> pgPoolCouverture = new Couverture<PgPool>().p(this).c(PgPool.class).var("pgPool").o(pgPool);

	/**	<br/> L'entité pgPool
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pgPool">Trouver l'entité pgPool dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pgPool(Couverture<PgPool> c);

	public PgPool getPgPool() {
		return pgPool;
	}

	public void setPgPool(PgPool pgPool) {
		this.pgPool = pgPool;
		this.pgPoolCouverture.dejaInitialise = true;
	}
	public static PgPool staticSetPgPool(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR pgPoolInit() {
		if(!pgPoolCouverture.dejaInitialise) {
			_pgPool(pgPoolCouverture);
			if(pgPool == null)
				setPgPool(pgPoolCouverture.o);
		}
		pgPoolCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////////
	// clientSolr //
	////////////////

	/**	 L'entité clientSolr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected HttpSolrClient clientSolr;
	@JsonIgnore
	public Couverture<HttpSolrClient> clientSolrCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolr").o(clientSolr);

	/**	<br/> L'entité clientSolr
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolr">Trouver l'entité clientSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolr(Couverture<HttpSolrClient> c);

	public HttpSolrClient getClientSolr() {
		return clientSolr;
	}

	public void setClientSolr(HttpSolrClient clientSolr) {
		this.clientSolr = clientSolr;
		this.clientSolrCouverture.dejaInitialise = true;
	}
	public static HttpSolrClient staticSetClientSolr(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR clientSolrInit() {
		if(!clientSolrCouverture.dejaInitialise) {
			_clientSolr(clientSolrCouverture);
			if(clientSolr == null)
				setClientSolr(clientSolrCouverture.o);
		}
		clientSolrCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////////
	// mailClient //
	////////////////

	/**	 L'entité mailClient
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected MailClient mailClient;
	@JsonIgnore
	public Couverture<MailClient> mailClientCouverture = new Couverture<MailClient>().p(this).c(MailClient.class).var("mailClient").o(mailClient);

	/**	<br/> L'entité mailClient
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailClient">Trouver l'entité mailClient dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailClient(Couverture<MailClient> c);

	public MailClient getMailClient() {
		return mailClient;
	}

	public void setMailClient(MailClient mailClient) {
		this.mailClient = mailClient;
		this.mailClientCouverture.dejaInitialise = true;
	}
	public static MailClient staticSetMailClient(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR mailClientInit() {
		if(!mailClientCouverture.dejaInitialise) {
			_mailClient(mailClientCouverture);
			if(mailClient == null)
				setMailClient(mailClientCouverture.o);
		}
		mailClientCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////////////////
	// clientSolrComputate //
	/////////////////////////

	/**	 L'entité clientSolrComputate
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected HttpSolrClient clientSolrComputate;
	@JsonIgnore
	public Couverture<HttpSolrClient> clientSolrComputateCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolrComputate").o(clientSolrComputate);

	/**	<br/> L'entité clientSolrComputate
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolrComputate">Trouver l'entité clientSolrComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolrComputate(Couverture<HttpSolrClient> c);

	public HttpSolrClient getClientSolrComputate() {
		return clientSolrComputate;
	}

	public void setClientSolrComputate(HttpSolrClient clientSolrComputate) {
		this.clientSolrComputate = clientSolrComputate;
		this.clientSolrComputateCouverture.dejaInitialise = true;
	}
	public static HttpSolrClient staticSetClientSolrComputate(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected SiteContexteFrFR clientSolrComputateInit() {
		if(!clientSolrComputateCouverture.dejaInitialise) {
			_clientSolrComputate(clientSolrComputateCouverture);
			if(clientSolrComputate == null)
				setClientSolrComputate(clientSolrComputateCouverture.o);
		}
		clientSolrComputateCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSiteContexteFrFR = false;

	public SiteContexteFrFR initLoinSiteContexteFrFR(RequeteSiteFrFR requeteSite_) {
		if(!dejaInitialiseSiteContexteFrFR) {
			dejaInitialiseSiteContexteFrFR = true;
			initLoinSiteContexteFrFR();
		}
		return (SiteContexteFrFR)this;
	}

	public void initLoinSiteContexteFrFR() {
		initSiteContexteFrFR();
	}

	public void initSiteContexteFrFR() {
		vertxInit();
		usineRouteurInit();
		routeurInit();
		gestionnaireAuthInit();
		authFournisseurInit();
		executeurTravailleurInit();
		configSiteInit();
		pgPoolInit();
		clientSolrInit();
		mailClientInit();
		clientSolrComputateInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSiteContexteFrFR(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSiteContexteFrFR(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirSiteContexteFrFR(String var) {
		SiteContexteFrFR oSiteContexteFrFR = (SiteContexteFrFR)this;
		switch(var) {
			case "vertx":
				return oSiteContexteFrFR.vertx;
			case "usineRouteur":
				return oSiteContexteFrFR.usineRouteur;
			case "routeur":
				return oSiteContexteFrFR.routeur;
			case "gestionnaireAuth":
				return oSiteContexteFrFR.gestionnaireAuth;
			case "authFournisseur":
				return oSiteContexteFrFR.authFournisseur;
			case "executeurTravailleur":
				return oSiteContexteFrFR.executeurTravailleur;
			case "configSite":
				return oSiteContexteFrFR.configSite;
			case "pgPool":
				return oSiteContexteFrFR.pgPool;
			case "clientSolr":
				return oSiteContexteFrFR.clientSolr;
			case "mailClient":
				return oSiteContexteFrFR.mailClient;
			case "clientSolrComputate":
				return oSiteContexteFrFR.clientSolrComputate;
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerSiteContexteFrFR(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSiteContexteFrFR(String var, Object val) {
		SiteContexteFrFR oSiteContexteFrFR = (SiteContexteFrFR)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetSiteContexteFrFR(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetSiteContexteFrFR(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrSiteContexteFrFR(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrSiteContexteFrFR(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrSiteContexteFrFR(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrSiteContexteFrFR(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqSiteContexteFrFR(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqSiteContexteFrFR(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirSiteContexteFrFR(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteContexteFrFR(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirSiteContexteFrFR(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteContexteFrFR(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SiteContexteFrFR))
			return false;
		SiteContexteFrFR that = (SiteContexteFrFR)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteContexteFrFR { ");
		sb.append(" }");
		return sb.toString();
	}
}
