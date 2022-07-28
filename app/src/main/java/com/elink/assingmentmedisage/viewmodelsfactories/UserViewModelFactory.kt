package com.elink.assingmentmedisage.viewmodelsfactories

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:32
 */
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elink.assingmentmedisage.viewmodel.UserViewModel

class UserViewModelFactory(private val context: Context,val application : Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(context,application) as T
    }

}