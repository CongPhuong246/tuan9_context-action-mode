package com.example.listview.mode;

public class Nhac {
    private int mColor;
    private String mNhac;
    private String mTen;

    public Nhac(int mColor, String mNhac, String mTen) {
        this.mColor = mColor;
        this.mNhac = mNhac;
        this.mTen = mTen;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public String getmNhac() {
        return mNhac;
    }

    public void setmNhac(String mNhac) {
        this.mNhac = mNhac;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }
}
