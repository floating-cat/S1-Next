package cl.monsoon.s1next.data.api.model.wrapper;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.Result;
import cl.monsoon.s1next.data.api.model.collection.Posts;

public final class PostsWrapper {
    @Json(name = "Variables")
    private final Posts posts;

    @Json(name = "Message")
    private final Result result;

    public PostsWrapper(Posts posts, Result result) {
        this.posts = posts;
        this.result = result;
    }

    public Posts getPosts() {
        return posts;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsWrapper that = (PostsWrapper) o;
        return Objects.equal(posts, that.posts) &&
                Objects.equal(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(posts, result);
    }
}
