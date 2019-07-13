
// PATCH //

/**
 * Modifier un ou plusiers utilisateurs sans valuers qui change, 
 * ou changer des valeurs pour un ou plusiers l'utilisateur. 
 * @param params: [ "q=*:*", "fq=pk:1", "sort=pk asc", "rows=1", "fl=pk" ]
 *        Une liste des opérations de recherche sur des utilisateurs 
 *        pour rechercher "q=*:*", filtrer "fq=pk:1", trier "sort=pk desc", 
 *        limiter les résultats "rows=1", ou limiter les valeurs "fl=pk". 
 * @param valeurs Noms des champs et valeurs à changer selon les filtres fq. 
 *           Example: { pk: 1 }
 */
function ($formulaireFiltres, $formulaireValeurs) {
	var filtres = [];

	var filtreUtilisateurId = $formulaireFiltres.find('.valeurUtilisateurId').val();
	if(filtreUtilisateurId != null && filtreUtilisateurId !== '')
		filtres.push({ name: 'fq', value: 'utilisateurId:' + filtreUtilisateurId });

	var filtreVoirArchive = $formulaireFiltres.find('.valeurVoirArchive').prop('checked');
	if(filtreVoirArchive != null && filtreVoirArchive === true)
		filtres.push({ name: 'fq', value: 'voirArchive:' + filtreVoirArchive });

	var filtreVoirSupprime = $formulaireFiltres.find('.valeurVoirSupprime').prop('checked');
	if(filtreVoirSupprime != null && filtreVoirSupprime === true)
		filtres.push({ name: 'fq', value: 'voirSupprime:' + filtreVoirSupprime });

	var filtreCalculInrPks = $formulaireFiltres.find('.valeurCalculInrPks').val();
	if(filtreCalculInrPks != null && filtreCalculInrPks !== '')
		filtres.push({ name: 'fq', value: 'calculInrPks:' + filtreCalculInrPks });

	var filtreUtilisateurNom = $formulaireFiltres.find('.valeurUtilisateurNom').val();
	if(filtreUtilisateurNom != null && filtreUtilisateurNom !== '')
		filtres.push({ name: 'fq', value: 'utilisateurNom:' + filtreUtilisateurNom });

	var filtreUtilisateurMail = $formulaireFiltres.find('.valeurUtilisateurMail').val();
	if(filtreUtilisateurMail != null && filtreUtilisateurMail !== '')
		filtres.push({ name: 'fq', value: 'utilisateurMail:' + filtreUtilisateurMail });

	var filtreUtilisateurPrenom = $formulaireFiltres.find('.valeurUtilisateurPrenom').val();
	if(filtreUtilisateurPrenom != null && filtreUtilisateurPrenom !== '')
		filtres.push({ name: 'fq', value: 'utilisateurPrenom:' + filtreUtilisateurPrenom });

	var filtreUtilisateurNomFamille = $formulaireFiltres.find('.valeurUtilisateurNomFamille').val();
	if(filtreUtilisateurNomFamille != null && filtreUtilisateurNomFamille !== '')
		filtres.push({ name: 'fq', value: 'utilisateurNomFamille:' + filtreUtilisateurNomFamille });

	var filtreUtilisateurNomComplet = $formulaireFiltres.find('.valeurUtilisateurNomComplet').val();
	if(filtreUtilisateurNomComplet != null && filtreUtilisateurNomComplet !== '')
		filtres.push({ name: 'fq', value: 'utilisateurNomComplet:' + filtreUtilisateurNomComplet });

	var filtreUtilisateurSite = $formulaireFiltres.find('.valeurUtilisateurSite').val();
	if(filtreUtilisateurSite != null && filtreUtilisateurSite !== '')
		filtres.push({ name: 'fq', value: 'utilisateurSite:' + filtreUtilisateurSite });

	var filtreUtilisateurRecevoirCourriels = $formulaireFiltres.find('.valeurUtilisateurRecevoirCourriels').prop('checked');
	if(filtreUtilisateurRecevoirCourriels != null && filtreUtilisateurRecevoirCourriels === true)
		filtres.push({ name: 'fq', value: 'utilisateurRecevoirCourriels:' + filtreUtilisateurRecevoirCourriels });

	var valeurs = {};

	var setUtilisateurId = $formulaireValeurs.find('.setUtilisateurId').val();
	if(setUtilisateurId != null && setUtilisateurId !== '')
		valeurs['setUtilisateurId'] = setUtilisateurId;
	var addUtilisateurId = $formulaireValeurs.find('.addUtilisateurId').val();
	if(addUtilisateurId != null && addUtilisateurId !== '')
		valeurs['addUtilisateurId'] = addUtilisateurId;
	var removeUtilisateurId = $formulaireValeurs.find('.removeUtilisateurId').val();
	if(removeUtilisateurId != null && removeUtilisateurId !== '')
		valeurs['removeUtilisateurId'] = removeUtilisateurId;

	var setVoirArchive = $formulaireValeurs.find('.setVoirArchive').prop('checked');
	if(setVoirArchive != null && setVoirArchive !== '')
		valeurs['setVoirArchive'] = setVoirArchive;
	var addVoirArchive = $formulaireValeurs.find('.addVoirArchive').prop('checked');
	if(addVoirArchive != null && addVoirArchive !== '')
		valeurs['addVoirArchive'] = addVoirArchive;
	var removeVoirArchive = $formulaireValeurs.find('.removeVoirArchive').prop('checked');
	if(removeVoirArchive != null && removeVoirArchive !== '')
		valeurs['removeVoirArchive'] = removeVoirArchive;

	var setVoirSupprime = $formulaireValeurs.find('.setVoirSupprime').prop('checked');
	if(setVoirSupprime != null && setVoirSupprime !== '')
		valeurs['setVoirSupprime'] = setVoirSupprime;
	var addVoirSupprime = $formulaireValeurs.find('.addVoirSupprime').prop('checked');
	if(addVoirSupprime != null && addVoirSupprime !== '')
		valeurs['addVoirSupprime'] = addVoirSupprime;
	var removeVoirSupprime = $formulaireValeurs.find('.removeVoirSupprime').prop('checked');
	if(removeVoirSupprime != null && removeVoirSupprime !== '')
		valeurs['removeVoirSupprime'] = removeVoirSupprime;

	$.ajax({
		url: '?' + $.param(filtres)
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
