package JACKSON.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class InfoSystem {

    @JacksonXmlProperty(localName = "techName")
    private String techName;
    @JacksonXmlProperty(localName = "rolesToAdd")
    private RolesToAdd rolesToAdd;
    @JacksonXmlProperty(localName = "rolesToDelete")
    private RolesToDelete rolesToDelete;

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public RolesToAdd getRolesToAdd() {
        return rolesToAdd;
    }

    public void setRolesToAdd(RolesToAdd rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public RolesToDelete getRolesToDelete() {
        return rolesToDelete;
    }

    public void setRolesToDelete(RolesToDelete rolesToDelete) {
        this.rolesToDelete = rolesToDelete;
    }

    @Override
    public String toString() {
        return "InfoSystem{" +
                "techName='" + techName + '\'' +
                ", rolesToAdd=" + rolesToAdd +
                ", rolesToDelete=" + rolesToDelete +
                '}';
    }
}
