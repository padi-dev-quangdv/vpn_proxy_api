package com.tanify.library.dns.domain.usecase.server_list

import com.tanify.library.dns.domain.model.server_list.ServerGroupType

data class ServerListParam(
    val type: ServerGroupType? = null,
    val takeRandom: Boolean = false,
    val numberOfRandom: Int = 2,
)