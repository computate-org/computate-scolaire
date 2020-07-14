package org.computate.scolaire.frFR.page;          

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.frFR.age.AgeGenPage;
import org.computate.scolaire.frFR.annee.AnneeGenPage;
import org.computate.scolaire.frFR.bloc.BlocGenPage;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.design.DesignPageGenPage;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.ecole.EcoleGenPage;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.enfant.EnfantGenPage;
import org.computate.scolaire.frFR.gardien.GardienGenPage;
import org.computate.scolaire.frFR.html.part.PartHtmlGenPage;
import org.computate.scolaire.frFR.inscription.InscriptionGenPage;
import org.computate.scolaire.frFR.mere.MereGenPage;
import org.computate.scolaire.frFR.page.part.PagePart;
import org.computate.scolaire.frFR.paiement.PaiementGenPage;
import org.computate.scolaire.frFR.pere.PereGenPage;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.xml.OutilXml;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfLineItem;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.CustomerProfilePaymentType;
import net.authorize.api.contract.v1.GetHostedPaymentPageRequest;
import net.authorize.api.contract.v1.GetHostedPaymentPageResponse;
import net.authorize.api.contract.v1.GetHostedProfilePageRequest;
import net.authorize.api.contract.v1.GetHostedProfilePageResponse;
import net.authorize.api.contract.v1.LineItemType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderType;
import net.authorize.api.contract.v1.SettingType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.GetHostedPaymentPageController;
import net.authorize.api.controller.GetHostedProfilePageController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**  
 * NomCanonique.enUS: org.computate.scolaire.enUS.page.PageLayout
 * MotCle: classeNomSimpleMiseEnPage
 */   
public class MiseEnPage extends MiseEnPageGen<Object> {   

	public static final List<String> ROLES_MANAGER = Arrays.asList("SiteManager");
	public static final List<String> ROLES_ADMIN = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("User");

