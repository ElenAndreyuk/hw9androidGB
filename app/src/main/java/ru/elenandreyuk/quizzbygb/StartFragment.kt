package ru.elenandreyuk.quizzbygb

import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import ru.elenandreyuk.quizzbygb.databinding.FragmentStartBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding?  = null
    private val binding get () = _binding!!
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.birthdayDateButton.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.title_enter_date_birth)
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.birthdayDateButton,
                     dateFormatter.format(calendar.time),
                     Snackbar.LENGTH_SHORT).show()
            }
            dateDialog .show(parentFragmentManager, "DatePicker")
        }
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
//    private fun dateDialogBuild(view: View) {
//        MaterialDatePicker.Builder
//            .datePicker()
//            .setTitleText(resources.getString(R.string.title_enter_date_birth))
//            .build().apply {
//                addOnPositiveButtonClickListener {
//                    calendar.timeInMillis = it
//                    creatureSnackBar(view)
//                }
//            }
//            .show(parentFragmentManager, DATE_PICKER_SHOW_TAG)
//    }

}