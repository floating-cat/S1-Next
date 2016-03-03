package cl.monsoon.s1next.data.api.typeadapter;

import android.util.SparseArray;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.monsoon.s1next.data.api.model.Forum;
import cl.monsoon.s1next.data.api.model.ForumCategoryByIds;
import cl.monsoon.s1next.data.api.model.collection.ForumGroups;

public final class ForumGroupsAdapter {
    @FromJson
    public ForumGroups fromOriginalForums(OriginalForums originalForums) {
        List<Forum> forumList = originalForums.forumList;
        sortForumListByTodayPostsCountDesc(forumList);
        List<String> forumGroupNameList = getForumGroupNameList(originalForums.forumCategoryByIdsList);
        List<List<Forum>> forumGroupList = getForumGroupList(forumList,
                originalForums.forumCategoryByIdsList);

        return new ForumGroups(forumList, forumGroupNameList, forumGroupList,
                originalForums.getAccountInfo());
    }

    private void sortForumListByTodayPostsCountDesc(List<Forum> forumList) {
        Collections.sort(forumList, (lhs, rhs) -> -(lhs.getTodayPostsCount() - rhs.getTodayPostsCount()));
    }

    private List<String> getForumGroupNameList(List<ForumCategoryByIds> forumGroupByIdsList) {
        List<String> forumGroupNameList = new ArrayList<>(forumGroupByIdsList.size());
        for (ForumCategoryByIds forumCategoryByIds : forumGroupByIdsList) {
            forumGroupNameList.add(forumCategoryByIds.getName());
        }
        return forumGroupNameList;
    }

    private List<List<Forum>> getForumGroupList(List<Forum> forumList,
                                                List<ForumCategoryByIds> forumGroupByIdsList) {
        SparseArray<Forum> forumSparseArray = new SparseArray<>(forumList.size());
        for (Forum forum : forumList) {
            forumSparseArray.put(Integer.parseInt(forum.getId()), forum);
        }

        List<List<Forum>> forumGroupList = new ArrayList<>();
        for (ForumCategoryByIds forumCategoryByIds : forumGroupByIdsList) {
            List<Forum> oneCategoryForumList = new ArrayList<>(forumCategoryByIds.getForumIds().size());
            for (int id : forumCategoryByIds.getForumIds()) {
                oneCategoryForumList.add(forumSparseArray.get(id));
            }
            forumGroupList.add(oneCategoryForumList);
        }
        return forumGroupList;
    }

    private static final class OriginalForums extends AccountInfoAdapter.OriginalAccountInfo {
        @Json(name = "catlist")
        List<ForumCategoryByIds> forumCategoryByIdsList;

        @Json(name = "forumlist")
        List<Forum> forumList;
    }
}
