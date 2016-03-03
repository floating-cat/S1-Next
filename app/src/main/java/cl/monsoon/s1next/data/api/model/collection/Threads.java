package cl.monsoon.s1next.data.api.model.collection;

import com.google.common.base.Objects;

import java.util.List;

import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.Forum;
import cl.monsoon.s1next.data.api.model.Thread;
import cl.monsoon.s1next.data.api.model.Thread.ThreadListInfo;

public final class Threads implements HasAccountInfo {
    private final ThreadListInfo threadListInfo;

    private final List<Thread> threadList;

    private final List<Forum> subForumList;

    private final AccountInfo accountInfo;

    public Threads(ThreadListInfo threadListInfo, List<Thread> threadList, List<Forum> subForumList,
                   AccountInfo accountInfo) {
        this.threadListInfo = threadListInfo;
        this.threadList = threadList;
        this.subForumList = subForumList;
        this.accountInfo = accountInfo;
    }

    public ThreadListInfo getThreadListInfo() {
        return threadListInfo;
    }

    public List<Thread> getThreadList() {
        return threadList;
    }

    public List<Forum> getSubForumList() {
        return subForumList;
    }

    @Override
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Threads threads = (Threads) o;
        return Objects.equal(threadListInfo, threads.threadListInfo) &&
                Objects.equal(threadList, threads.threadList) &&
                Objects.equal(subForumList, threads.subForumList) &&
                Objects.equal(accountInfo, threads.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(threadListInfo, threadList, subForumList, accountInfo);
    }
}
