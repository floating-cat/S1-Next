package cl.monsoon.s1next;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cl.monsoon.s1next.data.User;
import cl.monsoon.s1next.data.Wifi;
import cl.monsoon.s1next.data.api.Api;
import cl.monsoon.s1next.data.api.S1Service;
import cl.monsoon.s1next.data.api.UserValidator;
import cl.monsoon.s1next.data.pref.AvatarCacheInvalidationInterval;
import cl.monsoon.s1next.data.pref.AvatarResolutionStrategy;
import cl.monsoon.s1next.data.pref.DownloadStrategy;
import cl.monsoon.s1next.data.pref.GeneralPreferencesManager;
import cl.monsoon.s1next.data.pref.GeneralPreferencesRepository;
import cl.monsoon.s1next.data.pref.ThemeManager;
import cl.monsoon.s1next.data.pref.TotalDownloadCacheSize;
import cl.monsoon.s1next.data.pref.qualifier.AvatarCacheInvalidationIntervalPreference;
import cl.monsoon.s1next.data.pref.qualifier.AvatarResolutionStrategyPreference;
import cl.monsoon.s1next.data.pref.qualifier.AvatarsDownloadStrategyPreference;
import cl.monsoon.s1next.data.pref.qualifier.ImagesDownloadStrategyPreference;
import cl.monsoon.s1next.data.pref.qualifier.TotalDownloadCacheSizePreference;
import cl.monsoon.s1next.viewmodel.UserViewModel;
import cl.monsoon.s1next.widget.EventBus;
import cl.monsoon.s1next.widget.PersistentHttpCookieStore;
import dagger.Module;
import dagger.Provides;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Provides instances of the objects when we need to inject.
 */
@Module
final class AppModule {
    public static final String PREF_KEY_TOTAL_DOWNLOAD_CACHE_SIZE = "pref_key_total_download_cache_size";
    public static final String PREF_KEY_AVATARS_DOWNLOAD_STRATEGY = "pref_key_avatars_download_strategy";
    public static final String PREF_KEY_AVATAR_RESOLUTION_STRATEGY = "pref_key_avatar_resolution_strategy";
    public static final String PREF_KEY_AVATAR_CACHE_INVALIDATION_INTERVAL = "pref_key_avatar_cache_invalidation_interval";
    public static final String PREF_KEY_IMAGES_DOWNLOAD_STRATEGY = "pref_key_images_download_strategy";

    private final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    CookieManager providerCookieManager(Context context) {
        return new CookieManager(new PersistentHttpCookieStore(context), CookiePolicy.ACCEPT_ALL);
    }

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient(CookieManager cookieManager) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(17, TimeUnit.SECONDS);
        builder.writeTimeout(17, TimeUnit.SECONDS);
        builder.readTimeout(77, TimeUnit.SECONDS);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.interceptors().add(httpLoggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    S1Service providerRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.BASE_API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(S1Service.class);
    }

    @Provides
    @Singleton
    EventBus providerEventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    User providerUser(UserViewModel userViewModel) {
        return userViewModel.getUser();
    }

    @Provides
    @Singleton
    UserValidator providerUserValidator(User user) {
        return new UserValidator(user);
    }

    @Provides
    @Singleton
    UserViewModel providerUserViewModel() {
        return new UserViewModel();
    }

    @Provides
    @Singleton
    Wifi providerWifi() {
        return new Wifi();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences sharedPreferences) {
        return RxSharedPreferences.create(sharedPreferences);
    }

    @Provides
    @Singleton
    @TotalDownloadCacheSizePreference
    Preference<TotalDownloadCacheSize> provideTotalDownloadCacheSizePreference(
            RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getEnum(PREF_KEY_TOTAL_DOWNLOAD_CACHE_SIZE,
                TotalDownloadCacheSize.class);
    }

    @Provides
    @Singleton
    @AvatarsDownloadStrategyPreference
    Preference<DownloadStrategy> provideAvatarsDownloadStrategyPreference(
            RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getEnum(PREF_KEY_AVATARS_DOWNLOAD_STRATEGY,
                DownloadStrategy.class);
    }

    @Provides
    @Singleton
    @AvatarResolutionStrategyPreference
    Preference<AvatarResolutionStrategy> provideAvatarResolutionStrategyPreference(
            RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getEnum(PREF_KEY_AVATAR_RESOLUTION_STRATEGY,
                AvatarResolutionStrategy.class);
    }

    @Provides
    @Singleton
    @AvatarCacheInvalidationIntervalPreference
    Preference<AvatarCacheInvalidationInterval> provideAvatarCacheInvalidationIntervalPreference(
            RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getEnum(PREF_KEY_AVATAR_CACHE_INVALIDATION_INTERVAL,
                AvatarCacheInvalidationInterval.class);
    }

    @Provides
    @Singleton
    @ImagesDownloadStrategyPreference
    Preference<DownloadStrategy> provideImagesDownloadStrategyPreference(
            RxSharedPreferences rxSharedPreferences) {
        return rxSharedPreferences.getEnum(PREF_KEY_IMAGES_DOWNLOAD_STRATEGY,
                DownloadStrategy.class);
    }

    @Provides
    @Singleton
    GeneralPreferencesRepository provideGeneralPreferencesProvider(Context context, SharedPreferences sharedPreferences) {
        return new GeneralPreferencesRepository(context, sharedPreferences);
    }

    @Provides
    @Singleton
    GeneralPreferencesManager provideGeneralPreferencesManager(GeneralPreferencesRepository generalPreferencesProvider) {
        return new GeneralPreferencesManager(generalPreferencesProvider);
    }

    @Provides
    @Singleton
    ThemeManager provideThemeManager(Context context, GeneralPreferencesRepository generalPreferencesProvider) {
        return new ThemeManager(context, generalPreferencesProvider);
    }
}
