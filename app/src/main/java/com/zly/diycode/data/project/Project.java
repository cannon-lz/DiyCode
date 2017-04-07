package com.zly.diycode.data.project;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;
import com.zly.diycode.http.entities.RespProject;

/**
 * Created by zhangluya on 2017/4/6.
 */

public class Project implements Item , Parcelable {

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
    private Category category;
    @SerializedName("sub_category")
    private SubCategory subCategory;
    @SerializedName("last_updated_at")
    private String lastUpdatedAt;

    public Project() {
    }

    protected Project(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        readme = in.readString();
        github = in.readString();
        website = in.readString();
        download = in.readString();
        star = in.readInt();
        fork = in.readInt();
        watch = in.readInt();
        projectCoverUrl = in.readString();
        labelStr = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        subCategory = in.readParcelable(SubCategory.class.getClassLoader());
        lastUpdatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(readme);
        dest.writeString(github);
        dest.writeString(website);
        dest.writeString(download);
        dest.writeInt(star);
        dest.writeInt(fork);
        dest.writeInt(watch);
        dest.writeString(projectCoverUrl);
        dest.writeString(labelStr);
        dest.writeParcelable(category, flags);
        dest.writeParcelable(subCategory, flags);
        dest.writeString(lastUpdatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_project;
    }

    public static class Category implements Parcelable {
        /**
         * name : Android
         * id : 1
         */

        private String name;
        private int id;

        public Category() {
        }

        protected Category(Parcel in) {
            name = in.readString();
            id = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(id);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Category> CREATOR = new Creator<Category>() {
            @Override
            public Category createFromParcel(Parcel in) {
                return new Category(in);
            }

            @Override
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };

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
    }

    public static class SubCategory implements Parcelable {
        /**
         * name : 其他(other)
         * id : 23
         */

        private String name;
        private int id;

        public SubCategory() {
        }

        protected SubCategory(Parcel in) {
            name = in.readString();
            id = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(id);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
            @Override
            public SubCategory createFromParcel(Parcel in) {
                return new SubCategory(in);
            }

            @Override
            public SubCategory[] newArray(int size) {
                return new SubCategory[size];
            }
        };

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
    }
}
