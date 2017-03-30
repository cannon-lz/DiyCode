package com.zly.diycode.topics;

import android.os.Parcel;
import android.os.Parcelable;

import com.zly.diycode.R;
import com.zly.diycode.common.adapter.Item;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface EntitiesContract {

    class Reply implements Item, Parcelable {

        private String username;
        private String loginName;
        private String photo;
        private int count;
        private String floor;
        private String publishDate;
        private String content;

        public Reply() {
        }

        protected Reply(Parcel in) {
            username = in.readString();
            loginName = in.readString();
            photo = in.readString();
            count = in.readInt();
            floor = in.readString();
            publishDate = in.readString();
            content = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(username);
            dest.writeString(loginName);
            dest.writeString(photo);
            dest.writeInt(count);
            dest.writeString(floor);
            dest.writeString(publishDate);
            dest.writeString(content);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Reply> CREATOR = new Creator<Reply>() {
            @Override
            public Reply createFromParcel(Parcel in) {
                return new Reply(in);
            }

            @Override
            public Reply[] newArray(int size) {
                return new Reply[size];
            }
        };

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

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

        public static final int STATUS_LOADING = -1;
        public static final int STATUS_COMPLETE = -2;

        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isLoadComplete() {
            return status == STATUS_COMPLETE;
        }

        @Override
        public int getItemViewType() {
            return R.layout.item_progress;
        }
    }

    class Topics implements Item, Parcelable {

        private int mItemLayoutType;
        private String userPhoto;
        private String username;
        private String note;
        private String publishDate;
        private String title;
        private String id;
        private String subTitle;
        private String content;
        private String replyCount;
        private String likeCount;
        private boolean followed;
        private boolean favorited;
        private boolean liked;

        public Topics() {
        }


        protected Topics(Parcel in) {
            mItemLayoutType = in.readInt();
            userPhoto = in.readString();
            username = in.readString();
            note = in.readString();
            publishDate = in.readString();
            title = in.readString();
            id = in.readString();
            subTitle = in.readString();
            content = in.readString();
            replyCount = in.readString();
            likeCount = in.readString();
            followed = in.readByte() != 0;
            favorited = in.readByte() != 0;
            liked = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mItemLayoutType);
            dest.writeString(userPhoto);
            dest.writeString(username);
            dest.writeString(note);
            dest.writeString(publishDate);
            dest.writeString(title);
            dest.writeString(id);
            dest.writeString(subTitle);
            dest.writeString(content);
            dest.writeString(replyCount);
            dest.writeString(likeCount);
            dest.writeByte((byte) (followed ? 1 : 0));
            dest.writeByte((byte) (favorited ? 1 : 0));
            dest.writeByte((byte) (liked ? 1 : 0));
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Topics> CREATOR = new Creator<Topics>() {
            @Override
            public Topics createFromParcel(Parcel in) {
                return new Topics(in);
            }

            @Override
            public Topics[] newArray(int size) {
                return new Topics[size];
            }
        };

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

        public String getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(String replyCount) {
            this.replyCount = replyCount;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
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
