package be.bf.pawso.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import be.bf.pawso.adapters.CatAdapter
import be.bf.pawso.databinding.FragmentExploreBinding
import be.bf.pawso.models.Cat
import be.bf.pawso.ui.home.viewmodel.CatViewModel
import be.bf.pawso.ui.home.viewmodel.CatViewModelFactory
import com.lorentzos.flingswipe.SwipeFlingAdapterView

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {

    private lateinit var binding : FragmentExploreBinding

    private lateinit var adapter : CatAdapter

    private val cats = arrayListOf<Cat>()

    private val viewModel : CatViewModel by activityViewModels { CatViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = FragmentExploreBinding.inflate(inflater,container,false)

        adapter = CatAdapter(requireContext(), cats)

        setupSwipe()
        bindViewModel()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupSwipe() {
        val flingContainer : SwipeFlingAdapterView = binding.swipe
        flingContainer.adapter = adapter

        var toast : Toast? = null

        flingContainer.setFlingListener (object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                viewModel.deleteFirstCat()
            }

            override fun onLeftCardExit(position: Any?) {
                toast?.cancel()
                toast = Toast.makeText(requireContext(), "!! 0_o !!", Toast.LENGTH_SHORT)
                toast!!.show()
            }

            override fun onRightCardExit(position: Any?) {
                toast?.cancel()
                toast = Toast.makeText(requireContext(), "!! <3 !!", Toast.LENGTH_SHORT)
                toast!!.show()

                viewModel.addToFavs(cats[0],requireContext())
            }

            override fun onAdapterAboutToEmpty(p0: Int) {
                Log.d("LIST", p0.toString());
                viewModel.getCat()
            }

            override fun onScroll(p0: Float) {

            }

        })
    }

    private fun bindViewModel() {
        viewModel.Cats.observe(viewLifecycleOwner) {
            cats.clear()
            cats.addAll(it)
            adapter.notifyDataSetChanged()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ExploreFragment()

    }
}