package com.marvin.a08_navigation_02.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marvin.a08_navigation_02.databinding.FragmentGalleryBinding
import com.marvin.a08_navigation_02.R

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var text_dashboard = root.findViewById<TextView>(R.id.text_gallery)
        text_dashboard.setText(R.string.message_dashboard)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}