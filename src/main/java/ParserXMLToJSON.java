import model.Organization;
import model.User;
import model.Users;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParserXMLToJSON {

    public static void main(String[] args) {

        Users users = new Users();
        List<User> userList = new ArrayList<>();

        Document doc;
        try {
            doc = buildDocument("./src/main/resources/users.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (checkValidation(doc)) {
            System.out.println("Validity error.");
        }

        Node usersNode = doc.getFirstChild();

        NodeList usersChild = usersNode.getChildNodes();
        Node userNode = null;
        for (int i = 0; i < usersChild.getLength(); i++) {

            if (usersChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            userNode = usersChild.item(i);
            NodeList userChild = userNode.getChildNodes();

            User user = new User();
            for (int j = 0; j < userChild.getLength(); j++) {

                if (userChild.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (userChild.item(j).getNodeName()) {
                    case "firstName": {
                        user.setFirstName(userChild.item(j).getTextContent());
                        break;
                    }
                    case "lastName": {
                        user.setLastName(userChild.item(j).getTextContent());
                        break;
                    }
                    case "middleName": {
                        user.setMiddleName(userChild.item(j).getTextContent());
                        break;
                    }
                    case "inn": {
                        user.setInn(Long.parseLong(userChild.item(j).getTextContent()));
                        break;
                    }
                    case "snils": {
                        user.setSnils(Long.parseLong(userChild.item(j).getTextContent()));
                        break;
                    }
                    case "birthday": {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            user.setBirthday(format.parse(userChild.item(j).getTextContent()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "login": {
                        user.setLogin(userChild.item(j).getTextContent());
                        break;
                    }
                    case "email": {
                        user.setEmail(userChild.item(j).getTextContent());
                        break;
                    }
                    case "organizations": {
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
                                    case "inn": {
                                        organization.setInn(Long.parseLong(organizationChild.item(l).getTextContent()));
                                        break;
                                    }
                                    case "kpp": {
                                        organization.setKpp(organizationChild.item(l).getTextContent());
                                        break;
                                    }
                                }

                            }

                            organizationList.add(organization);
                        }

                        user.setOrganizationList(organizationList);
                    }
                }
            }
            userList.add(user);

            if (userNode == null) {
                return;
            }

        }

        users.setUserList(userList);

        System.out.println(users);
    }

    private static Document buildDocument(String path) throws Exception {
        File file = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        return dbf.newDocumentBuilder().parse(file);

    }

    private static boolean checkValidation(Document document) {
        SchemaFactory factory = SchemaFactory.newDefaultInstance();
        Source schemaFile = new StreamSource(new File("./src/main/resources/xsd-schema/users.xsd"));
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
