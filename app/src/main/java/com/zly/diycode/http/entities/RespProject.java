package com.zly.diycode.http.entities;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.common.DateUtils;
import com.zly.diycode.data.Mapper;
import com.zly.diycode.data.project.Project;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class RespProject implements Mapper<Project> {


    /**
     * id : 20891
     * name : cropiwa
     * description : 一个 Android自定义裁剪图片开源控件
     * readme : markdown 格式
     * github : https://github.com/steelkiwi/cropiwa
     * website : http://steelkiwi.com/
     * download : https://api.github.com/repos/steelkiwi/cropiwa/zipball
     * star : 403
     * fork : 38
     * watch : 14
     * project_cover_url : https://diycode.b0.upaiyun.com/developer_organization/avatar/3119_1491443524.jpg
     * label_str : Crop,widget,crop-image
     * category : {"name":"Android","id":1}
     * sub_category : {"name":"其他(other)","id":23}
     * last_updated_at : 2017-04-06T09:51:46.000+08:00
     */

    private int id;
    private String name;
    private String description;
    private String readme;
    private String github;
    private String website;
    private String download;
    private int star;
    private int fork;
    private int watch;
    @SerializedName("project_cover_url")
    private String projectCoverUrl;
    @SerializedName("label_str")
    private String labelStr;
    private CategoryBean category;
    @SerializedName("sub_category")
    private SubCategoryBean subCategory;
    @SerializedName("last_updated_at")
    private String lastUpdatedAt;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    public String getProjectCoverUrl() {
        return projectCoverUrl;
    }

    public void setProjectCoverUrl(String projectCoverUrl) {
        this.projectCoverUrl = projectCoverUrl;
    }

    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public SubCategoryBean getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryBean subCategory) {
        this.subCategory = subCategory;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public Project map() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setCategory(category.map());
        project.setDescription(description);
        project.setDownload(download);
        project.setFork(fork);
        project.setGithub(github);
        project.setLabelStr(labelStr);
        project.setLastUpdatedAt(DateUtils.computePastTime(lastUpdatedAt));
        project.setProjectCoverUrl(projectCoverUrl);
        project.setReadme(readme);
        project.setStar(star);
        project.setSubCategory(subCategory.map());
        project.setWatch(watch);
        project.setWebsite(website);
        return project;
    }

    public static class CategoryBean implements Mapper<Project.Category> {
        /**
         * name : Android
         * id : 1
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public Project.Category map() {
            Project.Category category = new Project.Category();
            category.setId(id);
            category.setName(name);
            return category;
        }
    }

    public static class SubCategoryBean implements Mapper<Project.SubCategory> {
        /**
         * name : 其他(other)
         * id : 23
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public Project.SubCategory map() {
            Project.SubCategory subCategory = new Project.SubCategory();
            subCategory.setId(id);
            subCategory.setName(name);
            return subCategory;
        }
    }
}
