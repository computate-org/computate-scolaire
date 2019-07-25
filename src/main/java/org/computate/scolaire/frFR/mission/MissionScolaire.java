package org.computate.scolaire.frFR.mission;       

import java.text.Normalizer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.couverture.Couverture;



/**
 * Modele: true
 * NomCanonique.enUS: org.computate.scolaire.enUS.mission.Mission
 * ApiUri: /api/mission
 * ApiMethode: Recherche
 * ApiMethode: POST
 * ApiMethode: PATCH
 * ApiMethode: GET
 * ApiMethode: DELETE
 * ApiMotCleRecherchePage.frFR: page
 * ApiMotCleRecherchePage.enUS: page
 * ApiUriRecherchePage.frFR: /frFR/mission
 * ApiUriRecherchePage.enUS: /enUS/mission
 * UnNomMinuscule.frFR: une mission
 * UnNomMinuscule.enUS: a mission
 * Couleur: pink
 * IconeGroupe: regular
 * IconeNom: fort-awesome
 * 
 * ApiTag.enUS: EducationMission
 * ApiTag.frFR: MissionScolaire
 */   
public class MissionScolaire extends MissionScolaireGen<Cluster> {  

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlColonne: 2
	 * NomAffichage.frFR: nom de la mission
	 * NomAffichage.enUS: mission name
	 * Description.frFR: Nom de la mission. 
	 * Description.enUS: Name of the mission. 
	 */ 
	protected void _missionNom(Couverture<String> c) {
	}

	/**
	 * Indexe: true
	 * Stocke: true
	 * HtmlLigne: 3
	 * HtmlColonne: 3
	 * NomAffichage.frFR: description de la mission
	 * NomAffichage.enUS: mission description
	 * Description.frFR: Numéro de téléphone de l'école. 
	 * Description.enUS: Telephone number of the school. 
	 */ 
	protected void _ecoleNumeroTelephone(Couverture<String> c) {
	}

	/**   
	 * Stocke: true
	 * Description.frFR: 
	 * Description.enUS: 
	 * NomAffichage.frFR: 
	 * NomAffichage.enUS: 
	 */          
	protected void _missionId(Couverture<String> c) {
		if(missionNom != null) {
			String s = Normalizer.normalize(missionNom, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
			c.o(s);
		}
		else if(pk != null){
			c.o(pk.toString());
		}
	}

	/**	la version plus courte de l'URL qui commence avec « / » 
	 * Indexe: true
	 * Stocke: true
	 * r: frFR
	 * r.enUS: enUS
	 * **/
	protected void _pageUri(Couverture<String> c)  {
		if(missionId != null) {
			String o = "/mission/" + missionId;
			c.o(o);
		}
	}

	public void htmlBody() {
		super.htmlBody();
	}
}

