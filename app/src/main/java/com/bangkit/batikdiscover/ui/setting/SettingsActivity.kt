package com.bangkit.batikdiscover.ui.setting

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bangkit.batikdiscover.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // Set up the custom action bar
        val customToolbar: Toolbar = findViewById(R.id.customToolbar)
        setSupportActionBar(customToolbar)

        // Set judul Toolbar using TextView
        val toolbarTitleTextView: TextView = findViewById(R.id.toolbar_title)

        // Hide the default ActionBar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Handle back button press
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
