
// Recherche //

/**
 */
function rechercheEcoleScolaire() {
	$.ajax({
		url: '/api/v1/ecole' + (!params || params.length == 0 ? '' : '?' + params.join('&'))
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

	var valeurPk = $formulaireValeurs.find('.valeurPk').val();
	if(valeurPk)
		valeurs['pk'] = valeurPk;

	var valeurSupprime = $formulaireValeurs.find('.valeurSupprime').prop('checked');
	if(valeurSupprime)
		valeurs['supprime'] = valeurSupprime;

	var valeurCree = $formulaireValeurs.find('.valeurCree').val();
	if(valeurCree)
		valeurs['cree'] = valeurCree;

	var valeurModifie = $formulaireValeurs.find('.valeurModifie').val();
	if(valeurModifie)
		valeurs['modifie'] = valeurModifie;

	var valeurEcoleCle = $formulaireValeurs.find('.valeurEcoleCle').val();
	if(valeurEcoleCle)
		valeurs['ecoleCle'] = valeurEcoleCle;

	var valeurEnfantCles = $formulaireValeurs.find('.valeurEnfantCles').val();
	if(valeurEnfantCles)
		valeurs['enfantCles'] = valeurEnfantCles;

	var valeurBlocCles = $formulaireValeurs.find('.valeurBlocCles').val();
	if(valeurBlocCles)
		valeurs['blocCles'] = valeurBlocCles;

	var valeurGroupeAgeCles = $formulaireValeurs.find('.valeurGroupeAgeCles').val();
	if(valeurGroupeAgeCles)
		valeurs['groupeAgeCles'] = valeurGroupeAgeCles;

	var valeurSessionCles = $formulaireValeurs.find('.valeurSessionCles').val();
	if(valeurSessionCles)
		valeurs['sessionCles'] = valeurSessionCles;

	var valeurSaisonCles = $formulaireValeurs.find('.valeurSaisonCles').val();
	if(valeurSaisonCles)
		valeurs['saisonCles'] = valeurSaisonCles;

	var valeurAnneeCles = $formulaireValeurs.find('.valeurAnneeCles').val();
	if(valeurAnneeCles)
		valeurs['anneeCles'] = valeurAnneeCles;

	var valeurSupprime = $formulaireValeurs.find('.valeurSupprime').prop('checked');
	if(valeurSupprime)
		valeurs['supprime'] = valeurSupprime;

	var valeurArchive = $formulaireValeurs.find('.valeurArchive').prop('checked');
	if(valeurArchive)
		valeurs['archive'] = valeurArchive;

	var valeurScolaireTri = $formulaireValeurs.find('.valeurScolaireTri').val();
	if(valeurScolaireTri)
		valeurs['scolaireTri'] = valeurScolaireTri;

	var valeurEcoleTri = $formulaireValeurs.find('.valeurEcoleTri').val();
	if(valeurEcoleTri)
		valeurs['ecoleTri'] = valeurEcoleTri;

	var valeurObjetSuggerePoids = $formulaireValeurs.find('.valeurObjetSuggerePoids').val();
	if(valeurObjetSuggerePoids)
		valeurs['objetSuggerePoids'] = valeurObjetSuggerePoids;

	var valeurObjetSuggere = $formulaireValeurs.find('.valeurObjetSuggere').val();
	if(valeurObjetSuggere)
		valeurs['objetSuggere'] = valeurObjetSuggere;

	var valeurEcoleNomCourt = $formulaireValeurs.find('.valeurEcoleNomCourt').val();
	if(valeurEcoleNomCourt)
		valeurs['ecoleNomCourt'] = valeurEcoleNomCourt;

	$.ajax({
		url: '/api/v1/ecole'
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
	var filtres = {};

	var filtrePk = $formulaireFiltres.find('.valeurPk').val();
	if(filtrePk)
		filtres['pk'] = valeurPk;

	var filtreSupprime = $formulaireFiltres.find('.valeurSupprime').prop('checked');
	if(filtreSupprime)
		filtres['supprime'] = valeurSupprime;

	var filtreCree = $formulaireFiltres.find('.valeurCree').val();
	if(filtreCree)
		filtres['cree'] = valeurCree;

	var filtreModifie = $formulaireFiltres.find('.valeurModifie').val();
	if(filtreModifie)
		filtres['modifie'] = valeurModifie;

	var filtreEcoleCle = $formulaireFiltres.find('.valeurEcoleCle').val();
	if(filtreEcoleCle)
		filtres['ecoleCle'] = valeurEcoleCle;

	var filtreEnfantCles = $formulaireFiltres.find('.valeurEnfantCles').val();
	if(filtreEnfantCles)
		filtres['enfantCles'] = valeurEnfantCles;

	var filtreBlocCles = $formulaireFiltres.find('.valeurBlocCles').val();
	if(filtreBlocCles)
		filtres['blocCles'] = valeurBlocCles;

	var filtreGroupeAgeCles = $formulaireFiltres.find('.valeurGroupeAgeCles').val();
	if(filtreGroupeAgeCles)
		filtres['groupeAgeCles'] = valeurGroupeAgeCles;

	var filtreSessionCles = $formulaireFiltres.find('.valeurSessionCles').val();
	if(filtreSessionCles)
		filtres['sessionCles'] = valeurSessionCles;

	var filtreSaisonCles = $formulaireFiltres.find('.valeurSaisonCles').val();
	if(filtreSaisonCles)
		filtres['saisonCles'] = valeurSaisonCles;

	var filtreAnneeCles = $formulaireFiltres.find('.valeurAnneeCles').val();
	if(filtreAnneeCles)
		filtres['anneeCles'] = valeurAnneeCles;

	var filtreSupprime = $formulaireFiltres.find('.valeurSupprime').prop('checked');
	if(filtreSupprime)
		filtres['supprime'] = valeurSupprime;

	var filtreArchive = $formulaireFiltres.find('.valeurArchive').prop('checked');
	if(filtreArchive)
		filtres['archive'] = valeurArchive;

	var filtreScolaireTri = $formulaireFiltres.find('.valeurScolaireTri').val();
	if(filtreScolaireTri)
		filtres['scolaireTri'] = valeurScolaireTri;

	var filtreEcoleTri = $formulaireFiltres.find('.valeurEcoleTri').val();
	if(filtreEcoleTri)
		filtres['ecoleTri'] = valeurEcoleTri;

	var filtreObjetSuggerePoids = $formulaireFiltres.find('.valeurObjetSuggerePoids').val();
	if(filtreObjetSuggerePoids)
		filtres['objetSuggerePoids'] = valeurObjetSuggerePoids;

	var filtreObjetSuggere = $formulaireFiltres.find('.valeurObjetSuggere').val();
	if(filtreObjetSuggere)
		filtres['objetSuggere'] = valeurObjetSuggere;

	var filtreEcoleNomCourt = $formulaireFiltres.find('.valeurEcoleNomCourt').val();
	if(filtreEcoleNomCourt)
		filtres['ecoleNomCourt'] = valeurEcoleNomCourt;

	var patchs = {};

	var setPk = $formulaireValeurs.find('.setPk').val();
	if(setPk)
		patchs['setPk'] = setPk;
	var addPk = $formulaireValeurs.find('.addPk').val();
	if(addPk)
		patchs['addPk'] = addPk;
	var removePk = $formulaireValeurs.find('.removePk').val();
	if(removePk)
		patchs['removePk'] = removePk;

	var setSupprime = $formulaireValeurs.find('.setSupprime').prop('checked');
	if(setSupprime)
		patchs['setSupprime'] = setSupprime;
	var addSupprime = $formulaireValeurs.find('.addSupprime').prop('checked');
	if(addSupprime)
		patchs['addSupprime'] = addSupprime;
	var removeSupprime = $formulaireValeurs.find('.removeSupprime').prop('checked');
	if(removeSupprime)
		patchs['removeSupprime'] = removeSupprime;

	var setCree = $formulaireValeurs.find('.setCree').val();
	if(setCree)
		patchs['setCree'] = setCree;
	var addCree = $formulaireValeurs.find('.addCree').val();
	if(addCree)
		patchs['addCree'] = addCree;
	var removeCree = $formulaireValeurs.find('.removeCree').val();
	if(removeCree)
		patchs['removeCree'] = removeCree;

	var setModifie = $formulaireValeurs.find('.setModifie').val();
	if(setModifie)
		patchs['setModifie'] = setModifie;
	var addModifie = $formulaireValeurs.find('.addModifie').val();
	if(addModifie)
		patchs['addModifie'] = addModifie;
	var removeModifie = $formulaireValeurs.find('.removeModifie').val();
	if(removeModifie)
		patchs['removeModifie'] = removeModifie;

	var setEcoleCle = $formulaireValeurs.find('.setEcoleCle').val();
	if(setEcoleCle)
		patchs['setEcoleCle'] = setEcoleCle;
	var addEcoleCle = $formulaireValeurs.find('.addEcoleCle').val();
	if(addEcoleCle)
		patchs['addEcoleCle'] = addEcoleCle;
	var removeEcoleCle = $formulaireValeurs.find('.removeEcoleCle').val();
	if(removeEcoleCle)
		patchs['removeEcoleCle'] = removeEcoleCle;

	var setEnfantCles = $formulaireValeurs.find('.setEnfantCles').val();
	if(setEnfantCles)
		patchs['setEnfantCles'] = setEnfantCles;
	var addEnfantCles = $formulaireValeurs.find('.addEnfantCles').val();
	if(addEnfantCles)
		patchs['addEnfantCles'] = addEnfantCles;
	var removeEnfantCles = $formulaireValeurs.find('.removeEnfantCles').val();
	if(removeEnfantCles)
		patchs['removeEnfantCles'] = removeEnfantCles;

	var setBlocCles = $formulaireValeurs.find('.setBlocCles').val();
	if(setBlocCles)
		patchs['setBlocCles'] = setBlocCles;
	var addBlocCles = $formulaireValeurs.find('.addBlocCles').val();
	if(addBlocCles)
		patchs['addBlocCles'] = addBlocCles;
	var removeBlocCles = $formulaireValeurs.find('.removeBlocCles').val();
	if(removeBlocCles)
		patchs['removeBlocCles'] = removeBlocCles;

	var setGroupeAgeCles = $formulaireValeurs.find('.setGroupeAgeCles').val();
	if(setGroupeAgeCles)
		patchs['setGroupeAgeCles'] = setGroupeAgeCles;
	var addGroupeAgeCles = $formulaireValeurs.find('.addGroupeAgeCles').val();
	if(addGroupeAgeCles)
		patchs['addGroupeAgeCles'] = addGroupeAgeCles;
	var removeGroupeAgeCles = $formulaireValeurs.find('.removeGroupeAgeCles').val();
	if(removeGroupeAgeCles)
		patchs['removeGroupeAgeCles'] = removeGroupeAgeCles;

	var setSessionCles = $formulaireValeurs.find('.setSessionCles').val();
	if(setSessionCles)
		patchs['setSessionCles'] = setSessionCles;
	var addSessionCles = $formulaireValeurs.find('.addSessionCles').val();
	if(addSessionCles)
		patchs['addSessionCles'] = addSessionCles;
	var removeSessionCles = $formulaireValeurs.find('.removeSessionCles').val();
	if(removeSessionCles)
		patchs['removeSessionCles'] = removeSessionCles;

	var setSaisonCles = $formulaireValeurs.find('.setSaisonCles').val();
	if(setSaisonCles)
		patchs['setSaisonCles'] = setSaisonCles;
	var addSaisonCles = $formulaireValeurs.find('.addSaisonCles').val();
	if(addSaisonCles)
		patchs['addSaisonCles'] = addSaisonCles;
	var removeSaisonCles = $formulaireValeurs.find('.removeSaisonCles').val();
	if(removeSaisonCles)
		patchs['removeSaisonCles'] = removeSaisonCles;

	var setAnneeCles = $formulaireValeurs.find('.setAnneeCles').val();
	if(setAnneeCles)
		patchs['setAnneeCles'] = setAnneeCles;
	var addAnneeCles = $formulaireValeurs.find('.addAnneeCles').val();
	if(addAnneeCles)
		patchs['addAnneeCles'] = addAnneeCles;
	var removeAnneeCles = $formulaireValeurs.find('.removeAnneeCles').val();
	if(removeAnneeCles)
		patchs['removeAnneeCles'] = removeAnneeCles;

	var setSupprime = $formulaireValeurs.find('.setSupprime').prop('checked');
	if(setSupprime)
		patchs['setSupprime'] = setSupprime;
	var addSupprime = $formulaireValeurs.find('.addSupprime').prop('checked');
	if(addSupprime)
		patchs['addSupprime'] = addSupprime;
	var removeSupprime = $formulaireValeurs.find('.removeSupprime').prop('checked');
	if(removeSupprime)
		patchs['removeSupprime'] = removeSupprime;

	var setArchive = $formulaireValeurs.find('.setArchive').prop('checked');
	if(setArchive)
		patchs['setArchive'] = setArchive;
	var addArchive = $formulaireValeurs.find('.addArchive').prop('checked');
	if(addArchive)
		patchs['addArchive'] = addArchive;
	var removeArchive = $formulaireValeurs.find('.removeArchive').prop('checked');
	if(removeArchive)
		patchs['removeArchive'] = removeArchive;

	var setScolaireTri = $formulaireValeurs.find('.setScolaireTri').val();
	if(setScolaireTri)
		patchs['setScolaireTri'] = setScolaireTri;
	var addScolaireTri = $formulaireValeurs.find('.addScolaireTri').val();
	if(addScolaireTri)
		patchs['addScolaireTri'] = addScolaireTri;
	var removeScolaireTri = $formulaireValeurs.find('.removeScolaireTri').val();
	if(removeScolaireTri)
		patchs['removeScolaireTri'] = removeScolaireTri;

	var setEcoleTri = $formulaireValeurs.find('.setEcoleTri').val();
	if(setEcoleTri)
		patchs['setEcoleTri'] = setEcoleTri;
	var addEcoleTri = $formulaireValeurs.find('.addEcoleTri').val();
	if(addEcoleTri)
		patchs['addEcoleTri'] = addEcoleTri;
	var removeEcoleTri = $formulaireValeurs.find('.removeEcoleTri').val();
	if(removeEcoleTri)
		patchs['removeEcoleTri'] = removeEcoleTri;

	var setObjetSuggerePoids = $formulaireValeurs.find('.setObjetSuggerePoids').val();
	if(setObjetSuggerePoids)
		patchs['setObjetSuggerePoids'] = setObjetSuggerePoids;
	var addObjetSuggerePoids = $formulaireValeurs.find('.addObjetSuggerePoids').val();
	if(addObjetSuggerePoids)
		patchs['addObjetSuggerePoids'] = addObjetSuggerePoids;
	var removeObjetSuggerePoids = $formulaireValeurs.find('.removeObjetSuggerePoids').val();
	if(removeObjetSuggerePoids)
		patchs['removeObjetSuggerePoids'] = removeObjetSuggerePoids;

	var setObjetSuggere = $formulaireValeurs.find('.setObjetSuggere').val();
	if(setObjetSuggere)
		patchs['setObjetSuggere'] = setObjetSuggere;
	var addObjetSuggere = $formulaireValeurs.find('.addObjetSuggere').val();
	if(addObjetSuggere)
		patchs['addObjetSuggere'] = addObjetSuggere;
	var removeObjetSuggere = $formulaireValeurs.find('.removeObjetSuggere').val();
	if(removeObjetSuggere)
		patchs['removeObjetSuggere'] = removeObjetSuggere;

	var setEcoleNomCourt = $formulaireValeurs.find('.setEcoleNomCourt').val();
	if(setEcoleNomCourt)
		patchs['setEcoleNomCourt'] = setEcoleNomCourt;
	var addEcoleNomCourt = $formulaireValeurs.find('.addEcoleNomCourt').val();
	if(addEcoleNomCourt)
		patchs['addEcoleNomCourt'] = addEcoleNomCourt;
	var removeEcoleNomCourt = $formulaireValeurs.find('.removeEcoleNomCourt').val();
	if(removeEcoleNomCourt)
		patchs['removeEcoleNomCourt'] = removeEcoleNomCourt;

	$.ajax({
		url: '/api/v1/ecole' + (!params || params.length == 0 ? '' : '?' + params.join('&'))
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
		url: '/api/v1/ecole/' + pk
		, dataType: 'json'
		, type: 'GET'
		, contentType: 'application/json; charset=utf-8'
		, success: function( data, textStatus, jQxhr ) {
		}
		, error: function( jqXhr, textStatus, errorThrown ) {
		}
	});
}

// PUT //

/**
 */
function putEcoleScolaire(pk, $formulaireValeurs) {
	var valeurs = {};

	var valeurPk = $formulaireValeurs.find('.valeurPk').val();
	if(valeurPk)
		valeurs['pk'] = valeurPk;

	var valeurSupprime = $formulaireValeurs.find('.valeurSupprime').prop('checked');
	if(valeurSupprime)
		valeurs['supprime'] = valeurSupprime;

	var valeurCree = $formulaireValeurs.find('.valeurCree').val();
	if(valeurCree)
		valeurs['cree'] = valeurCree;

	var valeurModifie = $formulaireValeurs.find('.valeurModifie').val();
	if(valeurModifie)
		valeurs['modifie'] = valeurModifie;

	var valeurEcoleCle = $formulaireValeurs.find('.valeurEcoleCle').val();
	if(valeurEcoleCle)
		valeurs['ecoleCle'] = valeurEcoleCle;

	var valeurEnfantCles = $formulaireValeurs.find('.valeurEnfantCles').val();
	if(valeurEnfantCles)
		valeurs['enfantCles'] = valeurEnfantCles;

	var valeurBlocCles = $formulaireValeurs.find('.valeurBlocCles').val();
	if(valeurBlocCles)
		valeurs['blocCles'] = valeurBlocCles;

	var valeurGroupeAgeCles = $formulaireValeurs.find('.valeurGroupeAgeCles').val();
	if(valeurGroupeAgeCles)
		valeurs['groupeAgeCles'] = valeurGroupeAgeCles;

	var valeurSessionCles = $formulaireValeurs.find('.valeurSessionCles').val();
	if(valeurSessionCles)
		valeurs['sessionCles'] = valeurSessionCles;

	var valeurSaisonCles = $formulaireValeurs.find('.valeurSaisonCles').val();
	if(valeurSaisonCles)
		valeurs['saisonCles'] = valeurSaisonCles;

	var valeurAnneeCles = $formulaireValeurs.find('.valeurAnneeCles').val();
	if(valeurAnneeCles)
		valeurs['anneeCles'] = valeurAnneeCles;

	var valeurSupprime = $formulaireValeurs.find('.valeurSupprime').prop('checked');
	if(valeurSupprime)
		valeurs['supprime'] = valeurSupprime;

	var valeurArchive = $formulaireValeurs.find('.valeurArchive').prop('checked');
	if(valeurArchive)
		valeurs['archive'] = valeurArchive;

	var valeurScolaireTri = $formulaireValeurs.find('.valeurScolaireTri').val();
	if(valeurScolaireTri)
		valeurs['scolaireTri'] = valeurScolaireTri;

	var valeurEcoleTri = $formulaireValeurs.find('.valeurEcoleTri').val();
	if(valeurEcoleTri)
		valeurs['ecoleTri'] = valeurEcoleTri;

	var valeurObjetSuggerePoids = $formulaireValeurs.find('.valeurObjetSuggerePoids').val();
	if(valeurObjetSuggerePoids)
		valeurs['objetSuggerePoids'] = valeurObjetSuggerePoids;

	var valeurObjetSuggere = $formulaireValeurs.find('.valeurObjetSuggere').val();
	if(valeurObjetSuggere)
		valeurs['objetSuggere'] = valeurObjetSuggere;

	var valeurEcoleNomCourt = $formulaireValeurs.find('.valeurEcoleNomCourt').val();
	if(valeurEcoleNomCourt)
		valeurs['ecoleNomCourt'] = valeurEcoleNomCourt;

	$.ajax({
		url: '/api/v1/ecole/' + pk
		, dataType: 'json'
		, type: 'PUT'
		, contentType: 'application/json; charset=utf-8'
		, data: JSON.stringify(valeurs)
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
		url: '/api/v1/ecole/' + pk
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
