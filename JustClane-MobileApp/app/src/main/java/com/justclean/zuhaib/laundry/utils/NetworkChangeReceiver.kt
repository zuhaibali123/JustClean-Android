package com.justclean.laundry.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.justclean.zuhaib.laundry.data.RoomSupport


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (checkInternet(context)) {

            apiCallForFav(context)
        }
    }

    fun apiCallForFav(context: Context){

        var unsynchlist =  RoomSupport.getInstance().database.getCommentListDao().getUnSyncFavItem(false)
        if(unsynchlist.size>0){
            Toast.makeText(context, "Network Available Sych you fav item  ", Toast.LENGTH_LONG).show()
        }

        //Now I got all the unsync data from table. If Api supports multiple data upload. It can be done in one call or We can loop on it.

        // After each success call we will update the local sync check to true
        unsynchlist.forEach {
            it.isSync = true
            RoomSupport.getInstance().database.getCommentListDao().updateFav(it)
        }

    }

    fun checkInternet(context: Context): Boolean {
        val serviceManager = ServiceManager(context)
        return if (serviceManager.isNetworkAvailable) {
            true
        } else {
            false
        }
    }
}
