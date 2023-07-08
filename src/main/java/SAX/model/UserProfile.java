package SAX.model;

public class UserProfile {

    private String organizationInn;
    private String organizationKpp;
    private String workEmail;
    private String departmentName;
    private String positionName;

    public String getOrganizationInn() {
        return organizationInn;
    }

    public void setOrganizationInn(String organizationInn) {
        this.organizationInn = organizationInn;
    }

    public String getOrganizationKpp() {
        return organizationKpp;
    }

    public void setOrganizationKpp(String organizationKpp) {
        this.organizationKpp = organizationKpp;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

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
