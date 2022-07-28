package com.elink.assingmentmedisage.services

import com.elink.assingmentmedisage.model.User
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:21
 */
interface ApiService {
    @GET("posts")
    fun getUsers(): Call<MutableList<User>>
}