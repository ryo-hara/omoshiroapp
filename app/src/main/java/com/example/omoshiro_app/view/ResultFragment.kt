package com.example.omoshiro_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.omoshiro_app.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() = ResultFragment()
    }

    private lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
        setTestImage()
    }

    private fun setTestImage() {
        val resourceNameBase = "result_image_"
        val range = (1..20)
        val randNum = range.random()
        val resourceName = resourceNameBase + (if(randNum < 9 ) "0" else "") + randNum;

        var resourceId = getResources().getIdentifier(resourceName, "drawable", context?.packageName);
        binding.image.setImageResource(resourceId)
    }
}