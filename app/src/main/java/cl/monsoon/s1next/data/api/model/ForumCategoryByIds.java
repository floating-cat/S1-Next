package cl.monsoon.s1next.data.api.model;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import java.util.List;

import cl.monsoon.s1next.data.api.typeadapter.XmlDecoded;

public final class ForumCategoryByIds {
    @Json(name = "name")
    @XmlDecoded
    private final String name;

    @Json(name = "forums")
    private final List<Integer> forumIds;

    public ForumCategoryByIds(String name, List<Integer> forumIds) {
        this.name = name;
        this.forumIds = forumIds;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getForumIds() {
        return forumIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumCategoryByIds that = (ForumCategoryByIds) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(forumIds, that.forumIds);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, forumIds);
    }
}
