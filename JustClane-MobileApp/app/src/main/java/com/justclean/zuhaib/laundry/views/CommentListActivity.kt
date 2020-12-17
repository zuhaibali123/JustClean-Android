package com.justclean.zuhaib.laundry.views


import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justclean.laundry.adapter.SectionsPagerAdapter
import com.justclean.laundry.utils.NetworkChangeReceiver
import com.justclean.zuhaib.laundry.R
import com.justclean.zuhaib.laundry.views.fragment.FavCommentFragment
import com.justclean.zuhaib.laundry.views.fragment.PostCommentFragment

import kotlinx.android.synthetic.main.activity_main.*

class CommentListActivity : AppCompatActivity(){
    lateinit var  mNetworkReceiver: BroadcastReceiver

    var postFragment = PostCommentFragment()
    var favFragment = FavCommentFragment();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()

        mNetworkReceiver = NetworkChangeReceiver()
        registerNetworkBroadcast()
    }


    private fun setUpViewPager() {
        var  adapter = SectionsPagerAdapter(supportFragmentManager)
        adapter.addFragment(postFragment, "Post Comment")
        adapter.addFragment(favFragment, "Fav Comment")
        view_pager.setAdapter(adapter)
        tabs.setupWithViewPager(view_pager)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        favFragment.updateData()
    }

    private fun registerNetworkBroadcast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChanges()
    }



}
