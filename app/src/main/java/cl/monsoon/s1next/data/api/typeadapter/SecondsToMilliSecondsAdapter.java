package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.concurrent.TimeUnit;

public final class SecondsToMilliSecondsAdapter {
    @FromJson
    @SecondsToMilliSeconds
    public long fromJson(long seconds) {
        return TimeUnit.SECONDS.toMillis(seconds);
    }

    @ToJson
    public long toJson(@SecondsToMilliSeconds long milliSeconds) {
        throw new UnsupportedOperationException();
    }
}
