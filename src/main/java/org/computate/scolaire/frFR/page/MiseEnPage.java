package org.computate.scolaire.frFR.page;          

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
import org.computate.scolaire.frFR.ecole.EcoleGenPage;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.enfant.EnfantGenPage;
import org.computate.scolaire.frFR.gardien.GardienGenPage;
import org.computate.scolaire.frFR.inscription.InscriptionGenPage;
import org.computate.scolaire.frFR.mere.MereGenPage;
import org.computate.scolaire.frFR.page.part.PagePart;
import org.computate.scolaire.frFR.paiement.PaiementGenPage;
import org.computate.scolaire.frFR.pere.PereGenPage;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.saison.SaisonGenPage;
import org.computate.scolaire.frFR.session.SessionGenPage;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.xml.OutilXml;

/**  
 * NomCanonique.enUS: org.computate.scolaire.enUS.page.PageLayout
 * MotCle: classeNomSimpleMiseEnPage
 */   
public class MiseEnPage extends MiseEnPageGen<Object> {   

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
		c.o("");
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
		e("script").a("src", statiqueUrlBase, "/js/frFR/SaisonPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/SessionPage.js").f().g("script");
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
	 * r: Ce site est open source.
	 * r.enUS: This site is open source. 
	 * r: Voir le code source ici. 
	 * r.enUS: View the source code here. 
	 */
	@Override public void htmlMiseEnPage() {
		e("html").a("xmlns:xlink", "http://www.w3.org/1999/xlink").a("xmlns", "http://www.w3.org/1999/xhtml").a("xmlns:fb", "http://ogp.me/ns/fb#").f();
			e("head").f();
				e("title").f();
					sx(pageTitre);
				g("title");
				htmlScripts();
				e("script").f().l("/*<![CDATA[*/");
				htmlScript();
				s("/*]]>*/").g("script");
				htmlStyles();
				e("style").f().l("/*<![CDATA[*/");
				htmlStyle();
				s("/*]]>*/").g("style");
	
			g("head");
			e("body").a("class", "w3-light-grey ").f(); 
				e("a").a("name", "top").f().g("a");
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
				e("div").a("class", "site-section-all ").f();
					e("div").a("class", "site-section-above ").f();
						e("div").a("class", "w3-content w3-center w3-black ").f();
							e("div").a("class", "").f();
								menu("Menu1");
							g("div"); 
						g("div");
						e("div").a("id", "site-section-primary").a("class", "site-section-primary w3-text-black w3-padding-bottom-32 ").f();
							e("div").a("class", "w3-content ").f();
	
								htmlBody();

								e("footer").a("class", "w3-center w3-black w3-padding-48 w3-margin-top ").f();
									e("div").a("class", "w3-xxlarge ").f();
										sx("Ce site est open source. ");
									g("div");
								g("footer");
							g("div");
						g("div");
					g("div");
				g("div");
				e("div").a("class", "w3-row site-section-contact ").f();
					e("div").a("class", "w3-content w3-center  w3-cell-row w3-margin-bottom-32 ").f();
						menu("Menu2");
						e("div").a("class", "w3-container ").f();
							e("div").a("class", "w3-container w3-text-black w3-margin-top ").f();
								e("h6").a("id", "h2-contactez-nous").a("class",  "w3-xlarge ").f();
									sx("Let's get connected. ");
								g("h6");
								e("h6").f();
									e("a").a("href", "#top").f();
										sx("Up to the top. ");
									g("a");
								g("h6");
							g("div");
						g("div");
					g("div");
				g("div");
			g("body");
		g("html");

	}

