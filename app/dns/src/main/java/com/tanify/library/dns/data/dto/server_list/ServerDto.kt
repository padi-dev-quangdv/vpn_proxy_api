package com.tanify.library.dns.data.dto.server_list

import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.dns.domain.model.server_list.ServerModel

data class ServerDto(
    val id: String = "",
    val ipv4: String? = null,
    val doh: String? = null,
    val type: String? = null,
    val name: String? = null,
) {
    fun toModel(): ServerModel {
        val groupType = when (type) {
            "standard" -> ServerGroupType.Standard
            "intense" -> ServerGroupType.Intense
            "ultimate" -> ServerGroupType.Ultimate
            else -> ServerGroupType.Standard
        }
        return ServerModel(
            id = id,
            ipv4 = ipv4,
            doh = doh,
            groupType = groupType,
            name = name ?: ""
        )
    }
}
