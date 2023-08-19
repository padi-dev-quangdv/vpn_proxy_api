package com.tanify.library.dns.domain.usecase.server_list

import com.tanify.library.dns.domain.model.server_list.ServerModel
import com.tanify.library.libcore.usecase.FlowResultUseCase

interface ServerListUseCase : FlowResultUseCase<ServerListParam, List<ServerModel>>