package org.computate.enUS.school.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.WordUtils;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import io.vertx.core.buffer.Buffer;

public class AllWriter extends AllWriterGen<Object> {

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _tabStr(Wrap<String> c) {
		c.o("\t");
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_) {
		AllWriter o = new AllWriter();
		o.initDeepForClass(siteRequest_);
		return o;
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_, String tabStr) {
		AllWriter o = new AllWriter();
		o.setTabStr(tabStr);
		o.initDeepForClass(siteRequest_);
		return o;
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_, File file) {
		AllWriter o = new AllWriter();
		o.setFile(file);
		o.initDeepForClass(siteRequest_);
		return o;
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_, File file, String tabStr) {
		AllWriter o = new AllWriter();
		o.setFile(file);
		o.setTabStr(tabStr);
		o.initDeepForClass(siteRequest_);
		return o;
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_, Buffer buffer) {
		AllWriter o = new AllWriter();
		o.setBuffer(buffer);
		o.initDeepForClass(siteRequest_);
		return o;
	}

	public static AllWriter create(SiteRequestEnUS siteRequest_, Buffer buffer, String tabStr) {
		AllWriter o = new AllWriter();
		o.setBuffer(buffer);
		o.setTabStr(tabStr);
		o.initDeepForClass(siteRequest_);
		return o;
	}

	protected void _file(Wrap<File> c) {
	}

	protected void _stringWriter(Wrap<StringWriter> c) {
		if(file == null && buffer == null)
			c.o(new StringWriter());
	}

	protected void _buffer(Wrap<Buffer> c) {
	}

	protected void _printWriter(Wrap<PrintWriter> c) {
		if(buffer == null) {
			if(file == null)
				c.o(new PrintWriter(stringWriter));
			else {
				try {
					c.o(new PrintWriter(file));
				} catch (FileNotFoundException e) {
					ExceptionUtils.rethrow(e);
				}
			}
		}
	}

	protected void _empty(Wrap<Boolean> c) {
		c.o(true);
	}

	public AllWriter t(int tabNumber, Object...objects) {
		for(int i = 0; i < tabNumber; i++)
			s(tabStr);
		s(objects);
		return this;
	}

	public AllWriter tl(int tabNumber, Object...objects) {
		for(int i = 0; i < tabNumber; i++)
			s(tabStr);
		s(objects);
		s("\n");
		return this;
	}

	public AllWriter l(Object...objects) {
		s(objects);
		s("\n");
		return this;
	}

	public AllWriter s(Object...objects) { 
		for(Object object : objects) {
			if(object != null) {
				if(object instanceof List) {
					List<?> chain = (List<?>)object;
					for(Object object2 : chain) {
						String str = object2.toString();
						if(object2 != null && !StringUtils.isEmpty(str)) {
//							if(httpServerResponse == null)
							if(buffer == null)
								printWriter.write(str);
							else
//								httpServerResponse.write(str);
								buffer.appendString(str);
						}
					}
				}
				else {
					String str = object.toString();
					if(!StringUtils.isEmpty(str)) {
//						if(httpServerResponse == null)
						if(buffer == null)
							printWriter.write(str);
						else
//							httpServerResponse.write(str);
							buffer.appendString(str);
					}
				}
			}
		}
		empty = false;
		return this;
	}

	public AllWriter string(Object...objets) {
		s("\"");
		for(Object objet : objets)
			if(objet != null)
				s(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""));
		s("\"");
		return this;
	}

	public String q(Object...objets) {
		StringBuilder o = new StringBuilder();
		o.append("\"");
		for(Object objet : objets)
			if(objet != null)
				o.append(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""));
		o.append("\"");
		return o.toString();
	}

	public String qjs(Object...objets) {
		StringBuilder o = new StringBuilder();
		o.append("\"");
		for(Object objet : objets)
			if(objet != null)
				o.append(StringUtils.replace(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""), "\n", "\\n"));
		o.append("\"");
		return o.toString();
	}

	public String js(Object...objets) {
		StringBuilder o = new StringBuilder();
		for(Object objet : objets)
			if(objet != null)
				o.append(StringUtils.replace(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""), "\n", "\\n"));
		return o.toString();
	}

	public void  flushClose() {

		if(printWriter != null)
			printWriter.flush();
		if(stringWriter != null)
			stringWriter.flush();

		if(printWriter != null)
			printWriter.close();
		if(stringWriter != null) {
			try {
				stringWriter.close();
			} catch (IOException e) {
				ExceptionUtils.rethrow(e);
			}
		}
	}

	@Override()
	public String toString() {
		if(buffer != null)
			return stringWriter.toString();
		else if(file != null)
			return printWriter.toString();
		else
			return stringWriter.toString();
	}
}
