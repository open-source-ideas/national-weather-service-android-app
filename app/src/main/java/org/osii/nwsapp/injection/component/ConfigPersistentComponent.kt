package org.osii.nwsapp.injection.component

import org.osii.nwsapp.injection.ConfigPersistent
import org.osii.nwsapp.injection.module.ActivityModule

import dagger.Component

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check [MainActivity] to see how this components
 * survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}