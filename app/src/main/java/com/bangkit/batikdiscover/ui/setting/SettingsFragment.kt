package com.bangkit.batikdiscover.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.bangkit.batikdiscover.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref, rootKey)
    }
}
