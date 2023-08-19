package com.tanify.library.localdb.tanify

import kotlinx.coroutines.flow.Flow

interface UserDataStore {

    fun getSelectedGroupType(): Flow<String?>

    suspend fun saveSelectedGroupType(type: String)

    fun isVpnTurnedOn(): Flow<Boolean>

    suspend fun saveVpnTurned(isOn: Boolean)
}