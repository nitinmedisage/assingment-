package com.elink.assingmentmedisage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elink.assingmentmedisage.R
import com.elink.assingmentmedisage.interfaces.OnItemClickListener
import com.elink.assingmentmedisage.adapter.UsersAdapter
import com.elink.assingmentmedisage.model.User
import com.elink.assingmentmedisage.viewmodel.UserViewModel
import com.elink.assingmentmedisage.viewmodelsfactories.UserViewModelFactory
import org.jetbrains.anko.doAsync

class FavouritesFragement : Fragment(), OnItemClickListener {
    lateinit var recyclerView: RecyclerView;
    private lateinit var listUsers: MutableList<User>
    private lateinit var adapter: UsersAdapter
    private lateinit var userViewModel :UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites_fragement, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        listUsers = mutableListOf<User>()
        adapter = UsersAdapter(requireActivity(), listUsers,this)
        recyclerView.adapter = adapter

        val applicationUser = requireActivity().application
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(requireActivity(),applicationUser)).get(UserViewModel::class.java)
        userViewModel.allFavoriteUser.observe(requireActivity(), Observer { it ->
            listUsers.clear()
            it?.let { listUsers.addAll(it) }
            adapter.setAdapterData(listUsers)
        })

    }

    override fun onItemClickListener(user: User, position: Int) {
        doAsync {
            userViewModel.update(user.favorite!!, user.id!!)
        }
    }

}