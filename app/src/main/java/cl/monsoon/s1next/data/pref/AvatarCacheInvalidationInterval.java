package cl.monsoon.s1next.data.pref;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.StringSignature;
import com.google.common.base.Supplier;

import cl.monsoon.s1next.util.DateUtil;

public enum AvatarCacheInvalidationInterval {
    EVERY_DAY(DateUtil::today),
    EVERY_WEEK(DateUtil::dayOfWeek),
    EVERY_MONTH(DateUtil::dayOfMonth);

    private final Supplier<String> supplier;

    AvatarCacheInvalidationInterval(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    /**
     * Gets a string signature in order to invalidate avatar every day/week/month.
     *
     * @return A {@link Key} representing the string signature of date that will be mixed in to the
     * cache key.
     */
    private Key getSignature() {
        return new StringSignature(supplier.get());
    }
}
