package hello.hellospring.controller;

public class AnimalForm {
    private String numOfRows;
    private String kindcd;
    private String upr_cd;

    public String getUpr_cd() {
        return upr_cd;
    }

    public void setUpr_cd(String upr_cd) {
        this.upr_cd = upr_cd;
    }

    public String getKindcd() {
        return kindcd;
    }

    public void setKindcd(String kindcd) {
        this.kindcd = kindcd;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }
}
