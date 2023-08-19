package com.tanify.library.dns.domain.datasource

import com.tanify.library.dns.domain.model.server_list.ServerModel
import kotlinx.coroutines.flow.Flow

interface DnsDataSource {
    fun subscribeDnsService()
    fun serverList(): Flow<List<ServerModel>>
}