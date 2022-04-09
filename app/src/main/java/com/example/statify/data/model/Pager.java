package com.example.statify.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class Pager<T> implements Parcelable {
    public String href;
    public List<T> items;
    public int limit;
    public String next;
    public int offset;
    public String previous;
    public int total;
    public static final Creator<Pager> CREATOR = new Creator<Pager>() {
        public Pager createFromParcel(Parcel source) {
            return new Pager(source);
        }

        public Pager[] newArray(int size) {
            return new Pager[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
        dest.writeList(this.items);
        dest.writeInt(this.limit);
        dest.writeString(this.next);
        dest.writeInt(this.offset);
        dest.writeString(this.previous);
        dest.writeInt(this.total);
    }

    public Pager() {
    }

    protected Pager(Parcel in) {
        this.href = in.readString();
        this.items = in.readArrayList(ArrayList.class.getClassLoader());
        this.limit = in.readInt();
        this.next = in.readString();
        this.offset = in.readInt();
        this.previous = in.readString();
        this.total = in.readInt();
    }
}