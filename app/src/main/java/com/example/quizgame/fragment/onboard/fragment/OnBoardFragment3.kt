package com.example.quizgame.fragment.onboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentOnBoard3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment3 : Fragment() {

    private lateinit var binding:FragmentOnBoard3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoard3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()

    }

    private fun initBtn() {
        binding.imgNext.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, OnBoardFragment4())
                .addToBackStack(null)
                .commit()
        }

        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, OnBoardFragment2())
                .addToBackStack(null)
                .commit()
        }
    }

}