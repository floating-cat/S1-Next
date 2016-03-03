package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.List;

import cl.monsoon.s1next.data.api.model.Favourite;
import cl.monsoon.s1next.data.api.model.collection.Favourites;

public final class FavouritesAdapter {
    @FromJson
    public Favourites fromOriginalFavourites(OriginalFavourites originalFavourites) {
        return new Favourites(originalFavourites.favouritesPerPage,
                originalFavourites.total, originalFavourites.favouriteList,
                originalFavourites.getAccountInfo());
    }

    private static final class OriginalFavourites extends AccountInfoAdapter.OriginalAccountInfo {
        @Json(name = "perpage")
        private int favouritesPerPage;

        @Json(name = "count")
        private int total;

        @Json(name = "list")
        private List<Favourite> favouriteList;
    }
}
