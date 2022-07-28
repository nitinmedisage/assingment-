package com.elink.assingmentmedisage.repository

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:27
 */
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.elink.assingmentmedisage.utilies.Utility.hideProgressBar
import com.elink.assingmentmedisage.utilies.Utility.showProgressBar
import com.elink.assingmentmedisage.model.User
import com.elink.assingmentmedisage.network.ApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

object UserRepository {

    fun getMutableLiveData(context: Context) : MutableLiveData<ArrayList<User>>{

        val mutableLiveData = MutableLiveData<ArrayList<User>>()

        context.showProgressBar()

        ApiClient.apiService.getUsers().enqueue(object : Callback<MutableList<User>> {
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                hideProgressBar()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                usersResponse?.let { mutableLiveData.value = it as ArrayList<User> }
            }

        })

        return mutableLiveData
    }

}