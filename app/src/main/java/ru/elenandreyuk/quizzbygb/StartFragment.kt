package ru.elenandreyuk.quizzbygb

import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.elenandreyuk.quizzbygb.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding?  = null
    private val binding get () = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNext.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_StartFragment_to_QuestionsFragment)
            } catch (e: Exception) {
                Log.e("Navigation", "Navigation failed: ${e.message}")
            }
        }
        val transition = Slide(Gravity.RIGHT)
        transition.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        enterTransition = transition
        exitTransition = transition
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}