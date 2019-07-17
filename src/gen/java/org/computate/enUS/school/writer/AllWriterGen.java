package org.computate.enUS.school.writer;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.enUS.school.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import io.vertx.core.buffer.Buffer;
import java.lang.Boolean;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AllWriterGen<DEV> extends Object {

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteRequestEnUS siteRequest_;
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
	}
	protected AllWriter siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	////////////
	// tabStr //
	////////////

	/**	L'entité « tabStr »
	 *	 is defined as null before being initialized. 
	 */
	protected String tabStr;
	public Wrap<String> tabStrWrap = new Wrap<String>().p(this).c(String.class).var("tabStr").o(tabStr);

	/**	<br/>L'entité « tabStr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:tabStr">Trouver l'entité tabStr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabStr(Wrap<String> c);

	public String getTabStr() {
		return tabStr;
	}

	public void setTabStr(String tabStr) {
		this.tabStr = tabStr;
		this.tabStrWrap.alreadyInitialized = true;
	}
	protected AllWriter tabStrInit() {
		if(!tabStrWrap.alreadyInitialized) {
			_tabStr(tabStrWrap);
			if(tabStr == null)
				setTabStr(tabStrWrap.o);
		}
		tabStrWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	public String solrTabStr() {
		return tabStr;
	}

	public String strTabStr() {
		return tabStr == null ? "" : tabStr;
	}

	public String nomAffichageTabStr() {
		return null;
	}

	public String htmTooltipTabStr() {
		return null;
	}

	public String htmTabStr() {
		return tabStr == null ? "" : StringEscapeUtils.escapeHtml4(strTabStr());
	}

	//////////
	// file //
	//////////

	/**	L'entité « file »
	 *	 is defined as null before being initialized. 
	 */
	protected File file;
	public Wrap<File> fileWrap = new Wrap<File>().p(this).c(File.class).var("file").o(file);

	/**	<br/>L'entité « file »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:file">Trouver l'entité file dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _file(Wrap<File> c);

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		this.fileWrap.alreadyInitialized = true;
	}
	protected AllWriter fileInit() {
		if(!fileWrap.alreadyInitialized) {
			_file(fileWrap);
			if(file == null)
				setFile(fileWrap.o);
		}
		fileWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	//////////////////
	// stringWriter //
	//////////////////

	/**	L'entité « stringWriter »
	 *	 is defined as null before being initialized. 
	 */
	protected StringWriter stringWriter;
	public Wrap<StringWriter> stringWriterWrap = new Wrap<StringWriter>().p(this).c(StringWriter.class).var("stringWriter").o(stringWriter);

	/**	<br/>L'entité « stringWriter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:stringWriter">Trouver l'entité stringWriter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _stringWriter(Wrap<StringWriter> c);

	public StringWriter getStringWriter() {
		return stringWriter;
	}

	public void setStringWriter(StringWriter stringWriter) {
		this.stringWriter = stringWriter;
		this.stringWriterWrap.alreadyInitialized = true;
	}
	protected AllWriter stringWriterInit() {
		if(!stringWriterWrap.alreadyInitialized) {
			_stringWriter(stringWriterWrap);
			if(stringWriter == null)
				setStringWriter(stringWriterWrap.o);
		}
		stringWriterWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	////////////
	// buffer //
	////////////

	/**	L'entité « buffer »
	 *	 is defined as null before being initialized. 
	 */
	protected Buffer buffer;
	public Wrap<Buffer> bufferWrap = new Wrap<Buffer>().p(this).c(Buffer.class).var("buffer").o(buffer);

	/**	<br/>L'entité « buffer »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:buffer">Trouver l'entité buffer dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _buffer(Wrap<Buffer> c);

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
		this.bufferWrap.alreadyInitialized = true;
	}
	protected AllWriter bufferInit() {
		if(!bufferWrap.alreadyInitialized) {
			_buffer(bufferWrap);
			if(buffer == null)
				setBuffer(bufferWrap.o);
		}
		bufferWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	/////////////////
	// printWriter //
	/////////////////

	/**	L'entité « printWriter »
	 *	 is defined as null before being initialized. 
	 */
	protected PrintWriter printWriter;
	public Wrap<PrintWriter> printWriterWrap = new Wrap<PrintWriter>().p(this).c(PrintWriter.class).var("printWriter").o(printWriter);

	/**	<br/>L'entité « printWriter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:printWriter">Trouver l'entité printWriter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _printWriter(Wrap<PrintWriter> c);

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
		this.printWriterWrap.alreadyInitialized = true;
	}
	protected AllWriter printWriterInit() {
		if(!printWriterWrap.alreadyInitialized) {
			_printWriter(printWriterWrap);
			if(printWriter == null)
				setPrintWriter(printWriterWrap.o);
		}
		printWriterWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	///////////
	// empty //
	///////////

	/**	L'entité « empty »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean empty;
	public Wrap<Boolean> emptyWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("empty").o(empty);

	/**	<br/>L'entité « empty »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.writer.AllWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:empty">Trouver l'entité empty dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _empty(Wrap<Boolean> c);

	public Boolean getEmpty() {
		return empty;
	}

	public void setEmpty(Boolean empty) {
		this.empty = empty;
		this.emptyWrap.alreadyInitialized = true;
	}
	public AllWriter setEmpty(String o) {
		this.empty = Boolean.parseBoolean(o);
		this.emptyWrap.alreadyInitialized = true;
		return (AllWriter)this;
	}
	protected AllWriter emptyInit() {
		if(!emptyWrap.alreadyInitialized) {
			_empty(emptyWrap);
			if(empty == null)
				setEmpty(emptyWrap.o);
		}
		emptyWrap.alreadyInitialized(true);
		return (AllWriter)this;
	}

	public Boolean solrEmpty() {
		return empty;
	}

	public String strEmpty() {
		return empty == null ? "" : empty.toString();
	}

	public String nomAffichageEmpty() {
		return null;
	}

	public String htmTooltipEmpty() {
		return null;
	}

	public String htmEmpty() {
		return empty == null ? "" : StringEscapeUtils.escapeHtml4(strEmpty());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAllWriter = false;

	public AllWriter initDeepAllWriter(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAllWriter) {
			alreadyInitializedAllWriter = true;
			initDeepAllWriter();
		}
		return (AllWriter)this;
	}

	public void initDeepAllWriter() {
		initAllWriter();
	}

	public void initAllWriter() {
		siteRequest_Init();
		tabStrInit();
		fileInit();
		stringWriterInit();
		bufferInit();
		printWriterInit();
		emptyInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAllWriter(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAllWriter(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAllWriter(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAllWriter(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainAllWriter(String var) {
		AllWriter oAllWriter = (AllWriter)this;
		switch(var) {
			case "siteRequest_":
				return oAllWriter.siteRequest_;
			case "tabStr":
				return oAllWriter.tabStr;
			case "file":
				return oAllWriter.file;
			case "stringWriter":
				return oAllWriter.stringWriter;
			case "buffer":
				return oAllWriter.buffer;
			case "printWriter":
				return oAllWriter.printWriter;
			case "empty":
				return oAllWriter.empty;
			default:
				return null;
		}
	}

	///////////////
	// attribute //
	///////////////

	public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributeAllWriter(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAllWriter(String var, Object val) {
		AllWriter oAllWriter = (AllWriter)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAllWriter(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAllWriter(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof AllWriter))
			return false;
		AllWriter that = (AllWriter)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AllWriter {");
		sb.append(" }");
		return sb.toString();
	}
}
