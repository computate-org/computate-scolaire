package org.computate.frFR.scolaire.chaine;    

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.page.parti.Icone;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**
 * NomCanonique.enUS: org.computate.enUS.school.chain.Chain
 */
public class Chaine extends ChaineGen<Object> {  

	protected void _requeteSite_(Couverture<RequeteSite> c) {}

	protected void _police(Couverture<String> c) {}

	protected void _alignerCentre(Couverture<Boolean> c) {
		c.o(false);
	}
	protected void _alignerGauche(Couverture<Boolean> c) {
		c.o(false);
	}
	protected void _alignerDroit(Couverture<Boolean> c) {
		c.o(false);
	}

	protected void _iconesAvant(ArrayList<Icone> l) {}
	protected void _iconesApres(ArrayList<Icone> l) {}
	protected void _xDecalage(Couverture<Double> c) {
		c.o(0D);
	}
	protected void _yDecalage(Couverture<Double> c) {
		c.o(0D);
	}
	protected void _hLigne(Couverture<Double> c) {}
	protected void _hEspace(Couverture<Double> c) {}
//
//	public Chaine iconeAvant(MiseEnPage page, String type, String nom) {
//		Icone o = new Icone();
//		o.type.tout(type);
//		o.nom.tout(nom);
//		o.setPage_(page);
//		o.requeteSitePourClasse(page.requeteSite_);
//		o.initLoinIcone(page.requeteSite_);
////		iconesAvantAjouter(o);
//		return this;
//	}
//	public Chaine iconeApres(MiseEnPage page, String type, String nom) {
//		Icone o = new Icone();
//		o.type.tout(type);
//		o.nom.tout(nom);
//		o.setPage_(page);
//		o.requeteSitePourClasse(page.requeteSite_);
//		o.initLoinIcone(page.requeteSite_);
////		iconesApresAjouter(o);
//		return this;
//	}

	protected void _tout(ArrayList<Object> l) {}

	public String s() {
		String o = "";
			o = StringUtils.join(tout.toArray());
		return o;
	}

	public String toString(String langue) {
		String o = StringUtils.join(tout.toArray());
		return o;
	}

	@Override public String toString() { 
		StringBuilder o = new StringBuilder();
		if(requeteSite_ != null) {
			for(Object p : tout) {
				if(p != null && !(p instanceof Chaine))
					o.append(p.toString());
			}
		}
		else {
			for(Object p : tout) {
				if(p != null)
					o.append(p.toString());
			}
		}
		return o.toString();
	}

