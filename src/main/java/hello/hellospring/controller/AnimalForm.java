package hello.hellospring.controller;

public class AnimalForm {
    private String numOfRows;
    private String kindcd;

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
