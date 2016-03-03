package cl.monsoon.s1next.data.api.model.collection;

import com.google.common.base.Objects;

import java.util.List;

import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.Forum;

public final class ForumGroups implements HasAccountInfo {
    private final List<Forum> forumList;

    private final List<String> forumGroupNameList;

    private final List<List<Forum>> forumGroupList;

    private final AccountInfo accountInfo;

    public ForumGroups(List<Forum> forumList, List<String> forumGroupNameList,
                       List<List<Forum>> forumGroupList, AccountInfo accountInfo) {
        this.forumList = forumList;
        this.forumGroupNameList = forumGroupNameList;
        this.forumGroupList = forumGroupList;
        this.accountInfo = accountInfo;
    }

    public List<Forum> getForumList() {
        return forumList;
    }

    public List<String> getForumGroupNameList() {
        return forumGroupNameList;
    }

    public List<List<Forum>> getForumGroupList() {
        return forumGroupList;
    }

    @Override
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumGroups that = (ForumGroups) o;
        return Objects.equal(forumList, that.forumList) &&
                Objects.equal(forumGroupNameList, that.forumGroupNameList) &&
                Objects.equal(forumGroupList, that.forumGroupList) &&
                Objects.equal(accountInfo, that.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(forumList, forumGroupNameList, forumGroupList, accountInfo);
    }
}
