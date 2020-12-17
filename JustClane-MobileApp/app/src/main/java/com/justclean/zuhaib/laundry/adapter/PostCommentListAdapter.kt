package com.justclean.zuhaib.laundry.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import com.justclean.zuhaib.laundry.databinding.PostCommentArticleBinding
import com.justclean.zuhaib.laundry.model.CommentModel


class PostCommentListAdapter(var callback:(CommentModel)->Unit) : RecyclerView.Adapter<CommentViewHolder>() {

    var postCommentList: List<CommentModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(PostCommentArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = postCommentList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {

        holder.populateData(postCommentList[position])


        holder.itemBinding.root.setOnClickListener {

            callback(postCommentList[position])

        }
    }
}