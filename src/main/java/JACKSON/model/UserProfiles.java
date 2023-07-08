package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "userProfiles")
public final class UserProfiles {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlProperty(localName = "noNamespaceSchemaLocation", isAttribute = true)
    private String noNamespaceSchemaLocation;
    @JacksonXmlElementWrapper(localName = "userProfile", useWrapping = true)
    private List<UserProfile> userProfileList = new ArrayList<>();

    public List<UserProfile> getUserProfileList() {
        return userProfileList;
    }

    public void setUserProfileList(List<UserProfile> userProfileList) {
        this.userProfileList = userProfileList;
    }

    @Override
    public String toString() {
        return "UserProfiles{" +
                "userProfileList=" + userProfileList +
                '}';
    }
}
