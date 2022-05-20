package hello.hellospring.controller;

public class AnimalForm {
    private int numOfRows;
    private String kindcd;
    private String upr_cd;
    private String specialMark;

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

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

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }
}
