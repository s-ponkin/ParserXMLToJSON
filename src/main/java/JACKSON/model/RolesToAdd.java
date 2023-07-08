package JACKSON.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class RolesToAdd {

    @JacksonXmlElementWrapper(localName = "rolesToAdd", useWrapping = false)
    private List<Role> roleList = new ArrayList<>();

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "RolesToAdd{" +
                "roleList=" + roleList +
                '}';
    }
}
