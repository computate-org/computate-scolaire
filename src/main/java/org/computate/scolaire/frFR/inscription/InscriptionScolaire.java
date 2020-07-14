package org.computate.scolaire.frFR.inscription;          

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import org.computate.scolaire.frFR.mere.MereScolaire;
import org.computate.scolaire.frFR.page.MiseEnPage;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.pere.PereScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.saison.SaisonScolaire;

/**    
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.SchoolEnrollment
 * Modele: true
 * Api: true
 * Indexe: true
 * Sauvegarde: true
 * 
 * ApiTag.frFR: Inscription
 * ApiUri.frFR: /api/inscription
 * 
 * ApiTag.enUS: Enrollment
 * ApiUri.enUS: /api/enrollment
 * 
 * ApiMethode: POST
 * 
 * ApiMethode.frFR: PUTImport
 * RoleUtilisateurPUTImport.frFR: true
 * ApiMethode.frFR: PUTFusion
 * ApiMethode.frFR: PUTCopie
 * ApiMethode.enUS: PUTImport
 * RoleUtilisateurPUTImport.enUS: true
 * ApiMethode.enUS: PUTMerge
 * ApiMethode.enUS: PUTCopy

 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode.frFR: Recherche
 * ApiMethode.enUS: Search
 * 
 * ApiMethode.frFR: RechercheAdmin
 * ApiUriRechercheAdmin.frFR: /api/admin/inscription
 * RoleUtilisateurRechercheAdmin.frFR: true
 * 
 * ApiMethode.enUS: AdminSearch
 * ApiUriAdminSearch.enUS: /api/admin/enrollment
 * RoleUtilisateurAdminSearch.enUS: true
 * 
 * ApiMethode.frFR: PATCHPaiements
 * ApiUriPATCHPayments.frFR: /inscription/paiements
 * 
 * ApiMethode.enUS: PATCHPayments
 * ApiUriPATCHPayments.enUS: /enrollment/payments
 * 
 * ApiMethode.frFR: PageRecherche
 * PagePageRecherche.frFR: InscriptionPage
 * PageSuperPageRecherche.frFR: ClusterPage
 * ApiUriPageRecherche.frFR: /inscription
 * 
 * ApiMethode.enUS: SearchPage
 * PageSearchPage.enUS: EnrollmentPage
 * PageSuperSearchPage.enUS: ClusterPage
 * ApiUriSearchPage.enUS: /enrollment
 * 
 * ApiMethode.frFR: RechargerPageRecherche
 * PageRechargerPageRecherche.frFR: PageInscription
 * PageSuperRechargerPageRecherche.frFR: ClusterPage
 * ApiUriRechargerPageRecherche.frFR: /recharger-inscription
 * RoleUtilisateurRechargerPageRecherche.frFR: true
 * 
 * ApiMethode.enUS: RefreshSearchPage
 * PageRefreshSearchPage.enUS: EnrollmentPage
 * PageSuperRefreshSearchPage.enUS: ClusterPage
 * ApiUriRefreshSearchPage.enUS: /refresh-enrollment
 * RoleUtilisateurRefreshSearchPage.enUS: true
 * 
 * UnNom.frFR: une inscription
 * UnNom.enUS: an enrollment
 * Couleur: blue-gray
 * IconeGroupe: solid
 * IconeNom: edit
 * 
 * Role.frFR: SiteAdmin
 * Role.enUS: SiteAdmin
 * RoleSession: true
 * RoleUtilisateur: true
*/  
public class InscriptionScolaire extends InscriptionScolaireGen<Cluster> {       

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'inscription dans la base de données. 
	 * Description.enUS: The primary key of the school enrollment in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 */                              
	protected void _inscriptionCle(Couverture<Long> c) {
		c.o(pk);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: AnneeScolaire.inscriptionCles
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: année
	 * NomAffichage.enUS: year
	*/            
	protected void _anneeCle(Couverture<Long> c) {
	}

	/**
	 * Var.enUS: yearSearch
	 * r: anneeCle
	 * r.enUS: yearKey
	 * r: AnneeScolaire
	 * r.enUS: SchoolYear
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 */ 
	protected void _anneeRecherche(ListeRecherche<AnneeScolaire> l) {
		if(anneeCle != null) {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:" + anneeCle);
			l.setC(AnneeScolaire.class);
			l.setStocker(true);
		}
		else {
			l.setQuery(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: year_
	 * r: anneeRecherche
	 * r.enUS: yearSearch
	 * Ignorer: true
	 */   
	protected void _annee_(Couverture<AnneeScolaire> c) {
		if(anneeRecherche.size() > 0) {
			c.o(anneeRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: BlocScolaire.inscriptionCles
	 * HtmlLigne: 9
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des blocs dans la base de données. 
	 * Description.enUS: The primary key of the school blocks in the database. 
	 * NomAffichage.frFR: blocs
	 * NomAffichage.enUS: blocks
	 */            
	protected void _blocCles(List<Long> o) {}

	/**
	 * Var.enUS: blockSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: BlocScolaire
	 * r.enUS: SchoolBlock
	 * r: setStocker
	 * r.enUS: setStore
	 * Ignorer: true
	 * r: blocCles
	 * r.enUS: blockKeys
	 */
	protected void _blocRecherche(ListeRecherche<BlocScolaire> l) {
		if(blocCles.size() > 0) {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:(" + StringUtils.join(blocCles, " ") + ")");
			l.setC(BlocScolaire.class);
			l.setStocker(true);
		}
		else {
			l.setQuery(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blocks_
	 * r: blocRecherche
	 * r.enUS: blockSearch
	 * Ignorer: true
	 */     
	protected void _blocs_(Couverture<List<BlocScolaire>> c) {
		c.o(blocRecherche.getList());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasons_
	 * r: blocRecherche
	 * r.enUS: blockSearch
	 * Ignorer: true
	 */    
	protected void _saisons_(List<SaisonScolaire> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: block_
	 * r: blocRecherche
	 * r.enUS: blockSearch
	 * Ignorer: true
	 */   
	protected void _bloc_(Couverture<BlocScolaire> c) {
		if(blocRecherche.size() > 0) {
			c.o(blocRecherche.get(0));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire de l'école dans la base de données. 
	 * Description.enUS: The primary key of the school in the database. 
	 * NomAffichage.frFR: école
	 * NomAffichage.enUS: school
	 * r: EcoleCle
	 * r.enUS: SchoolKey
	 * r: annee
	 * r.enUS: year
	 */              
	protected void _ecoleCle(Couverture<Long> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleCle());
	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonKey
//	 * Indexe: true
//	 * Stocke: true
//	 * Description.frFR: L'année scolaire de la saison scolaire. 
//	 * Description.enUS: The school year of the school season. 
//	 * NomAffichage.frFR: année
//	 * NomAffichage.enUS: year
//	 * r: SaisonCle
//	 * r.enUS: SeasonKey
//	 * r: bloc
//	 * r.enUS: block
//	*/             
//	protected void _saisonCle(Couverture<Long> c) {
//		if(bloc_ != null)
//			c.o(bloc_.getSaisonCle());
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: session
	 * NomAffichage.enUS: session
	 * r: SessionCle
	 * r.enUS: SessionKey
	 * r: bloc
	 * r.enUS: block
	*/             
	protected void _sessionCle(Couverture<Long> c) {
		if(bloc_ != null)
			c.o(bloc_.getSessionCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: L'année scolaire de la saison scolaire. 
	 * Description.enUS: The school year of the school season. 
	 * NomAffichage.frFR: âge
	 * NomAffichage.enUS: age
	 * r: AgeCle
	 * r.enUS: AgeKey
	 * r: bloc
	 * r.enUS: block
	*/             
	protected void _ageCle(Couverture<Long> c) {
		if(bloc_ != null)
			c.o(bloc_.getAgeCle());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockKey
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: La clé primaire du bloc dans la base de données. 
	 * Description.enUS: The primary key of the school block in the database. 
	 * NomAffichage.frFR: clé
	 * NomAffichage.enUS: key
	 * r: BlocCle
	 * r.enUS: BlockKey
	 * r: bloc
	 * r.enUS: block
	 */              
	protected void _blocCle(Couverture<Long> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocCle());
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: childKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: EnfantScolaire.inscriptionCles
	 * HtmlLigne: 9
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des enfants dans la base de données. 
	 * Description.enUS: The primary key of the school children in the database. 
	 * NomAffichage.frFR: enfants
	 * NomAffichage.enUS: children
	 */               
	protected void _enfantCle(Couverture<Long> c) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: MereScolaire.inscriptionCles
	 * HtmlLigne: 10
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des mères dans la base de données. 
	 * Description.enUS: The primary key of the school moms in the database. 
	 * NomAffichage.frFR: mères
	 * NomAffichage.enUS: moms
	 */            
	protected void _mereCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PereScolaire.inscriptionCles
	 * HtmlLigne: 10
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des pères dans la base de données. 
	 * Description.enUS: The primary key of the school dads in the database. 
	 * NomAffichage.frFR: pères
	 * NomAffichage.enUS: dads
	 */               
	protected void _pereCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: guardianKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: GardienScolaire.inscriptionCles
	 * HtmlLigne: 11
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des gardiens dans la base de données. 
	 * Description.enUS: The primary key of the school gardians in the database. 
	 * NomAffichage.frFR: gardiens
	 * NomAffichage.enUS: guardians
	 */              
	protected void _gardienCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: paymentKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: PaiementScolaire.inscriptionCle
	 * HtmlLigne: 11
	 * HtmlCellule: 2
	 * Description.frFR: La clé primaire des contacts d'urgence dans la base de données. 
	 * Description.enUS: The primary key of the school emergency contacts in the database. 
	 * NomAffichage.frFR: paiements
	 * NomAffichage.enUS: payments
	 */          
	protected void _paiementCles(List<Long> o) {}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentFormKey
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: FormInscription.partFormCles
	 * NomAffichage.frFR: formulaire d'inscription
	 * NomAffichage.enUS: enrollment form
	*/           
	protected void _formInscriptionCle(Couverture<Long> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: userKeys
	 * Indexe: true
	 * Stocke: true
	 * Attribuer: UtilisateurSite.inscriptionCles
	 * HtmlLigne: 12
	 * HtmlCellule: 1
	 * Description.frFR: La clé primaire des utilisateurs dans la base de données. 
	 * Description.enUS: The primary key of the users in the database. 
	 * NomAffichage.frFR: utilisateurs
	 * NomAffichage.enUS: users
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: utilisateurCle
	 * r.enUS: userKey
	 */          
	protected void _utilisateurCles(List<Long> l) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: educationSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _scolaireTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ecoleTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _anneeTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _saisonTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _sessionTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageSort
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _ageTri(Couverture<Integer> c) {
		c.o(6);
	}

	/**
	 * Var.enUS: childSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: EnfantScolaire
	 * r.enUS: SchoolChild
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * Ignorer: true
	 */
	protected void _enfantRecherche(ListeRecherche<EnfantScolaire> l) {
		if(enfantCle == null) {
			l.setQuery(null);
		}
		else {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:" + enfantCle);
			l.setC(EnfantScolaire.class);
			l.setStocker(true);
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: child_
	 * r: enfantRecherche
	 * r.enUS: childSearch
	 * Ignorer: true
	 */                               
	protected void _enfant_(Couverture<EnfantScolaire> c) {
		if(enfantRecherche.size() > 0) {
			c.o(enfantRecherche.get(0));
		}
	}

	/**
	 * Var.enUS: momSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * Ignorer: true
	 */
	protected void _mereRecherche(ListeRecherche<MereScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("inscriptionCles_indexed_longs:" + pk);
		l.setC(MereScolaire.class);
		l.setStocker(true);
	}

	/**  
	 * {@inheritDoc}
	 * Var.enUS: moms
	 * r: mereRecherche
	 * r.enUS: momSearch
	 * Ignorer: true
	 */      
	protected void _meres(Couverture<List<MereScolaire>> c) {
		c.o(mereRecherche.getList());
	}

	/**
	 * Var.enUS: dadSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * Ignorer: true
	 */
	protected void _pereRecherche(ListeRecherche<PereScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("inscriptionCles_indexed_longs:" + pk);
		l.setC(PereScolaire.class);
		l.setStocker(true);
	}

	/**  
	 * {@inheritDoc}
	 * Var.enUS: dads
	 * r: pereRecherche
	 * r.enUS: dadSearch
	 * Ignorer: true
	 */      
	protected void _peres(Couverture<List<PereScolaire>> c) {
		c.o(pereRecherche.getList());
	}

	/**
	 * Var.enUS: guardianSearch
	 * r: inscriptionCles
	 * r.enUS: enrollmentKeys
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * Ignorer: true
	 */
	protected void _gardienRecherche(ListeRecherche<GardienScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("inscriptionCles_indexed_longs:" + pk);
		l.setC(GardienScolaire.class);
		l.setStocker(true);
	}

	/**  
	 * {@inheritDoc}
	 * Var.enUS: guardians
	 * r: gardienRecherche
	 * r.enUS: guardianSearch
	 * Ignorer: true
	 */    
	protected void _gardiens(Couverture<List<GardienScolaire>> c) {
		c.o(gardienRecherche.getList());
	}

	/**
	 * Var.enUS: feeSearch
	 * r: inscriptionCle
	 * r.enUS: enrollmentKey
	 * r: PaiementScolaire
	 * r.enUS: SchoolPayment
	 * r: setStocker
	 * r.enUS: setStore
	 * r: enfantCle
	 * r.enUS: childKey
	 * r: paiementDate
	 * r.enUS: paymentDate
	 * Ignorer: true
	 * r: fraisMois
	 * r.enUS: chargeMonth
	 */
	protected void _fraisRecherche(ListeRecherche<PaiementScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("inscriptionCle_indexed_long:" + pk);
		l.addFilterQuery("fraisMois_indexed_boolean:true");
		l.add("json.facet", "{'paiementDateMax':'max(paiementDate_indexed_date)'}");
		l.setC(PaiementScolaire.class);
		l.setStocker(true);
	}

	/**
	 * Var.enUS: paymentSearch
	 * Ignorer: true
	 * r: inscriptionCle
	 * r.enUS: enrollmentKey
	 * r: PaiementScolaire
	 * r.enUS: SchoolPayment
	 * r: setStocker
	 * r.enUS: setStore
	 * r: paiementMontant
	 * r.enUS: paymentAmount
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: fraisMontantDu
	 * r.enUS: chargeAmountDue
	 * r: fraisMontantFuture
	 * r.enUS: chargeAmountFuture
	 */
	protected void _paiementRecherche(ListeRecherche<PaiementScolaire> l) {
		l.setQuery("*:*");
		l.addFilterQuery("inscriptionCle_indexed_long:" + pk);
		l.add("json.facet", "{sum_paiementMontant:'sum(paiementMontant_indexed_double)'}");
		l.add("json.facet", "{sum_fraisMontant:'sum(fraisMontant_indexed_double)'}");
		l.add("json.facet", "{sum_fraisMontantDu:'sum(fraisMontantDu_indexed_double)'}");
		l.add("json.facet", "{sum_fraisMontantFuture:'sum(fraisMontantFuture_indexed_double)'}");
		l.setC(PaiementScolaire.class);
		l.setStocker(true);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childFirstName
	 * Indexe: true
	 * Stocke: true
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonnePrenom
	 * r.enUS: PersonFirstName
	 */   
	protected void _enfantPrenom(Couverture<String> c) {
		if(enfant_ != null)
			c.o(enfant_.getPersonnePrenom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childFirstNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonnePrenomPrefere
	 * r.enUS: PersonFirstNamePreferred
	 */   
	protected void _enfantPrenomPrefere(Couverture<String> c) {
		if(enfant_ != null)
			c.o(enfant_.getPersonnePrenomPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childFamilyName
	 * Indexe: true
	 * Stocke: true
	 * r: enfant_
	 * r.enUS: child_
	 * r: FamilleNom
	 * r.enUS: FamilyName
	 */   
	protected void _enfantFamilleNom(Couverture<String> c) {
		if(enfant_ != null)
			c.o(enfant_.getFamilleNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momFirstName
	 * Indexe: true
	 * Stocke: true
	 * r: meres
	 * r.enUS: moms
	 * r: PersonnePrenom
	 * r.enUS: PersonFirstName
	 */   
	protected void _merePrenom(Couverture<String> c) {
		if(meres.size() > 0)
			c.o(meres.get(0).getPersonnePrenom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momFirstNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * r: meres
	 * r.enUS: moms
	 * r: PersonnePrenomPrefere
	 * r.enUS: PersonFirstNamePreferred
	 */   
	protected void _merePrenomPrefere(Couverture<String> c) {
		if(meres.size() > 0)
			c.o(meres.get(0).getPersonnePrenomPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: momCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * r: meres
	 * r.enUS: moms
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 */   
	protected void _mereNomCompletPrefere(Couverture<String> c) {
		if(meres.size() > 0)
			c.o(meres.get(0).getPersonneNomCompletPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadFirstName
	 * Indexe: true
	 * Stocke: true
	 * r: peres
	 * r.enUS: dads
	 * r: PersonnePrenom
	 * r.enUS: PersonFirstName
	 */   
	protected void _perePrenom(Couverture<String> c) {
		if(peres.size() > 0)
			c.o(peres.get(0).getPersonnePrenom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadFirstNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * r: peres
	 * r.enUS: dads
	 * r: PersonnePrenomPrefere
	 * r.enUS: PersonFirstNamePreferred
	 */   
	protected void _perePrenomPrefere(Couverture<String> c) {
		if(peres.size() > 0)
			c.o(peres.get(0).getPersonnePrenomPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: dadCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * r: peres
	 * r.enUS: dads
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 */   
	protected void _pereNomCompletPrefere(Couverture<String> c) {
		if(peres.size() > 0)
			c.o(peres.get(0).getPersonneNomCompletPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childCompleteName
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EnfantNomComplet
	 * r.enUS: ChildCompleteName
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonneNomComplet
	 * r.enUS: PersonCompleteName
	 */ 
	protected void _enfantNomComplet(Couverture<String> c) {
		if(enfant_ != null)
			c.o(enfant_.getPersonneNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childCompleteNamePreferred
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 */  
	protected void _enfantNomCompletPrefere(Couverture<String> c) {
		if(enfant_ != null)
			c.o(enfant_.getPersonneNomCompletPrefere());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: enfant_
	 * r.enUS: child_
	 * r: PersonneDateNaissance
	 * r.enUS: PersonBirthDate
	 */     
	protected void _enfantDateNaissance(Couverture<LocalDate> c) {
		if(enfant_ != null)
			c.o(enfant_.getPersonneDateNaissance());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDateYear
	 * Indexe: true
	 * Stocke: true
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _enfantDateNaissanceDAnnee(Couverture<Integer> c) {
		if(enfantDateNaissance != null)
			c.o(enfantDateNaissance.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDateMonthOfYear
	 * Indexe: true
	 * Stocke: true
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _enfantDateNaissanceMoisDAnnee(Couverture<String> c) {
		if(enfantDateNaissance != null)
			c.o(enfantDateNaissance.format(DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE)));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDateDayOfWeek
	 * Indexe: true
	 * Stocke: true
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _enfantDateNaissanceJourDeSemaine(Couverture<String> c) {
		if(enfantDateNaissance != null)
			c.o(enfantDateNaissance.format(DateTimeFormatter.ofPattern("EEEE", Locale.FRANCE)));
	}

	/**
	 * Var.enUS: strChildBirthDate
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 * r: "d MMM yyyy"
	 * r.enUS: "MMM d yyyy"
	 */
	@Override public String strEnfantDateNaissance() { 
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.FRANCE));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthMonth
	 * Indexe: true
	 * Stocke: true
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 */    
	protected void _enfantMoisNaissance(Couverture<Integer> c) {
		if(enfantDateNaissance != null)
			c.o(enfantDateNaissance.getMonthValue());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childBirthDay
	 * Indexe: true
	 * Stocke: true
	 * r: enfantDateNaissance
	 * r.enUS: childBirthDate
	 */    
	protected void _enfantJourNaissance(Couverture<Integer> c) {
		if(enfantDateNaissance != null)
			c.o(enfantDateNaissance.getMonthValue());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EcoleNom
	 * r.enUS: SchoolName
	 * r: annee
	 * r.enUS: year
	 */   
	protected void _ecoleNom(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolCompleteName
	 * Indexe: true
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 * r: EcoleNomComplet
	 * r.enUS: SchoolCompleteName
	 * r: annee
	 * r.enUS: year
	 */   
	protected void _ecoleNomComplet(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolLocation
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: location
	 * NomAffichage.frFR: l'emplacement
	 * r: EcoleEmplacement
	 * r.enUS: SchoolLocation
	 * r: annee
	 * r.enUS: year
	 */           
	protected void _ecoleEmplacement(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleEmplacement());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAddress
	 * Definir: true
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse
	 * NomAffichage.enUS: address
	 * r: EcoleAddresse
	 * r.enUS: SchoolAddress
	 * r: annee
	 * r.enUS: year
	 */
	protected void _ecoleAddresse(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleAddresse());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolPhoneNumber
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: numéro de téléphone
	 * NomAffichage.enUS: phone number
	 * r: EcoleNumeroTelephone
	 * r.enUS: SchoolPhoneNumber
	 * r: annee
	 * r.enUS: year
	 */    
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleNumeroTelephone());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolNumber
	 * Indexe: true
	 * Stocke: true
	 * r: EcoleNumero
	 * r.enUS: SchoolNumber
	 * r: annee
	 * r.enUS: year
	 */
	protected void _ecoleNumero(Couverture<Integer> c) {
		if(annee_ != null)
			c.o((Integer)annee_.getEcoleNumero());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: schoolAdministratorName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.enUS: administrator of the school
	 * NomAffichage.frFR: administrateur de l'école
	 * r: EcoleAdministrateurNom
	 * r.enUS: SchoolAdministratorName
	 * r: annee
	 * r.enUS: year
	 */             
	protected void _ecoleAdministrateurNom(Couverture<String> c) {
		if(annee_ != null)
			c.o(annee_.getEcoleAdministrateurNom());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearStart
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of year
	 * r: AnneeDebut
	 * r.enUS: YearStart
	 * r: annee
	 * r.enUS: year
	 */               
	protected void _anneeDebut(Couverture<Integer> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFin
	 * r.enUS: YearEnd
	 * r: annee
	 * r.enUS: year
	 */                     
	protected void _anneeFin(Couverture<Integer> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: seasonStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de l'année
	 * NomAffichage.enUS: start of season
	 * r: SaisonDateDebut
	 * r.enUS: SeasonStartDate
	 * r: bloc
	 * r.enUS: block
	 */                  
	protected void _saisonDateDebut(Couverture<LocalDate> c) {
		if(bloc_ != null)
			c.o(bloc_.getSaisonDateDebut());
	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonSummer
//	 * Indexe: true
//	 * Stocke: true
//	 * NomAffichage.frFR: été
//	 * NomAffichage.enUS: summer
//	 * r: SaisonEte
//	 * r.enUS: SeasonSummer
//	 * r: bloc
//	 * r.enUS: block
//	 */                   
//	protected void _saisonEte(Couverture<Boolean> c) {
//		if(bloc_ != null)
//			c.o(bloc_.getSaisonEte());
//	}
//
//	/**
//	 * {@inheritDoc}
//	 * Var.enUS: seasonWinter
//	 * Indexe: true
//	 * Stocke: true
//	 * NomAffichage.frFR: hiver
//	 * NomAffichage.enUS: winter
//	 * r: SaisonHiver
//	 * r.enUS: SeasonWinter
//	 * r: bloc
//	 * r.enUS: block
//	 */                   
//	protected void _saisonHiver(Couverture<Boolean> c) {
//		if(bloc_ != null)
//			c.o(bloc_.getSaisonHiver());
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: yearEnrollmentFee
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: le fin de l'année
	 * NomAffichage.enUS: end of year
	 * r: AnneeFraisInscription
	 * r.enUS: YearEnrollmentFee
	 * r: annee
	 * r.enUS: year
	 */                
	protected void _anneeFraisInscription(Couverture<BigDecimal> c) {
		if(annee_ != null)
			c.o(annee_.getAnneeFraisInscription());
	}
//
//	/**   
//	 * {@inheritDoc}
//	 * Var.enUS: seasonCompleteName
//	 * Indexe: true
//	 * Stocke: true
//	 * r: SaisonNomComplet
//	 * r.enUS: SeasonCompleteName
//	 * r: bloc
//	 * r.enUS: block
//	 */
//	protected void _saisonNomComplet(Couverture<String> c) {
//		if(bloc_ != null)
//			c.o(bloc_.getSaisonNomComplet());
//	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionStartDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début de la session
	 * NomAffichage.enUS: start of the session
	 * r: SessionDateDebut
	 * r.enUS: SessionStartDate
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _sessionDateDebut(Couverture<LocalDate> c) {
		if(bloc_ != null)
			c.o((LocalDate)bloc_.getSessionDateDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: sessionEndDate
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin de la session
	 * NomAffichage.enUS: end of the session
	 * r: SessionDateFin
	 * r.enUS: SessionEndDate
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _sessionDateFin(Couverture<LocalDate> c) {
		if(bloc_ != null)
			c.o((LocalDate)bloc_.getSessionDateFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: AgeNomComplet
	 * r.enUS: AgeCompleteName
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageNomComplet(Couverture<String> c) {
		if(bloc_ != null)
			c.o(bloc_.getAgeNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageStart
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: début du groupe d'âge
	 * NomAffichage.enUS: start of the age group
	 * r: AgeDebut
	 * r.enUS: AgeStart
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageDebut(Couverture<Integer> c) {
		if(bloc_ != null)
			c.o(bloc_.getAgeDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: ageEnd
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: fin du groupe d'âge
	 * NomAffichage.enUS: end of the age group
	 * r: AgeFin
	 * r.enUS: AgeEnd
	 * r: bloc
	 * r.enUS: block
	 */                   
	protected void _ageFin(Couverture<Integer> c) {
		if(bloc_ != null)
			c.o(bloc_.getAgeFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockStartTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure début
	 * NomAffichage.enUS: start time
	 * r: bloc
	 * r.enUS: block
	 * r: BlocHeureDebut
	 * r.enUS: BlockStartTime
	 */                   
	protected void _blocHeureDebut(Couverture<LocalTime> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocHeureDebut());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockEndTime
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure fin
	 * NomAffichage.enUS: end time
	 * r: bloc
	 * r.enUS: block
	 * r: BlocHeureFin
	 * r.enUS: BlockEndTime
	 */                   
	protected void _blocHeureFin(Couverture<LocalTime> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocHeureFin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockPricePerMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix par mois
	 * NomAffichage.enUS: price per month
	 * r: bloc
	 * r.enUS: block
	 * r: BlocPrixParMois
	 * r.enUS: BlockPricePerMonth
	 */                   
	protected void _blocPrixParMois(Couverture<BigDecimal> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocPrixParMois());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSunday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: dimanche
	 * NomAffichage.enUS: sunday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocDimanche
	 * r.enUS: BlockSunday
	 */                   
	protected void _blocDimanche(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocDimanche());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockMonday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: lundi
	 * NomAffichage.enUS: monday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocLundi
	 * r.enUS: BlockMonday
	 */                   
	protected void _blocLundi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocLundi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTuesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mardi
	 * NomAffichage.enUS: tuesday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocMardi
	 * r.enUS: BlockTuesday
	 */                   
	protected void _blocMardi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocMardi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockWednesday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: mercredi
	 * NomAffichage.enUS: wednesday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocMercredi
	 * r.enUS: BlockWednesday
	 */                   
	protected void _blocMercredi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocMercredi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockThursday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: jeudi
	 * NomAffichage.enUS: thursday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocJeudi
	 * r.enUS: BlockThursday
	 */                   
	protected void _blocJeudi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocJeudi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockFriday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vendredi
	 * NomAffichage.enUS: friday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocVendredi
	 * r.enUS: BlockFriday
	 */                   
	protected void _blocVendredi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocVendredi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockSaturday
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: samedi
	 * NomAffichage.enUS: saturday
	 * r: bloc
	 * r.enUS: block
	 * r: BlocSamedi
	 * r.enUS: BlockSaturday
	 */                   
	protected void _blocSamedi(Couverture<Boolean> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocSamedi());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockTotalPrice
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: prix total
	 * NomAffichage.enUS: total price
	 * r: bloc
	 * r.enUS: block
	 * r: BlocPrixTotal
	 * r.enUS: BlockTotalPrice
	 */                   
	protected void _blocPrixTotal(Couverture<BigDecimal> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocPrixTotal());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockAdminName
	 * Indexe: true
	 * Stocke: true
	 * r: bloc
	 * r.enUS: block
	 * r: BlocNomAdmin
	 * r.enUS: BlockAdminName
	 */               
	protected void _blocNomAdmin(Couverture<String> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocNomAdmin());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockShortName
	 * Indexe: true
	 * Stocke: true
	 * r: bloc
	 * r.enUS: block
	 * r: BlocNomCourt
	 * r.enUS: BlockShortName
	 */               
	protected void _blocNomCourt(Couverture<String> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocNomCourt());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: blockCompleteName
	 * Indexe: true
	 * Stocke: true
	 * r: bloc
	 * r.enUS: block
	 * r: BlocNomComplet
	 * r.enUS: BlockCompleteName
	 */               
	protected void _blocNomComplet(Couverture<String> c) {
		if(bloc_ != null)
			c.o(bloc_.getBlocNomComplet());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentApproved
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: approuvé
	 * NomAffichage.enUS: approved
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 1
	 */                   
	protected void _inscriptionApprouve(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentImmunizations
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: vacciné
	 * NomAffichage.enUS: immunized
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 2
	 */                   
	protected void _inscriptionImmunisations(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyMarried
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: parents marié
	 * NomAffichage.enUS: parents married
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 1
	 */                   
	protected void _familleMarie(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familySeparated
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: parents séparé
	 * NomAffichage.enUS: parents separated
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 2
	 */                   
	protected void _familleSepare(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyDivorced
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: parents divorcé
	 * NomAffichage.enUS: parents divorced
	 * Definir: true
	 * HtmlLigne: 5
	 * HtmlCellule: 3
	 */                   
	protected void _familleDivorce(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyAddress
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: addresse de la famille
	 * NomAffichage.enUS: family address
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 1
	 */                   
	protected void _familleAddresse(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: familyHowDoYouKnowTheSchool
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: comment vous connaissez l'école ? 
	 * NomAffichage.enUS: how do you know the school? 
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCellule: 3
	 */                   
	protected void _familleCommentVousConnaissezEcole(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSpecialConsiderations
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: considérations spéciale
	 * NomAffichage.enUS: special considerations
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 6
	 * HtmlCellule: 2
	 */                   
	protected void _inscriptionConsiderationsSpeciales(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childMedicalConditions
	 * Indexe: true
	 * Stocke: true
	 * Texte: true
	 * NomAffichage.frFR: conditions médicales
	 * NomAffichage.enUS: medical conditions
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCellule: 1
	 */                  
	protected void _enfantConditionsMedicales(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childPreviousSchoolsAttended
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: écoles précedemment fréqentées
	 * NomAffichage.enUS: schools previously attended
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 7
	 * HtmlCellule: 2
	 */                   
	protected void _enfantEcolesPrecedemmentFrequentees(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childDescription
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: description
	 * NomAffichage.enUS: description
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCellule: 1
	 */                   
	protected void _enfantDescription(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childObjectives
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: objectifs
	 * NomAffichage.enUS: objectives
	 * Multiligne: true
	 * Definir: true
	 * HtmlLigne: 8
	 * HtmlCellule: 2
	 */                   
	protected void _enfantObjectifs(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: childPottyTrained
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: propre
	 * NomAffichage.enUS: potty trained
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 */                  
	protected void _enfantPropre(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentGroupName
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: nom du groupe
	 * NomAffichage.enUS: group name
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 3
	 */                   
	protected void _inscriptionNomGroupe(Couverture<String> c) {
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentPaymentEachMonth
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: paiement chaque mois
	 * NomAffichage.enUS: payment each month
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 3
	 */                   
	protected void _inscriptionPaimentChaqueMois(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentPaymentComplete
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: paiement complet
	 * NomAffichage.enUS: complete payment
	 * Definir: true
	 * HtmlLigne: 4
	 * HtmlCellule: 2
	 */                       
	protected void _inscriptionPaimentComplet(Couverture<Boolean> c) {
		c.o(false);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: customerProfileId
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * HtmlLigne: 3
	 * HtmlCellule: 4
	 * NomAffichage.frFR: customer profile ID
	 * NomAffichage.enUS: customer profile ID
	 */               
	protected void _customerProfileId(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentChargeDate
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 * r: fraisRecherche
	 * r.enUS: feeSearch
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: paiementDateMax
	 * r.enUS: paymentDateMax
	 */
	protected void _inscriptionDateFrais(Couverture<LocalDate> c) {
		SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(fraisRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
		LocalDate o = Optional.ofNullable((Date)facets.get("paiementDateMax")).map(d -> d.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate()).orElse(null);
		c.o(o);
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: paymentFacets
	 * r: paiementRecherche
	 * r.enUS: paymentSearch
	 */
	protected void _paiementFacets(Couverture<SimpleOrderedMap> c) {
		c.o((SimpleOrderedMap)Optional.ofNullable(paiementRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap()));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: paymentAmount
	 * Indexe: true
	 * Stocke: true
	 * r: paiementFacets
	 * r.enUS: paymentFacets
	 * r: paiementMontant
	 * r.enUS: paymentAmount
	 */
	protected void _paiementMontant(Couverture<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paiementFacets.get("sum_paiementMontant")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2)));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: chargeAmount
	 * Indexe: true
	 * Stocke: true
	 * r: paiementFacets
	 * r.enUS: paymentFacets
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 */
	protected void _fraisMontant(Couverture<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paiementFacets.get("sum_fraisMontant")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2)));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: chargeAmountFuture
	 * Indexe: true
	 * Stocke: true
	 * r: paiementFacets
	 * r.enUS: paymentFacets
	 * r: fraisMontantFuture
	 * r.enUS: chargeAmountFuture
	 */
	protected void _fraisMontantFuture(Couverture<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paiementFacets.get("sum_chargeAmountFuture")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2)));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: chargeAmountDue
	 * Indexe: true
	 * Stocke: true
	 * r: paiementFacets
	 * r.enUS: paymentFacets
	 * r: fraisMontantDu
	 * r.enUS: chargeAmountDue
	 */
	protected void _fraisMontantDu(Couverture<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paiementFacets.get("sum_chargeAmountDue")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2)));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: chargesNow
	 * Indexe: true
	 * Stocke: true
	 * r: paiementMontant
	 * r.enUS: paymentAmount
	 * r: fraisMontant
	 * r.enUS: chargeAmount
	 * r: fraisMontantFuture
	 * r.enUS: chargeAmountFuture
	 */
	protected void _fraisMaintenant(Couverture<BigDecimal> c) {
		c.o(fraisMontant.subtract(paiementMontant).subtract(fraisMontantFuture));
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: paymentsCurrent
	 * Indexe: true
	 * Stocke: true
	 * r: fraisMaintenant
	 * r.enUS: chargesNow
	 */
	protected void _paiementsAJour(Couverture<Boolean> c) {
		c.o(fraisMaintenant.compareTo(BigDecimal.ZERO) <= 0);
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: paymentsLate
	 * Indexe: true
	 * Stocke: true
	 * r: fraisMaintenant
	 * r.enUS: chargesNow
	 * r: fraisMontantDu
	 * r.enUS: chargeAmountDue
	 */
	protected void _paiementsEnRetard(Couverture<Boolean> c) {
		c.o(fraisMaintenant.subtract(fraisMontantDu).compareTo(BigDecimal.ZERO) > 0);
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: paymentsAhead
	 * Indexe: true
	 * Stocke: true
	 * r: fraisMaintenant
	 * r.enUS: chargesNow
	 * r: fraisMontantDu
	 * r.enUS: chargeAmountDue
	 */
	protected void _paiementsEnAvance(Couverture<Boolean> c) {
		c.o(fraisMaintenant.compareTo(BigDecimal.ZERO) < 0);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: createdYear
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: crée l'année
	 * NomAffichage.enUS: created year
	 * r: cree
	 * r.enUS: created
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _creeDAnnee(Couverture<Integer> c) {
		if(cree != null)
			c.o(cree.getYear());
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: createdDayOfWeek
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: crée jour de la semaine
	 * NomAffichage.enUS: created day of the week
	 * r: cree
	 * r.enUS: created
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _creeJourDeSemaine(Couverture<String> c) {
		if(cree != null)
			c.o(cree.format(DateTimeFormatter.ofPattern("EEEE", Locale.FRANCE)));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: createdMonthOfYear
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: crée mois de l'année
	 * NomAffichage.enUS: created month of the year
	 * r: cree
	 * r.enUS: created
	 * r: Locale.FRANCE
	 * r.enUS: Locale.US
	 */                       
	protected void _creeMoisDAnnee(Couverture<String> c) {
		if(cree != null)
			c.o(cree.format(DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE)));
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: createdHourOfDay
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: heure du jour
	 * NomAffichage.enUS: hour of day
	 * r: cree
	 * r.enUS: created
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: FORMATHeureAffichage
	 * r.enUS: FORMATTimeDisplay
	 */                       
	protected void _creeHeureDuJour(Couverture<String> c) {
		if(cree != null) {
			ZonedDateTime cree1 = cree.truncatedTo(ChronoUnit.HOURS);
			ZonedDateTime cree2 = cree1.plusHours(1);
			c.o(cree1.format(MiseEnPage.FORMATHeureAffichage) + "-" + cree2.format(MiseEnPage.FORMATHeureAffichage));
		}
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDaysOfWeek
	 * Indexe: true
	 * Stocke: true
	 * NomAffichage.frFR: jours de la semaine
	 * NomAffichage.enUS: days of the week
	 * 
	 * r: "dimanche"
	 * r.enUS: "Sunday"
	 * r: "lundi"
	 * r.enUS: "Monday"
	 * r: "mardi"
	 * r.enUS: "Tuesday"
	 * r: "mercredi"
	 * r.enUS: "Wednesday"
	 * r: "jeudi"
	 * r.enUS: "Thursday"
	 * r: "vendredi"
	 * r.enUS: "Friday"
	 * r: "samedi"
	 * r.enUS: "Saturday"
	 * 
	 * r: Dimanche
	 * r.enUS: Sunday
	 * r: Lundi
	 * r.enUS: Monday
	 * r: Mardi
	 * r.enUS: Tuesday
	 * r: Mercredi
	 * r.enUS: Wednesday
	 * r: Jeudi
	 * r.enUS: Thursday
	 * r: Vendredi
	 * r.enUS: Friday
	 * r: Samedi
	 * r.enUS: Saturday
	 * 
	 * r: bloc
	 * r.enUS: block
	 */                       
	protected void _inscriptionJoursDeSemaine(List<String> l) {
		if(BooleanUtils.isTrue(blocDimanche))
			l.add("dimanche");
		if(BooleanUtils.isTrue(blocLundi))
			l.add("lundi");
		if(BooleanUtils.isTrue(blocMardi))
			l.add("mardi");
		if(BooleanUtils.isTrue(blocMercredi))
			l.add("mercredi");
		if(BooleanUtils.isTrue(blocJeudi))
			l.add("jeudi");
		if(BooleanUtils.isTrue(blocVendredi))
			l.add("vendredi");
		if(BooleanUtils.isTrue(blocSamedi))
			l.add("samedi");
	}

	/**     
	 * {@inheritDoc}
	 * Var.enUS: enrollmentParentNames
	 * Stocke: true
	 * Definir: true
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardien
	 * r.enUS: guardian
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 */     
	protected void _inscriptionNomsParents(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(meres.size() == 0 && peres.size() == 0) {
			for(GardienScolaire o : gardiens) {
				if(o.getPersonneNomCompletPrefere() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonneNomCompletPrefere());
				}
			}
		}
		else {
			for(MereScolaire o : meres) {
				if(o.getPersonneNomCompletPrefere() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonneNomCompletPrefere());
				}
			}
			for(PereScolaire o : peres) {
				if(o.getPersonneNomCompletPrefere() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonneNomCompletPrefere());
				}
			}
		}
		c.o(b.toString());
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentEmails
	 * Indexe: true
	 * Stocke: true
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardien
	 * r.enUS: guardian
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * r: PersonneMail
	 * r.enUS: PersonEmail
	 * r: personneMail
	 * r.enUS: personEmail
	 */     
	protected void _inscriptionMails(List<String> l) {
		if(meres.size() == 0 && peres.size() == 0) {
			for(GardienScolaire o : gardiens) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					l.add(o.getPersonneMail());
				}
			}
		}
		else {
			for(MereScolaire o : meres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					l.add(o.getPersonneMail());
				}
			}
			for(PereScolaire o : peres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					l.add(o.getPersonneMail());
				}
			}
		}
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentParentEmails
	 * Stocke: true
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardien
	 * r.enUS: guardian
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * r: PersonneMail
	 * r.enUS: PersonEmail
	 * r: personneMail
	 * r.enUS: personEmail
	 */     
	protected void _inscriptionMailsParents(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(meres.size() == 0 && peres.size() == 0) {
			for(GardienScolaire o : gardiens) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonneNomCompletPrefere()).append(" <").append(o.getPersonneMail()).append(">, ");
				}
			}
		}
		else {
			for(MereScolaire o : meres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonneNomCompletPrefere()).append(" <").append(o.getPersonneMail()).append(">, ");
				}
			}
			for(PereScolaire o : peres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonneNomCompletPrefere()).append(" <").append(o.getPersonneMail()).append(">, ");
				}
			}
		}
		c.o(b.toString());
	}

	/**
	 * Var.enUS: enrollmentParentNameLines
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardiens
	 * r.enUS: guardians
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * Stocke: true
	 */
	protected void _inscriptionNomParentLignes(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(meres.size() == 0 && peres.size() == 0) {
			gardiens.stream().forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append("\n"));
		}
		else {
			meres.stream().forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append("\n"));
			peres.stream().forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append("\n"));
		}
		c.o(b.toString().trim());
	}

	/**
	 * Var.enUS: enrollmentParentEmailLines
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardiens
	 * r.enUS: guardians
	 * r: PersonneMail
	 * r.enUS: PersonEmail
	 * Stocke: true
	 */
	protected void _inscriptionMailParentLignes(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(meres.size() == 0 && peres.size() == 0) {
			for(GardienScolaire o : gardiens) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonneMail()).append("\n");
				}
			}
		}
		else {
			for(MereScolaire o : meres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonneMail()).append("\n");
				}
			}
			for(PereScolaire o : peres) {
				String personneMail = o.getPersonneMail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonneMail()).append("\n");
				}
			}
		}
		c.o(b.toString().trim());
	}

	/**
	 * Var.enUS: enrollmentParentDetailLines
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardiens
	 * r.enUS: guardians
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * r: PersonneNumeroTelephone
	 * r.enUS: PersonPhoneNumber
	 * Stocke: true
	 */
	protected void _inscriptionDetailParentLignes(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		if(meres.size() == 0 && peres.size() == 0) {
			gardiens.stream().forEach(o -> b.append("G- ").append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		}
		else {
			meres.stream().forEach(o -> b.append("M- ").append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
			peres.stream().forEach(o -> b.append("D- ").append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		}
		c.o(b.toString().trim());
	}

	/**
	 * Var.enUS: enrollmentPickupDetailLines
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardiens
	 * r.enUS: guardians
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * r: PersonneNumeroTelephone
	 * r.enUS: PersonPhoneNumber
	 * r: PersonneChercher
	 * r.enUS: PersonPickup
	 * Stocke: true
	 */
	protected void _inscriptionChercherParentLignes(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		meres.stream().filter(o -> o.getPersonneChercher()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		peres.stream().filter(o -> o.getPersonneChercher()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		gardiens.stream().filter(o -> o.getPersonneChercher()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		c.o(b.toString().trim());
	}

	/**
	 * Var.enUS: enrollmentEmergencyContactDetailLines
	 * r: meres
	 * r.enUS: moms
	 * r: peres
	 * r.enUS: dads
	 * r: gardiens
	 * r.enUS: guardians
	 * r: PersonneNomCompletPrefere
	 * r.enUS: PersonCompleteNamePreferred
	 * r: PersonneNumeroTelephone
	 * r.enUS: PersonPhoneNumber
	 * r: PersonneContactUrgence
	 * r.enUS: PersonEmergencyContact
	 * Stocke: true
	 */
	protected void _inscriptionContactUrgenceParentLignes(Couverture<String> c) {
		StringBuilder b = new StringBuilder();
		meres.stream().filter(o -> o.getPersonneContactUrgence()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		peres.stream().filter(o -> o.getPersonneContactUrgence()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		gardiens.stream().filter(o -> o.getPersonneContactUrgence()).forEach(o -> b.append(o.getPersonneNomCompletPrefere()).append(" ").append(o.getPersonneNumeroTelephone()).append("\n"));
		c.o(b.toString().trim());
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature1
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature1(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature2
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature2(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature3
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature3(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature4
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature4(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature5
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature5(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature6
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature6(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature7
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature7(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature8
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature8(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature9
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature9(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentSignature10
	 * Stocke: true
	 * Definir: true
	 * Signature: true
	 */     
	protected void _inscriptionSignature10(Couverture<String> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate1
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate1(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate2
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate2(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate3
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate3(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate4
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate4(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate5
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate5(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate6
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate6(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate7
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate7(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate8
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate8(Couverture<LocalDate> c) {
	}

	/** 
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate9
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate9(Couverture<LocalDate> c) {
	}

	/**       
	 * {@inheritDoc}
	 * Var.enUS: enrollmentDate10
	 * Indexe: true
	 * Stocke: true
	 * Definir: true
	 */
	protected void _inscriptionDate10(Couverture<LocalDate> c) {
	}

	/**
	 * Var.enUS: enrollmentEnrollments
	 */
	protected void _inscriptionsInscription(List<InscriptionScolaire> l) {}

	/**
	 * Var.enUS: childImmunizationsReceived
	 * r: inscriptionImmunisations
	 * r.enUS: enrollmentImmunizations
	 * r: "oui"
	 * r.enUS: "yes"
	 * r: "non"
	 * r.enUS: "no"
	 * Indexe: true
	 * Stocke: true
	 */
	protected void _enfantImmunisationsRecu(Couverture<String> c) {
		c.o(inscriptionImmunisations ? "oui" : "non");
	}

	/**
	 * Var.enUS: childPhotosApproved
	 * r: inscriptionDate9
	 * r.enUS: enrollmentDate9
	 * r: "oui"
	 * r.enUS: "yes"
	 * r: "non"
	 * r.enUS: "no"
	 * Indexe: true
	 * Stocke: true
	 */  
	protected void _enfantPhotosApprouve(Couverture<String> c) {
		c.o(inscriptionDate9 != null ? "oui" : "non");
	}


	/**
	 * Var.enUS: enrollmentNumber
	 */
	protected void _inscriptionNumero(Couverture<Integer> c) {}

	/**           
	 * {@inheritDoc}
	 * Var.enUS: enrollmentCompleteName
	 * Indexe: true
	 * Stocke: true
	 * VarH2: true
	 * VarTitre: true
	 * NomAffichage.frFR: nom
	 * NomAffichage.enUS: name
	 * r: "inscription pour l'enfant %s %s %s"
	 * r.enUS: "enrollment for the child %s %s %s"
	 * r: "inscription %s %s %s"
	 * r.enUS: "enrollment %s %s %s"
	 * r: getPersonneNomCompletPrefere
	 * r.enUS: getPersonCompleteNamePreferred
	 * r: enfant_
	 * r.enUS: child_
	 * r: annee
	 * r.enUS: year
	 * r: AnneeNomCourt
	 * r.enUS: YearShortName
	 * r: ecoleEmplacement
	 * r.enUS: schoolLocation
	 */  
	protected void _inscriptionNomComplet(Couverture<String> c) {
		String o;
		if(enfant_ != null)
			o = String.format("inscription pour l'enfant %s %s %s", enfant_.getPersonneNomCompletPrefere(), annee_ == null ? "" : annee_.getAnneeNomCourt(), ecoleEmplacement);
		else
			o = String.format("inscription %s %s %s", pk, " ", annee_ == null ? "" : annee_.getAnneeNomCourt(), ecoleEmplacement);
		c.o(o);
	}

	/**
	 * {@inheritDoc}
	 * Var.enUS: _objectTitle
	 * r: inscriptionNomComplet
	 * r.enUS: enrollmentCompleteName
	 */
	@Override
	protected void _objetTitre(Couverture<String> c) {
		c.o(inscriptionNomComplet);
	}
}
