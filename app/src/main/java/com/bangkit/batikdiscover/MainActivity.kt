package com.bangkit.batikdiscover

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bangkit.batikdiscover.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_community, R.id.navigation_scan, R.id.navigation_product, R.id.navigation_profile
            )
        )

        // Set up the custom action bar
        val customToolbar: Toolbar = findViewById(R.id.customToolbar)
        setSupportActionBar(customToolbar)

        // Set judul Toolbar using TextView
        val toolbarTitleTextView: TextView = findViewById(R.id.toolbar_title)

        // Hide the default ActionBar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val destinationTitle = when (destination.id) {
                R.id.navigation_dashboard -> getString(R.string.title_dashboard)
                R.id.navigation_community -> getString(R.string.title_comunity)
                R.id.navigation_scan -> getString(R.string.title_scan)
                R.id.navigation_product -> getString(R.string.title_product)
                R.id.navigation_profile -> getString(R.string.title_profile)
                else -> getString(R.string.app_name)
            }

            toolbarTitleTextView.text = destinationTitle
        }

        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notification -> {
                // Handle notification menu item click
                return true
            }
            R.id.setting -> {
                // Handle setting menu item click
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
