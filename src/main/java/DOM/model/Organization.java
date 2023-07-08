package DOM.model;

public class Organization {

    private String inn;
    private String kpp;

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "inn=" + inn +
                ", kpp='" + kpp + '\'' +
                '}';
    }
}
