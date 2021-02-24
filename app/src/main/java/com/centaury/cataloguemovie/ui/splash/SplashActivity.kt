package com.centaury.cataloguemovie.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.main.MainActivity
import com.centaury.cataloguemovie.utils.getColorFromAttr

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 10/22/2020.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColorFromAttr(R.attr.colorSurface)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}