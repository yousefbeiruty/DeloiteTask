package com.example.deloitetask.screens.main.ui.settings

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.deloitetask.R
import com.example.deloitetask.common.LocalConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)
        val pref = preferenceScreen.findPreference<ListPreference>("setting_language")


        lifecycleScope.launch {
            var selectedLang = ""
            checkBuildTiramisu(tiramisu = {
                selectedLang = context?.getSystemService(LocaleManager::class.java)
                    ?.applicationLocales?.get(0).toString()
            }, lowerTiramisu = {
                selectedLang = AppCompatDelegate.getApplicationLocales().get(0).toString()

            })
            pref?.summary = if (selectedLang == LocalConstants.ARABIC_CODE) {
                LocalConstants.ARABIC
            } else {
                LocalConstants.ENGLISH
            }
        }

        pref?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue.toString()) {
                LocalConstants.ENGLISH -> {
                    checkBuildTiramisu(tiramisu = {
                        context?.getSystemService(LocaleManager::class.java)
                            ?.applicationLocales = LocaleList.forLanguageTags("en")
                    }, lowerTiramisu = {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
                    })
                }
                LocalConstants.ARABIC -> {
                    checkBuildTiramisu(tiramisu = {
                        context?.getSystemService(LocaleManager::class.java)
                            ?.applicationLocales = LocaleList.forLanguageTags("ar")
                    }, lowerTiramisu = {
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ar"))
                    })
                }
            }
            true
        }
    }

    private fun checkBuildTiramisu(tiramisu: () -> Unit, lowerTiramisu: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            tiramisu.invoke()
        } else {
            lowerTiramisu.invoke()
        }
    }
}