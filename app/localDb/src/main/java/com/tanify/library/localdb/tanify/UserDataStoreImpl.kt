package com.tanify.library.localdb.tanify

import com.tanify.library.localdb.dataStore.DataStore
import com.tanify.library.localdb.dataStore.annotation.UserStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreImpl @Inject constructor(
    @UserStorage private val dataStore: DataStore,
) : UserDataStore {
    override fun getSelectedGroupType(): Flow<String?> {
        return dataStore.getStringFlow(SELECTED_GROUP_TYPE_KEY).map {
            it ?: DEFAULT_GROUP_TYPE
        }
    }

    override suspend fun saveSelectedGroupType(type: String) {
        dataStore.save(SELECTED_GROUP_TYPE_KEY, type)
    }

    override fun isVpnTurnedOn(): Flow<Boolean> {
        return dataStore.getBooleanFlow(VPN_TURN_ON_STATE).map {
            it ?: false
        }
    }

    override suspend fun saveVpnTurned(isOn: Boolean) {
        dataStore.save(VPN_TURN_ON_STATE, isOn)
    }

    companion object {
        private const val SELECTED_GROUP_TYPE_KEY = "selected_group_type"
        private const val VPN_TURN_ON_STATE = "vpn_turn_on_state"
        private const val DEFAULT_GROUP_TYPE = "standard"
    }
}