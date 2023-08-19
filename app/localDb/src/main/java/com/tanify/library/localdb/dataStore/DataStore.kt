package com.tanify.library.localdb.dataStore

import kotlinx.coroutines.flow.Flow

interface DataStore {

    suspend fun <T : Any> save(key: String, value: T?)

    suspend fun <T : Any> saveObject(key: String, value: T?, clazz: Class<T>)

    suspend fun getString(key: String): String?

    fun getStringFlow(key: String): Flow<String?>

    suspend fun <T : Any> getObject(key: String, clazz: Class<T>): T?

    fun <T : Any> getObjectFlow(key: String, clazz: Class<T>): Flow<T?>

    suspend fun getInt(key: String): Int?

    fun getIntFlow(key: String): Flow<Int?>

    suspend fun getFloat(key: String): Float?

    fun getFloatFlow(key: String): Flow<Float?>

    suspend fun getLong(key: String): Long?

    fun getLongFlow(key: String): Flow<Long?>

    suspend fun getDouble(key: String): Double?

    fun getDoubleFlow(key: String): Flow<Double?>

    suspend fun getBoolean(key: String): Boolean?

    fun getBooleanFlow(key: String): Flow<Boolean?>

    suspend fun removeByKey(key: String): Boolean

    suspend fun clearAll()
}
