package fr.insee.lunatic.conversion;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import fr.insee.lunatic.model.Questionnaire;

public class JSONSerializer {

	public JSONSerializer() { }

	private static final Logger logger = LogManager.getLogger(JSONSerializer.class);

	public String serialize(Questionnaire questionnaire) throws JAXBException, UnsupportedEncodingException {

		if (questionnaire == null) return "";

		logger.debug("Serializing questionnaire " + questionnaire.getId());

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

		// Set it to true if you need to include the JSON root element in the JSON output
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
		// Set it to true if you need the JSON output to formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshal the questionnaire object to JSON and put the output in a string

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(questionnaire, baos);
	
		return baos.toString("UTF-8");

	}
}
