package com.example.theswitcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.theswitcher.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding

    lateinit var divisionListView : ListView
    private lateinit var divisionList : List<DivisionData>

    lateinit var division: String
    var isOn: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        divisionList = listOf(DivisionData("Kitchen", false), DivisionData("Living Room", false), DivisionData("Master Bedroom", false), DivisionData("Guest's Bedroom", false))

        initListView ()
        var adapterDiv = DivisionAdapter(this.requireContext(), divisionList as ArrayList<DivisionData>)
        divisionListView.apply {
            adapter = adapterDiv
        }

        setupButton()

        return binding.root
    }

    private fun initListView() {
        this.divisionListView = binding.root.findViewById(R.id.division_list)
    }

    private fun setupButton() {
        binding.apply {
            kitchenSwitch.setOnClickListener {changeLight(0)}
            livingRoomSwitch.setOnClickListener {changeLight(1)}
            masterBedroomSwitch.setOnClickListener {changeLight(2)}
            guestBedroomSwitch.setOnClickListener {changeLight(3)}

            kitchenText.setOnClickListener {printDivision(0)}
            livingRoomText.setOnClickListener{printDivision(1)}
            masterBedroomText.setOnClickListener{printDivision(2)}
            guestBedroomText.setOnClickListener{printDivision(3)}
        }
    }

    private fun changeLight(i : Int) {
        if (divisionList[i].mode) divisionList[i].mode == true
        else divisionList[i].mode == false
    }

    private fun printDivision(i : Int) {
        division = divisionList[i].name
        isOn = divisionList[i].mode
        view?.let {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToDetailsFragment(division, isOn))
        }
    }
}