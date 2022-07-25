package com.example.theswitcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Switch
import android.widget.TextView

class DivisionAdapter(private val context: Context, private val dataSource: ArrayList<DivisionData>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_item_division, parent, false) //R.layout.fragment_title

        val divisionTextView = rowView.findViewById(R.id.kitchen_text) as TextView
        val isOnSwitch = rowView.findViewById(R.id.kitchen_switch) as Switch

        val divisionData = getItem(position) as DivisionData
        divisionTextView.text = divisionData.name
        isOnSwitch.showText = divisionData.mode

        return inflater.inflate(R.layout.list_item_division, parent, false)
    }
 }