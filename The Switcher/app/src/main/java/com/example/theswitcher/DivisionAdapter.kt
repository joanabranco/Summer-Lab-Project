package com.example.theswitcher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Switch
import android.widget.TextView

class DivisionAdapter(private val divisionClick: DivisionClick, private val context: Context, private val dataSource: List<DivisionData>) : BaseAdapter() {

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
        val rowView = inflater.inflate(R.layout.list_item_division, parent, false)

        val divisionTextView = rowView.findViewById(R.id.list_division_text) as TextView
        val isOnSwitch = rowView.findViewById(R.id.list_switch_switch) as Switch
        val cell = rowView.findViewById(R.id.item_division) as View

        val divisionData = getItem(position) as DivisionData

        cell.setOnClickListener{
            divisionClick.clickDivision(position, 0)
        }

        isOnSwitch.setOnClickListener{
            divisionClick.clickDivision(position, 1)
        }

        divisionTextView.text = divisionData.name
        isOnSwitch.isChecked = divisionData.mode

        return rowView
    }
 }


