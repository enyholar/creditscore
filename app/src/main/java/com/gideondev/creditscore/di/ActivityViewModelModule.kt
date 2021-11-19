package com.gideondev.creditscore.di
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gideondev.creditscore.di.viewModelFactory.ViewModelFactory
import com.gideondev.creditscore.viewModel.CreditScoreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CreditScoreViewModel::class)
    internal abstract fun postCreditScoreViewModel(viewModel: CreditScoreViewModel): ViewModel

}