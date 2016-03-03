package cl.monsoon.s1next.data.api.model.collection;

import com.google.common.base.Objects;

import java.util.List;

import cl.monsoon.s1next.data.api.model.AccountInfo;
import cl.monsoon.s1next.data.api.model.Favourite;

public final class Favourites implements HasAccountInfo {
    private final int favouritesPerPage;

    private final int total;

    private final List<Favourite> favouriteList;

    private final AccountInfo accountInfo;

    public Favourites(int favouritesPerPage, int total, List<Favourite> favouriteList,
                      AccountInfo accountInfo) {
        this.favouritesPerPage = favouritesPerPage;
        this.total = total;
        this.favouriteList = favouriteList;
        this.accountInfo = accountInfo;
    }

    public int getFavouritesPerPage() {
        return favouritesPerPage;
    }

    public int getTotal() {
        return total;
    }

    public List<Favourite> getFavouriteList() {
        return favouriteList;
    }

    @Override
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourites that = (Favourites) o;
        return favouritesPerPage == that.favouritesPerPage &&
                total == that.total &&
                Objects.equal(favouriteList, that.favouriteList) &&
                Objects.equal(accountInfo, that.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(favouritesPerPage, total, favouriteList, accountInfo);
    }
}
