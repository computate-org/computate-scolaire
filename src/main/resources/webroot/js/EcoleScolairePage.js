
// Recherche //

/**
 */
function rechercheEcoleScolaire($formulaireFiltres) {
	var filtres = [];

	var filtreArchive = $formulaireFiltres.find('.valeurArchive').prop('checked');
	if(filtreArchive != null && filtreArchive !== '')
		filtres.push({ name: 'fq', value: 'archive:' + filtreArchive });

	var filtreSupprime = $formulaireFiltres.find('.valeurSupprime').prop('checked');
	if(filtreSupprime != null && filtreSupprime !== '')
		filtres.push({ name: 'fq', value: 'supprime:' + filtreSupprime });

	var filtreEcoleNom = $formulaireFiltres.find('.valeurEcoleNom').val();
	if(filtreEcoleNom != null && filtreEcoleNom !== '')
		filtres.push({ name: 'fq', value: 'ecoleNom:' + filtreEcoleNom });

	var filtreEcoleNumeroTelephone = $formulaireFiltres.find('.valeurEcoleNumeroTelephone').val();
	if(filtreEcoleNumeroTelephone != null && filtreEcoleNumeroTelephone !== '')
		filtres.push({ name: 'fq', value: 'ecoleNumeroTelephone:' + filtreEcoleNumeroTelephone });

	var filtreEcoleAdministrateurNom = $formulaireFiltres.find('.valeurEcoleAdministrateurNom').val();
	if(filtreEcoleAdministrateurNom != null && filtreEcoleAdministrateurNom !== '')
		filtres.push({ name: 'fq', value: 'ecoleAdministrateurNom:' + filtreEcoleAdministrateurNom });

	var filtreEcoleAddresse = $formulaireFiltres.find('.valeurEcoleAddresse').val();
	if(filtreEcoleAddresse != null && filtreEcoleAddresse !== '')
		filtres.push({ name: 'fq', value: 'ecoleAddresse:' + filtreEcoleAddresse });

	var filtrePk = $formulaireFiltres.find('.valeurPk').val();
	if(filtrePk != null && filtrePk !== '')
		filtres.push({ name: 'fq', value: 'pk:' + filtrePk });

	var filtreId = $formulaireFiltres.find('.valeurId').val();
	if(filtreId != null && filtreId !== '')
		filtres.push({ name: 'fq', value: 'id:' + filtreId });

	var filtreUtilisateurId = $formulaireFiltres.find('.valeurUtilisateurId').val();
	if(filtreUtilisateurId != null && filtreUtilisateurId !== '')
		filtres.push({ name: 'fq', value: 'utilisateurId:' + filtreUtilisateurId });

	var filtreCree = $formulaireFiltres.find('.valeurCree').val();
	if(filtreCree != null && filtreCree !== '')
		filtres.push({ name: 'fq', value: 'cree:' + filtreCree });

	var filtreModifie = $formulaireFiltres.find('.valeurModifie').val();
	if(filtreModifie != null && filtreModifie !== '')
		filtres.push({ name: 'fq', value: 'modifie:' + filtreModifie });

	var filtreClasseNomCanonique = $formulaireFiltres.find('.valeurClasseNomCanonique').val();
	if(filtreClasseNomCanonique != null && filtreClasseNomCanonique !== '')
		filtres.push({ name: 'fq', value: 'classeNomCanonique:' + filtreClasseNomCanonique });

	var filtreClasseNomSimple = $formulaireFiltres.find('.valeurClasseNomSimple').val();
	if(filtreClasseNomSimple != null && filtreClasseNomSimple !== '')
		filtres.push({ name: 'fq', value: 'classeNomSimple:' + filtreClasseNomSimple });

	var filtreEcoleCle = $formulaireFiltres.find('.valeurEcoleCle').val();
	if(filtreEcoleCle != null && filtreEcoleCle !== '')
		filtres.push({ name: 'fq', value: 'ecoleCle:' + filtreEcoleCle });

	var filtreEnfantCles = $formulaireFiltres.find('.valeurEnfantCles').val();
	if(filtreEnfantCles != null && filtreEnfantCles !== '')
		filtres.push({ name: 'fq', value: 'enfantCles:' + filtreEnfantCles });

	var filtreBlocCles = $formulaireFiltres.find('.valeurBlocCles').val();
	if(filtreBlocCles != null && filtreBlocCles !== '')
		filtres.push({ name: 'fq', value: 'blocCles:' + filtreBlocCles });

	var filtreGroupeAgeCles = $formulaireFiltres.find('.valeurGroupeAgeCles').val();
	if(filtreGroupeAgeCles != null && filtreGroupeAgeCles !== '')
		filtres.push({ name: 'fq', value: 'groupeAgeCles:' + filtreGroupeAgeCles });

	var filtreSessionCles = $formulaireFiltres.find('.valeurSessionCles').val();
	if(filtreSessionCles != null && filtreSessionCles !== '')
		filtres.push({ name: 'fq', value: 'sessionCles:' + filtreSessionCles });

	var filtreSaisonCles = $formulaireFiltres.find('.valeurSaisonCles').val();
	if(filtreSaisonCles != null && filtreSaisonCles !== '')
		filtres.push({ name: 'fq', value: 'saisonCles:' + filtreSaisonCles });

	var filtreAnneeCles = $formulaireFiltres.find('.valeurAnneeCles').val();
	if(filtreAnneeCles != null && filtreAnneeCles !== '')
		filtres.push({ name: 'fq', value: 'anneeCles:' + filtreAnneeCles });

	var filtreScolaireTri = $formulaireFiltres.find('.valeurScolaireTri').val();
	if(filtreScolaireTri != null && filtreScolaireTri !== '')
		filtres.push({ name: 'fq', value: 'scolaireTri:' + filtreScolaireTri });

	var filtreEcoleTri = $formulaireFiltres.find('.valeurEcoleTri').val();
	if(filtreEcoleTri != null && filtreEcoleTri !== '')
		filtres.push({ name: 'fq', value: 'ecoleTri:' + filtreEcoleTri });

	var filtrePageUri = $formulaireFiltres.find('.valeurPageUri').val();
	if(filtrePageUri != null && filtrePageUri !== '')
		filtres.push({ name: 'fq', value: 'pageUri:' + filtrePageUri });
	$.ajax({
		url: '/api/ecole?' + $.param(filtres)
		, dataType: 'json'
		, type: 'GET'
		, contentType: 'application/json; charset=utf-8'
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}

// POST //

/**
 */
function postEcoleScolaire($formulaireValeurs) {
	var valeurs = {};

	var valeurArchive = $formulaireValeurs.find('.valeurArchive').prop('checked');
	if(valeurArchive != null && valeurArchive !== '')
		valeurs['archive'] = valeurArchive;

	var valeurSupprime = $formulaireValeurs.find('.valeurSupprime').prop('checked');
	if(valeurSupprime != null && valeurSupprime !== '')
		valeurs['supprime'] = valeurSupprime;

	var valeurEcoleNom = $formulaireValeurs.find('.valeurEcoleNom').val();
	if(valeurEcoleNom != null && valeurEcoleNom !== '')
		valeurs['ecoleNom'] = valeurEcoleNom;

	var valeurEcoleNumeroTelephone = $formulaireValeurs.find('.valeurEcoleNumeroTelephone').val();
	if(valeurEcoleNumeroTelephone != null && valeurEcoleNumeroTelephone !== '')
		valeurs['ecoleNumeroTelephone'] = valeurEcoleNumeroTelephone;

	var valeurEcoleAdministrateurNom = $formulaireValeurs.find('.valeurEcoleAdministrateurNom').val();
	if(valeurEcoleAdministrateurNom != null && valeurEcoleAdministrateurNom !== '')
		valeurs['ecoleAdministrateurNom'] = valeurEcoleAdministrateurNom;

	var valeurEcoleAddresse = $formulaireValeurs.find('.valeurEcoleAddresse').val();
	if(valeurEcoleAddresse != null && valeurEcoleAddresse !== '')
		valeurs['ecoleAddresse'] = valeurEcoleAddresse;

	var valeurEcoleCle = $formulaireValeurs.find('.valeurEcoleCle').val();
	if(valeurEcoleCle != null && valeurEcoleCle !== '')
		valeurs['ecoleCle'] = valeurEcoleCle;

	$.ajax({
		url: '/api/ecole'
		, dataType: 'json'
		, type: 'POST'
		, contentType: 'application/json; charset=utf-8'
		, data: JSON.stringify(valeurs)
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}

// PATCH //

/**
 * Modifier un ou plusiers écoles sans valuers qui change, 
 * ou changer des valeurs pour un ou plusiers l'école. 
 * @param params: [ "q=*:*", "fq=pk:1", "sort=pk asc", "rows=1", "fl=pk" ]
 *        Une liste des opérations de recherche sur des écoles 
 *        pour rechercher "q=*:*", filtrer "fq=pk:1", trier "sort=pk desc", 
 *        limiter les résultats "rows=1", ou limiter les valeurs "fl=pk". 
 * @param valeurs Noms des champs et valeurs à changer selon les filtres fq. 
 *           Example: { pk: 1 }
 */
function patchEcoleScolaire($formulaireFiltres, $formulaireValeurs) {
	var filtres = [];

	var filtreArchive = $formulaireFiltres.find('.valeurArchive').prop('checked');
	if(filtreArchive != null && filtreArchive !== '')
		filtres.push({ name: 'fq', value: 'archive:' + filtreArchive });

	var filtreSupprime = $formulaireFiltres.find('.valeurSupprime').prop('checked');
	if(filtreSupprime != null && filtreSupprime !== '')
		filtres.push({ name: 'fq', value: 'supprime:' + filtreSupprime });

	var filtreEcoleNom = $formulaireFiltres.find('.valeurEcoleNom').val();
	if(filtreEcoleNom != null && filtreEcoleNom !== '')
		filtres.push({ name: 'fq', value: 'ecoleNom:' + filtreEcoleNom });

	var filtreEcoleNumeroTelephone = $formulaireFiltres.find('.valeurEcoleNumeroTelephone').val();
	if(filtreEcoleNumeroTelephone != null && filtreEcoleNumeroTelephone !== '')
		filtres.push({ name: 'fq', value: 'ecoleNumeroTelephone:' + filtreEcoleNumeroTelephone });

	var filtreEcoleAdministrateurNom = $formulaireFiltres.find('.valeurEcoleAdministrateurNom').val();
	if(filtreEcoleAdministrateurNom != null && filtreEcoleAdministrateurNom !== '')
		filtres.push({ name: 'fq', value: 'ecoleAdministrateurNom:' + filtreEcoleAdministrateurNom });

	var filtreEcoleAddresse = $formulaireFiltres.find('.valeurEcoleAddresse').val();
	if(filtreEcoleAddresse != null && filtreEcoleAddresse !== '')
		filtres.push({ name: 'fq', value: 'ecoleAddresse:' + filtreEcoleAddresse });

	var filtrePk = $formulaireFiltres.find('.valeurPk').val();
	if(filtrePk != null && filtrePk !== '')
		filtres.push({ name: 'fq', value: 'pk:' + filtrePk });

	var filtreId = $formulaireFiltres.find('.valeurId').val();
	if(filtreId != null && filtreId !== '')
		filtres.push({ name: 'fq', value: 'id:' + filtreId });

	var filtreUtilisateurId = $formulaireFiltres.find('.valeurUtilisateurId').val();
	if(filtreUtilisateurId != null && filtreUtilisateurId !== '')
		filtres.push({ name: 'fq', value: 'utilisateurId:' + filtreUtilisateurId });

	var filtreCree = $formulaireFiltres.find('.valeurCree').val();
	if(filtreCree != null && filtreCree !== '')
		filtres.push({ name: 'fq', value: 'cree:' + filtreCree });

	var filtreModifie = $formulaireFiltres.find('.valeurModifie').val();
	if(filtreModifie != null && filtreModifie !== '')
		filtres.push({ name: 'fq', value: 'modifie:' + filtreModifie });

	var filtreClasseNomCanonique = $formulaireFiltres.find('.valeurClasseNomCanonique').val();
	if(filtreClasseNomCanonique != null && filtreClasseNomCanonique !== '')
		filtres.push({ name: 'fq', value: 'classeNomCanonique:' + filtreClasseNomCanonique });

	var filtreClasseNomSimple = $formulaireFiltres.find('.valeurClasseNomSimple').val();
	if(filtreClasseNomSimple != null && filtreClasseNomSimple !== '')
		filtres.push({ name: 'fq', value: 'classeNomSimple:' + filtreClasseNomSimple });

	var filtreEcoleCle = $formulaireFiltres.find('.valeurEcoleCle').val();
	if(filtreEcoleCle != null && filtreEcoleCle !== '')
		filtres.push({ name: 'fq', value: 'ecoleCle:' + filtreEcoleCle });

	var filtreEnfantCles = $formulaireFiltres.find('.valeurEnfantCles').val();
	if(filtreEnfantCles != null && filtreEnfantCles !== '')
		filtres.push({ name: 'fq', value: 'enfantCles:' + filtreEnfantCles });

	var filtreBlocCles = $formulaireFiltres.find('.valeurBlocCles').val();
	if(filtreBlocCles != null && filtreBlocCles !== '')
		filtres.push({ name: 'fq', value: 'blocCles:' + filtreBlocCles });

	var filtreGroupeAgeCles = $formulaireFiltres.find('.valeurGroupeAgeCles').val();
	if(filtreGroupeAgeCles != null && filtreGroupeAgeCles !== '')
		filtres.push({ name: 'fq', value: 'groupeAgeCles:' + filtreGroupeAgeCles });

	var filtreSessionCles = $formulaireFiltres.find('.valeurSessionCles').val();
	if(filtreSessionCles != null && filtreSessionCles !== '')
		filtres.push({ name: 'fq', value: 'sessionCles:' + filtreSessionCles });

	var filtreSaisonCles = $formulaireFiltres.find('.valeurSaisonCles').val();
	if(filtreSaisonCles != null && filtreSaisonCles !== '')
		filtres.push({ name: 'fq', value: 'saisonCles:' + filtreSaisonCles });

	var filtreAnneeCles = $formulaireFiltres.find('.valeurAnneeCles').val();
	if(filtreAnneeCles != null && filtreAnneeCles !== '')
		filtres.push({ name: 'fq', value: 'anneeCles:' + filtreAnneeCles });

	var filtreScolaireTri = $formulaireFiltres.find('.valeurScolaireTri').val();
	if(filtreScolaireTri != null && filtreScolaireTri !== '')
		filtres.push({ name: 'fq', value: 'scolaireTri:' + filtreScolaireTri });

	var filtreEcoleTri = $formulaireFiltres.find('.valeurEcoleTri').val();
	if(filtreEcoleTri != null && filtreEcoleTri !== '')
		filtres.push({ name: 'fq', value: 'ecoleTri:' + filtreEcoleTri });

	var filtrePageUri = $formulaireFiltres.find('.valeurPageUri').val();
	if(filtrePageUri != null && filtrePageUri !== '')
		filtres.push({ name: 'fq', value: 'pageUri:' + filtrePageUri });

	var valeurs = {};

	var setArchive = $formulaireValeurs.find('.setArchive').prop('checked');
	if(setArchive != null && setArchive !== '')
		valeurs['setArchive'] = setArchive;
	var addArchive = $formulaireValeurs.find('.addArchive').prop('checked');
	if(addArchive != null && addArchive !== '')
		valeurs['addArchive'] = addArchive;
	var removeArchive = $formulaireValeurs.find('.removeArchive').prop('checked');
	if(removeArchive != null && removeArchive !== '')
		valeurs['removeArchive'] = removeArchive;

	var setSupprime = $formulaireValeurs.find('.setSupprime').prop('checked');
	if(setSupprime != null && setSupprime !== '')
		valeurs['setSupprime'] = setSupprime;
	var addSupprime = $formulaireValeurs.find('.addSupprime').prop('checked');
	if(addSupprime != null && addSupprime !== '')
		valeurs['addSupprime'] = addSupprime;
	var removeSupprime = $formulaireValeurs.find('.removeSupprime').prop('checked');
	if(removeSupprime != null && removeSupprime !== '')
		valeurs['removeSupprime'] = removeSupprime;

	var setEcoleNom = $formulaireValeurs.find('.setEcoleNom').val();
	if(setEcoleNom != null && setEcoleNom !== '')
		valeurs['setEcoleNom'] = setEcoleNom;
	var addEcoleNom = $formulaireValeurs.find('.addEcoleNom').val();
	if(addEcoleNom != null && addEcoleNom !== '')
		valeurs['addEcoleNom'] = addEcoleNom;
	var removeEcoleNom = $formulaireValeurs.find('.removeEcoleNom').val();
	if(removeEcoleNom != null && removeEcoleNom !== '')
		valeurs['removeEcoleNom'] = removeEcoleNom;

	var setEcoleNumeroTelephone = $formulaireValeurs.find('.setEcoleNumeroTelephone').val();
	if(setEcoleNumeroTelephone != null && setEcoleNumeroTelephone !== '')
		valeurs['setEcoleNumeroTelephone'] = setEcoleNumeroTelephone;
	var addEcoleNumeroTelephone = $formulaireValeurs.find('.addEcoleNumeroTelephone').val();
	if(addEcoleNumeroTelephone != null && addEcoleNumeroTelephone !== '')
		valeurs['addEcoleNumeroTelephone'] = addEcoleNumeroTelephone;
	var removeEcoleNumeroTelephone = $formulaireValeurs.find('.removeEcoleNumeroTelephone').val();
	if(removeEcoleNumeroTelephone != null && removeEcoleNumeroTelephone !== '')
		valeurs['removeEcoleNumeroTelephone'] = removeEcoleNumeroTelephone;

	var setEcoleAdministrateurNom = $formulaireValeurs.find('.setEcoleAdministrateurNom').val();
	if(setEcoleAdministrateurNom != null && setEcoleAdministrateurNom !== '')
		valeurs['setEcoleAdministrateurNom'] = setEcoleAdministrateurNom;
	var addEcoleAdministrateurNom = $formulaireValeurs.find('.addEcoleAdministrateurNom').val();
	if(addEcoleAdministrateurNom != null && addEcoleAdministrateurNom !== '')
		valeurs['addEcoleAdministrateurNom'] = addEcoleAdministrateurNom;
	var removeEcoleAdministrateurNom = $formulaireValeurs.find('.removeEcoleAdministrateurNom').val();
	if(removeEcoleAdministrateurNom != null && removeEcoleAdministrateurNom !== '')
		valeurs['removeEcoleAdministrateurNom'] = removeEcoleAdministrateurNom;

	var setEcoleAddresse = $formulaireValeurs.find('.setEcoleAddresse').val();
	if(setEcoleAddresse != null && setEcoleAddresse !== '')
		valeurs['setEcoleAddresse'] = setEcoleAddresse;
	var addEcoleAddresse = $formulaireValeurs.find('.addEcoleAddresse').val();
	if(addEcoleAddresse != null && addEcoleAddresse !== '')
		valeurs['addEcoleAddresse'] = addEcoleAddresse;
	var removeEcoleAddresse = $formulaireValeurs.find('.removeEcoleAddresse').val();
	if(removeEcoleAddresse != null && removeEcoleAddresse !== '')
		valeurs['removeEcoleAddresse'] = removeEcoleAddresse;

	var setEcoleCle = $formulaireValeurs.find('.setEcoleCle').val();
	if(setEcoleCle != null && setEcoleCle !== '')
		valeurs['setEcoleCle'] = setEcoleCle;
	var addEcoleCle = $formulaireValeurs.find('.addEcoleCle').val();
	if(addEcoleCle != null && addEcoleCle !== '')
		valeurs['addEcoleCle'] = addEcoleCle;
	var removeEcoleCle = $formulaireValeurs.find('.removeEcoleCle').val();
	if(removeEcoleCle != null && removeEcoleCle !== '')
		valeurs['removeEcoleCle'] = removeEcoleCle;

	$.ajax({
		url: '/api/ecole?' + $.param(filtres)
		, dataType: 'json'
		, type: 'PATCH'
		, contentType: 'application/json; charset=utf-8'
		, data: JSON.stringify(valeurs)
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}

// GET //

/**
 */
function getEcoleScolaire(pk) {
	$.ajax({
		url: '/api/ecole/' + id
		, dataType: 'json'
		, type: 'GET'
		, contentType: 'application/json; charset=utf-8'
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}

// DELETE //

/**
 */
function deleteEcoleScolaire(pk) {
	$.ajax({
		url: '/api/ecole/' + id
		, dataType: 'json'
		, type: 'DELETE'
		, contentType: 'application/json; charset=utf-8'
		, data: JSON.stringify(valeurs)
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}
