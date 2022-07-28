package com.elink.assingmentmedisage.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elink.assingmentmedisage.model.User

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 19:43
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM userTbl ORDER BY id ASC")
    fun allUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("UPDATE userTbl SET favorite=:favorite WHERE id=:id")
    fun update(favorite: String, id: Int)

    @Query("SELECT * FROM userTbl WHERE favorite=:favorite ORDER BY id ASC")
    fun allFavoriteUser(favorite: String): LiveData<List<User>>

}