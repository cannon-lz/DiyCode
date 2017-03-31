package com.zly.diycode.editor;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zhangluya on 2017/3/24.
 */

public class EditRequester implements Parcelable {

    public static final int TYPE_REPLY = 0;
    public static final int TYPE_CREATE_PAPER = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_REPLY, TYPE_CREATE_PAPER})
    public @interface EditorType {
    }

    @EditorType
    private int type;
    private String paperId;
    private String floor;
    private String content;
    private String paperTitle;
    private String loginName;

    public EditRequester() {
    }

    protected EditRequester(Parcel in) {
        @EditorType
        int conv = in.readInt();
        type = conv;
        paperId = in.readString();
        floor = in.readString();
        content = in.readString();
        paperTitle = in.readString();
        loginName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(paperId);
        dest.writeString(floor);
        dest.writeString(content);
        dest.writeString(paperTitle);
        dest.writeString(loginName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EditRequester> CREATOR = new Creator<EditRequester>() {
        @Override
        public EditRequester createFromParcel(Parcel in) {
            return new EditRequester(in);
        }

        @Override
        public EditRequester[] newArray(int size) {
            return new EditRequester[size];
        }
    };

    public
    @EditorType
    int getType() {
        return type;
    }

    public void setType(@EditorType int type) {
        this.type = type;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
