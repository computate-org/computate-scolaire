package org.computate.enUS.school.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

public class PageLayout extends PageLayoutGen<Object> {

	public static List<String> HTML_CLOSED_ELEMENTS = Arrays.asList("area", "base", "br", "col", "command", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");

	public static List<String> HTML_ELEMENTS_NO_WRAP = Arrays.asList("script", "span", "a", "b", "i", "u", "title", "use", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "p");

	public static DateTimeFormatter FORMATDateHeureCourt = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.US);

	public static DateTimeFormatter FORMATDateCourt = DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US);

	public static DateTimeFormatter FORMATAffichage = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy h:mm a", Locale.US);

	protected void _pageParts(List<PagePart> l) {
	}

	public void  avantPagePart(PagePart o, String var) {
		o.setPage_(this);
	}

	protected void _requeteSite_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _statiqueUrlBase(Wrap<String> c) {
		c.o(requeteSite_.getConfigSite_().getStatiqueUrlBase()); 
	}

	protected void _pageDocumentSolr(Wrap<SolrDocument> c) {
		
	}

	protected void __writer(Wrap<ToutEcrivain> c) {
		c.o(requeteSite_.getW());
	}

	protected void _contexteIconeGroupe(Wrap<String> c) {
	}

	protected void _contexteIconeNom(Wrap<String> c) {
	}

	protected void _contexteIconeClassesCss(Wrap<String> c) {
		if(contexteIconeGroupe != null && contexteIconeNom != null)
			c.o("fa" + StringUtils.substring(contexteIconeGroupe, 0, 1) + " fa-" + contexteIconeNom + " ");
	}

	protected void _pageVisibleToBots(Wrap<Boolean> c) {
		c.o(BooleanUtils.toBooleanDefaultIfNull((Boolean)pageDocumentSolr.get(c.var + "_stored_boolean"), false));
	}

	protected void _pageH1(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void _pageH2(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void _pageH3(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void __pageH1Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH1));
	}

	protected void __pageH2Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH2));
	}

	protected void __pageH2Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), pageH2));
	}

	protected void _pageTitle(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_enUS_stored_string"), StringUtils.join(pageH1, pageH2)));
	}

	protected void _pageUri(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_enUS_stored_string"), null));
	}

	protected void _pageUriFrFR(Wrap<String> c) {
	}

	protected void _pageUris(List<String> l) {
		l.add(pageUri.toString());
		l.add(pageUri + "/");
	}

	protected void _pageUrl(Wrap<String> c) {
	}

	protected void _pageImageUri(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void _pageImageUrl(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://" + requeteSite_.getConfigSite_().getSiteNomHote() + pageImageUri));
	}

	protected void _pageVideoId(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void _pageVideoUrl(Wrap<String> c) {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://youtu.be/" + pageVideoId));
		}
	}

	protected void _pageVideoUrlEmbed(Wrap<String> c) {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), "https://www.youtube.com/embed/" + pageVideoId));
		}
	}

	protected void __pageImageWidth(Wrap<Integer> c) {
		c.o((Integer)pageDocumentSolr.get(c.var + "_stored_int"));
	}

	protected void __pageImageHeight(Wrap<Integer> c) {
		c.o((Integer)pageDocumentSolr.get(c.var + "_stored_int"));
	}

	protected void __pageImageContentType(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageDocumentSolr.get(c.var + "_stored_string"), null));
	}

	protected void __pageContentType(Wrap<String> c) {
		c.o("text/html;charset=UTF-8");
	}

	protected void __pageCreated(Wrap<LocalDateTime> c) {   
		Date solrVal = (Date)pageDocumentSolr.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	protected void __pageModified(Wrap<LocalDateTime> c) {
		Date solrVal = (Date)pageDocumentSolr.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		else
			c.o(pageCree);
	}

	protected void __pageKeywords(Wrap<String> c) {
	}

	protected void _pageDescription(Wrap<String> c) {
	}

	protected void __homePageUri(Wrap<String> c) {
		c.o("");
	}

	protected void __pageInrEntry(Wrap<String> c) {
		c.o(" /school");
	}

	protected void __aboutPageUri(Wrap<String> c) {
		c.o("/apropos");
	}

	protected void _pageFaqUri(Wrap<String> c) {
		c.o("/faq");
	}

	protected void __userPageUri(Wrap<String> c) {
		c.o("/utilisateur");
	}

	protected void __logoutPageUri(Wrap<String> c) {
		try {
			ConfigSite configSite = requeteSite_.getConfigSite_();
			String o = configSite.getAuthUrl() + "/realms/" + configSite.getAuthRoyaume() + "/protocol/openid-connect/logout?redirect_uri=" + URLEncoder.encode(configSite.getSiteUrlBase() + "/deconnexion", "UTF-8");
			c.o(o);
		} catch (UnsupportedEncodingException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	@Override()
	public void  htmlMeta() {
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

	@Override()
	public void  htmlScriptsMiseEnPage() {
		e("script").a("src", statiqueUrlBase, "/js/site.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/UtilisateurSiteFrFRPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/moment.min.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/jqDatePicker.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/jquery.serialize-object.js").f().g("script");
	}

	@Override()
	public void  htmlScriptMiseEnPage() {
	}

	@Override()
	public void  htmlStylesMiseEnPage() {
		e("link").a("rel", "stylesheet").a("href", "https://www.w3schools.com/w3css/4/w3.css").fg();
		e("link").a("rel", "stylesheet").a("href", "/static/css/site.css").fg();
		e("link").a("rel", "stylesheet").a("href", "/static/css/datePicker.css").fg();
		e("link").a("rel", "stylesheet").a("href", "https://fonts.googleapis.com/css?family=Khand").fg();
	}

	@Override()
	public void  htmlStyleMiseEnPage() {
	}

	@Override()
	public void  htmlBodyMiseEnPage() {
	}

	@Override()
	public void  htmlMiseEnPage() {
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
						e("div").a("class", "w3-content w3-center w3-text-black ").f();
							e("div").a("class", "").f();
								menu();
							g("div"); 
						g("div");
						e("div").a("id", "site-section-primary").a("class", "site-section-primary w3-text-black w3-padding-bottom-32 ").f();
							e("div").a("class", "w3-content ").f();
	
								htmlBody();
	
								abondonnezPas();
	
								partagerPage();

								e("footer").a("class", "w3-center w3-black w3-padding-48 ").f();
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
						menu();
						e("div").a("class", "w3-container ").f();
							e("div").a("class", "w3-container w3-text-black w3-margin-top ").f();
								e("h6").a("id", "h2-contactez-nous").a("class",  "w3-xlarge ").f();
									sx("Let's get connected. ");
								g("h6");
								e("div").a("style", "").f();
									e("a").a("data-ajax", "false").a("href", "https://www.facebook.com/Trail-Blaze-Hunting-Consultants-LLC-179217055464922/").f();
//											e("img").a("class", "grow-30 ").a("style", "display: inline-block; width: 50px; height: 50px; margin: 0 10px;").a("src", "https://www.computate.org/svg/facebook.svg").fg();
									g("a");
								g("div");
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

	public void  menu() {
		e("div").a("class", "w3-bar w3-text-white w3-padding-bottom-8 w3-padding-top-8 ").a("style", "padding-left: 16px; padding-right: 16px; ").f();
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("span").a("class", "header-icon-a grow-30 w3-center ").f();
					e("a").a("class", "w3-hover-opacity").a("title", "English").a("href", pageUriEnUS).f();
						e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/flag-US.svg").a("style", "height: 50px; ").fg();
					g("a");
					e("a").a("class", "w3-hover-opacity").a("title", "français").a("href", pageUri).f();
						e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/flag-FR.svg").a("style", "height: 50px; ").fg();
					g("a");
					e("br").fg();
					e("span").a("class", "site-menu-item").f();
						sx("Langue");
					g("span");
				g("span");
			g("div");
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageAccueilUri).f();
	//				e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/computate-keys.svg").a("style", "width: 250px; ").fg();
					e("br").fg();
					e("span").a("class", "site-menu-item").f();
						sx("Accueil");
					g("span");
				g("a");
			g("div");
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageAProposUri).f();
	//				e("img").a("alt", "").a("src", statiqueUrlBase, "/png/computate.png").a("style", "height: 50px; ").fg();
					e("br").fg();
					e("span").a("class", "site-menu-item").f();
						sx("À propos");
					g("span");
				g("a");
			g("div");
			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageFaqUri).f();
	//				e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/ufo.svg").a("style", "height: 50px; ").fg();
					e("br").fg();
					e("span").a("class", "site-menu-item").f();
						sx("FAQ");
					g("span");
				g("a");
			g("div");
			if(requeteSite_.getUtilisateurId() == null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageUtilisateurUri).f(); 
	//					e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/astronaut-helmet.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx("Connexion");
						g("span");
					g("a");
				g("div");
			}
			if(requeteSite_.getUtilisateurId() != null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageUtilisateurUri).f(); 
	//					e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/astronaut-helmet.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx(requeteSite_.getUtilisateurNom());
						g("span");
					g("a");
				g("div");
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageDeconnexionUri).f();
	//					e("img").a("alt", "").a("src", statiqueUrlBase, "/svg/light-speed.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx("Déconnexion");
						g("span");
					g("a");
				g("div");
			}
		g("div");
	}

	public void  abondonnezPas() {
		{ e("div").a("class", "site-abondonnezPas-div ").f();
			{ e("fieldset").a("class", "site-abondonnezPas-fieldset ").f();
				{ e("legend").f();
					e("h3").f().sx("Don't give up on your dreams. You can do hard things! ").g("h3");
				} g("legend");
				{ e("h4").f();
					e("img").a("class", "w3-image ").a("src", "/svg/computate-keys.svg").fg();
				} g("h4");
			} g("fieldset");
		} g("div");
	}

	public void  partagerPage() {
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

	public String formaterDateHeureCourt(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateHeureCourt.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	public String formaterDateCourt(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateCourt.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	public String formaterDateAffichage(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATAffichage.format(date.toInstant());
		}
		return resultat;
	}

	public String formaterDateAffichage(LocalDateTime date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATAffichage.format(date);
		}
		return resultat;
	}

	public MiseEnPage e(String localName) {
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

	public MiseEnPage a(String attributeName, Object...objects) {
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

	public MiseEnPage a2() {
		w.s("\"");
		
		return this;
	}

	public MiseEnPage f() {
		w.s(">");
		
		return this;
	}

	public MiseEnPage all(Object...objects) {
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	public MiseEnPage t(int numberTabs, Object...objects) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}

	public MiseEnPage tabLine(int numberTabs, Object...objects) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	public MiseEnPage allLine(Object...objects) {
		s(objets);
		s("\n");
		return this;
	}

	public MiseEnPage lx(Object...objets) {
		s(objets);
		sx("\n");
		return this;
	}

	public MiseEnPage allXml(Object...objects) {
		for(Object objet : objets) {
			if(objet != null) {
				String s = objet.toString();
				w.s(OutilXml.echapper(s));
			}
		}
		
		return this;
	}

	public MiseEnPage allXml(int numberTabs, Object...objects) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		return this;
	}

	public MiseEnPage tabLineXml(int numberTabs, Object...objects) {
		for(int i = 0; i < nombreTabulations; i++)
			sx("\t");
		sx(objets);
		sx("\n");
		return this;
	}

	public MiseEnPage fg() {
		w.s("/>");
		requeteSite_.getXmlPile().pop();
		
		return this;
	}

	public MiseEnPage g(String localName) {
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

	public void  htmlFormOptionsUtilisateurSite(UtilisateurSite o) {
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
}
