package org.computate.scolaire.frFR.paiement;

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
public class PaiementGenPage extends PaiementGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listePaiementScolaire(Couverture<ListeRecherche<PaiementScolaire>> c) {
	}

	protected void _paiementScolaire(Couverture<PaiementScolaire> c) {
		if(listePaiementScolaire != null && listePaiementScolaire.size() == 1)
			c.o(listePaiementScolaire.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("paiements");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(paiementScolaire != null && paiementScolaire.getPaiementNomComplet() != null)
			c.o(paiementScolaire.getPaiementNomComplet());
		else if(paiementScolaire != null)
			c.o("");
		else if(listePaiementScolaire == null || listePaiementScolaire.size() == 0)
			c.o("aucun paiement trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/paiement");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/paiement-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("solid");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("dollar");
	}

	@Override public void initLoinPaiementGenPage() {
		initPaiementGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsPaiementGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptPaiementGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggerePaiementScolaireInscriptionCles($('#formPaiementScolaireInscriptionCles'), $('#listPaiementScolaireInscriptionCles')); ");
		l("});");
	}

	public void htmlFormPagePaiementScolaire(PaiementScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strPaiementId()).g("span");
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
					{ e("form").a("action", "").a("id", "formPaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurPaiementCles")
							.a("class", "valeurPaiementCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggerePaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
									e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
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

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "inscriptions")
											.a("title", "La clé primaire des enfants dans la base de données. ")
											.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setInscriptionCles")
											.a("id", "Page_inscriptionCles")
											.a("autocomplete", "off")
											.a("oninput", "suggerePaiementScolaireInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'PaiementScolaireInscriptionCles'), $('#listPaiementScolaireInscriptionCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
											.a("onclick", "postInscriptionScolaireVals({ paiementCles: ", o.getPk(), " }, function() { var $e = $('#Page_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_inscriptionCles')); }); ")
											.f().sx("ajouter une inscription")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTPaiementScolaire(PaiementScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPaiementId()).g("span");
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
				{ e("form").a("action", "").a("id", "formPaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurPaiementCles")
						.a("class", "valeurPaiementCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggerePaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
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

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "inscriptions")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setInscriptionCles")
										.a("id", "POST_inscriptionCles")
										.a("autocomplete", "off")
										.a("oninput", "suggerePaiementScolaireInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'PaiementScolaireInscriptionCles'), $('#listPaiementScolaireInscriptionCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postInscriptionScolaireVals({ paiementCles: ", o.getPk(), " }, function() { var $e = $('#POST_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_inscriptionCles')); }); ")
										.f().sx("ajouter une inscription")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHPaiementScolaire(PaiementScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPaiementId()).g("span");
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
				{ e("form").a("action", "").a("id", "formPaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurPaiementCles")
						.a("class", "valeurPaiementCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggerePaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
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

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "inscriptions")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setInscriptionCles")
										.a("id", "PATCH_inscriptionCles")
										.a("autocomplete", "off")
										.a("oninput", "suggerePaiementScolaireInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'PaiementScolaireInscriptionCles'), $('#listPaiementScolaireInscriptionCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postInscriptionScolaireVals({ paiementCles: ", o.getPk(), " }, function() { var $e = $('#PATCH_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_inscriptionCles')); }); ")
										.f().sx("ajouter une inscription")
									.g("button");
								} g("div");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPaiementNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRecherchePaiementScolaire(PaiementScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
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
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPaiementId()).g("span");
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
				{ e("form").a("action", "").a("id", "formPaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurPaiementCles")
						.a("class", "valeurPaiementCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggerePaiementScolaireInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
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

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "inscriptions")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setInscriptionCles")
										.a("id", "Recherche_inscriptionCles")
										.a("autocomplete", "off")
										.a("oninput", "suggerePaiementScolaireInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'PaiementScolaireInscriptionCles'), $('#listPaiementScolaireInscriptionCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postInscriptionScolaireVals({ paiementCles: ", o.getPk(), " }, function() { var $e = $('#Recherche_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_inscriptionCles')); }); ")
										.f().sx("ajouter une inscription")
									.g("button");
								} g("div");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPaiementNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyPaiementGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listePaiementScolaire == null || listePaiementScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("paiements").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun paiement trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listePaiementScolaire != null && listePaiementScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PaiementScolaire o = listePaiementScolaire.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/paiement").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("paiements").g("span");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listePaiementScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listePaiementScolaire.size(); i++) {
						PaiementScolaire o = listePaiementScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/paiement/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.getPaiementNomComplet());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listePaiementScolaire != null && listePaiementScolaire.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			PaiementScolaire o = listePaiementScolaire.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "PaiementScolaireForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPagePaiementScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsPaiementGenPage();
		htmlSuggerePaiementGenPage();
		g("div");
	}

	public void htmlBodyFormsPaiementGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postPaiementScolaireModale').show(); ")
			.f().sx("Créer un paiement")
		.g("button");
		{ e("div").a("id", "postPaiementScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postPaiementScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer un paiement").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					PaiementScolaire o = new PaiementScolaire();

					// Form POST
					{ e("form").a("action", "").a("id", "postPaiementScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTPaiementScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postPaiementScolaire($('#postPaiementScolaireForm')); ")
						.f().sx("Créer un paiement")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchPaiementScolaireModale').show(); ")
			.f().sx("Modifier des paiements")
		.g("button");
		{ e("div").a("id", "patchPaiementScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchPaiementScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modifier des paiements").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					PaiementScolaire o = new PaiementScolaire();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchPaiementScolaireFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRecherchePaiementScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "recherchePaiementScolaire($('#patchPaiementScolaireFormulaireFiltres')); ")
						.f().sx("Rechercher des un paiement")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchPaiementScolaireFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHPaiementScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchPaiementScolaire($('#patchPaiementScolaireFormulaireFiltres'), $('#patchPaiementScolaireFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des paiements")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#deletePaiementScolaireModale').show(); ")
			.f().sx("Supprimer des paiements")
		.g("button");
		{ e("div").a("id", "deletePaiementScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deletePaiementScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des paiements").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					PaiementScolaire o = new PaiementScolaire();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deletePaiementScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHPaiementScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "deletePaiementScolaire(); ")
						.f().sx("Supprimer des paiements")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestPaymentGenPage
	 * r: "/paiement"
	 * r.enUS: "/payment"
	 * r: "voir tous les paiements"
	 * r.enUS: "see all the payments"
	 * r: "rechargerPaiementGenPage"
	 * r.enUS: "refreshPaymentGenPage"
	 * r: "recharger tous les paiements"
	 * r.enUS: "refresh all the payments"
	 * r: "rechercher paiements : "
	 * r.enUS: "search payments: "
	 * r: "suggereFormPaiementScolaire"
	 * r.enUS: "suggestFormSchoolPayment"
	 * r: "rechercher paiements"
	 * r.enUS: "search payments"
	 * r: "suggerePaiementScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolPayment w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggerePaiementScolaire"
	 * r.enUS: "suggestSchoolPayment"
	 * r: patchPaiementScolaireVals
	 * r.enUS: patchSchoolPaymentVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerPaiementGenPage
	 * r.enUS: refreshPaymentGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggerePaiementScolaireObjetSuggere
	 * r.enUS: suggestSchoolPaymentObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListPaiementScolaire'
	 * r.enUS: '#suggestListSchoolPayment'
	 * r: "suggereListPaiementScolaire"
	 * r.enUS: "suggestListSchoolPayment"
	**/
	public void htmlSuggerePaiementGenPage() {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("a").a("href", "/paiement").a("class", "").f();
					e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
					sx("voir tous les paiements");
				} g("a");
			} g("div");
			{ e("div").a("class", "w3-cell ").f();
				{ e("a").a("id", "rechargerPaiementGenPage").a("href", "/paiement").a("class", "").a("onclick", "patchPaiementScolaireVals([], {}, function() { ajouterLueur($('#rechargerPaiementGenPage')); }, function() { ajouterErreur($('#rechargerPaiementGenPage')); }); return false; ").f();
					e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					sx("recharger tous les paiements");
				} g("a");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("span").f();
					sx("rechercher paiements : ");
				} g("span");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("div").a("class", "w3-cell-row ").f();

					e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ e("form").a("action", "").a("id", "suggereFormPaiementScolaire").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "text")
							.a("placeholder", "rechercher paiements")
							.a("class", "suggerePaiementScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggerePaiementScolaire")
							.a("id", "suggerePaiementScolaire")
							.a("autocomplete", "off")
							.a("oninput", "suggerePaiementScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListPaiementScolaire')); ")
							.fg();

					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListPaiementScolaire").f();
				} g("ul");
			} g("div");
		} g("div");
	}

}