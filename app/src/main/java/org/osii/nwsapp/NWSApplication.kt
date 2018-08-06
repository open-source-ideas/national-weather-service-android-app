package org.osii.nwsapp

import android.app.Application
import org.osii.nwsapp.injection.component.ApplicationComponent
import org.osii.nwsapp.injection.component.DaggerApplicationComponent
import org.osii.nwsapp.injection.module.ApplicationModule
import timber.log.Timber

class NWSApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initDaggerComponent()
    }


    @android.support.annotation.VisibleForTesting
    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}
