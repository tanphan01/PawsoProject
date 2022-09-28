package be.bf.pawso.ui.main.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import be.bf.pawso.R
import be.bf.pawso.databinding.FragmentBirthdateBinding
import be.bf.pawso.ui.main.viewmodel.UserViewModel
import be.bf.pawso.ui.main.viewmodel.UserViewModelFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BirthdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BirthdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentBirthdateBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels { UserViewModelFactory(requireContext()) }

    private var date : Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBirthdateBinding.inflate(inflater, container, false)

        binding.datePicker.setOnDateChangedListener() {_, y, m, d ->
            val a = LocalDate.of(y, m, d).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            date = a
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener { view: View ->
            viewModel.birthdate = date
            findNavController().navigate(R.id.action_birthdateFragment_to_descriptionFragment)
        }

        binding.backButton.setOnClickListener { view: View ->
            findNavController().navigate(R.id.action_birthdateFragment_to_registrationFragment)
        }
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
         * @return A new instance of fragment BirthdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BirthdateFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}