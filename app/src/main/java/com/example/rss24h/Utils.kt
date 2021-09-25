package com.example.rss24h

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Utils {
    //cac phuong thuc, thuoc tinh static năm trong companion object
    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, link: String){
            if (link == null || link.equals("")){
                Glide.with(imageView)
                    .load(R.drawable.arshavin)
                    .into(imageView)
            }else{
                Glide.with(imageView)
                    .load(link)
                    .placeholder(R.drawable.arshavin)
                    .error(R.drawable.arshavin)
                    .into(imageView)
            }
        }
    }
}