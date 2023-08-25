package com.tanify.library.localdb.dataStore.annotation

import javax.inject.Qualifier

/**
 * UserDataStore will be responsible for saving user data level (user info, eye visibility...etc).
 * For saving application data which should not be clear when user logged out. Please use [SystemStorage].
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserStorage
