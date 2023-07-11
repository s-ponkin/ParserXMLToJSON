package SAX.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

    private String uuid;
    private List<UserProfile> userProfileList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", userList=" + userProfileList +
                '}';
    }
}
