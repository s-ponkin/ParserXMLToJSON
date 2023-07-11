import DOM.ParserXMLToJSONMethodDOM;
import JACKSON.ParserXMLToJSONMethodJACKSON;
import SAX.ParserXMLToJSONMethodSAX;

public class Main {

    public static void main(String[] args) {

        String inputPathDOM = "users.xml";
        String outputPathDOM = "DOM/data/users.json";

        String inputPathSAX = "user_profiles.xml";
        String outputPathSAX = "SAX/data/user_profiles.json";

        String inputPathJACKSON = "user_profile_roles.xml";
        String outputPathJACKSON = "JACKSON/data/user_profile_roles.json";

        ParserXMLToJSONMethodDOM.parseXMLToJSON(inputPathDOM, outputPathDOM);
        ParserXMLToJSONMethodSAX.parseXMLToJSON(inputPathSAX, outputPathSAX);
        ParserXMLToJSONMethodJACKSON.parseXMLToJSON(inputPathJACKSON, outputPathJACKSON);
    }
}
