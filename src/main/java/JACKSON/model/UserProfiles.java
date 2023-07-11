package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "userProfiles")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserProfiles {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "userProfile")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<UserProfile> userProfiles = new ArrayList<>();

    public UserProfiles() {
        super();
    }

    @Override
    public String toString() {
        return "UserProfiles{" +
                "userProfileList=" + userProfiles +
                '}';
    }
}
