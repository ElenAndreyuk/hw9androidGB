package ru.elenandreyuk.quizzbygb

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
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
            findNavController().navigate(R.id.action_ResultFragment_to_QuestionsFragment)
        }
 //Анимируем результат
        ObjectAnimator.ofFloat(binding.result, View.ROTATION, 0f, 720f).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

            // Анимируем цвета заголовка
        ObjectAnimator.ofArgb(binding.resultText,
            "textColor",
            Color.parseColor("#00000000"),
            Color.parseColor("#FFFFFFFF")).apply {
                duration = 4000
            repeatCount = ObjectAnimator.INFINITE
            start()

        }
        requireActivity().window.enterTransition = Slide(Gravity.RIGHT)
        requireActivity().window.exitTransition = Slide(Gravity.RIGHT)

             return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correctAnswersCount = arguments?.getInt("correctAnswersCount") ?: 0

        val resultTextView: TextView = view.findViewById(R.id.result)
        resultTextView.text = "Количество правильных ответов: $correctAnswersCount"
        val transition = Slide(Gravity.RIGHT)
        transition.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        enterTransition = transition
        exitTransition = transition

    }


}