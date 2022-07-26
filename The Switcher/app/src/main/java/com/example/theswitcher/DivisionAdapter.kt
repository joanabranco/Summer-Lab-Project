package com.example.theswitcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DivisionAdapter (private val divisionClick : DivisionClick, private val divisionList: List<DivisionData>) : RecyclerView.Adapter<DivisionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_division, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.divisionList[position]

        holder.apply {
            divisionText.text = item.name
            isOnSwitch.isChecked = item.mode
            itemView.setOnClickListener{
                divisionClick.clickDivision(position, 0)
            }
            isOnSwitch.setOnClickListener{
                divisionClick.clickDivision(position, 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.divisionList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val divisionText: TextView = itemView.findViewById(R.id.list_division_text)
        val isOnSwitch: Switch = itemView.findViewById(R.id.list_switch)
    }
}