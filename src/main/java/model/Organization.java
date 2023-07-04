package model;

public class Organization {

    private long inn;
    private String kpp;

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
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
