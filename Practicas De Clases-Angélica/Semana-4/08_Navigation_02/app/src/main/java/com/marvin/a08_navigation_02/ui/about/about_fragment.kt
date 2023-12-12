package com.marvin.a08_navigation_02.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvin.a08_navigation_02.R
import android.widget.TextView
import com.marvin.a08_navigation_02.databinding.AboutFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [about_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class about_fragment : Fragment() {

    private var _binding: AboutFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.about_fragment, container, false)

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
         * @return A new instance of fragment about_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            about_fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}