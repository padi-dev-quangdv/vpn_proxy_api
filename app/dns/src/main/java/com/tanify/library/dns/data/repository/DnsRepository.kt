package com.tanify.library.dns.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tanify.library.dns.data.dto.server_list.ServerDto
import com.tanify.library.dns.domain.datasource.DnsDataSource
import com.tanify.library.dns.domain.model.server_list.ServerModel
import javax.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class DnsRepository @Inject constructor(
    private val db: FirebaseFirestore,
) : DnsDataSource {

    private val serverList: MutableSharedFlow<List<ServerDto>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private var serverListListenerRegistration: ListenerRegistration? = null

    override fun subscribeDnsService() {
        serverListListenerRegistration?.remove()
        serverListListenerRegistration = db
            .collection(dnsServerCollectionName)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    error.printStackTrace()
                    serverList.tryEmit(emptyList())
                    return@addSnapshotListener
                }
                snapshot ?: return@addSnapshotListener
                val data = snapshot.documents.mapNotNull { it.toObject(ServerDto::class.java) }
                serverList.tryEmit(data)
            }
    }

    override fun serverList(): Flow<List<ServerModel>> {
        if (serverListListenerRegistration == null) {
            subscribeDnsService()
        }
        return serverList.map {
            it.map { dto ->
                dto.toModel()
            }
        }
    }

    companion object {
        private const val dnsServerCollectionName = "dns"
    }
}