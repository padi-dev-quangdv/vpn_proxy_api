package com.tanify.library.dns.domain.usecase.server_list

import com.tanify.library.dns.domain.datasource.DnsDataSource
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.dns.domain.model.server_list.ServerModel
import com.tanify.library.libcore.usecase.ResultModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ServerListUseCaseImpl @Inject constructor(
    private val dataSource: DnsDataSource,
) : ServerListUseCase {
    override fun execute(param: ServerListParam): Flow<ResultModel<List<ServerModel>>> {
        return dataSource.serverList().map { list ->
            var newList = filterByGroupType(list, param.type)
            newList = takeRandom(newList, param.takeRandom, param.numberOfRandom)
            ResultModel.Success(newList)
        }
    }

    private fun takeRandom(
        list: List<ServerModel>,
        takeRandom: Boolean,
        numberOfRandom: Int
    ): List<ServerModel> {
        if (!takeRandom || list.size <= numberOfRandom) {
            return list
        }
        return list.asSequence().shuffled().take(numberOfRandom).toList()
    }

    private fun filterByGroupType(
        list: List<ServerModel>,
        type: ServerGroupType?
    ): List<ServerModel> {
        type ?: return list
        return list.filter { model ->
            model.groupType == type
        }
    }

}