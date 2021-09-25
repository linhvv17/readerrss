package com.example.rss24h

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rss24h.databinding.ItemDataBinding

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
//    class RSSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener, View.OnLongClickListener{
//        //item là toàn bộ item_data
//        var ivImg: ImageView
//        var tvTitle: TextView
//        var tvDescription: TextView
//        var tvpubDate: TextView
//        private var itemClickListener:ItemClickListener? = null
//        init {
//            ivImg = itemView.img
//            tvTitle = itemView.tvTitle
//            tvDescription = itemView.tvDescription
//            tvpubDate= itemView.tvPubDate
//            itemView.setOnClickListener(this)
//            itemView.setOnLongClickListener(this)
//        }
//
//        fun setItemClickListener(itemClickListener: ItemClickListener?) {
//            this.itemClickListener = itemClickListener
//        }
//
//        override fun onClick(v: View?) {
//            itemClickListener!!.onClick(v, adapterPosition,false)
//        }
//
//        override fun onLongClick(v: View?): Boolean {
//            itemClickListener!!.onClick(v, adapterPosition,true)
//            return true
//        }
//    }
    class RSSViewHolder : RecyclerView.ViewHolder {
    //Sử dụng dataBinding
    //thêm dataBinding { enabled = true}
        val binding: ItemDataBinding
        constructor(binding: ItemDataBinding): super(binding.root){
            this.binding = binding
        }
        private var itemClickListener:ItemClickListener? = null
        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
        //lay context tu group view vi tat ca cac loai view deu co context
        // vi co context moi hien thi được
        val binding =  ItemDataBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
//        val itemView = LayoutInflater.from(parent.context).inflate(
//            R.layout.item_data, parent, false
//        )
        return RSSViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rssDatas.size
    }

    //do du lieu len itemview(viewHolder)
    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {//vào khi cuộn recyclerview
        //val data = rssDatas.get(position)
//        holder.binding.tvTitle.text = data.title
//        holder.binding.tvPubDate.text = data.pubDate
//        holder.binding.tvDescription.text = data.description
        holder.binding.itemData = rssDatas.get(position) //đổ dữ liệu lên XML
        if (rssDatas.get(position).isClick){
            holder.itemView.setBackgroundColor(Color.RED)
        }else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
        holder.itemView.setOnClickListener ({
            rssDatas.get(position).isClick = rssDatas.get(position).isClick
            notifyItemChanged(position)
        })
        //holder.binding.executePendingBindings()//sét giá trị lần lượt cho từng view một
//        Glide.with(holder.binding.img)
//            .load(data.img)
//            .placeholder(R.drawable.arshavin)
//            .error(R.drawable.arshavin)
//            .into(holder.binding.img)
//        holder.setItemClickListener(object : ItemClickListener {
//            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
////                Toast.makeText(context, " Clicked ", Toast.LENGTH_SHORT).show();
//               onBrowseClick(view)
//            }
//            fun onBrowseClick(v: View?) {
//                val url = data.linkOP
//                val uri = Uri.parse(url)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                    startActivity(context, intent, null)
//            }
//        })
    }

}