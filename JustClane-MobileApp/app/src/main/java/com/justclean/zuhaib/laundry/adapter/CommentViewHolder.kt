package com.justclean.zuhaib.laundry.adapter


import androidx.recyclerview.widget.RecyclerView
import com.justclean.zuhaib.laundry.databinding.PostCommentArticleBinding
import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.model.FavModel

class CommentViewHolder(var itemBinding: PostCommentArticleBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun populateData(mostview: CommentModel){
        itemBinding.titleTxtView.text = mostview.title
        itemBinding.txtDesTxtView.text = mostview.body
    }

    fun favpopulateData(mostview: FavModel){
        itemBinding.titleTxtView.text = mostview.title
        itemBinding.txtDesTxtView.text = mostview.body
    }


}