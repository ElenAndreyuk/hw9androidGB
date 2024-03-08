package ru.elenandreyuk.quizzbygb

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.elenandreyuk.quizzbygb.databinding.FragmentQuestionsBinding



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
        binding.headerQuestions.animate().apply {
            duration = 3000
            alpha(  1.0f)
        }


        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonSend.setOnClickListener {
//            val correctAnswers = calculateCorrectAnswers()
//            val bundle = Bundle().apply {
//                putInt("correctAnswersCount", correctAnswers)
//            }
//            findNavController().navigate(R.id.action_QuestionsFragment_to_ResultFragment, bundle)
//        }
//        requireActivity().window.enterTransition = Slide(Gravity.RIGHT)
//        requireActivity().window.exitTransition = Slide(Gravity.RIGHT)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSend.setOnClickListener {
            val correctAnswers = calculateCorrectAnswers()
            val bundle = Bundle().apply {
                putInt("correctAnswersCount", correctAnswers)
            }
            val transition = Slide(Gravity.RIGHT)
            transition.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
            enterTransition = transition
            exitTransition = transition

            findNavController().navigate(R.id.action_QuestionsFragment_to_ResultFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}