package com.ntt.tainguyen197.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NGUYENTRUNGTAI on 4/16/2018.
 */

public class HinhAnh implements Parcelable {
    String urlhinh;
    String datetaken;

    public HinhAnh(String urlhinh,String datetaken) {
        this.urlhinh = urlhinh;this.datetaken = datetaken;
    }


    public String getDatetaken() {
        return datetaken;
    }

    public void setDatetaken(String datetaken) {
        this.datetaken = datetaken;
    }

    public String getUrlhinh() {
        return urlhinh;
    }

    public void setUrlhinh(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeString(urlhinh);
        dest.writeString(datetaken);
    }

    public HinhAnh(Parcel in) {
        urlhinh = in.readString();
        datetaken = in.readString();
    }

    public static final Parcelable.Creator<HinhAnh> CREATOR = new Parcelable.Creator<HinhAnh>() {
        public HinhAnh createFromParcel(Parcel in) {
            return new HinhAnh(in);
        }

        public HinhAnh[] newArray(int size) {
            return new HinhAnh[size];
        }
    };
}
