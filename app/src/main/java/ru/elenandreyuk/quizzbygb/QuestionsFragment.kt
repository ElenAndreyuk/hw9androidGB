package ru.elenandreyuk.quizzbygb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import ru.elenandreyuk.quizzbygb.databinding.FragmentQuestionsBinding
import ru.elenandreyuk.quizzbygb.databinding.FragmentStartBinding


class QuestionsFragment : Fragment() {
    private var _binding: FragmentQuestionsBinding?  = null
    private val binding get () = _binding!!

    private fun calculateCorrectAnswers(): Int {
        var correctAnswers = 0
        if (isAnswerCorrect(binding.radioGroupQuestion1.checkedRadioButtonId, R.id.answer2question1)) {
            correctAnswers++
        }
        if (isAnswerCorrect(binding.radioGroupQuestion2.checkedRadioButtonId, R.id.answer3question2)) {
            correctAnswers++
        }
        if (isAnswerCorrect(binding.radioGroupQuestion3.checkedRadioButtonId, R.id.answer3question3)) {
            correctAnswers++
        }
        if (isAnswerCorrect(binding.radioGroupQuestion4.checkedRadioButtonId, R.id.answer1question4)) {
            correctAnswers++
        }
        if (isAnswerCorrect(binding.radioGroupQuestion5.checkedRadioButtonId, R.id.answer2question5)) {
            correctAnswers++
        }

        return correctAnswers
    }
    private fun isAnswerCorrect(selectedAnswerId: Int, correctAnswerId: Int): Boolean {
        return selectedAnswerId == correctAnswerId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionsBinding.inflate(inflater)
        binding.buttonBack.setOnClickListener{
            parentFragmentManager.popBackStack()

        }
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSend.setOnClickListener {
            val correctAnswers = calculateCorrectAnswers()
            val bundle = Bundle().apply {
                putInt("correctAnswersCount", correctAnswers)
            }
            findNavController().navigate(R.id.action_QuestionsFragment_to_ResultFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}