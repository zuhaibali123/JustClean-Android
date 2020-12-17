package com.justclean.zuhaib.laundry.views.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager



import com.justclean.zuhaib.laundry.adapter.FavListAdapter
import com.justclean.zuhaib.laundry.data.RoomSupport
import com.justclean.zuhaib.laundry.databinding.FavFragmentBinding

import com.justclean.zuhaib.laundry.model.FavModel
import com.justclean.zuhaib.laundry.utils.Contract
import com.justclean.zuhaib.laundry.views.PostCommentDetailScreen


class FavCommentFragment : Fragment()  {


    private var mostViewArticleAdapter: FavListAdapter = FavListAdapter(::onCommentCLicked)

    private lateinit var binding: FavFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FavFragmentBinding.inflate(layoutInflater)
        instantiateTheViewModel()
        return binding.root
    }



    private fun instantiateTheViewModel(){
        intilizeAdapter()
        var commentList = RoomSupport.getInstance().database.getCommentListDao().getFavModel()
        mostViewArticleAdapter.postCommentList = commentList!!
        mostViewArticleAdapter.notifyDataSetChanged()
    }

    fun intilizeAdapter(){
        binding.favRc.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(binding.favRc.context, LinearLayoutManager(context).orientation))
            adapter = mostViewArticleAdapter
        }
    }



    fun onCommentCLicked(mosViewArticle: FavModel){
        var intent  = Intent(context, PostCommentDetailScreen::class.java)
        intent.putExtra(Contract.title, mosViewArticle.title)
        intent.putExtra(Contract.body, mosViewArticle.body)
        intent.putExtra(Contract.id, mosViewArticle.id)
        intent.putExtra(Contract.isCommingFromFave, true)
        startActivity(intent)
    }



    fun updateData(){
        instantiateTheViewModel()

    }





}