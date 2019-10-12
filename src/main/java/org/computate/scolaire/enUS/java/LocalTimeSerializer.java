package org.computate.scolaire.enUS.java;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import org.computate.scolaire.enUS.page.PageLayout;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override()
	public void  serialize(LocalTime o, JsonGenerator generator, SerializerProvider provider) throws IOException, IOException {
		generator.writeString(PageLayout.FORMATTimeDisplay.format(o));
	}
}
