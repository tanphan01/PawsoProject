package be.bf.pawso.ui.home.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import be.bf.pawso.databinding.FragmentProfileBinding
import be.bf.pawso.ui.home.viewmodel.CatViewModel
import be.bf.pawso.ui.home.viewmodel.CatViewModelFactory
import be.bf.pawso.ui.main.MainActivity
import java.time.Instant
import java.time.ZoneId

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel : CatViewModel by activityViewModels { CatViewModelFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.User.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvEmail.text = it.email
            binding.datePicker.text = Instant.ofEpochMilli(it.birthdate).atZone(ZoneId.systemDefault()).toLocalDate().toString();
            binding.description.text = it.description

        }



        binding.logoutButton.setOnClickListener {

            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            with (pref.edit()) {
                remove("email")
                putInt("ID", -1)
                apply()
            }
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }

    companion object {
        fun newInstance(param1: String, param2: String) = ProfileFragment()
    }
}