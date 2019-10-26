package org.computate.scolaire.frFR.inscription.form;

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
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.form.EnrollmentFormGenPage
 **/
public class FormInscriptionGenPage extends FormInscriptionGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listeFormInscription(Couverture<ListeRecherche<FormInscription>> c) {
	}

	protected void _formInscription(Couverture<FormInscription> c) {
		if(listeFormInscription != null && listeFormInscription.size() == 1)
			c.o(listeFormInscription.get(0));
	}

	@Override protected void _pageH1(Couverture<String> c) {
			c.o("formulaire d'inscriptions");
	}

	@Override protected void _pageH2(Couverture<String> c) {
		if(formInscription != null && formInscription.getFormInscriptionNomComplet() != null)
			c.o(formInscription.getFormInscriptionNomComplet());
	}

	@Override protected void _pageH3(Couverture<String> c) {
		c.o("");
	}

	@Override protected void _pageTitre(Couverture<String> c) {
		if(formInscription != null && formInscription.getFormInscriptionNomComplet() != null)
			c.o(formInscription.getFormInscriptionNomComplet());
		else if(formInscription != null)
			c.o("");
		else if(listeFormInscription == null || listeFormInscription.size() == 0)
			c.o("aucun formulaire d'inscription trouvé");
	}

	@Override protected void _pageUri(Couverture<String> c) {
		c.o("/form-inscription");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/form-inscription-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("regular");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("bell");
	}

	@Override public void initLoinFormInscriptionGenPage() {
		initFormInscriptionGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsFormInscriptionGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/FormInscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PartFormPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
	}

	@Override public void htmlScriptFormInscriptionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggereFormInscriptionPartFormCles($('#formFormInscriptionPartFormCles'), $('#listFormInscriptionPartFormCles_Page')); ");
		tl(1, "suggereFormInscriptionInscriptionCles($('#formFormInscriptionInscriptionCles'), $('#listFormInscriptionInscriptionCles_Page')); ");
		l("});");
	}

	public void htmlFormPageFormInscription(FormInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strObjetId()).g("span");
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
					{ e("form").a("action", "").a("id", "formFormInscriptionArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereFormInscriptionArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_archive").a("class", "").f().sx("archivé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setArchive")
										.a("name", "setArchive")
										.a("id", "Page_archive")
										.a("onchange", "patchFormInscriptionVal([{ name: 'fq', value: 'pk:' + $('#FormInscriptionForm :input[name=\"pk\"]').val() }], 'setArchive', $(this).prop('checked'), function() { ajouterLueur($('#Page_archive')); }, function() { ajouterErreur($('#Page_archive')); }); ")
										;
										if(o.getArchive() != null && o.getArchive())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formFormInscriptionSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereFormInscriptionSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-indigo ").f();
								e("label").a("for", "Page_supprime").a("class", "").f().sx("supprimé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setSupprime")
										.a("name", "setSupprime")
										.a("id", "Page_supprime")
										.a("onchange", "patchFormInscriptionVal([{ name: 'fq', value: 'pk:' + $('#FormInscriptionForm :input[name=\"pk\"]').val() }], 'setSupprime', $(this).prop('checked'), function() { ajouterLueur($('#Page_supprime')); }, function() { ajouterErreur($('#Page_supprime')); }); ")
										;
										if(o.getSupprime() != null && o.getSupprime())
											a("checked", "checked");
									fg();

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
					{ e("form").a("action", "").a("id", "formFormInscriptionPartFormCles").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurFormInscriptionCle")
							.a("class", "valeurFormInscriptionCle ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereFormInscriptionPartFormCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
									e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
									sx("parts");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a ce formulaire d'inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "parts")
											.a("class", "valeurObjetSuggere suggerePartFormCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setPartFormCles")
											.a("id", "Page_partFormCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereFormInscriptionPartFormCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'FormInscriptionPartFormCles'), $('#listFormInscriptionPartFormCles_Page')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listFormInscriptionPartFormCles_Page").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postPartFormVals({ formInscriptionCle: \"", o.getPk(), "\" }, function() { patchFormInscriptionVals([{ name: 'fq', value: 'pk:", o.getPk(), "' }], {}, function() { suggereFormInscriptionPartFormCles($('#' + ($('#Page_partFormCles').val() ? 'suggere' : 'form') + 'FormInscriptionPartFormCles'), $('#listFormInscriptionPartFormCles_Page')); var $e = $('#Page_partFormCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_partFormCles')); }); }, function() { ajouterErreur($('#Page_partFormCles')); });")
											.f().sx("ajouter un part de formulaire")
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
					{ e("form").a("action", "").a("id", "formFormInscriptionInscriptionCles").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurBlocCles")
							.a("class", "valeurBlocCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereFormInscriptionInscriptionCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
									e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
									sx("inscriptions");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a ce formulaire d'inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "inscriptions")
											.a("class", "valeurObjetSuggere suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setInscriptionCles")
											.a("id", "Page_inscriptionCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereFormInscriptionInscriptionCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'FormInscriptionInscriptionCles'), $('#listFormInscriptionInscriptionCles_Page')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listFormInscriptionInscriptionCles_Page").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
											.a("onclick", "postInscriptionScolaireVals({ blocCles: [ \"", o.getPk(), "\" ] }, function() { patchFormInscriptionVals([{ name: 'fq', value: 'pk:", o.getPk(), "' }], {}, function() { suggereFormInscriptionInscriptionCles($('#' + ($('#Page_inscriptionCles').val() ? 'suggere' : 'form') + 'FormInscriptionInscriptionCles'), $('#listFormInscriptionInscriptionCles_Page')); var $e = $('#Page_inscriptionCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_inscriptionCles')); }); }, function() { ajouterErreur($('#Page_inscriptionCles')); });")
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

	public void htmlFormPOSTFormInscription(FormInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
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
				{ e("form").a("action", "").a("id", "formFormInscriptionArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurArchive")
									.a("name", "archive")
									.a("id", "POST_archive")
									;
									if(o.getArchive() != null && o.getArchive())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormInscriptionSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "POST_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSupprime")
									.a("name", "supprime")
									.a("id", "POST_supprime")
									;
									if(o.getSupprime() != null && o.getSupprime())
										a("checked", "checked");
								fg();

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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("parts").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPartFormCles()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHFormInscription(FormInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
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
				{ e("form").a("action", "").a("id", "formFormInscriptionArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setArchive")
									.a("name", "setArchive")
									.a("id", "PATCH_archive")
									;
									if(o.getArchive() != null && o.getArchive())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormInscriptionSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "PATCH_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setSupprime")
									.a("name", "setSupprime")
									.a("id", "PATCH_supprime")
									;
									if(o.getSupprime() != null && o.getSupprime())
										a("checked", "checked");
								fg();

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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("parts").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPartFormCles()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("nom").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strFormInscriptionNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheFormInscription(FormInscription o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strObjetId()).g("span");
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
				{ e("form").a("action", "").a("id", "formFormInscriptionArchive").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionArchive").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_archive").a("class", "").f().sx("archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurArchive")
									.a("name", "archive")
									.a("id", "Recherche_archive")
									;
									if(o.getArchive() != null && o.getArchive())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formFormInscriptionSupprime").a("style", "display: inline; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereFormInscriptionSupprime").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", "Recherche_supprime").a("class", "").f().sx("supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurSupprime")
									.a("name", "supprime")
									.a("id", "Recherche_supprime")
									;
									if(o.getSupprime() != null && o.getSupprime())
										a("checked", "checked");
								fg();

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
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("parts").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strPartFormCles()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("inscriptions").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionCles()).g("span");
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-indigo ").f();
						e("label").a("class", "").f().sx("nom").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strFormInscriptionNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyFormInscriptionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeFormInscription == null || listeFormInscription.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/form-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("formulaire d'inscriptions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contexteIconeClassesCss != null)
						e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("aucun formulaire d'inscription trouvé").g("span");
				} g("span");
			} g("h2");
		} else if(listeFormInscription != null && listeFormInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			FormInscription o = listeFormInscription.get(0);
			requeteSite_.setRequetePk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/form-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/form-inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contexteIconeClassesCss != null)
							e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listeFormInscription.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listeFormInscription.getQuery(), "_suggested", "");
					Integer rows1 = listeFormInscription.getRows();
					Integer start1 = listeFormInscription.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-inscription?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-inscription?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/form-inscription?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/form-inscription?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " de ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
					{ e("tr").f();
						e("th").f().sx("nom").g("th");
						e("th").f().sx("crée").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeFormInscription.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeFormInscription.size(); i++) {
						FormInscription o = listeFormInscription.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/form-inscription/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strFormInscriptionNomComplet());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCree());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listeFormInscription != null && listeFormInscription.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			FormInscription o = listeFormInscription.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "FormInscriptionForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valeurPk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageFormInscription(o);
				}

			} g("div");

		}
		htmlBodyFormsFormInscriptionGenPage();
		htmlSuggereFormInscriptionGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsFormInscriptionGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("id", "rechargerCeFormInscriptionGenPage")
				.a("onclick", "patchFormInscriptionVals( [ {name: 'fq', value: 'pk:' + " + requeteSite_.getRequetePk() + " } ], {}, function() { ajouterLueur($('#rechargerCeFormInscriptionGenPage')); }, function() { ajouterErreur($('#rechargerCeFormInscriptionGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("recharger ce formulaire d'inscription");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postFormInscriptionModale').show(); ")
			.f().sx("Créer un formulaire d'inscription")
		.g("button");
		{ e("div").a("id", "postFormInscriptionModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postFormInscriptionModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Créer un formulaire d'inscription").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					FormInscription o = new FormInscription();

					// Form POST
					{ e("div").a("id", "postFormInscriptionForm").f();
						htmlFormPOSTFormInscription(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "postFormInscription($('#postFormInscriptionForm')); ")
						.f().sx("Créer un formulaire d'inscription")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchFormInscriptionModale').show(); ")
			.f().sx("Modifier des formulaire d'inscriptions")
		.g("button");
		{ e("div").a("id", "patchFormInscriptionModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchFormInscriptionModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modifier des formulaire d'inscriptions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					FormInscription o = new FormInscription();

					// FormulaireFiltres PATCH
					{ e("form").a("action", "").a("id", "patchFormInscriptionFormulaireFiltres").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormRechercheFormInscription(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "rechercheFormInscription($('#patchFormInscriptionFormulaireFiltres')); ")
						.f().sx("Rechercher des un formulaire d'inscription")
					.g("button");


					// FormulaireValeurs PATCH
					{ e("form").a("action", "").a("id", "patchFormInscriptionFormulaireValeurs").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHFormInscription(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "patchFormInscription($('#patchFormInscriptionFormulaireFiltres'), $('#patchFormInscriptionFormulaireValeurs'), function() {}, function() {}); ")
						.f().sx("Modifier des formulaire d'inscriptions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listeFormInscription != null && listeFormInscription.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#deleteFormInscriptionModale').show(); ")
				.f().sx("Supprimer des formulaire d'inscriptions")
			.g("button");
			{ e("div").a("id", "deleteFormInscriptionModale").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteFormInscriptionModale').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Supprimer des formulaire d'inscriptions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						FormInscription o = new FormInscription();

						// Form DELETE
						{ e("div").a("id", "deleteFormInscriptionForm").f();
							htmlFormPATCHFormInscription(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
							.a("onclick", "deleteFormInscription(", o.getPk(), "); ")
							.f().sx("Supprimer des formulaire d'inscriptions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestEnrollmentFormGenPage
	 * r: "/form-inscription"
	 * r.enUS: "/enrollment-form"
	 * r: "voir tous les formulaire d'inscriptions"
	 * r.enUS: "see all the enrollment forms"
	 * r: "rechargerFormInscriptionGenPage"
	 * r.enUS: "refreshEnrollmentFormGenPage"
	 * r: "recharger tous les formulaire d'inscriptions"
	 * r.enUS: "refresh all the enrollment forms"
	 * r: "rechercher formulaire d'inscriptions : "
	 * r.enUS: "search enrollment forms: "
	 * r: "suggereFormFormInscription"
	 * r.enUS: "suggestFormEnrollmentForm"
	 * r: "rechercher formulaire d'inscriptions"
	 * r.enUS: "search enrollment forms"
	 * r: "suggereFormInscription w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestEnrollmentForm w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereFormInscription"
	 * r.enUS: "suggestEnrollmentForm"
	 * r: patchFormInscriptionVals
	 * r.enUS: patchEnrollmentFormVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerFormInscriptionGenPage
	 * r.enUS: refreshEnrollmentFormGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereFormInscriptionObjetSuggere
	 * r.enUS: suggestEnrollmentFormObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListFormInscription'
	 * r.enUS: '#suggestListEnrollmentForm'
	 * r: "suggereListFormInscription"
	 * r.enUS: "suggestListEnrollmentForm"
	**/
	public static void htmlSuggereFormInscriptionGenPage(MiseEnPage p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/form-inscription").a("class", "").f();
					p.e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
					p.sx("voir tous les formulaire d'inscriptions");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "rechargerTousFormInscriptionGenPage", id).a("href", "/form-inscription").a("class", "").a("onclick", "patchFormInscriptionVals([], {}, function() { ajouterLueur($('#rechargerTousFormInscriptionGenPage", id, "')); }, function() { ajouterErreur($('#rechargerTousFormInscriptionGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("recharger tous les formulaire d'inscriptions");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("rechercher formulaire d'inscriptions : ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggereFormFormInscription", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/form-inscription?q=objetSuggere:' + encodeURIComponent($('#suggereFormInscription", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("placeholder", "rechercher formulaire d'inscriptions")
							.a("class", "suggereFormInscription w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereFormInscription")
							.a("id", "suggereFormInscription", id)
							.a("autocomplete", "off")
							.a("oninput", "suggereFormInscriptionObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListFormInscription", id, "')); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListFormInscription", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
