package com.ntt.tainguyen197.myapplication;

/**
 * Created by NGUYENTRUNGTAI on 4/27/2018.
 */

public class Contact {
    private String SDT;
    private String Ten;

    public Contact(String SDT, String ten) {
        this.SDT = SDT;
        this.Ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
