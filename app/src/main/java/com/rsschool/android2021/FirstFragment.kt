package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var etMinValue: EditText? = null
    private var etMaxValue: EditText? = null
    private var fragTrans: FragmentTransaction?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragTrans = context as FragmentTransaction
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        etMinValue = view.findViewById(R.id.min_value)
        etMaxValue = view.findViewById(R.id.max_value)
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        generateButton?.setOnClickListener {
            val min =
                if (etMinValue?.text.toString().isNotEmpty()) etMinValue?.text.toString().toInt()
                else 0
            val max =
                if (etMaxValue?.text.toString().isNotEmpty()) etMaxValue?.text.toString().toInt()
                else 0
            if (min < max && max > 0 && min > 0) {
                fragTrans?.onGenerate(min, max)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}