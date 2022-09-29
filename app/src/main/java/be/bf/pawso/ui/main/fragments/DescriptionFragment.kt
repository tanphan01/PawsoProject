package be.bf.pawso.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import be.bf.pawso.R
import be.bf.pawso.databinding.FragmentDescriptionBinding
import be.bf.pawso.ui.home.HomeActivity
import be.bf.pawso.ui.main.viewmodel.UserViewModel
import be.bf.pawso.ui.main.viewmodel.UserViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [DescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels { UserViewModelFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedUser.observe(viewLifecycleOwner) {
            if (it != null) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
            }
        }

        binding.nextButton.setOnClickListener { view: View ->
//            findNavController().navigate(R.id.action_birthdateFragment_to_descriptionFragment)
            viewModel.description = binding.etDescription.text.toString()
            viewModel.addUser()
        }

        binding.backButton.setOnClickListener { view: View ->
            findNavController().navigate(R.id.action_descriptionFragment_to_birthdateFragment)
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
         * @return A new instance of fragment DescriptionFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}