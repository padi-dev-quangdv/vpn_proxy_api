package com.tanify.library.localdb.dataStore.annotation

import javax.inject.Qualifier

/**
 * SystemDataStore will be responsible for saving application data level (masterdata, language...etc).
 * For saving User data which can be cleared separately. Please use [UserStorage].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SystemStorage
