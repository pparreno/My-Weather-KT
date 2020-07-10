package com.pparreno.myweather.ui.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pparreno.myweather.R
import com.pparreno.myweather.ui.main.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}