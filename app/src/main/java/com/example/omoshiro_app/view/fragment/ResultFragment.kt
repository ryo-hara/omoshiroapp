package com.example.omoshiro_app.view.fragment

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.omoshiro_app.R
import com.example.omoshiro_app.databinding.FragmentResultBinding
import com.example.omoshiro_app.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() = ResultFragment()
    }

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initializeUI()
    }

    private fun initializeUI(){
        binding.viewModel = viewModel

        binding.backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        viewModel.playSE(requireActivity(), R.raw.drum_roll)

        binding.blackView.let {
            it.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.Main).launch {
                delay(2400)
                it.visibility = View.INVISIBLE
            }
        }
        setImage()
    }

    private fun setImage() {
        val fileId = viewModel.divinationResult.value?.file_id
        val resourceId = getResources().getIdentifier(fileId, "drawable", context?.packageName);
        binding.image.setImageResource(resourceId)
    }
}