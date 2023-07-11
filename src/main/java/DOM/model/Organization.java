package DOM.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Organization {

    private String inn;
    private String kpp;

    @Override
    public String toString() {
        return "Organization{" +
                "inn=" + inn +
                ", kpp='" + kpp + '\'' +
                '}';
    }
}
