package cl.monsoon.s1next.data.api.model.wrapper;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.collection.ForumGroups;

public final class ForumGroupsWrapper {
    @Json(name = "Variables")
    private final ForumGroups forumGroups;

    public ForumGroupsWrapper(ForumGroups forumGroups) {
        this.forumGroups = forumGroups;
    }

    public ForumGroups getForumGroups() {
        return forumGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumGroupsWrapper that = (ForumGroupsWrapper) o;
        return Objects.equal(forumGroups, that.forumGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(forumGroups);
    }
}
