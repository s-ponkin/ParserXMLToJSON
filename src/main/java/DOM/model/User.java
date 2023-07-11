package DOM.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class User {

    private String firstName;
    private String lastName;
    private String middleName;
    private String inn;
    private String snils;
    private Date birthday;
    private String login;
    private String email;
    private List<Organization> organizationList;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", inn=" + inn +
                ", snils=" + snils +
                ", birthday=" + birthday +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", organizationList=" + organizationList +
                '}';
    }
}
