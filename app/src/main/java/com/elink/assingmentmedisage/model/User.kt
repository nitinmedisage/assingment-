package com.elink.assingmentmedisage.model

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:23
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "userTbl")
data class User(
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    val userId: Int? = null,

    @PrimaryKey
     @SerializedName("id")
    val id: Int? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String? = null,

    @ColumnInfo(name = "favorite")
    val favorite: String? = "N"

) : Serializable