package com.elink.assingmentmedisage.adapter

/**
 * Created by Nitin Sabale
 * @since 27-07-2022. 18:33
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elink.assingmentmedisage.R
import com.elink.assingmentmedisage.interfaces.OnItemClickListener
import com.elink.assingmentmedisage.model.User


class UsersAdapter(private val context: Context, private var list: MutableList<User>,val OnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.user_row,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list.get(position)
        holder.title?.text = "Title | "+user.title
        holder.info1?.text = user?.id.toString() + " | " + user?.userId.toString()
        holder.info2?.text = user?.body

        if (user?.favorite.equals("Y")){
            holder.favorite?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
        } else {
            holder.favorite?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_un_favorite_24))
        }

        holder.favorite?.setOnClickListener {
                if (user.favorite == "N") {
                    holder.favorite?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                    val model = User(user.userId,user.id,user.title,user.body,"Y")
                    OnItemClickListener.onItemClickListener(model, position)
                } else {
                    holder.favorite?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_un_favorite_24))
                    val model = User(user.userId,user.id,user.title,user.body,"N")
                    OnItemClickListener.onItemClickListener(model, position)
                }
            }
        }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.txt_title)
        val info1 = view.findViewById<TextView>(R.id.txt_user_info1)
        val info2 = view.findViewById<TextView>(R.id.txt_user_info2)
        val favorite = view.findViewById<ImageView>(R.id.iv_favorite)
    }

    public fun setAdapterData(userList: MutableList<User>) {
        list = userList
        notifyDataSetChanged()
    }
}