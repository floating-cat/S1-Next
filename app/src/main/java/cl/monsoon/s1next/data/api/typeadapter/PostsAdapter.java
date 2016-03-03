package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.List;

import cl.monsoon.s1next.data.api.model.Post;
import cl.monsoon.s1next.data.api.model.Thread;
import cl.monsoon.s1next.data.api.model.collection.Posts;
import cl.monsoon.s1next.data.api.model.collection.Posts.ThreadAttachment;

public final class PostsAdapter {
    @FromJson
    public Posts fromJson(OriginalPosts originalPosts) {
        return new Posts(originalPosts.postListInfo, originalPosts.threadAttachment,
                originalPosts.postList, originalPosts.getAccountInfo());
    }

    private static final class OriginalPosts extends AccountInfoAdapter.OriginalAccountInfo {
        @Json(name = "thread")
        private Thread postListInfo;

        @Json(name = "threadsortshow")
        private ThreadAttachment threadAttachment;

        @Json(name = "postlist")
        private List<Post> postList;
    }
}
