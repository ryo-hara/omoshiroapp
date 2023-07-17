package com.example.omoshiro_app.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omoshiro_app.viewmodel.HomeViewModel
import com.example.omoshiro_app.databinding.HomeFragmentBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.DivinationButton.setOnClickListener{
            showSnackBar("ボタンが押されました")
        }
    }

    private fun showSnackBar(text: String){
        Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }

}