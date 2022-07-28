package com.elink.assingmentmedisage.viewmodel

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:28
 */
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elink.assingmentmedisage.model.User
import com.elink.assingmentmedisage.repository.UserRepository
import com.elink.assingmentmedisage.roomdatabase.UserRoomSingleton
import kotlin.collections.ArrayList


class UserViewModel(private val context: Context,var application: Application) : ViewModel() {

    private var listData = MutableLiveData<ArrayList<User>>()
    private val db: UserRoomSingleton = UserRoomSingleton.getInstance(application)
    internal val allUser : LiveData<List<User>> = db.userDao().allUsers()
    internal val allFavoriteUser : LiveData<List<User>> = db.userDao().allFavoriteUser("Y")

    init {
        val userRepository: UserRepository by lazy {
            UserRepository
        }

        listData = userRepository.getMutableLiveData(context)

    }
    fun insert(user:User){
        db.userDao().insert(user)
    }
    fun update(favorite: String, id: Int){
        db.userDao().update(favorite,id)
    }
    fun getData(): MutableLiveData<ArrayList<User>> {
        return listData
    }
}