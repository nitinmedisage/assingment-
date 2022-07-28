package com.elink.assingmentmedisage.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elink.assingmentmedisage.model.User

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 19:42
 */

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserRoomSingleton : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: UserRoomSingleton? = null
        fun getInstance(context: Context): UserRoomSingleton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    UserRoomSingleton::class.java,
                    "userroomdb")
                    .build()
            }

            return INSTANCE as UserRoomSingleton
        }
    }
}