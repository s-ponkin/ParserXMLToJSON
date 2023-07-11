package SAX.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {

    private String organizationInn;
    private String organizationKpp;
    private String workEmail;
    private String departmentName;
    private String positionName;

    @Override
    public String toString() {
        return "UserProfile{" +
                "organizationInn='" + organizationInn + '\'' +
                ", organizationKpp='" + organizationKpp + '\'' +
                ", workEmail='" + workEmail + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
