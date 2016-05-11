package cl.monsoon.s1next.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import cl.monsoon.s1next.R;

/**
 * An Activity includes download settings that allow users
 * to modify download features and behaviors such as cache
 * size and avatars/images download strategy.
 */
public final class DownloadPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_download);
    }
}
