package com.example.quizgame.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentLoseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoseFragment : Fragment() {

    private lateinit var binding:FragmentLoseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgMenu.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}