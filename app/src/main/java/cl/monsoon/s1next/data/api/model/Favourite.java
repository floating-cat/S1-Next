package cl.monsoon.s1next.data.api.model;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.typeadapter.XmlDecoded;

public final class Favourite {
    @Json(name = "id")
    private final String id;

    @Json(name = "title")
    @XmlDecoded
    private final String title;

    public Favourite(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourite favourite = (Favourite) o;
        return Objects.equal(id, favourite.id) &&
                Objects.equal(title, favourite.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title);
    }
}
