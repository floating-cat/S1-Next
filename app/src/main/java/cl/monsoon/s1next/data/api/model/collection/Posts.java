package cl.monsoon.s1next.data.api.model.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.Post;
import cl.monsoon.s1next.data.api.model.Thread;

public final class Posts implements HasAccountInfo {
    private final Thread postListInfo;

    private final ThreadAttachment threadAttachment;

    private final List<Post> postList;

    private final AccountInfo accountInfo;

    public Posts(Thread postListInfo, ThreadAttachment threadAttachment, List<Post> postList,
                 AccountInfo accountInfo) {
        this.postListInfo = postListInfo;
        this.threadAttachment = threadAttachment;
        this.postList = postList;
        this.accountInfo = accountInfo;
    }

    public Thread getPostListInfo() {
        return postListInfo;
    }

    public ThreadAttachment getThreadAttachment() {
        return threadAttachment;
    }

    public List<Post> getPostList() {
        return postList;
    }

    @Override
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return Objects.equal(postListInfo, posts.postListInfo) &&
                Objects.equal(threadAttachment, posts.threadAttachment) &&
                Objects.equal(postList, posts.postList) &&
                Objects.equal(accountInfo, posts.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(postListInfo, threadAttachment, postList, accountInfo);
    }

    public static final class ThreadAttachment {
        @Json(name = "threadsortname")
        private final String title;

        @Json(name = "optionlist")
        private final List<ThreadAttachmentInfo> infoList;

        public ThreadAttachment(String title, ArrayList<ThreadAttachmentInfo> infoList) {
            this.title = title;
            this.infoList = infoList;
        }

        public String getTitle() {
            return title;
        }

        public List<ThreadAttachmentInfo> getInfoList() {
            return infoList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ThreadAttachment that = (ThreadAttachment) o;
            return Objects.equal(title, that.title) &&
                    Objects.equal(infoList, that.infoList);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(title, infoList);
        }

        public static final class ThreadAttachmentInfo implements Parcelable {
            public static final Creator<ThreadAttachmentInfo> CREATOR = new Creator<ThreadAttachmentInfo>() {
                @Override
                public ThreadAttachmentInfo createFromParcel(Parcel source) {
                    return new ThreadAttachmentInfo(source);
                }

                @Override
                public ThreadAttachmentInfo[] newArray(int size) {
                    return new ThreadAttachmentInfo[size];
                }
            };

            private final String label;

            private final String value;

            public ThreadAttachmentInfo(String label, String value) {
                this.label = label;
                this.value = value;
            }

            private ThreadAttachmentInfo(Parcel source) {
                label = source.readString();
                value = source.readString();
            }

            public String getLabel() {
                return label;
            }

            public String getValue() {
                return value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ThreadAttachmentInfo that = (ThreadAttachmentInfo) o;
                return Objects.equal(label, that.label) &&
                        Objects.equal(value, that.value);
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(label, value);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(label);
                dest.writeString(value);
            }
        }
    }
}