	public Chaine t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}
	public Chaine tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	public Chaine l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	public Chaine s(Object...objets) { 
		for(Object objet : objets) {
			if(objet != null) {
				if(objet instanceof Chaine) {
					Chaine chaine = (Chaine)objet;
					for(Object objet2 : chaine.tout) {
						if(objet2 != null && !StringUtils.isEmpty(objet2.toString()))
							tout.add(objet2);
					}
				}
				else if(objet instanceof List) {
					List<?> chaine = (List<?>)objet;
					for(Object objet2 : chaine) {
						if(objet2 != null && !StringUtils.isEmpty(objet2.toString()))
							tout.add(objet2);
					}
				}
				else {
					if(!StringUtils.isEmpty(objet.toString()))
						tout.add(objet);
				}
			}
		}
		return this;
	}
	
	public boolean estVide() {
		Boolean résultat = tout.size() == 0 && tout.size() == 0;
		return résultat;
	}
	
	public boolean pasVide() {
		Boolean résultat = !(tout.size() == 0 && tout.size() == 0);
		return résultat;
	}

	public String minuscules() {
		String résultat = StringUtils.lowerCase(toString());
		return résultat;
	}

	public String majuscules() {
		String résultat = StringUtils.upperCase(toString());
		return résultat;
	}

	public String capitaliser() {
		String résultat = StringUtils.capitalize(toString());
		return résultat;
	}

	public String noncapitaliser() {
		String résultat = StringUtils.uncapitalize(toString());
		return résultat;
	}

	public String sousChaineApres(String separator) {
		String résultat = StringUtils.substringAfter(toString(), separator);
		return résultat;
	}

	public String sousChaineApres(Chaine separator) {
		String résultat = StringUtils.substringAfter(toString(), separator.toString());
		return résultat;
	}

	public String sousChaineApresDernier(String separator) {
		String résultat = StringUtils.substringAfterLast(toString(), separator);
		return résultat;
	}

	public String sousChaineApresDernier(Chaine separator) {
		String résultat = StringUtils.substringAfterLast(toString(), separator.toString());
		return résultat;
	}

	public String sousChaineAvant(String separator) {
		String résultat = StringUtils.substringBefore(toString(), separator);
		return résultat;
	}

	public String sousChaineAvant(Chaine separator) {
		String résultat = StringUtils.substringBefore(toString(), separator.toString());
		return résultat;
	}

	public String sousChaineAvantDernier(String separator) {
		String résultat = StringUtils.substringBeforeLast(toString(), separator);
		return résultat;
	}

	public String sousChaineAvantDernier(Chaine separator) {
		String résultat = StringUtils.substringBeforeLast(toString(), separator.toString());
		return résultat;
	}

	public void sousChaineAvant(Chaine o, String separator) {
		String s = StringUtils.substringBefore(o.s(), separator);
		s(s);
	}

	public void sousChaineAvantDernier(Chaine o, String separator) {
		String s = StringUtils.substringBeforeLast(o.s(), separator);
		s(s);
	}

	public void sousChaineApres(Chaine o, String separator) {
		String s = StringUtils.substringAfter(o.s(), separator);
		s(s);
	}

	public void sousChaineApresDernier(Chaine o, String separator) {
		String s = StringUtils.substringAfterLast(o.s(), separator);
		s(s);
	}
	
	public String[] diviserParCaractèreTypeChameau() {
		String résultat[] = StringUtils.splitByCharacterTypeCamelCase(toString());
		return résultat;
	}
	
	public String couper() {
		String résultat = StringUtils.trim(toString());
		return résultat;
	}

	public boolean contains(String s) {
		boolean résultat = StringUtils.contains(toString(), s);
		return résultat;
	}

	public boolean contains(Chaine s) {
		boolean résultat = StringUtils.contains(toString(), s.toString());
		return résultat;
	}

	public boolean contient(String s) {
		boolean résultat = StringUtils.contains(toString(), s);
		return résultat;
	}

	public boolean contient(Chaine s) {
		boolean résultat = StringUtils.contains(toString(), s.toString());
		return résultat;
	}

	public boolean contientPas(String s) {
		boolean résultat = !StringUtils.contains(toString(), s);
		return résultat;
	}

	public boolean contientPas(Chaine s) {
		boolean résultat = !StringUtils.contains(toString(), s.toString());
		return résultat;
	}

	public boolean égal(String s) {
		boolean résultat = StringUtils.equals(toString(), s);
		return résultat;
	}

	public boolean égal(Chaine s) {
		boolean résultat = StringUtils.equals(toString(), s.toString());
		return résultat;
	}

	public boolean pasÉgal(String s) {
		boolean résultat = !StringUtils.equals(toString(), s);
		return résultat;
	}

	public boolean pasÉgal(Chaine s) {
		boolean résultat = !StringUtils.equals(toString(), s.toString());
		return résultat;
	}

	public boolean terminePar(String s) {
		boolean résultat = StringUtils.endsWith(toString(), s);
		return résultat;
	}

	public boolean terminePar(Chaine s) {
		boolean résultat = StringUtils.endsWith(toString(), s.toString());
		return résultat;
	}

	public boolean terminePasPar(String s) {
		boolean résultat = !StringUtils.endsWith(toString(), s);
		return résultat;
	}

	public boolean terminePasPar(Chaine s) {
		boolean résultat = !StringUtils.endsWith(toString(), s.toString());
		return résultat;
	}

	public boolean commencePar(String s) {
		boolean résultat = StringUtils.startsWith(toString(), s);
		return résultat;
	}

	public boolean commencePar(Chaine s) {
		boolean résultat = StringUtils.startsWith(toString(), s.toString());
		return résultat;
	}

	public boolean commencePasPar(String s) {
		boolean résultat = !StringUtils.startsWith(toString(), s);
		return résultat;
	}

	public boolean commencePasPar(Chaine s) {
		boolean résultat = !StringUtils.startsWith(toString(), s.toString());
		return résultat;
	}
	
	public Chaine vider() {

		this.tout = new ArrayList<Object>();

		return this;
	}

	@Override public int hashCode() {
		org.apache.commons.lang3.builder.HashCodeBuilder o = new org.apache.commons.lang3.builder.HashCodeBuilder();
		o.append(tout.toString());

		return o.toHashCode();
	}

	@Override public boolean equals(Object obj) {
		if(obj instanceof Chaine) {
			final Chaine other = (Chaine)obj;
			org.apache.commons.lang3.builder.EqualsBuilder o = new org.apache.commons.lang3.builder.EqualsBuilder();
			o.append(s(), other.s());

			return o.isEquals();
		} else if(obj != null) {
			Boolean o = toString().equals(obj.toString());
			return o;
		}
		else {
			return false;
		}
	}
