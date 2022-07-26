package com.example.theswitcher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.theswitcher.databinding.FragmentTitleBinding


class TitleFragment : Fragment() , DivisionClick{
    lateinit var binding: FragmentTitleBinding

    var divisionList : List<DivisionData> = listOf(DivisionData("Kitchen", false), DivisionData("Living Room", false), DivisionData("Master Bedroom", false), DivisionData("Guest's Bedroom", false))
    lateinit var divisionListView : ListView

    lateinit var division: String
    var isOn: Boolean = false

    lateinit var adapterDiv : DivisionAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        initListView ()

        adapterDiv = DivisionAdapter(this, this.requireContext(), divisionList as List<DivisionData>)

        divisionListView.apply {
            adapter = adapterDiv
        }

        return binding.root
    }

    private fun initListView() {
        this.divisionListView = binding.root.findViewById(R.id.division_list)
    }

    override fun clickDivision(position: Int, type: Int) {
        if (type == 0) printDivision(position)
        if (type == 1) changeLight(position)

        adapterDiv.notifyDataSetChanged()
    }

    private fun changeLight(i : Int) {
        if (!divisionList[i].mode) divisionList[i].mode = true
        else divisionList[i].mode = false
    }

    private fun printDivision(i : Int) {
        division = divisionList[i].name
        isOn = divisionList[i].mode
        view?.let {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToDetailsFragment(division, isOn))
        }
    }
}