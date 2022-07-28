package com.elink.assingmentmedisage.interfaces

import com.elink.assingmentmedisage.model.User

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 19:04
 */
interface OnItemClickListener {
    fun onItemClickListener(data: User, position: Int)
}