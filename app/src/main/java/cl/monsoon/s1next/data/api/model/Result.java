package cl.monsoon.s1next.data.api.model;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.typeadapter.AndroidMessageFormatter;

public final class Result {
    @Json(name = "messageval")
    private final String status;

    @Json(name = "messagestr")
    @AndroidMessageFormatter
    private final String message;

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equal(status, result.status) &&
                Objects.equal(message, result.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, message);
    }
}
