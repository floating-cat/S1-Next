package cl.monsoon.s1next.data.pref.qualifier;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ImagesDownloadStrategyPreference {
}
