package com.lib.intenttest

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("5e539ff52e00005b002dacf8")
    suspend fun getREsp() : Response<Resp>
}