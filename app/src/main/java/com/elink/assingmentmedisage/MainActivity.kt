package com.elink.assingmentmedisage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.elink.assingmentmedisage.utilies.SharedPref
import com.elink.assingmentmedisage.utilies.Utility
import com.elink.assingmentmedisage.fragment.FavouritesFragement
import com.elink.assingmentmedisage.adapter.TabAdapter
import com.elink.assingmentmedisage.fragment.PostFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var userTV: TextView
    lateinit var logoutBtn: Button
    private val tabsTitle = mutableListOf<String>()
    private val tabsFrag = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // fragment  start region
        if (tabsTitle.isEmpty()) {
            tabsTitle.add("Post")
            tabsTitle.add("Favourites")
        }
        if (tabsFrag.isNullOrEmpty()) {
            tabsFrag.add(PostFragment())
            tabsFrag.add(FavouritesFragement())
        }
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tl_apps = findViewById<TabLayout>(R.id.tl_apps)
        val tabAdapter = TabAdapter(this, tabsFrag)
        viewPager.adapter = tabAdapter
        TabLayoutMediator(tl_apps, viewPager) { tab, position ->
            tab.text = tabsTitle[position]
        }.attach()

        userTV = findViewById(R.id.idTVUserName)
        val email = SharedPref.read(SharedPref.EMAIL_KEY, null) //read string in shared preference.
        userTV.setText("$email")
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                Utility.showAlertMessage(this, resources.getString(R.string.logout_msg)) {
                    SharedPref.write(SharedPref.EMAIL_KEY, "");//save string in shared preference.
                    SharedPref.write(SharedPref.PWD_KEY, "");//save int in shared preference.
                    finish()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}