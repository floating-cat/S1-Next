package cl.monsoon.s1next.data.api.model.wrapper;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.Result;
import cl.monsoon.s1next.data.api.model.collection.Favourites;

public final class FavouritesWrapper {
    @Json(name = "Variables")
    private final Favourites favourites;

    @Json(name = "Message")
    private final Result result;

    public FavouritesWrapper(Favourites favourites, Result result) {
        this.favourites = favourites;
        this.result = result;
    }

    public Favourites getFavourites() {
        return favourites;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouritesWrapper that = (FavouritesWrapper) o;
        return Objects.equal(favourites, that.favourites) &&
                Objects.equal(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(favourites, result);
    }
}
