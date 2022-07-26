package com.example.theswitcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.theswitcher.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var division: String
    private var isOn: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val args = arguments?.let { DetailsFragmentArgs.fromBundle(it) }
        args?.let {
            division = it.division
            isOn = it.isOn
        }

        "Your $division light is".also { binding.divisionText.text = it }
        binding.apply{
            if (!isOn) {
                onOffText.text = "OFF"
                lightOnImage.visibility = INVISIBLE
                lightOffImage.visibility = VISIBLE
            }
                else {
                    onOffText.text = "ON"
                    lightOnImage.visibility = VISIBLE
                    lightOffImage.visibility = INVISIBLE
            }
        }
        return binding.root
    }



}