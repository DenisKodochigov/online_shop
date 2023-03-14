package com.example.online_shop.ui.home

import android.content.Intent
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
import com.example.online_shop.App
import com.example.online_shop.R
import com.example.online_shop.data.api.FlashDTO
import com.example.online_shop.data.api.LatestDTO
import com.example.online_shop.data.api.ProductDTO
import com.example.online_shop.data.api.ProductDiscountDTO
import com.example.online_shop.databinding.FragmentHomeBinding
import com.example.online_shop.entity.ImageFile
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
    private val latestAdapter = ListAdapter { item -> onItemClick(item) }
    private val flashAdapter = ListAdapter { item -> onItemClick(item) }
    private val brandsAdapter = ListAdapter { item -> onItemClick(item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).also {
            it.addCategory(Intent.CATEGORY_OPENABLE)
            it.type = "image/*"
            it.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar).visibility = View.VISIBLE
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar1).visibility = View.INVISIBLE
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.VISIBLE

        if (App.person.firstName != "" && App.person.password != "") {
            viewModel.loginPerson(App.person)
            viewModel.getLatestAndFlash(App.person)
        }
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

        viewModel.personGet.onEach { personDB ->
            personDB?.let {
                App.person.cp(it)

                if (App.person.photo != null){
                    context?.let { context ->
                        (activity as AppCompatActivity).findViewById<ImageView>(R.id.iv_photo)
                            .setImageBitmap(
                            ImageFile.loadImageBitmap( context, App.person.photo.toString())
                        )
                    }
                }
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
                if (!latest.latest.isNullOrEmpty() && !flash.flash_sale.isNullOrEmpty()  && !brands.latest.isNullOrEmpty()) {
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