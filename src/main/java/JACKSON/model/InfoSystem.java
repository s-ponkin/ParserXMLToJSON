package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoSystem {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "techName")
    private String techName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "rolesToAdd")
    private RolesToAdd rolesToAdd;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "rolesToDelete")
    private RolesToDelete rolesToDelete;

    InfoSystem() {
        super();
    }

    public void setTechName(String techName) {
        this.techName = techName.trim();
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
