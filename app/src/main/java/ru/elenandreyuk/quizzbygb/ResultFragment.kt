package ru.elenandreyuk.quizzbygb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.elenandreyuk.quizzbygb.databinding.FragmentResultBinding
import ru.elenandreyuk.quizzbygb.databinding.FragmentStartBinding

// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding?  = null
    private val binding get () = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater)
        binding.buttonStartAgain.setOnClickListener{
            parentFragmentManager.commit {
                replace<StartFragment>(R.id.action_ResultFragment_to_StartFragment)
            }
        }
             return inflater.inflate(R.layout.fragment_result, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correctAnswersCount = arguments?.getInt("correctAnswersCount") ?: 0

        val resultTextView: TextView = view.findViewById(R.id.result)
        resultTextView.text = "Количество правильных ответов: $correctAnswersCount"
    }


}