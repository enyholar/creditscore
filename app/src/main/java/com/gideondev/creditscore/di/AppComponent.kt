package com.gideondev.creditscore.di
import com.gideondev.creditscore.base.BaseActivity
import com.gideondev.creditscore.base.BaseFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ActivityViewModelModule::class,
    ]
)
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(fragment: BaseFragment)
}