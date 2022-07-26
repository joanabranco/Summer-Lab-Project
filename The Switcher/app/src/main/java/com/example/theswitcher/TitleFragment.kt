package com.example.theswitcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theswitcher.databinding.FragmentTitleBinding


class TitleFragment : Fragment() , DivisionClick{
    lateinit var binding: FragmentTitleBinding

    var divisionList : List<DivisionData> = listOf(DivisionData("Kitchen", false), DivisionData("Living Room", false), DivisionData("Master Bedroom", false), DivisionData("Guest's Bedroom", false))
    lateinit var divisionRecycler : RecyclerView

    lateinit var division: String
    var isOn: Boolean = false

    lateinit var adapterDiv : DivisionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        initView ()

        adapterDiv = DivisionAdapter(this, divisionList)
        divisionRecycler.layoutManager = LinearLayoutManager(this.requireContext())
        divisionRecycler.apply {
            adapter = adapterDiv
        }

        return binding.root
    }

    private fun initView() {
        this.divisionRecycler = binding.root.findViewById(R.id.division_recycler)
    }

    override fun clickDivision(position: Int, type: Int) {
        if (type == 0) printDivision(position)
        if (type == 1) changeLight(position)
    }

    private fun changeLight(i : Int) {
        divisionList[i].mode = !divisionList[i].mode
    }

    private fun printDivision(i : Int) {
        division = divisionList[i].name
        isOn = divisionList[i].mode
        view?.let {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToDetailsFragment(division, isOn))
        }
    }
}