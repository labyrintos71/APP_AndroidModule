package java.module.labyrintos.SharedPreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.module.labyrintos.R;

public class SettingFragment extends PreferenceFragmentCompat {
    private SharedPreferences prefs;

    private ListPreference soundPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.sharedpreference_preference, rootKey);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        soundPreference = findPreference("sound_list");

        if(!prefs.getString("sound_list", "").equals("")){
            soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("sound_list")){
                soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
                Log.d("TESTTTTT", "onSharedPreferenceChanged: "+key);
                Log.d("TESTTTTT", "onSharedPreferenceChanged: "+sharedPreferences.getString("sound_list","this is default"));
            }

        }
    };

}
