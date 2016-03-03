package cl.monsoon.s1next.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import cl.monsoon.s1next.data.api.model.Favourite;
import cl.monsoon.s1next.data.api.model.Thread;
import cl.monsoon.s1next.view.activity.PostListActivity;


public final class FavouriteViewModel {

    public final ObservableField<Favourite> favourite = new ObservableField<>();

    public View.OnClickListener goToThisFavourite() {
        return v -> {
            Favourite favourite = this.favourite.get();
            Thread thread = new Thread(favourite.getId(), favourite.getTitle(), 0, 0);

            PostListActivity.startPostListActivity(v.getContext(), thread, false);
        };
    }
}
