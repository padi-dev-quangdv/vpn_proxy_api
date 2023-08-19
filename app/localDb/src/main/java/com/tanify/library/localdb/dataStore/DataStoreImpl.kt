package com.tanify.library.localdb.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber

@Singleton
class DataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStore {

    private val Context.dataStore by preferencesDataStore(name = context.packageName)
    private val dataFlow = context.dataStore.data.catch {
        Timber.e("$this got error: $it")
    }

    /**
     * Moshi is used for serialization and deserialization Object <-> JSON
     */
//    private val moshi: Moshi by lazy {
//        Moshi.Builder()
//            .add(KotlinJsonAdapterFactory()) //Use for mapping by reflection if object class have no code gen adapter
//            .build()
//    }

    override suspend fun <T : Any> save(key: String, value: T?) {
        when (value) {
            is Boolean -> {
                val preferenceKey = booleanPreferencesKey(key)
                context.dataStore.edit {
                    it[preferenceKey] = value
                }
            }

            is Int -> {
                val preferenceKey = intPreferencesKey(key)
                context.dataStore.edit { preferences ->
                    preferences[preferenceKey] = value
                }
            }

            is Long -> {
                val preferenceKey = longPreferencesKey(key)
                context.dataStore.edit { preferences ->
                    preferences[preferenceKey] = value
                }
            }

            is Float -> {
                val preferenceKey = floatPreferencesKey(key)
                context.dataStore.edit { preferences ->
                    preferences[preferenceKey] = value
                }
            }

            is Double -> {
                val preferenceKey = doublePreferencesKey(key)
                context.dataStore.edit { preferences ->
                    preferences[preferenceKey] = value
                }
            }

            is String -> {
                val preferenceKey = stringPreferencesKey(key)
                context.dataStore.edit { preferences ->
                    preferences[preferenceKey] = value
                }
            }

            null -> removeByKey(key)
            else -> throw RuntimeException("The type of data is not supported yet!!!")
        }
    }

    override suspend fun <T : Any> saveObject(key: String, value: T?, clazz: Class<T>) {
//        //Remove by key if value is null
//        if (value == null) {
//            removeByKey(key)
//            return
//        }
//
//        try {
//            //Serialize object into JSON string
//            val adapter = moshi.adapter(clazz)
//            val jsonString = adapter.toJson(value)
//            save(key, jsonString)
//        } catch (exception: Exception) {
//            exception.printStackTrace()
//        }
    }

    override suspend fun getString(key: String): String? {
        val preferenceKey = stringPreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getStringFlow(key: String): Flow<String?> {
        val preferenceKey = stringPreferencesKey(key)
        return dataFlow.map { it[preferenceKey] }
    }

    override suspend fun <T : Any> getObject(key: String, clazz: Class<T>): T? {
        return try {
//            val json = getString(key) ?: return null
//            val adapter = moshi.adapter(clazz)
//            adapter.fromJson(json)
            null
        } catch (t: Throwable) {
//            t.printStackTrace()
            null
        }
    }

    override fun <T : Any> getObjectFlow(key: String, clazz: Class<T>): Flow<T?> {
        return getStringFlow(key).map { json ->
            if (json == null) return@map null
            try {
//                val adapter = moshi.adapter(clazz)
//                adapter.fromJson(json)
                null
            } catch (t: Throwable) {
                t.printStackTrace()
                null
            }
        }
    }

    override suspend fun getInt(key: String): Int? {
        val preferenceKey = intPreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getIntFlow(key: String): Flow<Int?> {
        val preferenceKey = intPreferencesKey(key)
        return dataFlow.map { preferences -> preferences[preferenceKey] }
    }

    override suspend fun getFloat(key: String): Float? {
        val preferenceKey = floatPreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getFloatFlow(key: String): Flow<Float?> {
        val preferenceKey = floatPreferencesKey(key)
        return dataFlow.map { preferences -> preferences[preferenceKey] }
    }

    override suspend fun getLong(key: String): Long? {
        val preferenceKey = longPreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getLongFlow(key: String): Flow<Long?> {
        val preferenceKey = longPreferencesKey(key)
        return dataFlow.map { preferences -> preferences[preferenceKey] }
    }

    override suspend fun getDouble(key: String): Double? {
        val preferenceKey = doublePreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getDoubleFlow(key: String): Flow<Double?> {
        val preferenceKey = doublePreferencesKey(key)
        return dataFlow.map { preferences -> preferences[preferenceKey] }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val preferenceKey = booleanPreferencesKey(key)
        return dataFlow.first()[preferenceKey]
    }

    override fun getBooleanFlow(key: String): Flow<Boolean?> {
        val preferenceKey = booleanPreferencesKey(key)
        return dataFlow.map { preferences -> preferences[preferenceKey] }
    }

    override suspend fun removeByKey(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun clearAll() {
        TODO("Not yet implemented")
    }
}