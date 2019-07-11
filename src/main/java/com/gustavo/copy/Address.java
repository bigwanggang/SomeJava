package com.gustavo.copy;

public class Address {
    private String youbian;
    private String road;
    private String number;

    public Address(String youbian, String road, String number) {
        this.youbian = youbian;
        this.road = road;
        this.number = number;
    }

    public String getYoubian() {
        return youbian;
    }

    public void setYoubian(String youbian) {
        this.youbian = youbian;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "youbian='" + youbian + '\'' +
                ", road='" + road + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
