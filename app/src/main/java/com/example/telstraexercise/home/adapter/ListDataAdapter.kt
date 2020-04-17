package com.example.telstraexercise.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.telstraexercise.R
import com.example.telstraexercise.home.model.ListData
import kotlinx.android.synthetic.main.listitem.view.*

/** class for binding the data in recycler view  */
class ListDataAdapter(private var context: Context, private var listData: ListData) :
    RecyclerView.Adapter<ListDataAdapter.ListDataAdapterViewHolder>() {

    /** inflating view */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataAdapterViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.listitem, parent, false)
        return ListDataAdapterViewHolder(view)
    }

    /** return item count */
    override fun getItemCount(): Int {
        return listData.listItem.size
    }

    /** evaluating data for each row */
    override fun onBindViewHolder(holder: ListDataAdapterViewHolder, position: Int) {
        if (!listData.listItem[position].title.isNullOrEmpty()) {
            holder.title?.text = listData.listItem[position].title
        }
        if (!listData.listItem[position].description.isNullOrEmpty()) {
            holder.description?.text = listData.listItem[position].description
        }
        if (!listData.listItem[position].imageHref.isNullOrEmpty()) {
            Glide.with(holder.image.context)
                .load(listData.listItem[position].imageHref)
                .placeholder(R.drawable.default_image_thumbnail)
                .error(R.drawable.default_image_thumbnail)
                .fallback(R.drawable.default_image_thumbnail)
                .into(holder.image)
        }
    }

    /**get position of item clicked*/
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class ListDataAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = itemView.title
        var description: TextView? = itemView.description
        var image: ImageView = itemView.listImage
    }
}