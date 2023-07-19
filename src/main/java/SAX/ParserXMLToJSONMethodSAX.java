package SAX;

import SAX.model.User;
import SAX.model.Users;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParserXMLToJSONMethodSAX {

	private static final Logger logger = Logger.getLogger(ParserXMLToJSONMethodSAX.class.getName());

	private static final String TAG_USER = "user";
	private static final String TAG_USERS = "users";
	private static final String TAG_UUID = "uuid";
	private static final String TAG_USER_PROFILES = "userProfiles";
	private static final String TAG_INN = "inn";
	private static final String TAG_KPP = "kpp";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_DEPARTMENT_NAME = "departmentName";
	private static final String TAG_POSITION_NAME = "positionName";

	/**
	 * Метод конвертирует файл формата XML в формат JSON методом SAX.
	 *
	 * @param inputPath  путь к файлу формата XML.
	 * @param outputPath путь к файлу формата JSON.
	 */
	public static void parseXMLToJSON(String inputPath, String outputPath) {
		if (!checkValidation(inputPath)) {
			System.out.println("Validity error.");
		}

		Users users = creatingListUsersObjectFromData(inputPath);
		if (users != null) {
			creatingJSONFile(users, outputPath);
		}
	}

	private static Users creatingListUsersObjectFromData(String path) {

		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		SAXParserHandler handler = new SAXParserHandler();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
		} catch (Exception e) {
			logger.log(Level.INFO, "Open sax parser error " + e.getMessage());
			return null;
		}

		File file = new File(path);
		try {
			parser.parse(file, handler);
		} catch (SAXException e) {
			logger.log(Level.INFO, "Sax parsing error " + e.getMessage());
		} catch (IOException e) {
			logger.log(Level.INFO, "IO parsing error " + e.getMessage());
		}
		return handler.getUsers();
	}

	private static void creatingJSONFile(Users users, String path) {
		JSONObject JSONObjectUsers = new JSONObject();
		JSONArray JSONOArray = new JSONArray();

		for (int i = 0; i < users.getUserList().size(); i++) {
			JSONObject JSONObjectUser = new JSONObject();
			User user = users.getUserList().get(i);

			if (user.getUuid() != null) {
				JSONObject JSONObjectUserUuid = new JSONObject();
				JSONObjectUserUuid.put(TAG_UUID, user.getUuid());
				JSONObjectUser.put(TAG_USER, JSONObjectUserUuid);
			}

			if (!user.getUserProfileList().isEmpty()) {
				JSONArray JSONOArrayInfoProfiles = new JSONArray();
				for (int j = 0; j < user.getUserProfileList().size(); j++) {
					JSONObject JSONObjectInfoProfile = new JSONObject();
					if (user.getUserProfileList().get(j).getOrganizationInn() != null) {
						JSONObjectInfoProfile.put(TAG_INN, user.getUserProfileList().get(j).getOrganizationInn());
					}
					if (user.getUserProfileList().get(j).getOrganizationKpp() != null) {
						JSONObjectInfoProfile.put(TAG_KPP, user.getUserProfileList().get(j).getOrganizationKpp());
					}
					if (user.getUserProfileList().get(j).getWorkEmail() != null) {
						JSONObjectInfoProfile.put(TAG_EMAIL, user.getUserProfileList().get(j).getWorkEmail());
					}
					if (user.getUserProfileList().get(j).getDepartmentName() != null) {
						JSONObjectInfoProfile.put(TAG_DEPARTMENT_NAME, user.getUserProfileList().get(j).getDepartmentName());
					}
					if (user.getUserProfileList().get(j).getPositionName() != null) {
						JSONObjectInfoProfile.put(TAG_POSITION_NAME, user.getUserProfileList().get(j).getPositionName());
					}
					JSONOArrayInfoProfiles.add(JSONObjectInfoProfile);
				}
				JSONObjectUser.put(TAG_USER_PROFILES, JSONOArrayInfoProfiles);
			}
			JSONOArray.add(JSONObjectUser);
		}
		JSONObjectUsers.put(TAG_USERS, JSONOArray);

		File file = new File(path);
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			String result = JSONObjectUsers.toJSONString();
			fileWriter.write(result);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	private static boolean checkValidation(String path) {
		SchemaFactory factory = SchemaFactory.newDefaultInstance();
		Source userProfilesSchemaFile = new StreamSource(new File("xsd-schema/user_profiles.xsd"));
		Source typesSchemaFile = new StreamSource(new File("xsd-schema/types.xsd"));
		try {
			Schema schema = factory.newSchema(new Source[] {userProfilesSchemaFile, typesSchemaFile});
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
			return true;
		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage());
			return false;
		}
	}
}
