package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.List;

import cl.monsoon.s1next.data.api.model.Forum;
import cl.monsoon.s1next.data.api.model.Thread;
import cl.monsoon.s1next.data.api.model.collection.Threads;

import static cl.monsoon.s1next.data.api.model.Thread.ThreadListInfo;

public final class ThreadsAdapter {
    @FromJson
    public Threads fromJson(OriginalThreads originalThreads) {
        return new Threads(originalThreads.threadListInfo, originalThreads.threadList,
                originalThreads.subForumList, originalThreads.getAccountInfo());
    }

    private static final class OriginalThreads extends AccountInfoAdapter.OriginalAccountInfo {
        @Json(name = "forum")
        private ThreadListInfo threadListInfo;

        @Json(name = "forum_threadlist")
        private List<Thread> threadList;

        @Json(name = "sublist")
        private List<Forum> subForumList;
    }
}
