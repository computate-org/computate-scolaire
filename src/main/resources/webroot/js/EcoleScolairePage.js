
// Recherche //

/**
 */
function rechercheEcoleScolaire($formulaireFiltres) {
	var filtres = {};

	var filtreEcoleNom = $formulaireFiltres.find('.valeurEcoleNom').val();
	if(filtreEcoleNom)
		filtres['ecoleNom'] = filtreEcoleNom;

	var filtreEcoleNumeroTelephone = $formulaireFiltres.find('.valeurEcoleNumeroTelephone').val();
	if(filtreEcoleNumeroTelephone)
		filtres['ecoleNumeroTelephone'] = filtreEcoleNumeroTelephone;

	var filtreEcoleAddresse = $formulaireFiltres.find('.valeurEcoleAddresse').val();
	if(filtreEcoleAddresse)
		filtres['ecoleAddresse'] = filtreEcoleAddresse;

	var filtreEcoleAdministrateurNom = $formulaireFiltres.find('.valeurEcoleAdministrateurNom').val();
	if(filtreEcoleAdministrateurNom)
		filtres['ecoleAdministrateurNom'] = filtreEcoleAdministrateurNom;

	var filtreEcoleCle = $formulaireFiltres.find('.valeurEcoleCle').val();
	if(filtreEcoleCle)
		filtres['ecoleCle'] = filtreEcoleCle;
	$.ajax({
		url: '/api/v1/ecole?' + $.param(filtres)
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

	var valeurEcoleNom = $formulaireValeurs.find('.valeurEcoleNom').val();
	if(valeurEcoleNom)
		valeurs['ecoleNom'] = valeurEcoleNom;

	var valeurEcoleNumeroTelephone = $formulaireValeurs.find('.valeurEcoleNumeroTelephone').val();
	if(valeurEcoleNumeroTelephone)
		valeurs['ecoleNumeroTelephone'] = valeurEcoleNumeroTelephone;

	var valeurEcoleAddresse = $formulaireValeurs.find('.valeurEcoleAddresse').val();
	if(valeurEcoleAddresse)
		valeurs['ecoleAddresse'] = valeurEcoleAddresse;

	var valeurEcoleAdministrateurNom = $formulaireValeurs.find('.valeurEcoleAdministrateurNom').val();
	if(valeurEcoleAdministrateurNom)
		valeurs['ecoleAdministrateurNom'] = valeurEcoleAdministrateurNom;

	var valeurEcoleCle = $formulaireValeurs.find('.valeurEcoleCle').val();
	if(valeurEcoleCle)
		valeurs['ecoleCle'] = valeurEcoleCle;

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

	var filtreEcoleNom = $formulaireFiltres.find('.valeurEcoleNom').val();
	if(filtreEcoleNom)
		filtres['ecoleNom'] = filtreEcoleNom;

	var filtreEcoleNumeroTelephone = $formulaireFiltres.find('.valeurEcoleNumeroTelephone').val();
	if(filtreEcoleNumeroTelephone)
		filtres['ecoleNumeroTelephone'] = filtreEcoleNumeroTelephone;

	var filtreEcoleAddresse = $formulaireFiltres.find('.valeurEcoleAddresse').val();
	if(filtreEcoleAddresse)
		filtres['ecoleAddresse'] = filtreEcoleAddresse;

	var filtreEcoleAdministrateurNom = $formulaireFiltres.find('.valeurEcoleAdministrateurNom').val();
	if(filtreEcoleAdministrateurNom)
		filtres['ecoleAdministrateurNom'] = filtreEcoleAdministrateurNom;

	var filtreEcoleCle = $formulaireFiltres.find('.valeurEcoleCle').val();
	if(filtreEcoleCle)
		filtres['ecoleCle'] = filtreEcoleCle;

	var valeurs = {};

	var setEcoleNom = $formulaireValeurs.find('.setEcoleNom').val();
	if(setEcoleNom)
		valeurs['setEcoleNom'] = setEcoleNom;
	var addEcoleNom = $formulaireValeurs.find('.addEcoleNom').val();
	if(addEcoleNom)
		valeurs['addEcoleNom'] = addEcoleNom;
	var removeEcoleNom = $formulaireValeurs.find('.removeEcoleNom').val();
	if(removeEcoleNom)
		valeurs['removeEcoleNom'] = removeEcoleNom;

	var setEcoleNumeroTelephone = $formulaireValeurs.find('.setEcoleNumeroTelephone').val();
	if(setEcoleNumeroTelephone)
		valeurs['setEcoleNumeroTelephone'] = setEcoleNumeroTelephone;
	var addEcoleNumeroTelephone = $formulaireValeurs.find('.addEcoleNumeroTelephone').val();
	if(addEcoleNumeroTelephone)
		valeurs['addEcoleNumeroTelephone'] = addEcoleNumeroTelephone;
	var removeEcoleNumeroTelephone = $formulaireValeurs.find('.removeEcoleNumeroTelephone').val();
	if(removeEcoleNumeroTelephone)
		valeurs['removeEcoleNumeroTelephone'] = removeEcoleNumeroTelephone;

	var setEcoleAddresse = $formulaireValeurs.find('.setEcoleAddresse').val();
	if(setEcoleAddresse)
		valeurs['setEcoleAddresse'] = setEcoleAddresse;
	var addEcoleAddresse = $formulaireValeurs.find('.addEcoleAddresse').val();
	if(addEcoleAddresse)
		valeurs['addEcoleAddresse'] = addEcoleAddresse;
	var removeEcoleAddresse = $formulaireValeurs.find('.removeEcoleAddresse').val();
	if(removeEcoleAddresse)
		valeurs['removeEcoleAddresse'] = removeEcoleAddresse;

	var setEcoleAdministrateurNom = $formulaireValeurs.find('.setEcoleAdministrateurNom').val();
	if(setEcoleAdministrateurNom)
		valeurs['setEcoleAdministrateurNom'] = setEcoleAdministrateurNom;
	var addEcoleAdministrateurNom = $formulaireValeurs.find('.addEcoleAdministrateurNom').val();
	if(addEcoleAdministrateurNom)
		valeurs['addEcoleAdministrateurNom'] = addEcoleAdministrateurNom;
	var removeEcoleAdministrateurNom = $formulaireValeurs.find('.removeEcoleAdministrateurNom').val();
	if(removeEcoleAdministrateurNom)
		valeurs['removeEcoleAdministrateurNom'] = removeEcoleAdministrateurNom;

	var setEcoleCle = $formulaireValeurs.find('.setEcoleCle').val();
	if(setEcoleCle)
		valeurs['setEcoleCle'] = setEcoleCle;
	var addEcoleCle = $formulaireValeurs.find('.addEcoleCle').val();
	if(addEcoleCle)
		valeurs['addEcoleCle'] = addEcoleCle;
	var removeEcoleCle = $formulaireValeurs.find('.removeEcoleCle').val();
	if(removeEcoleCle)
		valeurs['removeEcoleCle'] = removeEcoleCle;

	$.ajax({
		url: '/api/v1/ecole?' + $.param(filtres)
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
		url: '/api/v1/ecole/{id}
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
		url: '/api/v1/ecole/{id}
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
