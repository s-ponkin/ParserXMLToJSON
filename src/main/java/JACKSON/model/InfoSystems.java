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
public class InfoSystems {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "infoSystem")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<InfoSystem> infoSystemList = new ArrayList<>();

    InfoSystems() {
        super();
    }

    @Override
    public String toString() {
        return "InfoSystems{" +
                "infoSystemList=" + infoSystemList +
                '}';
    }
}
