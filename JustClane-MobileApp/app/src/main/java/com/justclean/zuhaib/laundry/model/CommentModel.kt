package com.justclean.zuhaib.laundry.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CommentModel(@PrimaryKey val id: Int,val title: String?, val body: String?){}