	/** 
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
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
	 * r: EcoleGenPage.htmlSuggereEcoleGenPage
	 * r.enUS: SchoolGenPage.htmlSuggestSchoolGenPage
	 * r: AnneeGenPage.htmlSuggereAnneeGenPage
	 * r.enUS: YearGenPage.htmlSuggestYearGenPage
	 * r: SaisonGenPage.htmlSuggereSaisonGenPage
	 * r.enUS: SeasonGenPage.htmlSuggestSeasonGenPage
	 * r: SessionGenPage.htmlSuggereSessionGenPage
	 * r.enUS: SessionGenPage.htmlSuggestSessionGenPage
	 * r: AgeGenPage.htmlSuggereAgeGenPage
	 * r.enUS: AgeGenPage.htmlSuggestAgeGenPage
	 * r: BlocGenPage.htmlSuggereBlocGenPage
	 * r.enUS: BlockGenPage.htmlSuggestBlockGenPage
	 * r: InscriptionGenPage.htmlSuggereInscriptionGenPage
	 * r.enUS: EnrollmentGenPage.htmlSuggestEnrollmentGenPage
	 * r: PaiementGenPage.htmlSuggerePaiementGenPage
	 * r.enUS: PaymentGenPage.htmlSuggestPaymentGenPage
	 * r: MereGenPage.htmlSuggereMereGenPage
	 * r.enUS: MomGenPage.htmlSuggestMomGenPage
	 * r: PereGenPage.htmlSuggerePereGenPage
	 * r.enUS: DadGenPage.htmlSuggestDadGenPage
	 * r: GardienGenPage.htmlSuggereGardienGenPage
	 * r.enUS: GuardianGenPage.htmlSuggestGuardianGenPage
	 * r: EnfantGenPage.htmlSuggereEnfantGenPage
	 * r.enUS: ChildGenPage.htmlSuggestChildGenPage
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

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-pink ").f();
						e("i").a("class", "far fa-school w3-padding-small ").f().g("i");
						sx("écoles");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					EcoleGenPage.htmlSuggereEcoleGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-orange ").f();
						e("i").a("class", "far fa-calendar-check w3-padding-small ").f().g("i");
						sx("années");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					AnneeGenPage.htmlSuggereAnneeGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-yellow ").f();
						e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
						sx("saisons");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					SaisonGenPage.htmlSuggereSaisonGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
						sx("sessions");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					SessionGenPage.htmlSuggereSessionGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-blue ").f();
						e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
						sx("âges");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					AgeGenPage.htmlSuggereAgeGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-indigo ").f();
						e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
						sx("blocs");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					BlocGenPage.htmlSuggereBlocGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-purple ").f();
						e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
						sx("inscriptions");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					InscriptionGenPage.htmlSuggereInscriptionGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "fas fa-search-dollar w3-padding-small ").f().g("i");
						sx("paiements");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					PaiementGenPage.htmlSuggerePaiementGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-pink ").f();
						e("i").a("class", "far fa-female w3-padding-small ").f().g("i");
						sx("mères");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					MereGenPage.htmlSuggereMereGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-light-blue ").f();
						e("i").a("class", "far fa-male w3-padding-small ").f().g("i");
						sx("pères");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					PereGenPage.htmlSuggerePereGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-orange ").f();
						e("i").a("class", "far fa-phone w3-padding-small ").f().g("i");
						sx("gardiens");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					GardienGenPage.htmlSuggereGardienGenPage(this, id);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
						sx("enfants");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					EnfantGenPage.htmlSuggereEnfantGenPage(this, id);
				} g("div");
			} g("div");
			if(requeteSite_.getUtilisateurId() == null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "w3-padding ").a("href", pageUtilisateurUri).f(); 
						e("span").a("class", "site-menu-item").f();
							sx("Connexion");
						g("span");
					g("a");
				g("div");
			}
			if(requeteSite_.getUtilisateurId() != null) {

				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-green ").f();
							e("i").a("class", "far fa-user-cog w3-padding-small ").f().g("i");
							sx(requeteSite_.getUtilisateurNom());
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						UtilisateurSite o = requeteSite_.getUtilisateurSite();
						{ e("div").a("class", "w3-cell-row ").f();
							e("label").a("for", "Page_voirArchive").a("class", "").f().sx("voir archivé").g("label");
							e("input")
								.a("type", "checkbox")
								.a("value", "true")
								.a("class", "setVoirArchive")
								.a("name", "setVoirArchive")
								.a("id", "Page_voirArchive")
								.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=\"pk\"]').val() }], 'setVoirArchive', $(this).prop('checked'), function() { addGlow($('#Page_voirArchive')); }, function() { addError($('#Page_voirArchive')); }); ")
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
								.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + $('#SiteUserForm :input[name=\"pk\"]').val() }], 'setVoirSupprime', $(this).prop('checked'), function() { addGlow($('#Page_voirSupprime')); }, function() { addError($('#Page_voirSupprime')); }); ")
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
							sx("Déconnexion");
						g("span");
					g("a");
				g("div");
			}
		g("div");
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
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		requeteSite_.getXmlPile().push(nomLocal);
		if(StringUtils.equals(nomLocal, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabulationsEchappes.isEmpty()) {
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
			s("\t");
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
			s("\t");
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
			sx("\t");
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
			sx("\t");
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
	 * r: tabulationsEchappes
	 * r.enUS: tabsEscaped
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
		String tabulations = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\t"));
		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.getXmlPile().size(), "\\t"));

		if(!eNoWrap && !tabulationsEchappes.isEmpty()) {
			w.l();
			w.s(tabulations);
		}
		w.s("</");
		w.s(nomLocal);
		w.s(">");
		
		return this;
	}

	/**
	 * r: VoirArchive
	 * r.enUS: SeeArchived
	 * r: VoirSupprime
	 * r.enUS: SeeDeleted
	 */
	public void htmlFormOptionsUtilisateurSite(UtilisateurSite o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "voirArchiveForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "voirArchive")
						.a("id", "Page_voirArchive")
						.a("value", "false")
					.fg();

					e("input")
						.a("type", "checkbox")
						.a("value", "true")
						.a("class", "setVoirArchive")
						.a("name", "setVoirArchive")
						.a("id", "Page_voirArchive")
						.a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirArchiveForm')); ")
						;
						if(o.getVoirArchive() != null && o.getVoirArchive())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_voirArchive").a("class", "").f().sx("voir archivé").g("label");
				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("id", "voirSupprimeForm").a("style", "display: inline-block; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "voirSupprime")
						.a("id", "Page_voirSupprime")
						.a("value", "false")
					.fg();

					e("input");
						a("type", "checkbox");
						a("value", "true");
						a("class", "setVoirSupprime");
						a("name", "setVoirSupprime");
						a("id", "Page_voirSupprime");
						a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirSupprimeForm')); ");
						if(o.getVoirSupprime() != null && o.getVoirSupprime())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
				} g("form");
			} g("div");
		} g("div");
	}

	/**
	 * r: obtenirPourClasse
	 * r.enUS: obtainForClass
	 * r: pageTypeContenu
	 * r.enUS: pageContentType
	 */
	public Integer htmlPageLayout2(List<HtmlPart> htmlPartList, HtmlPart htmlPartParent, Integer start, Integer size) {

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

		for(i = start; i < size; i++) {
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

			String htmlVar = htmlPart.getHtmlVar();
			String htmlVarInput = htmlPart.getHtmlVarInput();
			String htmlVarForm = htmlPart.getHtmlVarForm();
			String htmlVarForEach = htmlPart.getHtmlVarForEach();
			Boolean pdfExclude = htmlPart.getPdfExclude();

			if(!"application/pdf".equals(pageTypeContenu) || BooleanUtils.isNotTrue(pdfExclude)) {
				s(htmlPart.getHtmlBefore());
				if(htmlVar != null) {
					if(htmlVarForEach != null) {
	
						Object parent = StringUtils.contains(htmlVar, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVar, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVar, ".");
						if(StringUtils.isBlank(var))
							var = htmlVar;
	
						Object parentForEach = StringUtils.contains(htmlVarForEach, ".") ? obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarForEach, ".")) : null;
						if(parentForEach == null)
							parentForEach = this;
						String varForEach = StringUtils.substringAfterLast(htmlVarForEach, ".");
						if(StringUtils.isBlank(varForEach))
							var = htmlVarForEach;
	
						try {
							Collection<?> collection = (Collection<?>)MethodUtils.invokeMethod(parentForEach, "get" + StringUtils.capitalize(varForEach), new Object[] {});
							List<?> list = collection.stream().collect(Collectors.toList());
							Integer forStart = i + 1;
	
							for(Object o : list) {
								try {
									MethodUtils.invokeExactMethod(parent, "set" + StringUtils.capitalize(var), o);
									i = htmlPageLayout2(htmlPartList, htmlPart, forStart, size);
								} catch (Exception e) {
									throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "set" + StringUtils.capitalize(var), htmlVar, parent), e);
								}
							}
							i = i - 1;
						} catch (Exception e) {
							throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "get" + StringUtils.capitalize(var), varForEach, parentForEach), e);
						}
					}
					else {
						s(obtenirPourClasse(htmlVar));
					}
				}
				if(htmlVarForm != null) {
					Object o = obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarForm, "."));
					String var = StringUtils.substringAfterLast(htmlVarForm, ".");
					try {
						MethodUtils.invokeExactMethod(o, "htm" + StringUtils.capitalize(var), "Page");
					} catch (Exception e) {
						throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "htm" + StringUtils.capitalize(var), htmlVarInput, o), e);
					}
				}
				if(htmlVarInput != null) {
					Object o = obtenirPourClasse(StringUtils.substringBeforeLast(htmlVarInput, "."));
					String var = StringUtils.substringAfterLast(htmlVarInput, ".");
					try {
						MethodUtils.invokeExactMethod(o, "input" + StringUtils.capitalize(var), "Page");
					} catch (Exception e) {
						throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "input" + StringUtils.capitalize(var), htmlVarInput, o), e);
					}
				}
				s(htmlPart.getHtmlAfter());
			}
		}

		return i;
	}
}
