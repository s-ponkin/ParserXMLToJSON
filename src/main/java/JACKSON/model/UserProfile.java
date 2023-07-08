package JACKSON.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public final class UserProfile {
    @JacksonXmlProperty(localName = "uuid")
    private String uuid;
    @JacksonXmlProperty(localName = "infoSystems")
    private InfoSystems infoSystems;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public InfoSystems getInfoSystems() {
        return infoSystems;
    }

    public void setInfoSystems(InfoSystems infoSystems) {
        this.infoSystems = infoSystems;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "uuid='" + uuid + '\'' +
                ", infoSystems=" + infoSystems +
                '}';
    }
}
