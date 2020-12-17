package com.justclean.zuhaib.laundry.views

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.justclean.zuhaib.laundry.R
import com.justclean.zuhaib.laundry.data.RoomSupport
import com.justclean.zuhaib.laundry.model.FavModel
import com.justclean.zuhaib.laundry.utils.Contract
import kotlinx.android.synthetic.main.post_comment_article.*


class PostCommentDetailScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_comment_article)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        var title = intent.getStringExtra(Contract.title)
        var body = intent.getStringExtra(Contract.body)

        titleTxtView.text = title
        txtDesTxtView.text = body

        if (!intent.getBooleanExtra(Contract.isCommingFromFave,false)) {
            favbtn.visibility = View.VISIBLE
            var favid = intent.getIntExtra(Contract.id, 0)
            favbtn.visibility = View.VISIBLE
            favbtn.setOnClickListener {
                RoomSupport.getInstance().database.getCommentListDao().addFav(FavModel(favid, title, body))
                Toast.makeText(this@PostCommentDetailScreen, resources.getString(R.string.favcomment), Toast.LENGTH_LONG).show()

            }
        }

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return  true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}