package SAX.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

	private String uuid;

	private List<UserProfile> userProfileList = new ArrayList<>();
}
