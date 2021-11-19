package com.gideondev.creditscore.base

import android.app.Application
import com.gideondev.creditscore.di.*
import com.gideondev.creditscore.utils.BASE_URL
class BaseApplication : Application() {
     lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }

    protected open fun initDagger(): AppComponent
            = DaggerAppComponent.builder()
        .networkModule(NetworkModule(BASE_URL))
        .repositoryModule(RepositoryModule())
        .build()
}