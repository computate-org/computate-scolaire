package org.computate.scolaire.frFR.ecole;

import org.computate.scolaire.frFR.cluster.ClusterPage;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.page.MiseEnPage;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;


/**
 * Traduire: false
 **/
public class EcoleGenPage extends EcoleGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeEcole(Couverture<ListeRecherche<Ecole>> c) {
	}

	protected void _ecole(Couverture<Ecole> c) {
		if(listeEcole != null && listeEcole.size() == 1)
			c.o(listeEcole.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
		if(ecole != null && ecole.getPageH1() != null)
			c.o(ecole.getPageH1());
		else if(ecole != null)
			c.o("une école");
		else if(listeEcole == null || listeEcole.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(ecole != null)
			c.o("");
		else if(listeEcole == null || listeEcole.size() == 0)
			c.o("aucune école trouvée");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/frFR/ecole");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/frFR/ecole-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("duotone");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("school");
	}

	@Override public void initLoinEcoleGenPage() {
		initEcoleGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsEcoleGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/EcolePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/AnneePage.js").f().g("script");
	}

	@Override public void htmlScriptEcoleGenPage() {
		l("$(document).ready(function() {");
		tl(1, "rechercherEcoleAnneeCles($('#formEcoleAnneeCles'), $('#listEcoleAnneeCles')); ");
		l("});");
	}

	public void htmlFormPageEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("class", "").f().sx("clé primaire").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strPk()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("class", "").f().sx("crée").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strCree()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("class", "").f().sx("modifié").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strModifie()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strEcoleId()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-pink ").f();
								e("label").a("for", "Page_ecoleNom").a("class", "").f().sx("nom de l'école").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "nom de l'école")
										.a("title", "Nom de l'école. ")
										.a("class", "setEcoleNom w3-input w3-border ")
										.a("name", "setEcoleNom")
										.a("id", "Page_ecoleNom")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleNom', $(this).val(), function() { ajouterLueur($('#Page_ecoleNom')); }, function() { ajouterErreur($('#Page_ecoleNom')); }); ")
										.a("value", o.strEcoleNom())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
									.a("onclick", "enleverLueur($('#Page_ecoleNom')); $('#Page_ecoleNom').val(null); patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleNom', null, $('#Page_ecoleNom'), function() { ajouterLueur($('#Page_ecoleNom')); }, function() { ajouterErreur($('#Page_ecoleNom')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-pink ").f();
								e("label").a("for", "Page_ecoleAdministrateurNom").a("class", "").f().sx("administrateur de l'école").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "administrateur de l'école")
										.a("title", "Description.enUS: ")
										.a("class", "setEcoleAdministrateurNom w3-input w3-border ")
										.a("name", "setEcoleAdministrateurNom")
										.a("id", "Page_ecoleAdministrateurNom")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleAdministrateurNom', $(this).val(), function() { ajouterLueur($('#Page_ecoleAdministrateurNom')); }, function() { ajouterErreur($('#Page_ecoleAdministrateurNom')); }); ")
										.a("value", o.strEcoleAdministrateurNom())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
									.a("onclick", "enleverLueur($('#Page_ecoleAdministrateurNom')); $('#Page_ecoleAdministrateurNom').val(null); patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleAdministrateurNom', null, $('#Page_ecoleAdministrateurNom'), function() { ajouterLueur($('#Page_ecoleAdministrateurNom')); }, function() { ajouterErreur($('#Page_ecoleAdministrateurNom')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-pink ").f();
								e("label").a("for", "Page_ecoleNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "numéro de téléphone")
										.a("title", "Numéro de téléphone de l'école. ")
										.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
										.a("name", "setEcoleNumeroTelephone")
										.a("id", "Page_ecoleNumeroTelephone")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleNumeroTelephone', $(this).val(), function() { ajouterLueur($('#Page_ecoleNumeroTelephone')); }, function() { ajouterErreur($('#Page_ecoleNumeroTelephone')); }); ")
										.a("value", o.strEcoleNumeroTelephone())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
									.a("onclick", "enleverLueur($('#Page_ecoleNumeroTelephone')); $('#Page_ecoleNumeroTelephone').val(null); patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleNumeroTelephone', null, $('#Page_ecoleNumeroTelephone'), function() { ajouterLueur($('#Page_ecoleNumeroTelephone')); }, function() { ajouterErreur($('#Page_ecoleNumeroTelephone')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-pink ").f();
								e("label").a("for", "Page_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("textarea")
										.a("placeholder", "addresse")
										.a("title", "Description.enUS: ")
										.a("class", "setEcoleAddresse w3-input w3-border ")
										.a("name", "setEcoleAddresse")
										.a("id", "Page_ecoleAddresse")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#Page_ecoleAddresse')); }, function() { ajouterErreur($('#Page_ecoleAddresse')); }); ")
									.f().sx(o.strEcoleAddresse()).g("textarea");

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
									.a("onclick", "enleverLueur($('#Page_ecoleAddresse')); $('#Page_ecoleAddresse').val(null); patchEcoleVal([{ name: 'fq', value: 'pk:' + $('#EcoleForm :input[name=\"pk\"]').val() }], 'setEcoleAddresse', null, $('#Page_ecoleAddresse'), function() { ajouterLueur($('#Page_ecoleAddresse')); }, function() { ajouterErreur($('#Page_ecoleAddresse')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurEcoleCle")
							.a("class", "valeurEcoleCle ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-pink ").f();
								e("label").a("for", "Page_anneeCles").a("class", "").f().sx("années").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "années")
										.a("title", "Description.enUS: ")
										.a("class", "valeurObjetSuggere suggereAnneeCles w3-input w3-border ")
										.a("name", "setAnneeCles")
										.a("id", "Page_anneeCles")
										.a("autocomplete", "off")
										.a("oninput", "rechercherEcoleAnneeCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'EcoleAnneeCles'), $('#listEcoleAnneeCles')); ")
									.fg();

								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEcoleAnneeCles").f();
									} g("ul");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "POST_ecoleNom").a("class", "").f().sx("nom de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de l'école")
									.a("title", "Nom de l'école. ")
									.a("class", "valeurEcoleNom w3-input w3-border ")
									.a("name", "ecoleNom")
									.a("id", "POST_ecoleNom")
									.a("value", o.strEcoleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "POST_ecoleAdministrateurNom").a("class", "").f().sx("administrateur de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "administrateur de l'école")
									.a("title", "Description.enUS: ")
									.a("class", "valeurEcoleAdministrateurNom w3-input w3-border ")
									.a("name", "ecoleAdministrateurNom")
									.a("id", "POST_ecoleAdministrateurNom")
									.a("value", o.strEcoleAdministrateurNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "POST_ecoleNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "numéro de téléphone")
									.a("title", "Numéro de téléphone de l'école. ")
									.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
									.a("name", "ecoleNumeroTelephone")
									.a("id", "POST_ecoleNumeroTelephone")
									.a("value", o.strEcoleNumeroTelephone())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "POST_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "addresse")
									.a("title", "Description.enUS: ")
									.a("class", "valeurEcoleAddresse w3-input w3-border ")
									.a("name", "ecoleAddresse")
									.a("id", "POST_ecoleAddresse")
								.f().sx(o.strEcoleAddresse()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurEcoleCle")
						.a("class", "valeurEcoleCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "POST_anneeCles").a("class", "").f().sx("années").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "années")
									.a("title", "Description.enUS: ")
									.a("class", "valeurObjetSuggere suggereAnneeCles w3-input w3-border ")
									.a("name", "setAnneeCles")
									.a("id", "POST_anneeCles")
									.a("autocomplete", "off")
									.a("oninput", "rechercherEcoleAnneeCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'EcoleAnneeCles'), $('#listEcoleAnneeCles')); ")
								.fg();

							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEcoleAnneeCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "PATCH_ecoleNom").a("class", "").f().sx("nom de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de l'école")
									.a("title", "Nom de l'école. ")
									.a("class", "setEcoleNom w3-input w3-border ")
									.a("name", "setEcoleNom")
									.a("id", "PATCH_ecoleNom")
									.a("value", o.strEcoleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "PATCH_ecoleAdministrateurNom").a("class", "").f().sx("administrateur de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "administrateur de l'école")
									.a("title", "Description.enUS: ")
									.a("class", "setEcoleAdministrateurNom w3-input w3-border ")
									.a("name", "setEcoleAdministrateurNom")
									.a("id", "PATCH_ecoleAdministrateurNom")
									.a("value", o.strEcoleAdministrateurNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "PATCH_ecoleNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "numéro de téléphone")
									.a("title", "Numéro de téléphone de l'école. ")
									.a("class", "setEcoleNumeroTelephone w3-input w3-border ")
									.a("name", "setEcoleNumeroTelephone")
									.a("id", "PATCH_ecoleNumeroTelephone")
									.a("value", o.strEcoleNumeroTelephone())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "PATCH_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "addresse")
									.a("title", "Description.enUS: ")
									.a("class", "setEcoleAddresse w3-input w3-border ")
									.a("name", "setEcoleAddresse")
									.a("id", "PATCH_ecoleAddresse")
								.f().sx(o.strEcoleAddresse()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurEcoleCle")
						.a("class", "valeurEcoleCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "PATCH_anneeCles").a("class", "").f().sx("années").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "années")
									.a("title", "Description.enUS: ")
									.a("class", "valeurObjetSuggere suggereAnneeCles w3-input w3-border ")
									.a("name", "setAnneeCles")
									.a("id", "PATCH_anneeCles")
									.a("autocomplete", "off")
									.a("oninput", "rechercherEcoleAnneeCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'EcoleAnneeCles'), $('#listEcoleAnneeCles')); ")
								.fg();

							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEcoleAnneeCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheEcole(Ecole o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("clé primaire").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPk()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("crée").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCree()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("modifié").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModifie()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-pink ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strEcoleId()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "Recherche_ecoleNom").a("class", "").f().sx("nom de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "nom de l'école")
									.a("title", "Nom de l'école. ")
									.a("class", "valeurEcoleNom w3-input w3-border ")
									.a("name", "ecoleNom")
									.a("id", "Recherche_ecoleNom")
									.a("value", o.strEcoleNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAdministrateurNom").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "Recherche_ecoleAdministrateurNom").a("class", "").f().sx("administrateur de l'école").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "administrateur de l'école")
									.a("title", "Description.enUS: ")
									.a("class", "valeurEcoleAdministrateurNom w3-input w3-border ")
									.a("name", "ecoleAdministrateurNom")
									.a("id", "Recherche_ecoleAdministrateurNom")
									.a("value", o.strEcoleAdministrateurNom())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleNumeroTelephone").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "Recherche_ecoleNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "numéro de téléphone")
									.a("title", "Numéro de téléphone de l'école. ")
									.a("class", "valeurEcoleNumeroTelephone w3-input w3-border ")
									.a("name", "ecoleNumeroTelephone")
									.a("id", "Recherche_ecoleNumeroTelephone")
									.a("value", o.strEcoleNumeroTelephone())
								.fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleEcoleAddresse").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "Recherche_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("textarea")
									.a("placeholder", "addresse")
									.a("title", "Description.enUS: ")
									.a("class", "valeurEcoleAddresse w3-input w3-border ")
									.a("name", "ecoleAddresse")
									.a("id", "Recherche_ecoleAddresse")
								.f().sx(o.strEcoleAddresse()).g("textarea");

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurEcoleCle")
						.a("class", "valeurEcoleCle ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereEcoleAnneeCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", "Recherche_anneeCles").a("class", "").f().sx("années").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "années")
									.a("title", "Description.enUS: ")
									.a("class", "valeurObjetSuggere suggereAnneeCles w3-input w3-border ")
									.a("name", "setAnneeCles")
									.a("id", "Recherche_anneeCles")
									.a("autocomplete", "off")
									.a("oninput", "rechercherEcoleAnneeCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'EcoleAnneeCles'), $('#listEcoleAnneeCles')); ")
								.fg();

							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEcoleAnneeCles").f();
								} g("ul");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyEcoleGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeEcole == null || listeEcole.size() == 0) {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("aucune école trouvée").g("span");
			} g("h1");
		} else if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("h1");
				Ecole o = listeEcole.get(0);
				requeteSite_.setRequetePk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("écoles").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("années").g("th");
						e("th").f().sx("nom de l'école").g("th");
						e("th").f().sx("numéro de téléphone").g("th");
						e("th").f().sx("administrateur de l'école").g("th");
						e("th").f().sx("addresse").g("th");
						e("th").f().sx("clé primaire").g("th");
						e("th").f().sx("crée").g("th");
						e("th").f().sx("modifié").g("th");
						e("th").f().sx("ID").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeEcole.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeEcole.size(); i++) {
						Ecole o = listeEcole.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/frFR/ecole/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getAnneeCles());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleNom());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleNumeroTelephone());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleAdministrateurNom());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleAddresse());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getPk());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getCree());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getModifie());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getEcoleId());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeEcole != null && listeEcole.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Ecole o = listeEcole.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "EcoleForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageEcole(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsEcoleGenPage();
	}

	public void htmlBodyFormsEcoleGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postEcoleModale').show(); ")
			.f().sx("Créer une école")
		.g("button");
		{ e("div").a("id", "postEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une école").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// Form POST
					{ e("form").a("action", "").a("id", "postEcoleForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "postEcole($('#postEcoleForm')); ")
						.f().sx("Créer une école")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchEcoleModale').show(); ")
			.f().sx("Modifier des écoles")
		.g("button");
		{ e("div").a("id", "patchEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchEcoleFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "rechercheEcole($('#patchEcoleFormulaireFiltres')); ")
						.f().sx("Rechercher des une école")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchEcoleFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchEcole($('#patchEcoleFormulaireFiltres'), $('#patchEcoleFormulaireValeurs')); ")
						.f().sx("Modifier des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteEcoleModale').show(); ")
			.f().sx("Supprimer des écoles")
		.g("button");
		{ e("div").a("id", "deleteEcoleModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEcoleModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des écoles").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Ecole o = new Ecole();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteEcoleForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHEcole(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteEcole(); ")
						.f().sx("Supprimer des écoles")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
