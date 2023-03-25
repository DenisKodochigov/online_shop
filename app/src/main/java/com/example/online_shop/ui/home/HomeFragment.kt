package com.example.online_shop.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.example.online_shop.R
import com.example.online_shop.data.api.dto.FlashDTO
import com.example.online_shop.data.api.dto.LatestDTO
import com.example.online_shop.data.api.dto.ProductDTO
import com.example.online_shop.data.api.dto.ProductDiscountDTO
import com.example.online_shop.databinding.FragmentHomeBinding
import com.example.online_shop.entity.Person
import com.example.online_shop.ui.adapter.ListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val person = Person()
    private val latestAdapter = ListAdapter { item -> onItemClick(item) }
    private val flashAdapter = ListAdapter { item -> onItemClick(item) }
    private val brandsAdapter = ListAdapter { item -> onItemClick(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar).visibility =
            View.VISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1).visibility =
            View.INVISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility =
            View.VISIBLE

        person.firstName = arguments?.getString("firstName") ?: ""
        person.password = arguments?.getString("password") ?: ""

        if (person.firstName != "" && person.lastName != "" && person.password != "")
            viewModel.getPerson(person)
        viewModel.getLatestAndFlash(person)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var latest = LatestDTO()
        var flash = FlashDTO()
        binding.includeLatestHome.tvHeading.text = getString(R.string.latest_deal)
        binding.includeFlashHome.tvHeading.text = getString(R.string.flash_deal)
        binding.includeBrandsHome.tvHeading.text = getString(R.string.brands_header)
        binding.includeLatestHome.recyclerHorizontal.adapter = latestAdapter
        binding.includeFlashHome.recyclerHorizontal.adapter = flashAdapter
        binding.includeBrandsHome.recyclerHorizontal.adapter = brandsAdapter
        viewModel.person.onEach { personDB ->
            personDB?.let { person.cp(it) }
            if (person.photo != null) {
                (activity as AppCompatActivity).findViewById<ImageView>(R.id.iv_photo)
                    .setImageURI(Uri.parse(person.photo))
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.latest.onEach {
            if (it != null) {
                latest = it
                viewModel.getFlash()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.flash.onEach {
            if (it != null) {
                flash = it
                viewModel.getBrands()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.brands.onEach {
            if (it != null) {
                val brands = it
                if (!latest.latest.isNullOrEmpty() && !flash.flash_sale.isNullOrEmpty() && !brands.latest.isNullOrEmpty()) {
                    latestAdapter.setList(latest.latest as List<ProductDTO>)
                    flashAdapter.setList(flash.flash_sale as List<ProductDiscountDTO>)
                    brandsAdapter.setList(brands.latest as List<ProductDTO>)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: Any) {
//        findNavController().navigate(R.id.action_nav_home_to_nav_item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}