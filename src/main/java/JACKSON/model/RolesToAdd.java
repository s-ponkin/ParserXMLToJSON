package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RolesToAdd {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "role")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Role> roleList = new ArrayList<>();

    RolesToAdd() {
        super();
    }

    @Override
    public String toString() {
        return "RolesToAdd{" +
                "roleList=" + roleList +
                '}';
    }
}
