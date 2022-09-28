package be.bf.pawso.ui.main.fragments

import android.content.Intent
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import be.bf.pawso.R
import be.bf.pawso.databinding.FragmentRegistrationBinding
import be.bf.pawso.ui.home.HomeActivity
import be.bf.pawso.ui.main.viewmodel.UserViewModel
import be.bf.pawso.ui.main.viewmodel.UserViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels { UserViewModelFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        emailFocusListener()
        passwordFocusListener()

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogRegister.setOnClickListener { view: View ->
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.btnReg.setOnClickListener { view: View ->

            val mailValidity = validEmail()
            val passwordValidity = validPassword()

            if (mailValidity == null && passwordValidity == null) {
                viewModel.userName = binding.eTextName.text.toString()
                viewModel.email = binding.eTextEmail.text.toString()
                viewModel.password = binding.eTextPassword.text.toString()
                findNavController().navigate(R.id.action_registrationFragment_to_birthdateFragment)
            } else {
                val mailMessage = mailValidity ?: ""
                val passwordMessage = passwordValidity ?: ""

                val message = mailMessage + "\n" + passwordMessage

                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

                binding.eTextPassword.text = null
            }
        }

    }
    private fun emailFocusListener() {
        binding.eTextEmail.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.layoutEmail.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = binding.eTextEmail.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.eTextPassword.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.layoutPassword.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {
        val passwordText = binding.eTextPassword.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Characters Password"
        }
        if (!passwordText.matches(".*[A-Z].*.".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*.".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special-case Character (@#\$%^&+=)"
        }
            return null
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
         * @return A new instance of fragment RegistrationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}