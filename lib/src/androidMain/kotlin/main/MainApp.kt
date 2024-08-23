package main

import android.app.Application


class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        AppContext.setUp(applicationContext)

    }

}