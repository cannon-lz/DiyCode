package com.zly.diycode.editor;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zly.diycode.BR;
import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

import java.util.Objects;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class Node extends BaseObservable implements Item {

    private int id;
    private String name;
    private int topicsCount;
    private String summary;
    private int sectionId;
    private int sort;
    private String sectionName;
    private String updatedAt;
    private boolean isParent;

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(int topicsCount) {
        this.topicsCount = topicsCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Bindable
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
        notifyPropertyChanged(BR.sectionName);
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_node;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Node)) {

            return false;
        }

        Node node = (Node) obj;
        return node.getSectionId() == getSectionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSectionId(), getSectionName());
    }
}
