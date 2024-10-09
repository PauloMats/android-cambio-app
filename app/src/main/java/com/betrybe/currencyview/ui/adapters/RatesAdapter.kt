package com.betrybe.currencyview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.currencyview.R

class RatesAdapter(private val rates: Map<String, Double>) : RecyclerView.Adapter<RatesAdapter.RateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return RateViewHolder(view)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val currency = rates.keys.elementAt(position)
        val rate = rates[currency]
        holder.tvCurrency.text = currency
        holder.tvRate.text = rate.toString()
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)
        val tvRate: TextView = itemView.findViewById(R.id.tvRate)
    }
}
