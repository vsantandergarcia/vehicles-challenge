package com.vsantander.vehicleschallenge.ui.splash

import android.os.Bundle
import android.os.Handler
import com.vsantander.vehicleschallenge.R
import com.vsantander.vehicleschallenge.ui.base.BaseActivity
import java.util.concurrent.TimeUnit

@BaseActivity.Animation(BaseActivity.FADE)
class SplashActivity : BaseActivity() {

    companion object {
        private val SPLASH_DELAY = TimeUnit.SECONDS.toMillis(2) // 2 seconds
    }

    private val handler = Handler()

    private val runnable: Runnable = Runnable {
        if (!isFinishing) {
            //TODO route next activity
        }
    }

    /* Activity methods */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed(runnable, SPLASH_DELAY)
    }

    public override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }

}