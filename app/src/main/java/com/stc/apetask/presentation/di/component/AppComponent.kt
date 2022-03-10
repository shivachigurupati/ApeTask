package com.stc.apetask.presentation.di.component

import com.stc.apetask.MainActivity
import com.stc.apetask.presentation.di.module.RemoteDataSource
import com.stc.apetask.presentation.di.module.RepositoryModule
import com.stc.apetask.presentation.di.module.RetrofitInstanceModule
import com.stc.apetask.presentation.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        RetrofitInstanceModule::class,
        RemoteDataSource::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)


}