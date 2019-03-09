
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
function patchUtilisateurSite($formulaireFiltres, $formulaireValeurs) {
	var filtres = [];

	var filtreVoirArchive = $formulaireFiltres.find('.valeurVoirArchive').prop('checked');
	if(filtreVoirArchive != null && filtreVoirArchive !== '')
		filtres.push({ name: 'fq', value: 'voirArchive:' + filtreVoirArchive });

	var filtreVoirSupprime = $formulaireFiltres.find('.valeurVoirSupprime').prop('checked');
	if(filtreVoirSupprime != null && filtreVoirSupprime !== '')
		filtres.push({ name: 'fq', value: 'voirSupprime:' + filtreVoirSupprime });

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
	if(filtreUtilisateurRecevoirCourriels != null && filtreUtilisateurRecevoirCourriels !== '')
		filtres.push({ name: 'fq', value: 'utilisateurRecevoirCourriels:' + filtreUtilisateurRecevoirCourriels });

	var valeurs = {};

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
		url: '/api/utilisateur?' + $.param(filtres)
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
