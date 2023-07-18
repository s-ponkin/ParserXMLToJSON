package DOM.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
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
}
