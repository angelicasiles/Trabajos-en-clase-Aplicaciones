package com.marvin.a08_navigation_01.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.marvin.a08_navigation_01.R
import com.marvin.a08_navigation_01.databinding.FragmentAboutFragmentBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class fragment_about : Fragment() {
    // TODO: Rename and change types of parameters


    private var _binding: FragmentAboutFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_about_fragment, container, false)

        var text_about = root.findViewById<TextView>(R.id.text_about)
        text_about.setText(R.string.message_home)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_about.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_about().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
















