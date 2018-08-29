package org.osii.nwsapp.injection.component

import org.osii.nwsapp.injection.module.ActivityModule
import dagger.Subcomponent
import org.osii.nwsapp.injection.PerActivity
import org.osii.nwsapp.ui.main.MainActivity
import org.osii.nwsapp.ui.tmp.TmpActivity
import org.osii.nwsapp.ui.location.LocationActivity

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(tmpActivity: TmpActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(locationActivity: LocationActivity)

}
