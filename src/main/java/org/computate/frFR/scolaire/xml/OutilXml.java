package org.computate.frFR.scolaire.xml;

public class OutilXml {
	
	public static String echapperShQuotXml(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("\"", "\"'\"'\"").replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;");
		}
		
		return result;
	}
	
	public static String echapperShAposXml(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("'", "'\"'\"'").replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;");
		}
		
		return result;
	}
	
	public static String echapperShApos(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("'", "'\"'\"'");
		}
		
		return result;
	}
	
	public static String echapper(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;");
		}
		
		return result;
	}
	
	public static String echapperDansApostrophes(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("\n", " ").replace("\t", " ").replace("\r", "").replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;").replace("'", "&apos;");
		}
		
		return result;
	}
	
	public static String echapperDansCitatations(String str) {
		String result = str;
		
		if(str != null) { 
			result = result.replace("\n", " ").replace("\t", " ").replace("\r", "").replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;").replace("\"", "&quot;");
		}
		
		return result;
	}
	
	public static String echapperAjouterCitations(String str) {
		String result = str;
		
		if(str != null) { 
			result = "\"" + result.replace("\n", " ").replace("\t", " ").replace("\r", "").replace("&", "&amp;").replace("<", "&#60;").replace(">", "&#62;").replace("\"", "&quot;") + "\"";
		}
		
		return result;
	}
}
