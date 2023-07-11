package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserProfile {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "uuid")
    private String uuid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "infoSystems")
    private InfoSystems infoSystems;

    UserProfile() {
        super();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid.trim();
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "uuid='" + uuid + '\'' +
                ", infoSystems=" + infoSystems +
                '}';
    }
}
