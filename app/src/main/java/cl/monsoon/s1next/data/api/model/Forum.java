package cl.monsoon.s1next.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.typeadapter.XmlDecoded;

public final class Forum implements Parcelable {
    public static final Parcelable.Creator<Forum> CREATOR = new Parcelable.Creator<Forum>() {
        @Override
        public Forum createFromParcel(Parcel source) {
            return new Forum(source);
        }

        @Override
        public Forum[] newArray(int i) {
            return new Forum[i];
        }
    };

    @Json(name = "fid")
    private final String id;

    @Json(name = "name")
    @XmlDecoded
    private final String name;

    @Json(name = "threads")
    private final int threadsCount;

    @Json(name = "todayposts")
    private final int todayPostsCount;

    public Forum(String id, String name, int threadsCount, int todayPostsCount) {
        this.id = id;
        this.name = name;
        this.threadsCount = threadsCount;
        this.todayPostsCount = todayPostsCount;
    }

    private Forum(Parcel source) {
        id = source.readString();
        name = source.readString();
        threadsCount = source.readInt();
        todayPostsCount = source.readInt();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public int getTodayPostsCount() {
        return todayPostsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forum forum = (Forum) o;
        return threadsCount == forum.threadsCount &&
                todayPostsCount == forum.todayPostsCount &&
                Objects.equal(id, forum.id) &&
                Objects.equal(name, forum.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, threadsCount, todayPostsCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(threadsCount);
        dest.writeInt(todayPostsCount);
    }
}
