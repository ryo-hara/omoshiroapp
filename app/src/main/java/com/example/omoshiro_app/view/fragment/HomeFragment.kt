package com.example.omoshiro_app.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.LinkMovementMethod
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
import com.example.omoshiro_app.view.ProgressView
import android.content.Intent
import android.net.Uri


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel
    private var progressView: ProgressView? = null

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
        initUI()
    }


    private fun initUI() {
        binding.let {
            it.githubChip.setOnClickListener{
                openLink(getString(R.string.git_hub_link))
            }
            it.blueArchiveChip.setOnClickListener{
                openLink(getString(R.string.blue_archive_link))
            }
        }

        binding.DivinationButton.setOnClickListener{
            createProcessView()
            viewModel.requestDivination( onSuccessAction = {
                transactionResult()
                deleteProgressView()
            }, onFailureAction = {
                deleteProgressView()
                showSnackBar("遷移に失敗しました")
            })
        }
    }

    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }

    private fun transactionResult() {
        val fragmentManager: FragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().addToBackStack(null)
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)
            .replace(
                R.id.fragment_container_view,
                ResultFragment.newInstance())
            .commit()
    }

    private fun showSnackBar(text: String){
        Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }

    private fun createProcessView(){
        binding.rootConstraint .let {
            progressView = ProgressView(requireActivity())
            it.addView(progressView)
            progressView?.bringToFront()
        }
    }

    private fun deleteProgressView(){
        if (progressView != null) {
            binding.rootConstraint.removeView(progressView)
        }
    }

}