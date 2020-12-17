package com.justclean.zuhaib.laundry.views.fragment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.justclean.zuhaib.laundry.adapter.PostCommentListAdapter


import com.justclean.zuhaib.laundry.databinding.PostFragmentBinding
import com.justclean.zuhaib.laundry.data.RoomSupport


import com.justclean.zuhaib.laundry.model.CommentModel
import com.justclean.zuhaib.laundry.viewmodel.ListViewModel
import com.justclean.zuhaib.laundry.utils.Contract
import com.justclean.zuhaib.laundry.views.PostCommentDetailScreen


class PostCommentFragment : Fragment()  {

    lateinit var viewModel: ListViewModel
     private var mostViewArticleAdapter: PostCommentListAdapter = PostCommentListAdapter(::onCommentCLicked)

    private lateinit var binding: PostFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
         binding = PostFragmentBinding.inflate(layoutInflater)
        instantiateTheViewModel()
        return binding.root
    }

    private fun instantiateTheViewModel(){

        intilizeAdapter()
        viewModel  = ViewModelProviders.of(this).get(ListViewModel::class.java)
        if (isConnected(context)) {
            viewModel.fetchCommentData()
            binding.loadingView.visibility = View.VISIBLE
            observeViewModel()
        }else{
            var commentList = RoomSupport.getInstance().database.getCommentListDao().getCommentList()
            mostViewArticleAdapter.postCommentList = commentList!!
            mostViewArticleAdapter.notifyDataSetChanged()
            snakBar()
        }
    }

    fun intilizeAdapter(){
        binding.commentList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(binding.commentList.context, LinearLayoutManager(context).orientation))
            adapter = mostViewArticleAdapter

        }
    }




        fun observeViewModel(){


        viewModel.postCommentList.observe(this, Observer { mostViewd ->
            mostViewd?.let { list ->
                binding.loadingView.visibility = View.GONE
                mostViewArticleAdapter.postCommentList = list!!
                mostViewArticleAdapter.notifyDataSetChanged()

            }
        })

         viewModel.postCommentLoadError.observe(this, Observer { isError ->
             binding.loadingView.visibility = View.GONE

         })


    }


        fun onCommentCLicked(mosViewArticle: CommentModel){
        var intent  = Intent(context, PostCommentDetailScreen::class.java)
        intent.putExtra(Contract.title, mosViewArticle.title)
        intent.putExtra(Contract.body, mosViewArticle.body)
            intent.putExtra(Contract.id, mosViewArticle.id)
            intent.putExtra(Contract.isCommingFromFave, false)
            startActivityForResult(intent, 100)
        }


        fun snakBar(){
      Toast.makeText(context,"No internet",Toast.LENGTH_LONG).show()
        }


    fun isConnected(context: Context?): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm!!.activeNetworkInfo != null && cm!!.activeNetworkInfo.isConnected

    }





}