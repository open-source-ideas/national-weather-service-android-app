package org.osii.nwsapp.injection.module

import android.app.Application
import android.content.Context
import org.osii.nwsapp.injection.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Provide application-level dependencies.
 */
@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

}
