package com.stc.apetask.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stc.apetask.R
import com.stc.apetask.data.models.factsResponse.FactsData


class HomeDetailsAdapter(
    private val context: Context,
    private val productDetailsList: List<FactsData>
) :
    RecyclerView.Adapter<HomeDetailsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_details_item, parent, false)

        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = productDetailsList[position]

        item.imageHref?.let {
            val imgUrl = it.replace("http:", "https:")
            Picasso.with(context)
                .load(imgUrl)
                .into(holder.productIv)
        }
        item.title.let {
            holder.title.text = it
        }

        item.description.let {
            holder.description.text = it
        }
    }

    override fun getItemCount(): Int {
        return productDetailsList.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.title)
        val description: AppCompatTextView = itemView.findViewById(R.id.description)
        val productIv: AppCompatImageView = itemView.findViewById(R.id.iv)
    }
}
