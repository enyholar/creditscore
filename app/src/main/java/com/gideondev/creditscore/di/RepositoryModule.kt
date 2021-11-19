package com.gideondev.creditscore.di
import com.gideondev.creditscore.network.CreditScoreApiInterface
import com.gideondev.creditscore.repository.CreditScoreRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RepositoryModule  {
    @Provides
    @Singleton
    fun provideRegisterRepository(creditScoreApiInterface: CreditScoreApiInterface) = CreditScoreRepository(creditScoreApiInterface)

}