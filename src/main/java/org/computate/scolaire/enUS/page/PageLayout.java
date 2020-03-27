package org.computate.scolaire.enUS.page;

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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.age.AgeGenPage;
import org.computate.scolaire.enUS.year.YearGenPage;
import org.computate.scolaire.enUS.block.BlockGenPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.school.SchoolGenPage;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.child.ChildGenPage;
import org.computate.scolaire.enUS.guardian.GuardianGenPage;
import org.computate.scolaire.enUS.enrollment.EnrollmentGenPage;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage;
import org.computate.scolaire.enUS.mom.MomGenPage;
import org.computate.scolaire.enUS.page.part.PagePart;
import org.computate.scolaire.enUS.payment.PaymentGenPage;
import org.computate.scolaire.enUS.dad.DadGenPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.season.SeasonGenPage;
import org.computate.scolaire.enUS.session.SessionGenPage;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.enUS.xml.UtilXml;

public class PageLayout extends PageLayoutGen<Object> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");

	public static final List<String> ROLE_READS = Arrays.asList("User");

	public static List<String> HTML_CLOSED_ELEMENTS = Arrays.asList("area", "base", "br", "col", "command", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");

	public static List<String> HTML_ELEMENTS_NO_WRAP = Arrays.asList("script", "span", "a", "b", "i", "u", "title", "use", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "p", "textarea");

	public static DateTimeFormatter FORMATDateTimeShort = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.US);

	public static DateTimeFormatter FORMATDateShort = DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US);

	public static DateTimeFormatter FORMATMonthYear = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);

	public static DateTimeFormatter FORMATDateDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy", Locale.US);

	public static DateTimeFormatter FORMATDateTimeDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy h:mm a:ss.SSS", Locale.US);

	public static DateTimeFormatter FORMATZonedDateTimeDisplay = DateTimeFormatter.ofPattern("EEEE MMMM d yyyy H:mm:ss.SSS zz VV", Locale.US);

	public static DateTimeFormatter FORMATTimeDisplay = DateTimeFormatter.ofPattern("h:mm a", Locale.US);

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
		c.o(" /school");
	}

	protected void _pageUserUri(Wrap<String> c) {
		c.o("/user");
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

	protected void _listSchool(SearchList<School> l) {
		l.setQuery("*:*");
		l.setC(School.class);
		l.setStore(true);
		l.addFilterQuery("deleted_indexed_boolean:false");
		l.addFilterQuery("archived_indexed_boolean:false");
	}

	protected void _schools(Wrap<List<School>> c) {
		c.o(listSchool.getList());
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
		e("script").a("src", staticBaseUrl, "/js/sockjs.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/vertx-eventbus.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SiteUserPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SchoolPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SeasonPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SessionPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/MomPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/DadPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/GuardianPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/ChildPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/moment.min.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/jqDatePicker.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/jquery.serialize-object.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/jSignature.min.js").f().g("script");
		e("script").a("src", "https://kit.fontawesome.com/59d19567d5.js").f().g("script");
	}

	@Override()
	public void  htmlScriptPageLayout() {
	}

	@Override()
	public void  htmlStylesPageLayout() {
		e("link").a("rel", "stylesheet").a("href", staticBaseUrl, "/css/w3.css").fg();
		e("link").a("rel", "stylesheet").a("href", staticBaseUrl, "/css/site-enUS.css").fg();
		e("link").a("rel", "stylesheet").a("href", staticBaseUrl, "/css/datePicker.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "https://www.w3schools.com/w3css/4/w3.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "/static/css/site-enUS.css").fg();
//		e("link").a("rel", "stylesheet").a("href", "/static/css/datePicker.css").fg();
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
						e("div").a("class", "w3-content w3-center w3-black ").f();
							e("div").a("class", "").f();
								menu("Menu1");
							g("div"); 
						g("div");
						e("div").a("id", "site-section-primary").a("class", "site-section-primary w3-text-black w3-padding-bottom-32 ").f();
							e("div").a("class", "w3-content ").f();
	
								htmlBody();

								e("footer").a("class", "w3-center w3-black w3-padding-48 w3-margin-top ").f();
									e("div").f();
										e("a").a("href", "https://github.com/computate/computate-scolaire").a("class", "w3-xlarge ").f();
											sx("This site is open source");
										g("a");
									g("div");
									e("div").f();
										e("a").a("href", "https://github.com/computate/computate-scolaire").a("class", "w3-large ").f();
											sx("View the source code here");
										g("a");
									g("div");
									e("div").a("class", "grow-30 w3-margin ").f();
										e("a").a("href", "https://www.openshift.com/").a("target", "_blank").f();
											e("span").a("class", "w3-large ").f();
												sx("Powered by ");
											g("span");
											e("img").a("alt", "").a("class", "w3-image ").a("style", "display: inline-block; width: 200px; margin: 0 10px;").a("src", staticBaseUrl, "/svg/openshift.svg").fg();
										g("a");
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
								e("h6").a("id", "h2-contactez-nous").a("class",  "w3-padding w3-xlarge w3-text-white ").f();
									sx("Let's get connected. ");
								g("h6");
								e("div").a("class", "w3-cell-row ").f();
									e("div").a("class", "w3-cell ").f();
										e("a").a("target", "_blank").a("rel", "noopener noreferrer").a("data-ajax", "false").a("href", "https://www.facebook.com/littleorchardpreschool/").f();
											e("img").a("alt", "").a("class", "grow-30 ").a("style", "display: inline-block; width: 50px; height: 50px; margin: 0 10px;").a("src", staticBaseUrl, "/svg/facebook.svg").fg();
										g("a");
									g("div");
								g("div");
								e("div").a("class", "w3-cell-row w3-text-white ").f();
									for(int i = 0; i < schools.size(); i++) {
										School school = schools.get(i);

										e("div").a("class", "w3-cell ").f();
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx(school.getSchoolLocation()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Address: ").g("span");
												e("span").f().sx(school.getSchoolAddress()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Phone: ").g("span");
												e("span").f().sx(school.getSchoolPhoneNumber()).g("span");
											g("div");
											e("div").f();
												e("span").a("class", "font-weight-bold ").f().sx("Email: ").g("span");
												e("span").f().sx(school.getSchoolEmail()).g("span");
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
					g("div");
				g("div");
			g("body");
		g("html");

	}

	public void  menu(String id) {
		e("div").a("class", "w3-bar w3-text-white w3-padding-bottom-8 w3-padding-top-8 ").a("style", "padding-left: 16px; padding-right: 16px; ").f();

			e("div").a("class", "site-bar-item w3-bar-item ").f();
				e("a").a("class", "").a("href", pageHomeUri).f();
					e("span").a("class", "site-menu-item").f();
						sx("Home");
					g("span");
				g("a");
			g("div");

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {

				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-pink ").f();
							e("i").a("class", "far fa-school ").f().g("i");
							sx("schools");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						SchoolGenPage.htmlSuggestedSchoolGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-orange ").f();
							e("i").a("class", "far fa-calendar-check ").f().g("i");
							sx("years");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						YearGenPage.htmlSuggestedYearGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-yellow ").f();
							e("i").a("class", "far fa-sun ").f().g("i");
							sx("seasons");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						SeasonGenPage.htmlSuggestedSeasonGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-green ").f();
							e("i").a("class", "fad fa-graduation-cap ").f().g("i");
							sx("sessions");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						SessionGenPage.htmlSuggestedSessionGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-blue ").f();
							e("i").a("class", "fad fa-birthday-cake ").f().g("i");
							sx("ages");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						AgeGenPage.htmlSuggestedAgeGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-indigo ").f();
							e("i").a("class", "far fa-bell ").f().g("i");
							sx("blocks");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						BlockGenPage.htmlSuggestedBlockGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-khaki ").f();
							e("i").a("class", "far fa-drafting-compass ").f().g("i");
							sx("designs");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						EnrollmentDesignGenPage.htmlSuggestedEnrollmentDesignGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-orange ").f();
							e("i").a("class", "far fa-child ").f().g("i");
							sx("children");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						ChildGenPage.htmlSuggestedChildGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-pink ").f();
							e("i").a("class", "far fa-female ").f().g("i");
							sx("moms");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						MomGenPage.htmlSuggestedMomGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-light-blue ").f();
							e("i").a("class", "far fa-male ").f().g("i");
							sx("dads");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						DadGenPage.htmlSuggestedDadGenPage(this, id, null);
					} g("div");
				} g("div");
	
				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-yellow ").f();
							e("i").a("class", "far fa-phone ").f().g("i");
							sx("guardians");
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						GuardianGenPage.htmlSuggestedGuardianGenPage(this, id, null);
					} g("div");
				} g("div");
			}

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-purple ").f();
						e("i").a("class", "fas fa-edit ").f().g("i");
						sx("enrollments");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					EnrollmentGenPage.htmlSuggestedEnrollmentGenPage(this, id, null);
				} g("div");
			} g("div");

			{ e("div").a("class", "w3-dropdown-hover ").f();
				{ e("div").a("class", "w3-button w3-hover-green ").f();
						e("i").a("class", "fas fa-search-dollar ").f().g("i");
						sx("payments");
				} g("div");
				{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
					PaymentGenPage.htmlSuggestedPaymentGenPage(this, id, null);
				} g("div");
			} g("div");

			if(siteRequest_.getUserId() == null) {
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "w3-padding ").a("href", pageUserUri).f(); 
						e("span").a("class", "site-menu-item").f();
							sx("Login");
						g("span");
					g("a");
				g("div");
			}
			if(siteRequest_.getUserId() != null) {

				{ e("div").a("class", "w3-dropdown-hover ").f();
					{ e("div").a("class", "w3-button w3-hover-green ").f();
							e("i").a("class", "far fa-user-cog ").f().g("i");
							sx(siteRequest_.getUserName());
					} g("div");
					{ e("div").a("class", "w3-dropdown-content w3-card-4 w3-padding ").f();
						SiteUser o = siteRequest_.getSiteUser();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/user/", siteRequest_.getUserKey()).a("class", "").f();
								e("i").a("class", "far fa-user ").f().g("i");
								sx("my user page");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							e("label").a("for", "Page_seeArchived").a("class", "").f().sx("see archived").g("label");
							e("input")
								.a("type", "checkbox")
								.a("value", "true")
								.a("class", "setSeeArchived")
								.a("name", "setSeeArchived")
								.a("id", "Page_seeArchived")
								.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + ", siteRequest_.getUserKey(), " }], 'setSeeArchived', $(this).prop('checked'), function() { addGlow($('#Page_seeArchived')); }, function() { addError($('#Page_seeArchived')); }); ")
								;
								if(o.getSeeArchived() != null && o.getSeeArchived())
									a("checked", "checked");
							fg();
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							e("label").a("for", "Page_seeDeleted").a("class", "").f().sx("see deleted").g("label");
							e("input")
								.a("type", "checkbox")
								.a("value", "true")
								.a("class", "setSeeDeleted")
								.a("name", "setSeeDeleted")
								.a("id", "Page_seeDeleted")
								.a("onchange", "patchSiteUserVal([{ name: 'fq', value: 'pk:' + ", siteRequest_.getUserKey(), " }], 'setSeeDeleted', $(this).prop('checked'), function() { addGlow($('#Page_seeDeleted')); }, function() { addError($('#Page_seeDeleted')); }); ")
								;
								if(o.getSeeDeleted() != null && o.getSeeDeleted())
									a("checked", "checked");
							fg();
						} g("div");
					} g("div");
				} g("div");
				e("div").a("class", "site-bar-item w3-bar-item ").f();
					e("a").a("class", "w3-padding ").a("href", pageLogoutUri).f();
						e("span").a("class", "site-menu-item").f();
							sx("Logout");
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
						"application/pdf".equals(pageContentType) && BooleanUtils.isNotTrue(pdfExclude)
						|| !"application/pdf".equals(pageContentType) && BooleanUtils.isNotTrue(htmlExclude)
						) {
					s(htmlPart.getHtmlBefore());
					if(htmlVar != null) {
	
						Object parent = StringUtils.contains(htmlVar, ".") ? obtainForClass(StringUtils.substringBeforeLast(htmlVar, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVar, ".");
						if(StringUtils.isBlank(var))
							var = htmlVar;
	
						if(htmlVarForEach != null) {
		
							Object parentForEach = StringUtils.contains(htmlVarForEach, ".") ? obtainForClass(StringUtils.substringBeforeLast(htmlVarForEach, ".")) : null;
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
										i = htmlPageLayout2(htmlPartList, htmlPart, forStart, size);
									} catch (RuntimeException e) {
										throw e;
									} catch (Exception e) {
										throw new RuntimeException(String.format("Could not call method %s of var %s and object: %s", "set" + StringUtils.capitalize(var), htmlVar, parent), e);
									}
								}
								if(list.size() == 0) {
									i = htmlPageLayout2(htmlPartList, htmlPart, -forStart, size);
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
									s(s);
								}
							} catch (Exception e) {
								s(obtainForClass(htmlVar));
							}
						}
					}
					if(htmlVarForm != null) {
						Object parent = StringUtils.contains(htmlVarForm, ".") ? obtainForClass(StringUtils.substringBeforeLast(htmlVarForm, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVarForm, ".");
						if(StringUtils.isBlank(var))
							var = htmlVarForm;
	
	//					Object o = obtainForClass(StringUtils.substringBeforeLast(htmlVarForm, "."));
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
						Object parent = StringUtils.contains(htmlVarInput, ".") ? obtainForClass(StringUtils.substringBeforeLast(htmlVarInput, ".")) : null;
						if(parent == null)
							parent = this;
						String var = StringUtils.substringAfterLast(htmlVarInput, ".");
						if(StringUtils.isBlank(var))
							var = htmlVarInput;
	
						try {
	//					Object o = obtainForClass(StringUtils.substringBeforeLast(htmlVarInput, "."));
	//					String var = StringUtils.substringAfterLast(htmlVarInput, ".");
							if("application/pdf".equals(pageContentType)) {
								Object o = obtainForClass(htmlVarInput);
								if(o instanceof Boolean) {
									e("img").a("class", "").a("style", "width: 1em; height: 1em; position: relative; top: 3px; ").a("src", siteRequest_.getSiteConfig_().getStaticBaseUrl(), ((Boolean)o) ? "/png/check-square-o.png" : "/png/square-o.png").fg();
								}
								else if (o instanceof String && o.toString().startsWith("data:image")) {
									e("img").a("class", "").a("style", "").a("src", o.toString()).fg();
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
					s(htmlPart.getHtmlAfter());
				}
			}
		}

		return i;
	}
}
