package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InfoSystem {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "techName")
    private String techName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "rolesToAdd")
    private List<Role> rolesToAdd = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "rolesToDelete")
    private List<Role> rolesToDelete = new ArrayList<>();

    InfoSystem() {
        super();
    }

    public void setTechName(String techName) {
        this.techName = techName.trim();
    }
}
