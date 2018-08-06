package org.osii.nwsapp.injection.component

import android.app.Application
import android.content.Context
import org.osii.nwsapp.injection.ApplicationContext
import org.osii.nwsapp.injection.module.ApplicationModule
import dagger.Component
import org.osii.nwsapp.data.remote.ApiService
import org.osii.nwsapp.injection.module.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApiModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context
    fun application(): Application
    fun apiService(): ApiService

}
