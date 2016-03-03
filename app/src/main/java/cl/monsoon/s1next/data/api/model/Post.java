package cl.monsoon.s1next.data.api.model;

import android.support.annotation.Nullable;

import com.google.common.base.Objects;

public final class Post {
    private final String id;

    private final String authorName;

    private final String authorId;

    private final String reply;

    private final String count;

    private final long datetime;

    public Post(String id, String authorName, String authorId, String reply, String count, long datetime) {
        this.id = id;
        this.authorName = authorName;
        this.authorId = authorId;
        this.reply = reply;
        this.count = count;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    /**
     * Replies are null sometimes.
     * <p>
     * See https://github.com/floating-cat/S1-Next/issues/6
     */
    @Nullable
    public String getReply() {
        return reply;
    }

    public String getCount() {
        return count;
    }

    public long getDatetime() {
        return datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return datetime == post.datetime &&
                Objects.equal(id, post.id) &&
                Objects.equal(authorName, post.authorName) &&
                Objects.equal(authorId, post.authorId) &&
                Objects.equal(reply, post.reply) &&
                Objects.equal(count, post.count);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, authorName, authorId, reply, count, datetime);
    }
}