	/**
	 * Var.enUS: HTML_CLOSED_ELEMENTS
	 */
	public static List<String> HTML_ELEMENTS_FERMES = Arrays.asList("area", "base", "br", "col", "command", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");
	public static List<String> HTML_ELEMENTS_NO_WRAP = Arrays.asList("script", "span", "a", "b", "i", "u", "title", "use", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "p", "textarea");

	/**
	 * Var.enUS: FORMATDateTimeShort
	 * r: d MMM yyyy H'h'mm
	 * r.enUS: MMM d yyyy h:mm a
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATDateHeureCourt = DateTimeFormatter.ofPattern("d MMM yyyy H'h'mm", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATDateShort
	 * r: EEE d MMM yyyy
	 * r.enUS: EEE MMM d yyyy
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATDateCourt = DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATMonthYear
	 * r: EEE d MMM yyyy
	 * r.enUS: EEE MMM d yyyy
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATMoisAnnee = DateTimeFormatter.ofPattern("MMM yyyy", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATDateDisplay
	 * r: EEEE d MMMM yyyy
	 * r.enUS: EEEE MMMM d yyyy
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATDateAffichage = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATDateTimeDisplay
	 * r: EEEE d MMMM yyyy H'h'mm
	 * r.enUS: EEEE MMMM d yyyy h:mm a
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATDateHeureAffichage = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy H'h'mm:ss.SSS", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATZonedDateTimeDisplay
	 * r: EEEE d MMMM yyyy H'h'mm:ss.SSS zz VV
	 * r.enUS: EEEE MMMM d yyyy H:mm:ss.SSS zz VV
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATDateHeureZoneeAffichage = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy H'h'mm:ss.SSS zz VV", Locale.FRANCE);

	/**
	 * Var.enUS: FORMATTimeDisplay
	 * r: H'h'mm
	 * r.enUS: h:mm a
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */
	public static DateTimeFormatter FORMATHeureAffichage = DateTimeFormatter.ofPattern("H'h'mm", Locale.FRANCE);

	protected void _pageParts(List<PagePart> l) {
	}

	/**
	 * Var.enUS: beforePagePart
	 */
	public void avantPagePart(PagePart o, String var) {
		o.setPage_(this);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteRequest_
	 **/ 
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: siteBaseUrl
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 **/ 
	protected void _siteUrlBase(Couverture<String> c) {
		c.o(requeteSite_.getConfigSite_().getSiteUrlBase());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: staticBaseUrl
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: StatiqueUrlBase
	 * r.enUS: StaticBaseUrl
	 **/ 
	protected void _statiqueUrlBase(Couverture<String> c) {
		c.o(requeteSite_.getConfigSite_().getStatiqueUrlBase()); 
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: pageSolrDocument
	 **/
	protected void _pageDocumentSolr(Couverture<SolrDocument> c) {
		
	}

	/**
	 * frFR: L'écrivain pour écrirer le résultat du réponse. 
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 */ 
	protected void _w(Couverture<ToutEcrivain> c) {
		c.o(requeteSite_.getW());
	}

	/**
	 * Var.enUS: contextIconGroup
	 */
	protected void _contexteIconeGroupe(Couverture<String> c) {
	}

	/**
	 * Var.enUS: contextIconName
	 */
	protected void _contexteIconeNom(Couverture<String> c) {
	}

	/**
	 * Var.enUS: contextIconCssClasses
	 * r: contexteIconeGroupe
	 * r.enUS: contextIconGroup
	 * r: contexteIconeNom
	 * r.enUS: contextIconName
	 */
	protected void _contexteIconeClassesCss(Couverture<String> c) {
		if(contexteIconeGroupe != null && contexteIconeNom != null)
			c.o("fa" + StringUtils.substring(contexteIconeGroupe, 0, 1) + " fa-" + contexteIconeNom + " ");
	}

	/**
	 * Var.enUS: pageVisibleToBots
	 * frFR: Choisir si cette page est trouvée dans le /sitemap.xml.
	 * frFR: Si true, les bots de Google, Bing, Yahoo peuvent trouver la page. 
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageVisibleAuxBots(Couverture<Boolean> c)  {
		c.o(BooleanUtils.toBooleanDefaultIfNull((Boolean)pageDocumentSolr.get(c.var + "_stored_boolean"), false));
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH1(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH2(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH3(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * Var.enUS: _pageH1Short
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH1Court(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH1));
	}

	/**
	 * Var.enUS: _pageH2Short
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH2Court(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH2));
	}

	/**
	 * Var.enUS: _pageH3Short
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageH3Court(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH2));
	}

	/**
	 * Var.enUS: pageTitle
	 * Indexe: true
	 * Stocke: true
	 * r: frFR
	 * r.enUS: enUS
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageTitre(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_frFR_stored_string"), StringUtils.join(pageH1, pageH2)));
	}

	/**	la version plus courte de l'URL qui commence avec « / » 
	 * Indexe: true
	 * Stocke: true
	 * r: frFR
	 * r.enUS: enUS
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 * **/
	protected void _pageUri(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_frFR_stored_string"), null));
	}

	/**	Tous les URIs ensemble pour toutes les langues dans cette liste. 
	 * Indexe: true
	 * Stocke: true
	 * **/
	protected void _pageUris(List<String> l)  {
		l.add(pageUri.toString());
		l.add(pageUri + "/");
	}

	/**
	 * frFR: l'URL complet. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomHoteSite
	 * r.enUS: siteHostName
	 * r: requete
	 * r.enUS: request
	 * r: requeteServeur
	 * r.enUS: serverRequest
	 */ 
	protected void _pageUrl(Couverture<String> c)  {
	}

	/**
	 * frFR: l'URI courte vers l'image. 
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageImageUri(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * frFR: l'URL complet vers l'image. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: SiteNomHote
	 * r.enUS: SiteHostName
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageImageUrl(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://" + requeteSite_.getConfigSite_().getSiteNomHote() + pageImageUri));
	}

	/**
	 * frFR: l'ID Youtube du video. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomHoteSite
	 * r.enUS: siteHostName
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageVideoId(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * frFR: l'URL complet vers le video. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomHoteSite
	 * r.enUS: siteHostName
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageVideoUrl(Couverture<String> c)  {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://youtu.be/" + pageVideoId));
		}
	}

	/**
	 * frFR: l'URL embed vers le video. 
	 * Indexe: true
	 * Stocke: true
	 * r: requeteSite_
	 * r.enUS: siteRequest
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomHoteSite
	 * r.enUS: siteHostName
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageVideoUrlEmbed(Couverture<String> c)  {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://www.youtube.com/embed/" + pageVideoId));
		}
	}

	/**
	 * Var.enUS: pageImageWidth
	 * frFR: Le longeur de l'image. 
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageImageLargeur(Couverture<Integer> c)  {
		c.o((Integer)pageDocumentSolr.get(c.var + "_stored_int"));
	}

	/**
	 * Var.enUS: pageImageHeight
	 * frFR: Le hauteur de l'image. 
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageImageHauteur(Couverture<Integer> c)  {
		c.o((Integer)pageDocumentSolr.get(c.var + "_stored_int"));
	}

	/**
	 * Var.enUS: pageImageContentType
	 * frFR: Le type de contenu de l'image. 
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageImageTypeContenu(Couverture<String> c)  {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	/**
	 * Var.enUS: pageContentType
	 * frFR: Le type de contenu de la page. 
	 * Indexe: true
	 * Stocke: true
	 * r.enUS: requeteSite_
	 * siteRequest
	 * r.enUS: requete
	 * request
	 */
	protected void _pageTypeContenu(Couverture<String> c)  {
		c.o("text/html;charset=UTF-8");
	}

	/**
	 * Var.enUS: pageCreated
	 * Indexe: true
	 * Stocke: true
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 */
	protected void _pageCree(Couverture<LocalDateTime> c)  {   
		Date solrVal = (Date)pageDocumentSolr.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	/**
	 * Var.enUS: pageModified
	 * r.enUS: pageCree
	 * PageCreated
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 * r: pageCree
	 * r.enUS: pageCreated
	 */
	protected void _pageModifiee(Couverture<LocalDateTime> c)  {
		Date solrVal = (Date)pageDocumentSolr.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		else
			c.o(pageCree);
	}

	/**
	 * Var.enUS: pageKeywords
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _pageMotsCles(Couverture<String> c)  {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _pageDescription(Couverture<String> c)  {
	}

	/**
	 * Var.enUS: pageHomeUri
	 */
	protected void _pageAccueilUri(Couverture<String> c)  {
		c.o("/");
	}

	/**
	 * Var.enUS: pageSchoolUri
	 * String.enUS: /school
	 */
	protected void _pageEcoleUri(Couverture<String> c)  {
		c.o("/frFR/ecole");
	}

	/**
	 * Var.enUS: pageUserUri
	 * r: utilisateur
	 * r.enUS: user
	 * r: frFR
	 * r.enUS: enUS
	 */
	protected void _pageUtilisateurUri(Couverture<String> c)  {
		c.o("/utilisateur");
	}

	/**
	 * Var.enUS: pageLogoutUri
	 * r: deconnexion
	 * r.enUS: logout
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: AuthRoyaume
	 * r.enUS: AuthRealm
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 */
	protected void _pageDeconnexionUri(Couverture<String> c)  {
		try {
			ConfigSite configSite = requeteSite_.getConfigSite_();
			String o = configSite.getAuthUrl() + "/realms/" + configSite.getAuthRoyaume() + "/protocol/openid-connect/logout?redirect_uri=" + URLEncoder.encode(configSite.getSiteUrlBase() + "/deconnexion", "UTF-8");
			c.o(o);
		} catch (UnsupportedEncodingException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/**
	 * Var.enUS: listSchool
	 * r: supprime
	 * r.enUS: deleted
	 * r: archive
	 * r.enUS: archived
	 * r: Ecole
	 * r.enUS: School
	 * r: Stocker
	 * r.enUS: Store
	 */
	protected void _listeEcole(ListeRecherche<Ecole> l) {
		l.setQuery("*:*");
		l.setC(Ecole.class);
		l.setStocker(true);
		l.addFilterQuery("supprime_indexed_boolean:false");
		l.addFilterQuery("archive_indexed_boolean:false");
	}

	/**
	 * Var.enUS: schools
	 * r: listeEcole
	 * r.enUS: listSchool
	 */
	protected void _ecoles(Couverture<List<Ecole>> c) {
		c.o(listeEcole.getList());
	}

	/**
	 * Var.enUS: school_
	 * r: listeEcole
	 * r.enUS: listSchool
	 */
	protected void _ecole_(Couverture<Ecole> c) {
//		c.o(listeEcole.getList().stream().findFirst().orElse(null));
	}

	/**
	 * Var.enUS: yearVal
	 */
	protected void _anneeVal(Couverture<Integer> c) {
		c.o(LocalDate.now().getYear());
	}

	/**
	 * r: pageMotsCles
	 * r.enUS: pageKeywords
	 * r: pageTitre
	 * r.enUS: pageTitle
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: NomDomaine
	 * r.enUS: DomainName
	 * r: pageImageLargeur
	 * r.enUS: pageImageWidth
	 * r: pageImageHauteur
	 * r.enUS: pageImageHeight
	 * r: pageImageTypeContenu
	 * r.enUS: pageImageContentType
	 */
	@Override public void htmlMeta() {
		e("meta").a("charset", "UTF-8").fg();
		e("meta").a("name", "viewport").a("content", "width=device-width, initial-scale=1").fg();
		e("meta").a("name", "keywords").a("content", pageMotsCles).fg();
		e("meta").a("property", "og:title").a("content", pageTitre).fg();
		e("meta").a("property", "og:description").a("content", pageDescription).fg();
		e("meta").a("property", "og:url").a("content", pageUrl).fg();
		e("meta").a("property", "og:site_name").a("content", requeteSite_.getConfigSite_().getNomDomaine()).fg();
		e("meta").a("property", "og:image").a("content", pageImageUrl).fg();
		e("meta").a("property", "og:image:width").a("content", pageImageLargeur).fg();
		e("meta").a("property", "og:image:height").a("content", pageImageHauteur).fg();
		e("meta").a("property", "og:image:type").a("content", pageImageTypeContenu).fg();
		e("meta").a("property", "og:image:alt").a("content", pageTitre).fg();
		e("meta").a("property", "og:type").a("content", "article").fg();
		e("meta").a("name", "twitter:card").a("content", "summary_large_image").fg();
		e("meta").a("name", "twitter:site").a("content", "@computateorg").fg();
		e("meta").a("name", "twitter:creator").a("content", "@computateorg").fg();
		e("meta").a("name", "twitter:title").a("content", pageTitre).fg();
		e("meta").a("name", "twitter:description").a("content", pageDescription).fg();
		e("meta").a("name", "twitter:image").a("content", pageImageUrl).fg();
		e("meta").a("name", "description").a("content", pageDescription).fg();
	}

	/**
	 * Var.enUS: htmlScriptsPageLayout
	 * r: statiqueUrlBase
	 * r.enUS: staticBaseUrl
	 * r: /frFR/UtilisateurSitePage.js
	 * r.enUS: /enUS/SiteUserPage.js
	 * r: /frFR/EcolePage.js
	 * r.enUS: /enUS/SchoolPage.js
	 * r: /frFR/AnneePage.js
	 * r.enUS: /enUS/YearPage.js
	 * r: /frFR/SaisonPage.js
	 * r.enUS: /enUS/SeasonPage.js
	 * r: /frFR/SessionPage.js
	 * r.enUS: /enUS/SessionPage.js
	 * r: /frFR/AgePage.js
	 * r.enUS: /enUS/AgePage.js
	 * r: /frFR/BlocPage.js
	 * r.enUS: /enUS/BlockPage.js
	 * r: /frFR/InscriptionPage.js
	 * r.enUS: /enUS/EnrollmentPage.js
	 * r: /frFR/PaiementPage.js
	 * r.enUS: /enUS/PaymentPage.js
	 * r: /frFR/MerePage.js
	 * r.enUS: /enUS/MomPage.js
	 * r: /frFR/PerePage.js
	 * r.enUS: /enUS/DadPage.js
	 * r: /frFR/GardienPage.js
	 * r.enUS: /enUS/GuardianPage.js
	 * r: /frFR/EnfantPage.js
	 * r.enUS: /enUS/ChildPage.js
	 * r: frFR
	 * r.enUS: enUS
	 */
	@Override public void htmlScriptsMiseEnPage() {
		e("script").a("src", statiqueUrlBase, "/js/jquery-1.12.4.min.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/site-frFR.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/sockjs.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/vertx-eventbus.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/UtilisateurSitePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EcolePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
//		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
//		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AgePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/MerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/GardienPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/moment.min.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/jqDatePicker.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/jquery.serialize-object.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/jSignature.min.js").f().g("script");
		e("script").a("src", "https://kit.fontawesome.com/59d19567d5.js").f().g("script");
	}

	/**
	 * Var.enUS: htmlScriptPageLayout
	 */
	@Override public void htmlScriptMiseEnPage() {
	}

	/**
	 * Var.enUS: htmlStylesPageLayout
	 * r: statiqueUrlBase
	 * r.enUS: staticBaseUrl
	 * r: frFR
	 * r.enUS: enUS
	 */
	@Override public void htmlStylesMiseEnPage() {
		e("link").a("rel", "stylesheet").a("href", statiqueUrlBase, "/css/w3.css").fg();
		e("link").a("rel", "stylesheet").a("href", statiqueUrlBase, "/css/site-frFR.css").fg();
		e("link").a("rel", "stylesheet").a("href", statiqueUrlBase, "/css/datePicker.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "https://www.w3schools.com/w3css/4/w3.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "/static/css/site-frFR.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "/static/css/datePicker.css").fg();
		e("link").a("rel", "stylesheet").a("href", "https://fonts.googleapis.com/css?family=Khand").fg();
//		e("link").a("rel", "stylesheet").a("href", "https://pro.fontawesome.com/releases/v5.9.0/css/all.css").a("integrity", "sha384-vlOMx0hKjUCl4WzuhIhSNZSm2yQCaf0mOU1hEDK/iztH3gU4v5NMmJln9273A6Jz").a("crossorigin", "anonymous").fg();
	}

	/**
	 * Var.enUS: htmlStylePageLayout
	 */
	@Override public void htmlStyleMiseEnPage() {
	}

	/**
	 * Var.enUS: htmlBodyPageLayout
	 */
	@Override public void htmlBodyMiseEnPage() {
	}

	/**
	 * Var.enUS: htmlPageLayout
	 * r: pageTitre
	 * r.enUS: pageTitle
	 * r: Ce site est open source
	 * r.enUS: This site is open source
	 * r: Voir le code source ici
	 * r.enUS: View the source code here
	 * r: statiqueUrlBase
	 * r.enUS: staticBaseUrl
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: EcoleNumeroTelephone
	 * r.enUS: SchoolPhoneNumber
	 * r: EcoleMailDe
	 * r.enUS: SchoolEmailFrom
	 * r: EcoleEmplacement
	 * r.enUS: SchoolLocation
	 * r: Ecole
	 * r.enUS: School
	 * r: ecole
	 * r.enUS: school
	 */
	@Override public void htmlMiseEnPage() {
		e("html").a("style", "height: 100%; ").a("xmlns:xlink", "http://www.w3.org/1999/xlink").a("xmlns", "http://www.w3.org/1999/xhtml").a("xmlns:fb", "http://ogp.me/ns/fb#").f();
			e("head").f();
				e("title").f();
					sx(pageTitre);
				g("title");
				htmlMeta();
				htmlScripts();
				e("script").f().l("/*<![CDATA[*/");
				htmlScript();
				s("/*]]>*/").g("script");
				htmlStyles();
				e("style").f().l("/*<![CDATA[*/");
				htmlStyle();
				s("/*]]>*/").g("style");
	
			g("head");
			e("body").a("style", "height: 100%; ").a("class", "w3-light-grey ").f(); 
				e("a").a("name", "top").f().g("a");
				e("div").a("class", "top-box w3-top ").f();
				g("div");
				e("div").a("id", "modaleErreur").a("class", "w3-modal").a("onclick", "this.style.display = 'none';").f();
					e("div").a("class", "w3-modal-content w3-animate-zoom").f();
						e("header").a("class", "w3-container w3-center w3-red ").f();
							e("h3").f();
								sx("Une erreur s'est produite. ");
							g("h3");
						g("header");
					g("div");
					e("div").a("id", "modaleErreurMessage").a("class", "w3-container w3-center").f().g("div");
					e("header").a("class", "w3-container w3-center w3-padding-16 ").f();
						sx("L'erreur a été envoyée par e-mail à l'administrateur pour analyse. ");
					g("header");
				g("div");
				e("div").a("class", "w3-content w3-black ").f();
					menu("Menu1");
				g("div");
				e("div").a("class", "w3-content ").f();

					htmlBody();

				g("div");
				e("div").a("class", "w3-row site-section-contact ").f();
					e("div").a("class", "w3-content w3-center  w3-cell-row w3-margin-bottom-32 ").f();
						menu("Menu2");
						e("div").a("class", "w3-container ").f();
							e("div").a("class", "w3-container w3-text-black w3-margin-top ").f();
								e("h6").a("id", "h2-contactez-nous").a("class",  "w3-padding w3-xlarge w3-text-white ").f();
									sx("Let's get connected. ");
								g("h6");
								e("div").a("class", "w3-cell-row ").f();
									e("div").a("class", "w3-cell ").f();
										e("a").a("target", "_blank").a("rel", "noopener noreferrer").a("data-ajax", "false").a("href", "https://www.facebook.com/littleorchardpreschool/").f();
											e("img").a("alt", "").a("class", "grow-30 ").a("style", "display: inline-block; width: 50px; height: 50px; margin: 0 10px;").a("src", statiqueUrlBase, "/svg/facebook.svg").fg();
										g("a");
									g("div");
								g("div");
								e("div").a("class", "w3-cell-row w3-text-white ").f();
									for(int i = 0; i < ecoles.size(); i++) {
										Ecole ecole = ecoles.get(i);

										e("div").a("class", "w3-cell ").f();
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx(ecole.getEcoleEmplacement()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Address: ").g("span");
												e("span").f().sx(ecole.getEcoleAddresse()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Phone: ").g("span");
												e("span").f().sx(ecole.getEcoleNumeroTelephone()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Email: ").g("span");
												e("span").f().sx(ecole.getEcoleMailDe()).g("span");
											g("div");
										g("div");
										if(i == 2)
											break;
									}
								g("div");
								e("h6").a("class",  "w3-padding w3-large w3-text-white ").f();
									e("a").a("href", "#top").f();
										sx("Up to the top. ");
									g("a");
								g("h6");
							g("div");
						g("div");
						e("footer").a("class", "w3-center w3-padding-24 w3-margin-top ").f();
							e("div").f();
								e("a").a("href", "https://github.com/computate/computate-scolaire").a("class", "w3-margin w3-small w3-text-white ").f();
									sx("Ce site est open source.");
								g("a");
								e("a").a("href", "https://www.openshift.com/").a("class", "grow-20 w3-margin w3-text-white w3-small ").a("target", "_blank").f();
									sx("Powered by ");
									e("img").a("alt", "").a("class", "w3-image ").a("style", "display: inline-block; width: 200px; margin: 0 10px;").a("src", statiqueUrlBase, "/svg/openshift.svg").fg();
								g("a");
							g("div");
						g("footer");
					g("div");
				g("div");
			g("body");
		g("html");

	}

	/** 
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: UtilisateurCle
	 * r.enUS: UserKey
	 * r: voir archivé
	 * r.enUS: see archived
	 * r: voirArchive
	 * r.enUS: seeArchived
	 * r: VoirArchive
	 * r.enUS: SeeArchived
	 * r: voir supprimé
	 * r.enUS: see deleted
	 * r: voirSupprime
	 * r.enUS: seeDeleted
	 * r: VoirSupprime
	 * r.enUS: SeeDeleted
	 * r: langue
	 * r.enUS: language
	 * r: ecrireConnecterDeconnecter
	 * r.enUS: writeLoginLogout
	 * r: accueil
	 * r.enUS: home
	 * r: à propos
	 * r.enUS: about
	 * r: se connecter
	 * r.enUS: login
	 * r: se déconnecter
	 * r.enUS: logout
	 * r: statiqueUrlBase
	 * r.enUS: staticBaseUrl
	 * r: pageAccueilUri
	 * r.enUS: pageHomeUri
	 * r: pageUtilisateurUri
	 * r.enUS: pageUserUri
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * r: pageDeconnexionUri
	 * r.enUS: pageLogoutUri
	 * r: "Accueil"
	 * r.enUS: "Home"
	 * r: "Déconnexion"
	 * r.enUS: "Logout"
	 * r: "Connexion"
	 * r.enUS: "Login/Signup"
	 * r: "écoles"
	 * r.enUS: "schools"
	 * r: "années"
	 * r.enUS: "years"
	 * r: "saisons"
	 * r.enUS: "seasons"
	 * r: "sessions"
	 * r.enUS: "sessions"
	 * r: "âges"
	 * r.enUS: "ages"
	 * r: "blocs"
	 * r.enUS: "blocks"
	 * r: "inscriptions"
	 * r.enUS: "enrollments"
	 * r: "paiements"
	 * r.enUS: "payments"
	 * r: "mères"
	 * r.enUS: "moms"
	 * r: "pères"
	 * r.enUS: "dads"
	 * r: "gardiens"
	 * r.enUS: "guardians"
	 * r: "enfants"
	 * r.enUS: "children"
	 * r: "designs"
	 * r.enUS: "designs"
	 * r: "/utilisateur/"
	 * r.enUS: "/user/"
	 * r: "ma page utilisateur"
	 * r.enUS: "my user page"
	 * r: UtilisateurRolesRessource
	 * r.enUS: UserResourceRoles
	 * r: UtilisateurRolesRoyaume
	 * r.enUS: UserRealmRoles
	 * r: EcoleGenPage.htmlSuggereEcoleGenPage
	 * r.enUS: SchoolGenPage.htmlSuggestedSchoolGenPage
	 * r: AnneeGenPage.htmlSuggereAnneeGenPage
	 * r.enUS: YearGenPage.htmlSuggestedYearGenPage
	 * r: SaisonGenPage.htmlSuggereSaisonGenPage
	 * r.enUS: SeasonGenPage.htmlSuggestedSeasonGenPage
	 * r: SessionGenPage.htmlSuggereSessionGenPage
	 * r.enUS: SessionGenPage.htmlSuggestedSessionGenPage
	 * r: AgeGenPage.htmlSuggereAgeGenPage
	 * r.enUS: AgeGenPage.htmlSuggestedAgeGenPage
	 * r: BlocGenPage.htmlSuggereBlocGenPage
	 * r.enUS: BlockGenPage.htmlSuggestedBlockGenPage
	 * r: InscriptionGenPage.htmlSuggereInscriptionGenPage
	 * r.enUS: EnrollmentGenPage.htmlSuggestedEnrollmentGenPage
	 * r: PaiementGenPage.htmlSuggerePaiementGenPage
	 * r.enUS: PaymentGenPage.htmlSuggestedPaymentGenPage
	 * r: MereGenPage.htmlSuggereMereGenPage
	 * r.enUS: MomGenPage.htmlSuggestedMomGenPage
	 * r: PereGenPage.htmlSuggerePereGenPage
	 * r.enUS: DadGenPage.htmlSuggestedDadGenPage
	 * r: GardienGenPage.htmlSuggereGardienGenPage
	 * r.enUS: GuardianGenPage.htmlSuggestedGuardianGenPage
	 * r: EnfantGenPage.htmlSuggereEnfantGenPage
	 * r.enUS: ChildGenPage.htmlSuggestedChildGenPage
	 * r: DesignPageGenPage.htmlSuggereDesignPageGenPage
	 * r.enUS: PageDesignGenPage.htmlSuggestedPageDesignGenPage
	 * r: PartHtmlGenPage.htmlSuggerePartHtmlGenPage
	 * r.enUS: HtmlPartGenPage.htmlSuggestedHtmlPartGenPage
	 */ 
	public void menu(String id)  {
		e("div").a("class", "w3-bar w3-text-white w3-padding-bottom-8 w3-padding-top-8 ").a("style", "padding-left: 16px; padding-right: 16px; ").f();

			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "").a("href", pageAccueilUri).f();
					e("span").a("class", "site-menu-item").f();
						sx("Accueil");
					g("span");
				g("a");
			g("div");

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES_MANAGER)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES_MANAGER)
					) {

				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-pink ").f();
							e("i").a("class", "far fa-school ").f().g("i");
							sx("écoles");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						EcoleGenPage.htmlSuggereEcoleGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-orange ").f();
							e("i").a("class", "far fa-calendar-check ").f().g("i");
							sx("années");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						AnneeGenPage.htmlSuggereAnneeGenPage(this, id, null);
					} g("div");
				} g("div");
//	
//				{ e("div").a("class", "w3-dropdown-hover ").f();
//					{ e("div").a("class", "w3-button w3-hover-yellow ").f();
//							e("i").a("class", "far fa-sun ").f().g("i");
//							sx("saisons");
//					} g("div");
//					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
//						SaisonGenPage.htmlSuggereSaisonGenPage(this, id, null);
//					} g("div");
//				} g("div");
//	
//				{ e("div").a("class", "w3-dropdown-hover ").f();
//					{ e("div").a("class", "w3-button w3-hover-green ").f();
//							e("i").a("class", "fad fa-graduation-cap ").f().g("i");
//							sx("sessions");
//					} g("div");
//					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
//						SessionGenPage.htmlSuggereSessionGenPage(this, id, null);
//					} g("div");
//				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-blue ").f();
							e("i").a("class", "fad fa-birthday-cake ").f().g("i");
							sx("âges");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						AgeGenPage.htmlSuggereAgeGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-indigo ").f();
							e("i").a("class", "far fa-bell ").f().g("i");
							sx("blocs");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						BlocGenPage.htmlSuggereBlocGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-orange ").f();
							e("i").a("class", "far fa-child ").f().g("i");
							sx("enfants");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						EnfantGenPage.htmlSuggereEnfantGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-pink ").f();
							e("i").a("class", "far fa-female ").f().g("i");
							sx("mères");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						MereGenPage.htmlSuggereMereGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-light-blue ").f();
							e("i").a("class", "far fa-male ").f().g("i");
							sx("pères");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						PereGenPage.htmlSuggerePereGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-yellow ").f();
							e("i").a("class", "far fa-phone ").f().g("i");
							sx("gardiens");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						GardienGenPage.htmlSuggereGardienGenPage(this, id, null);
					} g("div");
				} g("div");
			}

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-purple ").f();
						e("i").a("class", "fas fa-edit ").f().g("i");
						sx("inscriptions");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					InscriptionGenPage.htmlSuggereInscriptionGenPage(this, id, null);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "fas fa-search-dollar ").f().g("i");
						sx("paiements");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					PaiementGenPage.htmlSuggerePaiementGenPage(this, id, null);
				} g("div");
			} g("div");

			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES_ADMIN)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES_ADMIN)
					) {
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-khaki ").f();
							e("i").a("class", "far fa-drafting-compass ").f().g("i");
							sx("designs");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						DesignPageGenPage.htmlSuggereDesignPageGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-khaki ").f();
							e("i").a("class", "far fa-puzzle-piece ").f().g("i");
							sx("designs");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						PartHtmlGenPage.htmlSuggerePartHtmlGenPage(this, id, null);
					} g("div");
				} g("div");
			}
			ecrireConnecterDeconnecter();
		g("div");
	} 

	/**
	 * Var.enUS: writeLoginLogout
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: pageUtilisateurUri
	 * r.enUS: pageUserUri
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: UtilisateurCle
	 * r.enUS: UserKey
	 * r: voir archivé
	 * r.enUS: see archived
	 * r: voirArchive
	 * r.enUS: seeArchived
	 * r: VoirArchive
	 * r.enUS: SeeArchived
	 * r: voir supprimé
	 * r.enUS: see deleted
	 * r: voirSupprime
	 * r.enUS: seeDeleted
	 * r: VoirSupprime
	 * r.enUS: SeeDeleted
	 * r: langue
	 * r.enUS: language
	 * r: ecrireConnecterDeconnecter
	 * r.enUS: writeLoginLogout
	 * r: accueil
	 * r.enUS: home
	 * r: à propos
	 * r.enUS: about
	 * r: se connecter
	 * r.enUS: login
	 * r: se déconnecter
	 * r.enUS: logout
	 * r: statiqueUrlBase
	 * r.enUS: staticBaseUrl
	 * r: pageAccueilUri
	 * r.enUS: pageHomeUri
	 * r: pageDeconnexionUri
	 * r.enUS: pageLogoutUri
	 * r: "Accueil"
	 * r.enUS: "Home"
	 * r: "Déconnexion"
	 * r.enUS: "Logout"
	 * r: "Connexion"
	 * r.enUS: "Login"
	 * r: "écoles"
	 * r.enUS: "schools"
	 * r: "années"
	 * r.enUS: "years"
	 * r: "saisons"
	 * r.enUS: "seasons"
	 * r: "sessions"
	 * r.enUS: "sessions"
	 * r: "âges"
	 * r.enUS: "ages"
	 * r: "blocs"
	 * r.enUS: "blocks"
	 * r: "inscriptions"
	 * r.enUS: "enrollments"
	 * r: "paiements"
	 * r.enUS: "payments"
	 * r: "mères"
	 * r.enUS: "moms"
	 * r: "pères"
	 * r.enUS: "dads"
	 * r: "gardiens"
	 * r.enUS: "guardians"
	 * r: "enfants"
	 * r.enUS: "children"
	 * r: "designs"
	 * r.enUS: "designs"
	 * r: "/utilisateur/"
	 * r.enUS: "/user/"
	 * r: "ma page utilisateur"
	 * r.enUS: "my user page"
	 * r: UtilisateurRolesRessource
	 * r.enUS: UserResourceRoles
	 * r: UtilisateurRolesRoyaume
	 * r.enUS: UserRealmRoles
	 * r: EcoleGenPage.htmlSuggereEcoleGenPage
	 * r.enUS: SchoolGenPage.htmlSuggestedSchoolGenPage
	 * r: AnneeGenPage.htmlSuggereAnneeGenPage
	 * r.enUS: YearGenPage.htmlSuggestedYearGenPage
	 * r: SaisonGenPage.htmlSuggereSaisonGenPage
	 * r.enUS: SeasonGenPage.htmlSuggestedSeasonGenPage
	 * r: SessionGenPage.htmlSuggereSessionGenPage
	 * r.enUS: SessionGenPage.htmlSuggestedSessionGenPage
	 * r: AgeGenPage.htmlSuggereAgeGenPage
	 * r.enUS: AgeGenPage.htmlSuggestedAgeGenPage
	 * r: BlocGenPage.htmlSuggereBlocGenPage
	 * r.enUS: BlockGenPage.htmlSuggestedBlockGenPage
	 * r: InscriptionGenPage.htmlSuggereInscriptionGenPage
	 * r.enUS: EnrollmentGenPage.htmlSuggestedEnrollmentGenPage
	 * r: PaiementGenPage.htmlSuggerePaiementGenPage
	 * r.enUS: PaymentGenPage.htmlSuggestedPaymentGenPage
	 * r: MereGenPage.htmlSuggereMereGenPage
	 * r.enUS: MomGenPage.htmlSuggestedMomGenPage
	 * r: PereGenPage.htmlSuggerePereGenPage
	 * r.enUS: DadGenPage.htmlSuggestedDadGenPage
	 * r: GardienGenPage.htmlSuggereGardienGenPage
	 * r.enUS: GuardianGenPage.htmlSuggestedGuardianGenPage
	 * r: EnfantGenPage.htmlSuggereEnfantGenPage
	 * r.enUS: ChildGenPage.htmlSuggestedChildGenPage
	 * r: DesignPageGenPage.htmlSuggereDesignPageGenPage
	 * r.enUS: PageDesignGenPage.htmlSuggestedPageDesignGenPage
	 * r: PartHtmlGenPage.htmlSuggerePartHtmlGenPage
	 * r.enUS: HtmlPartGenPage.htmlSuggestedHtmlPartGenPage
	 * r: xmlPile
	 * r.enUS: xmlStack
	 * r: XmlPile
	 * r.enUS: XmlStack
	 */
	public void ecrireConnecterDeconnecter() {
		Stack<String> xmlPile = requeteSite_.getXmlPile();
		boolean empty = xmlPile.isEmpty();
		if(empty) {
			xmlPile.push("html");
			xmlPile.push("body");
			xmlPile.push("div");
			xmlPile.push("div");
			xmlPile.push("div");
			
		}

		if(requeteSite_.getUtilisateurId() == null) {
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "w3-padding ").a("href", pageUtilisateurUri).f(); 
					e("span").a("class", "site-menu-item").f();
						e("i").a("class", "far fa-sign-in-alt ").f().g("i");
						sx("Connexion");
					g("span");
				g("a");
			g("div");
		}
		if(requeteSite_.getUtilisateurId() != null) {

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "far fa-user-cog ").f().g("i");
						sx(requeteSite_.getUtilisateurNom());
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					UtilisateurSite o = requeteSite_.getUtilisateurSite();
					{ e("div").a("class", "w3-cell-row ").f();
						{ e("a").a("href", "/utilisateur/", requeteSite_.getUtilisateurCle()).a("class", "").f();
							e("i").a("class", "far fa-user ").f().g("i");
							sx("ma page utilisateur");
						} g("a");
					} g("div");
					{ e("div").a("class", "w3-cell-row ").f();
						e("label").a("for", "Page_voirArchive").a("class", "").f().sx("voir archivé").g("label");
						e("input")
							.a("type", "checkbox")
							.a("value", "true")
							.a("class", "setVoirArchive")
							.a("name", "setVoirArchive")
							.a("id", "Page_voirArchive")
							.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + ", requeteSite_.getUtilisateurCle(), " }], 'setVoirArchive', $(this).prop('checked'), function() { addGlow($('#Page_voirArchive')); }, function() { addError($('#Page_voirArchive')); }); ")
							;
							if(o.getVoirArchive() != null && o.getVoirArchive())
								a("checked", "checked");
						fg();
					} g("div");
					{ e("div").a("class", "w3-cell-row ").f();
						e("label").a("for", "Page_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
						e("input")
							.a("type", "checkbox")
							.a("value", "true")
							.a("class", "setVoirSupprime")
							.a("name", "setVoirSupprime")
							.a("id", "Page_voirSupprime")
							.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + ", requeteSite_.getUtilisateurCle(), " }], 'setVoirSupprime', $(this).prop('checked'), function() { addGlow($('#Page_voirSupprime')); }, function() { addError($('#Page_voirSupprime')); }); ")
							;
							if(o.getVoirSupprime() != null && o.getVoirSupprime())
								a("checked", "checked");
						fg();
					} g("div");
				} g("div");
			} g("div");
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "w3-padding ").a("href", pageDeconnexionUri).f();
					e("span").a("class", "site-menu-item").f();
						e("i").a("class", "far fa-sign-out-alt ").f().g("i");
						sx("Déconnexion");
					g("span");
				g("a");
			g("div");
		}

		if(empty) {
			xmlPile.clear();
		}
	}

	/**  
	 * var.enUS: sharePage
	 * remplacer.enUS: toutXml
	 * allXml
	 */
	public void partagerPage() {
		{ e("div").a("class", "w3-content w3-center w3-padding-top-32 ").f();
			e("h3").f().sx("Share this story. ").g("h3");
			{ e("div").a("class", "w3-cell-row ").f();
				{ e("div").a("class", "blog-publication-social-div w3-cell ").f();
					e("img").a("class", "blog-publication-social-img").a("src", "https://www.computate.org/svg/facebook.svg").fg();
					{ e("div").f();
						e("div").a("class", "fb-like").a("data-href", pageUrl).a("data-layout", "box_count").a("data-action", "like").a("data-size", "small").a("data-show-faces", "true").a("data-share", "true").f().g("div");
					} g("div");
				} g("div");
				{ e("div").a("class", "blog-publication-social-div w3-cell ").f();
					e("img").a("class", "blog-publication-social-img").a("src", "https://www.computate.org/svg/googleplus.svg").fg();
					{ e("div").f();
						e("a").a("class", "blog-publication-social-a").a("href", "https://plus.google.com/b/118400712985074754853/118400712985074754853").f().g("a");
						e("div").f();
							e("div").a("class", "g-plusone").a("data-size", "tall").f().g("div");
						g("div");
					} g("div");
				} g("div");
				{ e("div").a("class", "blog-publication-social-div w3-cell ").f();
					e("img").a("class", "blog-publication-social-img").a("src", "https://www.computate.org/svg/twitter.svg").fg();
					{ e("div").f();
						e("a").a("href", "https://twitter.com/share").a("class", "twitter-share-button ").a("data-show-count", "false").f().g("a");
						e("script").a("async", "").a("charset", "utf-8").a("src", "//platform.twitter.com/widgets.js").f().g("script");
					} g("div");
				}g("div");
				{ e("div").a("class", "blog-publication-social-div w3-cell ").f();
					e("img").a("class", "blog-publication-social-img").a("src", "https://www.computate.org/svg/pinterest.svg").fg();
					{ e("div").a("class",  "pinterest-div ").f();
						{ e("a");
							a("data-pin-media", pageImageUrl);
							a("data-pin-description", pageDescription);
							a("data-pin-url", pageUrl);
							a("data-pin-id", "");
							a("href", "https://www.pinterest.com/pin/create/button/");
							a("data-pin-do", "buttonPin");
							f();
								e("img").a("src", "//assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png").fg();
						} g("a");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/**
	 * r: FORMATDateHeureCourt
	 * r.enUS: FORMATDateTimeShort
	 */
	public String formaterDateHeureCourt(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateHeureCourt.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	/**
	 * r: FORMATDateCourt
	 * r.enUS: FORMATDateShort
	 */
	public String formaterDateCourt(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateCourt.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	/**
	 * r: FORMATDateHeureAffichage
	 * r.enUS: FORMATDateTimeDisplay
	 */
	public String formaterDateAffichage(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateHeureAffichage.format(date.toInstant());
		}
		return resultat;
	}

	/**
	 * r: FORMATDateHeureAffichage
	 * r.enUS: FORMATDateTimeDisplay
	 */
	public String formaterDateAffichage(LocalDateTime date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateHeureAffichage.format(date);
		}
		return resultat;
	}

	/**
	 * Param1.var.enUS: localName
	 * r: nomLocal
	 * r.enUS: localName
	 * r: xmlPile
	 * r.enUS: xmlStack
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: tabulation
	 * r.enUS: tab
	 * r: Echappes
	 * r.enUS: Escaped
	 * r: ecrivain
	 * r.enUS: writer
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 */
	public MiseEnPage e(String nomLocal) {
		String nomLocalParent = requeteSite_.getXmlPile().isEmpty() ? null : requeteSite_.getXmlPile().peek();

		boolean eNoWrapParent = nomLocalParent == null || HTML_ELEMENTS_NO_WRAP.contains(nomLocalParent);
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "  "));

		requeteSite_.getXmlPile().push(nomLocal);
		if(StringUtils.equals(nomLocal, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabulations.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("<");
		w.s(nomLocal);
		
		return this;
	}

	/**
	 * Param1.var.enUS: attributeName
	 * Param2.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: echapperDansCitatations
	 * r.enUS: escapeInQuotes
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage a1(String nomAttribut, Object...objets) {
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		
		return this;
	}

	/**  
	 * Param1.var.enUS: attributeName
	 * Param2.var.enUS: objects
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: objet
	 * r.enUS: object
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: echapperDansCitatations
	 * r.enUS: escapeInQuotes
	 */
	public MiseEnPage a(String nomAttribut, Object...objets) {
		w.s(" ");
		w.s(nomAttribut);
		w.s("=\"");
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapperDansCitatations(s));
			}
		}
		w.s("\"");
		
		return this;
	}

	/**  
	 */
	public MiseEnPage a2() {
		w.s("\"");
		
		return this;
	}

	/** 
	 */
	public MiseEnPage f() {
		w.s(">");
		
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage s(Object...objets) {
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("  ");
		s(objets);
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("  ");
		s(objets);
		s("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage lx(Object...objets) {
		s(objets);
		sx("\n");
		return this;
	}

	/** 
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 * r: OutilXml
	 * r.enUS: UtilXml
	 * r: echapper
	 * r.enUS: escape
	 */
	public MiseEnPage sx(Object...objets) {
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapper(s));
			}
		}
		
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage tx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("  ");
		sx(objets);
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objet
	 * r.enUS: object
	 */
	public MiseEnPage tlx(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("  ");
		sx(objets);
		sx("\n");
		return this;
	}

	/**
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 */
	public MiseEnPage fg() {
		w.s("/>");
		requeteSite_.getXmlPile().pop();
		
		return this;
	}

	/**    
	 * Param1.var.enUS: localName
	 * r: requeteSite_
	 * r.enUS: siteRequest_
	 * r: getXmlPile
	 * r.enUS: getXmlStack
	 * r: nomLocalParent
	 * r.enUS: localNameParent
	 * r: tabulations
	 * r.enUS: tabs
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: nomLocal
	 * r.enUS: localName
	 */
	public MiseEnPage g(String nomLocal) {
		String nomLocalParent = requeteSite_.getXmlPile().peek();
		boolean eNoWrap = nomLocalParent == null || HTML_ELEMENTS_NO_WRAP.contains(nomLocal);

		requeteSite_.getXmlPile().pop();
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "  "));

		if(!eNoWrap && !tabulations.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("</");
		w.s(nomLocal);
		w.s(">");
		
		return this;
	}

	/**
	 * Param1.var.enUS: pageContentType
	 * r: pageTypeContenu
	 * r.enUS: pageContentType
	 * r: obtenirPourClasse
	 * r.enUS: obtainForClass
	 * r: pageTypeContenu
	 * r.enUS: pageContentType
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequetePk
	 * r.enUS: RequestPk
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: StatiqueUrlBase
	 * r.enUS: StaticBaseUrl
	 * r: ecrireConnecterDeconnecter
	 * r.enUS: writeLoginLogout
	 */
	public Integer htmlPageLayout2(String pageTypeContenu, List<HtmlPart> htmlPartList, HtmlPart htmlPartParent, Integer start, Integer size) {

		Integer i;

		Double parentSort1 = null;
		Double parentSort2 = null;
		Double parentSort3 = null;
		Double parentSort4 = null;
		Double parentSort5 = null;
		Double parentSort6 = null;
		Double parentSort7 = null;
		Double parentSort8 = null;
		Double parentSort9 = null;
		Double parentSort10 = null;

		if(htmlPartParent != null) {
			parentSort1 = htmlPartParent.getSort1();
			parentSort2 = htmlPartParent.getSort2();
			parentSort3 = htmlPartParent.getSort3();
			parentSort4 = htmlPartParent.getSort4();
			parentSort5 = htmlPartParent.getSort5();
			parentSort6 = htmlPartParent.getSort6();
			parentSort7 = htmlPartParent.getSort7();
			parentSort8 = htmlPartParent.getSort8();
			parentSort9 = htmlPartParent.getSort9();
			parentSort10 = htmlPartParent.getSort10();
		}

		for(i = Math.abs(start); i < size; i++) {
			HtmlPart htmlPart = htmlPartList.get(i);

			Double sort1 = htmlPart.getSort1();
			Double sort2 = htmlPart.getSort2();
			Double sort3 = htmlPart.getSort3();
			Double sort4 = htmlPart.getSort4();
			Double sort5 = htmlPart.getSort5();
			Double sort6 = htmlPart.getSort6();
			Double sort7 = htmlPart.getSort7();
			Double sort8 = htmlPart.getSort8();
			Double sort9 = htmlPart.getSort9();
			Double sort10 = htmlPart.getSort10();

			if(htmlPartParent != null) {
				if(parentSort2 != null && (sort1 == null || parentSort1.compareTo(sort1) != 0))
					return i;
				if(parentSort3 != null && (sort2 == null || parentSort2.compareTo(sort2) != 0))
					return i;
				if(parentSort4 != null && (sort3 == null || parentSort3.compareTo(sort3) != 0))
					return i;
				if(parentSort5 != null && (sort4 == null || parentSort4.compareTo(sort4) != 0))
					return i;
				if(parentSort6 != null && (sort5 == null || parentSort5.compareTo(sort5) != 0))
					return i;
				if(parentSort7 != null && (sort6 == null || parentSort6.compareTo(sort6) != 0))
					return i;
				if(parentSort8 != null && (sort7 == null || parentSort7.compareTo(sort7) != 0))
					return i;
				if(parentSort9 != null && (sort8 == null || parentSort8.compareTo(sort8) != 0))
					return i;
				if(parentSort10 != null && (sort9 == null || parentSort9.compareTo(sort9) != 0))
					return i;
			}

			if(start >= 0) {
	
				String htmlVar = htmlPart.getHtmlVar();
				String htmlVarSpan = htmlPart.getHtmlVarSpan();
				String htmlVarInput = htmlPart.getHtmlVarInput();
				String htmlVarForm = htmlPart.getHtmlVarForm();
				String htmlVarForEach = htmlPart.getHtmlVarForEach();
				Boolean pdfExclude = htmlPart.getPdfExclude();
				Boolean htmlExclude = htmlPart.getHtmlExclude();

				if(htmlVarSpan != null)
					htmlVar = htmlVarSpan;
	
				if(
						"application/pdf".equals(pageTypeContenu) && BooleanUtils.isNotTrue(pdfExclude)
						|| !"application/pdf".equals(pageTypeContenu) && BooleanUtils.isNotTrue(htmlExclude)
						) {
					s(htmlPart.getHtmlBefore());
					if(htmlVar != null) {
	
						Object parent = StringUtils.contains(htmlVar, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVar, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVar, ".");
						if(StringUtils.isBlank(var))
							var = htmlVar;
	
						if(htmlVarForEach != null) {
		
							Object parentForEach = StringUtils.contains(htmlVarForEach, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarForEach, ".")) : null;
							if(parentForEach == null)
								parentForEach = this;
							String varForEach = StringUtils.substringAfterLast(htmlVarForEach, ".");
							if(StringUtils.isBlank(varForEach))
								varForEach = htmlVarForEach;
		
							try {
								Collection<?> collection = (Collection<?>)MethodUtils.invokeMethod(parentForEach, "get" + StringUtils.capitalize(varForEach), new Object[] {});
								List<?> list = collection.stream().collect(Collectors.toList());
								Integer forStart = i + 1;
		
								for(Object o : list) {
									try {
										MethodUtils.invokeExactMethod(parent, "set" + StringUtils.capitalize(var), o);
										i = htmlPageLayout2(pageTypeContenu, htmlPartList, htmlPart, forStart, size);
									} catch (RuntimeException e) {
										throw e;
									} catch (Exception e) {
										throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "set" + StringUtils.capitalize(var), htmlVar, parent), e);
									}
								}
								if(list.size() == 0) {
									i = htmlPageLayout2(pageTypeContenu, htmlPartList, htmlPart, -forStart, size);
								}
								i = i - 1;
							} catch (RuntimeException e) {
								throw e;
							} catch (Exception e) {
								throw new RuntimeException(String.format("Could not call method %s of object: %s", "get" + StringUtils.capitalize(varForEach), parentForEach), e);
							}
						}
						else {
							try {
								String s = (String)MethodUtils.invokeExactMethod(parent, "str" + StringUtils.capitalize(var));
								if(htmlVarSpan != null) {
									Long pk = (Long)MethodUtils.invokeExactMethod(parent, "getPk");
									e("span").a("class", "var", parent.getClass().getSimpleName(), pk, StringUtils.capitalize(var), " ").f().s(s).g("span");
								}
								else {
									sx(s);
								}
							} catch (Exception e) {
								sx(obtenirPourClasse(htmlVar));
							}
						}
					}
					if(htmlVarForm != null) {
						Object parent = StringUtils.contains(htmlVarForm, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarForm, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVarForm, ".");
						if(StringUtils.isBlank(var))
							var = htmlVarForm;
	
	//					Object o = obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarForm, "."));
	//					String var = StringUtils.substringAfterLast(htmlVarForm, ".");
						try {
							MethodUtils.invokeExactMethod(parent, "htm" + StringUtils.capitalize(var), "Page");
						} catch (RuntimeException e) {
							throw e;
						} catch (Exception e) {
							throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "htm" + StringUtils.capitalize(var), htmlVarInput, parent), e);
						}
					}
					if(htmlVarInput != null) {
						Object parent = StringUtils.contains(htmlVarInput, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarInput, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVarInput, ".");
						if(StringUtils.isBlank(var))
							var = htmlVarInput;
	
						try {
	//					Object o = obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarInput, "."));
	//					String var = StringUtils.substringAfterLast(htmlVarInput, ".");
							if("application/pdf".equals(pageTypeContenu)) {
								Object o = obtenirPourClasse(htmlVarInput);
								if(o instanceof Boolean) {
									e("img").a("class", "").a("style", "width: 1em; height: 1em; position: relative; top: 3px; ").a("src", requeteSite_.getConfigSite_().getStatiqueUrlBase(), ((Boolean)o) ? "/png/check-square-o.png" : "/png/square-o.png").fg();
								}
								else if (o instanceof String && o.toString().startsWith("data:image")) {
									e("img").a("class", "").a("style", "height: 100px; ").a("src", o.toString()).fg();
								}
								else {
									e("span").a("style", "border-bottom: 1px solid black; display: block; ").f();
									String s = (String)MethodUtils.invokeExactMethod(parent, "str" + StringUtils.capitalize(var));
									s(s);
									g("span");
								}
							}
							else {
								try {
									MethodUtils.invokeExactMethod(parent, "input" + StringUtils.capitalize(var), "Page");
								} catch (RuntimeException e) {
									throw e;
								} catch (Exception e) {
									throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "input" + StringUtils.capitalize(var), htmlVarInput, parent), e);
								}
							}
						} catch (RuntimeException e) {
							throw e;
						} catch (Exception e) {
							throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "htm" + StringUtils.capitalize(var), htmlVarInput, parent), e);
						}
					}
					if(htmlPart.getLoginLogout()) {
						ecrireConnecterDeconnecter();
						l();
					}
					s(htmlPart.getHtmlAfter());
				}
			}
		}

		return i;
	}

	public String urlEncode(String s)  {
		try {
			return s == null ? "" : URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			ExceptionUtils.rethrow(e);
			return "";
		}
	}

	/**
	 * r: obtenirConfigSite
	 * r.enUS: obtainSiteConfig
	 * r: obtenirUtilisateurSite
	 * r.enUS: obtainSiteUser
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: utilisateurSite
	 * r.enUS: siteUser
	 * r: SiteUrlBase
	 * r.enUS: SiteBaseUrl
	 * r: PaiementProchain
	 * r.enUS: PaymentNext
	 */
	public void writeMakePayment(Integer ecoleNumero, BigDecimal amount, Long enrollmentKey, String childCompleteNamePreferred) {
		ConfigSite configSite = requeteSite_.getConfigSite_();
		UtilisateurSite utilisateurSite = requeteSite_.getUtilisateurSite();
		String authorizeApiLoginId = (String)configSite.obtenirConfigSite("authorizeApiLoginId" + ecoleNumero);
		String authorizeTransactionKey = (String)configSite.obtenirConfigSite("authorizeTransactionKey" + ecoleNumero);

		if(utilisateurSite != null) {
			String customerProfileId = (String)utilisateurSite.obtenirUtilisateurSite("customerProfileId" + ecoleNumero);
			if(customerProfileId != null) {
				MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
				merchantAuthenticationType.setName(authorizeApiLoginId);
				merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
				ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

				GetHostedProfilePageRequest createCustomerProfileRequest = new GetHostedProfilePageRequest();
				createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
				createCustomerProfileRequest.setCustomerProfileId(customerProfileId);
				ArrayOfSetting customerProfileSettings = new ArrayOfSetting();
				{
					SettingType settingType = new SettingType();
					settingType.setSettingName("hostedProfileManageOptions");
					settingType.setSettingValue("showPayment");
					customerProfileSettings.getSetting().add(settingType);
				}
				{
					SettingType settingType = new SettingType();
					settingType.setSettingName("hostedProfileValidationMode");
					settingType.setSettingValue("testMode");
					customerProfileSettings.getSetting().add(settingType);
				}
				{
					SettingType settingType = new SettingType();
					settingType.setSettingName("hostedProfileBillingAddressOptions");
					settingType.setSettingValue("showNone");
					customerProfileSettings.getSetting().add(settingType);
				}
				{
					SettingType settingType = new SettingType();
					settingType.setSettingName("hostedProfileCardCodeRequired");
					settingType.setSettingValue("true");
					customerProfileSettings.getSetting().add(settingType);
				}
				createCustomerProfileRequest.setHostedProfileSettings(customerProfileSettings);

				GetHostedProfilePageController hostedProfileController = new GetHostedProfilePageController(createCustomerProfileRequest);
				GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
				GetHostedProfilePageResponse profilePageResponse = null;
				hostedProfileController.execute();
				if(hostedProfileController.getErrorResponse() != null)
					throw new RuntimeException(hostedProfileController.getResults().toString());
				else {
					profilePageResponse = hostedProfileController.getApiResponse();
					if(MessageTypeEnum.ERROR.equals(profilePageResponse.getMessages().getResultCode())) {
						throw new RuntimeException(profilePageResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedProfilePageRequest failed. "));
					}
				}

				GetHostedPaymentPageRequest hostedPaymentPageRequest = new GetHostedPaymentPageRequest();
				hostedPaymentPageRequest.setMerchantAuthentication(merchantAuthenticationType);

				ArrayOfSetting hostedPaymentSettings = new ArrayOfSetting();
				{
					SettingType settingType = new SettingType();
					String hostedPaymentReturnOptionsStr = "{ \"url\": \"%s/refresh-enrollment/%s\", \"cancelUrl\": \"%s/refresh-enrollment/%s\" }";
					String hostedPaymentReturnOptions = String.format(hostedPaymentReturnOptionsStr, configSite.getSiteUrlBase(), enrollmentKey, configSite.getSiteUrlBase(), enrollmentKey);
					settingType.setSettingName("hostedPaymentReturnOptions");
					settingType.setSettingValue(hostedPaymentReturnOptions);
					hostedPaymentSettings.getSetting().add(settingType);
				}
				hostedPaymentPageRequest.setHostedPaymentSettings(hostedPaymentSettings);
				TransactionRequestType transactionRequest = new TransactionRequestType();
				// This is the default transaction type. 
				// When using AIM, if the x_type field is not sent to us, the type will default to AUTH_CAPTURE. 
				// Simple Checkout uses AUTH_CAPTURE only. 
				// The Virtual Terminal defaults to AUTH_CAPTURE unless you select a different transaction type.
				// With an AUTH_CAPTURE transaction, the process is completely automatic. 
				// The transaction is submitted to your processor for authorization and, 
				// if approved, is placed in your Unsettled Transactions with the status Captured Pending Settlement. 
				// The transaction will settle at your next batch. 
				// Settlement occurs every 24 hours, within 24 hours of your Transaction Cut-off Time.
				// See: https://support.authorize.net/s/article/What-Are-the-Transaction-Types-That-Can-Be-Submitted
				transactionRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
				transactionRequest.setAmount(amount);
				ArrayOfLineItem lineItems = new ArrayOfLineItem();
				LineItemType lineItem = new LineItemType();
				DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);
				LocalDate now = LocalDate.now();
				LocalDate chargeEndDate = configSite.getPaiementProchain();
				CustomerProfilePaymentType profile = new CustomerProfilePaymentType();
				profile.setCustomerProfileId(customerProfileId);
				transactionRequest.setProfile(profile);
				OrderType order = new OrderType();
				order.setDescription(String.format("%s payment for %s %s", enrollmentKey, childCompleteNamePreferred, fd.format(chargeEndDate)));
				transactionRequest.setOrder(order);
				hostedPaymentPageRequest.setTransactionRequest(transactionRequest);

				GetHostedPaymentPageController hostedPaymentController = new GetHostedPaymentPageController(hostedPaymentPageRequest);
				GetHostedPaymentPageController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
				GetHostedPaymentPageResponse hostedPaymentResponse = null;
				hostedPaymentController.execute();
				if(hostedPaymentController.getErrorResponse() != null)
					throw new RuntimeException(hostedPaymentController.getResults().toString());
				else {
					hostedPaymentResponse = hostedPaymentController.getApiResponse();
					if(MessageTypeEnum.ERROR.equals(hostedPaymentResponse.getMessages().getResultCode())) {
						throw new RuntimeException(hostedPaymentResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. "));
					}
				}
				{ e("div").a("class", "").f();
					e("div").a("class", "w3-large font-weight-bold ").f().sx("Configure school payments with authorize.net").g("div");
					{ e("form").a("method", "post").a("target", "_blank").a("action", configSite.getAuthorizeUrl() + "/customer/manage").f();
						e("input").a("type", "hidden").a("name", "token").a("value", profilePageResponse.getToken()).fg();
						e("button").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("type", "submit").f().sx("Manage payment profile").g("button");
					} g("form");
					e("div").a("class", "").f().sx("Click here to manage your payment profile with authorize.net. ").g("div");
				} g("div");
				{ e("div").a("class", "").f();
					{ e("form").a("method", "post").a("target", "_blank").a("action", configSite.getAuthorizeUrl() + "/payment/payment").f();
						e("input").a("type", "hidden").a("name", "token").a("value", hostedPaymentResponse.getToken()).fg();
						e("button").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("type", "submit").f().sx("Make a payment").g("button");
					} g("form");
					e("div").a("class", "").f().sx("Click here to make a payment with authorize.net. ").g("div");
				} g("div");
			}
		}
	}

}
