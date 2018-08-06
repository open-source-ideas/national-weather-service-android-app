package org.osii.nwsapp.injection.module

import android.app.Activity
import android.content.Context
import org.osii.nwsapp.injection.ActivityContext

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }
}
