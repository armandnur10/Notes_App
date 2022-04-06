package com.armand.notesapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.armand.notesapp.R
import com.armand.notesapp.ui.MainActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val splashTime: Long = 3000 // lama splashscreen berjalan

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Pindah ke Home Activity setelah 3 detik
            finish()
        }, splashTime)

    }
}