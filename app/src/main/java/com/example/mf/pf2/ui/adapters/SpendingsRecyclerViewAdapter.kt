package com.example.mf.pf2.ui.adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mf.pf2.R
import com.example.mf.pf2.Spendings
import com.example.mf.pf2.utils.Constants
import com.example.mf.pf2.utils.toCurrency
import com.example.mf.pf2.utils.toSimpleDateFormat
import kotlinx.android.synthetic.main.fragment_spendings.view.*
import java.util.*


class SpendingsRecyclerViewAdapter
    : RecyclerView.Adapter<SpendingsRecyclerViewAdapter.ViewHolder>() {

    private var expandedPosition = -1
    private var spendings: List<Spendings> = ArrayList()
    private lateinit var context: Context
    private val drawables = Constants.mockThumbnails

    init {
        spendings = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_spendings, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = spendings[position]
        initViewHolder(holder, item, position)
    }

    private fun SpendingsRecyclerViewAdapter.initViewHolder(holder: ViewHolder, item: Spendings, position: Int) {
        with(holder.mView) {
            amount.text = item.amount.toCurrency()
            product.text = item.product
            date.text = item.date.toSimpleDateFormat()

            //TODO Categorize Item
            Glide.with(context).load(drawables[Random().nextInt(3)]).into(img_type)

            //Expanded Item
            expandedItem.visibility = setExpanded(position)
            setOnClickListener({ onExpand(position) })
        }
    }
    fun addSpendings(list: List<Spendings>) {
        spendings += list
        notifyDataSetChanged()
    }

    private fun onExpand(position: Int) {
        expandedPosition = if (position == expandedPosition) -1 else position
        notifyItemChanged(expandedPosition)
    }

    private fun setExpanded(position: Int): Int {
        if (position == expandedPosition) return View.VISIBLE
        return View.GONE
    }


    override fun getItemCount(): Int = spendings.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

}
