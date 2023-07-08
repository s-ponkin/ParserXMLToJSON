package SAX;

import SAX.model.User;
import SAX.model.UserProfile;
import SAX.model.Users;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {

    private static final String TAG_USER = "user";
    private static final String TAG_USERS = "users";
    private static final String TAG_UUID = "uuid";
    private static final String TAG_USER_PROFILES = "userProfiles";
    private static final String TAG_USER_PROFILE = "userProfile";
    private static final String TAG_ORGANIZATIONS_INN = "organizationInn";
    private static final String TAG_ORGANIZATIONS_KPP = "organizationKpp";
    private static final String TAG_WORK_EMAIL = "workEmail";
    private static final String TAG_DEPARTMENT_NAME = "departmentName";
    private static final String TAG_POSITION_NAME = "positionName";

    Users users = new Users();
    User user;
    UserProfile userProfile;
    List<User> usersList;
    List<UserProfile> usersProfileList;
    private String currentTagName;

    public Users getUsers(){
        return users;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        switch (qName){
            case TAG_USERS -> usersList = new ArrayList<>();

            case TAG_USER -> user = new User();

            case TAG_USER_PROFILES -> usersProfileList = new ArrayList<>();

            case TAG_USER_PROFILE -> userProfile = new UserProfile();

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName){
            case TAG_USERS -> {
                users.setUserList(usersList);
            }

            case TAG_USER -> {
                usersList.add(user);
                user = null;
            }

            case TAG_USER_PROFILES -> {
                user.setUserProfileList(usersProfileList);
                usersProfileList = null;
            }

            case TAG_USER_PROFILE -> {
                usersProfileList.add(userProfile);
                userProfile = null;
            }

        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if(currentTagName != null){
            switch (currentTagName){
                case TAG_UUID -> user.setUuid(new String(ch, start, length));

                case TAG_ORGANIZATIONS_INN -> userProfile.setOrganizationInn(new String(ch, start, length));

                case TAG_ORGANIZATIONS_KPP -> userProfile.setOrganizationKpp(new String(ch, start, length));

                case TAG_WORK_EMAIL-> userProfile.setWorkEmail(new String(ch, start, length));

                case TAG_DEPARTMENT_NAME-> userProfile.setDepartmentName(new String(ch, start, length));

                case TAG_POSITION_NAME-> userProfile.setPositionName(new String(ch, start, length));
            }
        }
    }
}
