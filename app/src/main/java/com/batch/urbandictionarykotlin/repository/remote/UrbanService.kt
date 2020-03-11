package com.batch.urbandictionarykotlin.repository.remote

import com.batch.urbandictionarykotlin.dto.UrbanResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UrbanService {
    @Headers("X-RapidAPI-Key: 178a49fa98msh8c40e2c7437d459p143e7cjsn6f192c25f3d8")
    @GET("/define")
    fun getDefinitions(
        @Query("term") term: String
    ): Observable<UrbanResponse>
}