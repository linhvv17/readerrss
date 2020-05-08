package com.example.rss24h

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.itemdata.view.*


class RSSAdapter : RecyclerView.Adapter<RSSAdapter.RSSViewHolder> {
    private var rssDatas: MutableList<RSSData>
    private val context: Context
    constructor(
        rssDatas: MutableList<RSSData>,
        context: Context
    ) {
        this.rssDatas = rssDatas
        this.context = context
    }
    class RSSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{
        //item là toàn bộ itemdata
        var ivImg: ImageView
        var tvTitle: TextView
        var tvDescription: TextView
        var tvpubDate: TextView
        private var itemClickListener:ItemClickListener? = null
        init {
            ivImg = itemView.img
            tvTitle = itemView.tvTitle
            tvDescription = itemView.tvDescription
            tvpubDate= itemView.tvPubDate
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            itemClickListener!!.onClick(v, adapterPosition,false)
        }

        override fun onLongClick(v: View?): Boolean {
            itemClickListener!!.onClick(v, adapterPosition,true)
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //lay context tu group view vi tat ca cac loai view deu co context
        // vi co context moi hien thi được
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.itemdata, parent, false
        )
        return RSSViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rssDatas.size
    }

    //do du lieu len itemview(viewHolder)
    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {
        val data = rssDatas.get(position)
        holder.tvTitle.text = data.title
        holder.tvDescription.text = data.description
        holder.tvpubDate.text = data.pubDate
        Glide.with(holder.ivImg)
            .load(data.img)
            .placeholder(R.drawable.iconnew)
            .error(R.drawable.iconnew)
            .into(holder.ivImg)
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
//                Toast.makeText(context, " Clicked ", Toast.LENGTH_SHORT).show();
               onBrowseClick(view)
            }
            fun onBrowseClick(v: View?) {
                val url = data.linkOP
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(context, intent, null)
            }
        })
    }

}