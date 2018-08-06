package org.osii.nwsapp.injection

import javax.inject.Scope

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * [ConfigPersistentComponent]
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigPersistent
