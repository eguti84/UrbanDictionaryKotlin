package com.batch.urbandictionarykotlin.repository

import com.batch.urbandictionarykotlin.dto.UrbanResponse
import com.batch.urbandictionarykotlin.repository.remote.ServiceInstance
import io.reactivex.Observable

class DefinitionRepository private constructor() {
    private object RepositoryInstanceHolder {
        internal val INSTANCE = DefinitionRepository()
    }

    fun getDefinitions(term: String): Observable<UrbanResponse> {
        return ServiceInstance.urbanService.getDefinitions(term)
    }

    companion object {
        val instance: DefinitionRepository
            get() = RepositoryInstanceHolder.INSTANCE
    }
}
