package cl.monsoon.s1next.data.api.typeadapter;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JsonQualifier
public @interface SecondsToMilliSeconds {
}
