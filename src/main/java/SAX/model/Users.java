package SAX.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Users {

    private List<User> userList = new ArrayList<>();

    @Override
    public String toString() {
        return "Users{" +
                "userList=" + userList +
                '}';
    }
}
