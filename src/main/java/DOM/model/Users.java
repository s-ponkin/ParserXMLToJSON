package DOM.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Users {

	private List<User> userList = new ArrayList<>();
}
