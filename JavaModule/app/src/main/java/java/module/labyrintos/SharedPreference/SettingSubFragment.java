package java.module.labyrintos.SharedPreference;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.module.labyrintos.R;

/**
 * Created by Labyrintos on 2019-10-06
 */
public class SettingSubFragment extends PreferenceFragmentCompat {
    private SharedPreferences prefs;

    private ListPreference keywordSoundPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.sharedpreference_preference_sub, rootKey);
        keywordSoundPreference = findPreference("keyword_sound_list");
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("keyword_sound_list", "").equals("")){
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("keyword_sound_list")){
                keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }

        }
    };

}
