package JACKSON.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Role {

    @JacksonXmlProperty(localName = "techName")
    private String techName;
    @JacksonXmlProperty(localName = "startDate")
    private String startDate;
    @JacksonXmlProperty(localName = "endDate")
    private String endDate;
    @JacksonXmlProperty(localName = "controlledInfoSystemTechName")
    private String controlledInfoSystemTechName;

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getControlledInfoSystemTechName() {
        return controlledInfoSystemTechName;
    }

    public void setControlledInfoSystemTechName(String controlledInfoSystemTechName) {
        this.controlledInfoSystemTechName = controlledInfoSystemTechName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "techName='" + techName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", controlledInfoSystemTechName='" + controlledInfoSystemTechName + '\'' +
                '}';
    }
}