//
//	@org.junit.Test public void genererClasseChaine() {
//		org.computate.site.SiteContexte.SiteContexte SiteContexte = new org.computate.site.SiteContexte.SiteContexte();
//		SiteContexte.initialiserLoinSiteContexte();
//
//		org.computate.site.langue.LangueSite langue = new org.computate.site.langue.LangueSite();
//		langue.identifiant("frFR");
//		langue.initialiserLoinLangueSite();
//
//		org.computate.site.requete.RequeteSite requeteSite = new org.computate.site.requete.RequeteSite();
//		requeteSite.langue(langue);
//		requeteSite.initialiserLoinRequeteSite();
//
//		org.computate.site.java.classe.generateur.GenerateurClasse generateurClasse = new org.computate.site.java.classe.generateur.GenerateurClasse();
//		generateurClasse.configCheminuration(SiteContexte.configChemin);
//		String cheminRessource = SiteContexte.configSite.appliChemin + "/src/main/java/" + getClass().getCanonicalName().replace(".", "/") + ".java";
//		generateurClasse.requeteSite(requeteSite);
//		generateurClasse.cheminRessource(cheminRessource);
//		generateurClasse.initialiserLoinGenerateurClasse(requeteSite);
//		System.out.println(generateurClasse.texteClasse.toString());
//	}

	//////////
	// html //
	//////////

	public static List<String> HTML_ELEMENTS_FERMES = Arrays.asList("area", "base", "br", "col", "command", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");
	public static List<String> HTML_ELEMENTS_NO_WRAP = Arrays.asList("script", "span", "a", "b", "i", "u", "title", "use", "h1", "h2", "h3", "h4", "h5", "h6");
//
//	public Chaine e(String nomLocal) {
//		String nomLocalParent = requeteSite_.xmlPile.isEmpty() ? null : requeteSite_.xmlPile.peek();
//
//		boolean eNoWrapParent = nomLocalParent == null || HTML_ELEMENTS_NO_WRAP.contains(nomLocalParent);
//		String tabulations = String.join("", Collections.nCopies(requeteSite_.xmlPile.size(), "\t"));
//		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.xmlPile.size(), "\\t"));
//
//		requeteSite_.xmlPile.push(nomLocal);
//		if(StringUtils.equals(nomLocal, "html"))
//			tout("<!DOCTYPE html>\n");
//		if(!eNoWrapParent && !tabulationsEchappes.isEmpty()) {
//			tout("\n");
//			tout(tabulations);
//		}
//		tout("<");
//		tout(nomLocal);
//		
//		return this;
//	}
//
//	public Chaine a(String nomAttribut, Object...objets) {
//		tout(" ");
//		tout(nomAttribut);
//		tout("=\"");
//		for(Object objet : objets) {
//			if(objet != null) {
//				String s = objet.toString();
//				tout(OutilXml.echapperDansCitatations(s));
//			}
//		}
//		tout("\"");
//		
//		return this;
//	}
//
//	public Chaine f() {
//		tout(">");
//		
//		return this;
//	}
//
//	public Chaine toutXml(Object...objets) {
//		for(Object objet : objets) {
//			if(objet != null) {
//				if(objet instanceof Chaine) {
//					Chaine chaine = (Chaine)objet;
//					String s = chaine.s();
//					tout(OutilXml.echapper(s));
//				}
//				else {
//					String s = objet.toString();
//					tout(OutilXml.echapper(s));
//				}
//			}
//		}
//		
//		return this;
//	}
//
//	public Chaine fg() {
//		tout("/>");
//		requeteSite_.xmlPile.pop();
//		
//		return this;
//	}
//
//	public Chaine g(String nomLocal) {
//		String nomLocalParent = requeteSite_.xmlPile.peek();
//
//		boolean eNoWrap = nomLocalParent == null || HTML_ELEMENTS_NO_WRAP.contains(nomLocal);
//		String tabulations = String.join("", Collections.nCopies(requeteSite_.xmlPile.size(), "\t"));
//		String tabulationsEchappes = String.join("", Collections.nCopies(requeteSite_.xmlPile.size(), "\\t"));
//
//		if(!eNoWrap && !tabulationsEchappes.isEmpty()) {
//			tout("\n");
//			tout(tabulations);
//		}
//		tout("</");
//		tout(nomLocal);
//		tout(">");
//		requeteSite_.xmlPile.pop();
//		
//		return this;
//	}
//
//	public Chaine icone(String href) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", href).f().g("use");
//		g("svg");
//		return this;
//	}
//
//	public Chaine iconeLight(String nom) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", "/sprites/light.svg#", nom).f().g("use");
//		g("svg");
//		return this;
//	}
//
//	public Chaine iconeRegular(String nom) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", "/sprites/regular.svg#", nom).f().g("use");
//		g("svg");
//		return this;
//	}
//
//	public Chaine iconeSolid(String nom) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", "/sprites/solid.svg#", nom).f().g("use");
//		g("svg");
//		return this;
//	}
//
//	public Chaine iconeBrands(String nom) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", "/sprites/brands.svg#", nom).f().g("use");
//		g("svg");
//		return this;
//	}
//
//	public Chaine iconeComputate(String nom) {
//		e("svg").a("class", "fa-icon w3-padding-4 w3-margin-right-4 ").f();
//			e("use").a("xlink:href", "/sprites/computate.svg#", nom).f().g("use");
//		g("svg");
//		return this;
//	}
}
