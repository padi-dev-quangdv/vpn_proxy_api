package com.tanify.library.dns.domain.model.server_list

data class ServerModel(
    val id: String,
    val ipv4: String?,
    val doh: String?,
    val groupType: ServerGroupType,
    val name: String,
)
