package com.myapplication

import MainView
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private var removeSplashScreen: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                removeSplashScreen.not()
            }
            setOnExitAnimationListener { splashScreen ->
                // access to the splash screen and moving it down
                ObjectAnimator.ofFloat(
                    splashScreen.view,
                    View.TRANSLATION_Y,
                    // from top to down
                    0f, splashScreen.view.height.toFloat()
                ).apply {
                    // deceleration interpolaror, duration
                    interpolator = AccelerateInterpolator()
                    duration = 350L
                    // do not forget to remove the splash screen
                    doOnEnd { splashScreen.remove() }
                    start()
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(
                            android.graphics.Color.TRANSPARENT,
                            android.graphics.Color.TRANSPARENT
                        ),
                        navigationBarStyle = SystemBarStyle.light(
                            android.graphics.Color.TRANSPARENT,
                            android.graphics.Color.TRANSPARENT
                        )
                    )

                }
            }


        }
        super.onCreate(savedInstanceState)

        setContent {
            LaunchedEffect(Unit) {
                delay(2000)
                removeSplashScreen = true
            }
            MainView()
        }
    }
}
