package org.computate.scolaire.enUS.page;

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
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.page.part.PagePart;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.enUS.xml.UtilXml;

public class PageLayout extends PageLayoutGen<Object> {

	public static List<String> HTML_CLOSED_ELEMENTS = Arrays.asList("area", "base", "br", "col", "command", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");

	public static List<String> HTML_ELEMENTS_NO_WRAP = Arrays.asList("script", "span", "a", "b", "i", "u", "title", "use", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "p", "textarea");

	public static DateTimeFormatter FORMATDateTimeShort = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.US);

	public static DateTimeFormatter FORMATDateShort = DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US);

	public static DateTimeFormatter FORMATDateDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy", Locale.US);

	public static DateTimeFormatter FORMATDateTimeDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy h:mm a", Locale.US);

	public static DateTimeFormatter FORMATZonedDateTimeDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy h:mm a zz VV", Locale.US);

	protected void _pageParts(List<PagePart> l) {
	}

	public void  beforePagePart(PagePart o, String var) {
		o.setPage_(this);
	}

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _staticBaseUrl(Wrap<String> c) {
		c.o(siteRequest_.getSiteConfig_().getStaticBaseUrl()); 
	}

	protected void _pageSolrDocument(Wrap<SolrDocument> c) {
		
	}

	protected void _w(Wrap<AllWriter> c) {
		c.o(siteRequest_.getW());
	}

	protected void _contextIconGroup(Wrap<String> c) {
	}

	protected void _contextIconName(Wrap<String> c) {
	}

	protected void _contextIconCssClasses(Wrap<String> c) {
		if(contextIconGroup != null && contextIconName != null)
			c.o("fa" + StringUtils.substring(contextIconGroup, 0, 1) + " fa-" + contextIconName + " ");
	}

	protected void _pageVisibleToBots(Wrap<Boolean> c) {
		c.o(BooleanUtils.toBooleanDefaultIfNull((Boolean)pageSolrDocument.get(c.var + "_stored_boolean"), false));
	}

	protected void _pageH1(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void _pageH2(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void _pageH3(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void __pageH1Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), pageH1));
	}

	protected void __pageH2Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), pageH2));
	}

	protected void __pageH3Short(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), pageH2));
	}

	protected void _pageTitle(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_enUS_stored_string"), StringUtils.join(pageH1, pageH2)));
	}

	protected void _pageUri(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_enUS_stored_string"), null));
	}

	protected void _pageUris(List<String> l) {
		l.add(pageUri.toString());
		l.add(pageUri + "/");
	}

	protected void _pageUrl(Wrap<String> c) {
	}

	protected void _pageImageUri(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void _pageImageUrl(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), "https://" + siteRequest_.getSiteConfig_().getSiteHostName() + pageImageUri));
	}

	protected void _pageVideoId(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void _pageVideoUrl(Wrap<String> c) {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), "https://youtu.be/" + pageVideoId));
		}
	}

	protected void _pageVideoUrlEmbed(Wrap<String> c) {
		if(pageVideoId != null) {
			c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), "https://www.youtube.com/embed/" + pageVideoId));
		}
	}

	protected void _pageImageWidth(Wrap<Integer> c) {
		c.o((Integer)pageSolrDocument.get(c.var + "_stored_int"));
	}

	protected void _pageImageHeight(Wrap<Integer> c) {
		c.o((Integer)pageSolrDocument.get(c.var + "_stored_int"));
	}

	protected void _pageImageContentType(Wrap<String> c) {
		c.o(StringUtils.defaultIfBlank((String)pageSolrDocument.get(c.var + "_stored_string"), null));
	}

	protected void _pageContentType(Wrap<String> c) {
		c.o("text/html;charset=UTF-8");
	}

	protected void _pageCreated(Wrap<LocalDateTime> c) {   
		Date solrVal = (Date)pageSolrDocument.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	protected void _pageModified(Wrap<LocalDateTime> c) {
		Date solrVal = (Date)pageSolrDocument.get(c.var + "_stored_date");
		if(solrVal != null)
			c.o(solrVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		else
			c.o(pageCreated);
	}

	protected void _pageKeywords(Wrap<String> c) {
	}

	protected void _pageDescription(Wrap<String> c) {
	}

	protected void _pageHomeUri(Wrap<String> c) {
		c.o("");
	}

	protected void _pageSchoolUri(Wrap<String> c) {
		c.o(" /enUS/school");
	}

	protected void _pageUserUri(Wrap<String> c) {
		c.o("/enUS/user");
	}

	protected void _pageLogoutUri(Wrap<String> c) {
		try {
			SiteConfig siteConfig = siteRequest_.getSiteConfig_();
			String o = siteConfig.getAuthUrl() + "/realms/" + siteConfig.getAuthRealm() + "/protocol/openid-connect/logout?redirect_uri=" + URLEncoder.encode(siteConfig.getSiteBaseUrl() + "/logout", "UTF-8");
			c.o(o);
		} catch (UnsupportedEncodingException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	@Override()
	public void  htmlMeta() {
		e("meta").a("charset", "UTF-8").fg();
		e("meta").a("name", "viewport").a("content", "width=device-width, initial-scale=1").fg();
		e("meta").a("name", "keywords").a("content", pageKeywords).fg();
		e("meta").a("property", "og:title").a("content", pageTitle).fg();
		e("meta").a("property", "og:description").a("content", pageDescription).fg();
		e("meta").a("property", "og:url").a("content", pageUrl).fg();
		e("meta").a("property", "og:site_name").a("content", siteRequest_.getSiteConfig_().getDomainName()).fg();
		e("meta").a("property", "og:image").a("content", pageImageUrl).fg();
		e("meta").a("property", "og:image:width").a("content", pageImageWidth).fg();
		e("meta").a("property", "og:image:height").a("content", pageImageHeight).fg();
		e("meta").a("property", "og:image:type").a("content", pageImageContentType).fg();
		e("meta").a("property", "og:image:alt").a("content", pageTitle).fg();
		e("meta").a("property", "og:type").a("content", "article").fg();
		e("meta").a("name", "twitter:card").a("content", "summary_large_image").fg();
		e("meta").a("name", "twitter:site").a("content", "@computateorg").fg();
		e("meta").a("name", "twitter:creator").a("content", "@computateorg").fg();
		e("meta").a("name", "twitter:title").a("content", pageTitle).fg();
		e("meta").a("name", "twitter:description").a("content", pageDescription).fg();
		e("meta").a("name", "twitter:image").a("content", pageImageUrl).fg();
		e("meta").a("name", "description").a("content", pageDescription).fg();
	}

	@Override()
	public void  htmlScriptsPageLayout() {
		e("script").a("src", staticBaseUrl, "/js/jquery-1.12.4.min.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/site-enUS.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/UtilisateurSiteFrFRPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/moment.min.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/jqDatePicker.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/jquery.serialize-object.js").f().g("script");
		e("script").a("src", "https://kit.fontawesome.com/59d19567d5.js").f().g("script");
	}

	@Override()
	public void  htmlScriptPageLayout() {
	}

	@Override()
	public void  htmlStylesPageLayout() {
		e("link").a("rel", "stylesheet").a("href", "https://www.w3schools.com/w3css/4/w3.css").fg();
		e("link").a("rel", "stylesheet").a("href", "/static/css/site-enUS.css").fg();
		e("link").a("rel", "stylesheet").a("href", "/static/css/datePicker.css").fg();
		e("link").a("rel", "stylesheet").a("href", "https://fonts.googleapis.com/css?family=Khand").fg();
//		e("link").a("rel", "stylesheet").a("href", "https://pro.fontawesome.com/releases/v5.9.0/css/all.css").a("integrity", "sha384-vlOMx0hKjUCl4WzuhIhSNZSm2yQCaf0mOU1hEDK/iztH3gU4v5NMmJln9273A6Jz").a("crossorigin", "anonymous").fg();
	}

	@Override()
	public void  htmlStylePageLayout() {
	}

	@Override()
	public void  htmlBodyPageLayout() {
	}

	@Override()
	public void  htmlPageLayout() {
		e("html").a("xmlns:xlink", "http://www.w3.org/1999/xlink").a("xmlns", "http://www.w3.org/1999/xhtml").a("xmlns:fb", "http://ogp.me/ns/fb#").f();
			e("head").f();
				e("title").f();
					sx(pageTitle);
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

								e("footer").a("class", "w3-center w3-black w3-padding-48 w3-margin-top ").f();
									e("div").a("class", "w3-xxlarge ").f();
										sx("This site is open source.  ");
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
				e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageHomeUri).f();
	//				e("img").a("alt", "").a("src", staticBaseUrl, "/svg/computate-keys.svg").a("style", "width: 250px; ").fg();
					e("br").fg();
					e("span").a("class", "site-menu-item").f();
						sx("Accueil");
					g("span");
				g("a");
			g("div");
			if(siteRequest_.getUserId() == null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageUserUri).f(); 
	//					e("img").a("alt", "").a("src", staticBaseUrl, "/svg/astronaut-helmet.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx("Connexion");
						g("span");
					g("a");
				g("div");
			}
			if(siteRequest_.getUserId() != null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageUserUri).f(); 
	//					e("img").a("alt", "").a("src", staticBaseUrl, "/svg/astronaut-helmet.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx(siteRequest_.getUserName());
						g("span");
					g("a");
				g("div");
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "header-icon-a grow-30 w3-hover-opacity w3-center ").a("href", pageLogoutUri).f();
	//					e("img").a("alt", "").a("src", staticBaseUrl, "/svg/light-speed.svg").a("style", "height: 50px; ").fg();
						e("br").fg();
						e("span").a("class", "site-menu-item").f();
							sx("Déconnexion");
						g("span");
					g("a");
				g("div");
			}
		g("div");
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
			resultat = FORMATDateTimeShort.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	public String formaterDateCourt(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateShort.format(date.toInstant().atZone(ZoneId.systemDefault()));
		}
		return resultat;
	}

	public String formaterDateAffichage(Date date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateTimeDisplay.format(date.toInstant());
		}
		return resultat;
	}

	public String formaterDateAffichage(LocalDateTime date) {
		String resultat = "";
		if(date != null) {
			resultat = FORMATDateTimeDisplay.format(date);
		}
		return resultat;
	}

	public PageLayout e(String localName) {
		String localNameParent = siteRequest_.getXmlStack().isEmpty() ? null : siteRequest_.getXmlStack().peek();

		boolean eNoWrapParent = localNameParent == null || HTML_ELEMENTS_NO_WRAP.contains(localNameParent);
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\t"));
		String tabsEscaped = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\\t"));

		siteRequest_.getXmlStack().push(localName);
		if(StringUtils.equals(localName, "html"))
			w.s("<!DOCTYPE html>\n");
		if(!eNoWrapParent && !tabsEscaped.isEmpty()) {
			w.l();
			w.s(tabs);
		}
		w.s("<");
		w.s(localName);
		
		return this;
	}

	public PageLayout a1(String attributeName, Object...objects) {
		w.s(" ");
		w.s(attributeName);
		w.s("=\"");
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escapeInQuotes(s));
			}
		}
		
		return this;
	}

	public PageLayout a(String attributeName, Object...objects) {
		w.s(" ");
		w.s(attributeName);
		w.s("=\"");
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escapeInQuotes(s));
			}
		}
		w.s("\"");
		
		return this;
	}

	public PageLayout a2() {
		w.s("\"");
		
		return this;
	}

	public PageLayout f() {
		w.s(">");
		
		return this;
	}

	public PageLayout s(Object...objects) {
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(s);
			}
		}
		
		return this;
	}

	public PageLayout t(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			s("\t");
		s(objects);
		return this;
	}

	public PageLayout tl(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			s("\t");
		s(objects);
		s("\n");
		return this;
	}

	public PageLayout l(Object...objects) {
		s(objects);
		s("\n");
		return this;
	}

	public PageLayout lx(Object...objects) {
		s(objects);
		sx("\n");
		return this;
	}

	public PageLayout sx(Object...objects) {
		for(Object object : objects) {
			if(object != null) {
				String s = object.toString();
				w.s(UtilXml.escape(s));
			}
		}
		
		return this;
	}

	public PageLayout tx(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			sx("\t");
		sx(objects);
		return this;
	}

	public PageLayout tlx(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			sx("\t");
		sx(objects);
		sx("\n");
		return this;
	}

	public PageLayout fg() {
		w.s("/>");
		siteRequest_.getXmlStack().pop();
		
		return this;
	}

	public PageLayout g(String localName) {
		String localNameParent = siteRequest_.getXmlStack().peek();
		boolean eNoWrap = localNameParent == null || HTML_ELEMENTS_NO_WRAP.contains(localName);

		siteRequest_.getXmlStack().pop();
		String tabs = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\t"));
		String tabsEscaped = String.join("", Collections.nCopies(siteRequest_.getXmlStack().size(), "\\t"));

		if(!eNoWrap && !tabsEscaped.isEmpty()) {
			w.l();
			w.s(tabs);
		}
		w.s("</");
		w.s(localName);
		w.s(">");
		
		return this;
	}

	public void  htmlFormOptionsUtilisateurSite(SiteUser o) {
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
						.a("class", "setSeeArchived")
						.a("name", "setSeeArchived")
						.a("id", "Page_voirArchive")
						.a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirArchiveForm')); ")
						;
						if(o.getSeeArchived() != null && o.getSeeArchived())
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
						a("class", "setSeeDeleted");
						a("name", "setSeeDeleted");
						a("id", "Page_voirSupprime");
						a("onchange", "patchUtilisateurSite($('#UtilisateurSiteForm'), $('#voirSupprimeForm')); ");
						if(o.getSeeDeleted() != null && o.getSeeDeleted())
							a("checked", "checked");
					fg();

					e("label").a("for", "Page_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
				} g("form");
			} g("div");
		} g("div");
	}
}
