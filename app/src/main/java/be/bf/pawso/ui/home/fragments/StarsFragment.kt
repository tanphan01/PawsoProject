package be.bf.pawso.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import be.bf.pawso.adapters.CatDetailAdapter
import be.bf.pawso.databinding.FragmentStarsBinding
import be.bf.pawso.models.CatWithShelter
import be.bf.pawso.ui.home.MessageActivity
import be.bf.pawso.ui.home.viewmodel.CatViewModel
import be.bf.pawso.ui.home.viewmodel.CatViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StarsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StarsFragment : Fragment() {

    private lateinit var binding : FragmentStarsBinding

    private lateinit var adapter: CatDetailAdapter

    private var cats = mutableListOf<CatWithShelter>()

    private val viewModel : CatViewModel by activityViewModels { CatViewModelFactory(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = FragmentStarsBinding.inflate(inflater, container, false)

        adapter = CatDetailAdapter(requireContext(), cats) {
            Log.d("cat", it.toString())

            val intent = Intent(requireContext(), MessageActivity::class.java)
            intent.putExtra("cat", it)
            startActivity(intent)
        }



        binding.starsRecycler.adapter = adapter
        binding.starsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun bindViewModel() {
        viewModel.UserCats.observe(viewLifecycleOwner) {
            cats = it.toMutableList()
            adapter.update(cats)
        }

        viewModel.getUserCat()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StarsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StarsFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}