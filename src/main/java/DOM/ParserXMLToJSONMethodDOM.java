package DOM;

import DOM.model.Organization;
import DOM.model.User;
import DOM.model.Users;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParserXMLToJSONMethodDOM {

	private static final String TAG_FIRST_NAME = "firstName";
	private static final String TAG_LAST_NAME = "lastName";
	private static final String TAG_MIDDLE_NAME = "middleName";
	private static final String TAG_INN = "inn";
	private static final String TAG_SNILS = "snils";
	private static final String TAG_BIRTHDAY = "birthday";
	private static final String TAG_LOGIN = "login";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_KPP = "kpp";
	private static final String TAG_ORGANIZATIONS = "organizations";
	private static final String TAG_USER = "user";
	private static final String TAG_USERS = "users";
	private static final String DATA_FORMAT = "yyyy-MM-dd";

	/**
	 * Метод конвертирует файл формата XML в формат JSON методом DOM.
	 *
	 * @param inputPath  путь к файлу формата XML.
	 * @param outputPath путь к файлу формата JSON.
	 */
	public static void parseXMLToJSON(String inputPath, String outputPath) {
		Document doc;
		try {
			doc = buildDocument(inputPath);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (!checkValidation(inputPath)) {
			System.out.println("Validity error.");
		}

		Users users = new Users();
		users.setUserList(creatingListUsersObjectFromData(doc));
		creatingJSONFile(users, outputPath);
	}

	private static List<User> creatingListUsersObjectFromData(Document document) {
		List<User> userList = new ArrayList<>();
		Node usersNode = document.getFirstChild();
		NodeList usersChild = usersNode.getChildNodes();
		Node userNode;
		for (int i = 0; i < usersChild.getLength(); i++) {
			if (usersChild.item(i).getNodeType() == Node.ELEMENT_NODE) {
				userNode = usersChild.item(i);
				userList.add(creatingUserFromData(userNode));
			}
		}
		return userList;
	}

	private static User creatingUserFromData(Node userNode) {
		NodeList userChild = userNode.getChildNodes();
		User user = new User();
		for (int j = 0; j < userChild.getLength(); j++) {
			if (userChild.item(j).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			switch (userChild.item(j).getNodeName()) {
				case TAG_FIRST_NAME -> user.setFirstName(userChild.item(j).getTextContent().trim());
				case TAG_LAST_NAME -> user.setLastName(userChild.item(j).getTextContent().trim());
				case TAG_MIDDLE_NAME -> user.setMiddleName(userChild.item(j).getTextContent().trim());
				case TAG_INN -> user.setInn(userChild.item(j).getTextContent().trim());
				case TAG_SNILS -> user.setSnils(userChild.item(j).getTextContent().trim());
				case TAG_BIRTHDAY -> {
					SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT);
					try {
						user.setBirthday(format.parse(userChild.item(j).getTextContent().trim()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				case TAG_LOGIN -> user.setLogin(userChild.item(j).getTextContent().trim());
				case TAG_EMAIL -> user.setEmail(userChild.item(j).getTextContent().trim());
				case TAG_ORGANIZATIONS -> {
					List<Organization> organizationList = new ArrayList<>();
					Node organizationsNode = userChild.item(j);
					NodeList organizationsChild = organizationsNode.getChildNodes();

					for (int k = 0; k < organizationsChild.getLength(); k++) {

						Organization organization = new Organization();

						if (organizationsChild.item(k).getNodeType() != Node.ELEMENT_NODE) {
							continue;
						}

						Node organizationNode = organizationsChild.item(k);
						NodeList organizationChild = organizationNode.getChildNodes();

						for (int l = 0; l < organizationChild.getLength(); l++) {

							if (organizationChild.item(l).getNodeType() != Node.ELEMENT_NODE) {
								continue;
							}

							switch (organizationChild.item(l).getNodeName()) {
								case TAG_INN -> organization.setInn(organizationChild.item(l).getTextContent().trim());

								case TAG_KPP -> organization.setKpp(organizationChild.item(l).getTextContent().trim());
							}
						}
						organizationList.add(organization);
					}
					user.setOrganizationList(organizationList);
				}
			}
		}
		return user;
	}

	private static void creatingJSONFile(Users users, String path) {
		JSONObject JSONObjectUsers = new JSONObject();
		JSONArray JSONOArray = new JSONArray();

		for (int i = 0; i < users.getUserList().size(); i++) {
			JSONObject JSONObjectUser = new JSONObject();

			User user = users.getUserList().get(i);
			JSONObject JSONObjectInfoUser = new JSONObject();
			if (user.getFirstName() != null) {
				JSONObjectInfoUser.put(TAG_FIRST_NAME, user.getFirstName());
			}
			if (user.getLastName() != null) {
				JSONObjectInfoUser.put(TAG_LAST_NAME, user.getLastName());
			}
			if (user.getMiddleName() != null) {
				JSONObjectInfoUser.put(TAG_MIDDLE_NAME, user.getMiddleName());
			}
			if (user.getInn() != null) {
				JSONObjectInfoUser.put(TAG_INN, user.getInn());
			}
			if (user.getSnils() != null) {
				JSONObjectInfoUser.put(TAG_SNILS, user.getSnils());
			}
			if (user.getBirthday() != null) {
				SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT);
				String data = format.format(user.getBirthday());
				JSONObjectInfoUser.put(TAG_BIRTHDAY, data);
			}
			if (user.getLogin() != null) {
				JSONObjectInfoUser.put(TAG_LOGIN, user.getLogin());
			}
			if (user.getEmail() != null) {
				JSONObjectInfoUser.put(TAG_EMAIL, user.getEmail());
			}

			JSONArray JSONOArrayOrganizations = new JSONArray();
			for (int j = 0; j < user.getOrganizationList().size(); j++) {
				JSONObject JSONObjectOrganization = new JSONObject();
				if (user.getOrganizationList().get(j).getInn() != null) {
					JSONObjectOrganization.put(TAG_INN, user.getOrganizationList().get(j).getInn());
				}
				if (user.getOrganizationList().get(j).getKpp() != null) {
					JSONObjectOrganization.put(TAG_KPP, user.getOrganizationList().get(j).getKpp());
				}

				JSONOArrayOrganizations.add(JSONObjectOrganization);
			}

			if (!JSONObjectInfoUser.isEmpty()) {
				JSONObjectUser.put(TAG_USER, JSONObjectInfoUser);
			}
			if (!JSONOArrayOrganizations.isEmpty()) {
				JSONObjectInfoUser.put(TAG_ORGANIZATIONS, JSONOArrayOrganizations);
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
			e.printStackTrace();
		}
	}

	private static Document buildDocument(String path) throws Exception {
		File file = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		return dbf.newDocumentBuilder().parse(file);
	}

	private static boolean checkValidation(String path) {
		SchemaFactory factory = SchemaFactory.newDefaultInstance();
		Source usersSchemaFile = new StreamSource(new File("xsd-schema/users.xsd"));
		Source typesSchemaFile = new StreamSource(new File("xsd-schema/types.xsd"));
		try {
			Schema schema = factory.newSchema(new Source[] {usersSchemaFile, typesSchemaFile});
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
