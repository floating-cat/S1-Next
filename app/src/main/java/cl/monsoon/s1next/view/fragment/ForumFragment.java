package cl.monsoon.s1next.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import cl.monsoon.s1next.R;
import cl.monsoon.s1next.data.api.Api;
import cl.monsoon.s1next.data.api.model.collection.ForumGroups;
import cl.monsoon.s1next.data.api.model.wrapper.ForumGroupsWrapper;
import cl.monsoon.s1next.util.IntentUtil;
import cl.monsoon.s1next.view.adapter.ForumRecyclerViewAdapter;
import cl.monsoon.s1next.view.internal.ToolbarDropDownInterface;
import rx.Observable;

/**
 * A Fragment represents forum list.
 */
public final class ForumFragment extends BaseFragment<ForumGroupsWrapper>
        implements ToolbarDropDownInterface.OnItemSelectedListener {

    public static final String TAG = ForumFragment.class.getName();

    private ForumRecyclerViewAdapter mRecyclerAdapter;
    private ForumGroups mForumGroups;

    private ToolbarDropDownInterface.Callback mToolbarCallback;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAdapter = new ForumRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mToolbarCallback = (ToolbarDropDownInterface.Callback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mToolbarCallback = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_forum, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_browser:
                IntentUtil.startViewIntentExcludeOurApp(getContext(), Uri.parse(Api.BASE_URL));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    Observable<ForumGroupsWrapper> getSourceObservable() {
        return mS1Service.getForumGroupsWrapper();
    }

    @Override
    void onNext(ForumGroupsWrapper data) {
        super.onNext(data);

        mForumGroups = data.getForumGroups();
        // host activity would call #onToolbarDropDownItemSelected(int) after
        mToolbarCallback.setupToolbarDropDown(mForumGroups.getForumGroupNameList());
    }

    /**
     * Show all forums when {@code position == 0} otherwise show
     * corresponding forum group's forum list.
     */
    @Override
    public void onToolbarDropDownItemSelected(int position) {
        if (position == 0) {
            mRecyclerAdapter.setDataSet(mForumGroups.getForumList());
        } else {
            // the first position is "全部"
            // so position - 1 to correspond its group
            mRecyclerAdapter.setDataSet(mForumGroups.getForumGroupList().get(position - 1));
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
