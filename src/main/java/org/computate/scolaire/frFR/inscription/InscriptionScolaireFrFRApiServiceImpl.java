package org.computate.scolaire.frFR.inscription;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.CustomerProfileIdType;
import net.authorize.api.contract.v1.GetTransactionDetailsRequest;
import net.authorize.api.contract.v1.GetTransactionDetailsResponse;
import net.authorize.api.contract.v1.GetTransactionListForCustomerRequest;
import net.authorize.api.contract.v1.GetTransactionListResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderExType;
import net.authorize.api.contract.v1.Paging;
import net.authorize.api.contract.v1.TransactionDetailsType;
import net.authorize.api.contract.v1.TransactionListOrderFieldEnum;
import net.authorize.api.contract.v1.TransactionListSorting;
import net.authorize.api.contract.v1.TransactionSummaryType;
import net.authorize.api.controller.GetTransactionDetailsController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSApiServiceImpl
 **/
public class InscriptionScolaireFrFRApiServiceImpl extends InscriptionScolaireFrFRGenApiServiceImpl {

	public InscriptionScolaireFrFRApiServiceImpl(SiteContexteFrFR siteContexte) {
		super(siteContexte);
	}

	@Override
	public void rechargerpagerechercheInscriptionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete);
		try {
			utilisateurInscriptionScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					rechercheInscriptionScolaire(requeteSite, false, true, "/recharger-inscription", "RechargerPageRecherche", c -> {
						if(c.succeeded()) {
							try {
								ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = c.result();

								if(listeInscriptionScolaire.size() > 1) {
									throw new RuntimeException("The enrollment refresh page search returned more than one enrollment. ");
								}
								if(listeInscriptionScolaire.size() == 0) {
									throw new RuntimeException("The enrollment refresh page search did not find any enrollment. ");
								}

								InscriptionScolaire schoolEnrollment = listeInscriptionScolaire.first();
								ConfigSite siteConfig = requeteSite.getConfigSite_();
								MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
								String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
								String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
								merchantAuthenticationType.setName(authorizeApiLoginId);
								merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
								ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

								inscriptionFraisFuture(schoolEnrollment, d -> {
									if(d.succeeded()) {
										authorizeNetEnrollmentPaiementsFuture(merchantAuthenticationType, schoolEnrollment, e -> {
											if(e.succeeded()) {
												LOGGER.info("Creating paiements for customer %s succeeded. ");
												rechargerpagerechercheInscriptionScolaireReponse(listeInscriptionScolaire, f -> {
													if(e.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(f.result()));
														LOGGER.info(String.format("rechargerpagerechercheInscriptionScolaire succeeded. "));
													} else {
														LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", f.cause()));
														erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", e.cause()));
												erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", d.cause()));
										erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
									}
								});
							} catch(Exception e) {
								LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", e));
								erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
							}
						} else {
							LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", c.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", b.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechargerpagerechercheInscriptionScolaire failed. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}

	@Override
	public void listePATCHPaiementsInscriptionScolaire(RequeteApi requeteApi, ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		ConfigSite siteConfig = requeteSite.getConfigSite_();
		MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
		String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
		String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
		merchantAuthenticationType.setName(authorizeApiLoginId);
		merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
		ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

		listeInscriptionScolaire.getList().forEach(o -> {
			futures.add(
					inscriptionFraisFuture(o, a -> {
					if(a.succeeded()) {
						authorizeNetEnrollmentPaiementsFuture(merchantAuthenticationType, o, c -> {
							if(a.succeeded()) {
							} else {
								erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
							}
						});
					} else {
						erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeInscriptionScolaire.size());
				reponse200PATCHPaiementsInscriptionScolaire(requeteSite, gestionnaireEvenements);
			} else {
				erreurInscriptionScolaire(listeInscriptionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<InscriptionScolaire> inscriptionFraisFuture(InscriptionScolaire inscriptionScolaire, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = inscriptionScolaire.getRequeteSite_();
		SiteContexteFrFR siteContexteFrFR = requeteSite.getSiteContexte_();
		ConfigSite siteConfig = requeteSite.getConfigSite_();
		Integer paiementJour = siteConfig.getPaiementJour();
		Vertx vertx = requeteSite.getVertx();
		PaiementScolaireFrFRApiServiceImpl paiementService = new PaiementScolaireFrFRApiServiceImpl(siteContexteFrFR);
		Promise<InscriptionScolaire> promise = Promise.promise();
		try {

			ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(PaiementScolaire.class);
			listeRecherche.addFilterQuery("enrollmentKey_indexed_long:" + inscriptionScolaire.getPk());
			listeRecherche.add("json.facet", "{paiementDate:{terms:{field:paiementDate_indexed_date, limit:1000}}}");
			listeRecherche.add("json.facet", "{fraisInscription:{terms:{field:fraisInscription_indexed_boolean, limit:1000}}}");
			listeRecherche.add("json.facet", "{fraisPremierDernier:{terms:{field:fraisPremierDernier_indexed_boolean, limit:1000}}}");
			listeRecherche.setRows(0);
			listeRecherche.initLoinListeRecherche(requeteSite);

			ConfigSite configSite = siteContexteFrFR.getConfigSite();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			Integer fraisInscription = Optional.ofNullable((SimpleOrderedMap)facets.get("fraisInscription")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().filter(m -> BooleanUtils.isTrue((Boolean)m.get("val"))).findFirst().map(m -> (Integer)m.get("count")).orElse(0);
			Integer fraisPremierDernier = Optional.ofNullable((SimpleOrderedMap)facets.get("fraisPremierDernier")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().filter(m -> BooleanUtils.isTrue((Boolean)m.get("val"))).findFirst().map(m -> (Integer)m.get("count")).orElse(0);
			List<LocalDate> paiementsDue = Optional.ofNullable((SimpleOrderedMap)facets.get("paiementDate")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().collect(Collectors.mapping(m -> ((Date)m.get("val")).toInstant().atZone(ZoneId.of(configSite.getSiteZone())).toLocalDate(), Collectors.toList()));
			LocalDate sessionDateDebut = inscriptionScolaire.getSessionDateDebut();
			LocalDate sessionDateFin = inscriptionScolaire.getSessionDateFin();
			LocalDate fraisDateDebut = sessionDateDebut == null ? null : (sessionDateDebut.getDayOfMonth() < paiementJour ? sessionDateDebut.withDayOfMonth(paiementJour).minusMonths(1) : sessionDateDebut.withDayOfMonth(paiementJour));
			LocalDate fraisDateFin = sessionDateFin == null ? null : (sessionDateFin.getDayOfMonth() < paiementJour ? sessionDateFin.withDayOfMonth(paiementJour).minusMonths(1) : sessionDateFin.withDayOfMonth(paiementJour));
			BigDecimal blocPrixParMois = inscriptionScolaire.getBlocPrixParMois();
			BigDecimal anneeFraisInscription = inscriptionScolaire.getAnneeFraisInscription();
			Long inscriptionCle = inscriptionScolaire.getPk();
			Long numMois = fraisDateDebut == null ? null : ChronoUnit.MONTHS.between(fraisDateDebut, fraisDateFin);
			List<Future> futures = new ArrayList<>();

			if(sessionDateDebut != null && anneeFraisInscription != null && fraisInscription == 0) {
				PaiementScolaire o = new PaiementScolaire();
				o.setRequeteSite_(requeteSite);
				o.setFraisMontant(anneeFraisInscription);
				o.setPaiementDate(sessionDateDebut);
				o.setCustomerProfileId(inscriptionScolaire.getCustomerProfileId());
				o.setFraisInscription(true);
				o.setFraisInscription(true);
				o.setInscriptionCle(inscriptionScolaire.getPk());
				o.setInscription_(inscriptionScolaire);

				RequeteSiteFrFR requeteSite2 = requeteSite.copy();
				requeteSite2.setObjetJson(JsonObject.mapFrom(o));
				requeteSite2.initLoinRequeteSiteFrFR(requeteSite);

				RequeteApi requeteApi2 = new RequeteApi();
				requeteApi2.setRows(1);
				requeteApi2.setNumFound(1L);
				requeteApi2.setNumPATCH(0L);
				requeteApi2.initLoinRequeteApi(requeteSite2);
				requeteSite2.setRequeteApi_(requeteApi2);

				futures.add(paiementService.postPaiementScolaireFuture(requeteSite2, false, b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("frais %s créé pour inscription %s. ", sessionDateDebut, inscriptionCle));
					} else {
						LOGGER.error(String.format("créer frais %s a échoué pour inscription %s. ", sessionDateDebut, inscriptionCle), b.cause());
						promise.fail(b.cause());
					}
				}));
			}
			if(blocPrixParMois != null && fraisPremierDernier == 0) {
				PaiementScolaire o = new PaiementScolaire();
				o.setRequeteSite_(requeteSite);
				o.setFraisMontant(blocPrixParMois.multiply(BigDecimal.valueOf(2)));
				o.setPaiementDate(sessionDateDebut);
				o.setCustomerProfileId(inscriptionScolaire.getCustomerProfileId());
				o.setFraisPremierDernier(true);
				o.setInscriptionCle(inscriptionScolaire.getPk());
				o.setInscription_(inscriptionScolaire);

				RequeteSiteFrFR requeteSite2 = requeteSite.copy();
				requeteSite2.setObjetJson(JsonObject.mapFrom(o));
				requeteSite2.initLoinRequeteSiteFrFR(requeteSite);

				RequeteApi requeteApi2 = new RequeteApi();
				requeteApi2.setRows(1);
				requeteApi2.setNumFound(1L);
				requeteApi2.setNumPATCH(0L);
				requeteApi2.initLoinRequeteApi(requeteSite2);
				requeteSite2.setRequeteApi_(requeteApi2);

				futures.add(paiementService.postPaiementScolaireFuture(requeteSite2, false, b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("frais %s créé pour inscription %s. ", sessionDateDebut, inscriptionCle));
					} else {
						LOGGER.error(String.format("créer frais %s a échoué pour inscription %s. ", sessionDateDebut, inscriptionCle), b.cause());
						promise.fail(b.cause());
					}
				}));
			}
			if(numMois != null) {
				for(long i = 0; i < numMois; i++) {
					LocalDate paiementDate = fraisDateDebut.plusMonths(i);
					if(!paiementsDue.contains(paiementDate)) {
						PaiementScolaire o = new PaiementScolaire();
						o.setRequeteSite_(requeteSite);
						o.setFraisMontant(inscriptionScolaire.getBlocPrixParMois());
						o.setPaiementDate(paiementDate);
						o.setCustomerProfileId(inscriptionScolaire.getCustomerProfileId());
						o.setFraisMois(true);
						o.setInscriptionCle(inscriptionScolaire.getPk());
						o.setInscription_(inscriptionScolaire);
	
						RequeteSiteFrFR requeteSite2 = requeteSite.copy();
						requeteSite2.setObjetJson(JsonObject.mapFrom(o));
						requeteSite2.initLoinRequeteSiteFrFR(requeteSite);

						RequeteApi requeteApi2 = new RequeteApi();
						requeteApi2.setRows(1);
						requeteApi2.setNumFound(1L);
						requeteApi2.setNumPATCH(0L);
						requeteApi2.initLoinRequeteApi(requeteSite2);
						requeteSite2.setRequeteApi_(requeteApi2);
	
						futures.add(paiementService.postPaiementScolaireFuture(requeteSite2, false, b -> {
							if(b.succeeded()) {
								LOGGER.info(String.format("crais %s créé pour inscription %s. ", paiementDate, inscriptionCle));
							} else {
								LOGGER.error(String.format("créer frais %s a échoué pour inscription %s. ", paiementDate, inscriptionCle), b.cause());
								promise.fail(b.cause());
							}
						}));
					}
				}
			}
			CompositeFuture.all(futures).setHandler(b -> {
				if(b.succeeded()) {
					LOGGER.info(String.format("Frais créé pour inscription %s. ", inscriptionCle));
					gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
					promise.complete();
				} else {
					LOGGER.error("Recharger rélations a échoué. ", b.cause());
					gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
					promise.fail(b.cause());
				}
			});

			return promise.future();
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}

	public Future<Void> authorizeNetEnrollmentPaiementsFuture(MerchantAuthenticationType merchantAuthenticationType, InscriptionScolaire schoolEnrollment, Handler<AsyncResult<Void>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = schoolEnrollment.getRequeteSite_();
		SiteContexteFrFR siteContexteFrFR = requeteSite.getSiteContexte_();
		UtilisateurSite siteUser = requeteSite.getUtilisateurSite();
		ConfigSite siteConfig = requeteSite.getConfigSite_();
		Promise<Void> promise = Promise.promise();
		String customerProfileId = Optional.ofNullable(siteUser).map(UtilisateurSite::getCustomerProfileId).orElse(null);

		if(siteUser == null) {
			String m = String.format("Seul un utilisateur connecté peut accéder à cette page. ");
			LOGGER.error(m);
			gestionnaireEvenements.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		else if(customerProfileId == null) {
			String m = String.format(String.format("L'utilisateur %s n'a pas un customer profile ID. ", customerProfileId));
			LOGGER.error(m);
			gestionnaireEvenements.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		else {
			try {
				Paging paging = new Paging();
				paging.setLimit(1000);
				paging.setOffset(1);
				
				GetTransactionListForCustomerRequest getRequest = new GetTransactionListForCustomerRequest();
				getRequest.setMerchantAuthentication(merchantAuthenticationType);
				getRequest.setCustomerProfileId(siteUser.getCustomerProfileId());
	
				getRequest.setPaging(paging);
	
				TransactionListSorting sorting = new TransactionListSorting();
				sorting.setOrderBy(TransactionListOrderFieldEnum.SUBMIT_TIME_UTC);
				sorting.setOrderDescending(true);
	
				getRequest.setSorting(sorting);
	
				GetTransactionListForCustomerController controller = new GetTransactionListForCustomerController(getRequest);
				GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
				controller.execute();
				if(controller.getErrorResponse() != null)
					throw new RuntimeException(controller.getResults().toString());
	
				PaiementScolaireFrFRGenApiServiceImpl paiementService = new PaiementScolaireFrFRGenApiServiceImpl(siteContexteFrFR);
	
				List<Future> futures = new ArrayList<>();
	
				GetTransactionListResponse getResponse = controller.getApiResponse();
				if (getResponse != null) {
	
					if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
						List<TransactionSummaryType> transactions = Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList());
						LOGGER.info(String.format("Il y a %s transactions pour client %s à charger. ", transactions.size(), siteUser.getCustomerProfileId()));
						for(TransactionSummaryType transaction : transactions) {
							futures.add(
								futureAuthorizeNetPaiement(merchantAuthenticationType, paiementService, schoolEnrollment.getRequeteSite_(), transaction, schoolEnrollment, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("transaction %s chargé. ", transaction.getTransId()));
									} else {
										LOGGER.error(String.format("paiement future pour transaction %s a échoué. ", transaction.getTransId()), b.cause());
									}
								})
							);
						}
						CompositeFuture.all(futures).setHandler(b -> {
							if(b.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture());
								promise.complete();
								LOGGER.info(String.format("transactions pour client %s chargé. ", siteUser.getCustomerProfileId()));
							} else {
								LOGGER.error(String.format("transactions pour client %s ont échoués. ", siteUser.getCustomerProfileId()));
								gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
								promise.fail(b.cause());
							}
						});
					}
					else {
						LOGGER.error(String.format("transactions pour client %s ont échoués. ", siteUser.getCustomerProfileId()));
						gestionnaireEvenements.handle(Future.failedFuture(getResponse.getMessages().getMessage().stream().findFirst().map(m -> m.getText()).orElse("")));
					}
				}
				return promise.future();
			} catch(Exception e) {
				gestionnaireEvenements.handle(Future.failedFuture(e));
				return Future.failedFuture(e);
			}
		}
	}

	public Future<Void> futureAuthorizeNetPaiement(MerchantAuthenticationType merchantAuthenticationType, PaiementScolaireFrFRGenApiServiceImpl paiementService, RequeteSiteFrFR requeteSite, TransactionSummaryType transactionSummary, InscriptionScolaire schoolEnrollment, Handler<AsyncResult<Void>> a) {
		Promise<Void> promise = Promise.promise();
		try {
			ConfigSite siteConfig = requeteSite.getConfigSite_();
			GetTransactionDetailsRequest getRequest = new GetTransactionDetailsRequest();
			getRequest.setMerchantAuthentication(merchantAuthenticationType);
			getRequest.setTransId(transactionSummary.getTransId());
			GetTransactionDetailsController controller = new GetTransactionDetailsController(getRequest );
			GetTransactionDetailsController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
			controller.execute();
			if(controller.getErrorResponse() != null)
				throw new RuntimeException(controller.getResults().toString());
			GetTransactionDetailsResponse getResponse = controller.getApiResponse();
			TransactionDetailsType transaction = getResponse.getTransaction();
			String orderDescription = Optional.ofNullable(transaction).map(TransactionDetailsType::getOrder).map(OrderExType::getDescription).orElse("");
			Matcher enrollmentKeyMatcher = Pattern.compile("^(\\d+) paiement pour ").matcher(orderDescription);
			Boolean enrollmentKeyFound = enrollmentKeyMatcher.find();
			Long enrollmentKey = null;

			if(enrollmentKeyFound) {
				String enrollmentKeyStr = enrollmentKeyMatcher.group(1);
				if(NumberUtils.isCreatable(enrollmentKeyStr)) {
					enrollmentKey = Long.parseLong(enrollmentKeyStr);
					if(schoolEnrollment == null) {
						ListeRecherche<InscriptionScolaire> listeRechercheEnrollment = new ListeRecherche<InscriptionScolaire>();
						listeRechercheEnrollment.setStocker(true);
						listeRechercheEnrollment.setQuery("*:*");
						listeRechercheEnrollment.setC(InscriptionScolaire.class);
						listeRechercheEnrollment.addFilterQuery("pk_indexed_long:" + enrollmentKey);
						listeRechercheEnrollment.initLoinListeRecherche(requeteSite);
	
						if(listeRechercheEnrollment.getList().size() == 1) {
							schoolEnrollment = listeRechercheEnrollment.first();
						}
						else {
							enrollmentKey = null;
						}
					}
					else if(!enrollmentKey.equals(schoolEnrollment.getPk()))
						enrollmentKey = null;
				}
			}

			if(enrollmentKey == null) {
				promise.complete();
			}
			else {
				XMLGregorianCalendar submitTimeLocal = transactionSummary.getSubmitTimeLocal();
				LocalDate paiementDate = submitTimeLocal.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.of(siteContexte.getConfigSite().getSiteZone())).toLocalDate();
	
				ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PaiementScolaire.class);
				listeRecherche.addFilterQuery("transactionId_indexed_string:" + ClientUtils.escapeQueryChars(transactionSummary.getTransId()));
				listeRecherche.initLoinListeRecherche(requeteSite);

				if(listeRecherche.size() == 0) {
					PaiementScolaire paiement = new PaiementScolaire();
					paiement.setRequeteSite_(requeteSite);
					paiement.setPaiementMontant(transactionSummary.getSettleAmount());
					paiement.setPaiementDate(paiementDate);
					paiement.setPaiementSysteme(true);
					paiement.setTransactionId(transactionSummary.getTransId());
					paiement.setCustomerProfileId(Optional.ofNullable(transactionSummary.getProfile()).map(CustomerProfileIdType::getCustomerProfileId).orElse(null));
					paiement.setTransactionStatus(transactionSummary.getTransactionStatus());
					paiement.setPaiementPar(String.format("%s %s", transactionSummary.getFirstName(), transactionSummary.getLastName()).trim());
					paiement.setInscriptionCle(enrollmentKey);

					RequeteSiteFrFR requeteSite2 = paiementService.genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, null, JsonObject.mapFrom(paiement));
					paiementService.postPaiementScolaireFuture(requeteSite2, false, c -> {
						PaiementScolaire paiement2 = c.result();
						if(c.succeeded()) {
							a.handle(Future.succeededFuture());
							promise.complete();
							LOGGER.info(String.format("paiement %s créé pour transaction %s. ", paiement2.getPk(), transactionSummary.getTransId()));
						} else {
							LOGGER.error("créer paiement %s a échoué pour transaction %s. ", c.cause());
							promise.fail(c.cause());
						}
					});
				}
				else {
					promise.complete();
				}
			} 

			return promise.future();
		} catch(Exception e) {
			a.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}
}
