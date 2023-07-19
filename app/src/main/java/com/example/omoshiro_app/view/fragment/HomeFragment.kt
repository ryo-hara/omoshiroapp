package com.example.omoshiro_app.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omoshiro_app.viewmodel.HomeViewModel
import com.example.omoshiro_app.databinding.HomeFragmentBinding
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.omoshiro_app.R

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
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        binding.DivinationButton.setOnClickListener{
            viewModel.requestDivination( onSuccessAction = {
                transactionResult()
            }, onFailureAction = {
                showSnackBar("遷移に失敗しました")
            })
        }
    }

    private fun transactionResult() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.replace(
            R.id.fragment_container_view,
            ResultFragment.newInstance()
        )
        fragmentTransaction.commit()
    }

    private fun showSnackBar(text: String){
        Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }
}