package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Role {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "techName")
	private String techName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "startDate")
	private String startDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "endDate")
	private String endDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "controlledInfoSystemTechName")
	private String controlledInfoSystemTechName;

	public Role() {
		super();
	}

	public void setTechName(String techName) {
		this.techName = techName.trim();
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate.trim();
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate.trim();
	}

	public void setControlledInfoSystemTechName(String controlledInfoSystemTechName) {
		this.controlledInfoSystemTechName = controlledInfoSystemTechName.trim();
	}
}
