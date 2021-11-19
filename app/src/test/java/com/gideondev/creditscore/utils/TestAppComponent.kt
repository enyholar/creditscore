package com.gideondev.creditscore.utils
import com.gideondev.creditscore.di.ActivityViewModelModule
import com.gideondev.creditscore.di.NetworkModule
import com.gideondev.creditscore.di.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ActivityViewModelModule::class])
interface TestAppComponent {
    fun inject(baseTest: BaseTestTry)
}