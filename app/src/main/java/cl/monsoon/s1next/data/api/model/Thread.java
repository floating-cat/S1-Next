package cl.monsoon.s1next.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.typeadapter.XmlDecoded;

/**
 * Ambiguity in naming due to {@link java.lang.Thread}.
 */
public final class Thread implements Parcelable {
    public static final Parcelable.Creator<Thread> CREATOR = new Parcelable.Creator<Thread>() {
        @Override
        public Thread createFromParcel(Parcel source) {
            return new Thread(source);
        }

        @Override
        public Thread[] newArray(int i) {
            return new Thread[i];
        }
    };

    @Json(name = "tid")
    private final String id;

    @Json(name = "subject")
    @XmlDecoded
    private final String title;

    @Json(name = "replies")
    private final int repliesCount;

    @Json(name = "readperm")
    private final int permission;

    public Thread(String id, String title, int repliesCount, int permission) {
        this.id = id;
        this.title = title;
        this.repliesCount = repliesCount;
        this.permission = permission;
    }

    private Thread(Parcel source) {
        id = source.readString();
        title = source.readString();
        repliesCount = source.readInt();
        permission = source.readInt();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public int getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return repliesCount == thread.repliesCount &&
                permission == thread.permission &&
                Objects.equal(id, thread.id) &&
                Objects.equal(title, thread.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, repliesCount, permission);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeInt(repliesCount);
        dest.writeInt(permission);
    }

    public static final class ThreadListInfo {
        @Json(name = "threads")
        private final int threadsCount;

        public ThreadListInfo(int threadsCount) {
            this.threadsCount = threadsCount;
        }

        public int getThreadsCount() {
            return threadsCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ThreadListInfo that = (ThreadListInfo) o;
            return threadsCount == that.threadsCount;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(threadsCount);
        }
    }
}
