package JACKSON;

import JACKSON.model.UserProfiles;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParserXMLToJSONMethodJACKSON {

	private static final Logger logger = Logger.getLogger(ParserXMLToJSONMethodJACKSON.class.getName());

	/**
	 * Метод конвертирует файл формата XML в формат JSON методом JACKSON.
	 *
	 * @param inputPath  путь к файлу формата XML.
	 * @param outputPath путь к файлу формата JSON.
	 */
	public static void parseXMLToJSON(String inputPath, String outputPath) {
		if (!checkValidation(inputPath)) {
			System.out.println("Validity error.");
		}

		ObjectMapper objectMapper = new XmlMapper();
		UserProfiles userProfiles;
		String jsonObject = null;
		String stringUtils;
		try {
			stringUtils = StringUtils.toEncodedString(Files.readAllBytes(Paths.get(inputPath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			throw new RuntimeException(e);
		}
		try {
			userProfiles = objectMapper.readValue(stringUtils, UserProfiles.class);
			DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter();
			defaultPrettyPrinter.indentArraysWith(new DefaultIndenter());
			ObjectWriter objectWriter = new ObjectMapper().writer().with(defaultPrettyPrinter);
			jsonObject = objectWriter.writeValueAsString(userProfiles);
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}

		if (jsonObject == null) {
			return;
		}

		File file = new File(outputPath);
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(jsonObject);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	private static boolean checkValidation(String path) {
		SchemaFactory factory = SchemaFactory.newDefaultInstance();
		Source userProfileRolesSchemaFile = new StreamSource(new File("xsd-schema/user_profile_roles.xsd"));
		Source typesSchemaFile = new StreamSource(new File("xsd-schema/types.xsd"));
		try {
			Schema schema = factory.newSchema(new Source[] {userProfileRolesSchemaFile, typesSchemaFile});
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
			return true;
		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage());
			return false;
		}
	}
}
