package com.armand.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.armand.notesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*untuk menghilangkan stroke di action bar*/
//        supportActionBar?.elevation = 0f

        // MainActivity bertindak sebagai Navigation Host
//        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?
//
//        if (navHost != null){
//            val appBarConfiguration = AppBarConfiguration(navHost.navController.graph)
//            // action bar di set di nav host (nav host variabel yg nampung main activity)
//            setupActionBarWithNavController(navHost.navController, appBarConfiguration)
//        }
    }

    /* menampilkan menu bar atas*/
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_home, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}