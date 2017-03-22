package com.zly.diycode.topics;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface EntitiesContract {

    class Reply implements Item {

        private String username;
        private String photo;
        private int count;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        private String floor;
        private String publishDate;
        private String content;


        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public int getItemViewType() {
            return R.layout.item_reply;
        }

    }

    class ItemProgress implements Item {

        @Override
        public int getItemViewType() {
            return R.layout.item_progress;
        }
    }

    class Topics implements Item {

        private int mItemLayoutType;

        private String userPhoto;
        private String username;
        private String note;
        private String publishDate;
        private String title;
        private String id;
        private String subTitle;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        @Override
        public int getItemViewType() {
            return mItemLayoutType == 0 ? R.layout.item_topics : mItemLayoutType;
        }

        public void setItemViewType(int itemViewType) {
            mItemLayoutType = itemViewType;
        }
    }

}
