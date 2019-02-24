package com.project.jogjatour.ui.home

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.jogjatour.R
import com.project.jogjatour.data.Destination
import com.project.jogjatour.ui.detail.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var wisata: MutableList<Destination> = ArrayList()


    override fun getItemCount(): Int {
        return wisata.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.destination_name_detail.text = wisata[position].namaPariwisata
        Picasso.with(holder.itemView.getContext()).load(wisata[position].gambarPariwisata)
            .into(holder.itemView.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("wisata", wisata[position])
            intent.putExtra("wisata_name", wisata[position].namaPariwisata)
            intent.putExtra("position", position)

            holder.itemView.context.startActivity(intent)
        }
    }


    class HomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

    fun setData(des: MutableList<Destination>) {
        this.wisata = des
        notifyDataSetChanged()
    }
}
