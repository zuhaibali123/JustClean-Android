package com.justclean.zuhaib.laundry.data


import androidx.room.Room
import android.content.Context

class RoomSupport private constructor(cntxt: Context) {

    private val DB_NAME = "assets_db"
    var database: Database
    val context: Context

    init {
        this.context = cntxt
        this.database = Room.databaseBuilder(context.applicationContext,
                Database::class.java, DB_NAME)
                .allowMainThreadQueries().build()
    }

    companion object {
        private var instance: RoomSupport? = null

        fun createInstance(context: Context) {
            if (instance != null) {
                println("Room Instance already exist overriding")
            }
            instance = RoomSupport(context)
        }

        fun getInstance(): RoomSupport {
            if (instance == null) {
                throw IllegalArgumentException("Roomdatabase not initialied ..")
            }
            return instance!!
        }
    }

}