package cl.monsoon.s1next.data.api.model.wrapper;

import com.google.common.base.Objects;
import com.squareup.moshi.Json;

import cl.monsoon.s1next.data.api.model.Result;
import cl.monsoon.s1next.data.api.model.collection.Threads;

public final class ThreadsWrapper {
    @Json(name = "Variables")
    private final Threads threads;

    @Json(name = "Message")
    private final Result result;

    public ThreadsWrapper(Threads threads, Result result) {
        this.threads = threads;
        this.result = result;
    }

    public Threads getThreads() {
        return threads;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreadsWrapper that = (ThreadsWrapper) o;
        return Objects.equal(threads, that.threads) &&
                Objects.equal(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(threads, result);
    }
}
