import DOM.ParserXMLToJSONMethodDOM;
import JACKSON.ParserXMLToJSONMethodJACKSON;
import SAX.ParserXMLToJSONMethodSAX;

public class Main {

    public static void main(String[] args) {
        ParserXMLToJSONMethodDOM.parseXMLToJSON("users.xml", "DOM/data/users.json");
        ParserXMLToJSONMethodSAX.parseXMLToJSON("user_profiles.xml", "SAX/data/user_profiles.json");
//        ParserXMLToJSONMethodJACKSON.parseXMLToJSON("user_profile_roles.xml", "");

    }
}
