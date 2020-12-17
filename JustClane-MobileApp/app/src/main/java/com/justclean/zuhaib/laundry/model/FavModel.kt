package com.justclean.zuhaib.laundry.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class FavModel(@PrimaryKey val id: Int, val title: String?, val body: String?,var isSync:Boolean = false){}