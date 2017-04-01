package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.data.Mapper;
import com.zly.diycode.editor.Node;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class RespNode implements Mapper<Node> {


    /**
     * id : 39
     * name : HTC Vive
     * topics_count : 0
     * summary : Vive 头戴式设备由 HTC 与 Valve 联合开发，该公司曾开发 Portal 和 Half-Life 等独创游戏。世界上最精良的消费者电子产品生产商 HTC 和无可匹敌的虚拟世界设计商 Valve，强强联合，出手不凡。Vive 由 Valve 的 SteamVR 技术提供支持，因此 Steam 服务上不久将推出众多利用 Vive 功能的游戏。
     * section_id : 12
     * sort : 0
     * section_name : Virtual Reality
     * updated_at : 2016-03-27T20:23:53.440+08:00
     */

    private int id;
    private String name;
    @SerializedName("topics_count")
    private int topicsCount;
    private String summary;
    @SerializedName("section_id")
    private int sectionId;
    private int sort;
    @SerializedName("section_name")
    private String sectionName;
    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Node map() {
        Node node = new Node();
        node.setId(id);
        node.setName(name);
        node.setSectionId(sectionId);
        node.setSectionName(sectionName);
        node.setSort(sort);
        node.setSummary(summary);
        node.setTopicsCount(topicsCount);
        node.setUpdatedAt(updatedAt);
        return node;
    }
}
