package be.bf.pawso.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import be.bf.pawso.R
import be.bf.pawso.databinding.FragmentLoginBinding
import be.bf.pawso.ui.home.HomeActivity
import be.bf.pawso.ui.main.viewmodel.UserViewModel
import be.bf.pawso.ui.main.viewmodel.UserViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels { UserViewModelFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val email : String? = pref.getString("email", null)

        if (email != null) {
            viewModel.getUserByEmail(email)
        }

        viewModel.selectedUser.observe(viewLifecycleOwner) {
            if (it != null) {
                    val email = binding.eTextEmail.text.toString()
                    val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
                    with (pref.edit()) {
                        putString("email", email)
                        putInt("ID", it.id!!)
                        commit()
                    }

                val intent = Intent (requireContext(), HomeActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
            }
            else {
                Toast.makeText(requireContext(), "Email or Password doesn't match", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegLogin.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnLog.setOnClickListener {

            viewModel.logUser(binding.eTextEmail.text.toString(),
                    binding.eTextPassword.text.toString())
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
         * @return A new instance of fragment LoginFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

