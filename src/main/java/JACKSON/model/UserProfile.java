package JACKSON.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public final class UserProfile {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "uuid")
	private String uuid;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JacksonXmlProperty(localName = "infoSystems")
	private List<InfoSystem> infoSystems = new ArrayList<>();

	UserProfile() {
		super();
	}

	public void setUuid(String uuid) {
		this.uuid = uuid.trim();
	}
}
