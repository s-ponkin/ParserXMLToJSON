package JACKSON.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class InfoSystems {

    @JacksonXmlElementWrapper(localName = "infoSystem", useWrapping = false)
    private List<InfoSystem> infoSystemList = new ArrayList<>();

    public List<InfoSystem> getInfoSystemList() {
        return infoSystemList;
    }

    public void setInfoSystemList(List<InfoSystem> infoSystemList) {
        this.infoSystemList = infoSystemList;
    }

    @Override
    public String toString() {
        return "InfoSystems{" +
                "infoSystemList=" + infoSystemList +
                '}';
    }
}
